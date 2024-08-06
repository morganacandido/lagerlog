let precoTotal = 0;
let produtosNoCarrinho = [];

document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll('.ticket').forEach(function(ticket) {
        ticket.addEventListener('click', function() {
            var produtoId = ticket.id;
            var nomeProduto = ticket.querySelector('.nomeProduto').innerText;
            var precoProduto = parseFloat(ticket.querySelector('.precoProduto').innerText);
    
            var produtoExistente = document.querySelector('#itensCarrinho .detalhes-produto[data-produto="' + produtoId + '"]');
            
            if (produtoExistente) {
                var quantidadeAtual = parseInt(produtoExistente.getAttribute('data-quantidade'));
                quantidadeAtual++;
                produtoExistente.setAttribute('data-quantidade', quantidadeAtual);
                atualizarTextoQuantidade(produtoExistente, precoProduto, quantidadeAtual);
            } else {
                var carrinho = document.querySelector('#itensCarrinho');
                var produto = document.createElement('div');
                produto.classList.add('detalhes-produto');
                produto.setAttribute('data-produto', produtoId);
                produto.setAttribute('data-preco', precoProduto);
                produto.setAttribute('data-quantidade', 1); // Inicializa a quantidade como 1
    
                var itemCarrinho = document.createElement('p');
                itemCarrinho.textContent = nomeProduto + " R$ " + precoProduto;
                produto.appendChild(itemCarrinho);

                var quantidadeControle = document.createElement('div');
                quantidadeControle.classList.add('quantidade-controle');

                var diminuirBtn = document.createElement('button');
                diminuirBtn.textContent = '-';
                diminuirBtn.addEventListener('click', function() {
                    var quantidadeAtual = parseInt(produto.getAttribute('data-quantidade'));
                    if (quantidadeAtual > 1) {
                        quantidadeAtual--;
                        produto.setAttribute('data-quantidade', quantidadeAtual);
                        produto.querySelector('.quantidade').textContent = "QTD: " + quantidadeAtual + " | R$ " + (quantidadeAtual * precoProduto).toFixed(2);
                        var precoTotal = calcularPrecoTotal();
                        document.querySelector('#total').textContent = "R$ " + precoTotal.toFixed(2);
                    } else if (quantidadeAtual == 1){
                        produto.remove();
                        var precoTotal = calcularPrecoTotal();
                        document.querySelector('#total').textContent = "R$ " + precoTotal.toFixed(2);
                    }
                });

                var aumentarBtn = document.createElement('button');
                aumentarBtn.textContent = '+';
                aumentarBtn.addEventListener('click', function() {
                    var quantidadeAtual = parseInt(produto.getAttribute('data-quantidade'));
                    quantidadeAtual++;
                    produto.setAttribute('data-quantidade', quantidadeAtual);
                    produto.querySelector('.quantidade').textContent = "QTD: " + quantidadeAtual + " | R$ " + (quantidadeAtual * precoProduto).toFixed(2);
                    var precoTotal = calcularPrecoTotal();
                    document.querySelector('#total').textContent = "R$ " + precoTotal.toFixed(2);
                });

                var removerBtn = document.createElement('button');
                removerBtn.textContent = 'x';
                removerBtn.addEventListener('click', function() {
                produto.remove();
                var precoTotal = calcularPrecoTotal();
                document.querySelector('#total').textContent = "R$ " + precoTotal.toFixed(2);
                });

                var quantidade = document.createElement('p');
                quantidade.classList.add('quantidade');
                quantidade.textContent = "QTD: 1" + " | R$ " + precoProduto.toFixed(2);
                var divButton = document.createElement('div');
                divButton.setAttribute('id', "buttonCarrinho");
                quantidadeControle.appendChild(divButton);
                divButton.appendChild(diminuirBtn);
                divButton.appendChild(aumentarBtn);
                divButton.appendChild(removerBtn);
                quantidadeControle.appendChild(quantidade);
                produto.appendChild(quantidadeControle);
    
                carrinho.appendChild(produto);
            }
    
            var precoTotal = calcularPrecoTotal();
            document.querySelector('#total').textContent = "R$ " + precoTotal.toFixed(2);
        });
    });
    
    function atualizarTextoQuantidade(produto, precoProduto, quantidade) {
        var valorTotal = (precoProduto * quantidade).toFixed(2);
        produto.querySelector('.quantidade').textContent = "QTD: " + quantidade + " | R$ " + valorTotal;
    }

    function calcularPrecoTotal() {
        var detalhesProdutos = document.querySelectorAll('#itensCarrinho .detalhes-produto');
        var total = 0;
    
        detalhesProdutos.forEach(function(produto) {
            var precoTexto = produto.querySelector('p').textContent;
            var preco = parseFloat(precoTexto.split('R$ ')[1]);
            var quantidade = parseInt(produto.getAttribute('data-quantidade'));
            total += preco * quantidade;
        });
        return total;
    }

    document.getElementById('btnLimpar').addEventListener('click', function () {
        precoTotal = 0;
        document.getElementById('total').textContent = "Total: R$ " + precoTotal.toFixed(2);

        var carrinhoDiv = document.querySelector('#itensCarrinho');
        carrinhoDiv.innerHTML = "";
    });


    document.getElementById('btnFinalizar').addEventListener('click', function(){

        var itensCarrinho = document.getElementById('itensCarrinho');
        var produtos = itensCarrinho.querySelectorAll('.detalhes-produto');
        console.log(produtos.length);

        if(produtos.length > 0){
            if (confirm('Deseja realmente finalizar a venda?')) {
            
                var carrinho = [];
    
                produtos.forEach(function (produto) {
                    var idProduto = produto.getAttribute('data-produto');
                    var precoProduto = produto.getAttribute('data-preco');
                    var quantidadeProduto = produto.getAttribute('data-quantidade');
    
                    carrinho.push({
                        id: idProduto,
                        preco: precoProduto,
                        quantidade: quantidadeProduto
                    });
                });
    
                var url = 'http://localhost:8080/venda/finalizar';
    
                fetch(url, {
                    method: 'POST',
                    headers:{
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(carrinho)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('A resposta da rede não foi ok ' + response.statusText);
                    }
                    return response.text();
                })
                .then(data => {
                    console.log('Venda finalizada com sucesso:', data);
                    var btnLimpar = document.getElementById('btnLimpar');
                    btnLimpar.click();
    
                })
                .catch(error => {
                    console.error('Houve um problema com a operação fetch:', error);
                });
            } else {
                alert('Venda não finalizada.');
            }
        } else {
            alert('Carrinho sem produtos.');
        }
    });

    

});
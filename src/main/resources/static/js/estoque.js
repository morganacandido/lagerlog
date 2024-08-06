document.addEventListener("DOMContentLoaded", function() {
    function atualizarUnidade() {
        var select = document.getElementById("produto");
        var selectedOption = select.options[select.selectedIndex];
        var unidadeCadastro = selectedOption.getAttribute("data-unidade-cadastro");
        document.getElementById("unidadeCadastro").value = unidadeCadastro;
    }

    var selectProduto = document.getElementById("produto");
    selectProduto.addEventListener("change", atualizarUnidade);

    atualizarUnidade();
    
});
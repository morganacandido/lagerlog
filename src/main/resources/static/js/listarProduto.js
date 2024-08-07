document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll('.btnDelete').forEach(function (btn) {
        btn.addEventListener('click', function () {
            var id = this.getAttribute('data-id');
            var url = '/produto/' + id;

            fetch(url, {
                method: 'DELETE'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Erro ao executar solicitação DELETE');
                    }
                    window.location.reload(true);
                    console.log('Solicitação DELETE enviada com sucesso');
                })
                .catch(error => {
                    console.error('Erro ao executar solicitação DELETE:', error);
                    // Lidar com erros
                });
        });
    });

    document.querySelectorAll('.btnUpdate').forEach(function (btn) {
        btn.addEventListener('click', function () {
            var id = this.getAttribute('data-id');
            window.location.href = '/produto/editar/' + id;
        });
    });

});

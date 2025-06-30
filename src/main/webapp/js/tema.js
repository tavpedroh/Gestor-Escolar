
document.addEventListener('DOMContentLoaded', function() {


    function getCookie(nome) {

        const cookies = document.cookie.split(';').reduce((acc, cookie) => {

            const [key, value] = cookie.trim().split('=');
            acc[key] = value;
            return acc;
        }, {});
        return cookies[nome] || null;
    }

    function aplicarTema() {
        const corFundo = getCookie("corFundo");
        const corFonte = getCookie("corFonte");

        if (corFundo) {
            document.body.style.backgroundColor = corFundo;
        }
        if (corFonte) {
            document.body.style.color = corFonte;
            document.querySelectorAll('a').forEach(link => {
                link.style.color = corFonte;
            });
        }

        document.body.classList.remove('hidden');
    }

    aplicarTema();
});

const filtroForm = document.getElementById("filtroForm");
const filtroNomeInput = document.getElementById("filtroNome");
const tabelaCursosTbody = document.querySelector("#tabelaCursos tbody");

async function buscarCursos(nome = '') {
    const url = nome 
        ? `/gestor_escolar/cursos-json?q=${encodeURIComponent(nome)}` 
        : "/gestor_escolar/cursos-json";

    try {
        const resposta = await fetch(url);
        if (!resposta.ok) throw new Error('Erro na resposta do servidor.');

        const cursos = await resposta.json();

        tabelaCursosTbody.innerHTML = "";

        if (cursos.length === 0) {
            tabelaCursosTbody.innerHTML = '<tr><td colspan="4" style="text-align:center;">Nenhum curso encontrado.</td></tr>';
            return;
        }

        let htmlRows = "";
        cursos.forEach(curso => {
            htmlRows += `
                <tr id="curso-row-${curso.id}">
                    <td>${curso.id}</td>
                    <td>${curso.nome}</td>
                    <td>${curso.duracaoSemestres}</td>
                    <td>
                        <button class="btn-excluir" data-id="${curso.id}">Excluir</button>
                    </td>
                </tr>`;
        });
        tabelaCursosTbody.innerHTML = htmlRows;

    } catch (error) {
        console.error("Falha ao buscar cursos:", error);
        tabelaCursosTbody.innerHTML = '<tr><td colspan="4" style="text-align:center;">Erro ao carregar os cursos.</td></tr>';
    }
}

async function excluirCurso(cursoId) {
    if (!confirm(`Tem certeza que deseja excluir o curso com ID ${cursoId}?`)) {
        return;
    }

    try {
        const resposta = await fetch(`/gestor_escolar/cursos-json/${cursoId}`, {
            method: 'DELETE'
        });
        console.log(`Tentando excluir o curso com ID: ${cursoId}`);
        if (resposta.ok) {
            const resultadoJson = await resposta.json();
            console.log(resultadoJson.mensagem);
            document.getElementById(`curso-row-${cursoId}`).remove();
        } else {
            const erroJson = await resposta.json();
            alert(`Erro ao excluir: ${erroJson.erro}`);
        }
    } catch (error) {
        console.error("Erro de rede ao excluir:", error);
        alert("Não foi possível conectar ao servidor para excluir.");
    }
}

filtroForm.addEventListener("submit", function(event) {
    event.preventDefault();
    const nome = filtroNomeInput.value;
    buscarCursos(nome);
});

tabelaCursosTbody.addEventListener("click", function(event) {
    if (event.target.classList.contains('btn-excluir')) {
        const cursoId = event.target.dataset.id;
        excluirCurso(cursoId);
    }
});

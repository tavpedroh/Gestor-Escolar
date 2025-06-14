<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
</head>

<body>
    <header>
        <h1>Gestor Escolar - Menu</h1>
    </header>

    <h2>Menu Principal</h2>
    <section class="menu_principal">
        <div class="cadastro">
            <h2>Novos Cadastros</h2>
            <nav>
                <a href="/gestor_escolar/cursos?next=Aluno/cadastroAluno.jsp" target="_blank"> Cadastrar - Aluno </a> 
                <a href="/gestor_escolar/Curso/cadastroCurso.jsp" target="_blank"> Cadastrar - Curso</a> 
                <a href="/gestor_escolar/cursos?next=Disciplina/cadastroDisciplina.jsp" target="_blank"> Cadastrar - Disciplina </a>
            </nav>
        </div>
        <div class="relatorio">
            <h2>Relatórios</h2>
            <nav>
                <a href="/gestor_escolar/cursos?next=Aluno/relatorioAluno.jsp" target="_blank"> Relatório - Aluno </a> 
                <a href="/gestor_escolar/Curso/relatorioCurso.jsp" target="_blank"> Relatório - Curso </a> 
                <a href="/gestor_escolar/cursos?next=Disciplina/relatorioDisciplina.jsp" target="_blank"> Relatório - Disciplina </a>
            </nav>
        </div>
    </section>
    <h2>Json</h2>
    <a href="/gestor_escolar/Aluno/frm/frmAlunoListar.html" target="_blank"> Relatório - Aluno </a> <br>
    <a href="/gestor_escolar/Curso/frm/frmCursoListar.html" target="_blank"> Relatório - Curso </a> <br>
    <a href="/gestor_escolar/Disciplina/frm/frmDisciplinaListar.html" target="_blank"> Relatório - Disciplina </a> <br><br>

</body>
</html>
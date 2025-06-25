<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="jakarta.servlet.http.Cookie" %>

<%
    String corFundo = "white";
    String corFonte = "black";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("corFundo")) corFundo = c.getValue();
            if (c.getName().equals("corFonte")) corFonte = c.getValue();
        }
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
</head>
<style>
    a { 
        text-decoration: none;
    }
</style>
<body<% if (corFundo != null && !corFundo.isEmpty() && corFonte != null && !corFonte.isEmpty()) { %> style="background-color: <%= corFundo %>; color: <%= corFonte %>;"<% } %>>
    <header>
        <h1>Gestor Escolar - Menu</h1>
    </header>

    <h2>Menu Principal</h2>
    <section class="menu_principal">
        <div class="cadastro">
            <h2>Novos Cadastros</h2>
            <nav>
                <a href="/gestor_escolar/cursos?next=Aluno/cadastroAluno.jsp" target="_blank"> Cadastrar - Aluno </a> 
                <a href="/gestor_escolar/cursos?next=Aluno/cadastroAluno.jsp" target="_blank" > Cadastrar - Aluno </a> 
                <a href="/gestor_escolar/Curso/cadastroCurso.jsp" target="_blank" > Cadastrar - Curso</a> 
                <a href="/gestor_escolar/cursos?next=Disciplina/cadastroDisciplina.jsp" target="_blank"> Cadastrar - Disciplina </a>
        </div>
        <div class="relatorio">
            <h2>Relatórios</h2>
            <nav>
                <a href="/gestor_escolar/cursos?next=Aluno/relatorioAluno.jsp" target="_blank"> Relatório - Aluno </a> 
                <a href="/gestor_escolar/cursos?next=Aluno/relatorioAluno.jsp" target="_blank" > Relatório - Aluno </a> 
                <a href="/gestor_escolar/Curso/relatorioCurso.jsp" target="_blank" > Relatório - Curso </a> 
                <a href="/gestor_escolar/cursos?next=Disciplina/relatorioDisciplina.jsp" target="_blank" > Relatório - Disciplina </a>
        </div>
    </section>

    <h2>Json</h2>

    <h3>Curso</h3>
    <a href="/gestor_escolar/Curso/frm/frmCursoInserir.html" target="_blank"> Inserir - Curso </a> <br>
    <a href="/gestor_escolar/Curso/frm/frmCursoListar.html" target="_blank" > Listar - Curso </a> <br>
    <h2>Cadastro de Usuário</h2>
    <a href="/gestor_escolar/Usuario/cadastroUsuario.html" target="_blank"> Cadastro de Usuário </a> <br><br>

    <h2>Login</h2>
    <a href="/gestor_escolar/index.jsp" target="_blank"> Login </a> <br><br>

</body>
</html>
<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Curso</title>
    <link rel="stylesheet" href="./curso.css">
</head>
<body>
    <h1>Gestor Escolar - Cadastrar Curso</h1>
    <hr>
    <form action="/gestor_escolar/cursos" method="post">

        Nome: <input type="text" name="nome">
        <br><br>
        Duracao em Semetres: <input type="text" name="duracao_semetres">
        <br><br>
        <input type="hidden" name="_method" value="POST">
        <input type="submit" value="Cadastrar">
    </form>
    <p>
        <a href="/gestor_escolar/Menu/menu.jsp">Voltar para o menu.</a>
    </p>
</body>
</html>
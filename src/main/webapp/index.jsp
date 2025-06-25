<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Gestor Escolar - Login</h2>
    <form action="/gestor_escolar/login" method="post">

        <input type="text" placeholder="Login" name="login" required>
        <input type="password" placeholder="Senha" name="senha" required>
        
        <button type="submit">Entrar</button>
    </form>
    <p> ${msg} </p>
</body>
</html>

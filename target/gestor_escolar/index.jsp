<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <header>
        <h1>Gestor Escolar - Login</h1>
    </header>
    <main>
        <form action="${pageContext.request.contextPath}/login" method="post">

            <input type="text" placeholder="Login" name="login" required>
            <input type="password" placeholder="Senha" name="senha" required>
            
            <button type="submit">Entrar</button>
        </form>
        <p> ${msg} </p>
    </main>
</body>
</html>

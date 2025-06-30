<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    <style>
      :root {
        --cor-fundo-app: ${corFundo};
        --cor-fonte-app: ${corFonte};
      }
    </style>
</head>

<c:set var="corFundo" value="#FFFFFF" />
<c:set var="corFonte" value="#333333" />

<c:if test="${not empty cookie.corFundo}">
    <c:set var="corFundo" value="${cookie.corFundo.value}" />
</c:if>
<c:if test="${not empty cookie.corFonte}">
    <c:set var="corFonte" value="${cookie.corFonte.value}" />
</c:if>

<body style="--cor-fundo-app: ${corFundo}; --cor-fonte-app: ${corFonte};">
    <header>
        <h1>Gestor Escolar - Cadastro de Usuário</h1>
    </header>
    <main>
        <form action="${pageContext.request.contextPath}/usuario" method="post">
            <ul class="usuario-form">
                <li>Nome: <input type="text" name="nome" required></li>
                <li>Login: <input type="text" name="login" required></li>
                <li>Senha: <input type="password" name="senha" required></li>
                <li>Cor (Fundo): <input type="color" name="corDeFundo" required></li>
                <li>Cor (Fonte): <input type="color" name="corDeFonte" required></li>
                <li><input type="submit" value="Cadastrar"></li>
            </ul>
        </form>
        <a href="${pageContext.request.contextPath}/Menu/menu.jsp">Voltar para o menu.</a>
    </main>
</body>
</html>


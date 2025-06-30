<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Aluno</title>
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
        <h1>Gestor Escolar - Cadastrar Aluno</h1>
    </header>
    <main>
        <form action="${pageContext.request.contextPath}/alunos" method="post">
            Nome: <input type="text" name="nome" required>
            Data Ingresso: <input type="date" name="data_ingresso" required>
            Curso:  <select name="idCurso" required>
                        <option value="">Selecione um curso</option>
                        <c:forEach var="cur" items="${cursosParaOSelect}">
                            <option value="${cur.id}"> ${cur.nome} </option>
                        </c:forEach>
                    </select> 
            <input type="hidden" name="_method" value="POST">
            <input type="submit" value="Cadastrar">
        </form>
        <a href="${pageContext.request.contextPath}/Menu/menu.jsp">Voltar para o menu.</a>
    </main>
</body>
</html>
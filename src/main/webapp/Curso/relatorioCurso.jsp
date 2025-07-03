<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatorio Curso</title>
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
        <h1>Gestor Escolar - Relatório de Cursos</h1>
    </header>
    <main>
        <form action="${pageContext.request.contextPath}/cursos" method="get">
            Nome: <input type="text" name="q" value="${param.q}">
            <input type="hidden" name="acao" value="listar">   
            <input type="submit" value="Gerar Relatório">
        </form>
    
    <c:if test="${param.q != null}">
        <h2>Resultados</h2>
        <c:if test="${not empty listaDeCursos}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Curso</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="curso" items="${listaDeCursos}">
                        <tr>
                            <td>${curso.id}</td>
                            <td>${curso.nome}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/cursos" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este curso?');">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <input type="hidden" name="id" value="${curso.id}">
                                    <input type="hidden" name="q" value="${param.q}">
                                    <input type="submit" value="Excluir">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listaDeCursos}">
            <p style="text-align: center; font-weight: bold; margin-top: 20px;">
                Nenhum curso encontrado com os filtros aplicados.
            </p>
        </c:if>
    </c:if>
    <a href="${pageContext.request.contextPath}/Menu/menu.jsp">Voltar para o menu.</a>
    </main>
</body>

</html>
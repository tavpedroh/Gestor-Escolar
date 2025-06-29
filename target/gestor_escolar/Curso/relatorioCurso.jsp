<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatorio Curso</title>
    
</head>
<body>
    <h1>Gestor Escolar - Relatório de Cursos</h1>
    <hr>

    <form action="${pageContext.request.contextPath}/cursos" method="get">
        <input type="hidden" name="acao" value="listar">

        Nome:
        <input type="text" name="q" value="${param.q}">   

        <br><br>
        <input type="submit" value="Gerar Relatório">
    </form>
    <c:if test="${param.q != null}">
        <br>
        <hr>
        <h2>Resultados</h2>
        <hr>
        <br>
        <c:if test="${not empty listaDeCursos}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Curso</th>
                        <th>Curso</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="curso" items="${listaDeCursos}">
                        <tr>
                            <td>${curso.nome}</td>
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
    <br><br>
    <p><a href="${pageContext.request.contextPath}/menu.jsp">Voltar para o menu.</a></p>

</body>

</html>
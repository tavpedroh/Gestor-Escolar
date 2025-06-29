<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatorio Disciplina</title>
</head>
<body>
    <h1>Gestor Escolar - Relatorio Disciplina</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/disciplinas" method="get">
        <input type="hidden" name="acao" value="listar">

        Nome:
        <input type="text" name="q" value="${param.q}">
        <br><br>

        Curso: 
        <select name="idCurso">
            <option value=""> Todos </option>
            <c:forEach var="cur" items="${cursosParaOSelect}">
                <option value="${cur.id}" ${param.idCurso == cur.id.toString() ? 'selected' : ''}>
                     ${cur.nome}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="Gerar Relatório">
    </form>


    <c:if test="${param.q != null}">
        <br>
        <hr>
        <h2>Resultados</h2>
        <hr>
        <br>
        <c:if test="${not empty listaDeDisciplinas}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Curso</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="disciplina" items="${listaDeDisciplinas}">
                        <tr>
                            <td>${disciplina.nome}</td>
                            <td>${disciplina.curso.nome}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/disciplinas" method="post" onsubmit="return confirm('Tem certeza que deseja excluir esta disciplina?');">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <input type="hidden" name="id" value="${disciplina.id}">
                                    <input type="hidden" name="q" value="${param.q}">
                                    <input type="hidden" name="idCurso" value="${param.idCurso}">
                                    <input type="submit" value="Excluir">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listaDeDisciplinas}">
            <p style="text-align: center; font-weight: bold; margin-top: 20px;">
                Nenhum disciplina encontrado com os filtros aplicados.
            </p>
        </c:if>
    </c:if>
    <br><br>
    <p><a href="${pageContext.request.contextPath}/menu.jsp">Voltar para o menu.</a></p>
</body>
</html>
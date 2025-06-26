<%-- Diretivas no topo continuam iguais --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatório de Alunos</title>
</head>
<body>
    <h1>Gestor Escolar - Relatório de Alunos</h1>
    <hr>

    <form action="${pageContext.request.contextPath}/alunos" method="get">
        <input type="hidden" name="acao" value="listar">

        Nome:
        <input type="text" name="q" value="${param.q}">

        <br><br>

        Curso: 
        <select name="idCurso">
            <option value="">Todos</option>
            <c:forEach var="cur" items="${cursosParaOSelect}">
                <option value="${cur.id}" ${param.idCurso == cur.id ? 'selected' : ''}>
                    ${cur.nome}
                </option>
            </c:forEach>
        </select>

        <br><br>
        <input type="submit" value="Gerar Relatório">
    </form>
    <c:if test="${param.q != null}">
        <br>
        <hr>
        <h2>Resultados</h2>
        <hr>
        <br>
        <c:if test="${not empty listaDeAlunos}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Curso</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="aluno" items="${listaDeAlunos}">
                        <tr>
                            <td>${aluno.nome}</td>
                            <td>${aluno.curso.nome}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/alunos" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este aluno?');">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <input type="hidden" name="id" value="${aluno.id}">
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
        <c:if test="${empty listaDeAlunos}">
            <p style="text-align: center; font-weight: bold; margin-top: 20px;">
                Nenhum aluno encontrado com os filtros aplicados.
            </p>
        </c:if>
    </c:if>
    <br><br>
    <p><a href="${pageContext.request.contextPath}/menu.jsp">Voltar para o menu.</a></p>

</body>
</html>
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
    <form action="/gestor_escolar/relatorioDisciplinas" method="get">

        Nome:
        <input type="text" name="q">
        <br><br>

        Curso: 
        <select name="idCurso">
            <option value=""> Todos </option>
            <c:forEach var="cur" items="${cursos}">
                <option value="${cur.id}" ${param.idCurso == cur.id.toString() ? 'selected' : ''}>
                     ${cur.nome}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <input type="hidden" name="next" value="cursos?next=Disciplina/relatorioDisciplina.jsp"><br>
        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="Gerar Relatório">
    </form>


    <br>
    <h2>Relatório de Disciplinas</h2>
    <br>
    <table border="1">
        <tr>
            <th>Disciplina</th>
            <th>Curso</th>
            <!-- <th>Editar</th> -->
            <th>Excluir</th>
        </tr>
        <c:forEach var="rel" items="${relatorioD}">
            <tr>
                <td>${rel.nomeDisciplina}</td>
                <td>${rel.nomeCurso}</td>
                <!-- <td>
                    <form action="/gestor_escolar/disciplinas" method="post">
                        <input type="hidden" name="next" value="Disciplina/relatorioDisciplina.jsp">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="id" value="${rel.idDisciplina}">
                        <input type="hidden" name="q" value="${param.q}">
                        <input type="submit" value="Editar">
                    </form>
                </td> -->
                <td>
                    <form action="/gestor_escolar/disciplinas" method="post">
                        <input type="hidden" name="next" value="Disciplina/relatorioDisciplina.jsp">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="id" value="${rel.idDisciplina}">
                        <input type="hidden" name="q" value="${param.q}">
                        <input type="submit" value="Excluir">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a href="/gestor_escolar/menu.jsp">Voltar para o menu.</a>
    </p>
</body>
</html>
<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatorio Aluno</title>
    

</head>
<body>
    <h1>Gestor Escolar - Relatório de Alunos</h1>
    <hr>

    <form action="/gestor_escolar/relatorioAlunos" method="get">
        Nome:
        <input type="text" name="q" value="${q}">

        <br><br>

        Curso: 
        <select name="idCurso">
            <option value="">Todos</option>
            <c:forEach var="cur" items="${cursos}">
               <option value="${cur.id}" ${param.idCurso == cur.id.toString() ? 'selected' : ''}>
                     ${cur.nome}
                </option>
            </c:forEach>
        </select>

        <br><br>
        <input type="hidden" name="next" value="cursos?next=Aluno/relatorioAluno.jsp">
        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="Gerar Relatório">
    </form>

    <br>
    <hr>
    <h2>Relatório de Alunos</h2>
    <hr>
    <br>

    <table border="1">
        <tr>
            <th>Nome</th>
            <th>Curso</th>
            <!-- <th>Editar</th> -->
            <th>Excluir</th>
        </tr>

        <c:forEach var="rel" items="${relatorioA}">
            <tr>
                <td>${rel.nomeAluno}</td>
                <td>${rel.nomeCurso}</td>
                <!-- <td>
                    <form action="/gestor_escolar/alunos" method="post">
                        <input type="hidden" name="next" value="Aluno/relatorioAluno.jsp">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="id" value="${rel.id}">
                        <input type="hidden" name="q" value="${param.q}">
                        <input type="hidden" name="idCurso" value="${param.idCurso}">
                        <input type="submit" value="Editar">
                    </form>
                </td> -->
                <td>
                    <form action="/gestor_escolar/alunos" method="post">
                        <input type="hidden" name="next" value="Aluno/relatorioAluno.jsp">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="id" value="${rel.id}">
                        <input type="hidden" name="q" value="${param.q}">
                        <input type="hidden" name="idCurso" value="${param.idCurso}">
                        <input type="submit" value="Excluir">
                    </form>
                </td>
                
            </tr>
        </c:forEach>
    </table>
    
    <p>
        <a href="/gestor_escolar/Menu/menu.jsp">Voltar para o menu.</a>
    </p>

</body>

</html>
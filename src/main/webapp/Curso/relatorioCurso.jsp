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
    <h1>Gestor Escolar - Relatorio Curso</h1>
    <hr>
    <form action="/gestor_escolar/relatorioCursos" method="get">
        
        Nome:
        <input type="text" name="q">
        
        <br><br>   

        <input type="hidden" name="next" value="Curso/relatorioCurso.jsp"><br>
        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="Gerar Relatório">
    </form>
    <br>
    <h2>Relatório de Cursos</h2>
    <br>
    <table border="1">
        <tr>
            <th>Curso</th>
            <th>Quantidade de Alunos</th>
            <th>Quantidade de Disciplinas</th>
            <!-- <th>Editar</th> -->
            <th>Excluir</th>
        </tr>
        <c:forEach var="rel" items="${relatorioC}">
            <tr>
                <td>${rel.nomeCurso}</td>
                <td>${rel.quantidadeAluno}</td>
                <td>${rel.quantidadeDisciplina}</td>

                <!-- <td>
                    <form action="/gestor_escolar/cursos" method="post">
                        <input type="hidden" name="next" value="Curso/relatorioCurso.jsp">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="id" value="${rel.idCurso}">
                        <input type="hidden" name="q" value="${param.q}">
                        <input type="submit" value="Editar">
                    </form>
                </td> -->
                <td>
                    <form action="/gestor_escolar/cursos" method="post">
                        <input type="hidden" name="next" value="Curso/relatorioCurso.jsp">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="id" value="${rel.idCurso}">
                        <input type="hidden" name="q" value="${param.q}">
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
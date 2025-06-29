<%@ pagecontentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglibprefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Disciplina</title>
</head>
<body>
    <h1>Gestor Escolar - Cadastrar Disciplina</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/disciplinas" method="post">

        Nome: <input type="text" name="nome">
        <br><br>
        Carga Horaria: <input type="number" name="carga_horaria">
        <br><br>
        Curso:  <select name="idCurso">
                    <option value="">Selecione um curso</option>
                    <c:forEach var="cur" items="${cursosParaOSelect}">
                        <option value="${cur.id}"> ${cur.nome} </option>
                    </c:forEach>
                </select> 
        <br><br>
        
        <input type="hidden" name="_method" value="POST">
        <input type="submit" value="Cadastrar">
    </form>
    <p>
        <a href="${pageContext.request.contextPath}/menu.jsp">Voltar para o menu.</a>
    </p>
</body>
</html>
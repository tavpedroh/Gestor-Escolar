<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Aluno</title>
</head>
<body>
<h2>Editar Aluno</h2>

<form action="/gestor_escolar/relatorioAlunos/${aluno.id}" method="post">
    <input type="hidden" name="_method" value="PUT">

    Nome:
    <input type="text" name="nome" value="${aluno.nome}" required><br><br>

    Data de Ingresso:
    <input type="date" name="dataIngresso" value="${aluno.dataIngresso}" required><br><br>

    Curso:
    <select name="cursoId" required>
        <c:forEach var="curso" items="${cursos}">
            <option value="${curso.id}" ${curso.id == aluno.curso.id ? 'selected' : ''}>${curso.nome}</option>
        </c:forEach>
    </select><br><br>

    <input type="submit" value="Salvar">
</form>
</body>
</html>

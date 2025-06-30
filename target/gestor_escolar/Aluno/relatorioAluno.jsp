<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Relatório de Alunos</title>
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
        <h1>Gestor Escolar - Relatório de Alunos</h1>
    </header>
    <main>
        <form action="${pageContext.request.contextPath}/alunos" method="get">           
            Nome: <input type="text" name="q" value="${param.q}">
            Curso:  <select name="idCurso">
                        <option value="">Todos</option>
                        <c:forEach var="cur" items="${cursosParaOSelect}">
                            <option value="${cur.id}" ${param.idCurso == cur.id ? 'selected' : ''}>
                                ${cur.nome}
                            </option>
                        </c:forEach>
                    </select>
            <input type="hidden" name="acao" value="listar">
            <input type="submit" value="Gerar Relatório">
        </form>
    
        <c:if test="${param.q != null}">
            <h2>Resultados</h2>
            <c:if test="${not empty listaDeAlunos}">
                <table>
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
        <a href="${pageContext.request.contextPath}/Menu/menu.jsp">Voltar para o menu.</a>
    </main>
</body>
</html>
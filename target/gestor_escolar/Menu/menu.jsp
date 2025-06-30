
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Principal</title>
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
        <h1>Gestor Escolar - Menu Principal</h1>
    </header>
    <main>
        <section>
            <h3>Gerenciar Alunos</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/alunos?acao=exibirFormularioCadastro">Cadastrar Novo Aluno</a></li>
                    <li><a href="${pageContext.request.contextPath}/alunos?acao=listar">Gerar Relatório de Alunos</a></li>
                </ul>
            </nav>
        </section>
        <section>
            <h3>Gerenciar Cursos</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/cursos?acao=exibirFormularioCadastro">Cadastrar Novo Curso</a></li>
                    <li><a href="${pageContext.request.contextPath}/cursos?acao=listar">Gerar Relatório de Cursos</a></li>
                    <li><a href="${pageContext.request.contextPath}/Curso/Json/cursoInserir.html">Inserir Curso (via JSON)</a></li>
                    <li><a href="${pageContext.request.contextPath}/Curso/Json/cursoListar.html">Listar Cursos (via JSON)</a></li>
                </ul>
            </nav>
        </section>
        <section>
            <h3>Gerenciar Disciplinas</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/disciplinas?acao=exibirFormularioCadastro">Cadastrar Nova Disciplina</a></li>
                    <li><a href="${pageContext.request.contextPath}/disciplinas?acao=listar">Gerar Relatório de Disciplinas</a></li>
                </ul>
            </nav>
        </section>
        <section>
            <h3>Sua Conta</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Usuario/cadastroUsuario.jsp">Cadastrar Novo Usuário</a></li>
                    <li><a href="${pageContext.request.contextPath}/index.jsp">Entrar em outra conta (Login)</a></li>
                </ul>
            </nav>
        </section>
    </main>
</body>
</html>

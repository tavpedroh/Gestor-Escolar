
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Principal</title>

    <style>
        body {
            background-color: var(--cor-fundo-app);
            color: var(--cor-fonte-app);
            font-family: sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            
        }
        h1, h2, h3 {
            color: var(--cor-fonte-app);
        }
        h1 { font-size: 2rem; }
        h2 { font-size: 1.5rem; border-bottom: 2px solid; }
        h3 { font-size: 1.2rem; }
        .menu-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: space-around;
        }
        section {
            flex: 1 1 200px;
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        section h3 {
            margin-top: 0;
        }
        nav ul {
            list-style: none;
            padding: 0;
        }
        nav ul li {
            margin: 10px 0;
        }
        nav ul li a {
            text-decoration: none;
            color: var(--cor-fonte-app);
            font-weight: bold;
        }
        nav ul li a:hover {
            text-decoration: underline;
        }
        hr {
            border: 0;
            border-top: 1px solid var(--cor-fonte-app);
            margin: 20px 0;
        }
        body, h1, h2, h3 {
            color: var(--cor-fonte-app);
        }

    </style>

</head>


<c:set var="corFundo" value="#FFFFFF" /> <%-- Valor padrão: branco --%>
<c:set var="corFonte" value="#333333" /> <%-- Valor padrão: cinza escuro --%>

<c:if test="${not empty cookie.corFundo}">
    <c:set var="corFundo" value="${cookie.corFundo.value}" />
</c:if>
<c:if test="${not empty cookie.corFonte}">
    <c:set var="corFonte" value="${cookie.corFonte.value}" />
</c:if>


<body style="--cor-fundo-app: ${corFundo}; --cor-fonte-app: ${corFonte};">
    <h1>Gestor Escolar - Menu Principal</h1>
    <hr>
    <div class="menu-container">
        <section id="gerenciar-alunos">
            <h3>Gerenciar Alunos</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/alunos?acao=exibirFormularioCadastro">Cadastrar Novo Aluno</a></li>
                    <li><a href="${pageContext.request.contextPath}/alunos?acao=listar">Gerar Relatório de Alunos</a></li>
                </ul>
            </nav>
        </section>

        <section id="gerenciar-cursos">
            <h3>Gerenciar Cursos</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/cursos?acao=exibirFormularioCadastro">Cadastrar Novo Curso</a></li>
                    <li><a href="${pageContext.request.contextPath}/cursos?acao=listar">Gerar Relatório de Cursos</a></li>
                    <hr>
                    <li><a href="${pageContext.request.contextPath}/Curso/Json/cursoInserir.html">Inserir Curso (via JSON)</a></li>
                    <li><a href="${pageContext.request.contextPath}/Curso/Json/cursoListar.html">Listar Cursos (via JSON)</a></li>
                </ul>
            </nav>
        </section>

        <section id="gerenciar-disciplinas">
            <h3>Gerenciar Disciplinas</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/disciplinas?acao=exibirFormularioCadastro">Cadastrar Nova Disciplina</a></li>
                    <li><a href="${pageContext.request.contextPath}/disciplinas?acao=listar">Gerar Relatório de Disciplinas</a></li>
                </ul>
            </nav>
        </section>

        <section id="administracao">
            <h3>Sua Conta</h3>
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/Usuario/cadastroUsuario.html">Cadastrar Novo Usuário</a></li>
                    <li><a href="${pageContext.request.contextPath}/index.jsp">Entrar em outra conta (Login)</a></li>
                </ul>
            </nav>
        </section>
    </div>
</body>
</html>

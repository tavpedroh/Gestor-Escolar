
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestor Escolar - Menu Principal</title>
    <style>
        body {
            background-color: var(--cor-fundo-app);
            color: var(--cor-fonte-app);
            font-family: sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
        }
        header, footer {
            text-align: center;
            padding: 1rem 0;
        }
        main {
            max-width: 900px;
            margin: 0 auto;
        }
        .menu-container {
            display: flex;
            gap: 2rem;
            flex-wrap: wrap;
        }
        section {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 1rem;
            flex: 1;
            min-width: 250px;
        }
        h1, h2, h3 {
            color: var(--cor-fonte-app);
        }
        h1 { font-size: 2rem; }
        h2 { font-size: 1.5rem; border-bottom: 2px solid}
        h3 { font-size: 1.2rem; }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin-bottom: 0.5rem;
        }
        a {
            text-decoration: none;
            color: var(--cor-fonte-app);
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
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

    <header>
        <h1>Gestor Escolar</h1>
    </header>

    <main>
        <h2>Menu Principal</h2>
        
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
                        <li><a href="${pageContext.request.contextPath}/Curso/cadastroCurso.jsp">Cadastrar Novo Curso</a></li>
                        <li><a href="${pageContext.request.contextPath}/Curso/relatorioCurso.jsp">Gerar Relatório de Cursos</a></li>
                        <hr>
                        <li><a href="${pageContext.request.contextPath}/Json/cursoInserir.html">Inserir Curso (via JSON)</a></li>
                        <li><a href="${pageContext.request.contextPath}/Json/cursoListar.html">Listar Cursos (via JSON)</a></li>
                    </ul>
                </nav>
            </section>
    
            <section id="gerenciar-disciplinas">
                <h3>Gerenciar Disciplinas</h3>
                <nav>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/Disciplina/cadastroDisciplina.jsp">Cadastrar Nova Disciplina</a></li>
                        <li><a href="${pageContext.request.contextPath}/Disciplina//cursos?relatorioDisciplina.jsp">Gerar Relatório de Disciplinas</a></li>
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
    </main>

    <footer>
        <p>&copy; 2025 Gestor Escolar. Todos os direitos reservados.</p>
    </footer>

</body>
</html>
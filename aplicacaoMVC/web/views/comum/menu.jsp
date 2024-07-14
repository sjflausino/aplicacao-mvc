<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
                        
                        if (funcionarioLogado != null) {
                            String papelFuncionario = funcionarioLogado.getPapel();
                            
                            if (papelFuncionario.equals("0")) { %>
                            <a class="nav-link" href="/aplicacaoMVC/admin/AdministradorController?acao=Listar">Administrador</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/RelatorioController">Relatório de Produtos</a>
                            <%} else if (papelFuncionario.equals("2")) {%>
                            <a class="nav-link" href="/aplicacaoMVC/admin/CadastraCompraController?acao=Listar">Cadastrar Compra</a>
                            <%} else if (papelFuncionario.equals("1")) {%>
                            <a class="nav-link" href="/aplicacaoMVC/admin/VendaController?acao=Listar">Cadastrar Venda</a>
                            <%} else {%>
                            <a class="nav-link" href="/aplicacaoMVC/admin/ProdutoController?acao=Listar">Produto</a>
                            <%  } %>
                            <a class="nav-link" href="/aplicacaoMVC/admin/dashboard/">Dashboard</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/CategoriaController?acao=Listar">Categorias</a>                            
                            <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%  } else { %>
                            <a class="nav-link" href="/aplicacaoMVC/MostraProdutoController?acao=ListarDisponiveis">Visualizar produtos disponíveis</a>
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
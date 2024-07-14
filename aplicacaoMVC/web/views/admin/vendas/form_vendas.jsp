<%-- 
    Document   : form_vendas
    Created on : 12 de jul. de 2024, 20:35:31
    Author     : alexandre-colmenero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Venda"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Venda</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Venda venda = (Venda) request.getAttribute("venda");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Venda</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Venda</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Venda</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

            

                    <form action="/aplicacaoMVC/admin/VendaController" method="POST">
                        <input type="hidden" name="id" value="<%= venda.getId()%>" class="form-control">

                        <div class="mb-3">
                            <label for="quantidadeVenda" class="form-label">Quantidade de Venda</label>
                            <input type="text" name="quantidadeVenda" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= venda.getQuantidadeVenda()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="dataVenda" class="form-label">Data da Venda</label>
                            <input type="text" name="dataVenda" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= venda.getDataVenda()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="valorVenda" class="form-label">Valor da Venda</label>
                            <input type="text" name="valorVenda" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= venda.getValorVenda()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="idCliente" class="form-label">ID do Cliente</label>
                            <input type="text" name="idCliente" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= venda.getIdFuncionario()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="idProduto" class="form-label">ID do Produto</label>
                            <input type="text" name="idProduto" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= venda.getIdProduto()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="idFuncionario" class="form-label">ID do Funcionário</label>
                            <input type="text" name="idFuncionario" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= venda.getIdFuncionario()%>" class="form-control">
                        </div>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/VendaController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>


                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>
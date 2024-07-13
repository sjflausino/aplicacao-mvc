<%-- 
    Document   : form_compras
    Created on : 12 de jul. de 2024, 20:22:48
    Author     : alexandre-colmenero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Compra"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Compra</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Compra compra = (Compra) request.getAttribute("compra");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Compra</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Compra</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Compra</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

            

                    <form action="/aplicacaoMVC/admin/CadastroCompraController" method="POST">
                        <input type="hidden" name="id" value="<%= compra.getId()%>" class="form-control">

                        <div class="mb-3">
                            <label for="quantidadeCompra" class="form-label">Quantidade de Compra</label>
                            <input type="text" name="quantidadeCompra" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= compra.getQuantidadeCompra()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="dataCompra" class="form-label">Data da Compra</label>
                            <input type="text" name="dataCompra" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= compra.getDataCompra()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="valorCompra" class="form-label">Valor da Compra</label>
                            <input type="text" name="valorCompra" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= compra.getValorCompra()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="idFornecedor" class="form-label">ID do Fornecedor</label>
                            <input type="text" name="idFornecedor" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= compra.getIdFornecedor()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="idProduto" class="form-label">ID do Produto</label>
                            <input type="text" name="idProduto" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= compra.getIdProduto()%>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="idFuncionario" class="form-label">ID do Funcionário</label>
                            <input type="text" name="idFuncionario" <%= acao.equals("Excluir") ? "readonly" : ""%> value="<%= compra.getIdFuncionario()%>" class="form-control">
                        </div>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao%>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/CompraController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>


                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>
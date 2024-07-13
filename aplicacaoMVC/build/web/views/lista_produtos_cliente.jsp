<%-- 
    Document   : lista_produtos_cliente
    Created on : 12 de jul. de 2024, 20:51:58
    Author     : alexandre-colmenero
--%>

<%@page import="entidade.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Produtos</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <jsp:include page="comum/menu.jsp" />
            <div class="mt-5">

                <h2>Lista de Produtos Disponíveis para Compra</h2>
                
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">ID Produto</th>
                                <th scope="col">Nome Produto</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Preço da Compra</th>
                                <th scope="col">Preço da Venda</th>
                                <th scope="col">Quantidade Disponível</th>
                                <th scope="col">Liberado pra Venda</th>
                                <th scope="col">ID Categoria</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Produto> listaProdutos = (ArrayList<Produto>) request.getAttribute("listaProdutos");

                                for (Produto produto : listaProdutos) {
                                    out.println("<tr>");
                                    out.println("<th>" + produto.getId() + "</th>");
                                    out.println("<td>" + produto.getNome_produto() + "</td>");
                                    out.println("<td>" + produto.getDescricao() + "</td>");
                                    out.println("<td>" + produto.getPreco_compra() + "</td>");
                                    out.println("<td>" + produto.getPreco_venda() + "</td>");
                                    out.println("<td>" + produto.getQuantidade_disponivel() + "</td>");
                                    out.println("<td>" + produto.getLiberado_venda() + "</td>");
                                    out.println("<td>" + produto.getId_categoria() + "</td>");
                            %>

                        <%   out.println("</tr>");
                            }
                        %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>
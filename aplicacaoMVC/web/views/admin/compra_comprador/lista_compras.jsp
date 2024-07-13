<%-- 
    Document   : lista_compras
    Created on : 12 de jul. de 2024, 20:20:56
    Author     : alexandre-colmenero
--%>

<%@page import="entidade.Compra"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Categorias</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Compras</h2>

                <a href="/aplicacaoMVC/admin/CadastroCompraController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Quantidade Compra</th>
                                <th scope="col">Data Compra</th>
                                <th scope="col">Valor Compra</th>
                                <th scope="col">Id Fornecedor</th>
                                <th scope="col">Id Produto</th>
                                <th scope="col">Id Funcionario</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Compra> listaCompras = (ArrayList<Compra>) request.getAttribute("listaCompras");

                                for (Compra compra : listaCompras) {
                                    out.println("<tr>");                                    
                                    out.println("<td>" + compra.getId() + "</td>");
                                    out.println("<td>" + compra.getQuantidadeCompra() + "</td>");
                                    out.println("<td>" + compra.getDataCompra() + "</td>");
                                    out.println("<td>" + compra.getValorCompra() + "</td>");
                                    out.println("<td>" + compra.getIdFornecedor() + "</td>");
                                    out.println("<td>" + compra.getIdProduto() + "</td>");
                                    out.println("<td>" + compra.getIdFuncionario() + "</td>");

                            %>
                        <td>
                            <a href="/aplicacaoMVC/admin/CadastroCompraController?acao=Alterar&id=<%=compra.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/admin/CadastroCompraController?acao=Excluir&id=<%=compra.getId()%>" class="btn btn-danger">Excluir</a></td>

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
<%-- 
    Document   : tabela_produto
    Created on : 12 de jul. de 2024, 20:26:03
    Author     : alexandre-colmenero
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Produto"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Produtos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>

    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col sm 6 mt 5">
                <%
                    Produto produto = (Produto) request.getAttribute("produto");
                    String acao = (String) request.getAttribute("acao");
                    switch (acao) {
                        case "Incluir":
                            out.println("<h1>Incluir Produto</h1>");
                            break;
                        case "Alterar":
                            out.println("<h1>Alterar Produto</h1>");
                            break;
                        case "Excluir":
                            out.println("<h1>Excluir Produto</h1>");
                            break;
                    }

                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>

                <h1>Área Restrita</h1>
                <h2>Tabela Produtos</h2>

                <div class="col-sm-6 mt-5">
                    <form action="/aplicacaoMVC/admin/ProdutoController?acao=Incluir" method="post">

                        <input type="hidden" name="id" value="<%=produto.getId()%>" class="form-control">

                        <div class="mb-3">
                            <label for="nome_produto">
                                <div class="mb-3">
                                    <label for="nome_produto" class="form-label">Nome Produto</label>
                                    <input 
                                        required
                                        maxlength="100"
                                        minlength="3"
                                        type="text" 
                                        class="form-control" 
                                        name="nome_produto" 
                                        id="nome_produto" 
                                        aria-describedby="helpId"
                                        placeholder="produtin" 
                                        <%= acao.equals("Excluir") ? "Readonly" : ""%>
                                        value="<%=produto.getNome_produto()%>"
                                        />
                                    <small id="helpId" class="form-text text-muted">Digite a Nome Produto</small>
                                </div>
                            </label>
                        </div>

                        <div class="mb-3">
                            <label for="descricao">
                                <div class="mb-3">
                                    <label for="descricao" class="form-label">Descricao</label>
                                    <input 
                                        required
                                        maxlength="255"
                                        type="text" 
                                        class="form-control" 
                                        name="descricao" 
                                        id="descricao" 
                                        aria-describedby="helpId"
                                        placeholder="blablabla"
                                        <%= acao.equals("Excluir") ? "Readonly" : ""%>
                                        value="<%=produto.getDescricao()%>"
                                        />
                                    <small id="helpId" class="form-text text-muted">Digite a Descricao</small>
                                </div>
                            </label>
                        </div>
                                        
                        <div class="mb-3">
                            <label for="preco_compra">
                                <div class="mb-3">
                                    <label for="preco_venda" class="form-label">Preco da Venda</label>
                                    <input 
                                        required
                                        maxlength="50"
                                        minlength="3"
                                        step="0.01"
                                        type="number" 
                                        class="form-control" 
                                        name="preco_compra" 
                                        id="preco_compra" 
                                        aria-describedby="helpId"
                                        <%= acao.equals("Excluir") ? "Readonly" : ""%>
                                        value="<%=produto.getPreco_compra()%>"
                                        />
                                    <small id="helpId" class="form-text text-muted">Digite o preço da compra</small>
                                </div>
                            </label>
                        </div>

                        <div class="mb-3">
                            <label for="preco_venda">
                                <div class="mb-3">
                                    <label for="preco_venda" class="form-label">Preco da Venda</label>
                                    <input 
                                        required
                                        maxlength="50"
                                        minlength="3"
                                        step="0.01"
                                        type="number" 
                                        class="form-control" 
                                        name="preco_venda" 
                                        id="preco_venda" 
                                        aria-describedby="helpId"
                                        <%= acao.equals("Excluir") ? "Readonly" : ""%>
                                        value="<%=produto.getPreco_venda()%>"
                                        />
                                    <small id="helpId" class="form-text text-muted">Digite o preço da venda</small>
                                </div>
                            </label>
                        </div>


                        <div class="mb-3">
                            <label for="quantidade_disponivel">
                                <div class="mb-3">
                                    <label for="quantidade_disponivel" class="form-label">Quantidade Disponível</label>
                                    <input 
                                        required
                                        maxlength="5"
                                        minlength="1"
                                        type="number" 
                                        class="form-control" 
                                        name="quantidade_disponivel" 
                                        id="quantidade_disponivel" 
                                        aria-describedby="helpId"
                                        <%= acao.equals("Excluir") ? "Readonly" : ""%>
                                        value="<%=produto.getQuantidade_disponivel()%>"
                                        />
                                    <small id="helpId" class="form-text text-muted">Digite a Quantidade Disponível</small>
                                </div>
                            </label>
                        </div>  

                        <div class="mb-3">
                            <label for="liberado_venda">
                                <div class="mb-3">
                                    <label for="liberado_venda" class="form-label">Liberado Venda</label>
                                    <input 
                                        required
                                        maxlength="1"
                                        type="text" 
                                        class="form-control" 
                                        name="liberado_venda" 
                                        id="liberado_venda" 
                                        aria-describedby="helpId"
                                        placeholder="Lojinha" 
                                        <%= acao.equals("Excluir") ? "Readonly" : ""%>
                                        value="<%=produto.getLiberado_venda()%>"
                                        />
                                    <small id="helpId" class="form-text text-muted">Digite a liberação da Venda</small>
                                </div>
                            </label>
                        </div>
                                        
                        <div class="mb-3">
                            <label for="id_categoria">
                                <div class="mb-3">
                                    <label for="id_categoria" class="form-label">Id Categoria</label>
                                    <input 
                                        required
                                        maxlength="5"
                                        minlength="1"
                                        type="number" 
                                        class="form-control" 
                                        name="id_categoria" 
                                        id="id_categoria" 
                                        aria-describedby="helpId"
                                        <%= acao.equals("Excluir") ? "Readonly" : ""%>
                                        value="<%=produto.getId_categoria()%>"
                                        />
                                    <small id="helpId" class="form-text text-muted">Digite o Id Categoria</small>
                                </div>
                            </label>
                        </div>  

                        <button
                            type="submit"
                            class="btn btn-primary"
                            name="btEnviar"
                            value="<%=acao%>"
                            >
                            Enviar
                        </button>

                    </form>
                </div>
            </div>
        </div>

        <script></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>

</html>
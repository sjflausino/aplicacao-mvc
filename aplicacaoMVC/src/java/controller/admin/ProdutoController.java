package controller.admin;

import entidade.Produto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutoDAO;

@WebServlet(name = "ProdutoController", urlPatterns = {"/admin/ProdutoController"})
public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Produto> listaProdutos = produtoDAO.getAll();
                request.setAttribute("listaProdutos", listaProdutos);
                rd = request.getRequestDispatcher("/views/admin/produto/lista_produto.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                 {
                    try {
                        produto = produtoDAO.get(id);
                    } catch (Exception ex) {
                        Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                request.setAttribute("produto", produto);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/produto/tabela_produtos.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("produto", produto);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/produto/tabela_produtos.jsp");
                rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome_produto = request.getParameter("nome_produto");
        String descricao = request.getParameter("descricao");
        double preco_compra = Double.parseDouble(request.getParameter("preco_compra"));
        double preco_venda = Double.parseDouble(request.getParameter("preco_venda"));
        int quantidade_disponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
        String liberado_venda = request.getParameter("liberado_venda");
        int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));

        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        Produto produto = new Produto();

        if (nome_produto.isEmpty() || descricao.isEmpty() || preco_compra == 0 || preco_venda == 0 || quantidade_disponivel == 0 || liberado_venda.isEmpty() || id_categoria == 0) {

            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        ProdutoDAO produtoDAO = new ProdutoDAO();
                        produto = produtoDAO.get(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de funcionario.", ex);
                    }
                    break;
            }

            request.setAttribute("produto", produto);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/produto/tabela_produtos.jsp");
            rd.forward(request, response);

        } else {

            produto.setId(id);
            produto.setNome_produto(nome_produto);
            produto.setDescricao(descricao);
            produto.setPreco_compra(preco_compra);
            produto.setPreco_venda(preco_venda);
            produto.setQuantidade_disponivel(quantidade_disponivel);
            produto.setLiberado_venda(liberado_venda);
            produto.setId_categoria(id_categoria);


            ProdutoDAO produtoDAO = new ProdutoDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        produtoDAO.insert(produto);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        produtoDAO.update(produto);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        produtoDAO.delete(produto.getId());
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/ProdutoController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de funcionario!", ex);
            }
        }
    }
}

package controller.admin;

import entidade.Compra;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CompraDAO;

@WebServlet(name = "CadastroCompraController", urlPatterns = {"/admin/CadastroCompraController"})
public class CadastroCompraController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Compra compra = new Compra();
        CompraDAO compraDAO = new CompraDAO();
        RequestDispatcher rd;        
        
        switch (acao) {
            case "Listar":
                
                ArrayList <Compra> listaCompras = compraDAO.getAll();
                request.setAttribute("listaCompras", listaCompras);

                rd = request.getRequestDispatcher("/views/admin/compra_comprador/lista_compras.jsp");
                rd.forward(request, response);

                break;
                
            case "Alterar":
            case "Excluir":
                
                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                compra = compraDAO.get(id);

                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/compra_comprador/form_compras.jsp");
                rd.forward(request, response);
                break;
                
            case "Incluir":
                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/compra_comprador/form_compras.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int quantidadeCompra = Integer.parseInt(request.getParameter("quantidadeCompra"));
        String dataCompra = request.getParameter("dataCompra");
        double valorCompra = Double.parseDouble(request.getParameter("valorCompra"));
        int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (quantidadeCompra == 0) {
            Compra compra = new Compra();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                    CompraDAO compraDAO = new CompraDAO();
                    compra = compraDAO.get(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("compra", compra);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/categoria/form_compras.jsp");
            rd.forward(request, response);

        } else {
            
            Compra compra = new Compra(id, quantidadeCompra, dataCompra, valorCompra, idFornecedor, idProduto, idFuncionario);
            CompraDAO compraDAO = new CompraDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        compraDAO.insert(compra);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        compraDAO.update(compra);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        compraDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/CadastroCompraController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}

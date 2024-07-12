package controller.admin;

import entidade.Venda;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendaDAO;

@WebServlet(name = "VendaController", urlPatterns = {"/admin/VendaController"})
public class VendaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        Venda venda = new Venda();
        VendaDAO vendaDAO = new VendaDAO();
        RequestDispatcher rd;        
        
        switch (acao) {
            case "Listar":
                ArrayList<Venda> listaVendas = vendaDAO.getAll();
                request.setAttribute("listaVendas", listaVendas);

                rd = request.getRequestDispatcher("/views/admin/vendas/lista_vendas.jsp");
                rd.forward(request, response);
                break;
                
            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                venda = vendaDAO.get(id);

                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendas/form_vendas.jsp");
                rd.forward(request, response);
                break;
                
            case "Incluir":
                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendas/form_vendas.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int quantidadeVenda = Integer.parseInt(request.getParameter("quantidadeVenda"));
        String dataVenda = request.getParameter("dataVenda");
        double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (quantidadeVenda == 0) {
            Venda venda = new Venda();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        VendaDAO vendaDAO = new VendaDAO();
                        venda = vendaDAO.get(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de venda");
                    }
                    break;
            }

            request.setAttribute("venda", venda);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/venda/form_vendas.jsp");
            rd.forward(request, response);

        } else {
            
            Venda venda = new Venda(id, quantidadeVenda, dataVenda, valorVenda, idCliente, idProduto, idFuncionario);
            VendaDAO vendaDAO = new VendaDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        vendaDAO.insert(venda);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        vendaDAO.update(venda);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        vendaDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/VendaController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de venda");
            }
        }
    }
}

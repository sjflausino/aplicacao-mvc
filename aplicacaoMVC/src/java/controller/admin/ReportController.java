package controller.admin;

import entidade.RelatorioVendasProduto;
import entidade.RelatorioVendasDia;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutoDAO;

@WebServlet(name = "ReportController", urlPatterns = {"/admin/ReportController"})
public class ReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        RequestDispatcher rd;

        ArrayList<RelatorioVendasProduto> listaProdutos = new ArrayList<>();
        listaProdutos = produtoDAO.getRelatorioVendasPorProduto();
        request.setAttribute("listaProdutos", listaProdutos);
        

        ArrayList<RelatorioVendasDia> listaProdutosDia = new ArrayList<>();
        listaProdutosDia = produtoDAO.getRelatorioVendasPorDia();
        request.setAttribute("listaProdutosDia", listaProdutosDia);
        
        
        rd = request.getRequestDispatcher("/views/admin/administrador/relatorio_vendas.jsp");
        rd.forward(request, response);

    }

}

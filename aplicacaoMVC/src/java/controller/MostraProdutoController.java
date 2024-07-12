/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vertigo
 */
@WebServlet(name = "MostraProdutoController", urlPatterns = {"/MostraProdutoController"})
public class MostraProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        RequestDispatcher rd;

        switch (acao) {
            case "ListarTodos":
                listaProdutos = produtoDAO.getAll();
                request.setAttribute("listaProdutos", listaProdutos);
                rd = request.getRequestDispatcher("/views/lista_produtos_cliente.jsp");
                rd.forward(request, response);
                break;

            case "ListarDisponiveis":
                listaProdutos = produtoDAO.getProdutosDisponiveis();
                request.setAttribute("listaProdutos", listaProdutos);
                rd = request.getRequestDispatcher("/views/lista_produtos_cliente.jsp");
                rd.forward(request, response);
        }
    }
}

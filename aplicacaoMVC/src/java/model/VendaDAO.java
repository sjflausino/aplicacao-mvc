package model;

import entidade.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class VendaDAO implements Dao<Venda> {

    @Override
    public Venda get(int id) {
        Conexao conexao = new Conexao();
        try {
            Venda venda = new Venda();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM vendas WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    Date date = new Date(resultado.getDate("data_venda").getTime());

                    venda.setId(Integer.parseInt(resultado.getString("id")));
                    venda.setQuantidadeVenda(Integer.parseInt(resultado.getString("quantidade_venda")));
                    venda.setDataVenda(date);
                    venda.setValorVenda(Integer.parseInt(resultado.getString("valor_venda")));
                    venda.setIdCliente(resultado.getInt("id_cliente"));
                    venda.setIdProduto(Integer.parseInt(resultado.getString("id_produto")));
                    venda.setIdVendedor(Integer.parseInt(resultado.getString("id_vendedor")));
                }
            }
            return venda;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get venda) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Venda venda) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Vendas (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_vendedor ) VALUES (?,?,?,?,?,?)");
            java.sql.Date date = new java.sql.Date(venda.getDataVenda().getTime());

            sql.setInt(1, venda.getQuantidadeVenda());
            sql.setDate(2, date);
            sql.setDouble(3, venda.getValorVenda());
            sql.setInt(4, venda.getIdCliente());
            sql.setInt(5, venda.getIdProduto());
            sql.setInt(6, venda.getIdVendedor());

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (venda) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Venda> getAll() {
        ArrayList<Venda> meusVendas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM compras";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {

                    Venda venda = new Venda(
                        resultado.getInt("id"),
                        resultado.getInt("quantidade_venda"),
                        resultado.getDate("data_venda"),
                        resultado.getDouble("valor_venda"),
                        resultado.getInt("id_cliente"),
                        resultado.getInt("id_produto"),
                        resultado.getInt("id_vendedor")                   
                    );

                    meusVendas.add(venda);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusVendas;  
    }


    @Override
    public void update(Venda venda) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Vendas SET quantidadeVenda = ?, dataVenda = ?, valorVenda = ?, idCliente = ?, idProduto = ?, idVendedor = ?  WHERE id = ? ");
            java.sql.Date date = new java.sql.Date(venda.getDataVenda().getTime());

            sql.setInt(1, venda.getQuantidadeVenda());
            sql.setDate(2, date);
            sql.setDouble(3, venda.getValorVenda());
            sql.setInt(4, venda.getIdCliente());
            sql.setInt(5, venda.getIdProduto());
            sql.setInt(6, venda.getIdVendedor());
            sql.setInt(7, venda.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar venda) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM vendas WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (venda) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

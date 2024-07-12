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
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM vendas WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    Date date = new Date(resultado.getDate("DATA_VENDA").getTime());

                    venda.setId(Integer.parseInt(resultado.getString("ID")));
                    venda.setQuantidadeVenda(Integer.parseInt(resultado.getString("QUANTIDADE_VENDA")));
                    venda.setDataVenda(date);
                    venda.setValorVenda(Integer.parseInt(resultado.getString("VALOR_VENDA")));
                    venda.setIdCliente(resultado.getInt("ID_CLIENTE"));
                    venda.setIdProduto(Integer.parseInt(resultado.getString("ID_PRODUTO")));
                    venda.setIdVendedor(Integer.parseInt(resultado.getString("ID_VENDEDOR")));
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
                        resultado.getInt("ID"),
                        resultado.getInt("QUANTIDADE_VENDA"),
                        resultado.getDate("DATA_VENDA"),
                        resultado.getDouble("VALOR_VENDA"),
                        resultado.getInt("ID_CLIENTE"),
                        resultado.getInt("ID_PRODUTO"),
                        resultado.getInt("ID_VENDEDOR")                   
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Vendas SET quantidadeVenda = ?, dataVenda = ?, valorVenda = ?, idCliente = ?, idProduto = ?, idVendedor = ?  WHERE ID = ? ");
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM vendas WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (venda) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

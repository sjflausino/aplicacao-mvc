package model;

import entidade.Compra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CompraDAO implements Dao<Compra> {

    @Override
    public Compra get(int id) {
        Conexao conexao = new Conexao();
        try {
            Compra compra = new Compra();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    Date date = new Date(resultado.getDate("DATA_COMPRA").getTime());

                    compra.setId(Integer.parseInt(resultado.getString("ID")));
                    compra.setQuantidadeCompra(resultado.getInt("QUANTIDADE_COMPRA"));
                    compra.setDataCompra(date);
                    compra.setValorCompra(Double.parseDouble(resultado.getString("VALOR_COMPRA")));
                    compra.setIdFornecedor(Integer.parseInt(resultado.getString("ID_FORNECEDOR")));
                    compra.setIdProduto(Integer.parseInt(resultado.getString("ID_PRODUTO")));
                    compra.setIdComprador(Integer.parseInt(resultado.getString("ID_COMPRADOR")));

                }
            }
            return compra;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Compra compra) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_comprador ) VALUES (?,?,?,?,?,?)");
            java.sql.Date date = new java.sql.Date(compra.getDataCompra().getTime());

            sql.setDouble(1, compra.getQuantidadeCompra());
            sql.setDate(2, date);
            sql.setDouble(3, compra.getValorCompra());
            sql.setInt(4, compra.getIdFornecedor());
            sql.setInt(5, compra.getIdProduto());
            sql.setInt(6, compra.getIdComprador());

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Compra> getAll() {

        ArrayList<Compra> meusCompras = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM compras";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Compra compra = new Compra(
                        resultado.getInt("ID"),
                        resultado.getInt("QUANTIDADE_COMPRA"),
                        resultado.getDate("DATA_COMPRA"),
                        resultado.getDouble("VALOR_COMPRA"),
                        resultado.getInt("ID_FORNECEDOR"),
                        resultado.getInt("ID_PRODUTO"),
                        resultado.getInt("ID_FUNCIONARIO")
                    );
                    meusCompras.add(compra);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusCompras;
    }

    @Override
    public void update(Compra compra) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE compras SET quantidade_compra = ?, data_compra = ?, valor_compra = ?, id_fornecedor = ?, id_produto = ?, id_comprador = ?  WHERE ID = ? ");
            java.sql.Date date = new java.sql.Date(compra.getDataCompra().getTime());

            sql.setDouble(1, compra.getQuantidadeCompra());
            sql.setDate(2, date);
            sql.setDouble(3, compra.getValorCompra());
            sql.setInt(4, compra.getIdFornecedor());
            sql.setInt(5, compra.getIdProduto());
            sql.setInt(6, compra.getIdComprador());
            sql.setInt(7, compra.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM compras WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

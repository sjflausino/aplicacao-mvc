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
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    Date date = new Date(resultado.getDate("data_compra").getTime());

                    compra.setId(Integer.parseInt(resultado.getString("id")));
                    compra.setQuantidadeCompra(resultado.getInt("quantidade_compra"));
                    compra.setDataCompra(date);
                    compra.setValorCompra(Double.parseDouble(resultado.getString("valor_compra")));
                    compra.setIdFornecedor(Integer.parseInt(resultado.getString("id_fornecedor")));
                    compra.setIdProduto(Integer.parseInt(resultado.getString("id_produto")));
                    compra.setIdComprador(Integer.parseInt(resultado.getString("id_comprador")));

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
                        resultado.getInt("id"),
                        resultado.getInt("quantidade_compra"),
                        resultado.getDate("data_compra"),
                        resultado.getDouble("valor_compra"),
                        resultado.getInt("id_fornecedor"),
                        resultado.getInt("id_produto"),
                        resultado.getInt("id_funcionario")
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE compras SET quantidade_compra = ?, data_compra = ?, valor_compra = ?, id_fornecedor = ?, id_produto = ?, id_comprador = ?  WHERE id = ? ");
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM compras WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

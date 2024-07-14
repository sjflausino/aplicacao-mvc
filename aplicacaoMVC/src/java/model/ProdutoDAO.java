package model;

import entidade.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO implements Dao<Produto> {

    @Override
    public Produto get(int id) {
        Conexao conexao = new Conexao();
        try {
            Produto produto = new Produto();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM produtos WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    produto.setId(Integer.parseInt(resultado.getString("id")));
                    produto.setNomeProduto(resultado.getString("nome_produto"));
                    produto.setDescricao(resultado.getString("descricao"));
                    produto.setPrecoCompra(Double.parseDouble(resultado.getString("preco_compra")));
                    produto.setPrecoVenda(Double.parseDouble(resultado.getString("preco_venda")));
                    produto.setQuantidadeDisponivel(Integer.parseInt(resultado.getString("quantidade_disponivel")));
                    produto.setLiberadoVenda(resultado.getString("liberado_venda"));
                    produto.setIdCategoria(Integer.parseInt(resultado.getString("liberado_venda")));
                }
            }
            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Produto produto) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO produtos (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponivel, liberado_venda, id_categoria ) VALUES (?,?,?,?,?,?,?)");
            sql.setString(1, produto.getNomeProduto());
            sql.setString(2, produto.getDescricao());
            sql.setDouble(3, produto.getPrecoCompra());
            sql.setDouble(4, produto.getPrecoVenda());
            sql.setInt(5, produto.getQuantidadeDisponivel());
            sql.setString(6, produto.getLiberadoVenda());
            sql.setInt(6, produto.getIdCategoria());

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Produto> getAll() {
        ArrayList<Produto> meusProdutos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM produtos";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Produto produto = new Produto(
                        resultado.getInt("id"),
                        resultado.getString("nome_produto"),
                        resultado.getString("descricao"),
                        resultado.getDouble("preco_compra"),
                        resultado.getDouble("preco_venda"),
                        resultado.getInt("quantidade_disponivel"),
                        resultado.getString("liberado_venda"),                                                
                        resultado.getInt("id_categoria")
                    );
                    meusProdutos.add(produto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusProdutos;
    }

    @Override
    public void update(Produto produto) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE produtos SET nome_produto = ?, descricao = ?, preco_compra = ?, preco_venda = ?, quantidade_disponivel = ?, liberado_venda = ?, id_categoria = ?  WHERE id = ? ");
            sql.setString(1, produto.getNomeProduto());
            sql.setString(2, produto.getDescricao());
            sql.setDouble(3, produto.getPrecoCompra());
            sql.setDouble(4, produto.getPrecoVenda());
            sql.setInt(5, produto.getQuantidadeDisponivel());
            sql.setString(6, produto.getLiberadoVenda());
            sql.setInt(6, produto.getIdCategoria());
            sql.setInt(7, produto.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM produtos WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

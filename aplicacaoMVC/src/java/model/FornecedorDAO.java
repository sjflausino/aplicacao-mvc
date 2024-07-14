package model;

import entidade.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDAO implements Dao<Fornecedor> {

    @Override
    public Fornecedor get(int id) {
        Conexao conexao = new Conexao();
        try {
            Fornecedor fornecedor = new Fornecedor();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    fornecedor.setId(Integer.parseInt(resultado.getString("id")));
                    fornecedor.setRazaoSocial(resultado.getString("razao_social"));
                    fornecedor.setCnpj(resultado.getString("cnpj"));
                    fornecedor.setEndereco(resultado.getString("endereco"));
                    fornecedor.setBairro(resultado.getString("bairro"));
                    fornecedor.setCidade(resultado.getString("cidade"));
                    fornecedor.setUf(resultado.getString("uf"));
                    fornecedor.setCep(resultado.getString("cep"));
                    fornecedor.setTelefone(resultado.getString("telefone"));
                    fornecedor.setEmail(resultado.getString("email"));
                }
            }
            return fornecedor;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Fornecedor fornecedor) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO fornecedores (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?)");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Fornecedor> getAll() {

        ArrayList<Fornecedor> meusFornecedores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM fornecedores";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Fornecedor fornecedor = new Fornecedor(
                        resultado.getInt("id"),
                        resultado.getString("razao_social"),
                        resultado.getString("cnpj"),
                        resultado.getString("endereco"),
                        resultado.getString("bairro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("cep"),
                        resultado.getString("telefone"),
                        resultado.getString("email")
                    );
                    meusFornecedores.add(fornecedor);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusFornecedores;

    }

    @Override
    public void update(Fornecedor fornecedor) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE fornecedores SET razao_social = ?, cnpj = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ?  WHERE id = ? ");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());
            sql.setInt(10, fornecedor.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM fornecedores WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

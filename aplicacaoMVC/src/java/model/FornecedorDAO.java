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
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    fornecedor.setId(Integer.parseInt(resultado.getString("ID")));
                    fornecedor.setRazaoSocial(resultado.getString("RAZAO_SOCIAL"));
                    fornecedor.setCnpj(resultado.getString("CNPJ"));
                    fornecedor.setEndereco(resultado.getString("ENDERECO"));
                    fornecedor.setBairro(resultado.getString("BAIRRO"));
                    fornecedor.setCidade(resultado.getString("CIDADE"));
                    fornecedor.setUf(resultado.getString("UF"));
                    fornecedor.setCep(resultado.getString("CEP"));
                    fornecedor.setTelefone(resultado.getString("TELEFONE"));
                    fornecedor.setEmail(resultado.getString("EMAIL"));
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
                        resultado.getInt("ID"),
                        resultado.getString("RAZAO_SOCIAL"),
                        resultado.getString("CNPJ"),
                        resultado.getString("ENDERECO"),
                        resultado.getString("BAIRRO"),
                        resultado.getString("CIDADE"),
                        resultado.getString("UF"),
                        resultado.getString("CEP"),
                        resultado.getString("TELEFONE"),
                        resultado.getString("EMAIL")
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE fornecedores SET razao_social = ?, cnpj = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ?  WHERE ID = ? ");
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM fornecedores WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

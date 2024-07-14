package model;

import entidade.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO implements Dao<Cliente> {

    @Override
    public Cliente get(int id) {
        Conexao conexao = new Conexao();
        try {
            Cliente cliente = new Cliente();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM clientes WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    cliente.setId(Integer.parseInt(resultado.getString("id")));
                    cliente.setNome(resultado.getString("nome"));
                    cliente.setCpf(resultado.getString("cpf"));
                    cliente.setEndereco(resultado.getString("endereco"));
                    cliente.setBairro(resultado.getString("bairro"));
                    cliente.setCidade(resultado.getString("cidade"));
                    cliente.setUf(resultado.getString("uf"));
                    cliente.setCep(resultado.getString("cep"));
                    cliente.setTelefone(resultado.getString("telefone"));
                    cliente.setEmail(resultado.getString("email"));
                }
            }
            return cliente;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Cliente cliente) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO clientes (nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?)");
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getEndereco());
            sql.setString(4, cliente.getBairro());
            sql.setString(5, cliente.getCidade());
            sql.setString(6, cliente.getUf());
            sql.setString(7, cliente.getCep());
            sql.setString(8, cliente.getTelefone());
            sql.setString(9, cliente.getEmail());

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Cliente> getAll() {

        ArrayList<Cliente> meusClientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM clientes";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Cliente cliente = new Cliente(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("cpf"),
                        resultado.getString("endereco"),
                        resultado.getString("bairro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("cep"),
                        resultado.getString("telefone"),
                        resultado.getString("email")
                    );
                    meusClientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - clientes) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusClientes;

    }

    @Override
    public void update(Cliente cliente) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Clientes SET nome = ?, cpf = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ?  WHERE id = ? ");
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getEndereco());
            sql.setString(4, cliente.getBairro());
            sql.setString(5, cliente.getCidade());
            sql.setString(6, cliente.getUf());
            sql.setString(7, cliente.getCep());
            sql.setString(8, cliente.getTelefone());
            sql.setString(9, cliente.getEmail());
            sql.setInt(10, cliente.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM clientes WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}

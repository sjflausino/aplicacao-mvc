package model;

import entidade.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO implements Dao<Funcionario> {

    @Override
    public Funcionario get(int id) {
        Conexao conexao = new Conexao();
        try {
            Funcionario funcionario = new Funcionario();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    funcionario.setId(Integer.parseInt(resultado.getString("ID")));
                    funcionario.setNome(resultado.getString("NOME"));
                    funcionario.setCpf(resultado.getString("CPF"));
                    funcionario.setSenha(resultado.getString("SENHA"));
                    funcionario.setEmail(resultado.getString("EMAIL"));
                    funcionario.setPapel(resultado.getString("PAPEL"));
                }
            }
            return funcionario;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Funcionario funcionario) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO funcionarios (nome, cpf, senha,email, papel) VALUES (?,?,?,?,?,?,?,?)");
            sql.setString(1, funcionario.getNome());
            sql.setString(2, funcionario.getCpf());
            sql.setString(3, funcionario.getSenha());
            sql.setString(4, funcionario.getEmail());
            sql.setString(5, funcionario.getPapel());

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Funcionario> getAll() {
        ArrayList<Funcionario> meusFuncionarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM compras";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Funcionario Funcionario = new Funcionario(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getString("CPF"),
                        resultado.getString("SENHA"),
                        resultado.getString("EMAIL"),
                        resultado.getString("PAPEL")
                    );
                    meusFuncionarios.add(Funcionario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - funcionarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusFuncionarios;
    }

    @Override
    public void update(Funcionario funcionario) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE funcionarios SET nome = ?, cpf = ?, senha = ?, papel = ?  WHERE ID = ? ");
            sql.setString(1, funcionario.getNome());
            sql.setString(2, funcionario.getCpf());
            sql.setString(3, funcionario.getSenha());
            sql.setString(4, funcionario.getPapel());
            sql.setInt(5, funcionario.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM funcionarios WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

 
}

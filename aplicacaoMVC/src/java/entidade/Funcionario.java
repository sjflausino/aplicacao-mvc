package entidade;

public class Funcionario {

    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private String papel;

    public Funcionario(int id, String nome, String cpf, String senha, String email, String papel) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.papel = papel;
    }

    public Funcionario() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.senha = "";
        this.email = "";
        this.papel = "";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getPapel() {
        return papel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

}

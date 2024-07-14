package entidade;

public class RelatorioVendasProduto {
    
    private int id_produto;
    private String nome_produto;
    private int count_produto;

    // Constructor
    public RelatorioVendasProduto(int id_produto, String nome_produto, int count_produto) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.count_produto = count_produto;
    }
    
    public RelatorioVendasProduto() {
        this.id_produto = 0;
        this.nome_produto = "";
        this.count_produto = 0;
    }

    // Getter and Setter methods

    public int getId() {
        return id_produto;
    }

    public void setId(int id) {
        this.id_produto = id;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public int getContProduto() {
        return count_produto;
    }

    public void setContProduto(int count_produto) {
        this.count_produto = count_produto;
    }
}

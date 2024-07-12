package entidade;

public class Produto {

    private int id;
    private String nomeProduto;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int quantidadeDisponivel;
    private String liberadoVenda;
    private int idCategoria;

    public Produto(int id, String nomeProduto, String descicao, double precoCompra, double precoVenda, int quantidadeDisponivel, String liberadoVenda, int idCategoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descricao = descicao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.liberadoVenda = liberadoVenda;
        this.idCategoria = idCategoria;
    }

    public Produto() {
        this.id = 0;
        this.nomeProduto = "";
        this.descricao = "";
        this.precoCompra = 0;
        this.precoVenda = 0;
        this.quantidadeDisponivel = 0;
        this.liberadoVenda = "";
        this.idCategoria = 0;
    }

    public int getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public String getLiberadoVenda() {
        return liberadoVenda;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void setLiberadoVenda(String liberadoVenda) {
        this.liberadoVenda = liberadoVenda;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProdutodo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

package entidade;
import java.util.Date;

public class Venda {

    private int id;
    private int quantidadeVenda;
    private Date dataVenda;
    private double valorVenda;
    private int idCliente;
    private int idProduto;
    private int idVendedor;

    public Venda(int id, int quantidadeVenda, Date dataVenda, double valorVenda, int idCliente, int idProduto, int idVendedor) {
        this.id = id;
        this.quantidadeVenda = quantidadeVenda;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.idVendedor = idVendedor;
    }

    public Venda() {
        this.id = 0;
        this.quantidadeVenda = 0;
        this.dataVenda = null;
        this.valorVenda = 0;
        this.idCliente = 0;
        this.idProduto = 0;
        this.idVendedor = 0;
    }

    public int getId() {
        return id;
    }

    public int getQuantidadeVenda() {
        return quantidadeVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantidadeVenda(int quantidadeVenda) {
        this.quantidadeVenda = quantidadeVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

}

package entidade;

public class RelatorioVendasDia {
    
    private String data_venda;
    private int count_produto;

    public RelatorioVendasDia(String data_venda, int count_produto) {
        this.data_venda = data_venda;
        this.count_produto = count_produto;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }a

    public int getCount_produto() {
        return count_produto;
    }

    public void setCount_produto(int count_produto) {
        this.count_produto = count_produto;
    }

}

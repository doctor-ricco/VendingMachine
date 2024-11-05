package vending;


class Chocolate extends Produto {
    private int tipoCacau;
    private String marca;

    public Chocolate(String nome, double preco, String referencia, String prazoValidade, int tipoCacau, String marca) {
        super(nome, preco, referencia, prazoValidade);
        this.tipoCacau = tipoCacau;
        this.marca = marca;
    }

    public int getTipoCacau() {
        return tipoCacau;
    }

    public void setTipoCacau(int tipoCacau) {
        this.tipoCacau = tipoCacau;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
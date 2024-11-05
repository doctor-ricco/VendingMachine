package vending;

class Refrigerante extends Produto {
    private int tipo;
    private String marca;

    public Refrigerante(String nome, double preco, String referencia, String prazoValidade, int tipo, String marca) {
        super(nome, preco, referencia, prazoValidade);
        this.tipo = tipo;
        this.marca = marca;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
package vending;


class Sande extends Produto {
    private int tipo;
    private String nomeProdutor;

    public Sande(String nome, double preco, String referencia, String prazoValidade, int tipo, String nomeProdutor) {
        super(nome, preco, referencia, prazoValidade);
        this.tipo = tipo;
        this.nomeProdutor = nomeProdutor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNomeProdutor() {
        return nomeProdutor;
    }

    public void setNomeProdutor(String nomeProdutor) {
        this.nomeProdutor = nomeProdutor;
    }
}
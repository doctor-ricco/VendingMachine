package vending;


class Sande extends Produto {
    private String tipo;
    private String nomeProdutor;

    public Sande(String nome, double preco, String referencia, String prazoValidade, String tipo, String nomeProdutor) {
        super(nome, preco, referencia, prazoValidade);
        this.tipo = tipo;
        this.nomeProdutor = nomeProdutor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeProdutor() {
        return nomeProdutor;
    }

    public void setNomeProdutor(String nomeProdutor) {
        this.nomeProdutor = nomeProdutor;
    }
}
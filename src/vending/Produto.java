package vending;

import java.io.Serializable;

abstract class Produto implements Serializable {
    protected String nome;
    protected double preco;
    protected String referencia;
    protected String prazoValidade;

    public Produto(String nome, double preco, String referencia, String prazoValidade) {
        this.nome = nome;
        this.preco = preco;
        this.referencia = referencia;
        this.prazoValidade = prazoValidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getPrazoValidade() {
        return prazoValidade;
    }

    public void setPrazoValidade(String prazoValidade) {
        this.prazoValidade = prazoValidade;
    }
}
package vending;

class Colaborador {
    private String nome;
    private String id;

    public Colaborador(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public void adicionarProduto(MaquinaVenda maquina, Produto produto) {
        maquina.adicionarProduto(produto);
    }

    // Adicione este método para o colaborador retirar um produto
    public void retirarProduto(MaquinaVenda maquina, String referencia) {
        if (maquina.removerProdutoPorReferencia(referencia)) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Adicione este método para o colaborador consultar o saldo total
    public void consultarSaldoTotal(MaquinaVenda maquina) {
        System.out.printf("Saldo total de vendas: %.2f%n", maquina.getSaldoTotal());
    }

}
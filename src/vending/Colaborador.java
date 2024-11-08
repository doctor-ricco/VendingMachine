package vending;

class Colaborador {
    private String nome;
    private String id;
    private Usuario usuario;

    public Colaborador(String nome, String id, Usuario usuario) {
        this.nome = nome;
        this.id = id;
        this.usuario = usuario;
    }

    public boolean autenticar(String username, String password) {
        return usuario.autenticar(username, password);
    }

    public void adicionarProduto(MaquinaVenda maquina, Produto produto) {
        maquina.adicionarProduto(produto);
    }

    public void retirarProduto(MaquinaVenda maquina, String referencia) {
        if (maquina.removerProdutoPorReferencia(referencia)) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void consultarSaldoTotal(MaquinaVenda maquina) {
        System.out.printf("Saldo total de vendas: %.2f%n", maquina.getSaldoTotal());
    }
}

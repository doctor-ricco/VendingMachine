package vending;

import java.util.Scanner;

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

    public void adicionarProduto(MaquinaVenda maquina, Scanner scanner) {
        System.out.println("Escolha o tipo de produto (1: Chocolate, 2: Refrigerante, 3: Sande):");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nome do produto:");
        String nome = scanner.nextLine();
        System.out.println("Preço:");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Referência:");
        String referencia = scanner.nextLine();
        System.out.println("Prazo de validade:");
        String prazoValidade = scanner.nextLine();

        Produto novoProduto = null;

        if (tipoProduto == 1) {
            System.out.println("Tipo de Cacau (1: Branco || 2: Ao leite || 3: Negro)");
            int tipoCacau = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Marca:");
            String marca = scanner.nextLine();
            novoProduto = new Chocolate(nome, preco, referencia, prazoValidade, tipoCacau, marca);
        } else if (tipoProduto == 2) {
            System.out.println("Tipo (1: Normal || 2: Sem açúcar)");
            int tipo = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Marca:");
            String marca = scanner.nextLine();
            novoProduto = new Refrigerante(nome, preco, referencia, prazoValidade, tipo, marca);
        } else if (tipoProduto == 3) {
            System.out.println("Tipo (1: Queijo || 2: Fiambre || 3: Mista):");
            int tipo = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Nome do Produtor:");
            String nomeProdutor = scanner.nextLine();
            novoProduto = new Sande(nome, preco, referencia, prazoValidade, tipo, nomeProdutor);
        }

        if (novoProduto != null) {
            maquina.adicionarProduto(novoProduto);
        }
        maquina.salvarDados();
    }

    public void retirarProduto(MaquinaVenda maquina, Scanner scanner) {
        maquina.exibirProdutosDisponiveis();
        System.out.println(" ");
        System.out.println("Referência do produto a ser retirado:");
        String referenciaProduto = scanner.nextLine();
        if (maquina.removerProdutoPorReferencia(referenciaProduto)) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void consultarSaldoTotal(MaquinaVenda maquina) {
        System.out.printf("Saldo total de vendas: %.2f%n", maquina.getSaldoTotal());
    }
}
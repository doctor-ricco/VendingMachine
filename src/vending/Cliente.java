package vending;

import java.util.Scanner;

class Cliente {
    public static void comprarProduto(MaquinaVenda maquina, Scanner scanner) {
        if (maquina.getChocolates().isEmpty() && maquina.getRefrigerantes().isEmpty() && maquina.getSandes().isEmpty()) {
            System.out.println("Máquina temporariamente indisponível. Dirija-se a outra estação. Obrigado.");
        } else {

            System.out.println("Insira o montante:");
            double montante = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Escolha o tipo de produto (1: Chocolate, 2: Refrigerante, 3: Sande):");
            int tipoProduto = scanner.nextInt();
            scanner.nextLine();

            Produto produtoSelecionado = selecionarProduto(maquina, tipoProduto, scanner);

            if (produtoSelecionado == null) {
                System.out.println("Produto não encontrado.");
                return;
            }

            if (montante < produtoSelecionado.getPreco()) {
                System.out.printf("Montante insuficiente! Você precisa de %.2f a mais.\n", produtoSelecionado.getPreco() - montante);
                return;
            }

            maquina.registrarVenda(produtoSelecionado.getPreco(), produtoSelecionado.getNome());
            System.out.printf("Compra realizada com sucesso! Troco: %.2f\n", montante - produtoSelecionado.getPreco());

            if (produtoSelecionado instanceof Chocolate) {
                maquina.getChocolates().remove(produtoSelecionado);
            } else if (produtoSelecionado instanceof Refrigerante) {
                maquina.getRefrigerantes().remove(produtoSelecionado);
            } else if (produtoSelecionado instanceof Sande) {
                maquina.getSandes().remove(produtoSelecionado);
            }

            maquina.salvarDados();
        }
    }

    private static Produto selecionarProduto(MaquinaVenda maquina, int tipoProduto, Scanner scanner) {
        Produto produtoSelecionado = null;

        switch (tipoProduto) {
            case 1:
                for (Chocolate chocolate : maquina.getChocolates()) {
                    System.out.println(chocolate.getReferencia() + " - " + chocolate.getNome() + " - " + chocolate.getPreco());
                }
                break;
            case 2:
                for (Refrigerante refrigerante : maquina.getRefrigerantes()) {
                    System.out.println(refrigerante.getReferencia() + " - " + refrigerante.getNome() + " - " + refrigerante.getPreco());
                }
                break;
            case 3:
                for (Sande sande : maquina.getSandes()) {
                    System.out.println(sande.getReferencia() + " - " + sande.getNome() + " - " + sande.getPreco());
                }
                break;
        }

        System.out.println("Escolha a referência do produto:");
        String referenciaProduto = scanner.nextLine();

        produtoSelecionado = maquina.buscarProdutoPorReferencia(referenciaProduto, tipoProduto);
        return produtoSelecionado;
    }
}
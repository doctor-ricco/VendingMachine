// Classe MenuCliente

package vending;

import java.util.Scanner;

public class MenuCliente {
    private final MaquinaVenda maquina;

    public MenuCliente(MaquinaVenda maquina) {
        this.maquina = maquina;
    }

    public void exibirMenu(Scanner scanner) {
        while (true) {
            System.out.println("Menu do Cliente:");
            System.out.println("1. Comprar Produto");
            System.out.println("2. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 2) {
                break;
            }

            switch (opcao) {
                case 1:
                    maquina.exibirProdutosDisponiveis();
                    comprarProduto(scanner);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void comprarProduto(Scanner scanner) {

        if (maquina.getChocolates().isEmpty() && maquina.getRefrigerantes().isEmpty() && maquina.getSandes().isEmpty()) {
            System.out.println("Máquina temporariamente indisponível. Dirija-se a outra estação. Obrigado.");
        } else {
            System.out.println("Escolha o tipo de produto (1: Chocolate, 2: Refrigerante, 3: Sande):");
            int tipoProduto = scanner.nextInt();
            scanner.nextLine();

            if (tipoProduto == 1 && !maquina.getChocolates().isEmpty()) {
                System.out.println("Produtos disponíveis:");
                for (Chocolate chocolate : maquina.getChocolates()) {
                    System.out.println(chocolate.getReferencia() + " - " + chocolate.getNome() + " - " + chocolate.getPreco());
                }
            } else if (tipoProduto == 2 && !maquina.getRefrigerantes().isEmpty()) {
                System.out.println("Produtos disponíveis:");
                for (Refrigerante refrigerante : maquina.getRefrigerantes()) {
                    System.out.println(refrigerante.getReferencia() + " - " + refrigerante.getNome() + " - " + refrigerante.getPreco());
                }
            } else if (tipoProduto == 3 && !maquina.getSandes().isEmpty()) {
                System.out.println("Produtos disponíveis:");
                for (Sande sande : maquina.getSandes()) {
                    System.out.println(sande.getReferencia() + " - " + sande.getNome() + " - " + sande.getPreco());
                }
            } else {
                System.out.println("Não há produtos disponíveis nesta categoria.");
                System.out.println();
                return;
            }

            System.out.println("Escolha a referência do produto:");
            String referenciaProduto = scanner.nextLine();

            Produto produtoSelecionado = null;
            if (tipoProduto == 1) {
                for (Chocolate chocolate : maquina.getChocolates()) {
                    if (chocolate.getReferencia().equals(referenciaProduto)) {
                        produtoSelecionado = chocolate;
                        break;
                    }
                }
            } else if (tipoProduto == 2) {
                for (Refrigerante refrigerante : maquina.getRefrigerantes()) {
                    if (refrigerante.getReferencia().equals(referenciaProduto)) {
                        produtoSelecionado = refrigerante;
                        break;
                    }
                }
            } else if (tipoProduto == 3) {
                for (Sande sande : maquina.getSandes()) {
                    if (sande.getReferencia().equals(referenciaProduto)) {
                        produtoSelecionado = sande;
                        break;
                    }
                }
            }

            if (produtoSelecionado == null) {
                System.out.println("Produto não encontrado.");
                return;
            }

            System.out.println("Insira o montante:");
            double montante = scanner.nextDouble();
            scanner.nextLine();

            if (montante < produtoSelecionado.getPreco()) {
                System.out.printf("Montante insuficiente! Você precisa de %.2f a mais.\n", produtoSelecionado.getPreco() - montante);
                return;
            }

            // Registar a venda
            maquina.registrarVenda(produtoSelecionado.getPreco(), produtoSelecionado.getNome());
            System.out.printf("Compra realizada com sucesso! Troco: %.2f\n", montante - produtoSelecionado.getPreco());

            // Decrementar o estoque
            if (produtoSelecionado instanceof Chocolate) {
                maquina.getChocolates().remove(produtoSelecionado);
            } else if (produtoSelecionado instanceof Refrigerante) {
                maquina.getRefrigerantes().remove(produtoSelecionado);
            } else if (produtoSelecionado instanceof Sande) {
                maquina.getSandes().remove(produtoSelecionado);
            }
        }
        maquina.salvarDados();
    }
}

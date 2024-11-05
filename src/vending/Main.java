package vending;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("***     ********");
        System.out.println("***     ***********");
        System.out.println("***     ***      ***");
        System.out.println("***     *********");
        System.out.println("***     ***  ***");
        System.out.println("***     ***     ***");
        System.out.println("***     ***      ***");
        System.out.println("***     ***      ***");
        System.out.println(" ");
        System.out.println("VENDING MACHINES");
        System.out.println(" ");

        Scanner scanner = new Scanner(System.in);
        MaquinaVenda maquina = new MaquinaVenda();
        maquina.carregarDados();

        while (true) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Colaborador");
            System.out.println("2. Cliente");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 3) {
                maquina.salvarDados();
                System.out.println("Dados salvos. Saindo...");
                break;
            }

            switch (opcao) {
                case 1:
                    menuColaborador(scanner, maquina);
                    break;
                case 2:
                    menuCliente(scanner, maquina);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private static void menuColaborador(Scanner scanner, MaquinaVenda maquina) {
        Colaborador colaborador = new Colaborador("João", "12345");

        while (true) {
            System.out.println("Menu do Colaborador:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Visualizar Histórico de Vendas");
            System.out.println("3. Retirar Produto");
            System.out.println("4. Consultar Saldo Total");
            System.out.println("5. Voltar");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 5) {
                break;
            }

            switch (opcao) {
                case 1:

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
                        System.out.println("Tipo (1: Normal || 2: Sem açúcar)"); // FALTA IMPLEMENTAR COMO INTEIRO AO INVÉS DE STRING
                        int tipo = scanner.nextInt();
                        System.out.println("Marca:");
                        String marca = scanner.nextLine();
                        novoProduto = new Refrigerante(nome, preco, referencia, prazoValidade, tipo, marca);
                    } else if (tipoProduto == 3) {
                        System.out.println("Tipo (mista/fiambre/queijo):");
                        String tipo = scanner.nextLine();
                        System.out.println("Nome do Produtor:");
                        String nomeProdutor = scanner.nextLine();
                        novoProduto = new Sande(nome, preco, referencia, prazoValidade, tipo, nomeProdutor);
                    }

                    if (novoProduto != null) {
                        colaborador.adicionarProduto(maquina, novoProduto);
                    }
                    maquina.salvarDados();

                    break;

                case 2:
                    System.out.println("Histórico de Vendas:");
                    maquina.visualizarHistorico();
                    break;
                case 3:
                    System.out.println("Referência do produto a ser retirado:");
                    String referenciaProduto = scanner.nextLine();
                    colaborador.retirarProduto(maquina, referenciaProduto);
                    maquina.salvarDados();

                    break;
                case 4:
                    colaborador.consultarSaldoTotal(maquina);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuCliente(Scanner scanner, MaquinaVenda maquina) {
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
                    comprarProduto(scanner, maquina);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void comprarProduto(Scanner scanner, MaquinaVenda maquina) {

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
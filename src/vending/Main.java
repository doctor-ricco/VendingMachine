package vending;

import java.util.Scanner;
import java.io.Console;

public class Main {
    public static void main(String[] args) {
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
                    autenticarEExecutarMenuColaborador(scanner, maquina);
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

    private static void autenticarEExecutarMenuColaborador(Scanner scanner, MaquinaVenda maquina) {
        Usuario usuario = new Usuario("Isabela", "senha123"); // Login e senha de exemplo
        Colaborador colaborador = new Colaborador("João", "12345", usuario);

        Console console = System.console();
        if (console == null) {
            System.out.println("Console não está disponível. Execute o programa em um ambiente que suporte Console.");
            return;
        }

        System.out.println("Digite o nome de usuário:");
        String username = console.readLine();

        System.out.println("Digite a senha:");
        char[] passwordChars = console.readPassword(); // Lê a senha sem exibi-la
        String password = new String(passwordChars);

        if (colaborador.autenticar(username, password)) {
            System.out.println("Autenticação bem-sucedida!");
            menuColaborador(scanner, maquina, colaborador);
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    private static void menuColaborador(Scanner scanner, MaquinaVenda maquina, Colaborador colaborador) {
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
                    // Lógica para adicionar produto
                    break;
                case 2:
                    System.out.println("Histórico de Vendas:");
                    maquina.visualizarHistorico();
                    System.out.println(" ");
                    break;
                case 3:
                    maquina.exibirProdutosDisponiveis();
                    System.out.println(" ");
                    System.out.println("Referência do produto a ser retirado:");
                    String referenciaProduto = scanner.nextLine();
                    colaborador.retirarProduto(maquina, referenciaProduto);
                    maquina.salvarDados();
                    break;
                case 4:
                    colaborador.consultarSaldoTotal(maquina);
                    System.out.println(" ");
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
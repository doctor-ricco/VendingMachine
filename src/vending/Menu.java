package vending;

import java.util.Scanner;

public class Menu {
    private final Colaborador colaborador;
    private final MaquinaVenda maquina;

    public Menu(Colaborador colaborador, MaquinaVenda maquina) {
        this.colaborador = colaborador;
        this.maquina = maquina;
    }

    public void exibirMenuPrincipal(Scanner scanner) {
        while (true) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1. Menu do Colaborador");
            System.out.println("2. Menu do Cliente");
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
                    autenticarColaborador(scanner);
                    break;
                case 2:
                    exibirMenuCliente(scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void autenticarColaborador(Scanner scanner) {
        System.out.println("Digite o nome de usuário:");
        String username = scanner.nextLine();

        System.out.println("Digite a senha:");
        String password = scanner.nextLine();

        if (colaborador.autenticar(username, password)) {
            System.out.println("Autenticação bem-sucedida!");
            exibirMenuColaborador(scanner);
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    private void exibirMenuColaborador(Scanner scanner) {
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
                    colaborador.adicionarProduto(maquina, scanner);
                    break;
                case 2:
                    maquina.visualizarHistorico();
                    System.out.println(" ");
                    break;
                case 3:
                    colaborador.retirarProduto(maquina, scanner);
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

    private void exibirMenuCliente(Scanner scanner) {
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
                    Cliente.comprarProduto(maquina, scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}

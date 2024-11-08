package vending;

import java.util.Scanner;

public class MenuColaborador {
    private final Colaborador colaborador;
    private final MaquinaVenda maquina;

    public MenuColaborador(Colaborador colaborador, MaquinaVenda maquina) {
        this.colaborador = colaborador;
        this.maquina = maquina;
    }

    public void exibirMenu(Scanner scanner) {
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
                        System.out.println("Tipo (1: Normal || 2: Sem açúcar)");
                        int tipo = scanner.nextInt();
                        System.out.println("Marca:");
                        String marca = scanner.nextLine();
                        novoProduto = new Refrigerante(nome, preco, referencia, prazoValidade, tipo, marca);
                    } else if (tipoProduto == 3) {
                        System.out.println("Tipo (1: Queijo || 2: Fiambre || 3: Mista):");
                        int tipo = scanner.nextInt();
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
}
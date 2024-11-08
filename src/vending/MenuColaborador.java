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
}
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
                    new MenuCliente(maquina).exibirMenu(scanner);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private static void autenticarEExecutarMenuColaborador(Scanner scanner, MaquinaVenda maquina) {
        Usuario usuario = new Usuario("Isabella", "senha123"); //Credenciais do User Isabella
        Colaborador colaborador = new Colaborador("Isabella", "12345", usuario); // Info do Colaborador

        Console console = System.console();
        if (console == null) {
            System.out.println("Console não está disponível. Execute o programa em um ambiente que suporte Console.");
            return;
        }

        System.out.println("Digite o nome de usuário:");
        String username = console.readLine();

        System.out.println("Digite a senha:");
        char[] passwordChars = console.readPassword(); // Lê a senha sem exibi-la, mas não funciona no InteliJ, só na consola do SO
        String password = new String(passwordChars);

        if (colaborador.autenticar(username, password)) {
            System.out.println("Autenticação bem-sucedida!");
            new MenuColaborador(colaborador, maquina).exibirMenu(scanner);
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }
}
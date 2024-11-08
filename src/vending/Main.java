package vending;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaquinaVenda maquina = new MaquinaVenda();
        maquina.carregarDados();

        Usuario usuario = new Usuario("Isabella", "senha123"); // Credenciais do colaborador
        Colaborador colaborador = new Colaborador("Isabella", "12345", usuario); // Informações do colaborador

        Menu menu = new Menu(colaborador, maquina);

        menu.exibirMenuPrincipal(scanner);

        scanner.close();
    }
}

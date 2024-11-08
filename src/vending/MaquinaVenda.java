
package vending;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

class MaquinaVenda implements Serializable {
    private List<Chocolate> chocolates = new ArrayList<>();
    private List<Refrigerante> refrigerantes = new ArrayList<>();
    private List<Sande> sandes = new ArrayList<>();
    private double saldoTotal;
    private List<String> historicoVendas = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        if (produto instanceof Chocolate) {
            if (chocolates.size() < 20) {
                chocolates.add((Chocolate) produto);
                System.out.println("Chocolate adicionado com sucesso.");
            } else {
                System.out.println("Estoque de chocolates cheio.");
            }
        } else if (produto instanceof Refrigerante) {
            if (refrigerantes.size() < 15) {
                refrigerantes.add((Refrigerante) produto);
                System.out.println("Refrigerante adicionado com sucesso.");
            } else {
                System.out.println("Estoque de refrigerantes cheio.");
            }
        } else if (produto instanceof Sande) {
            if (sandes.size() < 10) {
                sandes.add((Sande) produto);
                System.out.println("Sande adicionada com sucesso.");
            } else {
                System.out.println("Estoque de sandes cheio.");
            }
        }
    }

    public Produto buscarProdutoPorReferencia(String referencia, int tipoProduto) {
        switch (tipoProduto) {
            case 1:
                for (Chocolate chocolate : chocolates) {
                    if (chocolate.getReferencia().equals(referencia)) {
                        return chocolate;
                    }
                }
                break;
            case 2:
                for (Refrigerante refrigerante : refrigerantes) {
                    if (refrigerante.getReferencia().equals(referencia)) {
                        return refrigerante;
                    }
                }
                break;
            case 3:
                for (Sande sande : sandes) {
                    if (sande.getReferencia().equals(referencia)) {
                        return sande;
                    }
                }
                break;
            default:
                System.out.println("Tipo de produto inválido.");
                return null;
        }
        System.out.println("Produto não encontrado.");
        return null;
    }

    public void registrarVenda(double preco, String nomeProduto) {
        saldoTotal += preco;
        historicoVendas.add(nomeProduto + " - " + preco);
    }

    public void visualizarHistorico() {
        if (historicoVendas.isEmpty()) {
            System.out.println("Não há histórico de vendas a exibir.");
        } else {
            for (String venda : historicoVendas) {
                System.out.println(venda);
            }
        }
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public List<Chocolate> getChocolates() {
        return chocolates;
    }

    public List<Refrigerante> getRefrigerantes() {
        return refrigerantes;
    }

    public List<Sande> getSandes() {
        return sandes;
    }

    public boolean removerProdutoPorReferencia(String referencia) {
        for (Chocolate chocolate : chocolates) {
            if (chocolate.getReferencia().equals(referencia)) {
                chocolates.remove(chocolate);
                return true;
            }
        }
        for (Refrigerante refrigerante : refrigerantes) {
            if (refrigerante.getReferencia().equals(referencia)) {
                refrigerantes.remove(refrigerante);
                return true;
            }
        }
        for (Sande sande : sandes) {
            if (sande.getReferencia().equals(referencia)) {
                sandes.remove(sande);
                return true;
            }
        }
        return false;
    }

    public void exibirProdutosDisponiveis() {
        System.out.println(" ");
        System.out.println("Produtos Disponíveis:");
        System.out.println(" ");
        if (chocolates.isEmpty()) {
            System.out.println("Não há chocolates disponíveis no momento.");
        } else {
            System.out.println("Chocolates:");
            for (Chocolate chocolate : chocolates) {
                System.out.printf("Referência: %s, Nome: %s, Preço: %.2f%n", chocolate.getReferencia(), chocolate.getNome(), chocolate.getPreco());
            }
        }

        if (refrigerantes.isEmpty()) {
            System.out.println("Não há refrigerantes disponíveis no momento.");
        } else {
            System.out.println("Refrigerantes:");
            for (Refrigerante refrigerante : refrigerantes) {
                System.out.printf("Referência: %s, Nome: %s, Preço: %.2f%n", refrigerante.getReferencia(), refrigerante.getNome(), refrigerante.getPreco());
            }
        }

        if (sandes.isEmpty()) {
            System.out.println("Não há sandes disponíveis no momento.");
        } else {
            System.out.println("Sandes:");
            for (Sande sande : sandes) {
                System.out.printf("Referência: %s, Nome: %s, Preço: %.2f%n", sande.getReferencia(), sande.getNome(), sande.getPreco());
            }
        }
    }


    public void carregarDados() {
        File file = new File("stock.dat");
        if (!file.exists()) {
            System.out.println("Arquivo de dados não encontrado. Iniciando novo estoque.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            MaquinaVenda maquina = (MaquinaVenda) ois.readObject();
            this.chocolates = maquina.chocolates;
            this.refrigerantes = maquina.refrigerantes;
            this.sandes = maquina.sandes;
            this.saldoTotal = maquina.saldoTotal;
            this.historicoVendas = maquina.historicoVendas;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stock.dat"))) {
            oos.writeObject(this);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }














}
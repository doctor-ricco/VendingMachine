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

//    public void carregarDados() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stock.dat"))) {
//            MaquinaVenda maquina = (MaquinaVenda) ois.readObject();
//            this.chocolates = maquina.chocolates;
//            this.refrigerantes = maquina.refrigerantes;
//            this.sandes = maquina.sandes;
//            this.saldoTotal = maquina.saldoTotal;
//            this.historicoVendas = maquina.historicoVendas;
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Erro ao carregar dados.");
//        }
//    }

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


//    public void salvarDados() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stock.dat"))) {
//            oos.writeObject(this);
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar dados.");
//        }
//    }

    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stock.dat"))) {
            oos.writeObject(this);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public boolean removerProdutoPorReferencia(String referencia) {
        // Tentar remover de cada lista de produtos
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
        return false; // Retorna falso se o produto não foi encontrado
    }

    public void exibirProdutosDisponiveis() {

        System.out.println("Produtos Disponíveis:");
        if (chocolates.isEmpty()) {
            System.out.println("Não há chocolates disponíveis no momento.");
        } else {
            String cacauTipoString="";
            System.out.println("Chocolates:");
            for (Chocolate chocolate : chocolates) {
                if(chocolate.getTipoCacau()==1){
                    cacauTipoString="Branco";
                } else if (chocolate.getTipoCacau()==2) {
                    cacauTipoString="Ao leite";
                } else{
                    cacauTipoString="Negro";
                }
                System.out.printf("Referência: %s, Nome: %s, Tipo Cacau: %s, Preço: %.2f%n", chocolate.getReferencia(), chocolate.getNome(), cacauTipoString , chocolate.getPreco());
            }
        }

        if (refrigerantes.isEmpty()) {
            System.out.println("Não há refrigerantes disponíveis no momento.");
        } else {
            String tipoRefri="";
            System.out.println("Refrigerantes:");
            for (Refrigerante refrigerante : refrigerantes) {
                if(refrigerante.getTipo()==1){
                    tipoRefri="Normal";
                }else{
                    tipoRefri="Sem açucar";
                }
                System.out.printf("Referência: %s, Nome: %s, Tipo: %s, Preço: %.2f%n", refrigerante.getReferencia(), refrigerante.getNome(), tipoRefri , refrigerante.getPreco());
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

}
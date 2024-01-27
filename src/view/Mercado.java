package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import model.Produto;
import util.Utils;

/**
 *
 * @author Bruno
 */
public class Mercado {

    private static Scanner teclado = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {

        produtos = new ArrayList<Produto>();
        carrinho = new HashMap<Produto, Integer>();
        Mercado.menu();

    }

    private static void menu() {
        System.out.println("===========================");
        System.out.println("==========Bem-vindo========");
        System.out.println("===========================");

        System.out.println("Selecione uma opção abaixo:");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Comprar Produto");
        System.out.println("4 - Visualizar Carrinho");
        System.out.println("5 - Sair");

        int opcao = 0;

        try {
            opcao = Integer.parseInt(Mercado.teclado.nextLine());

        } catch (InputMismatchException e) {
            Mercado.menu();
        } catch (NumberFormatException f) {
            Mercado.menu();
        }

        switch (opcao) {
            case 1:
                Mercado.cadastrarProduto();
                break;
            case 2:
                Mercado.listarProdutos();
                break;
            case 3:
                Mercado.comprarProdutos();
                break;
            case 4:
                Mercado.visualizarCarrinho();
                break;
            case 5:
                System.out.println("Volte sempre!");
                Utils.pausar(2);
                System.exit(0);
            default:
                System.out.println("Opção Inválida");
                Utils.pausar(2);
                Mercado.menu();
                break;
        }
    }

    private static void cadastrarProduto() {
        System.out.println("Cadastro de Produto");
        System.out.println("Informe o nome do produto: ");
        String nome = Mercado.teclado.nextLine();

        System.out.println("Informe o preço do produto: ");
        Double preco = Mercado.teclado.nextDouble();

        Produto produto = new Produto(nome, preco);

        Mercado.produtos.add(produto);

        System.out.println("O produto " + produto.getNome() + " foi cadastrado com sucesso!");
        Utils.pausar(2);
        Mercado.menu();

    }

    private static void listarProdutos() {

        if (Mercado.produtos.size() > 0) {
            System.out.println("Listagem de Produtos: ");
            System.out.println();

            for (Produto p : Mercado.produtos) {
                System.out.println(p);
                System.out.println();
            }
        } else {
            System.out.println("Não existem produtos cadastrados");
        }
        Utils.pausar(5);
        Mercado.menu();
    }

    private static void comprarProdutos() {
        if (Mercado.produtos.size() > 0) {
            System.out.println("Informe o codigo do produto que deseja comprar: ");
            System.out.println();
            System.out.println("********** Produtos disponiveis **********");
            for (Produto p : Mercado.produtos) {
                System.out.println(p);
                System.out.println("---------------------");
            }
            int codigo = Integer.parseInt(Mercado.teclado.nextLine());
            boolean existe = false;

            for (Produto p : Mercado.produtos) {
                if (p.getCodigo() == codigo) {
                    int quantidade = 0;
                    try {
                        quantidade = Mercado.carrinho.get(p);
                        //Ja tem esse produto atualiza a quantidade
                        Mercado.carrinho.put(p, quantidade + 1);
                    } catch (NullPointerException e) {
                        //Primeiro produto no carrinho
                        Mercado.carrinho.put(p, 1);
                    }

                    System.out.println("O produto " + p.getNome() + " foi adicionado ao carrinho");
                    existe = true;

                    if (existe) {
                        System.out.println("Deseja adicionar mais produtos?");
                        System.out.println("Informe 1 para sim ou 0 para não: ");
                        int op = Integer.parseInt(Mercado.teclado.nextLine());

                        if (op == 1) {
                            Mercado.comprarProdutos();
                        } else {
                            System.out.println("Aguarde enquanto fechamos o pedido...");
                            Utils.pausar(2);
                            Mercado.fecharPedido();
                        }
                    } else {
                        System.out.println("Produto não encontrado com o código  " + codigo);
                        Mercado.menu();
                    }
                }

            }

        } else {
            System.out.println("Não existem produtos cadastrados");
            Utils.pausar(2);
            Mercado.menu();

        }

    }

    private static void visualizarCarrinho() {
        if (Mercado.carrinho.size() > 0) {
            System.out.println("Produtos no carrinho: ");

            for (Produto p : Mercado.carrinho.keySet()) {
                System.out.println("Produto: " + p + "\nQuantidade: " + Mercado.carrinho.get(p));
            }
        } else {
            System.out.println("Não existem produtos no carrinho!");
        }
        Utils.pausar(3);
        Mercado.menu();
    }

    private static void fecharPedido() {
        Double valorTotal = 0.0;
        System.out.println("Produtos do Carrinho: ");
        System.out.println();
        for (Produto p : Mercado.carrinho.keySet()) {
            int quantidade = Mercado.carrinho.get(p);
            valorTotal = p.getPreco() * quantidade;
            System.out.println(p);
            System.out.println("Quantidade: " + quantidade);
            System.out.println("----------------");
        }
        System.out.println("Valor de compra é " + Utils.doubleToString(valorTotal));
        Mercado.carrinho.clear();
        System.out.println("Obrigado pela preferencia");
        Utils.pausar(5);
        Mercado.menu();
    }

}

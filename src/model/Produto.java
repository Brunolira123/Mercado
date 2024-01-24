package model;

import util.Utils;

/**
 *
 * @author Bruno
 */
public class Produto {

    private static int contador = 1;

    private int codigo;
    private String nome;
    private Double preco;

    public Produto(String nome, Double preco) {
        this.codigo = Produto.contador;
        this.nome = nome;
        this.preco = preco;
        Produto.contador += 1;
    }

    public int getCodigo() {
        return this.codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Codigo: " + this.getCodigo()
                + "\nNome: " + this.getNome()
                + "\nPreço: " + Utils.doubleToString(this.getPreco());
    }

}

package edu.devEdylan.operacoes;

import edu.devEdylan.model.Conta;

public class Operacao {
    private double valor;
    private String nomeOperacao;
    private Conta contadestino;

    public Operacao(String nomeOperacao, double valor){
        this.nomeOperacao = nomeOperacao;
        this.valor = valor;
    }

    public Operacao(String nomeOperacao, double valor, Conta contadestino){
        if (nomeOperacao.equalsIgnoreCase("transferencia")) {
            this.nomeOperacao = nomeOperacao;
            this.valor = valor;
            this.contadestino = contadestino;
        }
    }

    public double getValor() {
        return valor;
    }

    public String getNomeOperacao() {
        return nomeOperacao;
    }

    public Conta getContadestino() {
        return contadestino;
    }

    public String toString() {
        if (this.nomeOperacao.equalsIgnoreCase("transferencia")) {
            return "Operacao: " + nomeOperacao + "; Valor: " + valor + " para Conta " + contadestino.getNumero();
        }
        return "Operacao: " + nomeOperacao + "; Valor: " + valor;
    }
}

package edu.devEdylan.model;

import edu.devEdylan.operacoes.Operacao;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta{

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<Operacao> operacoesRealizadas = new ArrayList<>();

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void sacar(double valor) {
        saldo -= valor;
        operacoesRealizadas.add(new Operacao("Saque", valor));
    }

    public void depositar(double valor) {
        saldo += valor;
        operacoesRealizadas.add(new Operacao("Depósito", valor));
    }

    public void transferir(double valor, Conta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
        operacoesRealizadas.add(new Operacao("Transferencia", valor, contaDestino));
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    public void getOperacoesRealizadas(){
        operacoesRealizadas.stream().forEach(n -> System.out.println(n));
    }

    public abstract void imprimirExtrato();
}
package edu.devEdylan.model;

import edu.devEdylan.operacoes.Operacao;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta{

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected double limiteSaque;
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
        if (verificarSaldo(valor)){
            saldo -= valor;
            operacoesRealizadas.add(new Operacao("Saque", valor));
        }

    }

    public void depositar(double valor) {
        saldo += valor;
        operacoesRealizadas.add(new Operacao("Depósito", valor));
    }

    public void transferir(double valor, Conta contaDestino) {
        if (verificarSaldo(valor)){
            saldo -= valor;
            contaDestino.saldo += valor;
            operacoesRealizadas.add(new Operacao("Transferencia", valor, contaDestino));
            contaDestino.operacoesRealizadas.add(new Operacao("Transferencia", valor, contaDestino));
        }
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

    private boolean verificarSaldo(double valor){
        if ((saldo > limiteSaque) && (limiteSaque != 0)){
            System.out.println("Limite de Saque Atingido. Operação Negada.");
            return false;
        }
        if (saldo < valor){
            System.out.println("Saldo insuficiente");
            return false;
        }
        return true;

    }
    public void definirLimiteSaque(double lim){
        this.limiteSaque = lim;
        operacoesRealizadas.add(new Operacao("Definição de limite de saque", lim));
    }

    public void desfazerUltimaOperacao(){
        int ultimaOperacaoIndex = operacoesRealizadas.size() - 1;
        Operacao ultimaOperacao = operacoesRealizadas.get(ultimaOperacaoIndex);
        String nomeOperacao = ultimaOperacao.getNomeOperacao();
        Double valorOperacao = ultimaOperacao.getValor();
        Conta contaDestinoOperacao = ultimaOperacao.getContadestino();
        switch (nomeOperacao){
            case "Saque":
                saldo += valorOperacao;
                break;
            case "Depósito":
                saldo -= valorOperacao;
                break;
            case "Transferencia":
                System.out.println("Não é possível desfazer uma transferência, visto que envolve o domínio monetário de outrem, solicite à conta destino da transferência, a devolução!");
                break;
            case "Definição de limite de saque":
                this.limiteSaque = 0;
                break;
            default:
                System.out.println("Nenhuma operação foi realizada para poder ser desfeita.");
        }
        operacoesRealizadas.remove(ultimaOperacaoIndex);
    }

    public abstract void imprimirExtrato();
}
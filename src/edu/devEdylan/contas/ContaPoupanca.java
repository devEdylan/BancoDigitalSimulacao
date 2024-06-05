package edu.devEdylan.contas;

import edu.devEdylan.model.Cliente;
import edu.devEdylan.model.Conta;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupança ===");
        super.imprimirInfosComuns();
        System.out.println("=================================");
        System.out.println("Operações Recentes:");
        super.getOperacoesRealizadas();
        System.out.println("=================================");
    }
}
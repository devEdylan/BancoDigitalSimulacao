package edu.devEdylan.contas;

import edu.devEdylan.model.Cliente;
import edu.devEdylan.model.Conta;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
        System.out.println("=================================");
        System.out.println("Operações Recentes:");
        super.getOperacoesRealizadas();
        System.out.println("=================================");
    }

}
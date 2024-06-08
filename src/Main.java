import edu.devEdylan.contas.*;
import edu.devEdylan.model.*;

public class Main {

    public static void main(String[] args) {
        Cliente venilton = new Cliente();
        venilton.setNome("Venilton");

        Conta cc = new ContaCorrente(venilton);
        Conta poupanca = new ContaPoupanca(venilton);

        cc.depositar(1050);
        cc.transferir(100, poupanca);
        cc.definirLimiteSaque(1000);
        cc.sacar(950);
        cc.imprimirExtrato();
        //poupanca.imprimirExtrato();
        System.out.println("==============");
        cc.desfazerUltimaOperacao();
        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
        System.out.println("==============");
        poupanca.desfazerUltimaOperacao();

    }

}
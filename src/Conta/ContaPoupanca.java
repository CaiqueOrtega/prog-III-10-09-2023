package Conta;

import Cliente.Cliente;

public class ContaPoupanca extends AbstractConta {


    public ContaPoupanca(Cliente cliente, int codigoAgencia, int senha) {
        super(cliente, codigoAgencia, senha);
    }
}

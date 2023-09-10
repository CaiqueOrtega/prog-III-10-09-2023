package Conta;

import Cliente.ClienteService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class ContaService {

    private final int SAQUE = 1, DEPOSITO = 2, TRANSFERENCIA = 3, ORIGEM_CORRENTE =4, ORIGEM_POUPANCA = 5;
    private final boolean POUPANCA = false, CORRENTE = true;
    private ClienteService clienteService = new ClienteService();
    private HashMap<String, AbstractConta> hashContaCorrente = new HashMap<String, AbstractConta>();
    private HashMap<String, AbstractConta> hashContaPoupanca = new HashMap<String, AbstractConta>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ContaService(ClienteService cliente) {
        this.clienteService = cliente;
    }

//___________________________________________________________________________________________
    public void cadastrarContaCorrente() throws Exception {
        System.out.println("+--------------------------------------------------+");
        System.out.println("             Cadastrar Conta Corrente              |");
        System.out.println("+--------------------------------------------------+");

        ContaCorrente(new ContaCorrente(clienteService.verificarCliente(false),
                getCodigoAgencia(), getSenha()));
    }

    public void ContaCorrente(AbstractConta conta) throws Exception {
        if (hashContaCorrente.containsKey(conta.getCpf())) {
            System.out.println("> O CPF inserido ja possui uma conta corrente " +
                    "\nAperte qualquer tecla para continuar");
            System.in.read();
            return;
        }
        hashContaCorrente.put(conta.getCpf(), conta);

        System.out.println(hashContaCorrente.toString());
    }

//___________________________________________________________________________________________

    public void cadastrarContaPoupanca() throws Exception {
        System.out.println("+--------------------------------------------------+");
        System.out.println("             Cadastrar Conta Poupanca              |");
        System.out.println("+--------------------------------------------------+");

        ContaPoupanca(new ContaPoupanca(clienteService.verificarCliente(false),
                getCodigoAgencia(), getSenha()));

    }

    private void ContaPoupanca(AbstractConta conta) throws Exception {
        if (hashContaPoupanca.containsKey(conta.getCpf())) {
            System.out.println("> O CPF inserido ja possui uma conta corrente " +
                    "\nAperte qualquer tecla para continuar");
            System.in.read();
            return;
        }
        hashContaPoupanca.put(conta.getCpf(), conta);

        System.out.println(hashContaPoupanca.toString());

    }
//___________________________________________________________________________________________

    private Integer getSenha() throws Exception {
        System.out.println("> Insira uma senha de 4 digitos");
        String verificaSenha = reader.readLine();
        if (!verificaSenha.matches("[0-9]+") || verificaSenha.length() != 4) {
            System.out.println("Senha com parametros invalidos");
            return  getSenha();
        } else {
            int senha = Integer.parseInt(verificaSenha);
            System.out.println("+--------------------------------------------------+");
            return senha;
        }

    }

    private Boolean verificarSenha(AbstractConta conta) throws Exception {
        if (conta.getSenha() == getSenha() ){

            return true;
        } else {
            return false;
        }
    }

//___________________________________________________________________________________________
    private Integer getCodigoAgencia() throws NumberFormatException, IOException {
        System.out.println("> Insira o codigo da agencia: ");
        try {
            return Integer.parseInt(reader.readLine());

        } catch (NumberFormatException e) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("> Insira apenas numeros");
            System.out.println("+--------------------------------------------------+");
            return getCodigoAgencia();
        }

    }

    private Boolean verificarCodigoAgencia(AbstractConta conta) throws NumberFormatException, IOException {
        if (conta.getCodigoAgencia() == getCodigoAgencia()) {
            return true;
        } else {
            System.out.println("+--------------------------------------------------+");
            System.out.println("> Codigo de agencia não existe");
            System.out.println("+--------------------------------------------------+");
            return verificarCodigoAgencia(conta);
        }
    }
 //___________________________________________________________________________________________


    private AbstractConta verificarContaPoupanca() throws IOException {
        System.out.println("> Insira o CPF: ");
        String cpf = reader.readLine();
        if (hashContaPoupanca.containsKey(cpf)) {
            return hashContaPoupanca.get(cpf);
        } else {
            System.out.println("+------------------------------------------------------------+");
            System.out.println("> CPF não foi cadastrado como conta poupança, insira novamente");
            System.out.println("+------------------------------------------------------------+");

            return verificarContaPoupanca();
        }
    }


    private AbstractConta verificarContaCorrente() throws IOException {
        System.out.println("> Insira o CPF: ");
        String cpf = reader.readLine();
        if (hashContaCorrente.containsKey(cpf)) {
            return hashContaCorrente.get(cpf);
        } else {
            System.out.println("+------------------------------------------------------------+");
            System.out.println("> CPF não foi cadastrado como conta corrente, insira novamente");
            System.out.println("+------------------------------------------------------------+");
            return verificarContaCorrente();
        }
    }
//___________________________________________________________________________________________

    private void verificarConta(int verificar) throws Exception {
        System.out.println("   [1]   |  Conta Corrente                         |");
        System.out.println("   [2]   |  Conta Poupança                         |");
        switch (Integer.parseInt(reader.readLine())) {
            case 1:
                if (verificar == SAQUE) {
                    efetuarSaque(verificarContaCorrente(), CORRENTE);
                } else if (verificar == DEPOSITO) {
                    efetuarDeposito(verificarContaCorrente(), CORRENTE);

                } else if (verificar == TRANSFERENCIA) {
                    System.out.println("> Qual a conta destino da tranferencia");
                    verificarConta(ORIGEM_CORRENTE);

                } else if (verificar == ORIGEM_CORRENTE) {
                    System.out.println("> Insira o CPF da conta Origem, e apos a de Destino");
                    efetuarTransferencia(verificarContaCorrente(), verificarContaCorrente(), CORRENTE, CORRENTE);

                } else if (verificar == ORIGEM_POUPANCA) {
                    System.out.println("> Insira o CPF da conta Origem, e apos a de Destino");
                    efetuarTransferencia(verificarContaPoupanca(), verificarContaCorrente(), POUPANCA, CORRENTE);
                }

                break;
            case 2:
                if (verificar == SAQUE) {
                    efetuarSaque(verificarContaPoupanca(), POUPANCA);
                } else if (verificar == DEPOSITO) {
                    efetuarDeposito(verificarContaPoupanca(), POUPANCA);

                } else if (verificar == TRANSFERENCIA) {
                    System.out.println("> Qual a conta destino da tranferencia");
                    verificarConta(ORIGEM_POUPANCA);

                } else if (verificar == ORIGEM_CORRENTE) {
                    System.out.println("> Insira o CPF da conta Origem, e apos a de Destino");
                    efetuarTransferencia(verificarContaCorrente(), verificarContaPoupanca(), CORRENTE, POUPANCA);
                } else if (verificar == ORIGEM_POUPANCA) {
                    System.out.println("> Insira o CPF da conta Origem, e apos a de Destino");
                    efetuarTransferencia(verificarContaPoupanca(), verificarContaPoupanca(), POUPANCA, POUPANCA);
                }

                break;
            default:
                System.out.println("+--------------------------------------------------+");
                System.out.println("> Insira um valor presente no menu");
                System.out.println("+--------------------------------------------------+");
                return;
        }


    }
//___________________________________________________________________________________________

    public void Saque() throws Exception {
        System.out.println("+--------------------------------------------------+");
        System.out.println("| Atalho |             Efetuar Saque               |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("+ Qual a conta que deseja efetuar o saque?         |");
        verificarConta(SAQUE);

    }


    private void efetuarSaque( AbstractConta conta, boolean verificar) throws Exception {
        verificarCodigoAgencia(conta);
        Double valor = getValorTransacao();
        if (verificarSenha(conta)) {
            if (!verificarSaldo(conta, valor)) {
                System.out.println("+--------------------------------------------------+");
                System.out.println("Saque nao pode ser concluido. Saldo insuficiente");
                System.out.println("+--------------------------------------------------+");
            } else {
                conta.saldo = conta.saldo - valor;

                if (verificar) {
                    hashContaCorrente.replace(conta.getCpf(), conta);
                    System.out.println(hashContaCorrente.toString());
                } else{
                    hashContaPoupanca.replace(conta.getCpf(), conta);
                    System.out.println(hashContaPoupanca.toString());
                }
            }
        }
    }

//___________________________________________________________________________________________
public void deposito() throws Exception {
    System.out.println("+--------------------------------------------------+");
    System.out.println("| Atalho |          Efetuar Deposito               |");
    System.out.println("+--------------------------------------------------+");
    System.out.println("+ Qual a conta que deseja efetuar o deposito       |");
    verificarConta(DEPOSITO);

}


    private void efetuarDeposito( AbstractConta conta, boolean verificar) throws Exception {
        verificarCodigoAgencia(conta);
        Double valor = getValorTransacao();
        if (verificarSenha(conta)) {
            conta.saldo = conta.saldo + valor;

            if (verificar) {
                hashContaCorrente.replace(conta.getCpf(), conta);
                System.out.println(hashContaCorrente.toString());
            } else {
                hashContaPoupanca.replace(conta.getCpf(), conta);
                System.out.println(hashContaPoupanca.toString());
            }

        }
    }
//___________________________________________________________________________________________

    public void transferencia() throws Exception {
        System.out.println("+--------------------------------------------------+");
        System.out.println("| Atalho |         Efetuar Transferencia           |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("+ Qual a conta origem da tranferencia              |");
        verificarConta(TRANSFERENCIA);
    }

    private void efetuarTransferencia(AbstractConta contaOrigem, AbstractConta contaDestino, boolean origem, boolean destino) throws Exception {

        if (Objects.equals(contaOrigem.getCpf(), contaDestino.getCpf()) && origem == destino){
            System.out.println("+--------------------------------------------------+");
            System.out.println("Não é possivel fazer uma transferencia para a mesma conta");
            System.out.println("+--------------------------------------------------+");
            return;
        }
        System.out.println("+--------------------------------------------------+");
        System.out.println("> Insira o Codigo de Agencia da conta Origem, e apos a de Destino : ");

        verificarCodigoAgencia(contaOrigem);
        verificarCodigoAgencia(contaDestino);
        Double valor = getValorTransacao();
        if (!verificarSenha(contaOrigem)){
            System.out.println("+--------------------------------------------------+");
            System.out.println("Senha invalida");
            System.out.println("+--------------------------------------------------+");
            return;
        }
        if (!verificarSaldo(contaOrigem, valor)){
            System.out.println("+------------------------------------------------------+");
            System.out.println("Transferencia nao pode ser concluido. Saldo insuficiente");
            System.out.println("+------------------------------------------------------+");
            return;
        }
        contaOrigem.saldo = contaOrigem.saldo - valor;
        contaDestino.saldo = contaDestino.saldo + valor;


        if (origem == true && destino == true){
            hashContaCorrente.replace(contaDestino.getCpf(), contaDestino);
            hashContaCorrente.replace(contaOrigem.getCpf(), contaOrigem);
            System.out.println(hashContaCorrente.toString());
        } else if (origem == true && destino == false) {
            hashContaCorrente.replace(contaOrigem.getCpf(), contaOrigem);
            hashContaPoupanca.replace(contaDestino.getCpf(), contaDestino);
            System.out.println(hashContaCorrente.toString());
            System.out.println(hashContaPoupanca.toString());

        } else if (origem == false && destino == false) {
            hashContaPoupanca.replace(contaDestino.getCpf(), contaDestino);
            hashContaPoupanca.replace(contaOrigem.getCpf(), contaOrigem);
            System.out.println(hashContaPoupanca.toString());
        } else if (origem == false && destino == true) {
            hashContaPoupanca.replace(contaOrigem.getCpf(), contaOrigem);
            hashContaCorrente.replace(contaDestino.getCpf(), contaDestino);
            System.out.println(hashContaCorrente.toString());
            System.out.println(hashContaPoupanca.toString());

        }



    }
//___________________________________________________________________________________________
    private Boolean verificarSaldo(AbstractConta conta, Double valor){
        if (conta.saldo - valor < 0){
            return false;
        } else{
            return true;
        }
    }

    private Double getValorTransacao() throws NumberFormatException, IOException {
        double valor = 0;
        try {
        System.out.println("> Insira o valor desjado: ");
        valor = Double.parseDouble(reader.readLine());
            if (valor <= 0) {
                System.out.println("+--------------------------------------------------+");
                System.out.println("Valor nao pode ser igual ou menor a 0");
                System.out.println("+--------------------------------------------------+");
                return getValorTransacao();
            }
        }catch (Exception e){
            System.out.println("+--------------------------------------------------+");
            System.out.println("Invalido. Insira apenas numeros");
            System.out.println("+--------------------------------------------------+");
            return getValorTransacao();
        }

            return valor;
        }
    }

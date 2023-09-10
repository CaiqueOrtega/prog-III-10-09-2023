import Cliente.ClienteService;
import Conta.ContaService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean sistema = true;
    public static ClienteService clienteService = new ClienteService();
    private static ContaService contaService = new ContaService(clienteService);
    private static BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        do {

            switch (menu()) {
                case 1:
                    clienteService.cadastrarCliente();
                    break;

                case 2:
                    contaService.cadastrarContaCorrente();
                    break;

                case 3:
                    contaService.cadastrarContaPoupanca();
                    break;

                case 4:
                    contaService.Saque();
                    break;

                case 5:
                    contaService.deposito();
                    break;

                case 6:
                    contaService.transferencia();
                    break;


                case 0:

                    System.out.println("+--------------------------------------------------+");
                    System.out.println("Deseja mesmo sair (S)im/(N)ão");
                    System.out.println("+--------------------------------------------------+");
                    Terminal.confirmar = Terminal.verificarSN();
                    if (Terminal.confirmar.equalsIgnoreCase("N")) sistema = true;
                    else sistema = false;

                    if (!sistema) {
                        System.out.println("+--------------------------------------------------+");
                        System.out.println("          >>> Obrigado pela preferência  >>>        ");

                    }
                    break;

                default:
                    System.out.println("> Insira uma das opçoes do menu!" +
                            "\n+--------------------------------------------------+ " +
                            "\nAperte qualquer tecla para continuar:");
                    break;


            }
        }while (sistema);

    }

    public static int menu() throws IOException {
        System.out.println("+--------------------------------------------------+");
        System.out.println("                  MENU PRINCIPAL                   |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("| Atalho |              Submenu                    |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("   [1]   |  Cadastrar Cliente                      |");
        System.out.println("   [2]   |  Cadastrar Conta Corrente               |");
        System.out.println("   [3]   |  Cadastar Conta Poupança                |");
        System.out.println("   [4]   |  Efetuar Saque                          |");
        System.out.println("   [5]   |  Efetuar Deposito                       |");
        System.out.println("   [6]   |  Efetuar Transferencia                  |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("   [0]  |  Encerrar Programa                       |");
        System.out.println("+--------------------------------------------------+");
        try {
            return Integer.parseInt(ler.readLine());

        }catch (Exception e){
            System.out.println("> Insira apenas numeros! " +
                    "\n+--------------------------------------------------+" +
                    "\nAperte qualquer tecla para continuar:");
           System.in.read();
            return menu();
        }

    }





}
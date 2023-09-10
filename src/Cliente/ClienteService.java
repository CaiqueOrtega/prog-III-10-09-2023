package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ClienteService {

   private String cadastrarCpf;
   protected HashMap<String, Cliente> hashCliente = new HashMap<String, Cliente>();
   private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String getCadastrarCpf() {
        return cadastrarCpf;
    }

    public void setCadastrarCpf(String cadastrarCpf) {
        this.cadastrarCpf = cadastrarCpf;
    }
    
    public void cadastrarCliente() throws IOException {
        System.out.println("+--------------------------------------------------+");
        System.out.println("                     Cadastro                      |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("> Insira seu nome: ");
        String nome = reader.readLine();
        verificarCliente(true);
        System.out.println("+--------------------------------------------------+");
        System.out.println("Cliente cadastrado com sucesso");
        System.out.println("+--------------------------------------------------+");

        hashCliente.put(getCadastrarCpf(), new Cliente(getCadastrarCpf(), nome));

        System.out.println();

    }

    public Cliente obterPorCpf(String cpf) {
        return hashCliente.get(cpf);

    }

    public Cliente verificarCliente(boolean verifica) throws IOException {
        System.out.println("> Insira o seu CPF: ");
        this.cadastrarCpf = reader.readLine();
        if (hashCliente.containsKey(cadastrarCpf)) {
            if (verifica) {
                System.out.println("+--------------------------------------------------+");
                System.out.println("> Não é possivel cadastrar o mesmo CPF para duas Contas");
                System.out.println("+--------------------------------------------------+");
                return verificarCliente(true);
            }

            return obterPorCpf(cadastrarCpf);

        } else{
            if (!verifica) {
                System.out.println("+--------------------------------------------------+");
                System.out.println("> CPF não foi cadastrado como Cliente");
                System.out.println("+--------------------------------------------------+");
                return verificarCliente(false);
            }

            setCadastrarCpf(cadastrarCpf);
            return null;
        }
    }
}




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Terminal {
    public static String confirmar;
    private static BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));

    public static String verificarSN() throws IOException {
        String desejaContinuar;
        boolean desejaContinuarInvalido;

        do {
            desejaContinuar = ler.readLine();

            desejaContinuarInvalido = !desejaContinuar.equalsIgnoreCase("N") && !desejaContinuar.equalsIgnoreCase("S");

            if (desejaContinuarInvalido) {
                System.out.println("Resposta inválida! Por favor informe uma resposta válida");
            }
        } while (desejaContinuarInvalido);

        return desejaContinuar;
    }
}

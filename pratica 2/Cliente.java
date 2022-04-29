import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Por favor insira a resposta (<numero_questao>;<qtde_resposta>;<respostas>)");
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String resposta = in.nextLine();
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            QuestInterface stub = (QuestInterface) registry.lookup("server");
            String msg = stub.verify(resposta);
            System.out.println("Gabarito: ");
            System.out.println(msg);
            System.out.println("<questao>;<qtde_acertos>;<qtde_erros>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        in.close();
    }
}

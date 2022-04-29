import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cliente {
    public static int maxRandomValue = 5;
    public static void main(String[] args) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            CounterServer server = (CounterServer) registry.lookup("server");

            Scanner in = new Scanner(System.in);
            System.out.print("Insira a quantidade de contadores que deseja instanciar: ");
            int qtde = in.nextInt();
            ArrayList<Counter> counters = new ArrayList<>();
            for (int i = 0; i < qtde; i++)
                counters.add((Counter) server.createCounter(0));

            qtde = -1;
            while (qtde != 0) {
                System.out.print("\033\143");
                int c = 0;
                for (Counter co : counters)
                    System.out.println("Contador " + (c++) + ": " + co.getValue());
                System.out.println("1 - Incrementar aleatoriamente");
                System.out.println("0 - Sair");
                System.out.print("> ");
                qtde = in.nextInt();

                if (qtde == 1)
                    shuffle(counters);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void shuffle(ArrayList<Counter> l) {
        l.forEach((e) -> {
            int random = new Random().nextInt(maxRandomValue);
            for (int i = 0; i < random; i++) {
                try {
                    e.nextValue();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

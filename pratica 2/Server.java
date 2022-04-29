import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            QuestInterface q = new QuestImp();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("server", q);
            System.out.println("Servidor pronto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
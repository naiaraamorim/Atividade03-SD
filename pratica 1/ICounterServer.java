import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ICounterServer extends UnicastRemoteObject implements CounterServer {
    public ICounterServer() throws RemoteException {

    }

    @Override
    public Counter createCounter(int initValue) throws RemoteException {
        Counter c = (Counter) UnicastRemoteObject.exportObject(new ICounter(initValue), 0);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("counter", c);
        return c;
    }

    public static void main(String[] args) {
        try {
            CounterServer server = (CounterServer) new ICounterServer();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("server", server);
            System.out.println("Servidor iniciado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

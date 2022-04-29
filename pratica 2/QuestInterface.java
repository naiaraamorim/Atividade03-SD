import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuestInterface extends Remote {
    public String verify(String r) throws RemoteException;
}
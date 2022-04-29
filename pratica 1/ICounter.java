import java.rmi.RemoteException;

public class ICounter implements Counter {
    private int value = 0;
    
    ICounter(int initValue) throws RemoteException {
        this.value = initValue;
    }

    @Override
    public int getValue() throws RemoteException {
        return value;
    }

    @Override
    public int nextValue() throws RemoteException {
        return ++this.value;
    }
}

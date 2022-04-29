import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class QuestImp extends UnicastRemoteObject implements QuestInterface {
    QuestImp() throws RemoteException {
    }


    @Override
    public String verify(String r) throws RemoteException {
        String[] q = {
            "1;5;FFFFF",
            "2;5;VVFVF",
            "3;5;FFVFV",
            "4;5;FVFFV",
            "5;5;FVVVV"
        };

        try {
            String[] resposta = r.split(";")[2].toLowerCase().split("");
            String[] questionario = q[Integer.parseInt(r.split(";")[0]) - 1].split(";")[2].toLowerCase().split("");

            int hits = 0;
            for (int i = 0; i < questionario.length; i++)
                if (questionario[i].equals(resposta[i]))
                    hits++;

            return r.split(";")[0] + ";" + hits + ";" + (questionario.length - hits);
        } catch (Exception e) {
            return "Resposta inválida!";
        }
    }
}
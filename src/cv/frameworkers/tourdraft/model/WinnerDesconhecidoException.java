package cv.frameworkers.tourdraft.model;

/**
 * Created by AnaxAndrade on 12-07-2016.
 */
public class WinnerDesconhecidoException extends Exception {

    public WinnerDesconhecidoException(){
        super("Impossível Determinar Vencedor");
    }

    public WinnerDesconhecidoException(String msg){
        super(msg);
    }
}

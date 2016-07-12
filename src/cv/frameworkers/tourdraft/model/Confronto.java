package cv.frameworkers.tourdraft.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by AnaxAndrade on 10-07-2016.
 */
public class Confronto {
    private SimpleIntegerProperty id;

    //Competidores
    private ObjectProperty<Competidor> p1;
    private ObjectProperty<Competidor> p2;

    //Pontuação de Cada Competidor neste confronto
    private SimpleIntegerProperty s1;
    private SimpleIntegerProperty s2;

    private SimpleIntegerProperty winnerNumber;
    private SimpleIntegerProperty winnerId;

    public Confronto(SimpleIntegerProperty id, ObjectProperty<Competidor> p1, ObjectProperty<Competidor> p2, SimpleIntegerProperty s1, SimpleIntegerProperty s2, SimpleIntegerProperty winnerNumber, SimpleIntegerProperty winnerId) {
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
        this.s1 = s1;
        this.s2 = s2;
        //1 ou 2
        this.winnerNumber = winnerNumber;
        this.winnerId = winnerId;
    }

    public Confronto(int id) {
        this.id = new SimpleIntegerProperty(id);
        this.p1 = new SimpleObjectProperty<Competidor>();
        this.p2 = new SimpleObjectProperty<Competidor>();
        this.s1 = new SimpleIntegerProperty(0);
        this.s2 = new SimpleIntegerProperty(0);
        this.winnerNumber = new SimpleIntegerProperty(-1);
        this.winnerId = new SimpleIntegerProperty(-1);

        s1.addListener((observable, oldValue, newValue) -> {
            if(this.getS1() > this.getS2()){
                this.setWinnerNumber(1);
            }else if(this.getS2() > getS1()){
                this.setWinnerNumber(2);
            }
        });

        s2.addListener((observable, oldValue, newValue) -> {
            if(this.getS1() > this.getS2()){
                this.setWinnerNumber(1);
            }else if(this.getS2() > getS1()){
                this.setWinnerNumber(2);
            }
        });
    }


    public Confronto() {
        this(-1);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Competidor getP1() {
        return p1.get();
    }

    public ObjectProperty<Competidor> p1Property() {
        return p1;
    }

    public void setP1(Competidor p1) {
        this.p1.set(p1);
    }

    public Competidor getP2() {
        return p2.get();
    }

    public ObjectProperty<Competidor> p2Property() {
        return p2;
    }

    public void setP2(Competidor p2) {
        this.p2.set(p2);
    }

    public int getS1() {
        return s1.get();
    }

    public SimpleIntegerProperty s1Property() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1.set(s1);
    }

    public int getS2() {
        return s2.get();
    }

    public SimpleIntegerProperty s2Property() {
        return s2;
    }

    public void setS2(int s2) {
        this.s2.set(s2);
    }

    public int getWinnerNumber() {
        return winnerNumber.get();
    }

    public SimpleIntegerProperty winnerNumberProperty() {
        return winnerNumber;
    }

    public void setWinnerNumber(int winnerNumber) {
        this.winnerNumber.set(winnerNumber);
    }

    public int getWinnerId()throws WinnerDesconhecidoException {
        return getWinner().getId();
    }

    public SimpleIntegerProperty winnerIdProperty() {
        return winnerId;
    }

    public Competidor getWinner() throws WinnerDesconhecidoException {

        if (this.getWinnerNumber() == 1){
            return this.p1.get();
        }else if(this.getWinnerNumber() == 2){
            return this.p2.get();
        }else {//Caso não esteja definido, verificar pontos
            if(getS1() > getS2()){
                return getP1();
            }else if (getS1() < getS2()){
                return getP2();
            }else{
                //Caso não foi nem definido o número nem têm pontos diferentes
                throw new WinnerDesconhecidoException();

            }
        }

    }
}

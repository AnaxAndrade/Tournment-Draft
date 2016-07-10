package cv.frameworkers.tourdraft.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
        this.winnerNumber = winnerNumber;
        this.winnerId = winnerId;
    }

    public Confronto(int id) {
        this(new SimpleIntegerProperty(id), null, null, null,null, null,null);
    }

    public Confronto() {
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

    public int getWinnerId() {
        return winnerId.get();
    }

    public SimpleIntegerProperty winnerIdProperty() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) throws Exception{
        if (p1.get().getId() == winnerId) {
            this.winnerId.set(winnerId);
            this.setWinnerNumber(1);
        }else if (p2.get().getId() == winnerId){
            this.winnerId.set(winnerId);
            this.setWinnerNumber(2);
        }else {
            throw new Exception("O competidor não está ligado a este confronto");
        }

    }

    public Competidor getWinner(){
        if (this.getWinnerNumber() == 1){
            return this.p1.get();
        }else if(this.getWinnerNumber() == 2){
            return this.p2.get();
        }else {
            return null;
        }

    }
}

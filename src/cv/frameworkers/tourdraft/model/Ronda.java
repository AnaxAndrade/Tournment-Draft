package cv.frameworkers.tourdraft.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AnaxAndrade on 10-07-2016.
 */
public class Ronda {
    private SimpleIntegerProperty id;
    private SimpleStringProperty titulo;
    private SimpleBooleanProperty terminado;
    private ObservableList<Confronto> confrontos = FXCollections.observableArrayList();

    public Ronda(SimpleIntegerProperty id, SimpleStringProperty titulo, SimpleBooleanProperty terminado, ObservableList<Confronto> confrontos) {
        this.id = id;
        this.titulo = titulo;
        this.terminado = terminado;
        this.confrontos = confrontos;
    }

    public Ronda(int id) {
        this(new SimpleIntegerProperty(id), null, null, null);
    }

    public Ronda() {
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

    public String getTitulo() {
        return titulo.get();
    }

    public SimpleStringProperty tituloProperty() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public boolean getTerminado() {
        return terminado.get();
    }

    public SimpleBooleanProperty terminadoProperty() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado.set(terminado);
    }

    public ObservableList<Confronto> getConfrontos() {
        return this.confrontos;
    }

    public void setConfrontos(ObservableList<Confronto> confrontos) {
        this.confrontos = confrontos;
    }

    public void addConfronto(Confronto c){
        this.confrontos.add(c);
    }

    public List<Competidor> getWinners(){
        List<Competidor> a = new ArrayList<>();
        for(Confronto c : this.getConfrontos()){
            a.add(c.getWinner());
        }

        return a;
    }
}

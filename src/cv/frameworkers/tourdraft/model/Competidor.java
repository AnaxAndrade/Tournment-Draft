package cv.frameworkers.tourdraft.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Criado por AnaxAndrade em 10-07-2016.
 */
public class Competidor {

    private final IntegerProperty id;//Id do competidor em determinado torneio
    private final StringProperty nick;
    private final StringProperty nome;


    public Competidor(IntegerProperty id, StringProperty nick, StringProperty nome) {
        this.id = id;
        this.nick = nick;
        this.nome = nome;
    }

    public Competidor(int id, String nick, String nome) {
        this.id = new SimpleIntegerProperty(id);
        this.nick = new SimpleStringProperty(nick);
        this.nome = new SimpleStringProperty(nome);
    }

    public Competidor(){
        this(-1, "", "");
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNick() {
        return nick.get();
    }

    public StringProperty nickProperty() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick.set(nick);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }
}

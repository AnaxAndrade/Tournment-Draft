package cv.frameworkers.tourdraft.model;

import cv.frameworkers.tourdraft.util.SEL;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.List;

/**
 * Criado por AnaxAndrade em 10-07-2016.
 */
public class Torneio {
    private SimpleIntegerProperty id;
    private SimpleStringProperty titulo;
    private SimpleIntegerProperty rondaAtual;
    private SimpleBooleanProperty iniciado;
    private SimpleBooleanProperty terminado;
    private ObservableList<Ronda> rondas;
    private ObservableList<Competidor> competidores = FXCollections.observableArrayList(); //Auxiliar

    public Torneio(SimpleIntegerProperty id, SimpleStringProperty titulo, SimpleIntegerProperty rondaAtual, SimpleBooleanProperty iniciado, SimpleBooleanProperty terminado, ObservableList<Ronda> rondas) {
        this.id = id;
        this.titulo = titulo;
        this.rondaAtual = rondaAtual;
        this.iniciado = iniciado;
        this.terminado = terminado;
        this.rondas = rondas;
    }

    public Torneio() {
        this(new SimpleIntegerProperty(-1), new SimpleStringProperty(""), new SimpleIntegerProperty(-1), new SimpleBooleanProperty(false), new SimpleBooleanProperty(false), FXCollections.observableArrayList());
    }

    public Torneio(int id) {
        this(new SimpleIntegerProperty(id), new SimpleStringProperty(""), new SimpleIntegerProperty(-1), new SimpleBooleanProperty(false), new SimpleBooleanProperty(false), FXCollections.observableArrayList());
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

    public int getRondaAtual() {
        return rondaAtual.get();
    }

    public SimpleIntegerProperty rondaAtualProperty() {
        return rondaAtual;
    }

    public void setRondaAtual(int rondaAtual) {
        this.rondaAtual.set(rondaAtual);
    }

    public boolean isIniciado() {
        return iniciado.get();
    }

    public SimpleBooleanProperty iniciadoProperty() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado.set(iniciado);
    }

    public boolean isTerminado() {
        return terminado.get();
    }

    public SimpleBooleanProperty terminadoProperty() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado.set(terminado);
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void adicionarRonda(Ronda ron){
        if(this.rondas == null){
            this.rondas = FXCollections.observableArrayList();
        }

        this.rondas.add(ron);
    }

    public void setRondas(ObservableList<Ronda> rondas) {
        if (rondas == null)
            this.rondas = FXCollections.observableArrayList();
        else
            this.rondas = rondas;
    }

    public ObservableList<Competidor> getCompetidores() {
        return competidores;
    }

    //Utilitários básicos para a gestão do Torneio
    public boolean addCompetidor(Competidor c){
        //Se o torneio já iniciou ou já terminou, não deve adicionar ninguém
        if (this.isIniciado() || this.isTerminado())
            return false;

        //Redefinir Id de acordo com a posição no array
        int l = this.getCompetidores().size() + 1;
        c.setId(l);
        this.getCompetidores().add(c);

        return true;
    }

    /**
     * Retorna a ronda com o id especificado - começa em 1
     * @param index Obtém a ronda com o id especificado
     * @return A ronda com o id especificado no parametro ou null caso não encontrar
     * */
    private Ronda getRonda(int index){
        for (int i=0; i < this.getRondas().size(); i++){
            if (this.getRondas().get(i).getId() == index){
                return this.getRondas().get(i);
            }
        }

        return null;
    }

    public void randomize(){
        Collections.shuffle(this.competidores);
        for (int i = 0; i< this.competidores.size(); i++){
            this.competidores.get(i).setId(i+1);
        }

    }

    //Método para proceder para a próxima ronda (após todos os Confrontos da Ronda Atual)
    public boolean passarProximaRonda(){
        //Se já terminou
        if (this.getRondaAtual() > this.getRondas().size() || this.isTerminado()){
            return false;
        }

        //TODO Verificar se  todos os jogos da ronda atual foram concluídos

        //Obtém vencedores da Ronda Atual
        //TODO Certificar que retorno de getWinners() é uma cópia e não referências
        //Embora estou a remover de wins, NÃO devia remover também de SEUS LUGARES
        List<Competidor> wins = getRonda(this.getRondaAtual()).getWinners();
        int seguinte = this.getRondaAtual()+1;

        //Se esta é a ronda Final
        if (seguinte > this.getRondaAtual()){
            //O torneio terminou!
            return false;
        }

        //Definir confrontos da próxima Ronda
        for (int k=0; k < getRonda(seguinte).getConfrontos().size(); k++){
            if (getRonda(seguinte).getConfrontos().get(k).getP1() == null
                    && wins.size() > 0){
                getRonda(seguinte).getConfrontos().get(k).setP1(wins.remove(0));
            }

            if (getRonda(seguinte).getConfrontos().get(k).getP2() == null
                    && wins.size() > 0){
                getRonda(seguinte).getConfrontos().get(k).setP2(wins.remove(0));
            }
        }

        //Definir a próxima ronda como atual
        this.setRondaAtual(seguinte);

        return true;

    }

    /**
     * @param randomizar Caso seja necessário embaralhar primeiro
     * */
    public void gerarBaseBrackets(boolean randomizar){
        if (randomizar)
            this.randomize();

        //Esvaziar lista de rondas
        this.setRondas(null);

        int jogo = 0;
        //TODO CERTIFICAR QUE COMPETIDORES != Null (e não vazio)
        int qtd = this.getCompetidores().size();

        //Loop para Criar as rondas
        for (int i=0; i < SEL.numRondas(qtd); i++){
            int r = i+1;
            Ronda rounda = new Ronda(r);

            //Loop para Atribuir confrontos a jogos em uma ronda
            for (int j = 0; j < SEL.numJogosRonda(r, qtd); j++){
                jogo += 1;
                int r_atual = j + 1;

                Confronto c = new Confronto(jogo);
                //Enquanto estiver competidores, adicioná-los a confrontos(e removendo da lista)
                //Player 1
                if (this.getCompetidores().size() > 0){
                    c.setP1(this.getCompetidores().remove(0));
                }

                //Player 2
                //TODO Coment a eXPLICAR CASO DE BYES - depende do numero de jogos da primeira ronda
                if (this.getCompetidores().size() > 0 && (r==0 || r_atual > SEL.numJogosRonda(r-1, qtd))){
                    c.setP2(this.getCompetidores().remove(0));
                }

                rounda.addConfronto(c);
            }

            this.adicionarRonda(rounda);
        }

        this.setRondaAtual(1);
        this.setIniciado(true);
    }
}

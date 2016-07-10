package cv.frameworkers.tourdraft.util;

/**
 * Created by AnaxAndrade on 10-07-2016.
 */
public class SEL {
    /**
     * Calcula o numero Total de Jogos que vai ser Jogado
     * @param N O número de Participantes
     * @return Retorna o numero total de Jogos
     * */
    public static int numJogosTotal(int N){
        return N-1;
    }

    /**
     * Calcula o numero de participantes que vão direto para a próxima ronda
     * @param N O número de Participantes
     * @return Retorna de Byes
     * */
    public static int numByes(int N){
        int hp = 1;
        while (hp < N)
            hp *= 2;

        return hp - N;
    }

    /**
     * Calcula o numero Total de Rondas que vão existir
     * @param N O número de Participantes
     * @return Retorna o numero total de Rondas
     * */
    public static int numRondas(int N){
        int hp = 1, count = 0;
        while (hp < N){
            hp *= 2;
            count += 1;
        }

        return count;
    }

    /**
     * Calcula o numero Total de Jogos que vão ser Jogados na primeira ronda
     * @param N O número de Participantes
     * @return Retorna o numero total de Jogos na Primera Ronda
     * */
    public static int numJogosPrimeiraRonda(int N){
        int lp = 1;
        while (lp < N)
            lp *= 2;

        return N - (lp/2);
    }

    /**
     * Calcula o numero Total de Jogos que vão ser jogados em uma dada ronda
     * @param r A ronda desejada (começa em 1)
     * @param N O número de Participantes
     * @return Retorna o numero total de Jogos na ronda indicada
     * */
    public static int numJogosRonda(int r, int N){
        if (r < 1){
            return 0;
        }else if (r == 1){
            return numJogosPrimeiraRonda(N);
        }else {
            int soma = numJogosPrimeiraRonda(N) + numByes(N);
            return soma / ((int)Math.pow(2, (r-1)));
        }
    }
}

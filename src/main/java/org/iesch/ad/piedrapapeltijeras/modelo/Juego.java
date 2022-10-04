package org.iesch.ad.piedrapapeltijeras.modelo;


import org.iesch.ad.piedrapapeltijeras.App;

import java.util.Random;
import java.util.Scanner;

public class Juego {

    public static final int PIEDRA = 1;
    public static final int PAPEL = 2;
    public static final int TIJERA = 3;

    public static final int EMPATE = 0;
    public static final int VICTORIA = 1;
    public static final int DERROTA = -1;

    private int dificultad;

    public Juego(int dificultad) {
        this.dificultad = dificultad;
    }

    public void jugar() {
        try {
            mostrarGanador(validarJugada(elegirJugadaJugador(), elegirJugadaMaquina()));
        } catch (EleccionInvalidaException e) {
            System.out.println(App.recursos.getString("invalidChoice"));
            jugar();
        }
    }

    public void mostrarGanador(int resultado) {
        switch (resultado) {
            case VICTORIA:
                System.out.println(App.recursos.getString("playerWin"));
                break;

            case EMPATE:
                System.out.println(App.recursos.getString("tie"));
                break;

            case DERROTA:
                System.out.println(App.recursos.getString("computerWin"));
                break;
        }
    }

    public int validarJugada(int jugadaJugador, int jugadaMaquina) {
        if(gana(jugadaJugador, jugadaMaquina)) return 1;
        if(gana(jugadaMaquina, jugadaJugador)) return -1;
        else return 0;
    }

    private boolean gana(int jugada1, int jugada2) {
        return (jugada1 == PAPEL && jugada2 == PIEDRA ||
                jugada1 == PIEDRA && jugada2 == TIJERA ||
                jugada1 == TIJERA && jugada2 == PAPEL);
    }

    public int elegirJugadaJugador() throws EleccionInvalidaException {
        Scanner in = new Scanner(System.in);
        System.out.println(App.recursos.getString("rockPaperScissors"));
            switch (in.nextInt()) {
                case PIEDRA:
                    return PIEDRA;
                case PAPEL:
                    return PAPEL;
                case TIJERA:
                    return TIJERA;
                default:
                    throw new EleccionInvalidaException(App.recursos.getString("invalidChoice"));
            }
    }

    public int elegirJugadaMaquina() {
        Random random = new Random();
        int jugada = -1;
        jugada = random.nextInt(3);

        switch (jugada) {
            case PIEDRA:
                System.out.println(App.recursos.getString("machineSelectRock"));
                break;

            case PAPEL:
                System.out.println(App.recursos.getString("machineSelectPaper"));
                break;

            case TIJERA:
                System.out.println(App.recursos.getString("machineSelectScissors"));
                break;
        }
        return jugada;
    }
}

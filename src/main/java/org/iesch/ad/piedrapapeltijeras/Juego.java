package org.iesch.ad.piedrapapeltijeras;


import java.util.Random;
import java.util.Scanner;

public class Juego {

    public static final int PIEDRA = 0;
    public static final int PAPEL = 1;
    public static final int TIJERA = 2;

    public static final int EMPATE = 0;
    public static final int VICTORIA = 1;
    public static final int DERROTA = -1;

    private Scanner in;
    private Random random;
    private int dificultad;


    public Juego(int dificultad) {
        in = new Scanner(System.in);
        random = new Random(3);
        this.dificultad = dificultad;
    }

    public void jugar() throws EleccionInvalidaException {
        mostrarGanador(validarJugada(elegirJugadaMaquina(), elegirJugadaJugador()));
    }

    public void mostrarGanador(int resultado) {
        switch (resultado) {
            case VICTORIA:
                System.out.println("Gana el jugador.");
                break;

            case EMPATE:
                System.out.println("Empate.");
                break;

            case DERROTA:
                System.out.println("Gana la maquina.");
                break;
        }
    }

    public int validarJugada(int jugadaMaquina, int jugadaJugador) {
        return Integer.compare(jugadaJugador, jugadaMaquina);
    }

    public int elegirJugadaJugador() throws EleccionInvalidaException {
        System.out.println("Elige piedra, papel o tijera");
        switch (in.next().toLowerCase()) {
            case "piedra":
                return PIEDRA;
            case "papel":
                return PAPEL;
            case "tijera":
                return TIJERA;
            default:
                throw new EleccionInvalidaException("La opcion elegida no es invalida.");
        }
    }

    public int elegirJugadaMaquina() {
        int jugada = -1;
        jugada = random.nextInt(3);

        switch (jugada) {
            case PIEDRA:
                System.out.println("La maquina ha elegido piedra.");
                break;

            case PAPEL:
                System.out.println("La maquina ha elegido papel.");
                break;

            case TIJERA:
                System.out.println("La maquina ha elegido tijera.");

                break;
        }
        return jugada;
    }
}

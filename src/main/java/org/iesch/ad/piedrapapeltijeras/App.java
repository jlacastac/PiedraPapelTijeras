package org.iesch.ad.piedrapapeltijeras;

public class App {

    public static void main(String[] args) {

        Juego juego = new Juego(0);

        while(true) {
            try {

                juego.jugar();

            } catch (EleccionInvalidaException e) {
                System.out.println("Eleccion invalida.");
                return;
            }
        }

    }
}
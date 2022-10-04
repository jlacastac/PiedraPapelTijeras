package org.iesch.ad.piedrapapeltijeras;

import org.iesch.ad.piedrapapeltijeras.modelo.EleccionInvalidaException;
import org.iesch.ad.piedrapapeltijeras.modelo.Juego;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class App {
    static final String CONFIG_FILE = "config.properties";

    static final String SI = "y";

    static final int NORMAL = 0;
    static final int DIFICIL = 1;

    static Properties propiedades;
    static Locale localizacion;
    public static ResourceBundle recursos;

    static Juego juego;
    static int jugadas;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        cargarConfiguracion();
        nuevoJuego();
        jugar();
    }

    private static void nuevoJuego(){
        Scanner in = new Scanner(System.in);
        jugadas = 3;

        System.out.println(recursos.getString("selectDifficulty"));
        try {
            switch (in.nextInt()) {
                case NORMAL:
                    juego = new Juego(NORMAL);
                    break;
                case DIFICIL:
                    juego = new Juego(DIFICIL);
                    break;
                default:
                    throw new EleccionInvalidaException(recursos.getString("invalidChoice"));
            }
        } catch (Exception e) {
            nuevoJuego();
        }

        System.out.println(recursos.getString("selectNumberOfPlays"));
        jugadas = in.nextInt();
    }

    private static void jugar() {
        Scanner in = new Scanner(System.in);

        while (jugadas > 0) {
            juego.jugar();

            System.out.println(recursos.getString("finishMatchQuestion"));
            if(in.next().toLowerCase().equals(SI)) {
                return;
            }
            jugadas--;
        }
    }

    private static void cargarConfiguracion() {
        try (InputStream input = App.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            propiedades = new Properties();

            if (input == null) {
                System.out.println(recursos.getString("loadConfigFail"));
                return;
            }
            propiedades.load(input);

            String localeString = propiedades.getProperty("locale");
            localizacion = new Locale(localeString.substring(0, localeString.indexOf("_")),
                                      localeString.substring(localeString.indexOf("_"), localeString.length()));
            recursos = ResourceBundle.getBundle("bundle", localizacion);

        } catch (IOException e) {
            System.out.println(recursos.getString("configFileNotFound"));
        }
    }
}
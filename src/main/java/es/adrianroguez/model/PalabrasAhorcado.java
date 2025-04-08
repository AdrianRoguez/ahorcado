package es.adrianroguez.model;

import java.util.Random;

public enum PalabrasAhorcado {
    AHORCADO,
    JAVA,
    ORDENADOR,
    PROGRAMACION,
    DESARROLLO,
    CONTROLADOR,
    INTERFAZ,
    ESCENA;

    private static final Random RANDOM = new Random();

    public static String obtenerPalabraAleatoria() {
        PalabrasAhorcado[] palabras = values();
        return palabras[RANDOM.nextInt(palabras.length)].name();
    }
}
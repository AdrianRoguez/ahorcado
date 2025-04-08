package es.adrianroguez.utils;

public class NavigationManager {
    private static String rutaAnterior;

    public static void setRutaAnterior(String ruta) {
        rutaAnterior = ruta;
    }

    public static String getRutaAnterior() {
        return rutaAnterior;
    }
}

package es.adrianroguez.controller;

import java.util.HashSet;
import java.util.Set;

import es.adrianroguez.controller.abstracts.AbstractsController;
import es.adrianroguez.model.PalabrasAhorcado;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController extends AbstractsController {
    private String palabraSecreta;
    private Set<Character> letrasCorrectas = new HashSet<>();
    private Set<Character> letrasIncorrectas = new HashSet<>();
    private static final int INTENTOS_MAXIMOS = 6;

    private String usuarioActual;
    private String emailActual;

    @FXML
    Label userInfo;

    @FXML
    Label emailInfo;

    @FXML
    private Text wordDisplay;

    @FXML
    private Text incorrectLettersText;

    @FXML
    private Text messageText;

    @FXML
    private TextField letterInput;

    @FXML
    private Button rebootButton;

    @FXML
    Button goBackButton;

    @FXML
    public void initialize() {
        iniciarNuevoJuego();
    }

    private void iniciarNuevoJuego() {
        palabraSecreta = PalabrasAhorcado.obtenerPalabraAleatoria();
        letrasCorrectas.clear();
        letrasIncorrectas.clear();
        letterInput.setDisable(false);
        messageText.setText("");
        actualizarPantalla();
    }

    @FXML
    public void guessLetter() {
        String entrada = letterInput.getText().toUpperCase();
        letterInput.clear();

        if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
            messageText.setText("introduce una letra valida");
            return;
        }

        char letra = entrada.charAt(0);

        if (letrasCorrectas.contains(letra) || letrasIncorrectas.contains(letra)) {
            messageText.setText("ya probaste esa letra");
            return;
        }

        if (palabraSecreta.indexOf(letra) >= 0) {
            letrasCorrectas.add(letra);
        } else {
            letrasIncorrectas.add(letra);
        }

        actualizarPantalla();
        comprobarFinDelJuego();
    }

    private void actualizarPantalla() {
        StringBuilder display = new StringBuilder();
        for (char c : palabraSecreta.toCharArray()) {
            if (letrasCorrectas.contains(c)) {
                display.append(c).append(" ");
            } else {
                display.append("_ ");
            }
        }

        wordDisplay.setText(display.toString().trim());
        incorrectLettersText.setText("errores: " + letrasIncorrectas.size() + "/" + INTENTOS_MAXIMOS);
    }

    private void comprobarFinDelJuego() {
        if (letrasIncorrectas.size() >= INTENTOS_MAXIMOS) {
            messageText.setText("derrota la palabra era: " + palabraSecreta);
            letterInput.setDisable(true);
        } else if (palabraAdivinada()) {
            messageText.setText("victoria has adivinado la palabra");
            letterInput.setDisable(true);
        }
    }

    private boolean palabraAdivinada() {
        for (char c : palabraSecreta.toCharArray()) {
            if (!letrasCorrectas.contains(c))
                return false;
        }
        return true;
    }

    @FXML
    public void restartGame() {
        iniciarNuevoJuego();
    }

    @FXML
    public void goBack() {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        volverAtras(stage);
    }
}

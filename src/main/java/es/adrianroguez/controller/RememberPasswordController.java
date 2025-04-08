package es.adrianroguez.controller;

import es.adrianroguez.controller.abstracts.AbstractsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RememberPasswordController extends AbstractsController {
    @FXML
    TextField emailField;

    @FXML
    Button sendButton;

    @FXML
    Button goBackButton;

    @FXML
    public void initialize() {
        cambiarIdioma();
        cambiarIdiomaRememberPassword();
    }

    @FXML
    public void goBack() {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        volverAtras(stage);
    }
}

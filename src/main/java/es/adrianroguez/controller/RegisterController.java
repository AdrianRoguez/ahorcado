package es.adrianroguez.controller;

import es.adrianroguez.controller.abstracts.AbstractsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController extends AbstractsController {
    @FXML
    TextField userField;

    @FXML
    TextField emailField;

    @FXML
    TextField repeatEmailField;

    @FXML
    TextField passwordField;

    @FXML
    TextField repeatPasswordField;

    @FXML
    Button saveButton;

    @FXML
    Button goBackButton;

    @FXML
    public void initialize() {
        cambiarIdioma();
        cambiarIdiomaRegister();
    }

    @FXML
    public void goBack() {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        volverAtras(stage);
    }
}

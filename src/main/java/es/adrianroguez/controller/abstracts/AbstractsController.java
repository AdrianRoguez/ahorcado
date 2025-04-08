package es.adrianroguez.controller.abstracts;

import java.io.IOException;

import es.adrianroguez.config.ConfigManager;
import es.adrianroguez.utils.NavigationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class AbstractsController {
    // login
    @FXML
    Text userText;

    @FXML
    Text passwordText;

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;

    @FXML
    Button usersListButton;

    @FXML
    Button rememberPasswordButton;

    // register
    @FXML
    Text registerTitle;

    @FXML
    Text emailText;

    @FXML
    Text repeatEmailText;

    @FXML
    Text repeatPasswordText;

    @FXML
    Button saveButton;

    @FXML
    Button goBackButton;

    // remember password
    @FXML
    Text rememberPasswordTitle;

    @FXML
    Button sendButton;

    // user
    @FXML
    Text userTitle;

    @FXML
    Text levelText;

    @FXML
    Button editButton;

    @FXML
    Button playButton;

    public void cambiarIdioma() {
        if (!(userText == null)) {
            userText.setText(ConfigManager.ConfigProperties.getProperty("userText"));
        }
        if (!(passwordText == null)) {
            passwordText.setText(ConfigManager.ConfigProperties.getProperty("passwordText"));
        }
        if (!(loginButton == null)) {
            loginButton.setText(ConfigManager.ConfigProperties.getProperty("loginButton"));
        }
        if (!(registerButton == null)) {
            registerButton.setText(ConfigManager.ConfigProperties.getProperty("registerButton"));
        }
        if (!(usersListButton == null)) {
            usersListButton.setText(ConfigManager.ConfigProperties.getProperty("usersListButton"));
        }
        if (!(rememberPasswordButton == null)) {
            rememberPasswordButton.setText(ConfigManager.ConfigProperties.getProperty("rememberPasswordButton"));
        }
        if (!(emailText == null)) {
            emailText.setText(ConfigManager.ConfigProperties.getProperty("emailText"));
        }
        if (!(goBackButton == null)) {
            goBackButton.setText(ConfigManager.ConfigProperties.getProperty("goBackButton"));
        }
    }

    public void cambiarIdiomaRegister() {
        registerTitle.setText(ConfigManager.ConfigProperties.getProperty("registerTitle"));
        repeatEmailText.setText(ConfigManager.ConfigProperties.getProperty("repeatEmailText"));
        repeatPasswordText.setText(ConfigManager.ConfigProperties.getProperty("repeatPasswordText"));
        saveButton.setText(ConfigManager.ConfigProperties.getProperty("saveButton"));
    }

    public void cambiarIdiomaRememberPassword() {
        rememberPasswordTitle.setText(ConfigManager.ConfigProperties.getProperty("rememberPasswordTitle"));
        sendButton.setText(ConfigManager.ConfigProperties.getProperty("sendButton"));
    }

    public void cambiarIdiomaUser() {
        userTitle.setText(ConfigManager.ConfigProperties.getProperty("UserTitle"));
        levelText.setText(ConfigManager.ConfigProperties.getProperty("levelText"));
        editButton.setText(ConfigManager.ConfigProperties.getProperty("editButton"));
        playButton.setText(ConfigManager.ConfigProperties.getProperty("playButton"));
    }

    protected void cambiarPantalla(String nuevaRutaFXML, Stage stage, String rutaActual) {
        try {
            if (rutaActual != null) {
                NavigationManager.setRutaAnterior(rutaActual);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(nuevaRutaFXML));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void volverAtras(Stage stage) {
        String ruta = NavigationManager.getRutaAnterior();
        if (ruta != null) {
            cambiarPantalla(ruta, stage, null);
        } else {
            System.err.println("No hay pantalla anterior.");
        }
    }
}

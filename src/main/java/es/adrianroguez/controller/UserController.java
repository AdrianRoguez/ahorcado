package es.adrianroguez.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.adrianroguez.controller.abstracts.AbstractsController;
import es.adrianroguez.database.ConnectionDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserController extends AbstractsController {
    private String usuarioActual;
    private String emailActual;

    @FXML
    Label userInfo;

    @FXML
    Label emailInfo;

    @FXML
    Label levelInfo;

    @FXML
    Button editButton;

    @FXML
    Button playButton;

    @FXML
    Button goBackButton;

    @FXML
    public void initialize() {
        cambiarIdioma();
        cambiarIdiomaUser();
    }

    // metodo para abrir la pantalla de editar usuario
    public void openEdit() {
        Stage stage = (Stage) editButton.getScene().getWindow();
        cambiarPantalla("/view/register.fxml", stage, "/view/user.fxml");
    }

    // metodo para abrir la pantalla del juego
    public void openGame() {
        Stage stage = (Stage) playButton.getScene().getWindow();
        cambiarPantalla("/view/game.fxml", stage, "/view/user.fxml");
    }

    @FXML
    public void goBack() {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        volverAtras(stage);
    }

    // metodo para inicializar la pantalla y cargar los datos del usuario
    public void inicializarUsuario(String usuario) {
        this.usuarioActual = usuario;
        System.out.println("usuario actual en usercontroller: " + usuarioActual);
        cargarDatosUsuario();
    }

    // carga la informacion del usuario desde la base de datos y la muestra en la
    // interfaz
    private void cargarDatosUsuario() {
        if (usuarioActual == null || usuarioActual.isEmpty()) {
            System.out.println("error usuario no proporcionado");
            userInfo.setText("usuario no encontrado");
            emailInfo.setText("email no disponible");
            levelInfo.setText("nivel no disponible");
            return;
        }

        String sql = "SELECT usuario, email, nivel FROM usuarios WHERE usuario = ?";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuarioActual);
            System.out.println("ejecutando consulta sql: " + sql + " con usuario: " + usuarioActual);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioActual = rs.getString("usuario");
                    emailActual = rs.getString("email");

                    userInfo.setText(usuarioActual);
                    emailInfo.setText(emailActual);
                    levelInfo.setText(rs.getString("nivel"));

                    System.out.println("datos encontrados: " + usuarioActual + ", " + emailActual
                            + ", " + rs.getString("nivel"));
                } else {
                    System.out.println("no se encontro el usuario en la base de datos");
                    userInfo.setText("usuario no encontrado");
                    emailInfo.setText("email no disponible");
                    levelInfo.setText("nivel no disponible");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            userInfo.setText("error al cargar datos");
            emailInfo.setText("error al cargar datos");
            levelInfo.setText("error al cargar datos");
        } finally {
            ConnectionDB.closeConnection();
        }
    }
}
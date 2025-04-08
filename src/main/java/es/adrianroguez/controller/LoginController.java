package es.adrianroguez.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import es.adrianroguez.config.ConfigManager;
import es.adrianroguez.controller.abstracts.AbstractsController;
import es.adrianroguez.database.ConnectionDB;
import es.adrianroguez.utils.NavigationManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController extends AbstractsController {
    private final String pathIdioma = "src/main/resources/";
    private final String idiomaString = "idioma-";
    private final Map<String, String> idiomaCodigoMap = new HashMap<>();
    private static String usuarioActual;

    @FXML
    ComboBox<String> languagesComboBox;

    @FXML
    TextField userField;

    @FXML
    TextField passwordField;

    @FXML
    Text messageText;

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;

    @FXML
    Button usersListButton;

    @FXML
    Button rememberPasswordButton;

    public void openUser() {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user.fxml"));
        Scene scene = new Scene(loader.load());

        UserController controller = loader.getController();
        controller.inicializarUsuario(getUsuarioActual());

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        NavigationManager.setRutaAnterior("/view/login.fxml");

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void openRegister() {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        cambiarPantalla("/view/register.fxml", stage, "/view/login.fxml");
    }

    public void openUsersList() {
        Stage stage = (Stage) usersListButton.getScene().getWindow();
        cambiarPantalla("/view/users-list.fxml", stage, "/view/login.fxml");
    }

    @FXML
    public void openRememberPassword() {
        Stage stage = (Stage) rememberPasswordButton.getScene().getWindow();
        cambiarPantalla("/view/remember-password.fxml", stage, "/view/login.fxml");
    }

    @FXML
    public void initialize() {
        idiomaCodigoMap.put("Español", "es");
        idiomaCodigoMap.put("English", "en");
        idiomaCodigoMap.put("Français", "fr");
        languagesComboBox.getItems().addAll(idiomaCodigoMap.keySet());
        cargarIdioma("es");
        cambiarIdioma();
    }

    @FXML
    protected void seleccionarIdioma() {
        String idiomaSeleccionado = languagesComboBox.getValue();

        if (idiomaSeleccionado != null) {
            String codigoIdioma = idiomaCodigoMap.get(idiomaSeleccionado);
            cargarIdioma(codigoIdioma);
            cambiarIdioma();
        }
    }

    private void cargarIdioma(String idioma) {
        String pathCargarIdioma = pathIdioma + idiomaString + idioma + ".properties";
        ConfigManager.ConfigProperties.setPath(pathCargarIdioma);
    }

    /**
     * metodo para iniciar sesion
     */
    public void iniciarSesion() {
        String usuario = userField.getText().trim();
        String contrasenia = passwordField.getText().trim();

        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            messageText.setText("Por favor, complete todos los campos.");
            return;
        }

        if (validarUsuario(usuario, contrasenia)) {
            setUsuarioActual(usuario);
            System.out.println("Usuario autenticado: " + usuarioActual);
            openUser();
        } else {
            messageText.setText("Usuario o contraseña incorrectos.");
        }
    }

    /**
     * metodo para validar las credenciales del usuario en la base de datos
     *
     * @param usuario     nombre de usuario
     * @param contrasenia contrasenia del usuario
     * @return true si las credenciales son correctas, false en caso contrario
     */
    private boolean validarUsuario(String usuario, String contrasenia) {
        String sql = "SELECT usuario FROM usuarios WHERE LOWER(usuario) = LOWER(?) AND contrasenia = ?";

        try (Connection conn = ConnectionDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);

            System.out.println("Ejecutando consulta: " + sql + " con usuario: " + usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageText.setText("Error al conectar con la base de datos.");
            return false;
        }
    }

    /**
     * metodo para almacenar el usuario autenticado
     *
     * @param usuario nombre de usuario autenticado
     */
    public static void setUsuarioActual(String usuario) {
        usuarioActual = usuario;
    }

    /**
     * metodo para obtener el usuario autenticado
     *
     * @return nombre del usuario autenticado
     */
    public static String getUsuarioActual() {
        return usuarioActual;
    }
}

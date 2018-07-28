package com.braulionova.taskapp.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.AppConfig;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.UsuarioRepositorio;
import com.braulionova.taskapp.repositorio.UsuarioRepositorioDbImpl;

import se.simbio.encryption.Encryption;

public class LoginActivity extends Activity {
    //variables
    private static final String LOG_TAG = "LoginActivity";
    private UsuarioRepositorio usuarioRepositorio;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //botones
        Button btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        Button btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        //TextEdit
        //text
        final EditText txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        final EditText txtContrasena = (EditText) findViewById(R.id.txtPassword);

        //on click btnIniciarSesion
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validar si el email esta en blanco y si el valido
                //validar campos
                try {
                    String email = txtUsuario.getText().toString();
                    if (TextUtils.isEmpty(email)) {
                        txtUsuario.setError("El Email es requerido para iniciar sesión.");
                        txtUsuario.requestFocus();
                        return;
                    }
                    if (isValidEmail(email) == false) {
                        txtUsuario.setError("El formato del email no es correcto, ejemplo: info@gmail.com, favor de verificar.");
                        txtUsuario.requestFocus();
                        return;
                    }
                    //contrasena
                    String password = txtContrasena.getText().toString();
                    if (TextUtils.isEmpty(password)) {
                        txtContrasena.setError("Contraseña es requerida.");
                        txtContrasena.requestFocus();
                        return;
                    }
                    //usuario repositorio
                    usuarioRepositorio = new UsuarioRepositorioDbImpl(getApplicationContext());
                    //validar usuario
                    Encryption encryption = Encryption.getDefault("NovaLab", "braulion", new byte[16]);
                    String password_encrypted = encryption.encryptOrNull(txtContrasena.getText().toString());
                    Usuario usuario = usuarioRepositorio.validarUsuario(email, password_encrypted);
                    //tipo usuario
                    String tipoUsuario = usuario.getTipoUsuario().name();
                    //mensajes dependiendo el retorno
                    if (usuario == null) {
                        txtUsuario.setError("Email o Contraseña incorrectos, revise y vuelva a intentar.");
                        txtUsuario.requestFocus();
                        return;
                    }
                    else if(tipoUsuario == "TECNICO") {
                        //mensaje
                        Toast toast = Toast.makeText(getApplicationContext(), "Bienvenido, " + usuario.getNombre(), Toast.LENGTH_LONG);
                        toast.show();
                        //guardamos el usuario en la clase AppConfig
                        AppConfig.getConfig().setUsuario(usuario);
                        //ir al menu principal
                        Intent menuIntent = new Intent(LoginActivity.this, MenuTecnicoActivity.class);
                        startActivity(menuIntent);
                    }
                    else {
                        //mensaje
                        Toast toast = Toast.makeText(getApplicationContext(), "Bienvenido, " + usuario.getNombre(), Toast.LENGTH_LONG);
                        toast.show();
                        //guardamos el usuario en la clase AppConfig
                        AppConfig.getConfig().setUsuario(usuario);
                        //ir al menu principal
                        Intent menuIntent = new Intent(LoginActivity.this, MenuUsuarioActivity.class);
                        startActivity(menuIntent);
                    }
                }
                catch (Exception ex)
                {
                    //mensaje
                    Toast toast = Toast.makeText(getApplicationContext(), "Error: " + ex.toString(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        //on click
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent
                Intent registrarseIntent = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);
                startActivity(registrarseIntent);

            }
        });
    }
    //isEmailValid
    public final static boolean isValidEmail(CharSequence email) {
        if (email == null) {
            return false;
        }
        else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}

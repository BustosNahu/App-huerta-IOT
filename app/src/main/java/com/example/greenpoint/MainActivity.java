package com.example.greenpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.greenpoint.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText login_email_adress_et, login_password_et;
    Button bt_registrate, bt_olvi_password, bt_iniciar;
    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos cada boton con "find my view"
        bt_registrate = findViewById(R.id.bt_registrate);
        bt_iniciar = findViewById(R.id.bt_iniciar);
        bt_olvi_password = findViewById(R.id.bt_olvi_password);
        login_email_adress_et = findViewById(R.id.login_email_adress_et);
        login_password_et = findViewById(R.id.login_password_et);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            irahome();
        }

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.login_email_adress_et, Patterns.EMAIL_ADDRESS, R.string.invalid_mail);
        awesomeValidation.addValidation(this, R.id.login_password_et, ".{6,}", R.string.invalid_password);
        //PODEMOS CAMBIAR CANTIDAD DE CARACTERES DE  LA PASSWORD CAMBIANDO EL NRO 6 DE ARRIBA.


//        bt_olvi_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, .class);
//                startActivity(i);
//            }
//        });

        //PRUEBA DE ACTIVITY SPLASHSCREEN
//        bt_registrate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent x = new Intent(MainActivity.this, Splash_screen_1.class);
//                startActivity(x);
//            }
//        });

        bt_registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Actv_registrar.class);
                startActivity(i);
            }
        });

        bt_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    String mail = login_email_adress_et.getText().toString();
                    String pass = login_password_et.getText().toString();

                    firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                irahome(); //PARA IR AL MENU PRINCIPAL.
                            } else {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                dameToastdeerror(errorCode);

                            }
                        }

                    });
                }

            }
        });


    }

    private void irahome() {
        Intent i = new Intent(this, Act_menuprincipal.class);
        //i.putExtra("mail", login_email_adress_et.getText().toString());
        //BANDERAS PARA QUE NO SE CREEN ACTIVIDADES INNECESARIAS
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(MainActivity.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(MainActivity.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(MainActivity.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(MainActivity.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                login_email_adress_et.setError("La dirección de correo electrónico está mal formateada.");
                login_email_adress_et.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(MainActivity.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                login_password_et.setError("la contraseña es incorrecta ");
                login_password_et.requestFocus();
                login_password_et.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(MainActivity.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

        }


    }
}




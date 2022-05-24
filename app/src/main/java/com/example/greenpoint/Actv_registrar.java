package com.example.greenpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Actv_registrar extends AppCompatActivity {
    EditText registrar_email_adress_et,registrar_password_et;
    Button bt_create_account;
    AwesomeValidation awesomeValidation;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actv_registrar);

        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.registrar_email_adress_et, Patterns.EMAIL_ADDRESS,R.string.invalid_mail);
        awesomeValidation.addValidation(this,R.id.registrar_password_et,".{6,}",R.string.invalid_password);


        registrar_email_adress_et = findViewById(R.id.registrar_email_adress_et);
        registrar_password_et = findViewById(R.id.registrar_password_et);
        bt_create_account = findViewById(R.id.bt_create_account);

        bt_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = registrar_email_adress_et.getText().toString();
                String pass = registrar_password_et.getText().toString();

                if(awesomeValidation.validate()){
                    firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Actv_registrar.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Actv_registrar.this, Act_menuprincipal.class);
                                startActivity(i);
                                finish();
                            }else {
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                dameToastdeerror(errorCode);
                            }
                        }
                    });
                }else {
                    Toast.makeText(Actv_registrar.this, "Completa todos los datos..!!", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }//fin del oncreate!

    private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(Actv_registrar.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(Actv_registrar.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(Actv_registrar.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(Actv_registrar.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                registrar_email_adress_et.setError("La dirección de correo electrónico está mal formateada.");
                registrar_email_adress_et.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(Actv_registrar.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                registrar_password_et.setError("la contraseña es incorrecta ");
                registrar_password_et.requestFocus();
                registrar_password_et.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(Actv_registrar.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(Actv_registrar.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(Actv_registrar.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(Actv_registrar.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                registrar_email_adress_et.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                registrar_email_adress_et.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(Actv_registrar.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(Actv_registrar.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(Actv_registrar.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(Actv_registrar.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(Actv_registrar.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(Actv_registrar.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(Actv_registrar.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                registrar_password_et.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                registrar_password_et.requestFocus();
                break;

        }

    }

}
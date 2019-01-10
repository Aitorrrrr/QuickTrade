package com.example.a2dam.quicktrade;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class SignUp extends AppCompatActivity {

    private Button enviar;
    private Button cancelar;

    private EditText user;
    private EditText nom;
    private EditText apell;
    private EditText email;
    private EditText tel;
    private EditText pw;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user = (EditText) this.findViewById(R.id.input_usuario);
        nom = (EditText) this.findViewById(R.id.input_nombre);
        apell = (EditText) this.findViewById(R.id.input_apellidos);
        email = (EditText) this.findViewById(R.id.input_email);
        tel = (EditText) this.findViewById(R.id.input_telefono);
        pw = (EditText) this.findViewById(R.id.input_password);

        enviar = (Button) this.findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mail = email.getText().toString();
                String password = pw.getText().toString();

                registrar(mail, password);

                /* Usuario aux = new Usuario(Integer.parseInt(user.getText().toString()), nom.getText().toString(), apell.getText().toString(), email.getText().toString(), Integer.parseInt(tel.getText().toString()), pw.getText().toString(), true);

                Intent i = new Intent();
                i.putExtra("user", aux);

                setResult(RESULT_OK, i);
                finish(); */
            }
        });

        cancelar = (Button) this.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    public void registrar(String email, String password)
    {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUp.this, "Authentication successfull."+user.getUid(),
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            Log.d("MIO",e.getMessage());
                            Log.d("MIO",e.getErrorCode());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

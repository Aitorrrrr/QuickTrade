package com.example.a2dam.quicktrade;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    private Button enviar;
    private Button cancelar;

    private EditText user;
    private EditText nom;
    private EditText apell;
    private EditText email;
    private EditText tel;
    private EditText pw;

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
                Usuario aux = new Usuario(Integer.parseInt(user.getText().toString()), nom.getText().toString(), apell.getText().toString(), email.getText().toString(), Integer.parseInt(tel.getText().toString()), pw.getText().toString(), true);

                Intent i = new Intent();
                i.putExtra("user", aux);

                setResult(RESULT_OK, i);
                finish();
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
}

package com.example.a2dam.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Log extends AppCompatActivity {

    private Button entrar;
    private Button registrar;
    private ArrayList<Usuario> users;
    private EditText inputUser;
    private EditText inputPw;

    private static int REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        users = new ArrayList<Usuario>();

        inputUser = (EditText) this.findViewById(R.id.inputUser);
        inputPw = (EditText) this.findViewById(R.id.inputPw);

        registrar = (Button) this.findViewById(R.id.registrar);
        registrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),SignUp.class);

                startActivityForResult(i, REQUEST);
            }
        });

        entrar = (Button) this.findViewById(R.id.entrar);
        entrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for (Usuario aux: users)
                {
                    if (aux.getNombre().toString().compareTo(inputUser.getText().toString())==0)
                    {
                        inputUser.setText("Usuario válido");

                        if (aux.getPassword().toString().compareTo(inputPw.getText().toString())==0)
                        {
                            inputPw.setText("Contraseña valida");
                        }
                        else
                        {
                            inputPw.setText("Contraseña no válida");
                        }
                    }
                    else
                    {
                        inputUser.setText("Usuario no válido");
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                Usuario aux = (Usuario) data.getExtras().getParcelable("user");
                users.add(aux);
            }
        }
    }
}

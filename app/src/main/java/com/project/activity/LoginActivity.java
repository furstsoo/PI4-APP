package com.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.project.entity.GlobalUser;
import com.project.httpServices.HttpServiceLogin;
import com.project.entity.R;
import com.project.entity.Retorno;
import com.project.entity.User;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private EditText textEmail, textSenha;
    private Button btn;
    User us = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = findViewById(R.id.txEmail);
        textSenha = findViewById(R.id.txSenha);
        btn = findViewById(R.id.btnLogin);

        listenerButton();

    }

    private void listenerButton() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us.setEmail(textEmail.getText().toString());
                us.setPassword(textSenha.getText().toString());

                User user = validaLogin(us.getEmail(), us.getPassword());

                if (user != null) {
                    final GlobalUser globalUser = (GlobalUser) getApplicationContext();
                    globalUser.setId(user.getId());
                    globalUser.setName(user.getName());
                    globalUser.setEmail(user.getEmail());
                    globalUser.setApartment(user.getApartment());
                    globalUser.setBlock(user.getBlock());
                    globalUser.setTypeUser(user.getTypeUser());
                    Intent intentCad = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intentCad);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private User validaLogin(String email, String password) {
        try {
            User rs = new HttpServiceLogin(email, password).execute().get();
            return rs;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
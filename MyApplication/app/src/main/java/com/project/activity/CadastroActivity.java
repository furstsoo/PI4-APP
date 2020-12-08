package com.project.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.entity.R;
import com.project.entity.Retorno;
import com.project.entity.User;
import com.project.httpServices.HttpServiceCadastrarUser;

import java.util.concurrent.ExecutionException;

public class CadastroActivity extends AppCompatActivity {
    private EditText nome, email, senha, apto, bl;
    private Button btnCad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.txNome);
        email = findViewById(R.id.txEmail);
        senha = findViewById(R.id.txSenha);
        apto = findViewById(R.id.txApart);
        bl = findViewById(R.id.txBloco);
        btnCad = findViewById(R.id.btnCadastrar);

        listenerButtonCadastro();


    }

    private void listenerButtonCadastro() {
        btnCad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User us = criaEntidade(nome, email, senha, apto, bl);
                Retorno retorno = insereUsuario(us);

                if (retorno.getId() == 201) {
                    Intent intentCad = new Intent(CadastroActivity.this, MenuActivity.class);
                    startActivity(intentCad);
                } else {
                    Toast.makeText(getApplicationContext(), "Dados incorretos, verifique", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private Retorno insereUsuario(User u) {
        try {
            Retorno rs = new HttpServiceCadastrarUser().execute(u).get();
            return rs;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User criaEntidade(EditText nome, EditText email, EditText senha, EditText apto, EditText bl) {
        User user = new User();
        user.setApartment(apto.getText().toString());
        user.setBlock(bl.getText().toString());
        user.setEmail(email.getText().toString());
        user.setName(nome.getText().toString());
        user.setPassword(senha.getText().toString());
        user.setTypeUser("C");

        return user;
    }
}
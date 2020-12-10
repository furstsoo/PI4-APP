package com.project.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.entity.GlobalUser;
import com.project.entity.R;

public class MenuActivity extends AppCompatActivity {
    private Button encomendas, salaoFestas, avisos, sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        final GlobalUser globalUser = (GlobalUser) getApplicationContext();
        TextView textView = (TextView) findViewById(R.id.txtLogin);
        textView.setText("Bem vindo " + globalUser.getName());

        encomendas = findViewById(R.id.btnOrder);
        salaoFestas = findViewById(R.id.btnSalao);
        avisos = findViewById(R.id.btnAvisos);
        sair = findViewById(R.id.btnSair);

        if (!globalUser.getTypeUser().equals("C")) {
            salaoFestas.setVisibility(View.GONE);
        }

        listenerButtons();

    }

    private void listenerButtons() {
        encomendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, EncomendasActivity.class);
                    startActivity(intent);
            }
        });

        salaoFestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, SalaoActivity.class);
                    startActivity(intent);
            }
        });


        avisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, AvisosActivity.class);
                    startActivity(intent);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GlobalUser globalUser = (GlobalUser) getApplicationContext();
                globalUser.setId(0);
                globalUser.setName(null);
                globalUser.setEmail(null);
                globalUser.setApartment(null);
                globalUser.setBlock(null);
                globalUser.setTypeUser(null);
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
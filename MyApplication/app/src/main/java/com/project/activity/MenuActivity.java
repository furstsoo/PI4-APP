package com.project.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.entity.R;

public class MenuActivity extends AppCompatActivity {
    private Button encomendas, salaoFestas, message, avisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        encomendas = findViewById(R.id.btnOrder);
        salaoFestas = findViewById(R.id.btnSalao);
        message = findViewById(R.id.btnComunidade);
        avisos = findViewById(R.id.btnAvisos);

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

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MessageActivity.class);
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
    }
}
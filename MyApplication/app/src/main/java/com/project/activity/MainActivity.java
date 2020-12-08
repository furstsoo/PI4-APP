package com.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.project.entity.R;
import com.project.entity.User;

public class MainActivity extends AppCompatActivity {

  private Button btnLogin, btnCadastro;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnLogin = findViewById(R.id.login);
    btnCadastro = findViewById(R.id.register);

    listenersButtons();
  }

  private void listenersButtons() {
    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
      }
    });

    btnCadastro.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v) {

        Intent intentCad = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intentCad);

      }
    });
  }



}
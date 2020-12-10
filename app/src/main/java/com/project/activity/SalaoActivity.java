package com.project.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.entity.*;
import com.project.httpServices.HttpServiceAtualizarEncomenda;
import com.project.httpServices.HttpServiceCadastrarSalao;
import com.project.httpServices.HttpServiceRegister;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.security.AccessController.getContext;

public class SalaoActivity extends AppCompatActivity {
    private EditText txtDate;
    private Button btnVerificar;
    AlertDialog.Builder builder;
    GlobalUser globalUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salao);

        globalUser = (GlobalUser) getApplicationContext();

        txtDate = findViewById(R.id.txtDate);
        btnVerificar = findViewById(R.id.btnVerificar);

        listenerButton();

    }

    private void listenerButton() {
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String data = txtDate.getText().toString();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = simpleDateFormat.parse(data);
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    String dataFormatada = simpleDateFormat2.format(date);

                    if (new HttpServiceRegister(dataFormatada).execute().get()) {
                        Toast.makeText(getApplicationContext(), "Salão já está reservado neste dia, favor verificar outra data!", Toast.LENGTH_SHORT).show();
                    } else {
                        builder = new AlertDialog.Builder(SalaoActivity.this);
                        builder.setTitle("Confirmação de Reserva");
                        builder.setMessage("Deseja reservar o salão para esta data? (" + data + ")");

                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    Register register = new Register();
                                    register.setCd_user(globalUser.getId());
                                    register.setRegister_day(dataFormatada);
                                    Retorno retorno = new HttpServiceCadastrarSalao().execute(register).get();
                                    if (retorno != null && retorno.getId() == 201) {
                                        Toast.makeText(getApplicationContext(), "Salão agendado com sucesso!", Toast.LENGTH_SHORT).show();
                                        txtDate.setText("");
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Erro ao reservar salão!", Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            }
                        });

                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
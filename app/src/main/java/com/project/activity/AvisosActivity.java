package com.project.activity;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.entity.*;
import com.project.httpServices.HttpServiceAvisos;
import com.project.httpServices.HttpServiceCadastrarAviso;
import com.project.httpServices.HttpServiceCadastrarEncomenda;
import com.project.httpServices.HttpServiceLogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AvisosActivity extends AppCompatActivity {

    EditText txtTitulo, txtDescricao;
    Button btnCadastrar;
    GlobalUser globalUser;
    ListView listview;
    SimpleAdapter adapter;
    List<Map<String, String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_avisos);
            globalUser = (GlobalUser) getApplicationContext();
            listview = (ListView) findViewById(R.id.listView);

            data = new ArrayList<Map<String, String>>();

            List<Aviso> listAviso = (List<Aviso>) new HttpServiceAvisos().execute().get();
            adicionaDadosLista(data, listAviso);
            txtTitulo = (EditText) findViewById(R.id.txtTitulo);
            txtDescricao = (EditText) findViewById(R.id.txtDescricao);
            TableLayout tableCadastro = (TableLayout) findViewById(R.id.tableCadastro);
            btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

            if (globalUser.getTypeUser().equals("C")) {
                tableCadastro.setVisibility(View.GONE);
            }
            setListeners();
            adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,
                    new String[]{"First Line", "Second Line"},
                    new int[]{android.R.id.text1, android.R.id.text2});

            listview.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setListeners() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Aviso aviso = new Aviso();
                    aviso.setCd_user(globalUser.getId());
                    aviso.setMessage(txtDescricao.getText().toString());
                    aviso.setTitle(txtTitulo.getText().toString());
                    Retorno retorno = cadastrar(aviso);

                    if (retorno != null && retorno.getId() == 201) {
                        limparCampos();
                        List<Aviso> listAviso = (List<Aviso>) new HttpServiceAvisos().execute().get();
                        data.clear();
                        adicionaDadosLista(data, listAviso);

                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Aviso cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar aviso!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private Retorno cadastrar(Aviso aviso) {
        try {
            Retorno rs = new HttpServiceCadastrarAviso().execute(aviso).get();
            return rs;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void limparCampos() {
        txtDescricao.setText("");
        txtTitulo.setText("");
    }

    private void adicionaDadosLista(List<Map<String, String>> data, List<Aviso> avisos) {
        for (int i = 0; i < avisos.size(); i++) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("First Line", avisos.get(i).getTitle());
            datum.put("Second Line", avisos.get(i).getMessage());
            data.add(datum);
        }
    }
}
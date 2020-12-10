package com.project.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.entity.*;
import com.project.httpServices.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EncomendasActivity extends AppCompatActivity {
    private Button btnCadastrar, btnPesquisar;
    private EditText txtAddressee, txtApartment, txtBlock, txtBlockPesq, txtAddresseePesq, txtApartmentPesq;
    ListView listview;
    List<Map<String, String>> data;
    SimpleAdapter adapter;
    GlobalUser globalUser;
    Map<Integer, Order> listAvisos = new HashMap<>();
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encomendas);

        globalUser = (GlobalUser) getApplicationContext();
        TableLayout layoutCadastrar = (TableLayout) findViewById(R.id.layoutCadastrar);
        TableRow trowBlock = (TableRow) findViewById(R.id.trowBlock);
        TableRow trowApartment = (TableRow) findViewById(R.id.trowApartment);
        TableRow trowNome = (TableRow) findViewById(R.id.trowNome);
        txtAddressee = (EditText) findViewById(R.id.txtAddressee);
        txtApartment = (EditText) findViewById(R.id.txtApartment);
        txtBlock = (EditText) findViewById(R.id.txtBlock);
        txtAddresseePesq = (EditText) findViewById(R.id.txtAddresseePesq);
        txtApartmentPesq = (EditText) findViewById(R.id.txtApartmentPesq);
        txtBlockPesq = (EditText) findViewById(R.id.txtBlockPesq);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnPesquisar = (Button)  findViewById(R.id.btnPesquisar);

        listview = (ListView) findViewById(R.id.listView);
        data = new ArrayList<Map<String, String>>();
        adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"First Line", "Second Line" },
                new int[] {android.R.id.text1, android.R.id.text2 });
        listview.setAdapter(adapter);

        setListeners();

        if (!globalUser.getTypeUser().equals("O") && !globalUser.getTypeUser().equals("A")) {
            layoutCadastrar.setVisibility(View.GONE);
            trowBlock.setVisibility(View.GONE);
            trowApartment.setVisibility(View.GONE);
            trowNome.setVisibility(View.GONE);
        }

    }

    private void setListeners() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    final Order orderSelecionada = listAvisos.get(i);
                    if (orderSelecionada.getStatus().equals("A")) {
                        builder = new AlertDialog.Builder(EncomendasActivity.this);
                        builder.setTitle("Atualização de Encomenda");
                        builder.setMessage("Deseja marcar esta encomenda como entregue?");

                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    orderSelecionada.setStatus("I");
                                    orderSelecionada.setDt_pickup(getDateNow());
                                    Retorno retorno = new HttpServiceAtualizarEncomenda().execute(orderSelecionada).get();
                                    if (retorno != null && retorno.getId() == 200) {
                                        Toast.makeText(getApplicationContext(), "Encomenda entregue com sucesso!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar encomenda!", Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                    atualizarListaEncomendas();
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
                    } else {
                        Toast.makeText(getApplicationContext(), "Esta encomenda já foi entregue!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Order order = null;
                if (globalUser.getTypeUser().equals("C")) {
                    order = new Order(globalUser.getName(), "A", globalUser.getApartment(), globalUser.getBlock(), getDateNow(), globalUser.getId());
                } else {
                    order = new Order(txtAddressee.getText().toString(), "A", txtApartment.getText().toString(), txtBlock.getText().toString(), getDateNow(), globalUser.getId());
                }

                Retorno retorno = cadastrar(order);

                if (retorno != null && retorno.getId() == 201) {
                    Toast.makeText(getApplicationContext(), "Encomenda cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao cadastrar encomenda!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                atualizarListaEncomendas();
            }
        });
    }

    private void atualizarListaEncomendas() {
        User user = null;
        if (globalUser.getTypeUser().equals("C")) {
            user = new User(globalUser.getBlock(), globalUser.getApartment());
        } else {
            user = new User(txtBlockPesq.getText().toString(), txtApartmentPesq.getText().toString(), txtAddresseePesq.getText().toString());
        }
        List<Order> orders = pesquisar(user);

        if (orders != null) {
            if (orders.size() == 0) {
                Toast.makeText(getApplicationContext(), "Você não possui nenhuma encomenda para retirar!", Toast.LENGTH_SHORT).show();
            } else {
                adicionaDadosLista(data, orders);
                adapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Erro ao cadastrar encomenda!", Toast.LENGTH_SHORT).show();
        }
    }

    public List<Order> pesquisar(User user) {
        try {
            List<Order> rs = new HttpServiceEncomendas(user).execute().get();
            return rs;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void adicionaDadosLista(List<Map<String, String>> data, List<Order> orders) {
        data.clear();
        listAvisos.clear();
        for(int i= 0; i < orders.size(); i++) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("First Line", orders.get(i).getRemetente());
            datum.put("Second Line", orders.get(i).getDescricao() + " | Recebido em " + orders.get(i).getDt_delivery()
                    + ". | Situação: " + (orders.get(i).getStatus().equals("A") ? "AGUARDANDO RETIRADA" : "RETIRADO em " + orders.get(i).getDt_pickup() + "."));
            listAvisos.put(i, orders.get(i));
            data.add(datum);
        }
    }

    private void limparCampos() {
        txtAddressee.setText("");
        txtApartment.setText("");
        txtBlock.setText("");
    }

    public static String getDateNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(LocalDateTime.now());
    }

    private Retorno cadastrar(Order order) {
        try {
            Retorno rs = new HttpServiceCadastrarEncomenda().execute(order).get();
            return rs;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
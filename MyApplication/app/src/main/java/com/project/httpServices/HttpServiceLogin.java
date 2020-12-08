package com.project.httpServices;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.project.entity.Retorno;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HttpServiceLogin extends AsyncTask<Void, Void, Retorno> {
    private final String email;
    private final String senha;

    public HttpServiceLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Override
    protected Retorno doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("http://192.168.0.16:8080/login?email=" + this.email + "&password=" + this.senha);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(resposta.toString(), Retorno.class);
    }

}

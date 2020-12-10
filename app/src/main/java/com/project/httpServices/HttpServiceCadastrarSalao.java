package com.project.httpServices;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.project.entity.Order;
import com.project.entity.Register;
import com.project.entity.Retorno;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpServiceCadastrarSalao extends AsyncTask<Register, Void, Retorno> {

    @Override
    protected Retorno doInBackground(Register... registers) {
        Gson gson = new Gson();

        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("http://192.168.0.16:8080/add-register");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);

            String body = gson.toJson(registers[0]);
            OutputStream output = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
            writer.write(body);
            writer.flush();
            writer.close();
            output.close();

            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_ACCEPTED) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    resposta.append(line);
                }
            }

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(resposta.toString(), Retorno.class);
    }
}
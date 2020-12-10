package com.project.httpServices;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.entity.Aviso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpServiceAvisos extends AsyncTask<Void, Void, List<Aviso>> {

    public HttpServiceAvisos() {
    }

    @Override
    protected List<Aviso> doInBackground(Void... voids) {
        List<Aviso> resposta = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("http://192.168.0.16:8080/view-communication");
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
                result.append(scanner.next() + " ");
            }
            Type listOfMyClassObject = new TypeToken<ArrayList<Aviso>>() {}.getType();

            resposta = new Gson().fromJson(result.toString(), listOfMyClassObject);

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resposta;
    }

}
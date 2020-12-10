package com.project.httpServices;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.entity.Order;
import com.project.entity.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpServiceEncomendas extends AsyncTask<User, Void, List<Order>> {
    private final User user;

    public HttpServiceEncomendas(User user) {
        this.user = user;
    }

    @Override
    protected List<Order> doInBackground(User... users) {
        List<Order> listOrder = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try {
            StringBuilder urlReq = new StringBuilder();
            urlReq.append("http://192.168.0.16:8080/view-order");
            if (this.user != null && this.user.getName() != null) {
                urlReq.append("?name="+this.user.getName() + "&block=" + this.user.getBlock() + "&apartment=" +this.user.getApartment());
            } else {
                urlReq.append("?block=" + this.user.getBlock() + "&apartment=" +this.user.getApartment());
            }
            URL url = new URL(urlReq.toString());
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
            Type listOfMyClassObject = new TypeToken<ArrayList<Order>>() {}.getType();

            listOrder = new Gson().fromJson(result.toString(), listOfMyClassObject);

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOrder;
    }

}
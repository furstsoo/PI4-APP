package com.project.httpServices;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.entity.Order;
import com.project.entity.Register;
import com.project.entity.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HttpServiceRegister extends AsyncTask<String, Void, Boolean> {
    private final String string;

    public HttpServiceRegister(String string) {
        this.string = string;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        Boolean retorno = false;
        List<Register> listOrder = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        try {
            StringBuilder urlReq = new StringBuilder();



            urlReq.append("http://192.168.0.16:8080/view-register?date=" + string);

            URL url = new URL(urlReq.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "*/*");
            connection.setDoOutput(true);
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.connect();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                result.append(scanner.next());
            }
            retorno = Boolean.valueOf(result.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
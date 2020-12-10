package com.project.httpServices;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.project.entity.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, User> {
  private final String id;

  public HttpService(String id) {
    this.id = id;
  }


  @Override
  protected User doInBackground(Void... voids) {
    StringBuilder resposta = new StringBuilder();
    try {
      URL url = new URL("http://192.168.0.16:8080/view-user?id="+ this.id);
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
    return new Gson().fromJson(resposta.toString(), User.class);
  }
}

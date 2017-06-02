package ru.itis.kpfu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label messageLabel;

    public void sayHello() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    String firstName = username.getText();
                    String lastName = password.getText();
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpGet httpGet = new HttpGet("http://localhost:8080/rest"+"/hub?page=0&size=3");
                    log.debug("Http request sent to  " + httpGet.getURI());
                    CloseableHttpResponse response1 = httpclient.execute(httpGet);

                    System.out.println(response1.toString());
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
                    StringBuffer result = new StringBuffer();
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                  JSONObject o = new JSONObject(result.toString());
                   messageLabel.setText("Hello " + o.getJSONArray("content").get(0));
                    System.out.println(o.getJSONArray("content").get(0));



                    HttpPost httpPost = new HttpPost("http://localhost:8080/rest/change");


                    System.out.println("BBBBBBBBBBBBBBBBBBBBBB");

                    CloseableHttpResponse response2 = httpclient.execute(httpPost);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

}

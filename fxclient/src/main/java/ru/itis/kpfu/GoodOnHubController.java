package ru.itis.kpfu;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import ru.itis.kpfu.Pojo.GoodInOrderPojo;
import ru.itis.kpfu.Pojo.GoodOnHubPojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by habar on 01.06.2017.
 */
public class GoodOnHubController {

    private ObservableList<GoodInOrderPojo> usersData1 = FXCollections.observableArrayList();


    private ObservableList<GoodOnHubPojo> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<GoodOnHubPojo> table;
    @FXML
    private TableView<GoodInOrderPojo> table1;

    @FXML
    private TableColumn<GoodOnHubPojo, Integer> id;
    @FXML
    private TableColumn<GoodOnHubPojo, String> goodColumn;
    @FXML
    private TableColumn<GoodOnHubPojo, String> hubColumn;
    @FXML
    private TableColumn<GoodOnHubPojo, String> countColumn;

    @FXML
    private TableColumn<GoodInOrderPojo, Integer> id1;
    @FXML
    private TableColumn<GoodInOrderPojo, String> goodColumn1;
    @FXML
    private TableColumn<GoodInOrderPojo, String> orderColumn1;
    @FXML
    private TableColumn<GoodInOrderPojo, String> countColumn1;
    @FXML
    private TableColumn<GoodInOrderPojo, String> statusColumn1;

    @FXML
    private Pagination pagination;
    @FXML
    private Pagination pagination1;


    @FXML
    private void initialize() {


        if (usersData.size() == 0) {
            initGoodOnHubData(pagination.getCurrentPageIndex(), "first");
        } else {
            initGoodOnHubData(pagination.getCurrentPageIndex(), "second");
        }
        // устанавливаем тип и значение которое должно хранится в колонке


        id.setCellValueFactory(new PropertyValueFactory<GoodOnHubPojo, Integer>("id"));
        goodColumn.setCellValueFactory(new PropertyValueFactory<GoodOnHubPojo, String>("good"));
        hubColumn.setCellValueFactory(new PropertyValueFactory<GoodOnHubPojo, String>("hub"));
        countColumn.setCellValueFactory(new PropertyValueFactory<GoodOnHubPojo, String>("maxCount"));

        countColumn.setCellFactory(TextFieldTableCell.<GoodOnHubPojo>forTableColumn());
        countColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<GoodOnHubPojo, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<GoodOnHubPojo, String> t) {
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setMaxCount(t.getNewValue());
                       saveFirstTable();
                    }
                }
        );


        // заполняем таблицу данными
        table.setItems(usersData);
        table.editingCellProperty();


    }

    // подготавливаем данные для таблицы

    private void initGoodOnHubData(int page, String doing) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/rest" + "/hub?page=" + page + "&size=50");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);

            System.out.println(response1.toString());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);

            }

            JSONObject o = new JSONObject(result.toString());

            if (!doing.equals("first")) {
                usersData.remove(0, usersData.size());
            }
            for (int i = 0; i < o.getJSONArray("content").length(); i++) {
                GoodOnHubPojo goodOnHubPojo = new GoodOnHubPojo();
                JSONObject jsonObject = new JSONObject(o.getJSONArray("content").get(i).toString());

                goodOnHubPojo.setId(jsonObject.getInt("id"));
                JSONObject jsonObject1 = new JSONObject(jsonObject.get("hub").toString());
                System.out.println(jsonObject1.toString());
                goodOnHubPojo.setHub(jsonObject1.getString("address"));
                jsonObject1 = new JSONObject(jsonObject.get("good").toString());
                goodOnHubPojo.setGood(jsonObject1.getString("name"));
                goodOnHubPojo.setMaxCount(jsonObject.get("maxCount").toString());
                usersData.add(goodOnHubPojo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveFirstTable() {

        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {
            Gson gson = new Gson();
            // convert your list to json
            String jsonCartList = gson.toJson(table.getItems());
            // print your generated json
            System.out.println("jsonCartList: " + jsonCartList);

            HttpPost request = new HttpPost("http://localhost:8080/rest/saveMe");
            StringEntity params = new StringEntity(jsonCartList);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);


            initialize();


        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }


    @FXML
    private void initializeSecTable() {
        if (usersData1.size() == 0) {
            initGoodInOrderData(pagination1.getCurrentPageIndex(), "first");
        } else {
            initGoodInOrderData(pagination1.getCurrentPageIndex(), "second");
        }


        id1.setCellValueFactory(new PropertyValueFactory<GoodInOrderPojo, Integer>("id"));
        goodColumn1.setCellValueFactory(new PropertyValueFactory<GoodInOrderPojo, String>("good"));
        orderColumn1.setCellValueFactory(new PropertyValueFactory<GoodInOrderPojo, String>("order"));
        countColumn1.setCellValueFactory(new PropertyValueFactory<GoodInOrderPojo, String>("maxCount"));
        statusColumn1.setCellValueFactory(new PropertyValueFactory<GoodInOrderPojo, String>("status"));

        countColumn1.setCellFactory(TextFieldTableCell.<GoodInOrderPojo>forTableColumn());
        statusColumn1.setCellFactory(TextFieldTableCell.<GoodInOrderPojo>forTableColumn());


        countColumn1.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<GoodInOrderPojo, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<GoodInOrderPojo, String> t) {
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setMaxCount(t.getNewValue());
                        saveSecondTable();
                    }
                }
        );
        statusColumn1.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<GoodInOrderPojo, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<GoodInOrderPojo, String> t) {
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setStatus(t.getNewValue());
                        saveSecondTable();
                    }
                }
        );

        table1.setItems(usersData1);


    }

    private void initGoodInOrderData(int page, String doing) {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/rest" + "/order?page=" + page + "&size=50");
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);

            System.out.println(response1.toString());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response1.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            JSONObject o = new JSONObject(result.toString());

            if (!doing.equals("first")) {
                usersData1.remove(0, usersData1.size());
            }
            for (int i = 0; i < o.getJSONArray("content").length(); i++) {
                GoodInOrderPojo goodInOrderPojo = new GoodInOrderPojo();
                JSONObject jsonObject = new JSONObject(o.getJSONArray("content").get(i).toString());

                goodInOrderPojo.setId(jsonObject.getInt("id"));
                JSONObject jsonObject1 = new JSONObject(jsonObject.get("order").toString());

                goodInOrderPojo.setOrder(Integer.toString(jsonObject1.getInt("id")));
                goodInOrderPojo.setStatus(jsonObject1.getString("status"));
                jsonObject1 = new JSONObject(jsonObject.get("good").toString());
                goodInOrderPojo.setGood(jsonObject1.getString("name"));
                goodInOrderPojo.setMaxCount(jsonObject.get("count").toString());
                usersData1.add(goodInOrderPojo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void saveSecondTable() {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {
            Gson gson = new Gson();
            // convert your list to json
            String jsonCart = gson.toJson(table1.getItems());
            System.out.println(statusColumn1.getCellObservableValue(8));
            String jsonCart2 = gson.toJson(usersData1.get(8));
            System.out.println(jsonCart2);
            // print your generated json
            System.out.println("jsonCartList: " + jsonCart);

            HttpPost request = new HttpPost("http://localhost:8080/rest/saveMe2table");
            StringEntity params = new StringEntity(jsonCart);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);


            initializeSecTable();


        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

}

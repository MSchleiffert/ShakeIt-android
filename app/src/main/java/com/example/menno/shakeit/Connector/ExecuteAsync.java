package com.example.menno.shakeit.Connector;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Menno on 9-6-2016.
 */
public class ExecuteAsync extends AsyncTask<String, String, String> {
    public final static String apiURL = "http://dhb.jeroenkraan.eu/shakeit/api.php";

    private Activity activeActivity;
    private ApiStrategy activeStrategy;


    @Override
    protected String doInBackground(String... params) {
        String requestType = params[0];
        String urlString = params[1]; // URL to call
        String jsonString = "";

        if (requestType.equals("json")) {
            jsonString = params[2];
        }

        String resultToDisplay = "";

        InputStream in = null;

        // HTTP Get
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            if (requestType.equals("json")) {
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");


                DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream());
                printout.writeBytes(jsonString);
                printout.flush();
                printout.close();
            }

            in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            resultToDisplay = total.toString();


        } catch (Exception e) {

            System.out.println(e.getMessage());
            Log.d("asyncERR", e.getMessage());

            return e.getMessage();

        }

        return resultToDisplay;
    }

    protected void onPostExecute(String result) {
        try {
            this.activeStrategy.processData(this.activeActivity, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void executeStrategy(Activity activity) {
        this.activeActivity = activity;

        if (this.activeStrategy.getRequestType().equals("simple")) {  // check het requesttype
            this.execute(this.activeStrategy.getRequestType(), apiURL + this.activeStrategy.executeStrategy());
        } else if (this.activeStrategy.getRequestType().equals("json")) {
            ApiJSONStrategy tempStrat = (ApiJSONStrategy) this.activeStrategy;
            this.execute(tempStrat.getRequestType(), apiURL + tempStrat.executeStrategy(), tempStrat.stringJsonObject());
        }

    }

    public void executeStrategy() {

        if (this.activeStrategy.getRequestType().equals("simple")) {  // check het requesttype
            this.execute(this.activeStrategy.getRequestType(), apiURL + this.activeStrategy.executeStrategy());
        } else if (this.activeStrategy.getRequestType().equals("json")) {
            ApiJSONStrategy tempStrat = (ApiJSONStrategy) this.activeStrategy;

            this.execute(tempStrat.getRequestType(), apiURL + tempStrat.executeStrategy(), tempStrat.stringJsonObject());
            Log.d("testexecute", "executeStrategy: " + apiURL + tempStrat.executeStrategy());
        }

    }

    public void setStrategy(ApiStrategy strategy) {
        this.activeStrategy = strategy;
    }
}


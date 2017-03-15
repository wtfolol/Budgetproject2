package com.example.xiaows.budgetproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Dell on 8/3/2017.
 */

public class SubmitBG extends AsyncTask<String,Void,String> {


    Context context;
    public SubmitBG (Context context) {

        this.context = context;
    }
    @Override

    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://www.xwl.net23.net/bpupdate.php";

        if(type.equals("Submit")) {
            try {
                String expensestype = params[1];
                String expensesprice = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("expensestype","UTF-8")+"="+URLEncoder.encode(expensestype,"UTF-8")+"&"
                        +URLEncoder.encode("expensesprice","UTF-8")+"="+URLEncoder.encode(expensesprice,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {
        Toast t = Toast.makeText(context,"wtf"+result, Toast.LENGTH_LONG);
        t.show();
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
//package com.example.testapp;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.MalformedURLException;
//import android.os.AsyncTask;
//import android.support.v7.widget.RecyclerView;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.net.HttpURLConnection;
//import java.util.List;
//
//public class fetchData extends AsyncTask<Void, Void, String> {
//
//    private RecyclerView recyclerView;
//    private MainActivity viewAdapter;
//
//    public List<String> mValues;
//    private Fruit fruit;
//
//    public fetchData(List<String> mValues, MainActivity mainActivity) {
//    }
//
//
//    //Read JSON script
//    @Override
//    protected String doInBackground(Void... params) {
//            String resultJson = "";
//
//        try {
//
//                URL url = new URL("https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json");
//
//                ////BR connected to IS so it can read from the data from IS.
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                String line = "";
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line);
//                }
//
//                resultJson = buffer.toString();
//                //resultJson = readJsonFile();
//                 //return resultJson;
//
//        } catch (MalformedURLException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return resultJson;
//    }
//
//    //LOAD DATA TO USER
//    @Override
//    protected void onPostExecute(String strJson) {
//        super.onPostExecute(strJson);
//
//        try {
//            JSONObject json = new JSONObject(strJson);
//            JSONArray jArray = json.getJSONArray("fruit");
//
//            for (int i = 0; i < jArray.length(); i++) {
//                JSONObject jsonObject = jArray.getJSONObject(i);
//
//                Fruit fruit = new Fruit(jsonObject.getString("type"), jsonObject.getInt("price"),
//                        jsonObject.getInt("weight"));
//
//                mValues.add(String.valueOf(fruit));
//
//            }
//
//            viewAdapter = new MainActivity(mValues, fetchData.this);
//            recyclerView.setAdapter(viewAdapter);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//}
//

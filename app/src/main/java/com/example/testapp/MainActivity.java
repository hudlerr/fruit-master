package com.example.testapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter viewAdapter;
    //public static FruitAdapter viewAdapter;
    private List<Fruit> fruits;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById( R.id.fruit_list );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager( this ));

        fruits = new ArrayList<>();

        new fetchData().execute();


        //Create refresh button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "One second, refreshing list of fruits...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

            }
        });
    }


    public class fetchData extends AsyncTask<Void, Void, String> {
        //Read JSON script
        @Override
        protected String doInBackground(Void... params) {
            String resultJson = "";

            try {

                URL url = new URL( "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json" );

                ////BR connected to IS so it can read from the data from IS.
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append( line );
                }

                resultJson = buffer.toString();
                //resultJson = readJsonFile();
                //return resultJson;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return resultJson;
        }

        //LOAD DATA TO USER

        protected void onPostExecute(String strJson) {
            super.onPostExecute( strJson );

            try {
                JSONObject json = new JSONObject( strJson );
                JSONArray jArray = json.getJSONArray( "fruit" );

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject( i );

                    Fruit fruit = new Fruit( jsonObject.getString( "type" ), jsonObject.getInt( "price" ),
                            jsonObject.getInt( "weight" ) );

                    fruits.add( fruit );

                }

                    viewAdapter = new FruitAdapter( fruits, MainActivity.this );
                    recyclerView.setAdapter( viewAdapter );

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

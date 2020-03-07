package com.example.testapp;

import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import com.example.testapp.Fruit;
import com.example.testapp.FruitAdapter;
import java.math.BigDecimal;

public class MainFruitActivity extends AppCompatActivity {

    private TextView fruit_type, fruit_price, fruit_weight;
    private String type;
    private int price;
    private int weight;


    private Fruit mValuePos = new Fruit( "", 0, 0 );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fruit_detail_main );

        // Create action bar with up feature.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled( true );
        }
        fruit_type = findViewById( R.id.fruit_type );
        fruit_price = findViewById( R.id.fruit_price );
        fruit_weight = findViewById( R.id.fruit_weight );

        getFruitInfo();
        setFruitInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected( item );
    }

    private void getFruitInfo() {
        Intent intent = getIntent();
        type = intent.getStringExtra( "type" );
        price = intent.getIntExtra( "price", 0 );
        weight = intent.getIntExtra( "weight" , 0);
    }

    private void setFruitInfo() {
        fruit_type.setText( type );
        fruit_price.setText( getPrice() );
        fruit_weight.setText( getWeight() );

    }

    // display the price in pounds and pence
    private String getPrice() {

        BigDecimal amount = new BigDecimal( price ).movePointLeft( 2 );
        return String.format( "Price: " + "£" + amount );
        // BigDecimal  amount = item.getPrice()/100.0;
        // return "£" + String.format("%.2f", mValuePos.getPrice()/100.0);
    }

    // weight in kilograms
    private String getWeight() {
       // int value = (weight);
        //int weightInKg = value / 1000;
        BigDecimal weightInKg = new BigDecimal( weight ).movePointLeft( 2 );
        return String.format("Weight: " + weightInKg + " Kg");
        //return String.format("%.2f", mValuePos.getWeight()/1000.0)  +"Kg";
    }



}

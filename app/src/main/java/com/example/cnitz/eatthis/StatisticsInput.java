package com.example.cnitz.eatthis;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.content.Context;
import android.widget.Toast;

import java.util.Date;

public class StatisticsInput extends ActionBarActivity {

    Button saveButton;
    Button cancelButton;
    EditText restaurant;
    EditText price;
    EditText menuItems;
    EditText summary;
    RatingBar rating;
    Context ctx = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_input);


        saveButton = (Button)findViewById(R.id.SaveButton);
        cancelButton = (Button)findViewById(R.id.CancelButton);
        restaurant = (EditText)findViewById(R.id.RestaurantInput);
        price = (EditText)findViewById(R.id.PriceInput);
        menuItems = (EditText)findViewById(R.id.MenuListInput);
        summary = (EditText)findViewById(R.id.SummaryInput);
        rating = (RatingBar)findViewById(R.id.RatingInput);



        cancelButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restaurant.setText("");
                        price.setText("");
                        menuItems.setText("");
                        summary.setText("");
                        rating.setRating(0);
                        Intent goToInput = new Intent(StatisticsInput.this, ReviewAndStatsMenu.class);
                        StatisticsInput.this.startActivity(goToInput);
                    }
                }
        );

        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: Determine Required Fields
                        Review review = new Review();

                        if (!isEmpty(restaurant))
                            review.setName(restaurant.getText().toString());
                        else {
                            Toast.makeText(StatisticsInput.this, "Name field must be populated.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(isEmpty(price)){
                            Toast.makeText(StatisticsInput.this, "Price must be greater than zero, we know you paid for it...", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (Double.parseDouble(price.getText().toString()) > 0)
                            review.setPrice(Double.parseDouble(price.getText().toString()));
                        else{
                            Toast.makeText(StatisticsInput.this, "Price must be greater than zero, we know you paid for it...", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (!isEmpty(summary))
                            review.setSummary(summary.getText().toString());
                        else
                            review.setSummary("");

                        if (!isEmpty(menuItems))
                            review.setSummary(menuItems.getText().toString());
                        else
                            review.setSummary("");

                        review.setRating((int) rating.getRating());
                        Date date = new Date();
                        review.setDate(date.toString());

                        ReviewHelper rdb = new ReviewHelper(ctx);
                        rdb.addReview(rdb, review);


                        restaurant.setText("");
                        price.setText("");
                        menuItems.setText("");
                        summary.setText("");
                        rating.setRating(0);

                        Toast.makeText(StatisticsInput.this, "Added to DataBase", Toast.LENGTH_SHORT).show();
                        Intent goToInput = new Intent(StatisticsInput.this, ReviewAndStatsMenu.class);
                        StatisticsInput.this.startActivity(goToInput);

                    }
                }
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isEmpty(EditText et){
        return et.getText().toString().trim().length() == 0;

    }



}

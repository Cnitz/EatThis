package com.example.cnitz.eatthis;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.Date;
import java.text.DateFormat;

public class StatisticsInput extends ActionBarActivity {

    Button saveButton;
    Button cancelButton;
    EditText restaurant;
    EditText price;
    EditText menuItems;
    EditText summary;
    RatingBar rating;
    ReviewDb rdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_input);
        rdb = new ReviewDb();


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
                        rating.setNumStars(0);
                    }
                }
        );

        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: Determine Required Fields
                        Review review = new Review();

                        if (!isEmpty(restaurant)) {
                            review.setName(restaurant.getText().toString());
                        }

                        if (!isEmpty(price))
                            review.setPrice(Double.parseDouble(price.getText().toString()));

                        review.setSummary(summary.getText().toString());
                        review.setMenuItems(menuItems.getText().toString());
                        review.setRating(rating.getNumStars());
                        Date date = new Date();
                        review.setDate(date.toString());
                        rdb.InsertRestaurant(review);

                        restaurant.setText("");
                        price.setText("");
                        menuItems.setText("");
                        summary.setText("");
                        rating.setNumStars(0);


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

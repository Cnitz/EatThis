package com.example.cnitz.eatthis;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ReviewAndStatsMenu extends ActionBarActivity {

    Button newReview;
    Button reviewList;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_and_stats_menu);

        newReview = (Button) findViewById(R.id.NewReview);
        reviewList = (Button) findViewById(R.id.ReviewList);

        newReview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToInput = new Intent(ReviewAndStatsMenu.this, StatisticsInput.class);
                        android.support.v4.app.TaskStackBuilder.create(ctx).addNextIntentWithParentStack(goToInput).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                        ReviewAndStatsMenu.this.startActivity(goToInput);
                    }
                }

        );

        reviewList.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToList = new Intent(ReviewAndStatsMenu.this, ReviewList.class);
                        android.support.v4.app.TaskStackBuilder.create(ctx).addNextIntentWithParentStack(goToList).getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                        ReviewAndStatsMenu.this.startActivity(goToList);
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_and_stats_menu, menu);
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
}

package com.example.cnitz.eatthis;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ReviewList extends ActionBarActivity {

    ListView listView;
    Context ctx = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
//        ReviewHelper rdb = new ReviewHelper(ctx);
//        List<Review> reviewList = rdb.getAllReviews(rdb);
        String[] s = {"String1", "String2", "String3", "String4"};
        List<String> test = new ArrayList<String>();
        test.add(s[0]);
        test.add(s[1]);
        test.add(s[2]);
        test.add(s[3]);

        listView = (ListView)findViewById(R.id.ReviewList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.abc_action_menu_item_layout, test);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_list, menu);
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

package com.example.cnitz.eatthis;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReviewList extends ActionBarActivity implements ReviewDisplay.OnFragmentInteractionListener{

    ListView listView;
    Context ctx = this;
    ReviewHelper rdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);
        rdb = new ReviewHelper(ctx);
        List<Review> reviewList = rdb.getAllReviews(rdb);



        listView = (ListView) this.findViewById(R.id.ReviewList);


        ArrayAdapter<Review> adapter = new ArrayAdapter<Review>(ReviewList.this, R.layout.abc_list_menu_item_layout, reviewList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                         ReviewDisplay frg = ReviewDisplay.newInstance(rdb, (Review)parent.getItemAtPosition(position));
                         FragmentTransaction trans = getFragmentManager().beginTransaction();
                         listView.setVisibility(View.INVISIBLE);
                         trans.replace(R.id.ReviewDispFrame, frg);
                         trans.addToBackStack(null);
                         trans.commit();
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_review_list, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

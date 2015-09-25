package com.example.cnitz.eatthis;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ResterauntList extends Activity implements RestaurantsListFragment.OnFragmentInteractionListener{
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    String[] drawerStrings = { "Christian", "Likes", "To", "Eat", "Cheese" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resteraunt_list);
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = "";
                switch (position){
                    case 0:
                        text = "You clicked on option 0";
                        RestaurantsListFragment frg = RestaurantsListFragment.newInstance();
                        FragmentTransaction trans = getFragmentManager().beginTransaction();
                        trans.replace(R.id.content_frame, frg);
                        trans.addToBackStack(null);
                        trans.commit();
                        break;
                    case 1:
                        text = "You clicked on option 1";
                        break;
                    case 2:
                        text = "You clicked on option 2";
                        break;
                    case 3:
                        text = "You clicked on option 3";
                        break;
                    case 4:
                        text = "You clicked on option 4";
                        break;
                }
                Toast.makeText(ResterauntList.this, text, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void addDrawerItems() {

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerStrings);
        mDrawerList.setAdapter(mAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resteraunt_list, menu);
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
    public void onFragmentInteraction(String id) {

    }
}

package com.example.cnitz.eatthis;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class ResterauntList extends ActionBarActivity  implements RestaurantsListFragment.OnFragmentInteractionListener{
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resteraunt_list);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        addDrawerItems();
        setupDrawer();

        mActivityTitle = "EatThis";



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


        

    }




    private void addDrawerItems() {
        String[] drawerStrings = { "Restaurant List", "Schedule", "Map ", "Statistics", "Preferences" };

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerStrings);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = "";
                switch (position) {
                    case 0:
                        text = "You clicked on option 0";
                        RestaurantsListFragment frg = RestaurantsListFragment.newInstance();
                        FragmentTransaction trans = getFragmentManager().beginTransaction();
                        trans.replace(R.id.content_frame, frg);
                        trans.addToBackStack(null);
                        trans.commit();
                        break;
                    case 1:
                        //Schedule
                        Intent myIntent=new Intent(view.getContext(),SchedList.class);
                        startActivity(myIntent);
                        finish();
                        break;
                    case 2:
                        //Map
                        text = "You clicked on option 2";
                        addMapFragment();
                        break;
                    case 3:
                        //Statistics
                        Intent cnitzIntent = new Intent(view.getContext(),ReviewAndStatsMenu.class);
                        startActivity(cnitzIntent);
                        break;
                    case 4:
                        //Preference
                        text = "You clicked on option 4";
                        break;
                }
                //Toast.makeText(ResterauntList.this, text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resteraunt_list, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(double longitude, double latitude, String name) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putDouble("longitude", longitude);
        args.putDouble("latitude", latitude);
        args.putString("name", name);
        fragment.setArguments(args);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);


        transaction.commit();
    }

   private void addMapFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        MapFragment fragment = new MapFragment();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);


        transaction.commit();
    }
}

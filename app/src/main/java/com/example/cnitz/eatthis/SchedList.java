package com.example.cnitz.eatthis;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cnitz.eatthis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 10/30/2015.
 */
public class SchedList extends Activity implements SchedAddClassFragment.OnFragmentInteractionListener{
    Button addClass;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        addClass = (Button) findViewById(R.id.addClass);
        back =     (Button) findViewById(R.id.back);

       final SchedHelper sh = new SchedHelper(this);
        final List<String> list = sh.getAllClasses(sh);
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView listView = (ListView) findViewById(R.id.classList);
        listView.setAdapter(mAdapter);
        addClass = (Button) findViewById(R.id.addClass);
        addClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SchedAddClassFragment frg = SchedAddClassFragment.newInstance(sh);
                        FragmentTransaction trans = getFragmentManager().beginTransaction();
                        trans.replace(R.id.addFrag, frg);
                        trans.addToBackStack(null);
                        addClass.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.INVISIBLE);
                        trans.commit();
                    }
                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                AlertDialog.Builder ad = new AlertDialog.Builder(SchedList.this);
                ad.setTitle("Delete?");
                ad.setMessage("Are you sure you want to delete " + arg2);
                final int positionToRemove = arg2;
                ad.setNegativeButton("Cancel", null);
                ad.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sh.removeAt(positionToRemove, sh);
                        list.remove(positionToRemove);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                ad.show();
            }
        });

        }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_sched_list, menu);
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

package com.example.cnitz.eatthis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
<<<<<<< HEAD

import android.widget.ListView;
=======
>>>>>>> parent of 7b25cf4... working on list, view about to merge
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

import com.example.cnitz.eatthis.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
//TODO: add all the food types
public class RestaurantsListFragment extends Fragment implements AbsListView.OnItemClickListener, View.OnClickListener {
    public enum FoodTypes {
        ASIAN(0),
        PIZZA(1),
        MEXICAN(2);
        private final int number;

        FoodTypes(int number) {
            this.number = number;
        }
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String REST1 = "rest1";
    private static final String REST2 = "rest2";
    private static final String REST3 = "rest3";
    private static final String REST4 = "rest4";
    private static final String REST5 = "rest5";
    ETPlace etPlace;
    List<ETPlace> restList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static String rest1;
    private static String rest2;
    private static String rest3;
    private static String rest4;
    private static String rest5;


    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ArrayAdapter<ETPlace> mAdapter;
    private RestaurantDb rDB;

    // TODO: Rename and change types of parameters
    public static RestaurantsListFragment newInstance() {
        RestaurantsListFragment fragment = new RestaurantsListFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        args.putString(REST1, rest1);
        args.putString(REST2, rest2);
        args.putString(REST3, rest3);
        args.putString(REST4, rest4);
        args.putString(REST5, rest5);

        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestaurantsListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rDB = new RestaurantDb(getActivity().getApplicationContext());
        rDB.CreateExampleData();
        restList = rDB.GetAllRestaurants();

        //System.out.println(restList.get(0).getName());

//        restList = new ArrayList<>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // TODO: Change Adapter to display your content
        mAdapter = new ArrayAdapter<ETPlace>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, restList);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurantslist, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        Button b = (Button) view.findViewById(R.id.button);
        b.setOnClickListener(this);
        Button c = (Button) view.findViewById(R.id.button2);
        c.setOnClickListener(this);
        Button d = (Button) view.findViewById(R.id.button3);
        d.setOnClickListener(this);
        Button e = (Button) view.findViewById(R.id.button4);
        e.setOnClickListener(this);


        return view;
    }

    public void printList(ETPlace place) {
        //print the list everytime
        //put this on the list
        place.getName();


    }

    public void createFoodtype() {
        //create dialog to choose each food types to see. More lists should be added in enum FoodTypes.
        final CharSequence[] foodtypes = {"Asian", "Pizza", "Mexican"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle("Food Types");
        builder.setItems(foodtypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //System.out.println(foodtypes[which]);
                //get the restaurants by food types from the restList, and refresh it
                // mListView.setAdapter();
                String type = (String) foodtypes[which];
                int numberOfFoodTypes = FoodTypes.valueOf(type.toUpperCase()).number;
                //System.out.println(numberOfFoodTypes);
                restList.clear();
                restList = rDB.GetRestaurants(numberOfFoodTypes, null, null);
                System.out.println("Size" + restList.size());
                mAdapter.clear();
                mAdapter.addAll(restList);
                mAdapter.notifyDataSetChanged();
                //((BaseAdapter) mListView.getAdapter()).notifyDataSetChanged();
                System.out.println(numberOfFoodTypes);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        // builder.setView(view);
        builder.show();
        //  final AlertDialog alert = builder.create();
    }

    public void createRatingRange() {
        //create rating dialog, and choose between the numbers of database according to rating.
        final CharSequence[] ratingRange = {"0~1", "1~2", "2~3", "3~4", "4~5"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Rating Range");
        builder.setItems(ratingRange, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String type = (String) ratingRange[which];
                if (type.equals("0~1")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                    mAdapter.clear();
                    mAdapter.addAll(restList);
                    mAdapter.notifyDataSetChanged();
                } else if (type.equals("1~2")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                    mAdapter.clear();
                    mAdapter.addAll(restList);
                    mAdapter.notifyDataSetChanged();
                } else if (type.equals("2~3")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                    mAdapter.clear();
                    mAdapter.addAll(restList);
                    mAdapter.notifyDataSetChanged();
                } else if (type.equals("3~4")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                    mAdapter.clear();
                    mAdapter.addAll(restList);
                    mAdapter.notifyDataSetChanged();
                } else if (type.equals("4~5")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                    mAdapter.clear();
                    mAdapter.addAll(restList);
                    mAdapter.notifyDataSetChanged();
                }


                System.out.println(ratingRange[which]);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();

        builder.show();


    }

    public void createPriceRange() {
    //create the button dialog for price range, and pick according to the database.
        final CharSequence[] priceRange = {"$", "$$", "$$$"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Price Range");
        builder.setItems(priceRange, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(priceRange[which]);
                String type = (String) priceRange[which];
                if (type.equals("$")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                } else if (type.equals("$$")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                } else if (type.equals("$$$")) {
                    restList.clear();
                    restList = rDB.GetRestaurants(null, null, null);
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();

        builder.show();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public void chooseForMe() {
        //connect to greg's matching algorithm.
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                createFoodtype();
                break;
            case R.id.button2:
<<<<<<< HEAD
                System.out.println("at button 2");
=======
>>>>>>> parent of 7b25cf4... working on list, view about to merge
                createPriceRange();
                break;
            case R.id.button3:
                createRatingRange();
                break;
            case R.id.button4:
                chooseForMe();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String id);
    }

}

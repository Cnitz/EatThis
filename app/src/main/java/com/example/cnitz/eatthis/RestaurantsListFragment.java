package com.example.cnitz.eatthis;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.ListAdapter;
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
public class RestaurantsListFragment extends Fragment implements AbsListView.OnItemClickListener, View.OnClickListener {

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
    private ListAdapter mAdapter;
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
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        Button b = (Button) view.findViewById(R.id.button);
        b.setOnClickListener(this);


        return view;
    }

    public void printList(ETPlace place) {
        //print the list everytime
        //put this on the list
        place.getName();


    }

    public void createFoodtype() {
        final CharSequence[] foodtypes = {"Asian", "Pizza", "Mexican"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle("Food Types");
        builder.setItems(foodtypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(foodtypes[which]);
               // mListView.setAdapter();
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
        final CharSequence[] ratingRange = {};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Rating Range");
        builder.setItems(ratingRange, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
        final CharSequence[] priceRange = {};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Price Range");
        builder.setItems(priceRange, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(priceRange[which]);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                createFoodtype();
                break;
            case R.id.button2:
                createPriceRange();
                break;
            case R.id.button3:
                createRatingRange();
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
        public void onFragmentInteraction(String id);
    }

}

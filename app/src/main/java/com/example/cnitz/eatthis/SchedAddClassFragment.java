package com.example.cnitz.eatthis;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SchedAddClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedAddClassFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Button saveButton;
    Button cancelButton;
    EditText name;
    Spinner startTime;
    Spinner endTime;
    Spinner locations;
    Spinner days;
    SchedHelper sh;
    FragmentManager fragmentManager;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
    public static SchedAddClassFragment newInstance(SchedHelper s) {
        SchedAddClassFragment fragment = new SchedAddClassFragment();
        fragment.sh = s;
        Bundle args = new Bundle();;
        fragment.setArguments(args);
        return fragment;
    }

    public SchedAddClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_sched_add_class, container, false);

        view.setBackgroundColor(Color.WHITE);

        saveButton = (Button)view.findViewById(R.id.SaveButton);
        cancelButton = (Button)view.findViewById(R.id.CancelButton);
        name = (EditText)view.findViewById(R.id.NameInput);
        startTime = (Spinner)view.findViewById(R.id.STimeInput);
        endTime = (Spinner)view.findViewById(R.id.ETimeInput);
        days = (Spinner)view.findViewById(R.id.DaysInput);
        locations = (Spinner)view.findViewById(R.id.LocationInput);

        //Intialize Spinners
        //Start Time
        String[] itemsTimes = new String[]{"7:30", "8:30", "9:30","10:30","11:30","12:30","1:30","2:30","3:30","4:30","5:30"};
        String[] locs = new String[]{"Lawson", "Armstrong", "EE","CL50","PMU","Lily"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsTimes);
        startTime.setAdapter(adapter);
        //End Time
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsTimes);
        endTime.setAdapter(adapter2);
        //Days
        String[] itemsDays = new String[]{"Monday","Tuesday","Wednesday","Thrusday","Friday","M+W+F","T+R"};
        ArrayAdapter<String> adapterDays = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsDays);
        days.setAdapter(adapterDays);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locs);
        locations.setAdapter(adapter3);


        cancelButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //name.setText("");
                        //location.setText("");
                        getActivity().findViewById(R.id.addClass).setVisibility(View.VISIBLE);
                        getActivity().findViewById(R.id.back).setVisibility(View.VISIBLE);

                        fragmentManager = getFragmentManager();
                        fragmentManager.popBackStackImmediate("addClass", 0);
                        Intent myIntent = new Intent(getActivity(), SchedList.class);
                        getActivity().startActivity(myIntent);
                    }
                }
        );

        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: Determine Required Fields

                        SchedClass sclass = new SchedClass();
                        String className = name.getText().toString();
                        if(className.equals("")){
                            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                            ad.setTitle("Error");
                            ad.setMessage("Class Name cannot be empty");
                            ad.setNeutralButton("Ok", new AlertDialog.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            ad.show();
                        }
                        else if(endTime.getSelectedItemPosition() <= startTime.getSelectedItemPosition()){
                            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                            ad.setTitle("Error");
                            ad.setMessage("Class cannot start after it ends");
                            ad.setNeutralButton("Ok", new AlertDialog.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            ad.show();
                        }
                        else {
                            getActivity().findViewById(R.id.addClass).setVisibility(View.VISIBLE);
                            getActivity().findViewById(R.id.back).setVisibility(View.VISIBLE);
                            String loc = name.getText().toString();
                            if (loc.equals(null)) className = "ARMS";
                            if (className.equals(null)) className = "noName";
                            sclass.setName(className);
                            //Toast.makeText(SchedInputFragment.this, name.getText(), Toast.LENGTH_SHORT).show();
                            //else
                            //Toast.makeText(SchedInput.this, "Name field must be populated.", Toast.LENGTH_SHORT).show();
                            sclass.setLocation(locations.getSelectedItem().toString());
                            sclass.setDays(days.getSelectedItem().toString());
                            sclass.setStartTime(startTime.getSelectedItem().toString());
                            sclass.setEndTime(endTime.getSelectedItem().toString());


                            SchedHelper rdb = new SchedHelper(getActivity());
                            rdb.addClass(rdb, sclass);


                            //name.setText("");
                            //location.setText("");

                            Toast.makeText(getActivity(), "Added to the DB!", Toast.LENGTH_SHORT).show();

                            fragmentManager = getFragmentManager();
                            fragmentManager.popBackStackImmediate("addClass", 0);
                            Intent myIntent = new Intent(getActivity(), SchedList.class);
                            getActivity().startActivity(myIntent);
                        }
                    }
                }
        );


        // Inflate the layout for this fragment
      return view; //Original Statement Here
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        public void onFragmentInteraction(Uri uri);
    }

}

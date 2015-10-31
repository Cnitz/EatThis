package com.example.cnitz.eatthis;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReviewDisplay.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewDisplay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewDisplay extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static ReviewHelper rhelp;
    private static Review review;
    private TextView name;
    private TextView price;
    private TextView menuitems;
    private TextView summary;
    private TextView rating;
    private TextView date;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReviewDisplay.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewDisplay newInstance(ReviewHelper rh, Review r) {
        ReviewDisplay fragment = new ReviewDisplay();
        Bundle args = new Bundle();
        rhelp = rh;
        review = r;
        fragment.setArguments(args);
        return fragment;
    }

    public ReviewDisplay() {
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
        View view = inflater.inflate(R.layout.fragment_review_display, container, false);

        name = (TextView) view.findViewById(R.id.nameDisp);
        price = (TextView) view.findViewById(R.id.priceDisp);
        menuitems = (TextView) view.findViewById(R.id.menuItemsDisp);
        summary = (TextView) view.findViewById(R.id.summaryDisp);
        rating = (TextView) view.findViewById(R.id.ratingDisp);
        date = (TextView) view.findViewById(R.id.dateDisp);

        name.setText(review.getName());
        price.setText(Double.toString(review.getPrice()));
        menuitems.setText(review.getMenuItems());
        summary.setText(review.getSummary());
        rating.setText(Integer.toString(review.getRating()));
        date.setText(review.getDate());

        return view;
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

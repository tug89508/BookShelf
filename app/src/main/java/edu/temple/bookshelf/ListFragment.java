package edu.temple.bookshelf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass that shows a list of items
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private static final String ARG_PARAM1 = "list";    //bundle key for fragment
    private ArrayList<HashMap<String, String>> list;    //the "bookshelf"

    // Required empty public constructor
    public ListFragment(){}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance(ArrayList<HashMap<String, String>> param1) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();

        //put the arrayList into the bundle for this fragment
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = (ArrayList<HashMap<String, String>>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;//the view being presented by this fragment

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);

        //TODO: find the list, create and assign an adapter to the list, and set an onClickListener
        ListView listView = view.findViewById(R.id.listView);
        BookListAdapter bookListAdapter = new BookListAdapter(getContext(), list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bookClickedInterface.openDetails(list.get(position));
            }
        });

        return view;
    }

    //This will be used to tell the activity which book has been pressed,
    //  so that the activity can build a fragment with the proper details
    interface bookClickedInterface{
        void openDetails(HashMap<String, String> book);
    }

}

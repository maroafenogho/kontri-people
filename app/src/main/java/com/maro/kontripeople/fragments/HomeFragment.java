package com.maro.kontripeople.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.maro.kontripeople.R;

public class HomeFragment extends Fragment {


    TextView viewPeople, title, addPerson;
    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        title = requireActivity().findViewById(R.id.titleText);
        title.setText(R.string.app_name);

        addPerson = view.findViewById(R.id.add_person);
        viewPeople = view.findViewById(R.id.view_people);


        viewPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadFragment(new PeopleFragment());
            }
        });

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AddPeopleFragment());
            }
        });

        return view;
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment)
                .addToBackStack("back").commit();
    }
}
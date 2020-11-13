package com.maro.kontripeople.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maro.kontripeople.R;
import com.maro.kontripeople.util.People;
import com.maro.kontripeople.util.RecyclerAdapter;
import com.maro.kontripeople.viewmodel.PeopleViewModel;

import java.util.ArrayList;
import java.util.List;


public class PeopleFragment extends Fragment {
    TextView title;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<People> people;
    PeopleViewModel peopleViewModel;
    ProgressBar progressBar;
    TextView mAge, mGender, mOccupation, mEmploymentStatus, mIsCitizen, mName;
    ImageView cancel;

    public PeopleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.people, container, false);

        title = requireActivity().findViewById(R.id.titleText);
        title.setText("People");

        recyclerView = view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        progressBar = view.findViewById(R.id.progressBar2);

        recyclerAdapter = new RecyclerAdapter(people, getContext());
        people = new ArrayList<>();

        loadPeople();

        recyclerAdapter.setmOnPersonListener(new RecyclerAdapter.onPersonListener() {
            @Override
            public void onNewsClick(People person) {
                String name = person.getName();
                String occupation = person.getOccupation();
                String employmentStatus = person.getEmploymentStatus();
                boolean isCitizen = person.isCitizen();
                int age = person.getAge();
                String gender = person.getGender();

                Toast.makeText(getContext(), ""+ isCitizen, Toast.LENGTH_SHORT).show();


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = View.inflate(getContext(), R.layout.alert_page, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();

                cancel = view.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                mAge = view.findViewById(R.id.age);
                mEmploymentStatus = view.findViewById(R.id.employmentStatus);
                mGender = view.findViewById(R.id.gender);
                mOccupation = view.findViewById(R.id.occupation);
                mIsCitizen = view.findViewById(R.id.citizen);
                mName = view.findViewById(R.id.name);

                mName.setText(name);
                mOccupation.setText(occupation);
                mGender.setText(gender);
                mEmploymentStatus.setText(employmentStatus);
                mAge.setText(String.valueOf(age));
                if (isCitizen==true){
                    mIsCitizen.setText("Nigerian");
                } else{
                    mIsCitizen.setText("Non-Nigerian");
                }
                Toast.makeText(getContext(), "This is "+ name, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void loadPeople(){
        peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);
        peopleViewModel.getPeople().observe(getViewLifecycleOwner(), results1 ->{
            progressBar.setVisibility(View.GONE);
            recyclerAdapter.setResultsList(results1);
            recyclerView.setAdapter(recyclerAdapter);
            if (results1.size()==0){
                Toast.makeText(getContext(), "List not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
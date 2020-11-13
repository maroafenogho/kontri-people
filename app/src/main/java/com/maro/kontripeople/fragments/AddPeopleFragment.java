package com.maro.kontripeople.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.maro.kontripeople.R;
import com.maro.kontripeople.util.ApiClient;
import com.maro.kontripeople.util.ApiInterface;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPeopleFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    RadioGroup genderGroup, employmentStatusGroup, citizenGroup;
    RadioButton employedButton, male, female, unemployed, citizenButton, nonCitizen;
    TextView age, name, occupation;
    boolean isCitizen;
    String  citezenship;
    Button submit;
    Spinner genderSpinner, citizenSpinner, employmentSpinner;
    int mAge;
    Gender selectedGender;
    Employment selectedStatus;
    ProgressBar progressBar;

    TextView title;
    public AddPeopleFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_add_person, container, false);
        title = requireActivity().findViewById(R.id.titleText);
        title.setText("Add Person");

        submit = view.findViewById(R.id.submit);
        age = view.findViewById(R.id.age_edittext);
        name = view.findViewById(R.id.name_edittext);
        occupation = view.findViewById(R.id.occupation_edittext);
        progressBar = view.findViewById(R.id.submit_progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        genderSpinner = view.findViewById(R.id.gender_spinner);
        employmentSpinner = view.findViewById(R.id.employment_spinner);
        citizenSpinner = view.findViewById(R.id.citizen_spinner);

        ArrayList<Gender> spinnerArray = new ArrayList<>();
        ArrayList<Boolean> citizenArray = new ArrayList<>();
        ArrayList<Employment> employmentArrayList = new ArrayList<>();

        citizenArray.add(true);
        citizenArray.add(false);

        spinnerArray.add(Gender.male);
        spinnerArray.add(Gender.female);

        employmentArrayList.add(Employment.employed);
        employmentArrayList.add(Employment.unemployed);
        employmentArrayList.add(Employment.student);

        ArrayAdapter<Gender> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        ArrayAdapter<Employment> employmentArrayAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, employmentArrayList);
        employmentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employmentSpinner.setAdapter(employmentArrayAdapter);

        ArrayAdapter<Boolean> citizenAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, citizenArray);
        citizenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citizenSpinner.setAdapter(citizenAdapter);

        employmentSpinner.setOnItemSelectedListener(this);
        genderSpinner.setOnItemSelectedListener(this);
        citizenSpinner.setOnItemSelectedListener(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInfo();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        return view;

    }

    private void submitInfo() {
        ApiInterface apiInterface;
        apiInterface = ApiClient.submitPerson().create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.createPerson(name.getText().toString(), selectedGender,
                occupation.getText().toString(),Integer.parseInt(age.getText().toString()),  selectedStatus,
                isCitizen);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    Toast.makeText(getContext(), "successfully Submitted", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), ""+ t.toString(), Toast.LENGTH_LONG).show();
                Log.i("sadly", "onFailure: " + t.toString() );
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedGender = (Gender) genderSpinner.getSelectedItem();
        isCitizen = (boolean) citizenSpinner.getSelectedItem();
        selectedStatus = (Employment) employmentSpinner.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public enum Gender{
        male,
        female
    }

    public enum Employment{
        employed,
        unemployed,
        student
    }
}
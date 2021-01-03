package com.maro.kontripeople.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.maro.kontripeople.R;
import com.maro.kontripeople.util.ApiClient;
import com.maro.kontripeople.util.ApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText name, password, email;
    Button button;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
         password = findViewById(R.id.password);
         email = findViewById(R.id.email);
         button = findViewById(R.id.button);
         progressBar = findViewById(R.id.progressBar);
         progressBar.setVisibility(View.INVISIBLE);

         button.setOnClickListener(v -> {
             register();
             progressBar.setVisibility(View.VISIBLE);
         });

    }
    private void register() {
        ApiInterface apiInterface;
        apiInterface = ApiClient.auth().create(ApiInterface.class);

        Call<ResponseBody> myCall = apiInterface.signUp(name.getText().toString(), email.getText().toString(), password.getText().toString());
        myCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200){
                    Toast.makeText(SignUpActivity.this, "User successfully created", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "User creation failed" + t.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
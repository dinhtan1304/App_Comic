package com.example.comicapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.comicapp.API.APIServices;
import com.example.comicapp.R;
import com.example.comicapp.object.Account;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRes;
    private EditText edtName, edtUser, edtPass;
    private RelativeLayout relativeLayout;
    private LinearLayout progress;
    private List<Account> listAcc;
    private String userName;
    private String name;
    private String password;

    private void bindingView() {
        btnRes = findViewById(R.id.btnRegister);
        edtName = findViewById(R.id.name);
        edtUser = findViewById(R.id.usernames);
        edtPass = findViewById(R.id.passwords);
        relativeLayout = findViewById(R.id.relativeLayout);
        progress = findViewById(R.id.show_progress);
    }

    private void bindingAction() {
        btnRes.setOnClickListener(this::onClickRegister);
    }

    private void onClickRegister(View view) {
        performSignUp();
        progress.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindingView();
        bindingAction();
    }

    private void performSignUp() {
        userName = edtUser.getText().toString();
        password = edtPass.getText().toString();
        name = edtName.getText().toString();
        if (name.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Account newAccount = new Account();
        newAccount.setName(name);
        newAccount.setUserName(userName);
        newAccount.setPassword(password);
        APIServices.getAccountApiServies().getAllAccount().enqueue(new Callback<ArrayList<Account>>() {
            @Override
            public void onResponse(Call<ArrayList<Account>> call, Response<ArrayList<Account>> response) {
                listAcc = response.body();
                boolean isUsernameTaken = false;
                for (Account acc : listAcc) {
                    if (acc != null) {
                        if (acc.getUserName().equals(userName)) {
                            isUsernameTaken = true;
                            break;
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "null", Toast.LENGTH_SHORT).show();
                    }
                }
                if (isUsernameTaken) {
                    displayUserInfo("Username is already taken");
                } else {
                    Toast.makeText(RegisterActivity.this, "ok", Toast.LENGTH_SHORT).show();
                    APIServices.getAccountApiServies().createNewPhoto(newAccount).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            if (response.isSuccessful()) {
                                displayUserInfo("Registration successful!");
                                onBackPressed();
                                finish();
                            } else {
                                displayUserInfo("Registration Fail!");
                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Account>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Null", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayUserInfo(String mess) {
        Snackbar.make(relativeLayout, mess, Snackbar.LENGTH_SHORT).show();
        edtPass.setText("");
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
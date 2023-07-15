package com.example.comicapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comicapp.API.APIServices;
import com.example.comicapp.R;
import com.example.comicapp.object.Account;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegis;
    private EditText edtUserName, edtPassword;
    private String userName, password;
    private List<Account> listAcc;

    private void bindingView() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegis = findViewById(R.id.btnRegis);
        edtUserName = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(this::onClickLogin);
        btnRegis.setOnClickListener(this::onClickRegis);
    }

    private void onClickRegis(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void onClickLogin(View view) {
        userName = edtUserName.getText().toString();
        password = edtPassword.getText().toString();

        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        APIServices.getAccountApiServies().getAllAccount().enqueue(new Callback<ArrayList<Account>>() {
            @Override
            public void onResponse(Call<ArrayList<Account>> call, Response<ArrayList<Account>> response) {
                listAcc = response.body();
                for (Account acc : listAcc) {
                    if (acc != null) {
                        if (acc.getUserName().equals(userName) && acc.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "UserName or Password is wrong. Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Account>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Fail!", Toast.LENGTH_SHORT).show();
                edtUserName.setText("");
                edtPassword.setText("");
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindingView();
        bindingAction();
    }

}
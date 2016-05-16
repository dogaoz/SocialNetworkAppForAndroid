package com.dogaozkaraca.izunetwork;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.API.APIRequest;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.auth.AUTH;

public class AuthenticationActivity extends Activity  {
    int state = 0;
    RelativeLayout login_layout;
    RelativeLayout signup_layout;
    RelativeLayout signup_layout2;
    RelativeLayout signup_layout3;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_fragment);
        client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        login_layout= (RelativeLayout) findViewById(R.id.login_sublayout);
        signup_layout = (RelativeLayout) findViewById(R.id.signup_sublayout);
        signup_layout2 = (RelativeLayout) findViewById(R.id.signup_sublayout2);
        signup_layout3 = (RelativeLayout) findViewById(R.id.signup_sublayout3);

        TextView signup_text = (TextView) findViewById(R.id.textViewSignup);
        TextView login_text = (TextView) findViewById(R.id.textViewLogin);
        Button next = (Button) findViewById(R.id.buttonNext);
        Button next2 = (Button) findViewById(R.id.buttonNext2);
        Button signup = (Button) findViewById(R.id.buttonSignup);
        final Button login = (Button) findViewById(R.id.buttonLogin);

        final EditText emailLogin = (EditText) findViewById(R.id.editTextEmailLogin);
        final EditText passwordLogin = (EditText) findViewById(R.id.editTextPasswordLogin);
        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeViewTo(1);
            }
        });

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewTo(0);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewTo(2);

            }
        });
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeViewTo(3);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Signup

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login
                APIRequest.login(emailLogin.getText().toString(),passwordLogin.getText().toString(),AuthenticationActivity.this);
            }
        });

    }

    private void changeViewTo(int to) {
        state = to;

        if(to == 0)
        {
            login_layout.setVisibility(View.VISIBLE);
            signup_layout.setVisibility(View.GONE);
        }
        else if(to == 1)
        {
            login_layout.setVisibility(View.GONE);
            signup_layout.setVisibility(View.VISIBLE);
        }
        else if(to == 2)
        {
            signup_layout.setVisibility(View.GONE);
            signup_layout2.setVisibility(View.VISIBLE);
        }
        else if(to == 3)
        {
            signup_layout2.setVisibility(View.GONE);
            signup_layout3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
         if(state == 1)
        {
            login_layout.setVisibility(View.VISIBLE);
            signup_layout.setVisibility(View.GONE);
        }
        else if(state == 2)
        {
            signup_layout2.setVisibility(View.GONE);
            signup_layout.setVisibility(View.VISIBLE);
        }
        else if(state == 3)
        {
            signup_layout3.setVisibility(View.GONE);
            signup_layout2.setVisibility(View.VISIBLE);
        }

        if (state !=0)
        {
            state = state-1;
        }

    }




}

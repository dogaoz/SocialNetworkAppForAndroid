package com.dogaozkaraca.izunetwork.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dogaozkaraca.izunetwork.API.APIRequest;
import com.dogaozkaraca.izunetwork.AuthenticationActivity;
import com.dogaozkaraca.izunetwork.MainActivity;
import com.dogaozkaraca.izunetwork.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Izu Network
 * Created by doga on 24/04/16.
 */
public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.settings_fragment, container, false);

        //Settings

        Button logoutBtn = (Button) v.findViewById(R.id.buttonLogout);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIRequest.logout(v.getContext());
            }
        });


        return v;
    }



}

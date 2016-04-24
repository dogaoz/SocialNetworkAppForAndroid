package com.dogaozkaraca.izunetwork.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dogaozkaraca.izunetwork.CreateAccountActivity;
import com.dogaozkaraca.izunetwork.R;

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

        Button createAcc = (Button) v.findViewById(R.id.createAccBtn);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(container.getContext(), CreateAccountActivity.class);
                container.getContext().startActivity(i);
            }
        });




        return v;
    }

}

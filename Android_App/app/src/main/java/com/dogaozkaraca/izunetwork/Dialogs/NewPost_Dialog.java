package com.dogaozkaraca.izunetwork.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.API.APIRequest;
import com.dogaozkaraca.izunetwork.MainActivity;
import com.dogaozkaraca.izunetwork.R;

/**
 * Izu Reader
 * Created by doga on 24/04/16.
 */
public class NewPost_Dialog extends Dialog {

    public Activity activity;

    public NewPost_Dialog(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.newpost_dialog);
        TextView fullname = (TextView) findViewById(R.id.fullname);
        fullname.setText(MainActivity.currentUserName + " " + MainActivity.currentUserLastName);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPost_Dialog.this.dismiss();
            }
        });
        final EditText postText = (EditText) findViewById(R.id.editTextNewPost);
        Button shareButton = (Button) findViewById(R.id.buttonShare);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIRequest.newPost(MainActivity.currentUserID,0,postText.getText().toString(),getContext(),NewPost_Dialog.this);

            }
        });


    }


}

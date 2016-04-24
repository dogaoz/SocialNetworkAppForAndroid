package com.dogaozkaraca.izunetwork.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dogaozkaraca.izunetwork.R;

/**
 * Izu Reader
 * Created by doga on 24/04/16.
 */
public class CreateGroup_Dialog extends Dialog {

    public Activity activity;

    public CreateGroup_Dialog(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creategroup_dialog);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateGroup_Dialog.this.dismiss();
            }
        });

    }


}

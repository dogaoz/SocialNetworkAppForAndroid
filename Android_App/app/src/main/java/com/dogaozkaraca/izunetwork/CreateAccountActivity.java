package com.dogaozkaraca.izunetwork;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.dogaozkaraca.izunetwork.Dialogs.CreateGroup_Dialog;
import com.dogaozkaraca.izunetwork.Dialogs.NewPost_Dialog;
import com.dogaozkaraca.izunetwork.Fragments.AdminFragment;
import com.dogaozkaraca.izunetwork.Fragments.AnasayfaFragment;
import com.dogaozkaraca.izunetwork.Fragments.FriendsFragment;
import com.dogaozkaraca.izunetwork.Fragments.GroupsFragment;
import com.dogaozkaraca.izunetwork.Fragments.NotificationsFragment;
import com.dogaozkaraca.izunetwork.Fragments.ProfileEditFragment;
import com.dogaozkaraca.izunetwork.Fragments.ProfileFragment;
import com.dogaozkaraca.izunetwork.Fragments.SettingsFragment;

public class CreateAccountActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount_fragment);



    }

    @Override
    public void onBackPressed() {

    }


}

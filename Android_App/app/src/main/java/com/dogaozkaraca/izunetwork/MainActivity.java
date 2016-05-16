package com.dogaozkaraca.izunetwork;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.API.APIRequest;
import com.dogaozkaraca.izunetwork.Dialogs.CreateGroup_Dialog;
import com.dogaozkaraca.izunetwork.Dialogs.NewPost_Dialog;
import com.dogaozkaraca.izunetwork.Fragments.AdminFragment;
import com.dogaozkaraca.izunetwork.Fragments.AnasayfaFragment;
import com.dogaozkaraca.izunetwork.Fragments.FriendsFragment;
import com.dogaozkaraca.izunetwork.Fragments.GroupsFragment;
import com.dogaozkaraca.izunetwork.Fragments.NotificationsFragment;
import com.dogaozkaraca.izunetwork.Fragments.PostFragment;
import com.dogaozkaraca.izunetwork.Fragments.ProfileEditFragment;
import com.dogaozkaraca.izunetwork.Fragments.ProfileFragment;
import com.dogaozkaraca.izunetwork.Fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    NotificationsFragment notifications_frag;
    AnasayfaFragment anasayfa_frag;
    FriendsFragment friends_frag;
    GroupsFragment groups_frag;
    SettingsFragment settings_frag;
    ProfileFragment profile_frag;
    AdminFragment admin_frag;
    ProfileEditFragment profileedit_frag;
    PostFragment post_frag;

    MenuItem actionItem;
    FloatingActionButton fab;
    public static int currentUserID;
    public static String currentUserName;
    public static String currentUserLastName;

    public static boolean loggedin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);

        currentUserName = prefs.getString("userName","");
        currentUserLastName = prefs.getString("userLastName","");

        if(prefs.getInt("userID",-99) != -99)
        {
            loggedin = true;
        }

        if(loggedin == false)
        {
            Intent i = new Intent(MainActivity.this, AuthenticationActivity.class);
            startActivity(i);
            finish();
        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Ana Sayfa");
        fragmentManager = getFragmentManager();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                if (getSupportActionBar().getTitle().equals("Gruplar"))
                {
                    CreateGroup_Dialog dialog = new CreateGroup_Dialog(MainActivity.this);
                    dialog.show();
                }
                else if (getSupportActionBar().getTitle().equals("Arkadaşlar"))
                {

                }
                else
                {
                    NewPost_Dialog dialog = new NewPost_Dialog(MainActivity.this);
                    dialog.show();
                }
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        currentUserID = 1;
        //Define Fragments
        notifications_frag = new NotificationsFragment();
        anasayfa_frag = new AnasayfaFragment();
        friends_frag = new FriendsFragment();
        groups_frag = new GroupsFragment();
        settings_frag = new SettingsFragment();
        profile_frag = new ProfileFragment();
        admin_frag = new AdminFragment();
        profileedit_frag = new ProfileEditFragment();
        post_frag = new PostFragment();
        // update the main content by replacing fragments
        fragmentManager.beginTransaction()
                .replace(R.id.viewHolderContainer, anasayfa_frag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();

        LinearLayout profile_header = (LinearLayout) navigationView.getHeaderView(0).findViewById(R.id.profile_header);
        profile_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.getSupportActionBar().setTitle("Profilim");
                actionItem.setIcon(R.drawable.ic_mode_edit_white_48dp);
                drawer.closeDrawers();
                fragmentManager.beginTransaction()
                        .replace(R.id.viewHolderContainer, profile_frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
            }
        });

        TextView header_fullname = (TextView) navigationView.getHeaderView(0).findViewById(R.id.fullname_header);
        header_fullname.setText(currentUserName + " "  + currentUserLastName);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        actionItem = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notifications) {
            if(this.getSupportActionBar().getTitle().equals("Bildirimler"))
            {
                this.getSupportActionBar().setTitle("Ana Sayfa");
                item.setIcon(R.drawable.ic_notifications_white_48dp);
                // update the main content by replacing fragments
                fragmentManager.beginTransaction()
                        .replace(R.id.viewHolderContainer, anasayfa_frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
                fab.setVisibility(View.VISIBLE);
                fab.setImageResource(R.drawable.ic_add_white_48dp);


            }
            else if(this.getSupportActionBar().getTitle().equals("Profilim"))
            {
                //Launch Profile Editor
                this.getSupportActionBar().setTitle("Profilimi Düzenle");
                item.setIcon(R.drawable.ic_done_white_48dp);
                fragmentManager.beginTransaction()
                        .replace(R.id.viewHolderContainer, profileedit_frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
                fab.setVisibility(View.GONE);


            }
            else if(this.getSupportActionBar().getTitle().equals("Profilimi Düzenle"))
            {
                //Launch Profile Editor
                item.setIcon(R.drawable.ic_mode_edit_white_48dp);
                this.getSupportActionBar().setTitle("Profilim");
                fragmentManager.beginTransaction()
                        .replace(R.id.viewHolderContainer, profile_frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
                fab.setVisibility(View.VISIBLE);

            }
            else
            {
                this.getSupportActionBar().setTitle("Bildirimler");
                item.setIcon(R.drawable.ic_home_white_48dp);
                // update the main content by replacing fragments
                fragmentManager.beginTransaction()
                        .replace(R.id.viewHolderContainer, notifications_frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
                fab.setVisibility(View.GONE);

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fab.setVisibility(View.VISIBLE);
        actionItem.setIcon(R.drawable.ic_notifications_white_48dp);
        if (id == R.id.home)
        {
            this.getSupportActionBar().setTitle("Ana Sayfa");
            // update the main content by replacing fragments
            fragmentManager.beginTransaction()
                    .replace(R.id.viewHolderContainer, anasayfa_frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
            fab.setVisibility(View.VISIBLE);
            fab.setImageResource(R.drawable.ic_add_white_48dp);
        }
        else if (id == R.id.friends)
        {
            this.getSupportActionBar().setTitle("Arkadaşlar");
            // update the main content by replacing fragments
            fragmentManager.beginTransaction()
                    .replace(R.id.viewHolderContainer, friends_frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
            fab.setVisibility(View.VISIBLE);
            fab.setImageResource(R.drawable.ic_person_add_white_48dp);

        }
        else if (id == R.id.groups)
        {
            this.getSupportActionBar().setTitle("Gruplar");
            // update the main content by replacing fragments
            fragmentManager.beginTransaction()
                    .replace(R.id.viewHolderContainer, groups_frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
            fab.setVisibility(View.VISIBLE);
            fab.setImageResource(R.drawable.ic_add_white_48dp);
        }
        else if (id == R.id.settings)
        {
            this.getSupportActionBar().setTitle("Ayarlar");
            // update the main content by replacing fragments
            fragmentManager.beginTransaction()
                    .replace(R.id.viewHolderContainer, settings_frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
            fab.setVisibility(View.GONE);
        }
        else if (id == R.id.admin_panel)
        {
            this.getSupportActionBar().setTitle("Admin Kontrol Paneli");
            // update the main content by replacing fragments
            fragmentManager.beginTransaction()
                    .replace(R.id.viewHolderContainer, admin_frag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
            fab.setVisibility(View.GONE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

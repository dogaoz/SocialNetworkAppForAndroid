package com.dogaozkaraca.izunetwork.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.AdapterItems.FriendItem;
import com.dogaozkaraca.izunetwork.AdapterItems.GroupItem;
import com.dogaozkaraca.izunetwork.Adapters.FriendsAdapter;
import com.dogaozkaraca.izunetwork.Adapters.GroupsAdapter;
import com.dogaozkaraca.izunetwork.R;

import java.util.ArrayList;

/**
 * Izu Network
 * Created by doga on 24/04/16.
 */
public class GroupsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.groups_fragment, container, false);
        TextView noGroups = (TextView) v.findViewById(R.id.nogroupstext);
        //Load Groups

        // RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<GroupItem> feed = new ArrayList<>();
        feed.add(new GroupItem(1,3,"Yazılım Mühendisleri Grubu","groupImage.com/urlblabla"));
        feed.add(new GroupItem(5,1,"Bilgisayar Mühendisleri Grubu","groupImage.com/urlblabla"));
        feed.add(new GroupItem(18,2,"Java Grubu","groupImage.com/urlblabla"));

        if (feed.isEmpty())
        {
            noGroups.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
        else
        {
            noGroups.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);


        }
        mAdapter = new GroupsAdapter(feed);
        mRecyclerView.setAdapter(mAdapter);



        return v;
    }

}

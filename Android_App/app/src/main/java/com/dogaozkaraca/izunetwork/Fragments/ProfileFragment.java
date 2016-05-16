package com.dogaozkaraca.izunetwork.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.API.APIRequest;
import com.dogaozkaraca.izunetwork.AdapterItems.FeedItem;
import com.dogaozkaraca.izunetwork.Adapters.FeedAdapter;
import com.dogaozkaraca.izunetwork.MainActivity;
import com.dogaozkaraca.izunetwork.R;

import java.util.ArrayList;

/**
 * Izu Network
 * Created by doga on 24/04/16.
 */
public class ProfileFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        //Load Profile Posts

        // RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final SwipeRefreshLayout swp = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayoutProfile);
        TextView fullnameTV = (TextView) v.findViewById(R.id.textViewFullName);
        fullnameTV.setText(MainActivity.currentUserName+" " + MainActivity.currentUserLastName);
        APIRequest.loadMyPosts(MainActivity.currentUserID,container.getContext(),mRecyclerView,swp);
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                APIRequest.loadMyPosts(MainActivity.currentUserID,container.getContext(),mRecyclerView,swp);

            }
        });





        return v;
    }

}

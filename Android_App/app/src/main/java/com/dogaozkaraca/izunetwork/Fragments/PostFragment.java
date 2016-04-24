package com.dogaozkaraca.izunetwork.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dogaozkaraca.izunetwork.AdapterItems.CommentItem;
import com.dogaozkaraca.izunetwork.AdapterItems.FeedItem;
import com.dogaozkaraca.izunetwork.AdapterItems.NotificationItem;
import com.dogaozkaraca.izunetwork.Adapters.CommentsAdapter;
import com.dogaozkaraca.izunetwork.Adapters.FeedAdapter;
import com.dogaozkaraca.izunetwork.R;

import java.util.ArrayList;

/**
 * Izu Reader
 * Created by doga on 24/04/16.
 */
public class PostFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.post_fragment, container, false);

        //Load Post and its comment

        // RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<CommentItem> feed = new ArrayList<>();
        feed.add(new CommentItem(2,"Doğa","Özkaraca","imageurl.com","bir gönderini beğendi.",null));
        mAdapter = new CommentsAdapter(feed);
        mRecyclerView.setAdapter(mAdapter);



        return v;
    }

}
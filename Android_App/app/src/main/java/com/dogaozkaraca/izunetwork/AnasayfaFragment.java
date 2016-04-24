package com.dogaozkaraca.izunetwork;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Fluxx Reader
 * Created by doga on 24/04/16.
 */
public class AnasayfaFragment  extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.anasayfa_fragment, container, false);

        //Load Anasayfa

        // RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<FeedItem> feed = new ArrayList<>();
        feed.add(new FeedItem(1,"Adı","Soyadı","Image URL","Gönderi 1 ",null,null,150,15,3));
        feed.add(new FeedItem(2,"Adı","Soyadı","Image URL","Gönderi 2 ",null,null,18,65,45));
        feed.add(new FeedItem(3,"Adı","Soyadı","Image URL","Gönderi 3 ",null,null,45,2,0));
        feed.add(new FeedItem(4,"Adı","Soyadı","Image URL","Gönderi 4 ",null,null,120,4,22));
        mAdapter = new FeedAdapter(feed);
        mRecyclerView.setAdapter(mAdapter);



        return v;
    }

}
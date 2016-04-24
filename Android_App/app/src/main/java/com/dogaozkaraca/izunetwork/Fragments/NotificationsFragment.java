package com.dogaozkaraca.izunetwork.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.AdapterItems.GroupItem;
import com.dogaozkaraca.izunetwork.AdapterItems.NotificationItem;
import com.dogaozkaraca.izunetwork.Adapters.GroupsAdapter;
import com.dogaozkaraca.izunetwork.Adapters.NotificationsAdapter;
import com.dogaozkaraca.izunetwork.R;

import java.util.ArrayList;

/**
 * Izu Network
 * Created by doga on 24/04/16.
 */
public class NotificationsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.notifications_fragment, container, false);

        TextView noNotifications = (TextView) v.findViewById(R.id.nonotificationtext);
        //Load Notifications

        // RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(inflater.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<NotificationItem> feed = new ArrayList<>();
        feed.add(new NotificationItem(2,"Doğa","Özkaraca","imageurl.com","bir gönderini beğendi.",null));
        feed.add(new NotificationItem(3,"Veli","Toprak","imageurl.com","bir gönderine yorum yaptı.",null));

        if (feed.isEmpty())
        {
            noNotifications.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
        else
        {
            noNotifications.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);


        }
        mAdapter = new NotificationsAdapter(feed);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

}

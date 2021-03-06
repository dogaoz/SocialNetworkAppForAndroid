package com.dogaozkaraca.izunetwork.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.AdapterItems.CommentItem;
import com.dogaozkaraca.izunetwork.AdapterItems.NotificationItem;
import com.dogaozkaraca.izunetwork.R;

import java.util.ArrayList;

/**
 * Izu Network
 * Created by doga on 23/04/16.
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private ArrayList<CommentItem> mFeed;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        protected ImageView userPic;
        protected TextView notificationDetail;
        protected TextView notificationTime;
        public ViewHolder(View view) {
            super(view);
            this.userPic = (ImageView) view.findViewById(R.id.userPic);
            this.notificationDetail = (TextView) view.findViewById(R.id.notificationDetail);
            this.notificationTime = (TextView) view.findViewById(R.id.notification_time);


        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CommentsAdapter(ArrayList<CommentItem> myDataset) {
        mFeed = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.notificationDetail.setText(mFeed.get(position).getNotificationDetail());
        //holder.notificationTime.setText(mFeed.get(position).getCalculatedTimeTillNow());
        // set userPic with picasso library
        // holder.userPic




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFeed.size();
    }
}
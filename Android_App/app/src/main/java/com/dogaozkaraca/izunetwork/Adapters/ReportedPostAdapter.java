package com.dogaozkaraca.izunetwork.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.AdapterItems.ReportedPostItem;
import com.dogaozkaraca.izunetwork.R;

import java.util.ArrayList;

/**
 * Izu Network
 * Created by doga on 23/04/16.
 */
public class ReportedPostAdapter extends RecyclerView.Adapter<ReportedPostAdapter.ViewHolder> {
    private ArrayList<ReportedPostItem> mFeed;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        protected TextView reportedPostDetail;
        protected Button removePost;
        public ViewHolder(View view) {
            super(view);
            this.reportedPostDetail = (TextView) view.findViewById(R.id.reportDetail);
            this.removePost = (Button) view.findViewById(R.id.removePost);


        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReportedPostAdapter(ArrayList<ReportedPostItem> myDataset) {
        mFeed = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReportedPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reportedpost_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.fullname.setText("");
        // set userPic with picasso library
        // holder.userPic

        holder.removePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Confirmation Dialog to remove friend.
            }
        });



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFeed.size();
    }
}
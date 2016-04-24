package com.dogaozkaraca.izunetwork.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.AdapterItems.FeedItem;
import com.dogaozkaraca.izunetwork.R;

import java.util.ArrayList;

/**
 * Izu Network
 * Created by doga on 23/04/16.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private ArrayList<FeedItem> mFeed;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        protected TextView content;
        protected TextView fullname;
        protected TextView postedTime;
        protected TextView likeCount;
        protected TextView dislikeCount;
        protected TextView commentCount;
        protected ImageView userPic;
        protected ImageView like;
        protected ImageView dislike;
        protected ImageView comment;

        public ViewHolder(View view) {
            super(view);
            this.content = (TextView) view.findViewById(R.id.post_content);
            this.fullname = (TextView) view.findViewById(R.id.fullname);
            this.postedTime = (TextView) view.findViewById(R.id.postedTime);
            this.likeCount = (TextView) view.findViewById(R.id.like_count);
            this.dislikeCount = (TextView) view.findViewById(R.id.dislike_count);
            this.commentCount = (TextView) view.findViewById(R.id.comment_count);
            this.userPic = (ImageView) view.findViewById(R.id.userPic);
            this.like = (ImageView) view.findViewById(R.id.like);
            this.dislike = (ImageView) view.findViewById(R.id.dislike);
            this.comment = (ImageView) view.findViewById(R.id.comment);

        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FeedAdapter(ArrayList<FeedItem> myDataset) {
        mFeed = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.fullname.setText(mFeed.get(position).getName() + " " +  mFeed.get(position).getLastName());
        holder.postedTime.setText(mFeed.get(position).getPostedTime());
        holder.content.setText(mFeed.get(position).getContent());

        holder.like.setColorFilter(Color.argb(170, 38, 38, 255));
        holder.dislike.setColorFilter(Color.argb(170, 38, 38, 255));
        holder.comment.setColorFilter(Color.argb(170, 38, 38, 255));
        holder.likeCount.setText(mFeed.get(position).getLikeCount() + "");
        holder.dislikeCount.setText(mFeed.get(position).getDislikeCount() + "");
        holder.commentCount.setText(mFeed.get(position).getCommentCount() + "");



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFeed.size();
    }
}
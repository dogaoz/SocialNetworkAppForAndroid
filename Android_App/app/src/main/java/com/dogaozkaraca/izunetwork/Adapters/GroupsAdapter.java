package com.dogaozkaraca.izunetwork.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogaozkaraca.izunetwork.AdapterItems.FriendItem;
import com.dogaozkaraca.izunetwork.AdapterItems.GroupItem;
import com.dogaozkaraca.izunetwork.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Izu Network
 * Created by doga on 23/04/16.
 */
public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
    private ArrayList<GroupItem> mFeed;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        protected ImageView groupPic;
        protected TextView fullname;
        protected Button manage;
        protected TextView adminOfGroupText;
        public ViewHolder(View view) {
            super(view);
            this.groupPic = (ImageView) view.findViewById(R.id.userPic);
            this.fullname = (TextView) view.findViewById(R.id.fullname);
            this.manage = (Button) view.findViewById(R.id.manage);
            this.adminOfGroupText = (TextView) view.findViewById(R.id.adminOfGroupText);

        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GroupsAdapter(ArrayList<GroupItem> myDataset) {
        mFeed = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.fullname.setText(mFeed.get(position).getGroupName());
        // set userPic with picasso library
        // holder.userPic

        if (mFeed.get(position).isCurrentUserCreator()) {

            holder.manage.setVisibility(View.VISIBLE);
            holder.adminOfGroupText.setVisibility(View.VISIBLE);
            holder.manage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Redirect to manage group
                }
            });
        }
        else
        {
            holder.manage.setVisibility(View.GONE);
            holder.adminOfGroupText.setVisibility(View.GONE);

        }



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFeed.size();
    }
}
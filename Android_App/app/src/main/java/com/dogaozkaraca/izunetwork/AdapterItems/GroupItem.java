package com.dogaozkaraca.izunetwork.AdapterItems;

import com.dogaozkaraca.izunetwork.MainActivity;

/**
 * IZU Network
 * Created by doga on 23/04/16.
 */
public class GroupItem {
    int groupID;
    String groupName;
    int creatorUserID;
    String GprofileImageURL;

    public GroupItem(int fgroupID,int fcreatorUserID, String fgroupName, String fprofileImageURL)
    {
        groupID = fgroupID;
        groupName = fgroupName;
        creatorUserID = fcreatorUserID;
        GprofileImageURL = fprofileImageURL;
    }

    public int getGroupID() {return groupID;}
    public String getGroupName() {return groupName;}
    public String getGroupProfileImageURL()
    {
        return GprofileImageURL;
    }
    public Boolean isCurrentUserCreator()
    {
        if (MainActivity.currentUserID == creatorUserID)
            return true;
        else
            return false;

    }


}

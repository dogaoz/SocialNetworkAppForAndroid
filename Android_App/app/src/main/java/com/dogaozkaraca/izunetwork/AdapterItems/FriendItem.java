package com.dogaozkaraca.izunetwork.AdapterItems;

import java.util.Date;

/**
 * IZU Network
 * Created by doga on 23/04/16.
 */
public class FriendItem {
    int userID;
    String name;
    String lastName;
    String profileImageURL;

    public FriendItem(int fuserID, String fname, String flastName, String fprofileImageURL)
    {
        userID = fuserID;
        name = fname;
        lastName = flastName;
        profileImageURL = fprofileImageURL;
    }

    public int getUserID() {return userID;}
    public String getName()
    {
        return name;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getProfileImageURL()
    {
        return profileImageURL;
    }


}

package com.dogaozkaraca.izunetwork.AdapterItems;

import java.util.Date;

/**
 * IZU Network
 * Created by doga on 23/04/16.
 */
public class NotificationItem {
    int postID;
    String profileImageURL;
    String notificationDetail;
    String calculatedTimeTillNow;

    public NotificationItem(int fpostID, String interactionByUserName, String interactionByUserLastName,
                            String interactionByUserProfileImageURL, String interactionDesc, Date notificationPostedTime)
    {
        postID = fpostID;
        notificationDetail = interactionByUserName + " " + interactionByUserLastName + " " + interactionDesc;
        profileImageURL = interactionByUserProfileImageURL; // generated while creating..
        calculatedTimeTillNow = "52 dakika önce."; //TO-DO: Calculate time
    }

    public int getPostID() {return postID;}
    public String getProfileImageURL()
    {
        return profileImageURL;
    }
    public String getNotificationDetail() { return notificationDetail;}


    public String getCalculatedTimeTillNow() {
        return calculatedTimeTillNow;
    }
}

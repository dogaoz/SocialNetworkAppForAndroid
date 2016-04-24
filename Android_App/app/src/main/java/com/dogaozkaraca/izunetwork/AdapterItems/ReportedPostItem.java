package com.dogaozkaraca.izunetwork.AdapterItems;

/**
 * IZU Network
 * Created by doga on 23/04/16.
 */
public class ReportedPostItem {
    int userIDreported;
    String reportDetail;

    public ReportedPostItem(int fuserIDreported, String freportDetail)
    {
        userIDreported = fuserIDreported;
        reportDetail = freportDetail;
    }

    public int getUserIDwhoisreported() {return userIDreported;}
    public String getReportDetail()
    {
        return reportDetail;
    }


}

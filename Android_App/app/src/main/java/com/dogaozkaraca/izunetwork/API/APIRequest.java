package com.dogaozkaraca.izunetwork.API;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.dogaozkaraca.izunetwork.AdapterItems.FeedItem;
import com.dogaozkaraca.izunetwork.Adapters.FeedAdapter;
import com.dogaozkaraca.izunetwork.AuthenticationActivity;
import com.dogaozkaraca.izunetwork.Dialogs.NewPost_Dialog;
import com.dogaozkaraca.izunetwork.MainActivity;
import com.dogaozkaraca.izunetwork.Utils.timeOps;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Doga Oz on 5/15/2016.
 */
public class APIRequest {

    public static int login(String email, String password, final Context ct)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ct);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("functionname","login");
        params.put("email", email);
        params.put("password", password);
        final ProgressDialog[] progress = new ProgressDialog[1];
        client.post("http://www.dogaozkaraca.com/izu_network/API.php",params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progress[0] = ProgressDialog.show(ct, "Please wait...",
                        "Signing in", true);

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject object)
            {
                Log.w("izuNetwork", object.toString());
                try {
                    JSONArray resultArray = (JSONArray) object.get("result");
                    String result = (String) resultArray.get(0);
                    Log.w("izuNetwork",result);
                    if (result.equalsIgnoreCase("API Request is empty!"))
                    {
                        //Logged in already!

                        APIRequest.logout(ct);
                        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ct);
                        alertDialogBuilder.setTitle("Hata!");
                        alertDialogBuilder.setMessage("Lütfen tekrar giriş yapın!");
                        alertDialogBuilder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialogBuilder.show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progress[0].dismiss();

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                // called when response HTTP status is "200 OK"
                Log.w("izuNetwork", array.toString());

                try {
                    JSONObject user = array.getJSONObject(0);
                    String userID = (String) user.get("userID");
                    String userName = (String) user.get("userName");
                    String userLastName = (String) user.get("userLastName");
                    String userEmail = (String) user.get("userEmail");
                    String userBirthDate = (String) user.get("userBirthDate");

                    Log.w("izuNetwork","userID: "+userID);
                    Log.w("izuNetwork","userName: "+userName);
                    Log.w("izuNetwork","userLastName: "+userLastName);
                    Log.w("izuNetwork","userEmail: "+userEmail);
                    Log.w("izuNetwork","userBirthdate: "+userBirthDate);

                    SharedPreferences.Editor editor = ct.getSharedPreferences("user", ct.MODE_PRIVATE).edit();
                    editor.putInt("userID", Integer.parseInt(userID));
                    editor.putString("userName", userName);
                    editor.putString("userLastName", userLastName);
                    editor.putString("userEmail", userEmail);
                    editor.putString("userBirthdate", userBirthDate);
                    editor.commit();
                    Intent i = new Intent(ct,MainActivity.class);
                    ct.startActivity(i);
                    //Logged in show homepage!!

                } catch (JSONException e) {
                    //Then access denied is returned.
                    // e.printStackTrace();
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ct);
                    alertDialogBuilder.setTitle("Hata!");
                    alertDialogBuilder.setMessage("E-mail yada parola hatalı!");
                    alertDialogBuilder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialogBuilder.show();
                }

                progress[0].dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,String error, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progress[0].dismiss();

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
        return 0;
    }

    public static int logout(final Context ct)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ct);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("functionname","logout");
        final ProgressDialog[] progress = new ProgressDialog[1];
        client.post("http://www.dogaozkaraca.com/izu_network/API.php",params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progress[0] = ProgressDialog.show(ct, "Please wait...",
                        "Logging out", true);

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject object)
            {
                Log.w("izuNetwork", object.toString());
                progress[0].dismiss();
                SharedPreferences.Editor editor = ct.getSharedPreferences("user", ct.MODE_PRIVATE).edit();
                editor.putInt("userID", -99);
                editor.putString("userName", "");
                editor.putString("userLastName", "");
                editor.putString("userEmail", "");
                editor.putString("userBirthdate", "");
                editor.commit();

                Intent i = new Intent(ct,AuthenticationActivity.class);
                ct.startActivity(i);

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                // called when response HTTP status is "200 OK"
                Log.w("izuNetwork", array.toString());
                try {


                progress[0].dismiss();
                }
                catch (Exception e){}
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,String error, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progress[0].dismiss();
                Log.w("izuNetwork", "error:logout");

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
        return 0;
    }


    public static int loadFeed(int userID, final Context ct, final RecyclerView mRecyclerView, final SwipeRefreshLayout swp)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ct);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("functionname","loadFeed");
        params.put("userID",userID);

        final ProgressDialog[] progress = new ProgressDialog[1];
        client.post("http://www.dogaozkaraca.com/izu_network/API.php",params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progress[0] = ProgressDialog.show(ct, "Please wait...",
                        "Loading...", true);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject object)
            {
                Log.w("izuNetworkO", object.toString());
                ArrayList<FeedItem> feed = new ArrayList<>();

                try {
                    JSONArray feedItems = (JSONArray) object.get("result");

                    for(int i=0;i<feedItems.length();i++)
                    {
                        JSONObject item = (JSONObject) feedItems.get(i);
                        String postID_s = item.getString("postID");
                        int postID = Integer.parseInt(postID_s);
                        String name = item.getString("userName");
                        String lastname = item.getString("userLastName");
                        String postText = item.getString("postText");
                        String userProfilePicURL = item.getString("userProfilePicURL");
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date inputDate = fmt.parse(item.getString("postDate"));
                        long postDateTime = inputDate.getTime();
                        Log.w("izuNetwork","pdt: "+postDateTime);

                        String ago = timeOps.getTimeAgo(postDateTime);
                        Log.w("izuNetwork","ago: "+ago);
                        int likeCount = Integer.parseInt(item.getString("likeCount"));
                        int dislikeCount = Integer.parseInt(item.getString("dislikeCount"));
                        int commentCount = Integer.parseInt(item.getString("commentCount"));

                        feed.add(new FeedItem(postID,name,lastname, userProfilePicURL,postText,ago,null,likeCount,dislikeCount,commentCount));

                    }
                    RecyclerView.Adapter mAdapter;
                    mAdapter = new FeedAdapter(feed);
                    mRecyclerView.setAdapter(mAdapter);
                    swp.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                progress[0].dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,String error, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progress[0].dismiss();

            }

        });
        return 0;
    }

    public static int loadMyPosts(int userID, final Context ct, final RecyclerView mRecyclerView, final SwipeRefreshLayout swp)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ct);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("functionname","myPosts");
        params.put("userID",userID);

        final ProgressDialog[] progress = new ProgressDialog[1];
        client.post("http://www.dogaozkaraca.com/izu_network/API.php",params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progress[0] = ProgressDialog.show(ct, "Please wait...",
                        "Loading...", true);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject object)
            {
                Log.w("izuNetworkO", object.toString());
                ArrayList<FeedItem> feed = new ArrayList<>();

                try {
                    JSONArray feedItems = (JSONArray) object.get("result");

                    for(int i=0;i<feedItems.length();i++)
                    {
                        JSONObject item = (JSONObject) feedItems.get(i);
                        String postID_s = item.getString("postID");
                        int postID = Integer.parseInt(postID_s);
                        String name = item.getString("userName");
                        String lastname = item.getString("userLastName");
                        String postText = item.getString("postText");
                        String userProfilePicURL = item.getString("userProfilePicURL");
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date inputDate = fmt.parse(item.getString("postDate"));
                        long postDateTime = inputDate.getTime();
                        Log.w("izuNetwork","pdt: "+postDateTime);

                        String ago = timeOps.getTimeAgo(postDateTime);
                        Log.w("izuNetwork","ago: "+ago);
                        int likeCount = Integer.parseInt(item.getString("likeCount"));
                        int dislikeCount = Integer.parseInt(item.getString("dislikeCount"));
                        int commentCount = Integer.parseInt(item.getString("commentCount"));

                        feed.add(new FeedItem(postID,name,lastname, userProfilePicURL,postText,ago,null,likeCount,dislikeCount,commentCount));

                    }
                    RecyclerView.Adapter mAdapter;
                    mAdapter = new FeedAdapter(feed);
                    mRecyclerView.setAdapter(mAdapter);
                    swp.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                progress[0].dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,String error, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progress[0].dismiss();

            }

        });
        return 0;
    }

    public static int newPost(int userID, int groupID, String postText, final Context ct, final NewPost_Dialog newPost_dialog)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ct);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("functionname","newPost");
        params.put("userID",userID);
        params.put("postText",postText);
        final ProgressDialog[] progress = new ProgressDialog[1];
        client.post("http://www.dogaozkaraca.com/izu_network/API.php",params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progress[0] = ProgressDialog.show(ct, "Please wait...",
                        "Loading...", true);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject object)
            {
                Log.w("izuNetworkO", object.toString());
                try {
                    String result = object.getString("result");
                    Log.w("izuNetwork",result);
                    Toast.makeText(ct,result,Toast.LENGTH_LONG);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                progress[0].dismiss();
                newPost_dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,String error, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progress[0].dismiss();

            }

        });
        return 0;
    }


}

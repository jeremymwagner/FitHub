package me.jeremy.fithub;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.app.ProgressDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import static me.jeremy.fithub.R.id.imageView;



public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    String s = formURL("LEG PRESS","Search","Exercise");

    //s = formURL("100045858438","getFriends","Following")
    public String formURL(String userID, String requestType,String friendType){
        //hard code googleid until figure out how to grab it from sign o
        return baseURL + "?requestType="+requestType+"&friendType="+friendType +"&userID=" + userID;

    }


    private GoogleApiClient mGoogleApiClient;
    public Button register;
    public EditText email,password;
    private static final int RC_SIGN_IN = 9001;
    private ImageView imgProfilePic;
    private ImageView tempLogo;

    private SignInButton login;
    private GoogleApiClient googleApiClient;
    private static final int REQUEST_CODE = 100;
    Button signOut;

    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ListView lv1;
    ListView lv2;
    ArrayList<String> arrayFollowers;
    ArrayList<String> arrayFollowing;
    String[] items1;
    String[] items2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tempLogo = (ImageView) findViewById(R.id.tempLogo);
        String logoURL = "http://15minutesmore.com/blog/wp-content/uploads/2014/03/687474703a2f2f61313238302e70686f626f732e6170706c652e636f6d2f75732f72313030302f3130342f507572706c652f76342f65362f65352f39332f65366535393330642d666535332d646265652d316630392d3533393737626364636537322f6d7a6c2e696d6b64637272702e706e67.png";
        Glide.with(getApplicationContext()).load(logoURL)
                .thumbnail(1.0f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(tempLogo);

        mStatusTextView = (TextView) findViewById(R.id.name);

        // Button listeners
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, REQUEST_CODE);
            }
        });
        */


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }
/*
    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }*/

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            GoogleSignInAccount acct = result.getSignInAccount();
            setContentView(R.layout.activity_profile);
            TextView name = (TextView) findViewById(R.id.name);
            TextView email = (TextView) findViewById(R.id.email);
            //ImageView pic = (ImageView) findViewById(R.id.imgProfilePic);
            imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();

            //Uri personPhoto = acct.getPhotoUrl();
            name.setText(personName);
            email.setText(personEmail);

            try {
                // Add date to param string
                s = s.replaceAll("\\s+","+");
                Log.d("SEARCHSTRING:",s);
                String str = new TalkToServer().execute(s).get();

                JSONObject jobj = new JSONObject(str);


                // JSONArray res = new JSONArray(jobj);


                //TextView testReq = (TextView) findViewById(R.id.test);

                //TextView testReq = (TextView) findViewById(R.id.search);

                //testReq.setText(str);



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch(JSONException e){
                e.printStackTrace();
            }
            //pic.setImageURI(personPhoto);
            /*String personPhoto = acct.getPhotoUrl().toString();

            Glide.with(getApplicationContext()).load(personPhoto)
                    .thumbnail(1.0f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);
                    */
            String tempProfilePic = "https://www.glameve.com/media/testimonials/pictures/resized/100_100_empty.gif";
            try {
                String personPhoto = acct.getPhotoUrl().toString();
                Glide.with(getApplicationContext()).load(personPhoto)
                        .thumbnail(1.0f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgProfilePic);
            }
            catch(Exception e){
                Glide.with(getApplicationContext()).load(tempProfilePic)
                        .thumbnail(1.0f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgProfilePic);
            }

            signOut = (Button) findViewById(R.id.sign_out_button);
            signOut.setOnClickListener(this);

            lv1 = (ListView)findViewById(R.id.followersList);//
            lv2 = (ListView)findViewById(R.id.followingList);//

            initList();//
        }
    }

    public void initList() {
        items1 = new String[]{"Joe", "Bob", "Jessica", "Frank", "Rachel", "Patrick", "Sandy", "Cloud", "Janrae", "Jeremy", "Nicki"};
        arrayFollowers = new ArrayList<>(Arrays.asList(items1));
        adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayFollowers);
        lv1.setAdapter(adapter1);
        String numFollowers = Integer.toString(items1.length);
        TextView followers = (TextView) findViewById(R.id.numFollowers);
        followers.setText(numFollowers);

        items2 = new String[]{"Bob", "Jessica", "Quinn", "Frank", "Rachel", "Dillon", "Sandy", "Cloud", "Jeremy", "Nicki", "Roger", "Dom", "Annie", "Rob", "Sam", "Alex", "Meg"};
        arrayFollowing = new ArrayList<>(Arrays.asList(items2));
        adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayFollowing);
        lv2.setAdapter(adapter2);
        String numFollowing = Integer.toString(items2.length);
        TextView following = (TextView) findViewById(R.id.numFollowing);
        following.setText(numFollowing);
    }

    // [END onActivityResult]

    private static final String TAG = "SignInActivity";
    private TextView mStatusTextView;

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        //if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));

            //Intent myIntent3 = new Intent(Login.this, Search.class); //Calendar.class  //Delete
            //startActivity(myIntent3);    //Delete

            //updateUI(true);
        //} else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        //}
    }

    /*
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        } else {
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        }
    }
    */

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Intent toLogin = new Intent(Login.this,Login.class);
                        startActivity(toLogin);
                    }
                });
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;

            case R.id.sign_out_button:
                signOut();
                break;

            case R.id.calendarButton:
                Intent myIntent1 = new Intent(Login.this, Calendar.class);
                startActivity(myIntent1);
                break;

            case R.id.searchButton:
                Intent myIntent2 = new Intent(Login.this, Search.class);
                startActivity(myIntent2);
                break;
        }
    }

    private ProgressDialog mProgressDialog;

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            //mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

}

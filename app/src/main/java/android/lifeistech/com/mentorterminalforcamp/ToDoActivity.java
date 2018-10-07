package android.lifeistech.com.mentorterminalforcamp;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import org.mortbay.jetty.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ToDoActivity extends AppCompatActivity
        implements EasyPermissions.PermissionCallbacks{
    public Realm realm;
    SharedPreferences data;

    MyToDoListAdapter myToDoListAdapter;
    ListView myToDoListView;
    TextView memterToDoTitleTextView;

    ProgressDialog mProgress,mOutputText;
    public GoogleAccountCredential mCredential;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static final String BUTTON_TEXT = "Call Google Sheets API";
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = { SheetsScopes.SPREADSHEETS };

    Switch switch111 , switch112, switch113, switch114, switch121, switch131,switch132,switch133,switch134,switch135,switch141;
    Switch switch211 , switch221, switch222 , switch231, switch232, switch241;
    Switch switch311 , switch312, switch321 , switch322;
    Switch switch411 , switch421, switch422 , switch431;
    Switch switch511 , switch512, switch521 , switch522, switch523, switch524;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        realm = Realm.getDefaultInstance();
        data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);


        myToDoListView = (ListView)findViewById(R.id.myToDoList);
        memterToDoTitleTextView = (TextView)findViewById(R.id.memterToDoTitleTextView);
        switch111 = findViewById(R.id.switch111);
        switch112 = findViewById(R.id.switch112);
        switch113 = findViewById(R.id.switch113);
        switch114 = findViewById(R.id.switch114);
        switch121 = findViewById(R.id.switch121);
        switch131 = findViewById(R.id.switch131);
        switch132 = findViewById(R.id.switch132);
        switch133 = findViewById(R.id.switch133);
        switch134 = findViewById(R.id.switch134);
        switch135 = findViewById(R.id.switch135);
        switch141 = findViewById(R.id.switch141);
        switch211 = findViewById(R.id.switch211);
        switch221  =findViewById(R.id.switch221);
        switch222 = findViewById(R.id.switch222);
        switch231  =findViewById(R.id.switch231);
        switch232 = findViewById(R.id.switch232);
        switch241 = findViewById(R.id.switch241);

        switch111.setText(data.getString("elementName1",""));
        switch112.setText(data.getString("elementName2",""));
        switch113.setText(data.getString("elementName3",""));
        switch114.setText(data.getString("elementName4",""));
        switch121.setText(data.getString("elementName5",""));
        switch131.setText(data.getString("elementName6",""));
        switch132.setText(data.getString("elementName7",""));
        switch133.setText(data.getString("elementName8",""));
        switch134.setText(data.getString("elementName9",""));
        switch135.setText(data.getString("elementName10",""));
        switch141.setText(data.getString("elementName11",""));
        switch211.setText(data.getString("elementName12",""));
        switch221.setText(data.getString("elementName13",""));
        switch222.setText(data.getString("elementName14",""));
        switch231.setText(data.getString("elementName15",""));
        switch232.setText(data.getString("elementName16",""));
        switch241.setText(data.getString("elementName17",""));






        mProgress = new ProgressDialog(this);
        mOutputText = new ProgressDialog(this);
        mCredential = GoogleAccountCredential.usingOAuth2(getApplicationContext(),
                Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());


        setListComponent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//これはonOptionsItemSelected(MenuItem item){}のあとじゃないとonOptionsItemSelectedが機能しない。ここでonOptionsItemSelectedを含めた設定が適用されると思われる。

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//これは onCreateOptionsMenu(Menu menu){}の前じゃないとここは機能しない。onCreateOptionsMenu でonOptionsItemSelectedを含めた設定が適用されると思われる。

        switch(item.getItemId()) {
            case R.id.menuALL:
                Intent intent = new Intent(this, ConfigActivity.class);
                startActivity(intent);

            case R.id.menuSync:
                getResultsFromApi();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        setListComponent();
    }
    public void setListComponent(){

        RealmResults<RealmToDoObject> result = null;
        /*if (filterSignal ==1) {
            results = realm.where(RealmToDoObject.class).equalTo("checkBoxisChecked", true).findAll();
        } else if (filterSignal == 2) {
            results = realm.where(RealmToDoObject.class).equalTo("checkBoxisChecked", false).findAll();
        } else {
            results = realm.where(RealmToDoObject.class).findAll();
        }*/
        result = realm.where(RealmToDoObject.class).findAll();
        List<RealmToDoObject> item = realm.copyFromRealm(result);
        myToDoListAdapter = new MyToDoListAdapter(this, R.layout.activity_mytodo_component, item, realm);
        myToDoListView.setAdapter(myToDoListAdapter);

    }

    public void intentToDoMenter(View v){
    }
    public void intentToDoMember(View v){
        Intent intent = new Intent(v.getContext(), MemberActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        v.getContext().startActivity(intent);
    }
    public void intentToDoHome(View v){
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        v.getContext().startActivity(intent);    }

    public void addNewToDo(View v){
        Intent intent = new Intent(v.getContext(), AddNewTodo.class);
        v.getContext().startActivity(intent);
    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    public void getResultsFromApi() {
        if (! isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (! isDeviceOnline()) {
            mOutputText.setMessage("No network connection available.");
        } else {
            new MakeRequestTask(mCredential).execute();
        }
    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    public void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                this, Manifest.permission.GET_ACCOUNTS)) {
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                getResultsFromApi();
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }
    }

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode code indicating the result of the incoming
     *     activity result.
     * @param data Intent (containing result data) returned by incoming
     *     activity result.
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    mOutputText.setMessage(
                            "This app requires Google Play Services. Please install " +
                                    "Google Play Services on your device and relaunch this app.");
                } else {
                    getResultsFromApi();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        mCredential.setSelectedAccountName(accountName);
                        getResultsFromApi();
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    getResultsFromApi();
                }
                break;
        }
    }

    /**
     * Respond to requests for permissions at runtime for API 23 and above.
     * @param requestCode The request code passed in
     *     requestPermissions(android.app.Activity, String, int, String[])
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }

    /**
     * Callback for when a permission is granted using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Callback for when a permission is denied using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Checks whether the device currently has a network connection.
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }


    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    /**
     * An asynchronous task that handles the Google Sheets API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.sheets.v4.Sheets mService = null;
        private Exception mLastError = null;

        MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("MentorTerminalForCamp")
                    .build();
        }

        /**
         * Background task to call Google Sheets API.
         * @param params no parameters needed for this task.
         */
        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        /**
         * Fetch a list of names and majors of students in a sample spreadsheet:
         * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
         * @return List of names and majors
         * @throws IOException
         */
        private List<String> getDataFromApi() throws IOException {
            String spreadsheetId = "1QmTMcVTLQEoYBICUltn_s0YDYmeDlMmitta6U_raEt0";
            String range = "5Daysメンターto do!A1:AG";
            List<String> results = new ArrayList<String>();
            ValueRange response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    if(row.get(1).toString().indexOf(data.getString("MenterName", "")) != -1){
                        for (int i = 2; i < 33; i++){
                            SharedPreferences.Editor editor = data.edit();
                            editor.putString("element" + i, row.get(i) + "");
                            editor.apply();
                            results.add(row.get(i)+"");
                        }


                        results.add(data.getString("TeamAlphabet",""));
                    }
                }
            }
            range = "メンバーひとことメモ!A1:N";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                int aa = 0;
                int aapre = 0;
                int bb = 0;
                int cc = 0;
                for (List row : values) {
                    if (row.get(1).toString().indexOf(data.getString("MenterName", "")) != -1) {
                        aapre = aa;
                        bb = aa + Integer.parseInt(data.getString("NumberOfMember", "0"));
                    }
                    if(aa < bb){
                        cc++;
                        SharedPreferences.Editor editor = data.edit();
                        if((row.get(2) + "") != ""){
                            aapre = aa;
                        }
                        if(aapre == aa) {
                            editor.putString("element" + cc + "Course", row.get(2) + "");
                            editor.apply();
                            results.add(row.get(2) + "");
                        }
                        if(aapre != aa) {
                            editor.putString("element" + cc + "Course", data.getString("element" + 1 + "Course",""));
                            editor.apply();
                            results.add(data.getString("element" + 1 + "Course",""));
                        }
                        editor.putString("element" + cc + "Name", row.get(8) + "");
                        editor.apply();
                        results.add(row.get(8) + "");
                    }
                    aa++;
                }
            }
            range = "5Daysメンターto do!C3:AQ3";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                int aa = 0;
                int aapre = 0;
                int bb = 0;
                int cc = 0;
                for (List row : values) {
                    for(int i = 1; i <= 33;i++){
                        SharedPreferences.Editor editor = data.edit();
                        editor.putString("elementName" + i, row.get(i) + "");
                        editor.apply();
                        results.add(row.get(i) + "");
                    }
                    aa++;
                }
            }


            return results;
        }




        @Override
        protected void onPreExecute() {
            mOutputText.setMessage("");
            mProgress.show();
        }

        @Override
        protected void onPostExecute(List<String> output) {
            mProgress.hide();
            if (output == null || output.size() == 0) {
                mOutputText.setMessage("No results returned.");
            } else {
                //output.add(0, "Data retrieved using the Google Sheets API:");
                //mainMemberText.setText(TextUtils.join("\n", output));
                Log.d("SpreadSheetAPI", TextUtils.join("\n", output).toString());

            }
        }

        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            MainActivity.REQUEST_AUTHORIZATION);
                } else {
                    mOutputText.setMessage("The following error occurred:\n"
                            + mLastError.getMessage());
                }
            } else {
                mOutputText.setMessage("Request cancelled.");
            }
        }
    }

}

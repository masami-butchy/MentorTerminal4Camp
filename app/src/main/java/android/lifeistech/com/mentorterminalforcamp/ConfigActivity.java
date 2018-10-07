package android.lifeistech.com.mentorterminalforcamp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;


public class ConfigActivity extends AppCompatActivity {
    EditText menterName, numberOfMember;
    SharedPreferences data;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        realm = Realm.getDefaultInstance();


        menterName = (EditText)findViewById(R.id.menterName);
        numberOfMember = (EditText)findViewById(R.id.numberOfMember);
        menterName.setText(data.getString("MenterName", ""));
        numberOfMember.setText(data.getString("NumberOfMember", ""));

    }

    public void ok(View v){
        SharedPreferences.Editor editor = data.edit();
        editor.putString("MenterName", menterName.getText().toString());
        editor.apply();
        if(data.getString("NumberOfMember" , "") == ""){
            editor.putString("NumberOfMember", numberOfMember.getText().toString());
            editor.apply();
            memberListCreate(v);
        }else {
            editor.putString("NumberOfMember", numberOfMember.getText().toString());
            editor.apply();
        }
        finish();
    }
    public void memberListCreate(View v){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                final RealmResults realmResults = realm.where(RealmMemberObject.class).findAll();
                realmResults.deleteAllFromRealm();

            }
        });
        int i = Integer.parseInt(numberOfMember.getText().toString());
        int a = 0;
        for(;i > 0; i--) {
            a++;
            memberListSave((short)a);
        }
        Log.d("wwwwwwwwww","" + i);
        finish();
    }


    public void memberListSave(final short i){

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgrealm) {
                    RealmMemberObject realmMemberObject = realm.createObject(RealmMemberObject.class);
                    realmMemberObject.find = i;
                    realmMemberObject.textOne = 00;
                    realmMemberObject.textTwo = 00;
                    realmMemberObject.textThree = 00;
                    realmMemberObject.textFour = 00;
                    realmMemberObject.textFive = 00;
                    realmMemberObject.textsix = 00;
                    realmMemberObject.textSeven = 00;
                    realmMemberObject.textEight = 00;
                    realmMemberObject.textNine = 00;
                    realmMemberObject.textTen = 00;
                    realmMemberObject.textEleven = 00;
                    realmMemberObject.textTwelve = 00;
                    realmMemberObject.textThirteen = 00;
                    realmMemberObject.textFourteen = 00;
                    realmMemberObject.textFifteen = 00;
                    realmMemberObject.textSixteen = 00;
                    realmMemberObject.textSeventeen = 00;
                    realmMemberObject.textEightteen = 00;
                    realmMemberObject.textNineteen = 00;
                    realmMemberObject.textTwenty = 00;
                }
            });
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}

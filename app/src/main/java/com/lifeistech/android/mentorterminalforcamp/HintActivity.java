package com.lifeistech.android.mentorterminalforcamp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.Realm;

public class HintActivity extends AppCompatActivity {
    SharedPreferences data;

    CheckBox hintCheckBox;
    ImageView hintImageView;
    ImageButton hintLeftButton, hintRightButton;
    TextView hintTitleTextView;
    int imageFlag;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        hintCheckBox = (CheckBox)findViewById(R.id.hintCheckBox);
        hintImageView = (ImageView)findViewById(R.id.hintImageView);
        hintLeftButton = (ImageButton)findViewById(R.id.hintLeftButton);
        hintRightButton = (ImageButton)findViewById(R.id.hintRightButton);
        hintTitleTextView = (TextView)findViewById(R.id.hintTitleTextView);
        imageFlag = 1;

        if(data.getBoolean("CanShowUseHint", false)){
            hintCheckBox.setChecked(false);
        }if(!data.getBoolean("CanShowUseHint", true)){
            hintCheckBox.setChecked(true);
        }

    }
    public void hintCheck(View v){
        SharedPreferences.Editor editor = data.edit();
        editor.putBoolean("CanShowUseHint", false);
        editor.apply();
    }
    public void finishHint(View v){
        finish();
    }
    public void hintRight(View v){
        switch (imageFlag) {
            case 1:
                hintImageView.setImageResource(R.drawable.hint2);
                imageFlag++;
                hintTitleTextView.setText("HINT 2");
                return;
            case 2:
                hintImageView.setImageResource(R.drawable.hint3);
                imageFlag++;
                hintTitleTextView.setText("HINT 3");
                return;
            case 3:
                hintImageView.setImageResource(R.drawable.hint4);
                hintTitleTextView.setText("HINT 4");
                imageFlag++;
                return;
            case 4:
                hintImageView.setImageResource(R.drawable.hint5);
                hintTitleTextView.setText("HINT 5");
                imageFlag++;
                return;
            case 5:
                hintImageView.setImageResource(R.drawable.hint1);
                hintTitleTextView.setText("HINT 1");
                imageFlag = 1;
                return;
        }
    }
    public void hintLeft(View v){
        switch (imageFlag) {
            case 1:
                hintImageView.setImageResource(R.drawable.hint5);
                hintTitleTextView.setText("HINT 5");
                imageFlag = 5;
                return;
            case 2:
                hintImageView.setImageResource(R.drawable.hint1);
                hintTitleTextView.setText("HINT 1");
                imageFlag--;
                return;
            case 3:
                hintImageView.setImageResource(R.drawable.hint2);
                hintTitleTextView.setText("HINT 2");
                imageFlag--;
                return;
            case 4:
                hintImageView.setImageResource(R.drawable.hint3);
                hintTitleTextView.setText("HINT 3");
                imageFlag--;
                return;
            case 5:
                hintImageView.setImageResource(R.drawable.hint4);
                hintTitleTextView.setText("HINT 4");
                imageFlag--;
                return;
        }
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        if( hintCheckBox.isChecked() ){
            SharedPreferences.Editor editor = data.edit();
            editor.putBoolean("CanShowUseHint", false);
            editor.apply();
        }if(! hintCheckBox.isChecked() ){
            SharedPreferences.Editor editor = data.edit();
            editor.putBoolean("CanShowUseHint", true);
            editor.apply();
        }
    }
}

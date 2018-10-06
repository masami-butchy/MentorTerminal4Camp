package android.lifeistech.com.mentorterminalforcamp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class ConfigActivity extends AppCompatActivity {
    EditText menterName;
    SharedPreferences data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);

        menterName = (EditText)findViewById(R.id.menterName);
        menterName.setText(data.getString("MenterName", ""));
    }

    public void ok(View v){
        SharedPreferences.Editor editor = data.edit();
        editor.putString("MenterName", menterName.getText().toString());
        editor.apply();
        finish();
    }
}

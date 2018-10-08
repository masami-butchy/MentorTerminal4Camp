package com.lifeistech.android.mentorterminalforcamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class AddNewTodo extends AppCompatActivity {

    public Realm realm;

    EditText editTitleText, editContentText, editCategoryText, editPriorityText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);

        realm = Realm.getDefaultInstance();

        editTitleText = findViewById(R.id.editTitleText);
        editContentText = findViewById(R.id.editContentText);
        editCategoryText = findViewById(R.id.editCategoryText);
        editPriorityText = findViewById(R.id.editPriorityText2);

    }

    public void save(final String title, final String content, final String category, final String priority){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
               RealmToDoObject realmToDoObject = realm.createObject(RealmToDoObject.class);
               realmToDoObject.title = title;
               realmToDoObject.content = content;
               realmToDoObject.category = category;
               realmToDoObject.priority = priority;
               realmToDoObject.checkBoxisChecked = false;
            }
        });
    }

    public void create(View v){
        String title = editTitleText.getText().toString();
        String content = editContentText.getText().toString();
        String category  =editCategoryText.getText().toString();
        String priority = editPriorityText.getText().toString();
        save(title, content, category, priority);
        Log.d("wwwwwwwwww","" + title);
        finish();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        realm.close();
    }



}

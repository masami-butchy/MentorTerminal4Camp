package android.lifeistech.com.mentorterminalforcamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.mortbay.jetty.Main;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ToDoActivity extends AppCompatActivity {
    public Realm realm;
    MyToDoListAdapter myToDoListAdapter;
    ListView myToDoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        realm = Realm.getDefaultInstance();

        myToDoListView = (ListView)findViewById(R.id.myToDoList);


        setListComponent();
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

}

package android.lifeistech.com.mentorterminalforcamp;


import io.realm.RealmObject;

public class RealmToDoObject extends RealmObject {

        public String title;

        public String content;

        public String category;

        public String priority;

        public Boolean checkBoxisChecked;
}

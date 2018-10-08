package com.lifeistech.android.mentorterminalforcamp;


import io.realm.RealmObject;

public class RealmToDoObject extends RealmObject {

        //ToDoList
        public String title;

        public String content;

        public String category;

        public String priority;

        public Boolean checkBoxisChecked;
}

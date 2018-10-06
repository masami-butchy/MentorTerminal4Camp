package android.lifeistech.com.mentorterminalforcamp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;

public class MemberListAdapter extends ArrayAdapter<RealmToDoObject>{

    List<RealmToDoObject> mListComponent;
    Realm realm;
    public MemberListAdapter(Context context, int layoutResourceId, List<RealmToDoObject> objects, Realm bgrealm){
        super(context, layoutResourceId, objects);

        mListComponent = objects;
        realm = bgrealm;

    }

    @Override
    public RealmToDoObject getItem(int position){
        return mListComponent.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup paremt){
        final ViewHolder viewHolder;

        if(convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_member, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final RealmToDoObject item = getItem(position);

        if (item != null){
            viewHolder.titleTextView.setText(item.title);
            viewHolder.categoryTextView.setText(item.category);
            viewHolder.priorityTextView.setText(item.priority);
            viewHolder.contentTextView.setText(item.content);
            viewHolder.checkBox.setChecked(item.checkBoxisChecked);
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final RealmToDoObject realmToDoObject = realm.where(RealmToDoObject.class).equalTo("content", item.content).findFirst();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm bgrealm) {
                            if (realmToDoObject.checkBoxisChecked == true){
                                Log.d("WWWWWWWWWWWWWWWW", "XXXXXXXXXXXXXXXXXXX" + realmToDoObject.checkBoxisChecked);
                                realmToDoObject.checkBoxisChecked = false;
                            }
                            else {
                                realmToDoObject.checkBoxisChecked = true;
                            }
                        }
                    });
                }
            });

            /*viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    //intent.putExtra("checkBoxisChecked", item.checkBoxisChecked);
                    intent.putExtra("content", item.content);
                    //intent.putExtra("content", item.content);
                    v.getContext().startActivity(intent);
                }
            });*/
        }

        return convertView;
    }

    public static  class ViewHolder{

        TextView titleTextView, categoryTextView, priorityTextView, contentTextView;
        CheckBox checkBox;
        LinearLayout linearLayout;

        public ViewHolder(View view){
            titleTextView = (TextView)view.findViewById(R.id.myToDoComponentTitleTextView);
            categoryTextView = (TextView)view.findViewById(R.id.myToDoComponentCategoryTextView);
            priorityTextView = (TextView)view.findViewById(R.id.myToDoComponentPriorityTextView);
            contentTextView = (TextView)view.findViewById(R.id.myToDoComponentContentTextView);
            checkBox = (CheckBox)view.findViewById(R.id.myToDoComponentCheckBox);
            linearLayout = (LinearLayout) view.findViewById(R.id.myToDoComponentParentLinearLayout);
        }
    }

}

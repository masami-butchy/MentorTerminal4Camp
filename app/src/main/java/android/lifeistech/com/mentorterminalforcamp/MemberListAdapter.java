package android.lifeistech.com.mentorterminalforcamp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MemberListAdapter extends ArrayAdapter<RealmMemberObject>{

    List<RealmMemberObject> mListComponent;
    Realm realm;
    public MemberListAdapter(Context context, int layoutResourceId, List<RealmMemberObject> objects, Realm bgrealm){
        super(context, layoutResourceId, objects);

        mListComponent = objects;
        realm = bgrealm;

    }

    @Override
    public RealmMemberObject getItem(int position){
        return mListComponent.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup paremt){
        final ViewHolder viewHolder;

        if(convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_mainmemberlist_component, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final RealmMemberObject item = getItem(position);

        if (item != null){
            viewHolder.nameTextView.setText(item.name);
            viewHolder.courseTextView.setText(item.course);
            viewHolder.commentTextView.setText(item.coment);
            setViewHolderTextX(item.textOne,viewHolder.button1,item,viewHolder,1);
            setViewHolderTextX(item.textTwo,viewHolder.button2,item,viewHolder,2);
            setViewHolderTextX(item.textThree,viewHolder.button3,item,viewHolder,3);
            setViewHolderTextX(item.textFour,viewHolder.button4,item,viewHolder,4);
            setViewHolderTextX(item.textFive,viewHolder.button5,item,viewHolder,5);
            setViewHolderTextX(item.textsix,viewHolder.button6,item,viewHolder,6);
            setViewHolderTextX(item.textSeven,viewHolder.button7,item,viewHolder,7);
            setViewHolderTextX(item.textEight,viewHolder.button8,item,viewHolder,8);
            setViewHolderTextX(item.textNine,viewHolder.button9,item,viewHolder,9);
            setViewHolderTextX(item.textTen,viewHolder.button10,item,viewHolder,10);
            setViewHolderTextX(item.textEleven,viewHolder.button11,item,viewHolder,11);
            setViewHolderTextX(item.textTwelve,viewHolder.button12,item,viewHolder,12);
            setViewHolderTextX(item.textThirteen,viewHolder.button13,item,viewHolder,13);
            setViewHolderTextX(item.textFourteen,viewHolder.button14,item,viewHolder,14);
            setViewHolderTextX(item.textFifteen,viewHolder.button15,item,viewHolder,15);
            setViewHolderTextX(item.textSixteen,viewHolder.button16,item,viewHolder,16);
            setViewHolderTextX(item.textSeventeen,viewHolder.button17,item,viewHolder,17);
            setViewHolderTextX(item.textEightteen,viewHolder.button18,item,viewHolder,18);
            setViewHolderTextX(item.textNineteen,viewHolder.button19,item,viewHolder,19);
            setViewHolderTextX(item.textTwenty,viewHolder.button20,item,viewHolder,20);

//
//            /*viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v){
//                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
//                    //intent.putExtra("checkBoxisChecked", item.checkBoxisChecked);
//                    intent.putExtra("content", item.content);
//                    //intent.putExtra("content", item.content);
//                    v.getContext().startActivity(intent);
//                }
//            });*/
        }

        return convertView;
    }

    public static  class ViewHolder{

        TextView nameTextView, courseTextView, commentTextView;
        TextView button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
        TextView button11, button12, button13, button14, button15, button16, button17, button18, button19, button20;
        ConstraintLayout constraintLayout;

        public ViewHolder(View view){
            nameTextView = (TextView)view.findViewById(R.id.nameTextView);
            courseTextView = (TextView)view.findViewById(R.id.courseTextView);
            commentTextView = (TextView)view.findViewById(R.id.commentTextView);
            button1 = (TextView)view.findViewById(R.id.button1);
            button2 = (TextView)view.findViewById(R.id.button2);
            button3 = (TextView)view.findViewById(R.id.button3);
            button4 = (TextView)view.findViewById(R.id.button4);
            button5 = (TextView)view.findViewById(R.id.button5);
            button6 = (TextView)view.findViewById(R.id.button6);
            button7 = (TextView)view.findViewById(R.id.button7);
            button8 = (TextView)view.findViewById(R.id.button8);
            button9 = (TextView)view.findViewById(R.id.button9);
            button10 = (TextView)view.findViewById(R.id.button10);
            button11 = (TextView)view.findViewById(R.id.button11);
            button12 = (TextView)view.findViewById(R.id.button12);
            button13 = (TextView)view.findViewById(R.id.button13);
            button14 = (TextView)view.findViewById(R.id.button14);
            button15 = (TextView)view.findViewById(R.id.button15);
            button16 = (TextView)view.findViewById(R.id.button16);
            button17 = (TextView)view.findViewById(R.id.button17);
            button18 = (TextView)view.findViewById(R.id.button18);
            button19 = (TextView)view.findViewById(R.id.button19);
            button20 = (TextView)view.findViewById(R.id.button20);
            constraintLayout = (ConstraintLayout) view.findViewById(R.id.mainMemberList);
        }
    }
    public void setViewHolderTextX(final short textX, final TextView buttonX, final RealmMemberObject item, final ViewHolder viewHolder, final int a) {

        buttonX.setText("" + a);
        if (textX == 00 || textX == 01 || textX == 02){
            buttonX.setTextColor(0x89000000);
        }
        if (textX == 10 || textX == 11 || textX == 12){
            buttonX.setTextColor(0x4fc3f7);
        }
        if (textX == 20 || textX == 21 || textX == 22){
            buttonX.setTextColor(0x9ccc65);
        }
        if (textX == 00 || textX == 10 || textX == 20){
            buttonX.setBackgroundResource(R.drawable.frame_clear);
        }if (textX == 01 || textX == 11 || textX == 21){
            buttonX.setBackgroundResource(R.drawable.frame_highlight);
        }if (textX == 02 || textX == 12 || textX == 22){
            buttonX.setBackgroundResource(R.drawable.frame_highlight);
        }

        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RealmMemberObject realmMemberObject = realm.where(RealmMemberObject.class).equalTo("name", item.name).findFirst();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm bgrealm) {
                        if (buttonX == viewHolder.button1){
                            if(textX == 00){
                                realmMemberObject.textOne = 10;
                            }if(textX == 10){
                                realmMemberObject.textOne = 20;
                            }if(textX == 20){
                                realmMemberObject.textOne = 00;
                            }
                        }
                        if (buttonX == viewHolder.button2){
                            if(textX == 00){
                                realmMemberObject.textTwo = 10;
                            }if(textX == 10){
                                realmMemberObject.textTwo = 20;
                            }if(textX == 20){
                                realmMemberObject.textTwo = 00;
                            }
                        }if (buttonX == viewHolder.button3){
                            if(textX == 00){
                                realmMemberObject.textThree = 10;
                            }if(textX == 10){
                                realmMemberObject.textThree = 20;
                            }if(textX == 20){
                                realmMemberObject.textThree = 00;
                            }
                        }if (buttonX == viewHolder.button4){
                            if(textX == 00){
                                realmMemberObject.textFour = 10;
                            }if(textX == 10){
                                realmMemberObject.textFour = 20;
                            }if(textX == 20){
                                realmMemberObject.textFour = 00;
                            }
                        }if (buttonX == viewHolder.button5){
                            if(textX == 00){
                                realmMemberObject.textFive = 10;
                            }if(textX == 10){
                                realmMemberObject.textFive = 20;
                            }if(textX == 20){
                                realmMemberObject.textFive = 00;
                            }
                        }if (buttonX == viewHolder.button6){
                            if(textX == 00){
                                realmMemberObject.textsix = 10;
                            }if(textX == 10){
                                realmMemberObject.textsix = 20;
                            }if(textX == 20){
                                realmMemberObject.textsix = 00;
                            }
                        }if (buttonX == viewHolder.button7){
                            if(textX == 00){
                                realmMemberObject.textSeven = 10;
                            }if(textX == 10){
                                realmMemberObject.textSeven = 20;
                            }if(textX == 20){
                                realmMemberObject.textSeven = 00;
                            }
                        }if (buttonX == viewHolder.button8){
                            if(textX == 00){
                                realmMemberObject.textEight = 10;
                            }if(textX == 10){
                                realmMemberObject.textEight = 20;
                            }if(textX == 20){
                                realmMemberObject.textEight = 00;
                            }
                        }if (buttonX == viewHolder.button9){
                            if(textX == 00){
                                realmMemberObject.textNine = 10;
                            }if(textX == 10){
                                realmMemberObject.textNine = 20;
                            }if(textX == 20){
                                realmMemberObject.textNine = 00;
                            }
                        }if (buttonX == viewHolder.button10){
                            if(textX == 00){
                                realmMemberObject.textTen = 10;
                            }if(textX == 10){
                                realmMemberObject.textTen = 20;
                            }if(textX == 20){
                                realmMemberObject.textTen = 00;
                            }
                        }if (buttonX == viewHolder.button11){
                            if(textX == 00){
                                realmMemberObject.textEleven = 10;
                            }if(textX == 10){
                                realmMemberObject.textEleven = 20;
                            }if(textX == 20){
                                realmMemberObject.textEleven = 00;
                            }
                        }if (buttonX == viewHolder.button12){
                            if(textX == 00){
                                realmMemberObject.textTwelve = 10;
                            }if(textX == 10){
                                realmMemberObject.textTwelve = 20;
                            }if(textX == 20){
                                realmMemberObject.textTwelve = 00;
                            }
                        }if (buttonX == viewHolder.button13){
                            if(textX == 00){
                                realmMemberObject.textThirteen = 10;
                            }if(textX == 10){
                                realmMemberObject.textThirteen = 20;
                            }if(textX == 20){
                                realmMemberObject.textThirteen = 00;
                            }
                        }if (buttonX == viewHolder.button14){
                            if(textX == 00){
                                realmMemberObject.textFourteen = 10;
                            }if(textX == 10){
                                realmMemberObject.textFourteen = 20;
                            }if(textX == 20){
                                realmMemberObject.textFourteen = 00;
                            }
                        }if (buttonX == viewHolder.button15){
                            if(textX == 00){
                                realmMemberObject.textFifteen = 10;
                            }if(textX == 10){
                                realmMemberObject.textFifteen = 20;
                            }if(textX == 20){
                                realmMemberObject.textFifteen = 00;
                            }
                        }if (buttonX == viewHolder.button16){
                            if(textX == 00){
                                realmMemberObject.textSixteen = 10;
                            }if(textX == 10){
                                realmMemberObject.textSixteen = 20;
                            }if(textX == 20){
                                realmMemberObject.textSixteen = 00;
                            }
                        }if (buttonX == viewHolder.button17){
                            if(textX == 00){
                                realmMemberObject.textSeventeen = 10;
                            }if(textX == 10){
                                realmMemberObject.textSeventeen = 20;
                            }if(textX == 20){
                                realmMemberObject.textSeventeen = 00;
                            }
                        }if (buttonX == viewHolder.button18){
                            if(textX == 00){
                                realmMemberObject.textEightteen = 10;
                            }if(textX == 10){
                                realmMemberObject.textEightteen = 20;
                            }if(textX == 20){
                                realmMemberObject.textEightteen = 00;
                            }
                        }if (buttonX == viewHolder.button19){
                            if(textX == 00){
                                realmMemberObject.textNineteen = 10;
                            }if(textX == 10){
                                realmMemberObject.textNineteen = 20;
                            }if(textX == 20){
                                realmMemberObject.textNineteen = 00;
                            }
                        }if (buttonX == viewHolder.button20){
                            if(textX == 00){
                                realmMemberObject.textTwelve = 10;
                            }if(textX == 10){
                                realmMemberObject.textTwelve = 20;
                            }if(textX == 20){
                                realmMemberObject.textTwelve = 00;
                            }
                        }
                    }
                });

            }
        });

    }

}

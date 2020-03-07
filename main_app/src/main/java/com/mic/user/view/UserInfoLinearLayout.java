package com.mic.user.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mic.frame.model.user.User;
import com.mic.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("all")
public class UserInfoLinearLayout extends LinearLayout {


    private int mCardHeight =50;
    private LayoutInflater mInflater;


    public UserInfoLinearLayout(Context context) {
        this(context,null);
    }

    public UserInfoLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UserInfoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * update cardview
     * @param user
     */
    public void updateCardViews(User user){

       Map<String,String> hashMap = getModelValue(user);

       for (Map.Entry<String,String> entry:hashMap.entrySet()){
           String key =entry.getKey();
           String value = entry.getValue();
           addOrUpdateItemView(key,value);
       }
    }


    private void addOrUpdateItemView(String key,String value){

        String text = key+" : "+value;
        LinearLayout cardView;
        TextView textView;

        if(isHasViewByTag(key)){
            //如果布局中有就更新
            cardView=this.findViewWithTag(key);
            textView=cardView.findViewById(R.id.tv_user_item);
            textView.setText(text);
        }else{
            //如果布局中没有就添加
            cardView = (LinearLayout) mInflater.inflate(R.layout.item_userinfo_card,null);
            cardView.setTag(key);
            textView =cardView.findViewById(R.id.tv_user_item);
            textView.setText(text);
            this.addView(cardView);
        }
    }

    private boolean isHasViewByTag(String tag){

        int count = this.getChildCount();
        for(int i=0;i<count;i++){
            View view = this.getChildAt(i);
            String viewTag = (String) view.getTag();
            if(tag.trim().equalsIgnoreCase(viewTag.trim())){
                return true;
            }else{
                return false;
            }
        }

        return false;
    }

    /**
     * 返回类的file 与string 值
     * @param t
     * @param <T>
     * @return
     */
    private static<T> Map<String,String>  getModelValue(T t){

        Map<String,String> hashMap = new HashMap<String, String>();
        Field[] fields =t.getClass().getFields();
        for (Field field:fields){
            field.setAccessible(true);
            String key =field.getName();

            try {

                String value = (String) field.get(t);
                hashMap.put(key,value);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return hashMap;
    }
}

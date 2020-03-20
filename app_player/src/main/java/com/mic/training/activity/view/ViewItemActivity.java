package com.mic.training.activity.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mic.R;
import com.mic.training.fragment.view.CircleLoadingViewFragment;
import com.mic.training.fragment.view.ListMenuFragment;
import com.mic.training.fragment.view.LoadingViewFragment;
import com.mic.training.fragment.view.LockPatternViewFragment;
import com.mic.training.fragment.view.LoveLayoutFragment;
import com.mic.training.fragment.view.MessageBubbleView1Fragment;
import com.mic.training.fragment.view.MessageBubbleViewFragment;
import com.mic.training.fragment.view.QQSlidingMenuFragment;
import com.mic.training.fragment.view.SlidingMenuFragment;
import com.mic.training.fragment.view.TouchViewGroupFragment;
import com.mic.training.fragment.view.VerticalDragListViewFragment;

public class ViewItemActivity extends AppCompatActivity {


    private FragmentTransaction transaction;

    private static final int TRACK_TEXTVIEW=2;
    private static final int PROGRESS_BAR=3;
    private static final int VIEW_PAGER=4;
    private static final int SHAPE_VIEW=5;
    private static final int RATING_BAR=6;
    private static final int LETTER_SIDEBAR=7;
    private static final int VIEW_DRAW_FLOW=8;
    private static final int TAG_LAYOUT=9;
    private static final int TOUCH_VIEW=10;
    private static final int TOUCH_VIEWGROUP=11;
    private static final int SLIDING_MENU=12;
    private static final int QQ_SLIDING_MENU=13;
    private static final int VERTICAL_DRAG_LISTVIEW=14;
    private static final int LOCK_PATTERNVIEW=15;
    private static final int SWIPE_REFRESH_LAYOUT=16;
    private static final int NESTED_SCROLLVIEW =17;
    private static final int STATUS_BAR =18;
    private static final int MY_SCROLL_VIEW=19;
    private static final int BEHAVIOR =20;
    private static final int LOADING_VIEW =21;
    private static final int LIST_MENU=22;
    private static final int CIRCLE_LOADINGVIEW =23;
    private static final int MESSAGE_BUBBLEVIEW=24;
    private static final int MESSAGE_BUBBLEVIEW1=25;
    private static final int LOVE_LAYOUT =26;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        Intent intent =getIntent();
        int id = intent.getIntExtra("position",0);
        String name = intent.getStringExtra("name");

        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        showItemView(id);
    }

    private void showItemView(int id){
        switch (id){
            case VIEW_PAGER:
                startActivity(ViewPagerActivity.class);
                break;
            case TOUCH_VIEW:
                replaceFragment(new TouchViewFragment());
                break;
            case TOUCH_VIEWGROUP:
                replaceFragment(new TouchViewGroupFragment());
                break;
            case SLIDING_MENU:
                replaceFragment(new SlidingMenuFragment());
                break;
            case QQ_SLIDING_MENU:
                replaceFragment(new QQSlidingMenuFragment());
                break;
            case VERTICAL_DRAG_LISTVIEW:
                replaceFragment(new VerticalDragListViewFragment());
                break;
            case LOCK_PATTERNVIEW:
                replaceFragment(new LockPatternViewFragment());
                break;
            case SWIPE_REFRESH_LAYOUT:
                startActivity(SwipeActivity.class);
                break;
            case NESTED_SCROLLVIEW:
                startActivity(NestedActivity.class);
                break;
            case STATUS_BAR:
                startActivity(StatusBarActivity.class);
                break;
            case MY_SCROLL_VIEW:
                startActivity(MyScrollViewActivity.class);
                break;
            case BEHAVIOR:
                startActivity(BehaviorActivity.class);
                break;
            case LOADING_VIEW:
                replaceFragment(new LoadingViewFragment());
                break;
            case LIST_MENU:
                replaceFragment(new ListMenuFragment());
                break;
            case CIRCLE_LOADINGVIEW:
                replaceFragment(new CircleLoadingViewFragment());
                break;
            case MESSAGE_BUBBLEVIEW:
                replaceFragment(new MessageBubbleViewFragment());
                break;
            case MESSAGE_BUBBLEVIEW1:
                replaceFragment(new MessageBubbleView1Fragment());
                break;
            case LOVE_LAYOUT:
                replaceFragment(new LoveLayoutFragment());
                break;
        }
    }


    private void replaceFragment(Fragment fragment){
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }

    private void startActivity(Class clazz){
        finish();
        Intent intent = new Intent(ViewItemActivity.this,clazz);
        startActivity(intent);
    }


}

package com.mic.xsample.activity.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.mic.xsample.R;
import com.mic.xsample.fragment.view.LetterSideBarFragment;
import com.mic.xsample.fragment.view.LoadingViewFragment;
import com.mic.xsample.fragment.view.LockPatternViewFragment;
import com.mic.xsample.fragment.view.ProgressBarFragment;
import com.mic.xsample.fragment.view.QQSlidingMenuFragment;
import com.mic.xsample.fragment.view.QQStepFragment;
import com.mic.xsample.fragment.view.RatingBarFragment;
import com.mic.xsample.fragment.view.ShapeFragment;
import com.mic.xsample.fragment.view.SlidingMenuFragment;
import com.mic.xsample.fragment.view.TagLayoutFragment;
import com.mic.xsample.fragment.view.TouchViewFragment;
import com.mic.xsample.fragment.view.TouchViewGroupFragment;
import com.mic.xsample.fragment.view.TrackTextViewFragment;
import com.mic.xsample.fragment.view.VerticalDragListViewFragment;
import com.mic.xsample.fragment.view.XTextViewFragment;

public class ViewItemActivity extends AppCompatActivity {


    private FragmentTransaction transaction;

    private static final int X_TEXTVIEW=0;
    private static final int QQ_STEP=1;
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
            case X_TEXTVIEW:
                replaceFragment(new XTextViewFragment());
                break;
            case QQ_STEP:
                replaceFragment(new QQStepFragment());
                break;
            case TRACK_TEXTVIEW:
                replaceFragment(new TrackTextViewFragment());
                break;
            case PROGRESS_BAR:
                replaceFragment(new ProgressBarFragment());
                break;
            case VIEW_PAGER:
                startActivity(ViewPagerActivity.class);
                break;
            case SHAPE_VIEW:
                replaceFragment(new ShapeFragment());
                break;
            case RATING_BAR:
                replaceFragment(new RatingBarFragment());
                break;
            case LETTER_SIDEBAR:
                replaceFragment(new LetterSideBarFragment());
                break;
            case VIEW_DRAW_FLOW:
                startActivity(ViewDrawFlowActivity.class);
                break;
            case TAG_LAYOUT:
                replaceFragment(new TagLayoutFragment());
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

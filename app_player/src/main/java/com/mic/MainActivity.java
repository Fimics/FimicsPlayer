package com.mic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.mic.core.BaseFragment;
import com.mic.tabs.HomeFragment;
import com.mic.tabs.PublishFragment;
import com.mic.tabs.FindFragment;
import com.mic.tabs.UserFragment;
import com.mic.core.utils.StatusBarUtil;
import com.mic.tabs.SofaFragment;
import com.mic.view.bottomnav.Bottom;
import com.mic.view.bottomnav.BottomLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

@SuppressWarnings("")
public class MainActivity extends AppCompatActivity {

    private static final int CURRENT_PAGE=0;
    private DrawerLayout mDrawerLayout;
    private BottomLayout bottomLayout;
    private ViewPager mViewPager;
    private ArrayList<Bottom> bottomList = new ArrayList<Bottom>();
    private final ArrayList<BaseFragment> fragments = new ArrayList<>();
    private  ActionBar actionBar;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        initData();
        initView();
        bottomLayout.setBottomList(bottomList);
        bottomLayout.setmOnTabChanged(new BottomLayout.OnTabChanged() {
            @Override
            public void onSelected(int index) {
               mViewPager.setCurrentItem(index,true);
            }
        });

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView =findViewById(R.id.nav_view);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        initViewPager();
        setCurrentPage();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView(){
        bottomLayout=this.findViewById(R.id.bottom_nav);
        mViewPager=this.findViewById(R.id.view_pager);
    }

    private void initData() {
        bottomList.add(new Bottom("首页",R.drawable.tab_home));
        bottomList.add(new Bottom("新闻",R.drawable.tab_news));
        bottomList.add(new Bottom("视频",R.drawable.tab_video));
        bottomList.add(new Bottom("消息",R.drawable.tab_msg));
        bottomList.add(new Bottom("用户",R.drawable.tab_user));

        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new SofaFragment());
        fragments.add(new PublishFragment());
        fragments.add(new UserFragment());
    }

    private void setCurrentPage(){
        mDrawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mViewPager==null)
                    return;
                mViewPager.setCurrentItem(CURRENT_PAGE,true);

                if(bottomLayout==null)
                    return;
                bottomLayout.updateIndicator(CURRENT_PAGE);
            }
        },500);
    }

    private void setActionBarName(String title){
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setTitle(title);
        }
    }

    private void initViewPager() {
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setPageMargin(10);

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {


            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              bottomLayout.updateIndicator(position);
              setActionBarName(bottomList.get(position).name);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * 加载创建 menu 布局
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * 点击menu的一些回掉
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}

package com.mic;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Toast;

import com.mic.frame.common.BaseFragment;
import com.mic.home.fragment.HomeFragment;
import com.mic.ndk.NDKInterface;
import com.mic.ndk.NDKTools;
import com.mic.tools.Logger;
import com.mic.view.Bottom;
import com.mic.view.BottomLayout;
import com.mic.msg.fragment.MessageFragment;
import com.mic.news.fragment.NewsFragment;
import com.mic.user.fragment.UserFragment;
import com.mic.video.fragment.VideoFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {
    private static final String TAG ="main";

    private DrawerLayout mDrawerLayout;
    private BottomLayout bottomLayout;
    private ViewPager mViewPager;
    private ArrayList<Bottom> bottomList = new ArrayList<Bottom>();
    private final ArrayList<BaseFragment> fragments = new ArrayList<>();

    static {
        System.loadLibrary("hello");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String msg = NDKInterface.sayHello();
        Logger.d(TAG,msg);


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
        ActionBar actionBar = getSupportActionBar();
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
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        initViewPager();

    }

    private void initView(){
        bottomLayout=this.findViewById(R.id.bottom_layoty);
        mViewPager=this.findViewById(R.id.view_pager);
    }

    private void initData() {
        bottomList.add(new Bottom("首页",R.drawable.tab_home));
        bottomList.add(new Bottom("新闻",R.drawable.tab_news));
        bottomList.add(new Bottom("视频",R.drawable.tab_video));
        bottomList.add(new Bottom("消息",R.drawable.tab_msg));
        bottomList.add(new Bottom("用户",R.drawable.tab_user));

        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment());
        fragments.add(new VideoFragment());
        fragments.add(new MessageFragment());
        fragments.add(new UserFragment());
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
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
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

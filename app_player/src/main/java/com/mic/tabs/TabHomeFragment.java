package com.mic.tabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mic.annotation.FragmentDestination;
import com.mic.core.BaseFragment;
import com.mic.R;
import com.mic.demo.fragment.AndroidUIChildFragment;
import com.mic.demo.fragment.AndroidUIFragment;
import com.mic.demo.fragment.FruitFragment;
import com.mic.find.multitype.communicate.CommunicateFragment;
import com.mic.find.multitype.moreapis.MoreApisPlaygroundFragment;
import com.mic.find.multitype.multiselectable.MultiSelectableFragment;
import com.mic.find.multitype.payload.PayloadFragment;
import com.mic.find.multitype.weibo.WeiboFragment;
import com.mic.core.thirdparty.indicator.view.indicator.Indicator;
import com.mic.core.thirdparty.indicator.view.indicator.RecyclerIndicatorView;
import com.mic.core.thirdparty.indicator.view.indicator.slidebar.ColorBar;
import com.mic.core.thirdparty.indicator.view.indicator.transition.OnTransitionTextListener;
import com.mic.home.HomeFragment;

import java.util.ArrayList;

@FragmentDestination(pageUrl ="main/tabs/home" ,asStarter = true)
public class TabHomeFragment extends BaseFragment {

    private RecyclerIndicatorView indicatorView;
    private ViewPager viewPager;
    String[] names = {"Home", "AndroidUI","UIChild","Communicate-with-binder","MultiSelectable" , "Weibo", "Payload", "MoreApis","Fruit"};
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    public TabHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewPager = rootView.findViewById(R.id.moretab_viewPager);
        indicatorView = rootView.findViewById(R.id.moretab_indicator);
        set(indicatorView, names.length);
        indicatorView.setOnItemSelectListener(new Indicator.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View selectItemView, int select, int preSelect) {
                viewPager.setCurrentItem(select);
            }
        });
        fragments.add(HomeFragment.newInstance());
        fragments.add(new AndroidUIFragment());
        fragments.add(new AndroidUIChildFragment());
        fragments.add(new CommunicateFragment());
        fragments.add(new MultiSelectableFragment());
        fragments.add(new WeiboFragment());
        fragments.add(new PayloadFragment());
        fragments.add(new MoreApisPlaygroundFragment());
        fragments.add(new FruitFragment());
        initViewPager();
        return rootView;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_tab;
    }

    private void set(Indicator indicator, int count) {
        indicator.setAdapter(new MyAdapter(count));

        indicator.setScrollBar(new ColorBar(getContext(), R.color.colorPrimary, 5));

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.black);
        int unSelectColor = getResources().getColor(R.color.colorPrimary);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

        indicator.setCurrentItem(0,true);
    }

    public void toNextPage(){
        if(indicatorView!=null){
            indicatorView.setCurrentItem(indicatorView.getCurrentItem()+1);
        }
    }

    private class MyAdapter extends Indicator.IndicatorAdapter {

        private final int count;

        public MyAdapter(int count) {
            super();
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top_news, parent, false);
            }
            TextView textView = (TextView) convertView;
            //用了固定宽度可以避免TextView文字大小变化，tab宽度变化导致tab抖动现象
//            textView.setWidth(DisplayUtil.dipToPix(getContext(),50));
            textView.setText(names[position]);
            return convertView;
        }
    }

    private void initViewPager() {

        viewPager.setOffscreenPageLimit(0);
        viewPager.setPageMargin(10);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {


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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicatorView.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

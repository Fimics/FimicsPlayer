package com.mic.news.tab;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mic.R;
import com.mic.frame.common.BaseFragment;
import com.mic.news.multitype.bilibili.BilibiliFragment;
import com.mic.news.multitype.communicate.CommunicateFragment;
import com.mic.news.multitype.moreapis.MoreApisPlaygroundFragment;
import com.mic.news.multitype.multiselectable.MultiSelectableFragment;
import com.mic.news.multitype.normal.NormalFragment;
import com.mic.news.multitype.ontomany.OnDataToManyFragment;
import com.mic.news.multitype.testpayload.TestPayloadFragment;
import com.mic.news.multitype.weibo.WeiboFragment;
import com.mic.thirdparty.indicator.view.indicator.Indicator;
import com.mic.thirdparty.indicator.view.indicator.RecyclerIndicatorView;
import com.mic.thirdparty.indicator.view.indicator.slidebar.ColorBar;
import com.mic.thirdparty.indicator.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {

    private RecyclerIndicatorView indicatorView;
    private ViewPager viewPager;
    String[] names = {"Bilibili", "Normal", "MultiSelectable", "Communicate-with-binder", "Weibo", "OneDataToMany", "TestPayload", "MoreApisPlayground"};
    private final ArrayList<BaseFragment> fragments = new ArrayList<>();
    public NewsFragment() {
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
        fragments.add(new BilibiliFragment());
        fragments.add(new NormalFragment());
        fragments.add(new MultiSelectableFragment());
        fragments.add(new CommunicateFragment());
        fragments.add(new WeiboFragment());
        fragments.add(new OnDataToManyFragment());
        fragments.add(new TestPayloadFragment());
        fragments.add(new MoreApisPlaygroundFragment());
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
        return R.layout.fragment_news;
    }

    private void set(Indicator indicator, int count) {
        indicator.setAdapter(new MyAdapter(count));

        indicator.setScrollBar(new ColorBar(getContext(), Color.RED, 5));

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.tab_top_text_2);
        int unSelectColor = getResources().getColor(R.color.tab_top_text_1);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));

        indicator.setCurrentItem(0,true);
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

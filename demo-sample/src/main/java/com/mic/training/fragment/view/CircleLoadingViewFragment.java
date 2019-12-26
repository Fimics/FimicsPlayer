package com.mic.training.fragment.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.training.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleLoadingViewFragment extends Fragment {


    public CircleLoadingViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_circle_loading_view, container, false);
        // 获取后台数据

        // 获取完之后是不是要设置为 GONE
        View loadingView = view.findViewById(R.id.load_view);

       // loadingView.setVisibility(View.GONE);
        // 只是把它隐藏  58同城的加载动画讲过一次了
        // 10 个页面  内存泄漏  就是对象不可回收
        // 比如当前 Activity 关闭 但是实例一直存在内存中（就是被别人持有了）
        // 在项目中给大家讲 后面会专门讲 100 次  40次架构  10 kotlin  30 NDK  内存优化
        // 明天 上一次数学课 三角函数   QQ消息拖拽贝塞尔
        return view;
    }

}

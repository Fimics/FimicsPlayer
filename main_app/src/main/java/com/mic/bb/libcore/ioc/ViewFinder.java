package com.mic.bb.libcore.ioc;

import android.app.Activity;
import android.view.View;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/2/5.
 * Version 1.0
 * Description: View的findViewById的辅助类
 */
public class ViewFinder {
    private Activity mActivity;
    private View mView;


    public ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    public ViewFinder(View view) {
        this.mView = view;
    }

    public View findViewById(int viewId) {
        return mActivity != null ? mActivity.findViewById(viewId) : mView.findViewById(viewId);
    }
}

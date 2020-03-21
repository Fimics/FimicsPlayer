package com.mic.user;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mic.R;

import java.util.Objects;




public class LoginDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "login";
    private Dialog dialog;


    public static LoginDialogFragment instance() {
        LoginDialogFragment fragment = new LoginDialogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        AppCompatButton facebookLogin = view.findViewById(R.id.facebookLogin);
        AppCompatButton googleLogin = view.findViewById(R.id.googleLogin);
        AppCompatButton phoneLogin = view.findViewById(R.id.phoneLogin);
        facebookLogin.setOnClickListener(this);
        googleLogin.setOnClickListener(this);
        phoneLogin.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (dialog == null) return;
        Window window = dialog.getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
        window.setBackgroundDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.color.transparent));
        int dialogHeight = getResources().getDisplayMetrics().widthPixels;
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    public void show(Activity activity) {
        AppCompatActivity compatActivity = (AppCompatActivity) activity;
        show(compatActivity.getSupportFragmentManager(), TAG);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new LoginDialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return dialog;
    }

    private class LoginDialog extends Dialog {
        public LoginDialog(@NonNull Context context) {
            super(context,R.style.Theme_AppCompat_Dialog);
        }

        @Override
        public void onBackPressed() {
            LoginDialogFragment.this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.facebookLogin:
            case R.id.googleLogin:
            case R.id.phoneLogin:
                break;
        }
    }
}

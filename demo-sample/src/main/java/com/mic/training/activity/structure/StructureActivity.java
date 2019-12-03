package com.mic.training.activity.structure;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.training.R;

public class StructureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure);
        System.loadLibrary("structure");
        NDK.testLinkedList();
    }

}

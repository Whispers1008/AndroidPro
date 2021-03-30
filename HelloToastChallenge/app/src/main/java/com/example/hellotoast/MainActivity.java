package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private Button myBtn_zero;
    private Button myBtn_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        myBtn_zero = (Button) findViewById(R.id.button_zero);
        myBtn_count = (Button) findViewById(R.id.button_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                                                        Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
            if (mCount%2 == 0){
                // 偶数
                myBtn_count.setBackgroundColor(Color.parseColor("#F44336"));
            }else{
                // 奇数
                myBtn_count.setBackgroundColor(Color.parseColor("#4CAF50"));
            }
            myBtn_zero.setBackgroundColor(Color.parseColor("#FFC107"));
        }
    }

    public void changeColor(View view) {
        mCount = 0;
        myBtn_zero.setBackgroundColor(Color.parseColor("gray"));
        myBtn_count.setBackgroundColor(Color.parseColor("#6200ee"));
        mShowCount.setText(Integer.toString(0));
    }
}
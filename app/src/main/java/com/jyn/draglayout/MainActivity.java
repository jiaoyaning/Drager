package com.jyn.draglayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jyn.dragerlab.DragUtils;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private DragUtils dragUtils, dragUtils2;

    private LinearLayout text_layout;
    private RelativeLayout layout_border;

    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        textView = findViewById(R.id.text);
        text_layout = findViewById(R.id.text_layout);
        layout_border = findViewById(R.id.layout_border);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "button1", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "button2", Toast.LENGTH_SHORT).show();
            }
        });

        dragUtils = new DragUtils(text_layout);
        dragUtils2 = new DragUtils(textView);
    }

    public void reset(View view) {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        dragUtils2.setParentBorder(layout_border.getLeft(), layout_border.getTop(),
//                layout_border.getRight(), layout_border.getBottom());
    }
}

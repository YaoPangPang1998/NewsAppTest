package com.ypp.newsapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.ypp.newsapp.R;

public class WebActivity extends AppCompatActivity {
    LinearLayout layout;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        layout=findViewById(R.id.mLinearLaoyout);
        intent=getIntent();
        AgentWeb.with(this)
                .setAgentWebParent(layout,new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(intent.getStringExtra("url"));
    }
}

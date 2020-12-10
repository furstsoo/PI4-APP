package com.project.activity;

import com.project.entity.GlobalUser;
import com.project.entity.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MessageActivity extends AppCompatActivity {

    final GlobalUser globalUser = (GlobalUser) getApplicationContext();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
}
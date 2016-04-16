package com.piterquest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.piterquest.R;
import com.piterquest.data.DataTransition;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        TextView tvQuestName = (TextView) findViewById(R.id.textview_finish_questname);
        if (tvQuestName != null) {
            tvQuestName.setText(getIntent().getStringExtra(DataTransition.QUEST_NAME));
        }
    }

    public void backToQuestList(View view) {
        Intent intent = new Intent(getApplicationContext(), ListQuestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

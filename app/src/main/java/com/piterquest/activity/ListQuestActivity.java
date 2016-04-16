package com.piterquest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.piterquest.R;

import java.util.ArrayList;

public class ListQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quest);

        final ListView questListView = (ListView) findViewById(R.id.quest_list);

        String[] quests = new String[]{};
        final ArrayList<String> questList = new ArrayList<>();

    }
}

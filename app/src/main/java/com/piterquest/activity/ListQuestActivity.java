package com.piterquest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.piterquest.R;

import java.util.ArrayList;
import java.util.Collections;

public class ListQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quest);

        final ListView questListView = (ListView) findViewById(R.id.quest_list);

        String[] quests = new String[]{"Quest 1", "Quest 2", "Quest 3"};
        final ArrayList<String> questList = new ArrayList<>();
        Collections.addAll(questList, quests);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, quests);
        assert questListView != null;
        questListView.setAdapter(adapter);

        questListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) questListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Quest : " + itemValue + " was selected",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}

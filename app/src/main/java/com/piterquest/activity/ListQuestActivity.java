package com.piterquest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.piterquest.R;
import com.piterquest.adapter.ListQuestAdapter;
import com.piterquest.data.Quest;
import com.piterquest.data.QuestInfo;
import com.piterquest.data.QuestPoint;

import java.util.ArrayList;
import java.util.Collections;

public class ListQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quest);

        final ListView questListView = (ListView) findViewById(R.id.quest_list);
        QuestInfo questInfo1 = new QuestInfo(1, "Quest 1", "QQQ", "");
        QuestPoint questPoint1 = new QuestPoint("QQ", "Qq","QQ", true, "q", "qQ", "q");
        Quest quest1 = new Quest(questInfo1,questPoint1);
        ArrayList<Quest> quests = new ArrayList<Quest>();
        quests.add(quest1);
        quests.add(quest1);

        ListQuestAdapter adapter = new ListQuestAdapter(this, quests);
        assert questListView != null;
        questListView.setAdapter(adapter);

        questListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quest itemValue = (Quest) questListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Quest : " + itemValue + " was selected",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}

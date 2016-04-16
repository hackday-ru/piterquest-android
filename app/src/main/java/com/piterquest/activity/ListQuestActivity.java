package com.piterquest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.piterquest.R;
import com.piterquest.adapter.ListQuestAdapter;
import com.piterquest.data.DataTransition;
import com.piterquest.data.Quest;
import com.piterquest.data.QuestInfo;
import com.piterquest.data.QuestPoint;

import java.util.ArrayList;

public class ListQuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quest);

        final ListView questListView = (ListView) findViewById(R.id.quest_list);
        final Quest quest1 = getSampleQuest();
        ArrayList<Quest> quests = new ArrayList<Quest>();
        quests.add(quest1);
        quests.add(quest1);

        ListQuestAdapter adapter = new ListQuestAdapter(this, quests);
        assert questListView != null;
        questListView.setAdapter(adapter);

        questListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quest quest = (Quest) questListView.getItemAtPosition(position);
                questSelected(quest);
            }
        });
    }

    private void questSelected(Quest quest) {
        Intent detailIntent = new Intent(getApplicationContext(), QuestPreviewActivity.class);
        detailIntent.putExtra(DataTransition.QUEST, quest);
        startActivity(detailIntent);
    }

    /**
     * Provides sample quest for testing purposes.
     *
     * @return A sample quest.
     */
    private Quest getSampleQuest() {
        QuestInfo qInfo = new QuestInfo(
                1,
                "Sample Quest",
                "Description of the sample quest. It's gotta be awesome.",
                null
        );
        ArrayList<QuestPoint> qList = new ArrayList<>();
        qList.add(new QuestPoint(
                "Psst, first point is somewhere near you.",
                "No, u get no coors.",
                "https://pixabay.com/static/uploads/photo/2013/11/15/21/41/peter-211139_960_720.jpg",
                false,
                "What is the best city on Earth?",
                null,
                "Piter"
        ));
        qList.add(new QuestPoint(
                "A second point.",
                "Nope, no coors.",
                null,
                false,
                "What is first letter of alphabet?",
                "https://pixabay.com/static/uploads/photo/2013/11/15/21/45/bridge-211168_960_720.jpg",
                "A"
        ));
        return new Quest(qInfo, qList);
    }
}

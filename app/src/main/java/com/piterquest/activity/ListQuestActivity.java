package com.piterquest.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
        final Quest quest2 = getEpamQuest();
        ArrayList<Quest> quests = new ArrayList<Quest>();
        quests.add(quest1);
        quests.add(quest2);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quest_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                showAboutDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAboutDialog() {
        final SpannableString s = new SpannableString(getString(R.string.text_about));
        Linkify.addLinks(s, Linkify.ALL);

        final AlertDialog d = new AlertDialog.Builder(ListQuestActivity.this)
                .setPositiveButton(android.R.string.ok, null)
                .setMessage(s)
                .create();
        d.show();

        TextView tvMessage = (TextView) d.findViewById(android.R.id.message);
        if (tvMessage != null) {
            tvMessage.setMovementMethod(LinkMovementMethod.getInstance());
        }
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
                "https://s22.postimg.org/kkpedtka9/ic_launcher.png"
        );
        ArrayList<QuestPoint> qList = new ArrayList<>();
        qList.add(new QuestPoint(
                "Psst, first point is somewhere near you.",
                59.891466, 30.315883,
                "https://pixabay.com/static/uploads/photo/2013/11/15/21/41/peter-211139_960_720.jpg",
                true,
                "What is the best city on Earth?",
                null,
                "Piter"
        ));
        qList.add(new QuestPoint(
                "A second point.",
                37.4, -122.1,
                null,
                false,
                "What is first letter of alphabet?",
                "https://pixabay.com/static/uploads/photo/2013/11/15/21/45/bridge-211168_960_720.jpg",
                "A"
        ));
        return new Quest(qInfo, qList);
    }

    private Quest getEpamQuest() {
        QuestInfo qInfo = new QuestInfo(
                42,
                "EPAM HackDay",
                "Эпический квест для участников эпического хакатона EPAM.",
                "http://s23.postimg.org/xymwmpduj/photo_2016_04_17_11_44_04.jpg"
        );
        ArrayList<QuestPoint> qList = new ArrayList<>();
        qList.add(new QuestPoint(
                "Найдите Московские ворота.",
                59.891059, 30.318875,
                "http://s23.postimg.org/4gxcqv5nf/photo_2016_04_17_11_43_34.jpg",
                false,
                "Сколько колонн в Московских воротах?",
                null,
                "12"
        ));
        qList.add(new QuestPoint(
                "Найдите вход на хакатон.",
                59.890951, 30.317250,
                "http://s23.postimg.org/8h4i33w4b/photo_2016_04_17_11_44_10.jpg",
                true,
                "Кто победит на хакатоне EPAM?",
                "https://openclipart.org/image/2400px/svg_to_png/546/Gerald-G-House-sitting-on-a-pile-of-money.png",
                "PiterQuest"
        ));
        return new Quest(qInfo, qList);
    }
}

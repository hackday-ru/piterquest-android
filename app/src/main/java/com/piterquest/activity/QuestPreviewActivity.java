package com.piterquest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.piterquest.R;
import com.piterquest.data.DataTransition;
import com.piterquest.data.Quest;

public class QuestPreviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanseState) {
        super.onCreate(savedInstanseState);
        setContentView(R.layout.activity_quest_preview);

        Intent intent = getIntent();
        Quest quest = intent.getParcelableExtra(DataTransition.QUEST);

        makeGUI(quest);
    }

    private void makeGUI(Quest quest) {

        setTitle(quest.getInfo().getName());

        TextView descriptionQuestView = (TextView) findViewById(R.id.quest_preview_description);
        ImageView imageQuestionView = (ImageView) findViewById(R.id.big_image);

        assert descriptionQuestView != null && imageQuestionView != null;

        String description = quest.getInfo().getDescription();
        descriptionQuestView.setText(description);
        imageQuestionView.setImageResource(R.drawable.placeholder);
    }

}

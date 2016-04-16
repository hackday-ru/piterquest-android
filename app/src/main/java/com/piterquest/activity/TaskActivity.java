package com.piterquest.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.piterquest.R;
import com.piterquest.data.DataTransition;
import com.piterquest.data.Quest;
import com.piterquest.data.QuestPoint;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class TaskActivity extends AppCompatActivity {
    private TextView tvTaskContent;
    private ImageView imgTaskImage;
    private EditText etAnswer;

    private Quest quest;
    private int curPosition;
    private int pointsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // assigning controls
        tvTaskContent = (TextView) findViewById(R.id.textview_task_content);
        imgTaskImage = (ImageView) findViewById(R.id.image_task);
        etAnswer = (EditText) findViewById(R.id.edittext_answer);

        // extracting data from intent sent by QuestPreview
        quest = getIntent().getParcelableExtra(DataTransition.QUEST);
        pointsCount = quest.getPoints().size();

        // insta-launching PointSearchActivity with the first QuestPoint
        curPosition = 0;
        nextPointOrFinish(); // assuming the quest has at least 1 point
    }

    /**
     * Fills TaskActivity with contents of current task.
     */
    @SuppressLint("SetTextI18n") // suppresses useless warning about concatenation in setText
    private void refillActivity() {
        // setting activity header
        String title = getString(R.string.task_number) + " " + (curPosition + 1) + "/" + pointsCount;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
        // setting "inner" contents
        QuestPoint curPoint = quest.getPoints().get(curPosition);
        tvTaskContent.setText(curPoint.getTaskText());
        String imageUrl = curPoint.getTaskImage();
        if (imageUrl != null && !imageUrl.equals("")) {
            Picasso.with(TaskActivity.this).load(imageUrl).into(imgTaskImage);
            imgTaskImage.setVisibility(View.VISIBLE);
        } else {
            imgTaskImage.setVisibility(View.GONE);
        }
        etAnswer.setText("");
    }

    /**
     * If there are quest points left, switches into point finding mode.
     * If not, shows quest finish dialog.
     */
    private void nextPointOrFinish() {
        if (curPosition < pointsCount) {
            Intent intent = new Intent(TaskActivity.this, PointSearchActivity.class);
            intent.putExtra(DataTransition.QUEST_POINT, quest.getPoints().get(curPosition));
            startActivityForResult(intent, DataTransition.REQUEST_CODE_HINT);
        } else {
            Intent intent = new Intent(TaskActivity.this, FinishActivity.class);
            intent.putExtra(DataTransition.QUEST_NAME, quest.getInfo().getName());
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_task, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DataTransition.REQUEST_CODE_HINT && resultCode == Activity.RESULT_OK) {
            // player found the next point; should show task
            refillActivity();
        }
    }

    public void checkAnswer(View view) {
        String userAnswer = etAnswer.getText().toString();
        String rightAnswer = quest.getPoints().get(curPosition).getSolution();
        if (userAnswer.equals(rightAnswer)) {
            ++curPosition;
            nextPointOrFinish();
        } else {
            Toast.makeText(TaskActivity.this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
        }
    }
}

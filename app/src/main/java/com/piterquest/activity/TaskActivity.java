package com.piterquest.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.piterquest.R;
import com.piterquest.data.DataTransition;
import com.piterquest.data.Quest;
import com.piterquest.data.QuestPoint;
import com.squareup.picasso.Picasso;

public class TaskActivity extends AppCompatActivity {
    private TextView tvTaskNumber = (TextView) findViewById(R.id.textview_task_number);
    private TextView tvTaskContent = (TextView) findViewById(R.id.textview_task_content);
    private ImageView imgTaskImage = (ImageView) findViewById(R.id.image_task);
    private EditText etAnswer = (EditText) findViewById(R.id.edittext_answer);

    private Quest quest;
    private int curPosition;
    private int pointsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // extracting data from intent sent by QuestPreview
        Quest quest = getIntent().getParcelableExtra(DataTransition.QUEST);
        pointsCount = quest.getPoints().size();

        // insta-launching PointSearchActivity with the first QuestPoint
        curPosition = 0;
        startPointSearching(); // assuming the quest has at least 1 point
    }

    /**
     * Fills TaskActivity with contents of current task.
     */
    @SuppressLint("SetTextI18n") // suppresses useless warning about concatenation in setText
    private void refillActivity() {
        // setting activity header
        String title = quest.getInfo().getName() + " (" + curPosition + "/" + pointsCount + ")";
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
        // setting "inner" contents
        QuestPoint curPoint = quest.getPoints().get(curPosition);
        tvTaskNumber.setText(getString(R.string.task_number) + curPosition);
        tvTaskContent.setText(curPoint.getTaskText());
        Picasso.with(TaskActivity.this).load(curPoint.getTaskImage()).into(imgTaskImage);
        etAnswer.setText("");
    }

    /**
     * If there are quest points left, switches into point finding mode.
     * If not, shows quest finish dialog.
     */
    private void startPointSearching() {
        Intent intent = new Intent(TaskActivity.this, PointSearchActivity.class);
        intent.putExtra(DataTransition.QUEST_POINT, quest.getPoints().get(curPosition));
        intent.putExtra(DataTransition.CURRENT_POINT_INDEX, curPosition);
        intent.putExtra(DataTransition.POINTS_COUNT, pointsCount);
        startActivityForResult(intent, DataTransition.REQUEST_CODE_HINT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_task, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DataTransition.REQUEST_CODE_HINT && resultCode == DataTransition.FOUND_POINT) {
            // player found the next point; should show task
            refillActivity();
        }
    }
}

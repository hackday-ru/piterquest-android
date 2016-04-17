package com.piterquest.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.piterquest.R;
import com.piterquest.data.DataTransition;
import com.piterquest.data.Quest;
import com.piterquest.data.QuestPoint;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class PointSearchActivity extends AppCompatActivity {
    private QuestPoint questPoint;

    private int clickCount = 0; // stub; should be removed after real check is added

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_search);

        // assigning controls
        final TextView tvHint = (TextView) findViewById(R.id.textview_point_hint);
        final ImageView imgHint = (ImageView) findViewById(R.id.image_point_hint);

        if (savedInstanceState == null) {
            // extracting data from intent sent by Task and setting up header
            Intent intent = getIntent();
            questPoint = intent.getParcelableExtra(DataTransition.QUEST_POINT);
        }
        else {
            // extracting data from saved instance state
            questPoint = savedInstanceState.getParcelable(DataTransition.QUEST_POINT);
        }

        // setting up controls
        if (tvHint != null && questPoint != null) {
            tvHint.setText(questPoint.getHintText());
        }
        Picasso.with(PointSearchActivity.this).load(questPoint.getHintImage()).into(imgHint);

        final Button button = (Button) findViewById(R.id.button_see_map);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PointSearchActivity.this, MapActivity.class);
                intent.putExtra(DataTransition.QUEST_POINT, questPoint);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(DataTransition.QUEST_POINT, questPoint);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        questPoint = inState.getParcelable(DataTransition.QUEST_POINT);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            tryAbort();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        tryAbort();
    }

    /**
     * Checks whether user has reached the point, and moves one to the task, if yes.
     *
     * @param view View on which user clicked. Never mind, cuz it's just an OnClickListener.
     */
    public void checkLocation(View view) {
        // stub: no real check here yet
        if (clickCount > 0) {
            // user is at the place
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(PointSearchActivity.this, R.string.not_there_yet, Toast.LENGTH_SHORT)
                    .show();
            ++clickCount;
        }
    }

    private void tryAbort() {
        QuestAbortPopup.createAndShow(PointSearchActivity.this);
    }
}

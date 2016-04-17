package com.piterquest.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.piterquest.R;

/**
 * Popup dialog asking user whether they really want to abort the quest.
 */
public class QuestAbortPopup {
    private QuestAbortPopup() {
    }

    public static void createAndShow(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.quest_abort_popup));
        builder.setPositiveButton(context.getString(R.string.abort), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, ListQuestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel), null);
        builder.show();
    }
}

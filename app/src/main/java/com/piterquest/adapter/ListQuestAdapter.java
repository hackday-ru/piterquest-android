package com.piterquest.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.piterquest.R;
import com.piterquest.data.Quest;

import java.util.ArrayList;

public class ListQuestAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Quest> quests;

    public ListQuestAdapter(Activity activity, ArrayList<Quest> quests) {
        this.activity = activity;
        this.quests = quests;
    }
    @Override
    public int getCount() {
        return quests.size();
    }

    @Override
    public Object getItem(int position) {
        return quests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.row_layout, null);

        ImageView questImageView = (ImageView) convertView.findViewById(R.id.quest_image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.quest_name);
        TextView infoTextView = (TextView) convertView.findViewById(R.id.quest_info);

        final Quest item = quests.get(position);

        nameTextView.setText(item.getInfo().getName());
        infoTextView.setText(item.getInfo().getDescription());
        questImageView.setImageResource(R.drawable.placeholder);
//        Picasso.with(activity.getApplicationContext()).load(item.getInfo().getImage())
//                .error(R.drawable.placeholder)
//                .placeholder(R.drawable.placeholder)
//                .into(questImageView);

        return convertView;
    }
}

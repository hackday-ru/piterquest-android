package com.piterquest.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}

package com.piterquest.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Quest implements Parcelable {

    private QuestInfo info;
    private List<QuestPoint> points;

    public Quest(QuestInfo info, List<QuestPoint> points) {
        this.info = info;
        this.points = points;
    }

    protected Quest(Parcel in) {
        info = in.readParcelable(QuestInfo.class.getClassLoader());
        points = new ArrayList<>();
        in.readList(points, QuestPoint.class.getClassLoader());
    }

    public static final Creator<Quest> CREATOR = new Creator<Quest>() {
        @Override
        public Quest createFromParcel(Parcel in) {
            return new Quest(in);
        }

        @Override
        public Quest[] newArray(int size) {
            return new Quest[size];
        }
    };

    public QuestInfo getInfo() {
        return info;
    }

    public void setInfo(QuestInfo info) {
        this.info = info;
    }

    public List<QuestPoint> getPoints() {
        return points;
    }

    public void setPoints(List<QuestPoint> points) {
        this.points = points;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(info, flags);
        dest.writeList(points);
    }
}

package com.piterquest.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Quest implements Parcelable {

    private QuestInfo info;
    private QuestPoint points;

    public Quest(QuestInfo info, QuestPoint points) {
        this.info = info;
        this.points = points;
    }

    protected Quest(Parcel in) {
        info = in.readParcelable(QuestInfo.class.getClassLoader());
        points = in.readParcelable(QuestPoint.class.getClassLoader());
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

    public QuestPoint getPoints() {
        return points;
    }

    public void setPoints(QuestPoint points) {
        this.points = points;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(info, flags);
        dest.writeParcelable(points, flags);
    }
}

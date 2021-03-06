package com.piterquest.data;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestPoint implements Parcelable {

    public QuestPoint(String hintText, double dest_lat, double dest_long, String hintImage,
                      boolean hasGpsHint, String taskText, String taskImage,
                      String solution) {
        this.hintText = hintText;
        this.dest_lat = dest_lat;
        this.dest_long = dest_long;
        this.hintImage = hintImage;
        this.hasGpsHint = hasGpsHint;
        this.taskText = taskText;
        this.taskImage = taskImage;
        this.solution = solution;
    }

    private String hintText;
    private String hintImage;
    private double dest_long;
    private double dest_lat;
    private boolean hasGpsHint;
    private String taskText;
    private String taskImage;
    private String solution;

    protected QuestPoint(Parcel in) {
        hintText = in.readString();
        hintImage = in.readString();
        dest_long = in.readDouble();
        dest_lat  = in.readDouble();
        hasGpsHint = in.readByte() != 0;
        taskText = in.readString();
        taskImage = in.readString();
        solution = in.readString();
    }


    public static final Creator<QuestPoint> CREATOR = new Creator<QuestPoint>() {
        @Override
        public QuestPoint createFromParcel(Parcel in) {
            return new QuestPoint(in);
        }

        @Override
        public QuestPoint[] newArray(int size) {
            return new QuestPoint[size];
        }
    };

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public String getHintImage() {
        return hintImage;
    }

    public void setHintImage(String hintImage) {
        this.hintImage = hintImage;
    }

    public boolean isHasGpsHint() {
        return hasGpsHint;
    }

    public void setHasGpsHint(boolean hasGpsHint) {
        this.hasGpsHint = hasGpsHint;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getTaskImage() {
        return taskImage;
    }

    public void setTaskImage(String taskImage) {
        this.taskImage = taskImage;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hintText);
        dest.writeString(hintImage);
        dest.writeDouble(dest_lat);
        dest.writeDouble(dest_long);
        dest.writeByte((byte) (hasGpsHint ? 1 : 0));
        dest.writeString(taskText);
        dest.writeString(taskImage);
        dest.writeString(solution);
    }

    public double getDest_long() {
        return dest_long;
    }

    public void setDest_long(double dest_long) {
        this.dest_long = dest_long;
    }

    public double getDest_lat() {
        return dest_lat;
    }

    public void setDest_lat(double dest_lat) {
        this.dest_lat = dest_lat;
    }
}

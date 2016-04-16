package com.piterquest.data;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestInfo implements Parcelable{

    private int id;
    private String name;
    private String description;
    private String image;

    public QuestInfo(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    protected QuestInfo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        image = in.readString();
    }

    public static final Creator<QuestInfo> CREATOR = new Creator<QuestInfo>() {
        @Override
        public QuestInfo createFromParcel(Parcel in) {
            return new QuestInfo(in);
        }

        @Override
        public QuestInfo[] newArray(int size) {
            return new QuestInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
    }
}
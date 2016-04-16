package com.piterquest.data;

public class Quest {

    private QuestInfo info;
    private QuestPoint points;

    public Quest(QuestInfo info, QuestPoint points) {
        this.info = info;
        this.points = points;
    }

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
}

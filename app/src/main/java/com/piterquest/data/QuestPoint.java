package com.piterquest.data;

public class QuestPoint {
    public QuestPoint(String hintText, String gps, String hintImageUrl,
                      boolean hasGpsHint, String taskText, String taskImageUrl,
                      String solution) {
        this.hintText = hintText;
        this.gps = gps;
        this.hintImageUrl = hintImageUrl;
        this.hasGpsHint = hasGpsHint;
        this.taskText = taskText;
        this.taskImageUrl = taskImageUrl;
        this.solution = solution;
    }

    private String hintText;
    private String hintImageUrl;
    private String gps;
    private boolean hasGpsHint;
    private String taskText;
    private String taskImageUrl;
    private String solution;

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public String getHintImageUrl() {
        return hintImageUrl;
    }

    public void setHintImageUrl(String hintImageUrl) {
        this.hintImageUrl = hintImageUrl;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
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

    public String getTaskImageUrl() {
        return taskImageUrl;
    }

    public void setTaskImageUrl(String taskImageUrl) {
        this.taskImageUrl = taskImageUrl;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}

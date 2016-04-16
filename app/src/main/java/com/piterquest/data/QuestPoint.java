package com.piterquest.data;

public class QuestPoint {

    public QuestPoint(String hintText, String gps, String hintImage,
                      boolean hasGpsHint, String taskText, String taskImage,
                      String solution) {
        this.hintText = hintText;
        this.gps = gps;
        this.hintImage = hintImage;
        this.hasGpsHint = hasGpsHint;
        this.taskText = taskText;
        this.taskImage = taskImage;
        this.solution = solution;
    }

    private String hintText;
    private String hintImage;
    private String gps;
    private boolean hasGpsHint;
    private String taskText;
    private String taskImage;
    private String solution;

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
}

package com.example.allocation.dto;

public class AssignedEntry {
    private String taskId;
    private String runner;
    private String label;
    private String distance;
    private String colorCode;

    public AssignedEntry() {}
    public AssignedEntry(String taskId, String runner, String label, String distance, String colorCode) {
        this.taskId = taskId;
        this.runner = runner;
        this.label = label;
        this.distance = distance;
        this.colorCode = colorCode;
    }
    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }
    public String getRunner() { return runner; }
    public void setRunner(String runner) { this.runner = runner; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getDistance() { return distance; }
    public void setDistance(String distance) { this.distance = distance; }
    public String getColorCode() { return colorCode; }
    public void setColorCode(String colorCode) { this.colorCode = colorCode; }
}

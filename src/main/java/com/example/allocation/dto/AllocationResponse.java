package com.example.allocation.dto;

import java.util.ArrayList;
import java.util.List;

public class AllocationResponse {
    private String statusCode;
    private String statusMessage;
    private List<AssignedEntry> readyTasks = new ArrayList<>();

    public String getStatusCode() { return statusCode; }
    public void setStatusCode(String statusCode) { this.statusCode = statusCode; }
    public String getStatusMessage() { return statusMessage; }
    public void setStatusMessage(String statusMessage) { this.statusMessage = statusMessage; }
    public List<AssignedEntry> getReadyTasks() { return readyTasks; }
    public void setReadyTasks(List<AssignedEntry> readyTasks) { this.readyTasks = readyTasks; }
}

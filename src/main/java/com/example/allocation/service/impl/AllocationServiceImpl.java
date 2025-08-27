package com.example.allocation.service.impl;

import com.example.allocation.dto.AllocationRequest;
import com.example.allocation.dto.AllocationResponse;
import com.example.allocation.dto.AssignedEntry;
import com.example.allocation.entity.Runner;
import com.example.allocation.entity.Task;
import com.example.allocation.repository.RunnerRepository;
import com.example.allocation.repository.TaskRepository;
import com.example.allocation.service.AllocationService;
import com.example.allocation.util.GeoUtils;
import com.example.allocation.util.HungarianAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AllocationServiceImpl implements AllocationService {

    private final TaskRepository taskRepository;
    private final RunnerRepository runnerRepository;

    public AllocationServiceImpl(TaskRepository taskRepository, RunnerRepository runnerRepository) {
        this.taskRepository = taskRepository;
        this.runnerRepository = runnerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AllocationResponse allocate(AllocationRequest request) {
        AllocationResponse response = new AllocationResponse();

        List<Task> readyTasks = taskRepository.findByStatus("READY");
        List<Runner> runners = runnerRepository.findAll();

        if (readyTasks.isEmpty()) {
            response.setStatusCode("0");
            response.setStatusMessage("NO READY TASKS");
            return response;
        }
        if (runners.isEmpty()) {
            response.setStatusCode("0");
            response.setStatusMessage("NO RUNNERS FOUND");
            return response;
        }

        // Build cost matrix (rows: tasks, cols: runners) as distances in KM
        int m = readyTasks.size();
        int n = runners.size();
        double[][] cost = new double[m][n];
        for (int i = 0; i < m; i++) {
            Task t = readyTasks.get(i);
            for (int j = 0; j < n; j++) {
                Runner r = runners.get(j);
                cost[i][j] = GeoUtils.haversineKm(
                        t.getLatitude(), t.getLongitude(),
                        r.getLatitude(), r.getLongitude());
            }
        }

        int[] assignment = HungarianAlgorithm.assign(cost);

        DecimalFormat df = new DecimalFormat("0.00");
        List<AssignedEntry> entries = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int j = assignment[i];
            if (j >= 0 && j < n) {
                Task t = readyTasks.get(i);
                Runner r = runners.get(j);
                double dist = cost[i][j];
                String label = t.getCustId() + " | " + t.getStatus();
                entries.add(new AssignedEntry(
                        String.valueOf(t.getId()),
                        r.getUsername(),
                        label,
                        df.format(dist) + " Km",
                        colorForDistance(dist)
                ));
            }
        }

        // Optional: keep output sorted by taskId asc
        entries.sort(Comparator.comparing(AssignedEntry::getTaskId));

        response.setStatusCode(entries.isEmpty() ? "0" : "1");
        response.setStatusMessage(entries.isEmpty() ? "NO ENTRIES FOUND" : "SUCCESS");
        response.setReadyTasks(entries);
        return response;
    }

    private String colorForDistance(double km) {
        if (km <= 1.0) return "#077411";     // dark green
        if (km <= 3.0) return "#06D001";     // light green
        return "#FF8225";                    // orange
    }
}

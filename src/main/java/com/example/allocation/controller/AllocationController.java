package com.example.allocation.controller;

import com.example.allocation.dto.AllocationRequest;
import com.example.allocation.dto.AllocationResponse;
import com.example.allocation.service.AllocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping("/assignments")
    public ResponseEntity<AllocationResponse> assign(@Valid @RequestBody AllocationRequest request) {
        AllocationResponse response = allocationService.allocate(request);
        return ResponseEntity.ok(response);
        // demo
    }
}

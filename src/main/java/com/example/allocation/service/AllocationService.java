package com.example.allocation.service;

import com.example.allocation.dto.AllocationRequest;
import com.example.allocation.dto.AllocationResponse;

public interface AllocationService {
    AllocationResponse allocate(AllocationRequest request);
}

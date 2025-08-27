package com.example.allocation.dto;

import jakarta.validation.constraints.NotBlank;

public class AllocationRequest {
    @NotBlank
    private String type;
    @NotBlank
    private String org;
    @NotBlank
    private String version;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getOrg() { return org; }
    public void setOrg(String org) { this.org = org; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
}

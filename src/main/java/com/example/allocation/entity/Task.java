package com.example.allocation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cust_id")
    private Integer custId;

    @Column(name = "current_runner")
    private String currentRunner;

    @Column(name = "status")
    private String status;

    @Column(name = "latitude", columnDefinition = "decimal(11,8)")
    private Double latitude;

    @Column(name = "longitude", columnDefinition = "decimal(11,8)")
    private Double longitude;

    @Column(name = "alloc_time")
    private LocalDateTime allocTime;

    @Column(name = "accept_time")
    private LocalDateTime acceptTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getCustId() { return custId; }
    public void setCustId(Integer custId) { this.custId = custId; }
    public String getCurrentRunner() { return currentRunner; }
    public void setCurrentRunner(String currentRunner) { this.currentRunner = currentRunner; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public LocalDateTime getAllocTime() { return allocTime; }
    public void setAllocTime(LocalDateTime allocTime) { this.allocTime = allocTime; }
    public LocalDateTime getAcceptTime() { return acceptTime; }
    public void setAcceptTime(LocalDateTime acceptTime) { this.acceptTime = acceptTime; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getModifyTime() { return modifyTime; }
    public void setModifyTime(LocalDateTime modifyTime) { this.modifyTime = modifyTime; }
}

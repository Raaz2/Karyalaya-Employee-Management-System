package com.karyalaya.KaryalayaEMS.controller;

import com.karyalaya.KaryalayaEMS.model.PerformanceReview;
import com.karyalaya.KaryalayaEMS.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
    @Autowired
    private PerformanceService performanceService;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<PerformanceReview> addPerformanceReview(@RequestBody PerformanceReview review) {
        review.setReviewDate(LocalDate.now());
        return ResponseEntity.ok(performanceService.addPerformanceReview(review));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<PerformanceReview> updatePerformanceReview(@PathVariable Integer id, @RequestBody PerformanceReview review) {
        return ResponseEntity.ok(performanceService.updatePerformanceReview(id, review));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PerformanceReview>> getPerformanceReviewsByEmployee(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(performanceService.getPerformanceReviewsByEmployee(employeeId));
    }
}


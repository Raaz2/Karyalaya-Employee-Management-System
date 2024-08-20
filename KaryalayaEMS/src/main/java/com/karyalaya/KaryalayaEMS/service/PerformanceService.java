package com.karyalaya.KaryalayaEMS.service;


import com.karyalaya.KaryalayaEMS.model.PerformanceReview;
import com.karyalaya.KaryalayaEMS.model.User;
import com.karyalaya.KaryalayaEMS.repository.PerformanceReviewRepository;
import com.karyalaya.KaryalayaEMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    private UserRepository UserRepository;

    public PerformanceReview addPerformanceReview(PerformanceReview review) {
        User User = UserRepository.findById(review.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        review.setEmployee(User);
        return performanceReviewRepository.save(review);
    }

    public PerformanceReview updatePerformanceReview(Integer id, PerformanceReview review) {
        PerformanceReview existingReview = performanceReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setId(id);
        return performanceReviewRepository.save(review);
    }

    public List<PerformanceReview> getPerformanceReviewsByEmployee(Integer UserId) {
        User User = UserRepository.findById(UserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return performanceReviewRepository.findByEmployee(User);
    }
}


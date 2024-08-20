package com.karyalaya.KaryalayaEMS.repository;

import com.karyalaya.KaryalayaEMS.model.PerformanceReview;
import com.karyalaya.KaryalayaEMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Integer> {
    List<PerformanceReview> findByEmployee(User employee);
}

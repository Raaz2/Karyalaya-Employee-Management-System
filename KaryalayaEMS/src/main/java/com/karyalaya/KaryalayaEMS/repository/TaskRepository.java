package com.karyalaya.KaryalayaEMS.repository;

import com.karyalaya.KaryalayaEMS.model.Task;
import com.karyalaya.KaryalayaEMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByAssignedTo(User employee);
}

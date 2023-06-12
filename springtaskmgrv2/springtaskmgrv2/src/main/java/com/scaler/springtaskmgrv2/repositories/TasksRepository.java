package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByCompleted(Boolean completed);

    //    Ideally this is 'business logic' terminology and should be in a service layer
    @Query("select t from tasks t where t.dueDate < current_date and t.completed = false")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByCompletedAndDueDateLessThan(Boolean completed, Date dueDate);

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String title);


}

package net.clima.demo.repository;

import net.clima.demo.model.entity.GoalKindsValues.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
}

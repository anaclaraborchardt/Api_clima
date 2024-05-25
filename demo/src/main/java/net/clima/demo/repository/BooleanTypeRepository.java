package net.clima.demo.repository;

import net.clima.demo.model.entity.GoalKindsValues.BooleanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooleanTypeRepository extends JpaRepository<BooleanType, Long> {
}

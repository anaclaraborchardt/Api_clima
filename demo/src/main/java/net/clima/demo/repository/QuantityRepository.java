package net.clima.demo.repository;

import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long> {
}

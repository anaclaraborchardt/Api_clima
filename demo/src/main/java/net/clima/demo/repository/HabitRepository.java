package net.clima.demo.repository;

import net.clima.demo.model.entity.Habits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habits, Long> {

    public List<Habits> findAllByUser_Id(Long id);
}

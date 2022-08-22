package com.example.api.model.Achievement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unused")
public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
}

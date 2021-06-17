package com.osomapps.pt.goals;

import com.osomapps.pt.token.InUserGoal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InUserGoalRepository extends JpaRepository<InUserGoal, Long> {

    List<InUserGoal> findByGoalId(Long goalId);
}

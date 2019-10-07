package com.burger.repositories;

import com.burger.models.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> {
    List<Command> findByDone(int done);
}

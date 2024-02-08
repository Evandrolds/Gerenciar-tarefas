package com.evandro.gerenciar.repositories;

import com.evandro.gerenciar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

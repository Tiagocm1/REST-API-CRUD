package br.com.dev.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.dev.api.rest.entity.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}

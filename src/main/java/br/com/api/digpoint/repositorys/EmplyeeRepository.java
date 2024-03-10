package br.com.api.digpoint.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.digpoint.models.Emplyee;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface EmplyeeRepository extends JpaRepository<Emplyee, UUID> {
  Optional<Emplyee> findByCode(String code);
}

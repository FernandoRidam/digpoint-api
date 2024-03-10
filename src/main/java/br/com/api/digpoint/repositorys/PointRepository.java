package br.com.api.digpoint.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.digpoint.models.Point;
import java.util.UUID;

@Repository
public interface PointRepository extends JpaRepository<Point, UUID> {}

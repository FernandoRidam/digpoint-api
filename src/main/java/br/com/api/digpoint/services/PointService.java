package br.com.api.digpoint.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import br.com.api.digpoint.models.Emplyee;
import br.com.api.digpoint.models.Point;
import br.com.api.digpoint.repositorys.PointRepository;

@Service
public class PointService {
  @Autowired
  PointRepository pointRepository;

  public Point savePoint(UUID companyId, Emplyee emplyee) {

    var point = new Point();

    point.setEmployee(emplyee);

    return pointRepository.save(point);
  }
}

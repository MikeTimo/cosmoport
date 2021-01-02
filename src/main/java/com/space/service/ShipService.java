package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ShipService {
    public Page<Ship> allShips(Specification<Ship> shipSpecification, Pageable pageable);

    public List countShips(Specification<Ship> shipSpecification);

    public Ship saveShip(Ship ship);

    public Ship getShip(Long id);

    public void deleteShip(Long id);

    Specification<Ship> filterByName(String name);

    Specification<Ship> filterByPlanet(String planet);

    Specification<Ship> filterByShipType(ShipType shipType);

    Specification<Ship> filterByDate(Long after, Long before);

    Specification<Ship> filterByUsage(Boolean isUsed);

    Specification<Ship> filterBySpeed(Double min, Double max);

    Specification<Ship> filterByCrewSize(Integer min, Integer max);

    Specification<Ship> filterByRating(Double min, Double max);

    public Long checkAndParseId(String id);

    public void checkShipParams(Ship ship);

    public Ship editShip(Long id, Ship ship);
}

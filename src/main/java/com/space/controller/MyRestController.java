package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    private ShipService shipService;

    @Autowired
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/ships")
    @ResponseStatus(HttpStatus.OK)
    public List<Ship> showAllShips(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "planet", required = false) String planet,
                                   @RequestParam(value = "shipType", required = false) ShipType shipType,
                                   @RequestParam(value = "after", required = false) Long after,
                                   @RequestParam(value = "before", required = false) Long before,
                                   @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                   @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                   @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                   @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                   @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                   @RequestParam(value = "minRating", required = false) Double minRating,
                                   @RequestParam(value = "maxRating", required = false) Double maxRating,
                                   @RequestParam(value = "order", required = false, defaultValue = "ID") ShipOrder order,
                                   @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));

        return shipService.allShips(
                Specification.where(shipService.filterByName(name)
                        .and(shipService.filterByPlanet(planet))
                        .and(shipService.filterByShipType(shipType))
                        .and(shipService.filterByDate(after, before))
                        .and(shipService.filterByUsage(isUsed))
                        .and(shipService.filterBySpeed(minSpeed, maxSpeed))
                        .and(shipService.filterByCrewSize(minCrewSize, maxCrewSize))
                        .and(shipService.filterByRating(minRating, maxRating))), pageable).getContent();
    }

    @GetMapping("/ships/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer showCountShips(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "planet", required = false) String planet,
                               @RequestParam(value = "shipType", required = false) ShipType shipType,
                               @RequestParam(value = "after", required = false) Long after,
                               @RequestParam(value = "before", required = false) Long before,
                               @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                               @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                               @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                               @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                               @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                               @RequestParam(value = "minRating", required = false) Double minRating,
                               @RequestParam(value = "maxRating", required = false) Double maxRating) {
        return shipService.countShips(
                Specification.where(shipService.filterByName(name)
                        .and(shipService.filterByPlanet(planet))
                        .and(shipService.filterByShipType(shipType))
                        .and(shipService.filterByDate(after, before))
                        .and(shipService.filterByUsage(isUsed))
                        .and(shipService.filterBySpeed(minSpeed, maxSpeed))
                        .and(shipService.filterByCrewSize(minCrewSize, maxCrewSize))
                        .and(shipService.filterByRating(minRating, maxRating)))).size();
    }

    @PostMapping("/ships")
    @ResponseStatus(HttpStatus.OK)
    public Ship addNewShip(@RequestBody Ship ship) {
        return shipService.saveShip(ship);
    }

    @GetMapping("/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship getShip(@PathVariable (value = "id") String strId) {
        Long id = shipService.checkAndParseId(strId);
        return shipService.getShip(id);
    }

    @PostMapping("/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship updateShip(@PathVariable(value = "id") String strId, @RequestBody Ship ship) {
        Long id = shipService.checkAndParseId(strId);
        return shipService.editShip(id, ship);
    }

    @DeleteMapping("/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShip(@PathVariable(value = "id") String id) {
        Long longId = shipService.checkAndParseId(id);
        shipService.deleteShip(longId);
    }
}

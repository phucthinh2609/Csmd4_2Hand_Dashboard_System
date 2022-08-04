package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.LocationRegion;
import com.mvpt.model.Unit;
import com.mvpt.model.dto.UnitDTO;
import com.mvpt.service.locationRegion.LocationRegionService;
import com.mvpt.service.unit.UnitService;
import com.mvpt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/units")
public class UnitRestController {

    @Autowired
    UnitService unitService;

    @Autowired
    LocationRegionService locationRegionService;

    @Autowired
    AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UnitDTO> unitDTOList = unitService.getAllUnitDTO();
        return new ResponseEntity<>(unitDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<UnitDTO> unitDTO = unitService.getUnitDTOById(id);

        return new ResponseEntity<>(unitDTO.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@Validated @RequestBody UnitDTO unitDTO, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<UnitDTO> currentUnitDTO = unitService.getUnitDTOByCodeAndIdIsNot(unitDTO.getCode(), Long.parseLong(unitDTO.getId()));

        if (currentUnitDTO.isPresent()) {
            throw new DataInputException("Code already exists!!!");
        }

        LocationRegion newLocationRegion = locationRegionService.save(unitDTO.getLocationRegion().toLocationRegion());
        unitDTO.setLocationRegion(newLocationRegion.toLocationRegionDTO());

        Unit newUnit = unitDTO.toUnit();

        unitService.save(newUnit);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}

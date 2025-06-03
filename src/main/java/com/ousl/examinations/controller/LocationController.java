package com.ousl.examinations.controller;

import com.ousl.examinations.model.Location;
import com.ousl.examinations.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
@CrossOrigin(maxAge = 3360,origins= "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.addLocation(location));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        return ResponseEntity.ok(locationService.updateLocation(id, location));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}

package com.ousl.examinations.service.Impl;

import com.ousl.examinations.model.Location;
import com.ousl.examinations.repository.LocationRepository;
import com.ousl.examinations.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        locationRepository.findAll().forEach(locations::add);
        return locations;
    }

    @Override
    public Location getById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long id, Location updatedLocation) {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            location.setName(updatedLocation.getName());
            return locationRepository.save(location);
        }
        return null;
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);

    }
}

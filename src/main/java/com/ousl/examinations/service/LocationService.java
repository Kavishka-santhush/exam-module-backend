package com.ousl.examinations.service;
import com.ousl.examinations.payload.request.LocationRequest;
import com.ousl.examinations.model.Location;
import com.ousl.examinations.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocationService {
   List<Location> getAllLocations();
   Location getById(Long id);
   Location addLocation(Location location);
   Location updateLocation(Long id, Location updatedLocation);
   void deleteLocation(Long id);
}
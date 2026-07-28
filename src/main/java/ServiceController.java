/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import co.za.obcodes.local_service_directory_api.model.Service;
import co.za.obcodes.local_service_directory_api.repository.CategoryRepository;
import co.za.obcodes.local_service_directory_api.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Obakeng Phale
 */

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    public ServiceController(ServiceRepository serviceRepository,
                             CategoryRepository categoryRepository) {
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Service>> getAllServices(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search) {

        List<Service> services;

        if (categoryId != null) {
            services = serviceRepository.findByCategoryId(categoryId);
        } else if (search != null && !search.isBlank()) {
            services = serviceRepository.findByNameContainingIgnoreCase(search);
        } else {
            services = serviceRepository.findAll();
        }

        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createService(@RequestBody Service service) {
        if (service.getCategory() == null || service.getCategory().getId() == null) {
            return ResponseEntity.badRequest()
                    .body("Category ID is required");
        }

        if (!categoryRepository.existsById(service.getCategory().getId())) {
            return ResponseEntity.badRequest()
                    .body("Category with ID " + service.getCategory().getId() + " does not exist");
        }

        Service savedService = serviceRepository.save(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id,
                                            @RequestBody Service serviceDetails) {
        Optional<Service> optionalService = serviceRepository.findById(id);

        if (optionalService.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Service existingService = optionalService.get();
        existingService.setName(serviceDetails.getName());
        existingService.setDescription(serviceDetails.getDescription());
        existingService.setContactNumber(serviceDetails.getContactNumber());
        existingService.setAddress(serviceDetails.getAddress());
        existingService.setOperatingHours(serviceDetails.getOperatingHours());

        if (serviceDetails.getCategory() != null && serviceDetails.getCategory().getId() != null) {
            if (!categoryRepository.existsById(serviceDetails.getCategory().getId())) {
                return ResponseEntity.badRequest()
                        .body("Category with ID " + serviceDetails.getCategory().getId() + " does not exist");
            }
            existingService.setCategory(serviceDetails.getCategory());
        }

        Service updatedService = serviceRepository.save(existingService);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (!serviceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        serviceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

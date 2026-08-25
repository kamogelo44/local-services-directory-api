/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.service;

import co.za.obcodes.local_service_directory_api.exception.ResourceNotFoundException;
import co.za.obcodes.local_service_directory_api.model.Category;
import co.za.obcodes.local_service_directory_api.model.Service;
import co.za.obcodes.local_service_directory_api.repository.CategoryRepository;
import co.za.obcodes.local_service_directory_api.repository.ServiceRepository;

import java.util.List;

/**
 *
 * @author Obakeng Phale
 */

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    public ServiceService(ServiceRepository serviceRepository,
                          CategoryRepository categoryRepository) {
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Service> getAllServices(Long categoryId, String search) {
        if (categoryId != null) {
            return serviceRepository.findByCategoryId(categoryId);
        }
        if (search != null && !search.isBlank()) {
            return serviceRepository.findByNameContainingIgnoreCase(search);
        }
        return serviceRepository.findAll();
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + id));
    }

    public Service createService(Service service) {
        Long categoryId = service.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
        service.setCategory(category);
        return serviceRepository.save(service);
    }

    public Service updateService(Long id, Service serviceDetails) {
        Service existingService = getServiceById(id);

        existingService.setName(serviceDetails.getName());
        existingService.setDescription(serviceDetails.getDescription());
        existingService.setContactNumber(serviceDetails.getContactNumber());
        existingService.setAddress(serviceDetails.getAddress());
        existingService.setOperatingHours(serviceDetails.getOperatingHours());

        if (serviceDetails.getCategory() != null && serviceDetails.getCategory().getId() != null) {
            Category category = categoryRepository.findById(serviceDetails.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id " + serviceDetails.getCategory().getId()));
            existingService.setCategory(category);
        }

        return serviceRepository.save(existingService);
    }

    public void deleteService(Long id) {
        Service service = getServiceById(id);
        serviceRepository.delete(service);
    }
}

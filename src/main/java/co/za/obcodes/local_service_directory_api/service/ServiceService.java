/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.service;

import co.za.obcodes.local_service_directory_api.dto.ServiceDTO;
import co.za.obcodes.local_service_directory_api.exception.ResourceNotFoundException;
import co.za.obcodes.local_service_directory_api.model.Category;
import co.za.obcodes.local_service_directory_api.model.Service;
import co.za.obcodes.local_service_directory_api.repository.CategoryRepository;
import co.za.obcodes.local_service_directory_api.repository.ServiceRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ServiceDTO> getAllServices(Long categoryId, String search) {
        List<Service> services;

        if (categoryId != null) {
            services = serviceRepository.findByCategoryId(categoryId);
        } else if (search != null && !search.isBlank()) {
            services = serviceRepository.findByNameContainingIgnoreCase(search);
        } else {
            services = serviceRepository.findAll();
        }

        return services.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ServiceDTO getServiceById(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + id));
        return toDTO(service);
    }

    public ServiceDTO createService(Service service) {
        Long categoryId = service.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
        service.setCategory(category);
        Service savedService = serviceRepository.save(service);
        return toDTO(savedService);
    }

    public ServiceDTO updateService(Long id, Service serviceDetails) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + id));

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

        Service updatedService = serviceRepository.save(existingService);
        return toDTO(updatedService);
    }

    public void deleteService(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + id));
        serviceRepository.delete(service);
    }

    private ServiceDTO toDTO(Service service) {
        Category category = service.getCategory();
        return new ServiceDTO(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getContactNumber(),
                service.getAddress(),
                service.getOperatingHours(),
                category != null ? category.getId() : null,
                category != null ? category.getName() : null
        );
    }
}
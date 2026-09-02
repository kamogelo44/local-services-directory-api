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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
/**
 *
 * @author Obakeng Phale
 */
@ExtendWith(MockitoExtension.class)
class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ServiceService serviceService;

    private Category category;
    private Service service;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Clinics");

        service = new Service();
        service.setId(1L);
        service.setName("CityMed Clinic");
        service.setDescription("24-hour medical center");
        service.setContactNumber("011-555-0199");
        service.setAddress("123 Main Street");
        service.setOperatingHours("Mon-Sun: 08:00-20:00");
        service.setCategory(category);
    }

    @Test
    void getServiceById_WhenServiceExists_ShouldReturnServiceDTO() {
        // Arrange
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));

        // Act
        ServiceDTO result = serviceService.getServiceById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("CityMed Clinic", result.getName());
        assertEquals(1L, result.getCategoryId());
        assertEquals("Clinics", result.getCategoryName());
    }

    @Test
    void getServiceById_WhenServiceDoesNotExist_ShouldThrowException() {
        // Arrange
        when(serviceRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            serviceService.getServiceById(999L);
        });
    }

    @Test
    void createService_WhenCategoryExists_ShouldSaveAndReturnDTO() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(serviceRepository.save(any(Service.class))).thenReturn(service);

        // Act
        ServiceDTO result = serviceService.createService(service);

        // Assert
        assertNotNull(result);
        assertEquals("CityMed Clinic", result.getName());
        assertEquals(1L, result.getCategoryId());
        assertEquals("Clinics", result.getCategoryName());
    }

    @Test
    void createService_WhenCategoryDoesNotExist_ShouldThrowException() {
        // Arrange
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        Service invalidService = new Service();
        invalidService.setName("Ghost Service");
        invalidService.setCategory(new Category());
        invalidService.getCategory().setId(999L);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            serviceService.createService(invalidService);
        });
    }

    @Test
    void deleteService_WhenServiceExists_ShouldDeleteWithoutException() {
        // Arrange
        when(serviceRepository.findById(1L)).thenReturn(Optional.of(service));

        // Act & Assert
        serviceService.deleteService(1L);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.service;

import co.za.obcodes.local_service_directory_api.dto.CategoryDTO;
import co.za.obcodes.local_service_directory_api.exception.ResourceNotFoundException;
import co.za.obcodes.local_service_directory_api.model.Category;
import co.za.obcodes.local_service_directory_api.repository.CategoryRepository;
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
import static org.mockito.Mockito.when;

/**
 *
 * @author Obakeng Phale
 */
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Clinics");
    }

    @Test
    void getCategoryById_WhenCategoryExists_ShouldReturnCategoryDTO() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Act
        CategoryDTO result = categoryService.getCategoryById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clinics", result.getName());
    }

    @Test
    void getCategoryById_WhenCategoryDoesNotExist_ShouldThrowException() {
        // Arrange
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.getCategoryById(999L);
        });
    }

    @Test
    void createCategory_ShouldSaveAndReturnDTO() {
        // Arrange
        when(categoryRepository.save(category)).thenReturn(category);

        // Act
        CategoryDTO result = categoryService.createCategory(category);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clinics", result.getName());
    }

    @Test
    void deleteCategory_WhenCategoryExists_ShouldDeleteWithoutException() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Act & Assert — no exception should be thrown
        categoryService.deleteCategory(1L);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.repository;

/**
 *
 * @author Obakeng Phale
 */

import co.za.obcodes.local_service_directory_api.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    
    List<Service> findByCategoryId(Long categoryId);
    
    List<Service> findByNameContainingIgnoreCase(String keyword);
}

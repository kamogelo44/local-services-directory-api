/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.repository;

import co.za.obcodes.local_service_directory_api.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Obakeng Phale
 */

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    Page<Service> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Service> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}

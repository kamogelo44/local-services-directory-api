package co.za.obcodes.local_service_directory_api.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Obakeng Phale
 */
@Entity
@Table(name = "services")
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Service name is required")
    @Size(max = 150, message = "Service name must be 150 characters or less")
    @Column(nullable = false, length = 150)
    private String name;
    
    @Size(max = 500, message = "Description must be 500 characters or less")
    @Column(length = 500)
    private String description;
    
    @Size(max = 20, message = "Contact number must be 20 characters or less")
    @Column(name = "contact_number", length = 20)
    private String contactNumber;
    
    @Size(max = 300, message = "Address must be 300 characters or less")
    @Column(length = 300)
    private String address;
    
    @Size(max = 200, message = "Operating hours must be 200 characters or less")
    @Column(name = "operating_hours", length = 200)
    private String operatingHours;
    
    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    
    // JPA-required no-arg constructor
    public Service() {
    }
    
    // Convenience constructor
    public Service(String name, String description, String contactNumber, 
                   String address, String operatingHours, Category category) {
        this.name = name;
        this.description = description;
        this.contactNumber = contactNumber;
        this.address = address;
        this.operatingHours = operatingHours;
        this.category = category;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getOperatingHours() { return operatingHours; }
    public void setOperatingHours(String operatingHours) { this.operatingHours = operatingHours; }
    
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}

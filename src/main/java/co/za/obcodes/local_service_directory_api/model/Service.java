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
    
    @Column(nullable = false, length = 150)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    @Column(name = "contact_number", length = 20)
    private String contactNumber;
    
    @Column(length = 300)
    private String address;
    
    @Column(name = "operating_hours", length = 200)
    private String operatingHours;
    
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

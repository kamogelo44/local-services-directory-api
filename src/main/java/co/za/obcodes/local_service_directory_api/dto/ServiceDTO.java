/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.dto;

/**
 *
 * @author Obakeng Phale
 */

public class ServiceDTO {

    private Long id;
    private String name;
    private String description;
    private String contactNumber;
    private String address;
    private String operatingHours;
    private Long categoryId;
    private String categoryName;

    public ServiceDTO() {
    }

    public ServiceDTO(Long id, String name, String description,
                      String contactNumber, String address,
                      String operatingHours, Long categoryId,
                      String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.contactNumber = contactNumber;
        this.address = address;
        this.operatingHours = operatingHours;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

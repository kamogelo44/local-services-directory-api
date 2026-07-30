/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.za.obcodes.local_service_directory_api.config;

/**
 *
 * @author Obakeng Phale
 */

import co.za.obcodes.local_service_directory_api.model.Category;
import co.za.obcodes.local_service_directory_api.model.Service;
import co.za.obcodes.local_service_directory_api.repository.CategoryRepository;
import co.za.obcodes.local_service_directory_api.repository.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;

    public DataSeeder(CategoryRepository categoryRepository,
                      ServiceRepository serviceRepository) {
        this.categoryRepository = categoryRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            return;
        }

        Category clinics = categoryRepository.save(new Category("Clinics"));
        Category taxiRanks = categoryRepository.save(new Category("Taxi Ranks"));
        Category govOffices = categoryRepository.save(new Category("Government Offices"));
        Category libraries = categoryRepository.save(new Category("Libraries"));
        Category policeStations = categoryRepository.save(new Category("Police Stations"));

        serviceRepository.save(new Service(
                "CityMed Clinic",
                "24-hour medical center with emergency room and pharmacy",
                "011-555-0199",
                "123 Main Street, Soweto",
                "Mon-Sun: 00:00-23:59",
                clinics
        ));

        serviceRepository.save(new Service(
                "Soweto Community Health Centre",
                "Public health services including vaccinations and TB screening",
                "011-555-0455",
                "78 Vilakazi Street, Soweto",
                "Mon-Fri: 07:00-17:00",
                clinics
        ));

        serviceRepository.save(new Service(
                "Phomolong Primary Healthcare",
                "Basic primary care, antenatal services, and HIV counseling",
                "011-555-0678",
                "12 Masilela Street, Phomolong",
                "Mon-Fri: 07:30-16:00, Sat: 08:00-12:00",
                clinics
        ));

        serviceRepository.save(new Service(
                "Bree Street Taxi Rank",
                "Main taxi rank serving Johannesburg CBD and Soweto routes",
                "011-555-0300",
                "45 Bree Street, Johannesburg CBD",
                "Mon-Sat: 05:00-21:00, Sun: 06:00-18:00",
                taxiRanks
        ));

        serviceRepository.save(new Service(
                "Noord Street Taxi Rank",
                "Long-distance taxis to Pretoria, Rustenburg, and Polokwane",
                "011-555-0311",
                "1 Noord Street, Johannesburg CBD",
                "Mon-Sun: 04:00-22:00",
                taxiRanks
        ));

        serviceRepository.save(new Service(
                "Home Affairs - Soweto Branch",
                "ID applications, passports, birth and death certificates",
                "012-555-6000",
                "89 Koma Road, Jabulani, Soweto",
                "Mon-Fri: 08:00-15:30",
                govOffices
        ));

        serviceRepository.save(new Service(
                "SASSA Soweto Local Office",
                "Social grant applications, inquiries, and payment support",
                "0800-601-011",
                "1900 Chris Hani Road, Klipspruit, Soweto",
                "Mon-Fri: 08:00-16:00",
                govOffices
        ));

        serviceRepository.save(new Service(
                "Soweto Library - Diepkloof",
                "Public library with free WiFi, study spaces, and community programs",
                "011-555-0876",
                "1234 Immink Drive, Diepkloof, Soweto",
                "Mon-Fri: 09:00-17:00, Sat: 09:00-13:00",
                libraries
        ));

        serviceRepository.save(new Service(
                "Protea Glen Library",
                "Community library with children's reading programs and computer access",
                "011-555-0900",
                "456 Protea Boulevard, Protea Glen, Soweto",
                "Mon-Fri: 10:00-18:00, Sat: 09:00-14:00",
                libraries
        ));

        serviceRepository.save(new Service(
                "Soweto Police Station",
                "SAPS station serving the Soweto community",
                "011-555-1000",
                "1 Mooki Street, Orlando, Soweto",
                "Mon-Sun: 00:00-23:59",
                policeStations
        ));

        serviceRepository.save(new Service(
                "Moroka Police Station",
                "SAPS station with detective branch and victim support unit",
                "011-555-1100",
                "234 Moroka Road, Jabavu, Soweto",
                "Mon-Sun: 00:00-23:59",
                policeStations
        ));

        System.out.println("Database seeded with " + categoryRepository.count() +
                " categories and " + serviceRepository.count() + " services.");
    }
}

package com.vortex.challenge.configurations;

import com.github.javafaker.Faker;
import com.vortex.challenge.dtos.CreateEmployeeDTO;
import com.vortex.challenge.entities.*;
import com.vortex.challenge.repositories.*;
import com.vortex.challenge.services.DepartmentService;
import com.vortex.challenge.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    Faker faker;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    JobHistoryRepository jobHistoryRepository;
    @Autowired
    DepartmentService departmentService;

    @Override
    public void run(String... args) throws Exception {

        CreateEmployeeDTO newEmp = new CreateEmployeeDTO();
        Random random = new Random();

        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Job job = new Job();
            job.setJobTitle(faker.job().title());
            job.setMaxSalary(faker.number().randomDouble(2, 1100, 12000));
            job.setMinSalary(faker.number().randomDouble(2, 1000, 5000));
            jobRepository.save(job);
            jobList.add(job);
        }

        Employee manager = new Employee();
        manager.setFirstName("Principal Manager");
        manager.setSalary(5000D);
        manager.setJobId(jobList.get(1));
        employeeRepository.save(manager);


        Region region[] = {
                new Region(faker.lordOfTheRings().location()),
                new Region(faker.lordOfTheRings().location()),
                new Region(faker.lordOfTheRings().location())};
        for (Region reg : region) {
            regionRepository.save(reg);
        }

        List<Country> countryList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Country country = new Country();

            country.setName(faker.country().name());
            country.setRegion(region[random.nextInt(3)]);
            countryRepository.save(country);
            countryList.add(country);
        }

        List<Location> locationList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Location location = new Location();
            location.setCountry(countryList.get(random.nextInt(5)));
            location.setCity(faker.nation().capitalCity());
            location.setPostalCode(random.nextInt(5000));
            location.setStateProvince(faker.address().state());
            location.setStreet(faker.address().streetAddress());
            locationRepository.save(location);
            locationList.add(location);
        }

        List<Department> departmentList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {

            Department department = new Department();
            department.setName(faker.job().field());
            department.setLocation(locationList.get(random.nextInt(11)));
            department.setManagerId(manager);
            departmentRepository.save(department);
            departmentList.add(department);
        }


        for (int i = 0; i < 30; i++) {


            newEmp.setFirstName(faker.name().firstName());
            newEmp.setLastName(faker.name().lastName());
            newEmp.setSalary(faker.number().randomDouble(2, 500, 8000));
            newEmp.setEmail(faker.internet().emailAddress());
            newEmp.setPhone(faker.phoneNumber().phoneNumber());
            newEmp.setHireDate(faker.date().past(1500, TimeUnit.DAYS));
            newEmp.setJobId(jobList.get(random.nextInt(6)));
            newEmp.setSalary(faker.number().randomDouble(2, 1000, 12000));
            newEmp.setCommission(faker.number().randomDouble(2, 100, 1200));
            newEmp.setManager(manager);
            newEmp.setDepartment(departmentList.get(random.nextInt(13)));
            employeeService.create(newEmp);

        }

        for (int i = 0; i < 25; i++) {

            JobHistory jobHistory = new JobHistory();
            jobHistory.setEmployeeId(employeeRepository.findById(faker.number().numberBetween(1L, 30L)).get());
            jobHistory.setJobId(jobList.get(random.nextInt(6)));
            jobHistory.setDepartment(departmentList.get(random.nextInt(13)));
            jobHistory.setStartDate(faker.date().past(1500, TimeUnit.DAYS));
            jobHistoryRepository.save(jobHistory);
        }


    }
}

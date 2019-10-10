package com.VetSystem.RestAPI.controller;

import com.VetSystem.RestAPI.entity.Branch;
import com.VetSystem.RestAPI.entity.Checkup;
import com.VetSystem.RestAPI.entity.Checkupreport;
import com.VetSystem.RestAPI.entity.Company;
import com.VetSystem.RestAPI.entity.Employee;
import com.VetSystem.RestAPI.entity.Owner;
import com.VetSystem.RestAPI.entity.Pet;
import com.VetSystem.RestAPI.entity.Vet;
import com.VetSystem.RestAPI.repository.*;
import java.util.List;
import java.util.Optional;
//import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ApiEndpoint {

    private static Logger LOG = LogManager.getLogger();
    //final static Logger LOG = Logger.getLogger(ApiEndpoint.class.getName());
    
    @Autowired
    CompanyRepository companyService;

    @Autowired
    BranchRepository branchService;

    @Autowired
    EmployeeRepository employeeService;

    @Autowired
    VetRepository vetService;

    @Autowired
    OwnerRepository ownerService;

    @Autowired
    PetRepository petService;

    @Autowired
    CheckupRepository checkupService;

    @Autowired
    CheckupreportRepository checkupreportService;

    @GetMapping("/")
    public String Echo() {
        return "Hello world!!";
    }

    // <editor-fold defaultstate="collapsed" desc=" company methods ">
    //---------------------------- Retrieve company methods---------------------------------------------------------
    @GetMapping("/company/")
    public ResponseEntity<List<Company>> ListAllCompany() {
        List<Company> lTemp = companyService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }

    @GetMapping("/company/byName")
    public ResponseEntity<Company> GetCompanyByName(@RequestParam("name") String name) {

        Company Temp = companyService.findByName(name);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp, HttpStatus.OK);

    }

    @GetMapping("/company/byId")
    public ResponseEntity<Company> GetCompanyById(@RequestParam("id") int id) {

        Optional<Company> Temp = companyService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation company methods ---------------------------------------------------------
    @PostMapping("/company")
    public Company CreateCompany(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
        return companyService.save(company);
    }

    //---------------------------- Update company methoda ---------------------------------------------------------  
    @PutMapping("/company")
    public Company UpdateCompany(@RequestBody Company company) {

        return companyService.findById(company.getCompanyid()).map(
                c -> {
                    return companyService.save(company);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("test"));

    }

    //---------------------------- Delete company methoda ---------------------------------------------------------  
    @DeleteMapping("/company")
    public ResponseEntity<?> DeleteCompany(@RequestParam("companyId") int id) {
        LOG.info("deleting ocmpany -> " + id);
        return companyService.findById(id).map(
                c -> {
                    companyService.deleteById(id);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("test"));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" branch methods ">
    //---------------------------- Retrieve branch methods---------------------------------------------------------
    @GetMapping("/branch/")
    public ResponseEntity<List<Branch>> ListAllBranch() {
        List<Branch> lTemp = branchService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }

    @GetMapping("/branch/byCompanyId")
    public ResponseEntity<List<Branch>> ListAllBranchByCompany(@RequestParam("id") int id) {
        List<Branch> lTemp = branchService.findBycompanyid(id);
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }

    @GetMapping("/branch/byName")
    public ResponseEntity<Branch> GetBranchByName(@RequestParam("name") String name) {

        Branch Temp = branchService.findByName(name);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp, HttpStatus.OK);

    }

    @GetMapping("/branch/byId")
    public ResponseEntity<Branch> GetBranchById(@RequestParam("id") int id) {

        Optional<Branch> Temp = branchService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation branch methods ---------------------------------------------------------
    @PostMapping(value = "/branch")
    public Branch CreateBranch(@RequestParam("ParentId") int ParentId, @RequestBody Branch branch, UriComponentsBuilder ucBuilder) {
        LOG.info("creating new branch for the company -> " + ParentId);
        //LOG.info("creating new branch for the company -> " + branch.getCompanyid());
        
        return companyService.findById(ParentId)
                .map(c -> {
                    branch.setCompanyid(ParentId);
                    branch.setCompany(c);
                    return branchService.save(branch);
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + ParentId));

    }

    //---------------------------- Update branch methoda ---------------------------------------------------------  
    @PutMapping("/branch")
    public Branch UpdateBranch(@RequestBody Branch branch) {
        
        LOG.info("upadting branch " + branch.getBranchid() +" for the company -> " + branch.getCompanyid());
        
        if(!branchService.existsById(branch.getBranchid())) {
            throw new ResourceNotFoundException("Branch not found with id " + branch.getBranchid());
        }
        //LOG.info("MyBranch -> " + ReflectionToStringBuilder.toString(branch));
        
        companyService.findById(branch.getCompanyid())
                .map(c -> {
                    branch.setCompanyid(c.getCompanyid());
                    branch.setCompany(c);
                    return branchService.save(branch);
                }).orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + branch.getCompanyid()));
        
        return branchService.findById(branch.getBranchid())
                .map(b -> {
                    return branchService.save(branch);
                }).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + branch.getBranchid()));

    }

    //---------------------------- Delete branch methoda ---------------------------------------------------------  
    @DeleteMapping("/branch")
    public ResponseEntity<?> DeleteBranch(@RequestParam("branchId") int id) {
        LOG.info("Deleting branch " + id);
        
        if(!branchService.existsById(id)) {
            throw new ResourceNotFoundException("Branch not found with id " + id);
        }
        
        return branchService.findById(id)
                .map(b -> {
                    branchService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" employee methods ">
    //---------------------------- Retrieve employee methods---------------------------------------------------------
    @GetMapping("/employee/")
    public ResponseEntity<List<Employee>> ListAllEmployee() {
        List<Employee> lTemp = employeeService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }
    
    @GetMapping("/employee/byBranchId")
    public ResponseEntity<List<Employee>> ListAllEmployeeByBranch(@RequestParam("id") int id) {
        List<Employee> lTemp = employeeService.findBybranchid(id);
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }

    @GetMapping("/employee/byName")
    public ResponseEntity<Employee> GetEmployeeByName(@RequestParam("name") String name) {

        Employee Temp = employeeService.findByName(name);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp, HttpStatus.OK);

    }

    @GetMapping("/employee/byId")
    public ResponseEntity<Employee> GetEmployeeById(@RequestParam("id") int id) {

        Optional<Employee> Temp = employeeService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation employee methods ---------------------------------------------------------
    @PostMapping("/employee")
    public Employee CreateEmployee(
            @RequestBody Employee employee,
            UriComponentsBuilder ucBuilder) {
        
        LOG.info("creating new employee for the branch -> " + employee.getBranchid());
        
        return branchService.findById(employee.getBranchid())
                .map(b -> {
                    employee.setBranchid(b.getBranchid());
                    employee.setBranch(b);
                    return employeeService.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + employee.getBranchid()));
        
    }

    //---------------------------- Update employee methoda ---------------------------------------------------------  
    @PutMapping("/employee")
    public Employee UpdateEmployee(@RequestBody Employee employee) {
        
        LOG.info("updating employee for the branch -> " + employee.getBranchid());
        
        if(!employeeService.existsById(employee.getEmployeeid())) {
            throw new ResourceNotFoundException("Employee not found with id " + employee.getEmployeeid());
        }
        
        branchService.findById(employee.getBranchid())
                .map(b -> {
                    employee.setBranchid(b.getBranchid());
                    employee.setBranch(b);
                    return employeeService.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + employee.getBranchid()));
        
        return employeeService.findById(employee.getEmployeeid())
                .map(b -> {
                    return employeeService.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employee.getEmployeeid()));

    }

    //---------------------------- Delete employee methoda ---------------------------------------------------------  
    @DeleteMapping("/employee")
    public ResponseEntity<?> DeleteEmployee(@RequestParam("employeeId") int id) {

        if(!employeeService.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
        
        return employeeService.findById(id)
                .map(b -> {
                    employeeService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" vet methods ">
    //---------------------------- Retrieve vet methods---------------------------------------------------------
    @GetMapping("/vet/")
    public ResponseEntity<List<Vet>> ListAllVet() {
        List<Vet> lTemp = vetService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }
    
    @GetMapping("/vet/byEmployeeId")
    public ResponseEntity<Vet> GetVetByEmployee(@RequestParam("id") int id) {
        List<Vet> lTemp = vetService.findByemployeeid(id);
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp.get(0), HttpStatus.OK);
    }

    @GetMapping("/vet/byName")
    public ResponseEntity<Vet> GetVetByName(@RequestParam("name") String name) {

        Vet Temp = vetService.findByLicenseid(name);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp, HttpStatus.OK);

    }

    @GetMapping("/vet/byId")
    public ResponseEntity<Vet> GetVetById(@RequestParam("id") int id) {

        Optional<Vet> Temp = vetService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation vet methods ---------------------------------------------------------
    @PostMapping("/vet")
    public Vet CreateVet(
            @RequestBody Vet vet,
            UriComponentsBuilder ucBuilder) {
        
        LOG.info("creating new vet for the employee -> " + vet.getEmployeeid());
        
        return employeeService.findById(vet.getEmployeeid())
                .map(b -> {
                    vet.setEmployeeid(b.getEmployeeid());
                    vet.setEmployee(b);
                    return vetService.save(vet);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + vet.getEmployeeid()));
        
        

    }

    //---------------------------- Update vet methoda ---------------------------------------------------------  
    @PutMapping("/vet")
    public Vet UpdateVet(@RequestBody Vet vet) {

        LOG.info("updating vet for the employee -> " + vet.getEmployeeid());
        
        if(!vetService.existsById(vet.getVetid())) {
            throw new ResourceNotFoundException("Vet not found with id " + vet.getVetid());
        }
        
        employeeService.findById(vet.getEmployeeid())
                .map(b -> {
                    vet.setEmployeeid(b.getEmployeeid());
                    vet.setEmployee(b);
                    return vetService.save(vet);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + vet.getEmployeeid()));
        
        return vetService.findById(vet.getVetid())
                .map(b -> {
                    return vetService.save(vet);
                }).orElseThrow(() -> new ResourceNotFoundException("Vet not found with id " + vet.getVetid()));
    }

    //---------------------------- Delete vet methoda ---------------------------------------------------------  
    @DeleteMapping("/vet")
    public ResponseEntity<?> DeleteVet(@RequestParam("vetId") int id) {

        if(!vetService.existsById(id)) {
            throw new ResourceNotFoundException("Vet not found with id " + id);
        }
        
        return vetService.findById(id)
                .map(b -> {
                    vetService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Vet not found with id " + id));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Owner methods ">
    //---------------------------- Retrieve Owner methods---------------------------------------------------------
    @GetMapping("/owner/")
    public ResponseEntity<List<Owner>> ListAllOwner() {
        List<Owner> lTemp = ownerService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }

    @GetMapping("/owner/byName")
    public ResponseEntity<Owner> GetOwnerByName(@RequestParam("name") String name) {

        Owner Temp = ownerService.findByName(name);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp, HttpStatus.OK);

    }

    @GetMapping("/owner/byId")
    public ResponseEntity<Owner> GetOwnerById(@RequestParam("id") int id) {

        Optional<Owner> Temp = ownerService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation Owner methods ---------------------------------------------------------
    @PostMapping("/owner")
    public Owner CreateOwner(@RequestBody Owner owner, UriComponentsBuilder ucBuilder)
    {
        return ownerService.save(owner);
    }

    //---------------------------- Update Owner methoda ---------------------------------------------------------  
    @PutMapping("/owner")
    public Owner UpdateOwner(@RequestBody Owner owner) {
        
         return ownerService.findById(owner.getOwnerid()).map(
                c -> {
                    return ownerService.save(owner);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("test"));

    }

    //---------------------------- Delete Owner methoda ---------------------------------------------------------  
    @DeleteMapping("/owner")
    public ResponseEntity<?> DeleteOwner(@RequestParam("ownerId") int id) {
        
        return ownerService.findById(id).map(
                o -> {
                    ownerService.deleteById(id);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("test"));
        
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Pet methods ">
    //---------------------------- Retrieve Pet methods---------------------------------------------------------
    @GetMapping("/pet/")
    public ResponseEntity<List<Pet>> ListAllPet() {
        List<Pet> lTemp = petService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }
    
    @GetMapping("/pet/byOwnerId")
    public ResponseEntity<List<Pet>> GetPetsByOwner(@RequestParam("id") int id) {
        List<Pet> lTemp = petService.findByownerid(id);
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }

    @GetMapping("/pet/byName")
    public ResponseEntity<Pet> GetPetByName(@RequestParam("name") String name) {

        Pet Temp = petService.findByName(name);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp, HttpStatus.OK);

    }

    @GetMapping("/pet/byId")
    public ResponseEntity<Pet> GetPetById(@RequestParam("id") int id) {

        Optional<Pet> Temp = petService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation Pet methods ---------------------------------------------------------
    @PostMapping("/pet")
    public Pet CreatePet(
            @RequestBody Pet pet,
            UriComponentsBuilder ucBuilder) {
        
        LOG.info("creating new pet for the owner -> " + pet.getOwnerid());
        
        return ownerService.findById(pet.getOwnerid())
                .map(b -> {
                    pet.setOwnerid(b.getOwnerid());
                    pet.setOwner(b);
                    return petService.save(pet);
                }).orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + pet.getOwnerid()));
    }

    //---------------------------- Update Pet methoda ---------------------------------------------------------  
    @PutMapping("/pet")
    public Pet UpdatePet(@RequestBody Pet pet) {

        LOG.info("updating pet for the owner -> " + pet.getOwnerid());
        
        if(!petService.existsById(pet.getPetid())) {
            throw new ResourceNotFoundException("Pet not found with id " + pet.getPetid());
        }
        
        ownerService.findById(pet.getOwnerid())
                .map(b -> {
                    pet.setOwnerid(b.getOwnerid());
                    pet.setOwner(b);
                    return petService.save(pet);
                }).orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + pet.getOwnerid()));
        
        return petService.findById(pet.getPetid())
                .map(b -> {
                    return petService.save(pet);
                }).orElseThrow(() -> new ResourceNotFoundException("Pet not found with id " + pet.getPetid()));

    }

    //---------------------------- Delete Pet methoda ---------------------------------------------------------  
    @DeleteMapping("/pet")
    public ResponseEntity<?> DeletePet(@RequestParam("petId") int id) {
        
        if(!petService.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found with id " + id);
        }
        
        return petService.findById(id)
                .map(b -> {
                    petService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Pet not found with id " + id));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Checkup methods ">
    //---------------------------- Retrieve Checkup methods---------------------------------------------------------
    @GetMapping("/checkup/")
    public ResponseEntity<List<Checkup>> ListAllCheckup() {
        List<Checkup> lTemp = checkupService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }
    
    @GetMapping("/checkup/byVetId")
    public ResponseEntity<List<Checkup>> GetCheckupsByVet(@RequestParam("id") int id) {
        List<Checkup> lTemp = checkupService.findByvetid(id);
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }


    @GetMapping("/checkup/byId")
    public ResponseEntity<Checkup> GetCheckupById(@RequestParam("id") int id) {

        Optional<Checkup> Temp = checkupService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation Checkup methods ---------------------------------------------------------
    @PostMapping("/checkup")
    public Checkup CreateCheckup(
            @RequestBody Checkup checkup,
            UriComponentsBuilder ucBuilder) {
        
        LOG.info("creating new Checkup for the vet -> " + checkup.getVetid());
        
        return vetService.findById(checkup.getVetid())
                .map(b -> {
                    checkup.setVetid(b.getVetid());
                    checkup.setVet(b);
                    return checkupService.save(checkup);
                }).orElseThrow(() -> new ResourceNotFoundException("Vet not found with id " + checkup.getVetid()));
    }

    //---------------------------- Update Checkup methoda ---------------------------------------------------------  
    @PutMapping("/checkup")
    public Checkup UpdateCheckup(@RequestBody Checkup checkup) {
        
        LOG.info("updating new Checkup for the vet -> " + checkup.getVetid());
        
        if(!checkupService.existsById(checkup.getCheckupid())) {
            throw new ResourceNotFoundException("Checkup not found with id " + checkup.getCheckupid());
        }
        
        vetService.findById(checkup.getVetid())
                .map(b -> {
                    checkup.setVetid(b.getVetid());
                    checkup.setVet(b);
                    return checkupService.save(checkup);
                }).orElseThrow(() -> new ResourceNotFoundException("Vet not found with id " + checkup.getVetid()));
        
        return checkupService.findById(checkup.getCheckupid())
                .map(b -> {
                    return checkupService.save(checkup);
                }).orElseThrow(() -> new ResourceNotFoundException("Checkup not found with id " + checkup.getCheckupid()));
        

    }

    //---------------------------- Delete Checkup methoda ---------------------------------------------------------  
    @DeleteMapping("/checkup")
    public ResponseEntity<?> DeleteCheckup(@RequestParam("checkupId") int id) {
        
        if(!checkupService.existsById(id)) {
            throw new ResourceNotFoundException("checkup not found with id " + id);
        }
        
        return checkupService.findById(id)
                .map(b -> {
                    checkupService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("checkup not found with id " + id));
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Checkupreport methods ">
    //---------------------------- Retrieve Checkupreport methods---------------------------------------------------------
    @GetMapping("/checkupreport/")
    public ResponseEntity<List<Checkupreport>> ListAllCheckupreport() {
        List<Checkupreport> lTemp = checkupreportService.findAll();
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }
    
    @GetMapping("/checkupreport/byCheckupid")
    public ResponseEntity<List<Checkupreport>> GetCheckupreportsByVet(@RequestParam("id") int id) {
        List<Checkupreport> lTemp = checkupreportService.findBycheckupid(id);
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }
    
    @GetMapping("/checkupreport/byPetId")
    public ResponseEntity<List<Checkupreport>> GetCheckupreportsByPet(@RequestParam("id") int id) {
        List<Checkupreport> lTemp = checkupreportService.findBypetid(id);
        if (lTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lTemp, HttpStatus.OK);
    }

    @GetMapping("/checkupreport/byId")
    public ResponseEntity<Checkupreport> GetCheckupreportById(@RequestParam("id") int id) {

        Optional<Checkupreport> Temp = checkupreportService.findById(id);
        if (Temp == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Temp.get(), HttpStatus.OK);
    }

    //---------------------------- Creation Checkupreport methods ---------------------------------------------------------
    @PostMapping("/checkupreport")
    public Checkupreport CreateCheckupreport(
            @RequestBody Checkupreport checkupreport,
            UriComponentsBuilder ucBuilder) {
        
        LOG.info("creating new checkupreport for the pet -> " + checkupreport.getPetid());
        LOG.info("creating new checkupreport for the checkup -> " + checkupreport.getCheckupid());
        
        petService.findById(checkupreport.getPetid())
                .map(b -> {
                    checkupreport.setPetid(b.getPetid());
                    checkupreport.setPet(b);
                    return checkupreport;
                }).orElseThrow(() -> new ResourceNotFoundException("Pet not found with id " + checkupreport.getPetid()));
        
        return checkupService.findById(checkupreport.getCheckupid())
                .map(b -> {
                    checkupreport.setCheckupid(b.getCheckupid());
                    checkupreport.setCheckup(b);
                    return checkupreportService.save(checkupreport);
                }).orElseThrow(() -> new ResourceNotFoundException("Checkup not found with id " + checkupreport.getCheckupid()));
        
        
        
        
    }

    //---------------------------- Update Checkupreport methoda ---------------------------------------------------------  
    @PutMapping("/checkupreport")
    public Checkupreport UpdateCheckupreport(@RequestBody Checkupreport checkupreport) {
        
        LOG.info("creating new checkupreport for the pet -> " + checkupreport.getPetid());
        LOG.info("creating new checkupreport for the checkup -> " + checkupreport.getCheckupid());
        
        
        if(!checkupreportService.existsById(checkupreport.getCheckupreportid())) {
            throw new ResourceNotFoundException("Checkup report not found with id " + checkupreport.getCheckupreportid());
        }
        
        petService.findById(checkupreport.getPetid())
                .map(b -> {
                    checkupreport.setPetid(b.getPetid());
                    checkupreport.setPet(b);
                    return checkupreport;
                }).orElseThrow(() -> new ResourceNotFoundException("Pet not found with id " + checkupreport.getPetid()));
        
        checkupService.findById(checkupreport.getCheckupid())
                .map(b -> {
                    checkupreport.setCheckupid(b.getCheckupid());
                    checkupreport.setCheckup(b);
                    return checkupreportService.save(checkupreport);
                }).orElseThrow(() -> new ResourceNotFoundException("Checkup not found with id " + checkupreport.getCheckupid()));
        
        return checkupreportService.findById(checkupreport.getCheckupreportid())
                .map(b -> {
                    return checkupreportService.save(checkupreport);
                }).orElseThrow(() -> new ResourceNotFoundException("Checkup report not found with id " + checkupreport.getCheckupreportid()));
        

    }

    //---------------------------- Delete Checkupreport methoda ---------------------------------------------------------  
    @DeleteMapping("/checkupreport")
    public ResponseEntity<?> DeleteCheckupreport(@RequestParam("checkupreportId") int id) {

        if(!checkupreportService.existsById(id)) {
            throw new ResourceNotFoundException("checkup report not found with id " + id);
        }
        
        return checkupreportService.findById(id)
                .map(b -> {
                    checkupreportService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("checkup report not found with id " + id));
        
    }

    // </editor-fold>
}

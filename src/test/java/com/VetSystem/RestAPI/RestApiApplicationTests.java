package com.VetSystem.RestAPI;

import com.VetSystem.RestAPI.controller.ApiEndpoint;
import com.VetSystem.RestAPI.entity.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApiApplicationTests {

    @Autowired
    ApiEndpoint client;
    
        @Test
	public void contextLoads() {
	}
        
        @Test
        public void QueryCompanies(){
             List<Company> ltemp =  client.ListAllCompany().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void QueryBranches(){
             List<Branch> ltemp =  client.ListAllBranch().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void QueryEmployees(){
             List<Employee> ltemp =  client.ListAllEmployee().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void QueryVets(){
             List<Vet> ltemp =  client.ListAllVet().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void QueryOwners(){
             List<Owner> ltemp =  client.ListAllOwner().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void QueryPets(){
             List<Pet> ltemp =  client.ListAllPet().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void QueryChechups(){
             List<Checkup> ltemp =  client.ListAllCheckup().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void QueryCheckupreports(){
             List<Checkupreport> ltemp =  client.ListAllCheckupreport().getBody();
             assertTrue(ltemp.size() > 0); 
        }
        
        @Test
        public void NewCompany(){
            System.out.println("Start NewCompany test");
            
            List<Company> ltemp =  client.ListAllCompany().getBody();
            
            Company tmp = new Company();
            tmp.setName("Company test");
            tmp.setAddress("address test");
            tmp.setIsactive("A");
            
            UriComponentsBuilder ucBuilder = null;
            
            Company objectIns = client.CreateCompany(tmp, ucBuilder);
            
            Company objectQue = client.GetCompanyById(objectIns.getCompanyid()).getBody();
            
            assertTrue(tmp.getName().equals(objectQue.getName())); 
            
            objectIns.setName(objectIns.getName() + "_2");
            
            Company objectUpd = client.UpdateCompany(objectIns);
            
            objectQue = client.GetCompanyById(objectIns.getCompanyid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeleteCompany(objectQue.getCompanyid());
            
            List<Company> ltemp2 =  client.ListAllCompany().getBody();
            
            assertTrue(ltemp.size() == ltemp2.size()); 
            System.out.println("End NewCompany test");
        }
        
        
        @Test
        public void NewBranch(){
            System.out.println("Start NewBranch test");
            List<Company> ltemp =  client.ListAllCompany().getBody();
            List<Branch> ltemp1 =  client.ListAllBranch().getBody();
            
            Random r = new Random();
            
            Integer index = r.nextInt(ltemp.size());
                    
            
            Branch tmp = new Branch();
            tmp.setName("Branch test");
            tmp.setAddress("address test");
            tmp.setIsactive("A");
            
            Company parent = ltemp.get(index);
            
            UriComponentsBuilder ucBuilder = null;
            
            //System.out.println("Branch id -> "+ tmp.getBranchid());
            
            Branch objectIns = client.CreateBranch(parent.getCompanyid(), tmp, ucBuilder);
            
            System.out.println("Branch id -> "+ objectIns.getBranchid());
            
            Branch objectQue = client.GetBranchById(objectIns.getBranchid()).getBody();
            
            assertTrue(tmp.getName().equals(objectQue.getName())); 
            
            objectIns.setName(objectIns.getName() + "_2");
            
            Branch objectUpd = client.UpdateBranch(objectIns);
            
            objectQue = client.GetBranchById(objectIns.getBranchid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeleteBranch(objectQue.getBranchid());
            
            List<Branch> ltemp2 =  client.ListAllBranch().getBody();
            
            assertTrue(ltemp1.size() == ltemp2.size()); 
            System.out.println("End NewBranch test");
            
        }
        
        @Test
        public void NewEmployee(){
            System.out.println("Start NewEmployee test");
            List<Branch> ltemp =  client.ListAllBranch().getBody();
            List<Employee> ltemp1 =  client.ListAllEmployee().getBody();
            
            Random r = new Random();
            
            Integer index = r.nextInt(ltemp.size());
            Branch parent = ltemp.get(index);
            
            Employee tmp = new Employee();
            tmp.setName("Branch test");
            tmp.setSurname("Branch test");
            tmp.setAddress("address test");
            tmp.setRole("T");
            tmp.setIsactive("A");
            tmp.setBranchid(parent.getBranchid());
            
            UriComponentsBuilder ucBuilder = null;
            
            //System.out.println("Branch id -> "+ tmp.getBranchid());
            
            Employee objectIns = client.CreateEmployee(tmp, ucBuilder);
            
            System.out.println("Employee id -> "+ objectIns.getEmployeeid());
            
            Employee objectQue = client.GetEmployeeById(objectIns.getEmployeeid()).getBody();
            
            assertTrue(tmp.getName().equals(objectQue.getName())); 
            
            objectIns.setName(objectIns.getName() + "_2");
            
            Employee objectUpd = client.UpdateEmployee(objectIns);
            
            objectQue = client.GetEmployeeById(objectIns.getEmployeeid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeleteEmployee(objectQue.getEmployeeid());
            
            List<Employee> ltemp2 =  client.ListAllEmployee().getBody();
            
            assertTrue(ltemp1.size() == ltemp2.size()); 
            
            System.out.println("End NewEmployee test");
            
        }
        
        @Test
        public void NewVet(){
            System.out.println("Start NewVet test");
            List<Employee> ltemp =  client.ListAllEmployee().getBody();
            List<Vet> ltemp1 =  client.ListAllVet().getBody();
            
            Random r = new Random();
            
            Integer index = r.nextInt(ltemp.size());
            Employee parent = ltemp.get(index);
            
            Vet tmp = new Vet();
            tmp.setLicenseid("Licence 1");
            tmp.setLicenseissuedby("KZN");
            tmp.setLicencetype("Test");
            tmp.setIsactive("A");
            tmp.setEmployeeid(parent.getEmployeeid());
            
            UriComponentsBuilder ucBuilder = null;
            
            //System.out.println("Branch id -> "+ tmp.getBranchid());
            
            Vet objectIns = client.CreateVet(tmp, ucBuilder);
            
            System.out.println("Vet id -> "+ objectIns.getVetid());
            
            Vet objectQue = client.GetVetById(objectIns.getVetid()).getBody();
            
            assertTrue(tmp.getLicenseid().equals(objectQue.getLicenseid())); 
            
            objectIns.setLicenseid(objectIns.getLicenseid()+ "_2");
            
            Vet objectUpd = client.UpdateVet(objectIns);
            
            objectQue = client.GetVetById(objectIns.getVetid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeleteVet(objectQue.getVetid());
            
            List<Vet> ltemp2 =  client.ListAllVet().getBody();
            
            assertTrue(ltemp1.size() == ltemp2.size()); 
            System.out.println("End NewVet test");
        }
        
        @Test
        public void NewOwner(){
            System.out.println("Start NewOwner test");
            List<Owner> ltemp =  client.ListAllOwner().getBody();
            
            Owner tmp = new Owner();
            tmp.setName("Owner test");
            tmp.setSurname("Owner surname test");
            tmp.setAddress1("address test");
            tmp.setIsactive("A");
            
            UriComponentsBuilder ucBuilder = null;
            
            Owner objectIns = client.CreateOwner(tmp, ucBuilder);
            
            Owner objectQue = client.GetOwnerById(objectIns.getOwnerid()).getBody();
            
            assertTrue(tmp.getName().equals(objectQue.getName())); 
            
            objectIns.setName(objectIns.getName() + "_2");
            
            Owner objectUpd = client.UpdateOwner(objectIns);
            
            objectQue = client.GetOwnerById(objectIns.getOwnerid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeleteOwner(objectQue.getOwnerid());
            
            List<Owner> ltemp2 =  client.ListAllOwner().getBody();
            System.out.println(ltemp.size());
            System.out.println(ltemp2.size());
            assertTrue(ltemp.size() == ltemp2.size()); 
            System.out.println("End NewOwner test");
        }
        
        @Test
        public void NewPet(){
            System.out.println("Start NewPet test");
            List<Owner> ltemp =  client.ListAllOwner().getBody();
            List<Pet> ltemp1 =  client.ListAllPet().getBody();
            
            Random r = new Random();
            
            Integer index = r.nextInt(ltemp.size());
            Owner parent = ltemp.get(index);
            
            Pet tmp = new Pet();
            tmp.setName("Pet name 1");
            tmp.setBirthplace("KZN");
            tmp.setAnimaltype("Type 1");
            tmp.setBreedname("Breed 1");
            tmp.setBirthdate(new Date());
            tmp.setIsactive("A");
            tmp.setOwnerid(parent.getOwnerid());
            
            UriComponentsBuilder ucBuilder = null;
            
            
            Pet objectIns = client.CreatePet(tmp, ucBuilder);
            
            System.out.println("Pet id -> "+ objectIns.getPetid());
            
            Pet objectQue = client.GetPetById(objectIns.getPetid()).getBody();
            
            assertTrue(tmp.getName().equals(objectQue.getName())); 
            
            objectIns.setName(objectIns.getName()+ "_2");
            
            Pet objectUpd = client.UpdatePet(objectIns);
            
            objectQue = client.GetPetById(objectIns.getPetid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeletePet(objectQue.getPetid());
            
            List<Pet> ltemp2 =  client.ListAllPet().getBody();
            
            assertTrue(ltemp1.size() == ltemp2.size()); 
            System.out.println("End NewPet test");
        }

        @Test
        public void NewCheckup(){
            List<Vet> ltemp =  client.ListAllVet().getBody();
            List<Checkup> ltemp1 =  client.ListAllCheckup().getBody();
            
            Random r = new Random();
            
            Integer index = r.nextInt(ltemp.size());
            Vet parent = ltemp.get(index);
            
            Checkup tmp = new Checkup();
            tmp.setCheckupdate(new Date());
            tmp.setIsactive("A");
            tmp.setVetid(parent.getVetid());
            
            UriComponentsBuilder ucBuilder = null;
            
            
            Checkup objectIns = client.CreateCheckup(tmp, ucBuilder);
            
            System.out.println("Checkup id -> "+ objectIns.getCheckupid());
            
            
            Checkup objectQue = client.GetCheckupById(objectIns.getCheckupid()).getBody();
            
            
            SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
            System.out.println(dateOnly.format(tmp.getCheckupdate()));
            System.out.println(dateOnly.format(objectQue.getCheckupdate()));
            
            assertTrue(dateOnly.format(tmp.getCheckupdate()).equals(dateOnly.format(objectQue.getCheckupdate())));
            
            objectIns.setIsactive("I");
            
            Checkup objectUpd = client.UpdateCheckup(objectIns);
            
            objectQue = client.GetCheckupById(objectIns.getCheckupid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeleteCheckup(objectQue.getCheckupid());
            
            List<Checkup> ltemp2 =  client.ListAllCheckup().getBody();
            
            assertTrue(ltemp1.size() == ltemp2.size()); 
            
        }
        
        @Test
        public void NewCheckupreport(){
            List<Checkup> ltemp =  client.ListAllCheckup().getBody();
            List<Pet> l1temp =  client.ListAllPet().getBody();
            List<Checkupreport> ltemp1 =  client.ListAllCheckupreport().getBody();
            
            Random r = new Random();
            
            Integer index = r.nextInt(ltemp.size());
            Checkup parent = ltemp.get(index);
            
            index = r.nextInt(l1temp.size());
            Pet parent2 = l1temp.get(index);
            
            
            Checkupreport tmp = new Checkupreport();
            tmp.setReportnotes(null);
            tmp.setIsactive("A");
            tmp.setCheckupid(parent.getCheckupid());
            tmp.setPetid(parent2.getPetid());
            
            UriComponentsBuilder ucBuilder = null;
            
            
            Checkupreport objectIns = client.CreateCheckupreport(tmp, ucBuilder);
            
            System.out.println("Checkupreport id -> "+ objectIns.getCheckupreportid());
            
            Checkupreport objectQue = client.GetCheckupreportById(objectIns.getCheckupreportid()).getBody();
            
            SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
            
            //assertTrue(dateOnly.format(new Date()).equals(dateOnly.format(objectQue.getCreationdate())));
            
            objectIns.setIsactive("I");
            
            Checkupreport objectUpd = client.UpdateCheckupreport(objectIns);
            
            objectQue = client.GetCheckupreportById(objectIns.getCheckupreportid()).getBody();
            
            assertTrue(objectQue.getUpdateby()!= null); 
            
            client.DeleteCheckupreport(objectQue.getCheckupreportid());
            
            List<Checkupreport> ltemp2 =  client.ListAllCheckupreport().getBody();
            
            assertTrue(ltemp1.size() == ltemp2.size()); 
            
        }
        
}

package com.VetSystem.RestAPI.entity;
// Generated 17-sep-2019 16:08:27 by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * Branch generated by hbm2java
 */
@Entity
@Table
public class Branch  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branchid", updatable = false)
    private int branchid;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "companyid")
    private Company company;
    

    @Column(insertable = false, updatable = false)
    private int companyid;
    
    @Column
    private String name;
    
    @Column
    private String address;
    
    @Column
    private String location;
    
    @Column
    private String phonenumber1;
    
    @Column
    private String phonenumber2;
    
    @Column
    private String isactive;
    
    @Column(name = "creationdate", insertable = false, updatable = false)
    private Date creationdate;
    
    @Column(name = "createdby", insertable = false, updatable = false)
    private String createdby;
    
    @Column(name = "lastupdate", insertable = false)
    private Date lastupdate;
    
    @Column(name = "updateby", insertable = false)
    private String updateby;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")//, cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();


    public Branch() {
    }

	
    public Branch(int branchid, int companyid, String name, String isactive, Date creationdate, String createdby) {
        this.branchid = branchid;
        this.companyid = companyid;
        this.name = name;
        this.isactive = isactive;
        this.creationdate = creationdate;
        this.createdby = createdby;
    }
    public Branch(int branchid, int companyid, Company company, String name, String address, String location, String phonenumber1, String phonenumber2, String isactive, Date creationdate, String createdby, Date lastupdate, String updateby, Set employees) {
       this.branchid = branchid;
       this.companyid = companyid;
       this.company = company;
       this.name = name;
       this.address = address;
       this.location = location;
       this.phonenumber1 = phonenumber1;
       this.phonenumber2 = phonenumber2;
       this.isactive = isactive;
       this.creationdate = creationdate;
       this.createdby = createdby;
       this.lastupdate = lastupdate;
       this.updateby = updateby;
       //this.employees = employees;
    }
   
    public int getBranchid() {
        return this.branchid;
    }
    
    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }
    
    
    
    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPhonenumber1() {
        return this.phonenumber1;
    }
    
    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }
    public String getPhonenumber2() {
        return this.phonenumber2;
    }
    
    public void setPhonenumber2(String phonenumber2) {
        this.phonenumber2 = phonenumber2;
    }
    public String getIsactive() {
        return this.isactive;
    }
    
    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
    public Date getCreationdate() {
        return this.creationdate;
    }
    
    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }
    public String getCreatedby() {
        return this.createdby;
    }
    
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }
    public Date getLastupdate() {
        return this.lastupdate;
    }
    
    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }
    public String getUpdateby() {
        return this.updateby;
    }
    
    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }
    
    public Set getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Set employees) {
        this.employees = employees;
    }
    
    @PreUpdate
    public void preUpdate(){
        this.lastupdate = new Date();
        this.updateby = "postgres";
    }
}



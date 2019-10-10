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
 * Vet generated by hbm2java
 */

@Entity
@Table
public class Vet  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vetid", updatable = false)
    private int vetid;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    private Employee employee;
    
    @Column(insertable = false, updatable = false)
    private int employeeid;
    
    @Column
    private String licenseid;
    
    @Column
    private String licenseissuedby;
    
    @Column
    private String licensetype;
    
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vet")//, cascade = CascadeType.ALL)
    private Set<Checkup> checkups = new HashSet<>();
    

    public Vet() {
    }

	
    public Vet(int vetid, int employeeid, String licenseid, String licenseissuedby, String licensetype, String isactive, Date creationdate, String createdby) {
        this.vetid = vetid;
        this.employeeid = employeeid;
        this.licenseid = licenseid;
        this.licenseissuedby = licenseissuedby;
        this.licensetype = licensetype;
        this.isactive = isactive;
        this.creationdate = creationdate;
        this.createdby = createdby;
    }
    public Vet(int vetid, int employeeid, Employee employee, String licenseid, String licenseissuedby, String licensetype, String isactive, Date creationdate, String createdby, Date lastupdate, String updateby, Set checkups) {
       this.vetid = vetid;
       this.employeeid = employeeid;
       this.employee = employee;
       this.licenseid = licenseid;
       this.licenseissuedby = licenseissuedby;
       this.licensetype = licensetype;
       this.isactive = isactive;
       this.creationdate = creationdate;
       this.createdby = createdby;
       this.lastupdate = lastupdate;
       this.updateby = updateby;
       this.checkups = checkups;
    }
   
    public int getVetid() {
        return this.vetid;
    }
    
    public void setVetid(int vetid) {
        this.vetid = vetid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
    
    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public String getLicenseid() {
        return this.licenseid;
    }
    
    public void setLicenseid(String licenseid) {
        this.licenseid = licenseid;
    }
    public String getLicenseissuedby() {
        return this.licenseissuedby;
    }
    
    public void setLicenseissuedby(String licenseissuedby) {
        this.licenseissuedby = licenseissuedby;
    }
    public String getLicensetype() {
        return this.licensetype;
    }
    
    public void setLicencetype(String licensetype) {
        this.licensetype = licensetype;
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
    public Set getCheckups() {
        return this.checkups;
    }
    
    public void setCheckups(Set checkups) {
        this.checkups = checkups;
    }

    @PreUpdate
    public void preUpdate(){
        this.lastupdate = new Date();
        this.updateby = "postgres";
    }

}



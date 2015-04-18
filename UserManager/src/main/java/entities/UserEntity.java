/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jason
 */
@XmlRootElement
public class UserEntity {
    private int id;
    private int age;
    private String firstname;
    private String lastname;
    
    public UserEntity() {}
    
    public UserEntity(int age, String firstname, String lastname) {
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public void setId (int value) { this.id = value; }
    @XmlAttribute
    public int getId () { return this.id; }
    
    public void setAge(int value) { this.age = value; }
    @XmlAttribute
    public int getAge() { return this.age; }
    
    public void setFirstname(String value) { this.firstname = value; }
    @XmlAttribute
    public String getFirstname() { return this.firstname; }
    
    public void setLastname (String value) { this.lastname = value; }
    @XmlAttribute
    public String getLastname () { return this.lastname; }
    
    @Override
    public String toString() {
        return "[" + 
                    "id=" + this.getId() +
                    "firstname=" + this.getFirstname() +
                    "lastname=" + this.getLastname() +
                    "age=" + this.getAge()
                + "]";
    }
}

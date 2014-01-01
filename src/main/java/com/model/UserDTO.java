package com.model;

import org.bson.types.ObjectId;

import javax.xml.bind.annotation.*;

/**
 * @author Ben Yasin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user",
   propOrder={
    "id",
    "name",
    "age",
    "position",
    "interest"})
@XmlRootElement
public class UserDTO {

    @XmlElement(required = true)
    private String id;
    @XmlElement(required = true)
    private String name;
    @XmlElement
    private int age;
    @XmlElement
    private String position;
    @XmlElement
    private String interest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}


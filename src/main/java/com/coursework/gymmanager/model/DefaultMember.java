package com.coursework.gymmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
MongoDB Collection: Members
*/
@Document(collection = "Members")
public class DefaultMember {

    @Id
    private String membershipNumber;
    private String name;
    private Date startMembershipDate;
    private String gender;
    private int contactNum;

    public DefaultMember() {
    }

    public DefaultMember(String membershipNumber, String name, Date startMembershipDate, String gender, int contactNum) {
        this.membershipNumber = membershipNumber;
        this.name = name;
        this.startMembershipDate = startMembershipDate;
        this.gender = gender;
        this.contactNum = contactNum;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartMembershipDate() {
        return startMembershipDate;
    }

    public void setStartMembershipDate(Date startMembershipDate) {
        this.startMembershipDate = startMembershipDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getContactNum() {
        return contactNum;
    }

    public void setContactNum(int contactNum) {
        this.contactNum = contactNum;
    }

    @Override
    public String toString() {
        return "DefaultMember{ " +
                "membershipNumber= " + membershipNumber  +
                ", name= " + name  +
                ", startMembershipDate= " + startMembershipDate +
                ", gender= " + gender  +
                ", contactNum= " + contactNum +
                '}';
    }
}
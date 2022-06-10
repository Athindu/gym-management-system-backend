package com.coursework.gymmanager.model;

public class Over60Member extends DefaultMember {

    private int age;
    private String idNum;
    private String occupation;

    public Over60Member() {
    }

    public Over60Member(String membershipNumber, String name, Date startMembershipDate, String gender, int contactNum, int age, String idNum, String occupation) {
        super(membershipNumber, name, startMembershipDate, gender, contactNum);
        this.age = age;
        this.idNum = idNum;
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Over60Member{" +
                " membershipNumber= " + getMembershipNumber()  +
                ", name= " + getName()  +
                ", startMembershipDate=" + getStartMembershipDate()  +
                ", gender= " + getGender()  +
                ", contactNo= " + getContactNum()  +
                ", age=" + age +
                ", idNum=" + idNum +
                ", occupation=" + occupation  +
                "}";
    }
}

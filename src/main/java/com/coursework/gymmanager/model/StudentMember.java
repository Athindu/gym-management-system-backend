package com.coursework.gymmanager.model;

public class StudentMember extends DefaultMember {

    private String schoolName;
    private String grade;
    private String guardianName;

    public StudentMember() {
    }

    public StudentMember(String membershipNumber, String name, Date startMembershipDate, String gender, int contactNum, String schoolName, String grade, String guardianName) {
        super(membershipNumber, name, startMembershipDate, gender, contactNum);
        this.schoolName = schoolName;
        this.grade = grade;
        this.guardianName = guardianName;
    }

    public void setNameAAA(String a){}

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }


    @Override
    public String toString() {
        return "StudentMember{ " +
                "membershipNumber= " + getMembershipNumber()  +
                ", name= " + getName()  +
                ", startMembershipDate=" + getStartMembershipDate()  +
                ", gender= " + getGender()  +
                ", contactNo= " + getContactNum()  +
                ", schoolName=" + schoolName  +
                ", grade=" + grade  +
                ", guardianName=" + guardianName  +
                '}';
    }
}

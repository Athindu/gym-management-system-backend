package com.coursework.gymmanager.model;

public class Count {

    private int fullCount;
    private int studentCount;
    private int seniorCount;
    private int defaultCount;

    public Count(int fullCount, int studentCount, int seniorCount, int defaultCount) {
        this.fullCount = fullCount;
        this.studentCount = studentCount;
        this.seniorCount = seniorCount;
        this.defaultCount = defaultCount;
    }

    public int getFullCount() {
        return fullCount;
    }

    public void setFullCount(int fullCount) {
        this.fullCount = fullCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getSeniorCount() {
        return seniorCount;
    }

    public void setSeniorCount(int seniorCount) {
        this.seniorCount = seniorCount;
    }

    public int getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount(int defaultCount) {
        this.defaultCount = defaultCount;
    }

    @Override
    public String toString() {
        return "Count{" +
                "fullCount=" + fullCount +
                ", studentCount=" + studentCount +
                ", seniorCount=" + seniorCount +
                ", defaultCount=" + defaultCount +
                '}';
    }
}

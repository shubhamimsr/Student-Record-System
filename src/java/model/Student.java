package model;

public class Student {

    private int rno;
    private String name;
    private String qualification;
    private float percentage;

    public Student() {
    }

    public Student(int rno, String name, String qualification, float percentage) {
        this.rno = rno;
        this.name = name;
        this.qualification = qualification;
        this.percentage = percentage;
    }

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}

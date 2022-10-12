/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.courses;

import java.sql.Date;

/**
 *
 * @author tlmin
 */
public class CourseDTO {

    private String courseId;
    private String courseType;
    private String courseName;
    private String courseImg;
    private String courseDesc;
    private double courseFee;
    private int courseQuantity;
    private Date startDate;
    private Date endDate;
    private String courseStatus;

    public CourseDTO() {
        this.courseId = "";
        this.courseType = "";
        this.courseName = "";
        this.courseImg = "";
        this.courseDesc = "";
        this.courseFee = 0;
        this.courseQuantity = 0;
        this.startDate = new Date(System.currentTimeMillis());
        this.endDate = new Date(System.currentTimeMillis());
        this.courseStatus = "";
    }

    public CourseDTO(String courseId, String courseType, String courseName, String courseImg, String courseDesc, double courseFee, int courseQuantity, Date startDate, Date endDate, String courseStatus) {
        this.courseId = courseId;
        this.courseType = courseType;
        this.courseName = courseName;
        this.courseImg = courseImg;
        this.courseDesc = courseDesc;
        this.courseFee = courseFee;
        this.courseQuantity = courseQuantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseStatus = courseStatus;
    }

    public CourseDTO(String courseId, String courseName, int courseQuantity, double courseFee) {
        this.courseId = courseId;
        this.courseQuantity = courseQuantity;
        this.courseFee = courseFee;
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public int getCourseQuantity() {
        return courseQuantity;
    }

    public void setCourseQuantity(int courseQuantity) {
        this.courseQuantity = courseQuantity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

}

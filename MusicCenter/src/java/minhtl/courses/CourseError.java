/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.courses;

/**
 *
 * @author tlmin
 */
public class CourseError {

    private String courseId;
    private String error;

    public CourseError() {
        this.courseId = "<br>";
        this.error = "";
    }

    public CourseError(String courseId, String error) {
        this.courseId = courseId;
        this.error = error;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}

<%@page import="java.sql.Date"%>
<%@page import="minhtl.courses.CourseError"%>
<%@page import="minhtl.users.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="minhtl.courses.CourseDAO"%>
<%@page import="minhtl.courses.CourseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Course Page</title>
        <link rel="stylesheet" href="css/addCourse.css" type="text/css">
        <link rel="stylesheet" href="css/header.css" type="text/css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <main>
            <%                
                CourseError courseError = (CourseError) request.getAttribute("COURSE_ERROR");
                if (courseError == null) {
                    courseError = new CourseError();
                }
                CourseDTO course = (CourseDTO) request.getAttribute("TMP_COURSE");
                if (course == null) {
                    course = new CourseDTO();
                }
            %>
            <h1>ADD COURSES</h1>
            <form class="add-form" action="MainController">
                Course ID:     <input type="text" name="courseId" required="" value="<%=course.getCourseId()%>"/><br>
                <div class="error"><%= courseError.getCourseId()%></div>
                Image Link:    <input type="text" name="courseImg" required="" value="<%=course.getCourseImg()%>"/><br><br>
                Course Type:   <select name="courseType" required="">
                    <option value="piano">Piano</option>
                    <option value="guitar">Guitar</option>
                    <option value="drum">Drum</option>
                    <option value="saxophone">Saxophone</option>
                    <option value="violin">Violin</option>
                </select><br><br>
                Course Name:   <input type="text" name="courseName" required="" value="<%=course.getCourseName()%>"/><br><br>
                Course Description: <input type="text" name="courseDesc" required="" value="<%=course.getCourseDesc()%>"/><br><br>
                Start Date: <input type="date" name="startDate" required="" value="<%=course.getStartDate()%>"/><br><br>
                End Date: <input type="date" name="endDate" required="" value="<%=course.getEndDate()%>"/><br><br>
                Tuition:  $<input type="number" step="0.01" name="courseFee" required="" value="<%=course.getCourseFee()%>"/><br><br>
                Quantity:  <input type="number" name="courseQuantity" required="" value="<%=course.getCourseQuantity()%>"/><br><br>
                Status: <select name="courseStatus" required="">
                    <option value="active">Active</option>
                    <option value="inactive">Inactive</option>
                </select>
                <div class="add-button">
                    <input type="submit" name="action" value="Add Course">
                </div>
            </form>
            <%
                String message = (String) request.getAttribute("MESSAGE");
                if (message == null) {
                    message = "";
                }
            %>
            <div class="message">
                <%= message%>
            </div>
        </main>
    </body>
</html>

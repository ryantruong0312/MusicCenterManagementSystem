<%@page import="java.util.ArrayList"%>
<%@page import="minhtl.courses.CourseDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="minhtl.users.UserDTO"%>
<%@page import="minhtl.courses.CourseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Detail</title>
        <link rel="stylesheet" href="css/courseDetail.css" type="text/css">
        <link rel="stylesheet" href="css/header.css" type="text/css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <main>
            <%
                CourseDTO course = (CourseDTO) request.getAttribute("COURSE_DETAIL");
            %>
            <div class="mainContainer">
            <div class="left">
                <img src="<%= course.getCourseImg()%>">
            </div>
            <div class="right">
                <h1><%= course.getCourseName()%></h1>
                <h3>Course ID: <%= course.getCourseId()%></h3>
                <h3>Category: <%= course.getCourseType()%></h3>
                <h3>Description: <%= course.getCourseDesc()%></h3>
                <h3>Start: <%= course.getStartDate()%></h3>
                <h3>Complete <%= course.getEndDate()%></h3>
                <h3>Price: $<%= course.getCourseFee()%></h3>
                <% String courseStatus = course.getCourseStatus();
                    if("active".equals(courseStatus) && (loginUser == null || !"AD".equals(loginUser.getUserRoleId()))){                         
            %>  <h3>Status: Active </h3>
                <form action="MainController">
                    <input type="number" name="orderQuantity" value="1" required=""/>
                    <input type="hidden" name="courseId" value="<%= course.getCourseId()%>"/>
                    <input type="hidden" name="courseName" value="<%= course.getCourseName()%>"/>
                    <input type="hidden" name="courseFee" value="<%= course.getCourseFee()%>"/>
                    <input type="submit" name="action" value="Add to Cart">
                </form>
                    <%  } else if("inactive".equals(courseStatus)){ %>
                <h3>Status: Inactive </h3> 
            <%
                }else{}
                String message = (String) request.getAttribute("MESSAGE");
                if (message == null) {
                    message = "";
                }
                %>
                <div class="message">
                <%= message%>
                </div>
            </div>
            </div>
        </main>
    </body>
</html>

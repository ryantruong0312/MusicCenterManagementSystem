<%@page import="minhtl.updateinfo.UpdateInfoDTO"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="minhtl.courses.CourseDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="minhtl.users.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="minhtl.courses.CourseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Course Page</title>
        <link rel="stylesheet" href="css/course.css" type="text/css">
        <link rel="stylesheet" href="css/header.css" type="text/css">
    </head>
    <body>

        <%@include file="header.jsp" %>

        <main>
            <%String courseTypeName = request.getParameter("courseType");%>
            <form action="MainController" class="searchTypeBox">
                <select name="courseType">
                    <option value="Piano" <% if ("Piano".equals(courseTypeName)) {%> selected="" <% } %>>Piano</option>
                    <option value="Guitar" <% if ("Guitar".equals(courseTypeName)) {%> selected="" <% } %>> Guitar </option>
                    <option value="Drum" <% if ("Drum".equals(courseTypeName)) { %> selected="" <% } %>>Drum</option>
                    <option value="Saxophone" <% if ("Saxophone".equals(courseTypeName)) { %> selected="" <% } %>>Saxophone</option>
                    <option value="Violin" <% if ("Violin".equals(courseTypeName)) { %> selected="" <% }%>>Violin</option>
                </select>
                <input type="submit" name="action" value="Search By Type">
            </form>
            <%
                List<CourseDTO> courseList = (List<CourseDTO>) request.getAttribute("COURSE_LIST");
                if (courseList != null && courseList.size() > 0) {
                    Collections.sort(courseList, new Comparator<CourseDTO>() {
                        public int compare(CourseDTO o1, CourseDTO o2) {
                            return o1.getStartDate().compareTo(o2.getStartDate());
                        }
                    });
                }

                if (loginUser != null && "AD".equals(loginUser.getUserRoleId())) {
                    UpdateInfoDTO updateInfo = (UpdateInfoDTO) request.getAttribute("UPDATE_INFO");
                    if (updateInfo != null) {
            %>
            <div class="update-info"> 
                Last Update Admin: <%=updateInfo.getAdminName()%> (<%=updateInfo.getAdminId()%>)<br>
                Last Update Date: <%=updateInfo.getLastUpDate()%>
            </div>
            <%
                }
            %>

            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th></th>
                        <th>Course ID</th>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Tuition Fee</th>
                        <th>Quantity</th>
                        <th>Status</th>
                        <th colspan="2">Edit Course</th>
                    </tr>
                </thead>
                <tbody>            
                    <%int count = 1;
                        for (CourseDTO course : courseList) {

                    %>  

                    <tr>
                <form action="MainController">
                    <td><%= count++%></td>
                    <td><img src="<%= course.getCourseImg()%>"></td>
                    <td><%= course.getCourseId()%></td>
                    <!-- Update function-->
                    <td><select name="courseType" >
                            <option value="Piano" <% if ("Piano".equals(course.getCourseType())) { %> selected="" <% } %> >Piano</option>
                            <option value="Guitar" <% if ("Guitar".equals(course.getCourseType())) { %> selected="" <% } %>>Guitar</option>
                            <option value="Drum" <% if ("Drum".equals(course.getCourseType())) { %> selected="" <% } %>>Drum</option>
                            <option value="Saxophone" <% if ("Saxophone".equals(course.getCourseType())) { %> selected="" <% } %>>Saxophone</option>
                            <option value="Violin" <% if ("Violin".equals(course.getCourseType())) { %> selected="" <% }%>>Violin</option>
                        </select>
                    </td>
                    <td><input type="text" name="courseName" value="<%= course.getCourseName()%>" required="" /></td>
                    <td><input type="text" name="courseDesc" value="<%= course.getCourseDesc()%>" required="" /></td>
                    <td><input type="date" name="startDate" value="<%= course.getStartDate()%>" required="" /></td>
                    <td><input type="date" name="endDate" value="<%= course.getEndDate()%>" required="" /></td>
                    <td><input type="number" step="0.01" name="courseFee" value="<%= course.getCourseFee()%>" required="" /></td>
                    <td><input type="number" name="courseQuantity" value="<%= course.getCourseQuantity()%>" required="" /></td>
                    <td><select name="courseStatus" >
                            <option value="active" <% if ("active".equals(course.getCourseStatus())) { %> selected="" <% } %>>Active</option>
                            <option value="inactive" <% if ("inactive".equals(course.getCourseStatus())) { %> selected="" <% }%>>Inactive</option>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="courseId" value="<%= course.getCourseId()%>"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                        <input type="hidden" name="adminId" value="<%=loginUser.getUserId()%>"/>
                        <input type="submit" name="action" value="Update"/>
                    </td>
                    <!-- Delete function-->
                    <td><a class="del-button" onclick="return confirm('Are you sure you want to remove this item?');"
                           href="MainController?action=Delete&courseId=<%= course.getCourseId()%>&search=<%= search%>">Delete</a></td>

                </form>
                </tr>
                <%
                    }
                %> 

                </tbody>
            </table>              
            <%
            } else {
                for (CourseDTO course : courseList) {
            %>
            <div class="course-base">
                <div class="course-detail">
                    <img src="<%= course.getCourseImg()%>">
                    <h2><a href="MainController?action=Detail&courseId=<%= course.getCourseId()%>"><%= course.getCourseName()%></a></h2>
                    <h4>Start: <%= course.getStartDate()%></h4>
                    <h4>End: <%= course.getEndDate()%></h4>
                    <h4>Tuition: $<%= course.getCourseFee()%></h4>
                    <input type="hidden" name="courseName" value="<%= course.getCourseName()%>"/>
                    <input type="hidden" name="coursePrice" value="<%= course.getCourseFee()%>"/>
                    <input type="hidden" name="courseType" value="<%= course.getCourseType()%>"/>
                </div>
            </div>
            <%
                    }
                }

                String error = (String) request.getAttribute("ERROR");
                if (error != null) {
            %>
            <div class="error-mess"><%=error%></div>
            <%
                }
            %>


        </main>
    </body>
</html>

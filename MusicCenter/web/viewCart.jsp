<%@page import="minhtl.users.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="minhtl.courses.CourseDAO"%>
<%@page import="java.util.List"%>
<%@page import="minhtl.courses.CourseDTO"%>
<%@page import="minhtl.courses.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link rel="stylesheet" href="css/viewCart.css" type="text/css">
        <link rel="stylesheet" href="css/header.css" type="text/css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <main>

            <h1>SHOPPING CART</h1>
            <%                
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
            %>

            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Course ID</th>
                        <th>Course Name</th>
                        <th>Tuition Fee</th>
                        <th colspan="2">Quantity</th>
                        <th>Total</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        double total = 0;
                        for (CourseDTO course : cart.getCart().values()) {
                            total += course.getCourseFee() * course.getCourseQuantity();
                    %>
                <form action="MainController">
                    <tr>
                        <td><%= count++%></td>
                        <td>
                            <%= course.getCourseId()%>
                            <input type="hidden" name="courseId" value="<%=course.getCourseId()%>">
                        </td>
                        <td><%= course.getCourseName()%></td>
                        <td><%= course.getCourseFee()%></td>
                        <td>
                            <input type="number" name="orderQuantity" value="<%=course.getCourseQuantity()%>" required="">
                        </td>
                        <td>
                            <input type="submit" name="action" value="Update Order"/>
                        </td>
                        <td>$<%= course.getCourseFee() * course.getCourseQuantity()%></td>
                        <td>
                            <a href="MainController?action=Remove&courseId=<%= course.getCourseId()%>" onclick="return confirm('Are you sure you want to remove this item?');">Remove</a>
                        </td>
                    </tr>
                </form>

                <%
                    }
                %>

                </tbody>
            </table>
            <h2 class="total">Total: $<%= total%></h2>
            <form action="MainController">
                <input class="checkout" type="submit" name="action" value="Check Out">
            </form>
            <%
            } else {
            %>
            <h3>Empty!!!</h3>
            <%
                }
                String message = (String) request.getAttribute("MESSAGE");
                if (message == null) {
                    message = "";
                }
            %>
            <%= message%>
        </main>
    </body>
</html>

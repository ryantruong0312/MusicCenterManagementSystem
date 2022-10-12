<%@page import="minhtl.courses.CourseDTO"%>
<%@page import="minhtl.courses.Cart"%>
<%@page import="minhtl.users.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
        <link rel="stylesheet" href="css/payment.css" type="text/css">
        <link rel="stylesheet" href="css/header.css" type="text/css">
    </head>
    <body>
        <%@include file="header.jsp"%>
        <main>
            <h1>PAYMENT DETAIL</h1>
            <%                
                Cart cart = (Cart) session.getAttribute("CART");
                double total = 0;
                if (loginUser == null) {
                    loginUser = new UserDTO();
                }
                for (CourseDTO course : cart.getCart().values()) {
                    total += course.getCourseFee() * course.getCourseQuantity();
                }
            %>

            <form action="MainController">
                Customer name: <input type="text" name="cusName" value="<%=loginUser.getUserName()%>" required=""> <br><br>
                Contact number: <input type="tel" name="cusPhone" value="<%=loginUser.getUserPhone()%>" required=""><br><br>
                Email: <input type="email" name="cusEmail" value="<%=loginUser.getUserEmail()%>" required=""><br><br>
                Total: $<%=total%><br><br>
                Payment Method: <input type="radio" name="paymentType" value="cash" required="">Cash</input>

                <div class="conf-but"><input type="submit" name="action" value="Confirm Booking"/></div>
            </form>


        </main>
    </body>
</html>

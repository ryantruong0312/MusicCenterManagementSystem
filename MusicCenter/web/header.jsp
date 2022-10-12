    <%@page import="minhtl.users.UserDTO"%>

<header>
    <ul class="navigation">
        <li><a href="home.jsp" class="nav">HOME</a></li>
        <li class="courses"><a class="nav" href="SearchCourseController?search=">COURSES</a>
            <div class="nav-courses"></div>
        </li>
        <li><a class="nav" href="MainController?action=Track">TRACK ORDER</a></li>

        </li>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser != null && "AD".equals(loginUser.getUserRoleId())) {
                String empty = "";
        %>
        <li class="inventory"><a class="nav" href="addCourse.jsp">ADD COURSES</a>
        </li>
        <%
        } else {
        %>
        <li><a class="nav" href="MainController?action=View Cart">VIEW CART</a></li>
            <%
                }
            %>
    </ul>
    <%
        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }
    %>
    <form class="searchbox" action="MainController">
        <input type="text" name="search"  placeholder="Enter course name" value="<%=search%>"/>
        <input type="submit" name="action" value="Search"/>
    </form>
    <%
        if (loginUser != null) {
    %>
    <div class="loginField">
        Hi, <a class="greet" href=""><%=loginUser.getUserName()%></a> |
        <a class="logout" href="MainController?action=Sign out">Sign out</a>
    </div>

    <%
    } else {
    %>
    <div class="loginField">
        <a class="login" href="login.jsp" >Sign in</a> |
        <a class="signup" href="createUser.jsp" >Sign up</a>
    </div>
    <%
        }
    %>
</header>

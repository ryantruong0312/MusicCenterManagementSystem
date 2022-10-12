/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtl.courses.CourseDAO;
import minhtl.courses.CourseDTO;
import minhtl.courses.CourseError;

/**
 *
 * @author tlmin
 */
public class AddCourseController extends HttpServlet {

    private static final String ERROR = "addCourse.jsp";
    private static final String SUCCESS = "addCourse.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CourseError courseError = new CourseError();
        boolean checkValidation = true;
        CourseDAO dao = new CourseDAO();
        try {
            String courseId = request.getParameter("courseId");
            String courseImg = request.getParameter("courseImg");
            String courseName = request.getParameter("courseName");
            String courseDesc = request.getParameter("courseDesc");
            double courseFee = Double.parseDouble(request.getParameter("courseFee"));
            String courseType = request.getParameter("courseType");
            int courseQuantity = Integer.parseInt(request.getParameter("courseQuantity"));
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            String courseStatus = request.getParameter("courseStatus");
            CourseDTO tmpCourse = new CourseDTO(courseId, courseType, courseName, courseImg, courseDesc, courseFee, courseQuantity, startDate, endDate, courseStatus);
            boolean checkDuplicate = dao.checkDuplicate(courseId);
            if (checkDuplicate) {
                courseError.setCourseId("This courseID has been taken!!");
                checkValidation = false;
                request.setAttribute("TMP_COURSE", tmpCourse);
            }
            if (checkValidation) {

                boolean checkAdd = dao.insert(tmpCourse);
                if (checkAdd) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Course Added Successfully!!");
                } else {
                    courseError.setError("Error detected!!");
                }
            } else {
                request.setAttribute("COURSE_ERROR", courseError);
            }

        } catch (Exception e) {
            log("Error at AddCourseController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

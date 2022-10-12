/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.controllers;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtl.courses.CourseDAO;
import minhtl.updateinfo.UpdateInfoDAO;
import minhtl.updateinfo.UpdateInfoDTO;
import minhtl.users.UserDTO;

/**
 *
 * @author tlmin
 */
public class UpdateCourseController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchCourseController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String adminId = request.getParameter("adminId");
            String courseId = request.getParameter("courseId");
            String courseType = request.getParameter("courseType");
            String courseName = request.getParameter("courseName");
            String courseDesc = request.getParameter("courseDesc");
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            double courseFee = Double.parseDouble(request.getParameter("courseFee"));
            int courseQuantity = Integer.parseInt(request.getParameter("courseQuantity"));
            String courseStatus = request.getParameter("courseStatus");

            CourseDAO dao = new CourseDAO();
            boolean checkUpdate = dao.updateCourse(courseId, courseType, courseName, courseDesc, startDate, endDate, courseFee, courseQuantity, courseStatus);
            if (checkUpdate) {
                url = SUCCESS;
                UpdateInfoDAO udao = new UpdateInfoDAO();
                udao.updateLastInfo(adminId);
                UpdateInfoDTO updateInfo = udao.getUpdateInfo();
                request.setAttribute("UPDATE_INFO", updateInfo);
            }
        } catch (Exception e) {
            log("Error at UpdateCourseController: " + e.toString());
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
        processRequest(request, response);
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
        processRequest(request, response);
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

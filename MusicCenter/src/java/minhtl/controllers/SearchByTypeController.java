/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtl.courses.CourseDAO;
import minhtl.courses.CourseDTO;
import minhtl.updateinfo.UpdateInfoDAO;
import minhtl.updateinfo.UpdateInfoDTO;

/**
 *
 * @author tlminh
 */
public class SearchByTypeController extends HttpServlet {

    private static final String ERROR = "course.jsp";
    private static final String SUCCESS = "course.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String courseType = request.getParameter("courseType");
            CourseDAO dao = new CourseDAO();
            List<CourseDTO> courseList = dao.getCourseListByType(courseType);
            UpdateInfoDAO udao = new UpdateInfoDAO();
            UpdateInfoDTO updateInfo = udao.getUpdateInfo();
            if (courseList.size() > 0) {
                request.setAttribute("COURSE_LIST", courseList);
                url = SUCCESS + "?courseType=" + courseType;
            } else {
                request.setAttribute("ERROR", "No course found!");
            }
            request.setAttribute("UPDATE_INFO", updateInfo);
        } catch (Exception e) {
            log("Error at SearchCourseController: " + e.toString());
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

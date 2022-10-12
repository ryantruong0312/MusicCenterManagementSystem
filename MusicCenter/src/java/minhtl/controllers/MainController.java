/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tlmin
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String NULL_CONTROLLER = "NullController";
    private static final String SIGNIN = "Sign in";
    private static final String SIGNIN_CONTROLLER = "SignInController";
    private static final String SEARCH_PRODUCT = "Search";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchCourseController";
    private static final String DELETE_PRODUCT = "Delete";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteCourseController";
    private static final String UPDATE_PRODUCT = "Update";
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateCourseController";
    private static final String REGISTER = "Register";
    private static final String REGISTER_CONTROLLER = "RegisterController";
    private static final String SIGNOUT = "Sign out";
    private static final String SIGNOUT_CONTROLLER = "SignOutController";
    private static final String ADD_TO_CART = "Add to Cart";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    private static final String PRODUCT_DETAIL = "Detail";
    private static final String PRODUCT_DETAIL_CONTROLLER = "CourseDetailController";
    private static final String VIEW_CART = "View Cart";
    private static final String VIEW_CART_PAGE = "viewCart.jsp";
    private static final String REMOVE_ORDER = "Remove";
    private static final String REMOVE_ORDER_CONTROLLER = "RemoveOrderController";
    private static final String UPDATE_ORDER = "Update Order";
    private static final String UPDATE_ORDER_CONTROLLER = "UpdateOrderController";
    private static final String CHECK_OUT = "Check Out";
    private static final String CHECK_OUT_CONTROLLER = "CheckOutController";
    private static final String ADD_PRODUCT = "Add Course";
    private static final String ADD_PRODUCT_CONTROLLER = "AddCourseController";
    private static final String CONFIRM_BOOKING = "Confirm Booking";
    private static final String COMPLETE_ORDER_CONTROLLER = "CompleteOrderController";
    private static final String TRACK = "Track";
    private static final String TRACK_CONTROLLER = "TrackController";
    private static final String SEARCH_BY_TYPE = "Search By Type";
    private static final String SEARCH_BY_TYPE_CONTROLLER = "SearchByTypeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = NULL_CONTROLLER;
            } else if (SIGNIN.equals(action)) {
                url = SIGNIN_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;
            } else if (UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTROLLER;
            } else if (SIGNOUT.equals(action)) {
                url = SIGNOUT_CONTROLLER;
            } else if (ADD_TO_CART.equals(action)) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (PRODUCT_DETAIL.equals(action)) {
                url = PRODUCT_DETAIL_CONTROLLER;
            } else if (VIEW_CART.equals(action)) {
                url = VIEW_CART_PAGE;
            } else if (REMOVE_ORDER.equals(action)) {
                url = REMOVE_ORDER_CONTROLLER;
            } else if (UPDATE_ORDER.equals(action)) {
                url = UPDATE_ORDER_CONTROLLER;
            } else if (CHECK_OUT.equals(action)) {
                url = CHECK_OUT_CONTROLLER;
            } else if (ADD_PRODUCT.equals(action)) {
                url = ADD_PRODUCT_CONTROLLER;
            } else if (CONFIRM_BOOKING.equals(action)) {
                url = COMPLETE_ORDER_CONTROLLER;
            } else if (TRACK.equals(action)) {
                url = TRACK_CONTROLLER;
            } else if (SEARCH_BY_TYPE.equals(action)) {
                url = SEARCH_BY_TYPE_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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

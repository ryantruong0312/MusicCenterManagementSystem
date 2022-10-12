/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.courses;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tlmin
 */
public class Cart {

    private Map<String, CourseDTO> cart;

    public Cart() {
    }

    public Cart(Map<String, CourseDTO> cart) {
        this.cart = cart;
    }

    public Map<String, CourseDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, CourseDTO> cart) {
        this.cart = cart;
    }

    public boolean add(CourseDTO course) {
        boolean check = false;
        if (this.cart == null) {
            this.cart = new HashMap<>();
            this.cart.put(course.getCourseId(), new CourseDTO());
        }
        if (this.cart.containsKey(course.getCourseId())) {
            int currentQuantity = (this.cart.get(course.getCourseId()).getCourseQuantity());
            course.setCourseQuantity(currentQuantity + course.getCourseQuantity());
        }
        this.cart.put(course.getCourseId(), course);
        check = true;
        return check;
    }

    public boolean remove(String courseId) {
        boolean check = false;
        if (this.cart != null) {
            if (this.cart.containsKey(courseId)) {
                this.cart.remove(courseId);
                check = true;
            }
        }
        return check;
    }

    public boolean update(String courseId, CourseDTO course) {
        boolean check = false;
        if (this.cart != null) {
            if (this.cart.containsKey(courseId)) {
                this.cart.replace(courseId, course);
                check = true;
            }
        }
        return check;
    }
}

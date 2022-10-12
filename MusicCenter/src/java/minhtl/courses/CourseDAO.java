/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.courses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhtl.utils.DBUtils;

/**
 *
 * @author tlmin
 */
public class CourseDAO {

    private static final String SEARCH_COURSE = "SELECT courseId, S2.courseTypeName as courseType, courseName, courseDesc, courseFee, courseQuantity, courseImg, startDate, endDate, courseStatus "
            + "FROM tblCourses as S1, tblCourseTypes as S2 WHERE S1.courseName LIKE ? AND S1.courseTypeId = S2.courseTypeId";
    private static final String SEARCH_BY_ID = "SELECT courseId, S2.courseTypeName as courseType, courseName, courseDesc, courseFee, startDate, endDate, courseQuantity, courseImg, courseStatus "
            + "FROM tblCourses as S1, tblCourseTypes as S2 WHERE S1.courseId = ? AND S1.courseTypeId = S2.courseTypeId";
    private static final String DELETE_COURSE = "DELETE FROM tblCourses WHERE courseId=?";
    private static final String SEARCH_COURSE_TYPE = "SELECT courseTypeName FROM tblCourseTypes";
    private static final String UPDATE_COURSE = "UPDATE tblCourses SET courseTypeId=?, courseName=?, courseDesc=?, courseFee=?, startDate=?, endDate=?, courseQuantity=?, courseStatus=? WHERE courseId=?";
    private static final String UPDATE_QUANTITY = "UPDATE tblCourses SET courseQuantity=? WHERE courseId=?";
    private static final String GET_COURSE_QUANTITY = "SELECT courseQuantity FROM tblCourses WHERE courseId=?";
    private static final String CHECK_DUPLICATE = "SELECT courseId FROM tblCourses WHERE courseId=?";
    private static final String INSERT = "INSERT INTO tblCourses(courseId, courseTypeId, courseName, courseDesc, startDate, endDate, "
            + "courseFee, courseQuantity, courseImg, courseStatus) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String SEARCH_COURSE_TYPE_ID = "SELECT courseTypeId FROM tblCourseTypes WHERE courseTypeName=?";
    private static final String SEARCH_BY_TYPE = "SELECT courseId, S2.courseTypeName as courseType, courseName, courseDesc, courseFee, courseQuantity, courseImg, startDate, endDate, courseStatus "
            + "FROM tblCourses as S1, tblCourseTypes as S2 WHERE S2.courseTypeName = ? AND S1.courseTypeId = S2.courseTypeId";

    public CourseDTO getCourseById(String courseId) throws SQLException {
        CourseDTO course = new CourseDTO();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_ID);
                ptm.setString(1, courseId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    course.setCourseId(courseId);
                    course.setCourseType(rs.getString("courseType"));
                    course.setCourseName(rs.getString("courseName"));
                    course.setCourseDesc(rs.getString("courseDesc"));
                    course.setCourseFee(rs.getDouble("courseFee"));
                    course.setStartDate(rs.getDate("startDate"));
                    course.setEndDate(rs.getDate("endDate"));
                    course.setCourseQuantity(rs.getInt("courseQuantity"));
                    course.setCourseImg(rs.getString("courseImg"));
                    course.setCourseStatus(rs.getString("courseStatus"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return course;
    }

    public List<CourseDTO> getCourseList(String search) throws SQLException {
        List<CourseDTO> courseList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_COURSE);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String courseId = rs.getString("courseId");
                    String courseType = rs.getString("courseType");
                    String courseName = rs.getString("courseName");
                    String courseDesc = rs.getString("courseDesc");
                    double courseFee = rs.getDouble("courseFee");
                    int courseQuantity = rs.getInt("courseQuantity");
                    String courseImg = rs.getString("courseImg");
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");
                    String courseStatus = rs.getString("courseStatus");
                    courseList.add(new CourseDTO(courseId, courseType, courseName, courseImg, courseDesc, courseFee, courseQuantity, startDate, endDate, courseStatus));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return courseList;
    }
    
    public List<CourseDTO> getCourseListByType(String courseTypeName) throws SQLException {
        List<CourseDTO> courseList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_TYPE);
                ptm.setString(1, courseTypeName);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String courseId = rs.getString("courseId");
                    String courseType = rs.getString("courseType");
                    String courseName = rs.getString("courseName");
                    String courseDesc = rs.getString("courseDesc");
                    double courseFee = rs.getDouble("courseFee");
                    int courseQuantity = rs.getInt("courseQuantity");
                    String courseImg = rs.getString("courseImg");
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");
                    String courseStatus = rs.getString("courseStatus");
                    courseList.add(new CourseDTO(courseId, courseType, courseName, courseImg, courseDesc, courseFee, courseQuantity, startDate, endDate, courseStatus));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return courseList;
    }
    
    public List<String> getCourseTypeList() throws SQLException, ClassNotFoundException {
        List<String> courseTypeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_COURSE_TYPE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String courseTypeName = rs.getString("courseTypeName");
                    courseTypeList.add(courseTypeName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return courseTypeList;
    }

    public boolean deleteCourse(String courseId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_COURSE);
                ptm.setString(1, courseId);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateCourse(String courseId, String courseType, String courseName, String courseDesc, Date startDate, Date endDate, double courseFee, int courseQuantity, String courseStatus) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_COURSE);
                ptm.setString(1, getCourseTypeId(courseType));
                ptm.setString(2, courseName);
                ptm.setString(3, courseDesc);
                ptm.setDouble(4, courseFee);
                ptm.setDate(5, startDate);
                ptm.setDate(6, endDate);
                ptm.setInt(7, courseQuantity);
                ptm.setString(8, courseStatus);
                ptm.setString(9, courseId);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean enoughQuantity(String courseId, int orderQuantity) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int curQuantity = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_COURSE_QUANTITY);
                ptm.setString(1, courseId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    curQuantity = rs.getInt("courseQuantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return curQuantity >= orderQuantity;
    }

    public boolean checkDuplicate(String courseId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, courseId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String getCourseTypeId(String courseType) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String courseTypeId = "";
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_COURSE_TYPE_ID);
            if (conn != null) {
                ptm.setString(1, courseType);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    courseTypeId = rs.getString("courseTypeId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return courseTypeId;
    }

    public boolean insert(CourseDTO course) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(INSERT);
            if (conn != null) {
                String courseTypeId = getCourseTypeId(course.getCourseType());
                ptm.setString(1, course.getCourseId());
                ptm.setString(2, courseTypeId);
                ptm.setString(3, course.getCourseName());
                ptm.setString(4, course.getCourseDesc());
                ptm.setDate(5, course.getStartDate());
                ptm.setDate(6, course.getEndDate());
                ptm.setDouble(7, course.getCourseFee());
                ptm.setInt(8, course.getCourseQuantity());
                ptm.setString(9, course.getCourseImg());
                ptm.setString(10, course.getCourseStatus());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int getCurQuantity(String courseId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int curQuantity = 0;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_COURSE_QUANTITY);
            if (conn != null) {
                ptm.setString(1, courseId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    curQuantity = rs.getInt("courseQuantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return curQuantity;
    }

    public boolean updateQuantity(String courseId, int courseQuantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            int curQuantity = this.getCurQuantity(courseId);
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(UPDATE_QUANTITY);
            if (conn != null) {
                ptm.setInt(1, curQuantity - courseQuantity);
                ptm.setString(2, courseId);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}

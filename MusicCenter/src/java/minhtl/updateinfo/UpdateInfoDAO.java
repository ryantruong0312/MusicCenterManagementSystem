/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.updateinfo;

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
 * @author tlminh
 */
public class UpdateInfoDAO {

    private static final String GET_UPDATE_INFO = "SELECT S1.updateAdminId, S2.userName, S1.updateDate FROM tblUpdateInfo as S1, tblUsers as S2"
            + " WHERE S1.updateDate = (SELECT MAX(updateDate) FROM tblUpdateInfo) AND S1.updateAdminId = S2.userId";
    private static final String GET_UPDATE_ID_LIST = "SELECT updateId FROM tblUpdateInfo";
    private static final String UPDATE_INFO = "INSERT INTO tblUpdateInfo(updateId, updateAdminId, updateDate) VALUES(?,?,?)";

    public UpdateInfoDTO getUpdateInfo() throws SQLException {
        UpdateInfoDTO updateInfo = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_UPDATE_INFO);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String adminId = rs.getString("updateAdminId");
                    String adminName = rs.getString("userName");
                    Date lastUpDate = rs.getDate("updateDate");
                    updateInfo = new UpdateInfoDTO(adminId, adminName, lastUpDate);
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
        return updateInfo;

    }

    public String generateUpdateId() throws SQLException {
        List<String> updateIdList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String newUpdateId;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_UPDATE_ID_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String updateId = rs.getString("updateId");
                    updateIdList.add(updateId);
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
        int update = updateIdList.size() + 1;
        for (int i = 0; i < updateIdList.size(); i++) {
            int curUpdate = (Integer.parseInt(updateIdList.get(i).substring(1)));
            if (curUpdate == update) {
                update += 1;
            }
        }
        newUpdateId = "U" + String.format("%02d", update);
        return newUpdateId;
    }

    public void updateLastInfo(String adminId) throws SQLException {
        String updateId = generateUpdateId();
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_INFO);
                ptm.setString(1, updateId);
                ptm.setString(2, adminId);
                ptm.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ptm.executeUpdate();
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
    }
}

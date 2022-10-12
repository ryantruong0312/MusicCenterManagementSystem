/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtl.updateinfo;

import java.sql.Date;

/**
 *
 * @author tlminh
 */
public class UpdateInfoDTO {
    private String adminId;
    private String adminName;
    private Date lastUpDate;

    public UpdateInfoDTO() {
    }

    public UpdateInfoDTO(String adminId, String adminName, Date lastUpDate) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.lastUpDate = lastUpDate;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getLastUpDate() {
        return lastUpDate;
    }

    public void setLastUpDate(Date lastUpDate) {
        this.lastUpDate = lastUpDate;
    }
    
    
}


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yukselaggoz
 */
public class Yemek {
    
    int id;
    Date gun;
    String sabah;
    String ogle;
    String aksam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGun() {
        return gun;
    }

    public void setGun(Date gun) {
        this.gun = gun;
    }

    public String getSabah() {
        return sabah;
    }

    public void setSabah(String sabah) {
        this.sabah = sabah;
    }

    public String getOgle() {
        return ogle;
    }

    public void setOgle(String ogle) {
        this.ogle = ogle;
    }

    public String getAksam() {
        return aksam;
    }

    public void setAksam(String aksam) {
        this.aksam = aksam;
    }
    
}

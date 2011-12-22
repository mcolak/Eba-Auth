/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.oturum.entity;

import java.io.Serializable;

/**
 *
 * @author murat
 */
public class Oturum implements Serializable {

    private static final long serialVersionUID = -8091913392876609903L;
    private String kimlikNo;
    private Integer level;

    public Oturum(String kimlikNo, Integer level) {
        this.kimlikNo = kimlikNo;
        this.level = level;
    }

    public Oturum() {
    }

    /**
     * @return the kimlikNo
     */
    public String getKimlikNo() {
        return kimlikNo;
    }

    /**
     * @param kimlikNo the kimlikNo to set
     */
    public void setKimlikNo(String kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    /**
     * @return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}

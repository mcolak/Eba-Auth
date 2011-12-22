/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.oturum.entity;

/**
 *
 * @author murat
 */
public class OturumAcC {
    private String token;
    private Sonuc sonuc;

    public Sonuc getSonuc() {
        return sonuc;
    }

    public void setSonuc(Sonuc sonuc) {
        this.sonuc = sonuc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}

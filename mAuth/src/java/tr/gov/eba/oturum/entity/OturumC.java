/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.oturum.entity;

/**
 *
 * @author murat
 */
public class OturumC {
    private Sonuc sonuc;
    private Oturum oturum;

    public Oturum getOturum() {
        return oturum;
    }

    public void setOturum(Oturum oturum) {
        this.oturum = oturum;
    }

    public Sonuc getSonuc() {
        return sonuc;
    }

    public void setSonuc(Sonuc sonuc) {
        this.sonuc = sonuc;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.oturum.entity;

/**
 *
 * @author murat
 */
public class Sonuc {
    private String sonucKodu;
    private String sonucAciklamasi;

    public Sonuc(String sonucKodu, String sonucAciklamasi) {
        this.sonucKodu = sonucKodu;
        this.sonucAciklamasi = sonucAciklamasi;
    }

    public Sonuc() {
    }
 
    public String getSonucAciklamasi() {
        return sonucAciklamasi;
    }

    public void setSonucAciklamasi(String sonucAciklamasi) {
        this.sonucAciklamasi = sonucAciklamasi;
    }

    public String getSonucKodu() {
        return sonucKodu;
    }

    public void setSonucKodu(String sonucKodu) {
        this.sonucKodu = sonucKodu;
    }
    
}

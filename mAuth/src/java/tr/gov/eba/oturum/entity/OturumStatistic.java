/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gov.eba.oturum.entity;

import java.io.Serializable;

/**
 *
 * @author iozen
 */
public class OturumStatistic implements Serializable{
    private static final long serialVersionUID = -2304572343180340067L;

    private String oturumZamanAraligi;
    private int oturumSayisi;

    public OturumStatistic(String oturumZamanAraligi){
        this.oturumZamanAraligi = oturumZamanAraligi;
        this.oturumSayisi = 0;
    }

    public String getOturumZamanAraligi() {
        return oturumZamanAraligi;
    }

    public void setOturumZamanAraligi(String oturumZamanAraligi) {
        this.oturumZamanAraligi = oturumZamanAraligi;
    }

    public int getOturumSayisi() {
        return oturumSayisi;
    }

    public void setOturumSayisi(int oturumSayisi) {
        this.oturumSayisi = oturumSayisi;
    }

    public void oturumSayisiArtir(){
        oturumSayisi++;
    }

    public void oturumSayisiAzalt(){
        if(oturumSayisi > 0)
            oturumSayisi--;
    }

}

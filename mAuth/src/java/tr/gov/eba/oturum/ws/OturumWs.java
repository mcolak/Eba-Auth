/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.oturum.ws;

import java.util.UUID;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import tr.gov.eba.cache.OtCache;
import tr.gov.eba.oturum.entity.Oturum;
import tr.gov.eba.oturum.entity.OturumAcC;
import tr.gov.eba.oturum.entity.Sonuc;

/**
 *
 * @author murat
 */
@WebService(serviceName = "OturumWs")
public class OturumWs {

    /** This is a sample web service operation */
    @WebMethod(operationName = "oturumAc")
    public OturumAcC hello(@WebParam(name = "kimlikNo") String kimlikNo,@WebParam(name = "password")String password) {
        if(kimlikNo.equals("a") && password.equals("b") ){
            String uid= UUID.randomUUID().toString();
           // uid+=UUID.randomUUID().toString();
            /*
             * Burada kullanici bilgileri alinacak
             */
            OtCache.getInstance().oturumEkle(uid,"hebele");
           OturumAcC oturumAc= new OturumAcC();
           oturumAc.setToken(uid);
           oturumAc.setSonuc(new Sonuc("eba.001","HATASIZ") );
            return oturumAc;
        }else{
            return new OturumAcC();
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public OturumAcC operation(@WebParam(name = "kullanici") String kullanici) {
        //TODO write your implementation code here:
       
        OturumAcC ac= new OturumAcC();
        String uid=UUID.randomUUID().toString();
        ac.setSonuc(new Sonuc("aa","bb"));
        ac.setToken(uid);
        Oturum ot= new Oturum();
        ot.setKimlikNo("1154221225");
        ot.setLevel(1);
        OtCache.getInstance().oturumEkle(uid,ot );
        return ac;
    }
}

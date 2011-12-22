/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.cache;

/**
 *
 * @author murat
 */
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;
import net.sf.ehcache.search.SearchException;
import net.sf.ehcache.search.expression.Or;


/**
 *
 * @author murat
 */
public class OtCache {

    private static final CacheManager cacheManager = new CacheManager();
    private static final String otCacheName = "OtCache";
    private static final String generalCacheName = "GeneralCache";
    private static final String statisticCacheName = "StatisticCache";
    private static final String statisticUUID = UUID.randomUUID().toString();

    private OtCache() {
    }
    private static OtCache otcache = new OtCache();
    
    public static OtCache getInstance(){       
            return otcache ;        
    }
    
    private Ehcache getCache() {
        return cacheManager.getCache(otCacheName);
    }

    private Ehcache getGeneralCache() {
        return cacheManager.getCache(generalCacheName);
    }

    private Ehcache getStatisticCache() {
        return cacheManager.getCache(statisticCacheName);
    }

    public void oturumEkle(String key, Object obj) {
        Element el = new Element(key, obj);
        getCache().put(el);
        //oturumSayisiArtir();
    }

    public Element oturumKontrol(String key) {
        Element elem = getCache().get(key);
        return elem;
    }

    public void oturumKaldir(String key) {
        Element elem = getCache().get(key);
        getCache().remove(key);
        oturumSayisiAzalt(elem.getCreationTime());
    }

    public void bilgiEkle(String key, Object obj) {
        Element el = new Element(key, obj);
        getGeneralCache().put(el);
    }

    public Element bilgiGetir(String key) {
        Element elem = getGeneralCache().get(key);
        return elem;
    }

    public void bilgiKaldir(String key) {
        getGeneralCache().remove(key);
    }

    private void oturumSayisiArtir() {
        GregorianCalendar gcal = new GregorianCalendar();
        int minute = gcal.get(GregorianCalendar.MINUTE);
        int hour = gcal.get(GregorianCalendar.HOUR_OF_DAY);
        String statisticKey = "" + ((hour * 6) + (minute / 10));
        Element element = getStatisticCache().get(statisticUUID + statisticKey);
        OturumStatistic oturumSayisi = null;
        if (element == null) {
            oturumSayisi = new OturumStatistic(statisticKey);
        } else {
            oturumSayisi = (OturumStatistic) element.getValue();
        }
        oturumSayisi.oturumSayisiArtir();
        Element element2 = new Element(statisticUUID + statisticKey, oturumSayisi);
        getStatisticCache().put(element2);
    }

    private void oturumSayisiAzalt(long time) {
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTimeInMillis(time);
        //gcal.add(GregorianCalendar.MINUTE, -20);
        int minute = gcal.get(GregorianCalendar.MINUTE);
        int hour = gcal.get(GregorianCalendar.HOUR_OF_DAY);
        String statisticKey = "" + ((hour * 6) + (minute / 10));
        Element element = getStatisticCache().get(statisticUUID + statisticKey);
        OturumStatistic oturumSayisi = null;
        if (element == null) {
            oturumSayisi = new OturumStatistic(statisticKey);
        } else {
            oturumSayisi = (OturumStatistic) element.getValue();
        }
        oturumSayisi.oturumSayisiAzalt();
        Element element2 = new Element(statisticUUID + statisticKey, oturumSayisi);
        getStatisticCache().put(element2);
    }

    public String anlikOturumSayisiHesapla(boolean detayli) {
        try {
            Attribute<String> oturumZamanAraligiAtt = getStatisticCache().getSearchAttribute("oturumZamanAraligi");
            GregorianCalendar gcal = new GregorianCalendar();
            int minute = gcal.get(GregorianCalendar.MINUTE);
            int hour = gcal.get(GregorianCalendar.HOUR_OF_DAY);
            String firstInterval = "" + ((hour * 6) + (minute / 10));
            int fourthRatio = (10 - (minute % 10)) * 10;
            gcal.add(GregorianCalendar.MINUTE, -10);
            minute = gcal.get(GregorianCalendar.MINUTE);
            hour = gcal.get(GregorianCalendar.HOUR_OF_DAY);
            String secondInterval = "" + ((hour * 6) + (minute / 10));
            gcal.add(GregorianCalendar.MINUTE, -10);
            minute = gcal.get(GregorianCalendar.MINUTE);
            hour = gcal.get(GregorianCalendar.HOUR_OF_DAY);
            String thirdInterval = "" + ((hour * 6) + (minute / 10));
            gcal.add(GregorianCalendar.MINUTE, -10);
            minute = gcal.get(GregorianCalendar.MINUTE);
            hour = gcal.get(GregorianCalendar.HOUR_OF_DAY);
            String fourthInterval = "" + ((hour * 6) + (minute / 10));

            Query query = null;
            if(detayli)
                query = getStatisticCache().createQuery().includeKeys().includeValues().addCriteria(new Or(oturumZamanAraligiAtt.eq(firstInterval), oturumZamanAraligiAtt.eq(secondInterval)).or(oturumZamanAraligiAtt.eq(thirdInterval)).or(oturumZamanAraligiAtt.eq(fourthInterval)));
            else
                query = getStatisticCache().createQuery().includeValues().addCriteria(new Or(oturumZamanAraligiAtt.eq(firstInterval), oturumZamanAraligiAtt.eq(secondInterval)).or(oturumZamanAraligiAtt.eq(thirdInterval)).or(oturumZamanAraligiAtt.eq(fourthInterval)));
        
            Results resultElement = query.execute();
            int toplamOturumSayisi = 0;
            String denemeMesaj = "";
            if (resultElement.hasValues()) {
                List<Result> resultList = resultElement.all();

                for (Result result : resultList) {
                    OturumStatistic obj = (OturumStatistic) result.getValue();
                    if(detayli){
                        denemeMesaj += "Key : " + result.getKey() + "   ZAMANA ARALIĞI : " + obj.getOturumZamanAraligi() + "  OTURUM SAYISI : " + obj.getOturumSayisi() + "<br />";
                        denemeMesaj += "-----------------------<br />";
                    }
                    if(obj.getOturumZamanAraligi().equals(fourthInterval)){
                        toplamOturumSayisi += ((obj.getOturumSayisi() * fourthRatio)/100);
                    }  else {
                        toplamOturumSayisi += obj.getOturumSayisi();
                    }
                }

                if(detayli)
                    denemeMesaj += "  TOPLAM OTURUM SAYISI : " + toplamOturumSayisi;
                else
                    denemeMesaj = "" + toplamOturumSayisi;

                return denemeMesaj;

            } else
                return "-1";

        } catch (SearchException e) {
        }

        return "-1";
    }
    
    public void shutdown(){
        cacheManager.shutdown();
    }
}

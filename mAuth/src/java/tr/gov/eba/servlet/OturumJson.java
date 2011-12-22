/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.servlet;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.ehcache.Element;
import tr.gov.eba.cache.OtCache;
import tr.gov.eba.oturum.entity.Oturum;

/**
 *
 * @author murat
 */
public class OturumJson extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                Gson gson = new Gson();
        String sonuc = gson.toJson(new Oturum());
        try {
            String oturumKodu = request.getParameter("oturumKodu");

            Oturum oturum = getOturum(oturumKodu);
            int operasyonKodu = 0;
            try {
                operasyonKodu = Integer.parseInt(request.getParameter("opKodu"));
            } catch (Exception ex) {
                operasyonKodu = -1;
            }
            if (oturum != null && operasyonKodu != -1) {
                if (operasyonKodu == 1 ) {
                    //oturum Kontrol yapılıyor
                    sonuc = gson.toJson(oturum);
                    
                    out.print(sonuc);
                } else if (operasyonKodu == 2) {
                    //Kullanıcı bilgileri gönder
                    /*
                     * Detayları için ayrı bir yere gidilebilinir
                     */
                    sonuc=gson.toJson(null);
                    out.print(sonuc);
                } else{
                   // sonuc = gson.toJson(SonucKodu.sunucuHatasi);
                    out.print(sonuc);
                }
            } else{
                //  sonuc = gson.toJson(SonucKodu.gecersizOturum);
                out.print(sonuc);
            }
        }  catch(Exception ex){
           // sonuc = gson.toJson(new SonucKodu(SonucKodu.sunucuHatasi));
            out.print(sonuc);
        } finally {
            out.close();
        }
    }

    private Oturum getOturum(String oturumKodu) {
        Element element = OtCache.getInstance().oturumKontrol(oturumKodu);
        if (element != null) {
            // String jObj=  element.getValue().toString();
            Oturum oturum = (Oturum) element.getValue();
            return oturum;
        }
        return null;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

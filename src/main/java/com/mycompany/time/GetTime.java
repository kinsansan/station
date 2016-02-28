/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author 2nas2nik
 */
@WebServlet(name = "GetTime", urlPatterns = {"/GetTime"})
public class GetTime extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(getServletContext().getRealPath("time.txt"))));
            String st = "";
            Calendar now = Calendar.getInstance();
            int count = 0;
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int imin = now.get(Calendar.MINUTE);
            String min = "";
            switch (imin) {
                case 0:
                    min = "00";
                    break;
                case 1:
                    min = "01";
                    break;
                case 2:
                    min = "02";
                    break;
                case 3:
                    min = "03";
                    break;
                case 4:
                    min = "04";
                    break;
                case 5:
                    min = "05";
                    break;
                case 6:
                    min = "06";
                    break;
                case 7:
                    min = "07";
                    break;
                case 8:
                    min = "08";
                    break;
                case 9:
                    min = "09";
                    break;
                default:
                    min = String.valueOf(imin);
            }
            int time = Integer.parseInt(String.valueOf(hour) + min);
            if (hour == 0) {
                hour = 25;
            }
            st = br.readLine();
            String sp[] = st.split(" ");
            JSONObject obj = new JSONObject();
            for (int i = 0; i < sp.length; i++) {
                int tmp = Integer.parseInt(sp[i]);
//                if (time < tmp) {
//                    out.print(time);
//                    out.print(tmp);
//                }
                if (time + 7 <= tmp) {
                    obj.put("駅", "立川南");
                    obj.put("時刻", tmp);
                    break;
                }
                if (time + 5 <= tmp) {
                    obj.put("駅", "立川北");
                    obj.put("時刻", tmp);
                    break;
                }
            }
            JSONArray array = JSONArray.fromObject(obj);
            out.print(array);
        } catch (IOException iOException) {
            out.print(iOException);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

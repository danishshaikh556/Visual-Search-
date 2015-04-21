/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.imageio.*;

/**
 *
 * @author Danish556
 */
public class First extends HttpServlet {

    public static BufferedImage readImg(String uri) throws MalformedURLException, IOException {
        BufferedImage image = null;
        try {
            URL url = new URL(uri);
            //InputStream in = new BufferedInputStream(url.openStream());
            //ByteArrayOutputStream out = new ByteArrayOutputStream();
            //byte[] buf = new byte[1024];
            //int n = 0;
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            BufferedImage urlImage= null;
            String productID = null;
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("submitButton") != null) {
                String username = request.getParameter("usernameField");
                String password = request.getParameter("passwordField");
                if (username.length() > 0  ||  password.length() >0) {

                    if(username.length() > 0){
                       //"http://productshots1.modcloth.net/productshots/0129/7217/4399f1c3562548a88df66702525bf78d.jpg?1370554496" 
                     urlImage = readImg(username);
                    }else if (password.length() > 0){
                        productID = password;
                    }else {
                        // Default
                        productID = "103233-2083593.jpg";
                    }
                    ArrayList<String> list = new ArrayList<String>();
                    Searcher tempSearch = new Searcher();
                             list = tempSearch.runSearcher(urlImage, productID);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", username);
                    RequestDispatcher rd = request.getRequestDispatcher("Second");
                    String s = "example";
                    request.setAttribute("list", list);
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }
        } finally {
            // out.close();
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

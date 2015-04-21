           /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Danish556
 */
@WebServlet(urlPatterns = {"/Second"})
public class Second extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        try {
            String jspPath = session.getServletContext().getRealPath("");
            String txtFilePath = jspPath+ "/video.txt";
            BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine())!= null){
                sb.append(line+"\n");
            }
           
            /* TODO output your page here. You may use following sample code. */
                        
           //  Object list =  session.getAttribute("pids");
             
            Object array =  request.getAttribute("list");
            
            String s = array.toString();
            
            s = s.replace("[", "");
            s = s.replace("]", "");
            String[] allImages = s.split(",");
           String baseUrl = "https://11main.com/tucsontiquescollectibles/red-wing-wall-pocket-pottery-lute/p/";
           String matchingBaseUrl = "https://11main.com/search?source=user&q=";  
           
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Second</title>");            
            out.println("</head>");
            out.println("<body bgcolor=\"#E6E6FA\">");
            
            //out.println("<h1>Servlet Second at " + request.getAttribute("list") +"</h1>");
            //out.println("<h2>list " + allImages.length +"</h2>");
           
            String originalImgSrc = allImages[0];
            if(!originalImgSrc.equals("Danish"))
            {    
                originalImgSrc = originalImgSrc.trim();
                String origPid = originalImgSrc.substring(0, originalImgSrc.indexOf("-"));
                String originalProductUrl = baseUrl + origPid;
                out.println("<h2> Original Item:-  </h2>");
                out.println("<span>");
                out.println("<img src=" + "allImages\\" + originalImgSrc + " alt=\"Original Image\" width=\"200\" height=\"200\" />");
                out.println("<br>");
                out.println("<a href = " + originalProductUrl + ">Original Item</a>");
                out.println("</span>");
            }      
            else
            {
                out.println("<h2> Original Item:-  </h2>");
                out.println("<span>");
                out.println("<img src=" + "allImages\\default.jpg alt=\"Original Image\" width=\"200\" height=\"200\" />");
                //out.println("<a href = " + originalProductUrl + ">Original Item</a>");
                out.println("</span>");
                
            }
            String query = "ProductID:";
            for(int i=1;i<allImages.length;i++)
            {
                String imageSource = allImages[i];
                imageSource = imageSource.trim();
               
                imageSource = imageSource.substring(0,imageSource.indexOf("-"));
                if(i <allImages.length-1)
                {
                    query+= imageSource + ' ' + "OR ProductID:";
                }   
                else
                {
                    query+= imageSource + " ";

                }
            }
            String uri = URLEncoder.encode(query);
            String finalMatchingUrl = matchingBaseUrl + uri;
            
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            //out.println("<h2>"+ finalMatchingUrl+"</h2>"); 
            out.println("<a href=" + finalMatchingUrl + "> Matching Items </a>");
            out.println("<br>");
            out.println("<br>");

            for(int i=1;i<allImages.length;i++)
            {
                String imgSrc = allImages[i];
                imgSrc = imgSrc.trim();
                String matchingPid = imgSrc.substring(0,imgSrc.indexOf("-"));
                String matchingProductUrl = baseUrl + matchingPid;
                
                out.println("<div style=\" display:inline-block; list-style-type: none;padding-right: 20px;" +"\">");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>");
                out.println("<img src=" + "allImages\\" + imgSrc + " alt=\"Matching Product Images\" width=\"180\" height=\"180\" />");
                out.println("</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>");
                out.println("<a href = " + matchingProductUrl + ">Item " + i + "</a>");
                out.println("</th>");
                out.println("</tr>");
                out.println("</table>");

                out.println("</div>");
                
            }      
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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

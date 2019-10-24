package servlets;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Constants;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet("/servlet")
public class MoreComplete extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log("Request received");
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        JSONObject body;

        try {
            body = new JSONObject(jb.toString());
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String resp = "OK";
        out.print(resp);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            InputStream isJson = new URL(Constants.BASEURL_DATABASE + Utils.buildURLParams(request)).openStream();
            String json = IOUtils.toString(isJson,"UTF-8");

            response.setContentType("application/json");
            response.getWriter()
                    .println(json);
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
            e.printStackTrace();
        }
    }

}

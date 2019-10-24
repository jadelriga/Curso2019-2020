package utils;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Utils {

    /**
     * Returns a string with concatenated parameters for use in a URL
     *
     * @param request HTTP request where the parameters that will be concatenated are found
     * @return The string built
     */
    public static String buildURLParams(HttpServletRequest request) {

        String built = "";
        Map<String, String[]> params = request.getParameterMap();

        for (Map.Entry<String, String[]> param : params.entrySet())
            built += "&" + param.getKey() + "=" + param.getValue()[0];


        return built;
    }

    /**
     * Returns a normalized string, with all the acute accents removed for compatibility with
     * more restrictive charsets
     *
     * @param string The string to normalize
     * @return The normalized string
     */

    private static String normalize(String string){
        return string.toLowerCase()
                .replaceAll("á", "a")
                .replaceAll("é", "e")
                .replaceAll("í", "i")
                .replaceAll("ó", "o")
                .replaceAll("ú", "u");
    }

}

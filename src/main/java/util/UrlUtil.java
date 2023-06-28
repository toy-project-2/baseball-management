package util;

import java.util.HashMap;

public class UrlUtil {

    private static HashMap<String, String> map = null;

    public UrlUtil(String url) {
        map = new HashMap<>();
        String[] strings = url.split("\\?");
        String path = strings[0];
        map.put("path", path);

        if (strings.length >= 2) {
            String parameters = strings[1];
            String[] parameter = parameters.split("&");
            for (String param : parameter) {
                String[] p = param.split("=");
                map.put(p[0], p[1]);
            }
        }
    }

    public String getPath() {
        return map.get("path");
    }

    public String getParameter(String key) {
        return map.get(key);
    }

    public void printAllParameter() {
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }

}

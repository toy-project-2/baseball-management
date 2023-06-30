package util;

import java.util.HashMap;
import java.util.Scanner;

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
                if (p.length == 2)
                    map.put(p[0], p[1]);
            }
        }
    }

    public String getPath() {
        return map.get("path");
    }

    public String getStringParameter(String key) {
        String value = map.get(key);
        if (value == null) {
            return null;
        }
        return value;
    }

    public Integer getIntegerParameter(String key) {
        String value = map.get(key);
        if (value == null) {
            return null;
        }
        if (value.matches("-?\\d+(\\.\\d+)?")) {
            return Integer.parseInt(value);
        }
        return -1;
    }

    public static UrlUtil run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("어떤 기능을 요청하시겠습니까?:");
        return new UrlUtil(sc.nextLine());
    }

    public void printAllParameter() {
        map.forEach((key, value) ->
                System.out.println(key + ":" + value)
        );
    }

}

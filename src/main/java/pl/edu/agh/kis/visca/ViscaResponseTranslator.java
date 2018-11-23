package pl.edu.agh.kis.visca;

import java.util.HashMap;
import java.util.Map;

public class ViscaResponseTranslator {
    private static final Map<String, String> MAP;
    static {
        MAP = new HashMap<>();
        MAP.put("41 FF", "ACK");
        MAP.put("51 FF", "OK");

        MAP.put("42 FF", "ACK");
        MAP.put("52 FF", "OK");

        MAP.put("60 01 FF", "Message length error (>14 bytes)");
        MAP.put("60 02 FF", "Syntax error");
        MAP.put("60 03 FF", "Command buffer full");
        MAP.put("60 04 FF", "Command cancelled");
        MAP.put("60 05 FF", "No sockets");
        MAP.put("60 41 FF", "Command not executable");

    }

    public static String translateResponse(String response) {
        String responseBody = response.substring(response.indexOf(" ")+1).trim();
        return MAP.getOrDefault(responseBody, "");
    }
}

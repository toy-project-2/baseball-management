package player.outplayer;

import java.util.Arrays;

public enum OutReason {

    실적부진, 태도불량, 도박, 음주운전, 폭행;

    public static boolean isOutReason(String s) {
        OutReason[] values = OutReason.values();
        return Arrays.stream(values).anyMatch(outReason -> outReason.toString().equals(s));
    }
}
package com.queryholic.dicegame.spring.recorder;

import com.queryholic.dicegame.spring.dice.FraudDice;
import com.queryholic.dicegame.spring.player.Player;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Recorder {
    /**
     * 게임 라운드 현황을 기록한다.
     * 모든 플레이어의 스코어를 기록하며, 사기주사위를 쓰는 플레이어의 경우 주사위의 상태까지 보여줍니다.
     */
    public void recordRound(Map<Integer, Player> players) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (final Player player : players.values()) {
            stringBuilder.append(" ")
                    .append(player.getName())
                    .append(":")
                    .append(player.getScore());

            if (player.getDice() instanceof FraudDice) {
                stringBuilder.append("[")
                        .append(((FraudDice) player.getDice()).getMode().toString())
                        .append("]");
            }
        }
        stringBuilder.append(" ]");

        System.out.println(stringBuilder.toString());
    }

    /**
     * 승자를 기록한다.
     *
     * @param winner 승자
     */
    public void recordWinner(final Player winner) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(winner.getName())
                .append(" 승리했습니다!");

        System.out.println(stringBuilder.toString());
    }
}

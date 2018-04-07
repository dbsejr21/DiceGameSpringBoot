package com.queryholic.dicegame.spring.player;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class PlayerStore {
    private static int nextPlayerIdentifier = 0;
    public static final int THE_NUMBER_OF_PLAYER = 2;
    @Getter
    private final Map<Integer, Player> players = new HashMap<>();

    /**
     * 선수를 등록한다.
     *
     * @param player 등록할 플레이어
     * @return 등록 결과 메시지
     */
    public void putPlayer(final Player player) {
        if (players.size() == THE_NUMBER_OF_PLAYER) {
            log.error("### failed to register player.");
            throw new IllegalStateException("플레이어는 " + THE_NUMBER_OF_PLAYER + "명까지 등록 가능합니다.");
        }
        players.put(player.getIdentifier(), player);

        System.out.println(player.getClass().getSimpleName() + " " + player.getName() + " 등록 완료.");
    }

    /**
     * 다른 선수의 점수를 리턴한다.
     *
     * @param fraudPlayer
     * @return
     */
    public int getOtherPlayerScore(final FraudPlayer fraudPlayer) {
        final Player other = players.values().stream()
                .filter(
                        player -> player.getIdentifier() != fraudPlayer.getIdentifier()
                )
                .findAny()
                .orElse(null);

        return other.getScore();

    }

    /**
     * 플레이어 식별자 생성
     *
     * @return
     */
    public static int generatePlayerIdentifier() {
        return nextPlayerIdentifier++;
    }
}

package com.queryholic.dicegame.spring.judge;

import com.queryholic.dicegame.spring.player.Player;
import com.queryholic.dicegame.spring.player.PlayerStore;
import com.queryholic.dicegame.spring.recorder.Recorder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;

@Slf4j
@Component
@RequiredArgsConstructor
public class Judge {
    private final PlayerStore playerStore;
    private final Recorder recorder;

    /**
     * 선수를 등록한다.
     *
     * @param player
     * @return 등록 결과 메시지
     */
    public void registerPlayer(final Player player) {
        playerStore.putPlayer(player);
    }

    /**
     * 게임을 진행한다.
     * 매 라운드마다 주사위 총합을 기록(출력)할 수 있으며, 모든 라운드가 종료되면 승자를 판정
     *
     * @param round 주사위 던질 횟수
     */
    public void playGame(final int round) {
        validatePlayers();

        for (int i = 1; i <= round; i++) {
            for (final Player player : playerStore.getPlayers().values()) {
                player.play();
            }

            recorder.recordRound(playerStore.getPlayers());
        }

        recorder.recordWinner(chooseWinner());
    }

    // 승자를 판정한다.
    private Player chooseWinner() {
        final Comparator<Player> comparator = Comparator.comparingInt(Player::getScore);
        return playerStore.getPlayers().values().stream()
                .max(comparator)
                .get();
    }

    // 게임 시작 전에 참가자 리스트 유효성 검사
    private void validatePlayers() {
        if (CollectionUtils.isEmpty(playerStore.getPlayers())) {
            log.error("### failed to start game. there is no player.");
            throw new IllegalStateException("등록된 플레이어가 없음.");
        }

        if (playerStore.getPlayers().size() != PlayerStore.THE_NUMBER_OF_PLAYER) {
            log.error("failed to start game. incorrect the number of player.");
            throw new IllegalStateException("플레이어 수가 맞지 않습니다. Correct: " + PlayerStore.THE_NUMBER_OF_PLAYER);
        }

        System.out.println("참가자 리스트 유효성 검사 끝.");
    }
}

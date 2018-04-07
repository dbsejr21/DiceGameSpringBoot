package com.queryholic.dicegame.spring.player;

import com.queryholic.dicegame.spring.dice.Mode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Cheating {
    private final PlayerStore playerStore;

    /**
     * 플레이한다(주사위를 던진다).
     * 사기플레이어는 지고있을경우 얍삽하게도 사기주사위 레벨을 높입니다(강함모드).
     * 6점이상차이로 이기고 있는경우엔 거만해져서 사기주사위 레벨을 낮춥니다(약함모드).
     * 위의 경우가 아닌경우엔 일반모드로 플레이합니다.
     *
     * @param me 치트를 하는 플레이어(자신)
     * @return 치트를 통해 굴려진 주사위 눈 수
     */
    public int roll(final FraudPlayer me) {
        final int otherPlayerScore = playerStore.getOtherPlayerScore(me);

        if (me.getScore() < otherPlayerScore) {
            me.getDice().setMode(Mode.STRONG);
        } else if (me.getScore() - 6 > otherPlayerScore) {
            me.getDice().setMode(Mode.WEAK);
        } else {
            me.getDice().setMode(Mode.NORMAL);
        }

        return me.getDice().roll();
    }
}

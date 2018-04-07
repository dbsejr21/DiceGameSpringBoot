package com.queryholic.dicegame.spring.dice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FraudDice extends Dice {
    /**
     * 사기주사위 모드
     * NORMAL : 일반모드, 보통 주사위와 동일합니다.
     * WEAK : 약함모드, 5이상의 눈금이 나오면 다시 던집니다.
     * STRONG : 강함모드, 2이하의 눈금이 나오면 다시 던집니다.
     */
    private Mode mode = Mode.NORMAL;

    /**
     * 주사위를 던진다.
     * 1~6 사이의 정수 리턴
     *
     * @return
     */
    public int roll() {
        int num = super.roll();

        if (mode.equals(Mode.NORMAL)) {
            return num;
        }

        if (mode.equals(Mode.STRONG)) {
            while (num <= 2) {
                num = super.roll();
            }
            return num;
        }

        if (mode.equals(Mode.WEAK)) {
            while (num >= 5) {
                num = super.roll();
            }
            return num;
        }

        throw new IllegalStateException("사기 주사위 모드가 선택되지 않았습니다.");

    }
}

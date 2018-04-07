package com.queryholic.dicegame.spring.dice;

import java.util.Random;

public class Dice {

    /**
     * 주사위를 던진다.
     * 1~6 사이의 정수 리턴
     *
     * @return
     */
    public int roll() {
        return new Random().nextInt(6) + 1;
    }

}

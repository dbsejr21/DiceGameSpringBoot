package com.queryholic.dicegame.spring.player;

import com.queryholic.dicegame.spring.dice.Dice;
import lombok.Getter;

@Getter
public class Player {
    protected int identifier;
    protected String name;
    protected int score = 0;
    protected Dice dice;

    public Player(String name) {
        this.name = name;
        this.dice = new Dice();
        this.identifier = PlayerStore.generatePlayerIdentifier();
    }

    /**
     * 플레이한다(주사위를 던진다).
     *
     * @return
     */
    public void play() {
        score += dice.roll();
    }


}

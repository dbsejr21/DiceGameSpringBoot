package com.queryholic.dicegame.spring;

import com.queryholic.dicegame.spring.judge.Judge;
import com.queryholic.dicegame.spring.player.Cheating;
import com.queryholic.dicegame.spring.player.FraudPlayer;
import com.queryholic.dicegame.spring.player.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        final Judge judge = context.getBean(Judge.class);
        final Cheating cheating = context.getBean(Cheating.class);

        judge.registerPlayer(new FraudPlayer(args[0], cheating));
        judge.registerPlayer(new Player(args[1]));

        judge.playGame(5);
    }
}

package DAO;

import java.util.concurrent.ThreadLocalRandom;


public class PowerBall {
    public PowerBall() {
        // no-arg constructor
    }

    public String generateNumbers() {


        String drawballs = "";
        // this for loop generates 5 random numbers between 1 and 69
        for (int i = 0; i <= 4; i++) {
            Integer draw = ThreadLocalRandom.current().nextInt(1, 69 + 1);
            if (draw.toString().equals(drawballs) == false) {
                drawballs += draw.toString() + " ";
            }

        } Integer BonusBall = ThreadLocalRandom.current().nextInt(1, 26 + 1);
        drawballs = drawballs + " " + BonusBall.toString();

        return drawballs; // returns a string of numbers that represents the powerball ticket.


    }

}



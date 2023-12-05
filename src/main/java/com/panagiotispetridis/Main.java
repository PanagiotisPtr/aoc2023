package com.panagiotispetridis;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "exactly 2 parameters expected: day{1,2..} (eg. day1) and --part{1,2} (eg. --part2)");
            return;
        }

        var partArgs = Arrays.copyOfRange(args, 1, args.length);
        switch (args[0]) {
            case "day1":
                com.panagiotispetridis.day1.App.main(partArgs);
                break;
            case "day2":
                com.panagiotispetridis.day2.App.main(partArgs);
                break;
            case "day3":
                com.panagiotispetridis.day3.App.main(partArgs);
                break;
            case "day4":
                com.panagiotispetridis.day4.App.main(partArgs);
                break;
            case "day5":
                com.panagiotispetridis.day5.App.main(partArgs);
                break;
            default:
                System.out.println("invalid day");
        }
    }
}


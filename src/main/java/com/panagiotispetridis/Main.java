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
            case "day6":
                com.panagiotispetridis.day6.App.main(partArgs);
                break;
            case "day7":
                com.panagiotispetridis.day7.App.main(partArgs);
                break;
            case "day8":
                com.panagiotispetridis.day8.App.main(partArgs);
                break;
            case "day9":
                com.panagiotispetridis.day9.App.main(partArgs);
                break;
            case "day10":
                com.panagiotispetridis.day10.App.main(partArgs);
                break;
            case "day11":
                com.panagiotispetridis.day11.App.main(partArgs);
                break;
            case "day12":
                com.panagiotispetridis.day12.App.main(partArgs);
                break;
            case "day13":
                com.panagiotispetridis.day13.App.main(partArgs);
                break;
            case "day14":
                com.panagiotispetridis.day14.App.main(partArgs);
                break;
            default:
                System.out.println("invalid day");
        }
    }
}


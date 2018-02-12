package us.mattgreen;

import java.util.Scanner;

public class Main {

    private static String line = "";
    private static String lineRatings = "";
    private final static FileInput cardAccts = new FileInput("movie_cards.csv");
    private final static FileInput cardPurchases = new FileInput("movie_purchases.csv");
    private final static FileInput cardRatings = new FileInput("movie_rating.csv");
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        String line;
        String[] fields;
        int[] nums = new int[2];
        double[] ratingNums = new double [2];
        boolean first = true;
        System.out.format("%8s  %-18s %6s %6s %3s\n", "Account", "Name", "Movies", "Points", "Avg");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(first, fields[0], nums);
            findScores(first, fields[0], ratingNums);
            first = false;
            System.out.format("00%6s  %-18s  %2d   %6d %.2f  \n", fields[0], fields[1], nums[0], nums[1], ratingNums[1]);
        }
    }

    public static void findPurchases(boolean first, String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        //String line = "";
        String[] fields;
        boolean done = false;

        if (first) {
            line = cardPurchases.fileReadLine();
        }
        while ((line != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            } else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += Integer.parseInt(fields[2]);
                line = cardPurchases.fileReadLine();
            }

        }
    }


    public static void findScores(boolean first, String acct, double[] ratingNums) {
        ratingNums[0] = 0;
        ratingNums[1] = 0;
        String[] fields;
        boolean done = false;
        if (first) {
            lineRatings = cardRatings.fileReadLine();
        }
        while ((line != null) && !(done)) {
            fields = lineRatings.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            } else if (fields[0].equals(acct)) {
                ratingNums[0]++;
                ratingNums[1] += Double.parseDouble(fields[1]);
                lineRatings = cardRatings.fileReadLine();
            }
        }
        if (ratingNums[0] != 0) {
            ratingNums[1] = ratingNums[1] / ratingNums[0];
        }
    }


}
package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(RomanNumbers.toInt("MDCCXCI"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
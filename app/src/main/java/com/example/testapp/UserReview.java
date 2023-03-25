package com.example.testapp;

public class UserReview {
    private String name;
    private double rate;
    private String review;

    @Override
    public String toString() {
        return "UserReview{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", review='" + review + '\'' +
                '}';
    }

    public UserReview(String name, double rate, String review) {
        this.name = name;
        this.rate = rate;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }
}

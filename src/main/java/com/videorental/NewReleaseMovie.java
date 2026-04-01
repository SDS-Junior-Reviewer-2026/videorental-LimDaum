package com.videorental;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title, Movie.NEW_RELEASE);
    }

    @Override
    double getChargeFor(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPointsFor(int daysRented) {
        return daysRented>1? 2:1;
    }
}

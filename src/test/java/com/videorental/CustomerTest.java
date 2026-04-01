package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private static final String CUSTOMER_NAME = "name_not_important";
    private static final String MOVIE_NAME = "movie_name";

    private static int daysRented;

    private static Customer customer;
    private static Movie regularMovie = new Movie(MOVIE_NAME, 0);;
    private static Movie newReleaseMovie = new Movie(MOVIE_NAME, 1);;
    private static Movie childrensMovie = new Movie(MOVIE_NAME, 2);


    @BeforeEach
    void beforeEach() {
        customer = new Customer(CUSTOMER_NAME);
    }

    @Test
    public void returnNewCustomer() {
        assertThat(customer).isNotNull();
    }

    @Test
    public void statementForNotRental()  {
        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter pointers"
        );
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        daysRented = 2;
        Rental rental = new Rental(regularMovie, daysRented);
        customer.addRental(rental);

        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t2.0(movie_name)\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan3Days() {
        daysRented = 3;
        Rental rental = new Rental(regularMovie, daysRented);
        customer.addRental(rental);

        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t3.5(movie_name)\n" +
                        "Amount owed is 3.5\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForNewReleaseMovieRentalForLessThan3Days() {
        daysRented = 1;
        Rental rental = new Rental(newReleaseMovie, daysRented);
        customer.addRental(rental);

        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t3.0(movie_name)\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForChildrensMovieRentalLessThan4Days() {
        daysRented = 3;
        Rental rental = new Rental(childrensMovie, daysRented);
        customer.addRental(rental);

        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t1.5(movie_name)\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForChildrensMovieRentalMoreThan4Days() {
        daysRented = 4;
        Rental rental = new Rental(childrensMovie, daysRented);
        customer.addRental(rental);

        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t3.0(movie_name)\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {
        daysRented = 3;
        Rental rental = new Rental(newReleaseMovie, daysRented);
        customer.addRental(rental);

        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t9.0(movie_name)\n" +
                        "Amount owed is 9.0\n" +
                        "You earned 2 frequent renter pointers"
        );
    }

    @Test
    public void statementForFewMovieRental() {
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(newReleaseMovie, 4));
        customer.addRental(new Rental(childrensMovie, 4));

        String statement = customer.statement();

        assertThat(statement).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t2.0(movie_name)\n" +
                        "\t12.0(movie_name)\n" +
                        "\t3.0(movie_name)\n" +
                        "Amount owed is 17.0\n" +
                        "You earned 4 frequent renter pointers"
        );
    }
}

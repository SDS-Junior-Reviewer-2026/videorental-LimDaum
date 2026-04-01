package com.videorental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private static Customer customer;
    private static final String CUSTOMER_NAME = "name_not_important";
    private static final String MOVIE_NAME = "movie_name";
    private static final int NEW_RELEASE = 1;

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
                "Rental Record for WHO\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter pointers"
        );
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        Movie movie = new Movie(MOVIE_NAME, Movie.NEW_RELEASE);

    }
}

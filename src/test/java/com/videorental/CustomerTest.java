package com.videorental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerTest {
    public static final String NAME = "name_not_important";
    public static final String TITLE = "movie_name";

    Customer customer;

    Movie regularMovie = new RegularMovie(TITLE);
    Movie newReleaseMovie = new NewReleaseMovie(TITLE);
    Movie childrensMovie = new ChildrensMovie(TITLE);

    public Rental createRental(Movie movieRented, int daysRented) {
        return new Rental(movieRented, daysRented);
    }

    @BeforeEach
    void beforeEach() {
        customer = new Customer(NAME);
    }

    @Test
    public void returnNewCustomer() {
        assertThat(customer).isNotNull();
    }

    @Test
    public void statementForNotRental()  {
        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter pointers"
        );
    }

    @Test
    public void statementForRegularMovieRentalForLessThan3Days() {
        customer.addRental(createRental(regularMovie, 2));

        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t2.0(movie_name)\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForRegularMovieRentalForMoreThan3Days() {
        customer.addRental(createRental(regularMovie, 3));

        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t3.5(movie_name)\n" +
                        "Amount owed is 3.5\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForNewReleaseMovieRentalForLessThan3Days() {
        customer.addRental(createRental(newReleaseMovie, 1));

        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t3.0(movie_name)\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForChildrensMovieRentalLessThan4Days() {
        customer.addRental(createRental(childrensMovie, 3));

        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t1.5(movie_name)\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForChildrensMovieRentalMoreThan4Days() {
        customer.addRental(createRental(childrensMovie, 4));

        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t3.0(movie_name)\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter pointers"
        );
    }

    @Test
    public void statementForNewReleaseMovieRentalMoreThan1Day() {
        customer.addRental(createRental(newReleaseMovie, 3));

        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t9.0(movie_name)\n" +
                        "Amount owed is 9.0\n" +
                        "You earned 2 frequent renter pointers"
        );
    }

    @Test
    public void statementForFewMovieRental() {
        customer.addRental(createRental(regularMovie, 1));
        customer.addRental(createRental(newReleaseMovie, 4));
        customer.addRental(createRental(childrensMovie, 4));

        assertThat(customer.statement()).isEqualTo(
                "Rental Record for name_not_important\n" +
                        "\t2.0(movie_name)\n" +
                        "\t12.0(movie_name)\n" +
                        "\t3.0(movie_name)\n" +
                        "Amount owed is 17.0\n" +
                        "You earned 4 frequent renter pointers"
        );
    }

    @Test
    public void statementForSetPriceCodeOfRegularMovie() {
        // 새로운 영화라 아직 장르 미정
        Movie newRegularMovie = new RegularMovie(TITLE);

        // 장르 Regular로 지정 -> priceCode 입력
        newRegularMovie.setPriceCode(0);

        // priceCode 정상 입력 여부 확인
        assertThat(0).isEqualTo(newRegularMovie.getPriceCode());
    }
}

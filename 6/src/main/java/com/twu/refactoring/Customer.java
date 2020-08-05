package com.twu.refactoring;

import java.util.ArrayList;

public class Customer {

  private final String name;
  private final ArrayList<Rental> rentalList = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental arg) {
    rentalList.add(arg);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    return this.getHeaderStr() + this.getMovieStr() + this.getFooterStr();
  }

  private String getMovieStr() {
    return this.rentalList.stream().map(this::getSingleMovieStr).reduce("", (a, b) -> a + b);
  }

  private String getSingleMovieStr(Rental rental) {
    return "\t" + rental.getMovie().getTitle() + "\t"
        + this.getRentalAmount(rental) + "\n";
  }

  private String getFooterStr() {
    return "Amount owed is " + this.getTotalAmount() + "\n" +
        "You earned " + this.getTotalFrequentRenterPoints() + " frequent renter points";
  }

  private int getTotalFrequentRenterPoints() {
    return this.rentalList.stream().map(this::getFrequentRenterPoint).reduce(0, Integer::sum);
  }

  private double getTotalAmount() {
    return this.rentalList.stream().mapToDouble(this::getRentalAmount).sum();
  }

  private String getHeaderStr() {
    return "Rental Record for " + getName() + "\n";
  }

  private double getRentalAmount(Rental rental) {
    double res = 0;

    switch (rental.getMovie().getPriceCode()) {
      case Movie.REGULAR:
        res += 2;
        if (rental.getDaysRented() > 2) {
          res += (rental.getDaysRented() - 2) * 1.5;
        }
        break;
      case Movie.NEW_RELEASE:
        res += rental.getDaysRented() * 3;
        break;
      case Movie.CHILDRENS:
        res += 1.5;
        if (rental.getDaysRented() > 3) {
          res += (rental.getDaysRented() - 3) * 1.5;
        }
        break;
      default:
        res += 0;
    }

    return res;
  }

  private int getFrequentRenterPoint(Rental rental) {
    return ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
        && rental.getDaysRented() > 1) ? 2 : 1;
  }

}

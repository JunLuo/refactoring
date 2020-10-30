package Chapter1.Refact;

import java.util.List;

public class Invoice {
  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  private String customer;
  private List<Performance> performances;

  public Invoice(String customer, List<Performance> performances){
    this.customer = customer;
    this.performances = performances;
  }

  public List<Performance> getPerformances() {
    return performances;
  }

  public void setPerformances(List<Performance> performances) {
    this.performances = performances;
  }

}

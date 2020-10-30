package Chapter1.Refact;

import java.lang.reflect.Array;
import java.util.List;

public class RenderParmData {
  private String customer;
  private List<Performance> performances;


  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public List<Performance> getPerformances() {
    return performances;
  }

  public void setPerformances(List<Performance> performances) {
    this.performances = performances;
  }


}

package Chapter1.Refact;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RenderData {
  private String customer;
  private final Map<Performance, RenderDataAmount> perfAmount = new HashMap<Performance,
      RenderDataAmount>();
  private int totalAmount;
  private int totalVolumeCredits;

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public Map<Performance, RenderDataAmount> getPerfAmount() {
    return perfAmount;
  }


  public int getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  public int getTotalVolumeCredits() {
    return totalVolumeCredits;
  }

  public void setTotalVolumeCredits(int totalVolumeCredits) {
    this.totalVolumeCredits = totalVolumeCredits;
  }
}

package Chapter1.Refact;

public class RenderDataAmount {
  public RenderDataAmount(String playName, int amount, int audience) {
    this.playName = playName;
    this.amount = amount;
    this.audience = audience;
  }
  private String playName;
  private int amount;
  private int audience;

  public String getPlayName() {
    return playName;
  }

  public void setPlayName(String playName) {
    this.playName = playName;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getAudience() {
    return audience;
  }

  public void setAudience(int audience) {
    this.audience = audience;
  }
}



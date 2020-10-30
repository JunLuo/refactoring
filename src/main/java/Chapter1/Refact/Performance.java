package Chapter1.Refact;


public class Performance{
  private String playID;

  public String getPlayID() {
    return playID;
  }

  public void setPlayID(String playID) {
    this.playID = playID;
  }

  public int getAudience() {
    return audience;
  }

  public void setAudience(int audience) {
    this.audience = audience;
  }

  private int audience;

  public Performance(String playID, int audience){
    this.playID = playID;
    this.audience = audience;
  }
}
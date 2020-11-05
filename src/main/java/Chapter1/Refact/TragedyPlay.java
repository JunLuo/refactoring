package Chapter1.Refact;

public class TragedyPlay extends Play {
  public TragedyPlay(String name, String type) {
    super(name, type);
  }

  public TragedyPlay(String name, playType type){
    super(name, type);
  }

  @Override
  public int amountFor(Performance perf){
    int thisAmount = 40000;
    if (perf.getAudience() > 30){
      thisAmount += 1000 * (perf.getAudience() - 30);
    }
    return thisAmount;
  }

  @Override
  public int volumeCreditFor(Performance perf){
    return Math.max(perf.getAudience() - 30, 0);
  }
}

package Chapter1.Refact;

public class ComedyPlay extends Play {
  public ComedyPlay(String name, String type) {
    super(name, type);
  }

  public ComedyPlay(String name, playType type){
    super(name, type);
  }

  @Override
  public int amountFor(Performance perf) {
    int thisAmount = 30000;
    if (perf.getAudience() > 20) {
      thisAmount += 10000 + 500 * (perf.getAudience() - 20);
    }
    thisAmount += 300 * perf.getAudience();
    return thisAmount;
  }

  @Override
  public int volumeCreditFor(Performance perf){
    int volumnCredit = Math.max(perf.getAudience() - 30, 0);
    volumnCredit += Math.floor(perf.getAudience() / 5);
    return volumnCredit;
  }
}

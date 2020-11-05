package Chapter1.Refact;

import sun.misc.Perf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Cinema {

  private ArrayList<Invoice> invoices;
  private HashMap<String,Play> plays;

  public Cinema(ArrayList<Invoice> invoices , HashMap<String,Play> plays){
    this.invoices = invoices;
    this.plays = plays;
  }

  private int amountFor(Performance perf){
    return playFor(perf).amountFor(perf);
  }

  private int volumeCreditFor(Performance perf){
    return playFor(perf).volumeCreditFor(perf);
  }

  private String usd(int amount){
    String result = String.format("$%.2f", (double)amount / 100);
    return result;
  }

  private Play playFor(Performance perf){
    return plays.get(perf.getPlayID());
  }

  private int totalVolumeCredits(Invoice invoice){
    int volumeCredits = 0;
    for (Performance perf: invoice.getPerformances()){
      volumeCredits += volumeCreditFor(perf);
    }
    return volumeCredits;
  }

  private int totalAmountFor(Invoice invoice){
    int totalAmount = 0;
    for (Performance perf : invoice.getPerformances()) {
      int thisAmount = amountFor(perf);
      totalAmount += thisAmount;
    }
    return totalAmount;
  }

  private String renderPlainText(RenderData data){
    String result = String.format("Statement for %s:\n", data.getCustomer());
    for (Performance perf : data.getPerfAmount().keySet()) {
      RenderDataAmount renderAmount = data.getPerfAmount().get(perf);
      result += String.format("  %s: %s (%d seats)\n", renderAmount.getPlayName(),
          usd(renderAmount.getAmount()), renderAmount.getAudience());
    }

    result += String.format("Amount owed is %s\n", usd(data.getTotalAmount()));
    result += String.format("You earned %d credits\n", data.getTotalVolumeCredits());
    return result;
  }

  private String HTMLPlainText(RenderData data){
    String result = String.format("<h1>Statement for %s</h1>\n",data.getCustomer());
    result += "<table>\n";
    result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>";
    for (Performance perf: data.getPerfAmount().keySet()){
      RenderDataAmount renderAmount = data.getPerfAmount().get(perf);
      result += String.format("<tr><td>%s</td><td>%d</td><td>%s</td></tr>\n",
          renderAmount.getPlayName(),renderAmount.getAudience(),usd(renderAmount.getAmount()));
    }
    result += "</table>\n";
    result += String.format("<p>Amount owed is <em>%s</em></p>\n", usd(data.getTotalAmount()));
    result += String.format("<p>You earned <em>%d</em> credits</p>\n",data.getTotalVolumeCredits());
    return result;
  }


  public void statement() {
    for (Invoice invoice : invoices) {
      RenderData data = new RenderData();
      data.setCustomer(invoice.getCustomer());
      for (Performance performance : invoice.getPerformances()){
        data.getPerfAmount().put(performance, new RenderDataAmount(playFor(performance).getName(),
            amountFor(performance),performance.getAudience()));
      }
      data.setTotalAmount(totalAmountFor(invoice));
      data.setTotalVolumeCredits(totalVolumeCredits(invoice));
      System.out.println(renderPlainText(data));
    }
  }

  public static void main(String argvs[]) throws IOException, NoSuchMethodException {
    HashMap<String, Play> plays = new JsonToObject<HashMap<String, Play>>(){}.getFromJson("plays.json");
    ArrayList<Invoice> invoices = new JsonToObject<ArrayList<Invoice>>(){}.getFromJson("invoices.json");
    for (String playType : plays.keySet()){
      switch (plays.get(playType).getType()){
        case tragedy:
          plays.put(playType, new TragedyPlay(plays.get(playType).getName(),
              plays.get(playType).getType()));
          break;
        case comedy:
          plays.put(playType, new ComedyPlay(plays.get(playType).getName(),
              plays.get(playType).getType()));
          break;
        default:
          break;
      }
    }
    new Cinema(invoices, plays).statement();

  }
}

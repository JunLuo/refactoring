package Chapter1.Refact;

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
    int thisAmount = 0;
    switch (playFor(perf).getType()){
      case tragedy:
        thisAmount = 40000;
        if (perf.getAudience() > 30){
          thisAmount += 1000 * (perf.getAudience() - 30);
        }
        break;
      case comedy:
        thisAmount = 30000;
        if (perf.getAudience() > 20){
          thisAmount += 10000 + 500 * (perf.getAudience() - 20);
        }
        thisAmount += 300 * perf.getAudience();
        break;
      default:
        throw new Error(String.format("Unknow type %s",playFor(perf).getType()));
    }
    return thisAmount;
  }

  private int volumeCreditFor(Performance perf){
    int volumeCredits = 0;
    //add volume credits
    volumeCredits += Math.max(perf.getAudience() - 30, 0);
    //add extra credit for every ten comedy attendees
    if (Play.playType.comedy == playFor(perf).getType()) {
      volumeCredits += Math.floor(perf.getAudience() / 5);
    }
    return volumeCredits;
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
      //print line for this order
      totalAmount += thisAmount;
    }
    return totalAmount;
  }

  private String renderPlainText(RenderData data){
    String result = String.format("Statement for %s:\n", data.getCustomer());
    for (Performance perf : data.getPerfAmount().keySet()) {
      RenderDataAmount renderAmount = data.getPerfAmount().get(perf);
      //print line for this order
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
      System.out.println(HTMLPlainText(data));
    }
  }

  public static void main(String argvs[]) throws IOException, NoSuchMethodException {
    HashMap<String, Play> plays = new JsonToObject<HashMap<String, Play>>(){}.getFromJson("plays.json");
    ArrayList<Invoice> invoices = new JsonToObject<ArrayList<Invoice>>(){}.getFromJson("invoices.json");
    new Cinema(invoices, plays).statement();
  }
}

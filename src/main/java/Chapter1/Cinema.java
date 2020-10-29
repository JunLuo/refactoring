package Chapter1;

import java.util.ArrayList;
import java.util.HashMap;

public class Cinema {
  public static void statement(ArrayList<HashMap<String, Object>> invoices,HashMap<String,
      HashMap<String, String>> plays){
    int totalAmount = 0;
    int volumeCredits = 0;
    for(HashMap<String, Object> customer : invoices) {
      String result = String.format("Statement for %s:\n", customer.get("customer"));
      for (HashMap<String, Object> perf : (ArrayList<HashMap<String, Object>>) customer.get(
          "performances")) {
        HashMap<String, String> play = plays.get(perf.get("PlayID"));
        double thisAmount = 0;

        switch (Plays.getType(play.get("type"))) {
          case tragedy:
            thisAmount = 40000;
            if ((Integer) perf.get("audience") > 30) {
              thisAmount += 1000 * ((Integer) perf.get("audience") - 30);
            }
            break;
          case comedy:
            thisAmount = 30000;
            if ((Integer) perf.get("audience") > 20) {
              thisAmount += 10000 + 500 * ((Integer) perf.get("audience") - 20);
            }
            thisAmount += 300 * (Integer) perf.get("audience");
            break;
          default:
            throw new Error(String.format("unknow type: %s", play.get("type")));
        }
        //add volume credits
        volumeCredits += Math.max((Integer) perf.get("audience") - 30, 0);
        //add extra credit for every ten comedy attendees
        if (Plays.type.comedy == Plays.getType(play.get("type"))) {
          volumeCredits += Math.floor((Integer) perf.get("audience") / 5);
        }

        //print line for this order
        result += String.format("  %s: $%.2f (%d seats)\n", play.get("name"),
            (double) thisAmount / 100,
            (Integer) perf.get("audience"));
        totalAmount += thisAmount;
      }
      result += String.format("Amount owed is %.2f\n", (double)totalAmount/100);
      result += String.format("You earned %d credits\n", volumeCredits);
      System.out.println(result);
    }
  }

  public static void main(String argvs[]) {
    statement(Invoices.invoices,Plays.plays);
  }
}

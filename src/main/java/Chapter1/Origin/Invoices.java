package Chapter1.Origin;

import java.util.ArrayList;
import java.util.HashMap;

public class Invoices {
  public static ArrayList<HashMap<String, Object>> invoices =
      new ArrayList<HashMap<String, Object>>();
  static{
    invoices.add(new HashMap<String, Object>(){{
      put("customer", "Bigco");
      put("performances", new ArrayList<HashMap<String, Object>>(){{
        add(new HashMap<String, Object>(){{
          put("PlayID","hamlet");
          put("audience",55);
        }});
        add(new HashMap<String, Object>(){{
          put("PlayID","as-like");
          put("audience",35);
        }});
        add(new HashMap<String, Object>(){{
          put("PlayID","othello");
          put("audience",40);
        }});
      }});
    }});
  }
}

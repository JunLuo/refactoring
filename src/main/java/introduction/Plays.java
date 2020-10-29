package introduction;

import java.util.HashMap;

public class Plays {
  public static HashMap<String, HashMap<String, String>> plays = new HashMap<String,
      HashMap<String, String>>();
  public enum type{
    tragedy, comedy;
  }
  public static type getType(String types){
    return type.valueOf(types.toLowerCase());
  }

  static{
    plays.put("hamlet",new HashMap<String, String>(){{
      put("name","Hamlet");
      put("type","tragedy");
    }});
    plays.put("as-like", new HashMap<String, String>(){{
      put("name","As You Like It");
      put("type","comedy");
    }});
    plays.put("othello", new HashMap<String, String>(){{
      put("name","Othello");
      put("type","tragedy");
    }});
  }
}

package Chapter1.Refact;


public class Play {
  public enum playType{
    tragedy, comedy
  }
  public playType getType(String typeName){
    return playType.valueOf(typeName);
  }

  private String name;
  private playType type;

  public Play(String name, String type){
    this.name = name;
    this.setType(type);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public playType getType() {
    return type;
  }

  public void setType(String type) {
    this.type = getType(type);
  }
}

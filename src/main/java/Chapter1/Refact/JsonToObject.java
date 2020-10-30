package Chapter1.Refact;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class JsonToObject<T> {

  private Type type;

  public JsonToObject(){
    this.type =
        ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public T getFromJson(String filename) throws IOException, NoSuchMethodException {
    File file = new File(Cinema.class.getClassLoader().getResource(filename).getPath());
    Gson gson  = new GsonBuilder().enableComplexMapKeySerialization().create();
    String content = FileUtils.readFileToString(file, "utf-8");
    T results = gson.fromJson(content,this.type);
    return results;
  };
}

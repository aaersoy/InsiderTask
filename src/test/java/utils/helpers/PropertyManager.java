package utils.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {
  private static Map<String, Properties> map = new HashMap<>();
  static Map<String, File> files = new HashMap<>();
  private static boolean isPropertyManagerCreated = false;

  public PropertyManager() {
    if(!isPropertyManagerCreated){
      files.put("env", new File(ClassLoader.getSystemResource("env.properties").getPath()));
      files.put("test_data", new File(ClassLoader.getSystemResource("test_data.properties").getPath()));

      for (File f : files.values()) {
        Properties props = new Properties();
        try {
          props.load(new FileReader(f));
          map.put(f.getName(), props);
        } catch (IOException ex) {
        }
      }
      isPropertyManagerCreated = true;
    }
  }

  public String getProperty(String file, String key) {
    Properties props = map.get(file);

    if (props != null) {
      return props.getProperty(key);
    }
    return null;
  }
}

package src.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static final String PROP_FILE = "dbconfig.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(PROP_FILE)) {
            if (inputStream == null) {
                throw new RuntimeException("Unable to find " + PROP_FILE);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load database properties file.", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

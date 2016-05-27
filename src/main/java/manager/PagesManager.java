package manager;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by EgorVeremeychik on 01.05.2016.
 */
public class PagesManager {
    private static final Logger LOG = Logger.getLogger(PagesManager.class);
    private static final String PATH = "properties/pages.properties";
    private static Properties props;

    public static String getPage(PagesEnum key){
        if(props == null){
            init();
        }
        return props.getProperty(key.getValue());
    }

    public static void init(){
        try(InputStream inputStream = PagesManager.class.getClassLoader().getResourceAsStream(PATH)){
            props = new Properties();
            props.load(inputStream);
        }catch (IOException e){
            LOG.error("ERROR!!!!!");
        }
    }
}

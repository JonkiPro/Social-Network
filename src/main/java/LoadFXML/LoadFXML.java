package LoadFXML;

import javafx.fxml.FXMLLoader;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Jonatan on 2017-01-03.
 */
public class LoadFXML {
    public static FXMLLoader loadFXML(Class clas, String linkFXML, String bundle, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle, locale);

        return new FXMLLoader(clas.getResource(linkFXML), resourceBundle);
    }

    public static FXMLLoader loadFXML(Class clas, String linkFXML) {

        return new FXMLLoader(clas.getResource(linkFXML));
    }
}

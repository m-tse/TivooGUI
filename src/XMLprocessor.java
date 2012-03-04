import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class XMLprocessor {
    

    public File chooseXML() throws NoSuchAlgorithmException, IOException, ParseException{
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML Files", "xml");
            fc.setFileFilter(filter);

        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
            

    }
        return null;
    }
}

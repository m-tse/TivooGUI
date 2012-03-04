import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.Publisher;
import calendar.CalendarFilter;
import calendar.XMLCal;


public class GUISystem {
    public static final String filter = "LLC";
    public ArrayList<File> xmlFileList;
    public XMLCal calendar;
    
    public GUISystem(){
        xmlFileList = new ArrayList<File>();
    JFrame frame = new JFrame("TivooGUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    JButton chooseXML = new JButton("Add XML");
    chooseXML.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            XMLprocessor x = new XMLprocessor();
            try {
                xmlFileList.add(x.chooseXML());
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    });
    frame.getContentPane().add(chooseXML, BorderLayout.WEST);
    
    JButton generateHTML = new JButton("Make HTML");
    generateHTML.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            try {
                makeHTML();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    });
    frame.getContentPane().add(generateHTML, BorderLayout.EAST);
    
    
    
    
    
    JButton viewHTML = new JButton("View HTML");
    viewHTML.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            File htmlfile = new File("resources/summary.html");
            try {
                TivooDisplayHTML foo = new TivooDisplayHTML(htmlfile.toURI().toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    });
    frame.getContentPane().add(viewHTML, BorderLayout.SOUTH);
    
    JButton setFilter = new JButton("set Filter");
    setFilter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            ChooseFilters a = new ChooseFilters(xmlFileList);
            calendar = a.getCal();

        }
    });
    frame.getContentPane().add(setFilter, BorderLayout.CENTER);
    
    frame.pack();
    frame.setVisible(true);

    
    
    
    
    
    
    
    
}
    
    public void makeHTML() throws ParseException, NoSuchAlgorithmException, IOException{
//        TivooSystem s = new TivooSystem();
//        String[] fileStrings = new String[xmlFileList.size()];
//        for(int i = 0 ; i<xmlFileList.size();i++) fileStrings[i]=xmlFileList.get(i).toString();
//        XMLCal calendar = s.parse(fileStrings);
//        CalendarFilter filter = new CalendarFilter(calendar);
        
        
        
        Publisher publisher = new Publisher(calendar);
        publisher.publish();
        
    }
}

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;

import calendar.KeywordFilter;
import calendar.TimeFilter;
import calendar.XMLCal;


public class ChooseFilters {
    public XMLCal cal;
    public final static Calendar before = new GregorianCalendar(2011, 8, 12);
    public final static Calendar after = new GregorianCalendar(2011, 10, 17);
    TextField tf1;
    ArrayList<String> filterArrayList;
    JFrame frame;
    JFrame tfieldFrame;
    public ChooseFilters(ArrayList<File> xmlFileList){
        TivooSystem s = new TivooSystem();
        String[] fileStrings = new String[xmlFileList.size()];
        for(int i = 0 ; i<xmlFileList.size();i++) fileStrings[i]=xmlFileList.get(i).toString();
        cal = s.parse(fileStrings);
    frame = new JFrame("Choose filter(s)");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    
    JButton keywordFilter = new JButton("by keyword");
    keywordFilter.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            filterArrayList = new ArrayList<String>();
            tfieldFrame = new JFrame("Enter your keyword");
            tfieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tf1 = new TextField();
  
            tfieldFrame.getContentPane().add(tf1, BorderLayout.NORTH);
            
            JButton setKeyword = new JButton("Store");
            setKeyword.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0){
                    String text = tf1.getText();
                    filterArrayList.add(text);
                }
                
            });
            tfieldFrame.getContentPane().add(setKeyword, BorderLayout.WEST);
            
            JButton filterNow = new JButton("apply filter");
            filterNow.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0){

                    KeywordFilter filter = new KeywordFilter(cal);
                    String[] filters = new String[filterArrayList.size()];
                    filter.filterTitle(filterArrayList.toArray(filters));
                    tfieldFrame.hide();
                    frame.hide();
                }
                
            });
            tfieldFrame.getContentPane().add(filterNow, BorderLayout.EAST);
            
            
            
            tfieldFrame.pack();
            tfieldFrame.setVisible(true);
            
            
            
            
            

//            KeywordFilter filter = new KeywordFilter(cal);
//            String[] filters = new String[filterArrayList.size()];
//            filter.filterTitle(filterArrayList.toArray(filters));
            
        }
        
        
    });
    frame.getContentPane().add(keywordFilter, BorderLayout.CENTER);
    
    
    JButton doNothingFilter = new JButton("No Filter");
    doNothingFilter.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            //do nothing
            frame.hide();
            
        }
        
        
    });
    frame.getContentPane().add(doNothingFilter, BorderLayout.WEST);
    
    JButton timeFilter = new JButton("Time Filter");
    timeFilter.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            TimeFilter timefilter = new TimeFilter(cal);
            try {
                timefilter.filterTime(before, after);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            frame.hide();
            
        }
        
        
    });
    frame.getContentPane().add(timeFilter, BorderLayout.EAST);
    
    
    frame.pack();
    frame.setVisible(true);
    
    }

    public XMLCal getCal() {
        return cal;
    }

}

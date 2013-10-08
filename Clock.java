import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Clock{
	javax.swing.Timer m_t;
	javax.swing.Timer timer;
	String time = "";
	JPanel panel;
	JTextField employeeId;
	static long now = 0;
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	DateFormat formatterDuration = new SimpleDateFormat("HH ':' mm ':' ss");
    JFrame frameV;
    JPanel panelV;
    String[] returned;
    JFrame frame;
	
	public class graphics extends JComponent {
		
        graphics() {
            setPreferredSize(new Dimension(300, 250));
        }

        @Override
        public void paintComponent(Graphics g) {
		super.paintComponents(g);
		 Font f = new Font("Futura", Font.BOLD, 20);
		 g.setFont(f);
		g.drawString(time, 40, 30);
		}
	}
	public class graphicsV extends JComponent {
		
        graphicsV() {
            setPreferredSize(new Dimension(250, 200));
        }

        @Override
        public void paintComponent(Graphics g) {
		super.paintComponents(g);
		 Font f = new Font("Futura", Font.BOLD, 20);
		 g.setFont(f);
		 if(returned.length == 1){
			 g.drawString(returned[0], 3, 100);
		 }
		 else{
			 g.drawString(returned[0], 3, 100);
			 long timeDuration = Long.parseLong(returned[1]);
			 formatterDuration.setTimeZone(TimeZone.getTimeZone("GMT+0"));
			 String timeDurations = "" + formatterDuration.format(timeDuration);
			 g.drawString(timeDurations, 50, 130);
			 
		 }
		}
	}
	public void test() {
		panel.add(new JLabel("fdss"));
	}
	private class clockTick implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            now = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(now);
            time = "" + formatter.format(calendar.getTime());
            test();
            panel.repaint();
		
        }
	}
	public boolean checkString(){
		String contents = employeeId.getText();
		int length = contents.length();
		if(length == 3){
			insertToDB inserts = new insertToDB(now,contents);
			inserts.initateConnection();
		    returned = inserts.insert(now,contents);
		    JLabel test = new JLabel("fsaf");
		    panel.add(test);
		    frameV = new JFrame("Verfication");
		    panelV = new JPanel();
			panelV.add(new graphicsV());
			JTextField field = new JTextField(10);
			frameV.add(field);
			frameV.add(panelV);
			frameV.setUndecorated(true);
			frameV.setResizable(false);
			frameV.setSize(250,200);
			frameV.setVisible(true);
			Timer timer = new Timer(1500, new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            frameV.dispose();
		        }
		    });
		    timer.setRepeats(false);
		    timer.start();
			inserts.closeConnection();
			return true;
		}
		else{
			return false;
		}
	}
	 public void createGUI(){
	  frame = new JFrame("Clock");
	    employeeId = new JTextField("",10);
	    Hint IpHint  = new Hint("Scan Card", employeeId);
		panel = new JPanel();
		DocumentListener listenerEIN = new DocumentListener() {
			public void changedUpdate(DocumentEvent documentEvent) {
				
			 }
			 public void insertUpdate(DocumentEvent documentEvent) {
				boolean tf = false;
				  tf = checkString();
				 if(tf){
					 EventQueue.invokeLater(new Runnable() {
				            @Override
				            public void run() {
				                employeeId.setText("");
				            }
				        });
				 }
			 }
			public void removeUpdate(DocumentEvent arg0) {
			}
		};
				
		employeeId.getDocument().addDocumentListener(listenerEIN);
		
		frame.setSize(250,200);
		panel.add(employeeId);
		panel.add(new graphics());
		panel.add(new graphicsV());
		frame.add(panel);
		panel.repaint();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);	
		m_t = new javax.swing.Timer(1000, new clockTick());
        m_t.start();
	}
	public static void startClock(){
		now = System.currentTimeMillis();
	}
	 
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	startClock();
                Clock GUI = new Clock();
                GUI.createGUI();
            }
        });
		
	}
}

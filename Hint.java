/**
 * Class that creates the text hints for the JTexfields
 * Adapted from: http://tips4java.wordpress.com/2009/11/29/text-prompt/
 */

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class Hint extends JLabel implements DocumentListener {
	
	
	private JTextComponent component;
	private Document document;
	public Hint(String text, JTextComponent component) {
		this.component = component;
		document = component.getDocument();
		setText(text);
		//sets font for text, takes in an integer value. (Font.BOLD + Font.Italics = 3
		setFont(getFont().deriveFont(3));
		//sets the color (0,0,0) = black (0,0,0,0) = transparent black
		setForeground(new Color(0,0,0,128));
		//creates the constraints for the border layout to place JLabel in 
		setBorder(new EmptyBorder(component.getInsets()));
		//aligns the JLabel
		setHorizontalAlignment(JLabel.CENTER);
		//adds document listener to the JTextField
		document.addDocumentListener(this);
		//sets the layout inside the JTextField
		component.setLayout(new BorderLayout());
		//overlays JLabel over the JTextField
		component.add(this);
		//calls the checkForPrompt method which determines if there is text in the JTextField and whether to overlay label
		checkForPrompt();
	}

	public void checkForPrompt() {
		if(document.getLength() > 0) {
			setVisible(false);
			return;
		}
		else {
			setVisible(true);
		}
	}

	public void insertUpdate(DocumentEvent e) {
		checkForPrompt();
	}
	public void removeUpdate(DocumentEvent e) {
		checkForPrompt();
	}
	public void changedUpdate(DocumentEvent e) {
	}
}
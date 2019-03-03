package Frames;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import lab01.Centrala;

public class CentralaFrame extends JDialog implements Serializable {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton exitButton;
	private Centrala centrala;
	
	public CentralaFrame() throws RemoteException, NotBoundException {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(exitButton);
		
		centrala = new Centrala();
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitButton();
			}
		});
		
		 setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		 addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	                exitButton();
	            }
	        });
		 
		 contentPane.registerKeyboardAction(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
	                exitButton();
	            }
		 }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
    }

	
	
	private void exitButton() {
		System.exit(0);
		dispose();
	}

	 public static void main(String[] args) throws RemoteException, NotBoundException {
		 
		 CentralaFrame centralaMain = new CentralaFrame();
		 centralaMain.pack();
		 centralaMain.setVisible(true);
		 System.exit(0);
	 }
}

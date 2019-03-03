package Frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interfaces.IBramka;
import Interfaces.ICentrala;
import lab01.Bramka;

public class BramkaFrame extends JDialog implements Serializable{

	
	    private ICentrala nCentrala;
	    private Registry registry;
	    
	 private Bramka bramka;
	 private ICentrala oCentrala;
	 
	 private JPanel contentPane;
	 private JButton buttonStart;
	 private JButton buttonStop;
	 private JButton buttonWe;
	 private JButton buttonWy;
	 private JLabel weLabel;
	 private JLabel wyLabel;
	 private static final long serialVersionUID = 1L;

	 public BramkaFrame() throws java.rmi.AlreadyBoundException {
		 
		 try {
			 bramka = new Bramka();
			 
			 IBramka nBramka = (IBramka) UnicastRemoteObject.exportObject((Remote) this, 29);
	         registry = LocateRegistry.getRegistry("localhost",Registry.REGISTRY_PORT);

	            nCentrala = (ICentrala) registry.lookup("Centrala");

	            registry.bind("Bramka",nBramka);
	        } catch (AlreadyBoundException | IOException | NotBoundException e) {
	            e.printStackTrace();
	        }
		 
		 checkButtonsStatus();
		 setContentPane(contentPane);
	     setModal(true);
	     getRootPane().setDefaultButton(buttonStart);

	     
	     buttonStop.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    oCentrala.wyrejestrujBramke(bramka.getNumer());
	                    checkButtonsStatus();
	                } catch (RemoteException e0) {
	                    e0.printStackTrace();
	                }
	            }
	        });
	     
	     buttonWe.addActionListener(new ActionListener() {
	    	 @Override
	    	 public void actionPerformed(ActionEvent e) {
	    		 bramka.addWe();
	    		 weLabel.setText("Wejscia: " + bramka.getWe());
				    checkButtonsStatus();
	    	 }
	     });
	     
	     buttonWy.addActionListener(new ActionListener() {
	    	 @Override
	    	 public void actionPerformed(ActionEvent e) {
	    		 
	    			bramka.addWy();
	    			wyLabel.setText("Wyjscia: " + bramka.getWy());
	    		 
	    	 }
	     });
	     
	     buttonStart.addActionListener(new ActionListener() {
	    	 
	    	 @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    oCentrala.zarejestrujBramke(bramka.getNumer());
	                    checkButtonsStatus();
	                } catch (RemoteException e3) {
	                    e3.printStackTrace();
	                }
	            }
	     });
	 }
	 
	
	 public static void main(String[] args) throws java.rmi.AlreadyBoundException {
	     
		 
		 BramkaFrame dialog = new BramkaFrame();
	        dialog.pack();
	        dialog.setVisible(true);
	        System.exit(0);
	    }
	 
	 private void checkButtonsStatus(){
	        if (bramka.isRunning()){
	            buttonWy.setEnabled(true);
	            buttonWe.setEnabled(true);
	            buttonStart.setEnabled(false);
	            buttonStop.setEnabled(true);
	        }else{
	            buttonWy.setEnabled(false);
	            buttonWe.setEnabled(false);
	            buttonStart.setEnabled(true);
	            buttonStop.setEnabled(false);
	        }
	        bramka.setRunningParameter(!bramka.isRunning());
	    }

	 
	 
}

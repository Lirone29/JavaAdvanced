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
import javax.swing.JPanel;
import javax.swing.JTable;

import Interfaces.IBramka;
import Interfaces.ICentrala;
import Interfaces.IMonitor;
import lab01.Bramka;
import lab01.Monitor;


public class MonitorFrame extends JDialog implements Serializable {

	 private ICentrala nCentrala;
	    private Registry registry;
	    
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton exitButton;
	private JTable bramkiTable;
	private Monitor monitor;
	

	public MonitorFrame() throws RemoteException, NotBoundException, java.rmi.AlreadyBoundException {
		
		 monitor = new Monitor();
		 
		 try {
			
			 
			 IMonitor nMonitor = (IMonitor) UnicastRemoteObject.exportObject((Remote) this, 29);
	         registry = LocateRegistry.getRegistry("localhost",Registry.REGISTRY_PORT);

	            nCentrala = (ICentrala) registry.lookup("Centrala");

	            registry.bind("Monitor",nMonitor);
	            
	        } catch (AlreadyBoundException | IOException | NotBoundException e) {
	            e.printStackTrace();
	        } 
		 
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(exitButton);
		
	}
	
	 public static void main(String[] args) throws RemoteException, NotBoundException, java.rmi.AlreadyBoundException {
	        MonitorFrame mFrame = new MonitorFrame();
	        mFrame.pack();
	        mFrame.setVisible(true);
	        System.exit(0);
	    }
	 
}

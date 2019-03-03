package lab01;

import java.awt.EventQueue;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;

import javax.swing.JLabel;

import Interfaces.IBramka;
import Interfaces.IMonitor;

public class Monitor extends UnicastRemoteObject implements IMonitor, Serializable {

	Date pocz;
	Date kon;
	private int indexMonitor;
	private String host= "Monitor";
	private int port = 29;
	 
	private static final long serialVersionUID = 1L;

	private Centrala oCentrala;
	private Bramka oBramka;
	private Registry registry;
	
	public Monitor() throws RemoteException, NotBoundException {
		registry = LocateRegistry.getRegistry(host, port);
		oCentrala = (Centrala) registry.lookup("Centrala");
		setIndexMonitor(1);
		oBramka = new Bramka();
	}
	
	@Override
	public void koniecznaAktualizacja() throws RemoteException {
		oBramka.getStatystyka(pocz, kon);

	}

	public int getIndexMonitor() {
		return indexMonitor;
	}

	public void setIndexMonitor(int iMonitor) {
		indexMonitor = iMonitor;
	}

}

package lab01;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import Interfaces.IBramka;

public class Bramka extends UnicastRemoteObject implements IBramka, Serializable {

	private String host= "Bramka";
	private int port = 29;
	private int liczbaWe;
	private int liczbaWy; 
	
	private int index;
	
	private int startTime;
	private Date endTime;
	
	private boolean runningParameter;
	private Centrala oCentrala;
	private Registry registry;
	
	public Bramka() throws RemoteException, NotBoundException {
		
		
		
		//registry = LocateRegistry.getRegistry(host, port);
		//oCentrala = (Centrala) registry.lookup("Centrala");
		//registry.bind("Bramka",nBramka);
		liczbaWe =0;
		liczbaWy = 0;
		startTime = 1;
		index = oCentrala.zarejestrujBramke(this);
		runningParameter = true;
	}

	private static final long serialVersionUID = 1L;

	public boolean isRunning() {
		return runningParameter;
	}
	
	public void setRunningParameter(boolean a) {
		runningParameter =a;
	}
	
	
	@Override
	public int[] getStatystyka(Date pocz, Date kon) throws RemoteException {
		int[] bramkStatystyka = new int[3];
		//if(startTime.after(pocz) && startTime.before(kon)) bramkStatystyka[0] = index;
		//bramkStatystyka[1] = endTime;
		
		return bramkStatystyka;
	}

	@Override
	public boolean zamknijBramke() throws RemoteException {
		oCentrala.wyrejestrujBramke(index);
		return true;
	}

	@Override
	public int getNumer() throws RemoteException {
		
		return index;
	}
	
	public void addWe() {
		liczbaWe++;
	}
	
	public void addWy() {
		liczbaWy++;
	}

	public int getWe() {
		return liczbaWe;
	}
	
	public int getWy() {
		return liczbaWy;
	}
}

package lab01;

import java.awt.EventQueue;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Interfaces.ICentrala;

public class Centrala extends UnicastRemoteObject implements ICentrala, Serializable {

	private static final long serialVersionUID = 1L;
	private static Monitor monitor;
	private static Bramka bramka;
	
	private ArrayList<Object> bramkiList = new ArrayList<Object>(); 
	//private ArrayList<Object> monitorList = new ArrayList<Object>(); 
	
	
	
	
	public Centrala() throws RemoteException, NotBoundException {
		//monitor = new Monitor();
		//bramka = new Bramka();
		
		//Centrala oCentrala = new Centrala();
		//Registry registry = LocateRegistry.createRegistry(29);
		//registry.rebind("Centrala", this);
		//super();
	}
	
	public static void initializeCentrala() throws RemoteException, NotBoundException {
		monitor = new Monitor();
		bramka = new Bramka();
	}

	
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					Centrala oCentrala = new Centrala();
					Registry registry = LocateRegistry.createRegistry(29);
					registry.rebind("Centrala", oCentrala);
					initializeCentrala();
					//System.out.println("Central enabled!");
					
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//*/
	
	@Override
	public int zarejestrujBramke(Object bramka) throws RemoteException {
		bramkiList.add(bramka);
		monitor.koniecznaAktualizacja();
		return (bramkiList.size());
		//return 1;
	}

	@Override
	public boolean wyrejestrujBramke(int nrBramki) throws RemoteException {
		bramkiList.remove(nrBramki);
		monitor.koniecznaAktualizacja();
		if(bramkiList.get(nrBramki)==null) return true;
		else return false;
	}

	@Override
	public ArrayList<Object> getZarejestrowaneBramki() throws RemoteException {
		return bramkiList;
	}

	@Override
	public void zarejestrujMonitor(Object o) throws RemoteException {
		monitor = (Monitor) o;

	}

	@Override
	public void wyrejestrujMonitor() throws RemoteException {
		monitor = null;

	}

	@Override
	public Object getMonitor() throws RemoteException {
		
		return monitor;
	}

}

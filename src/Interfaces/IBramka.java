package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IBramka extends Remote {

	// do pobierania informacji o statystyce � do wykorzystania przez Monitor
		// statystyka to tablica z dwiema warto�ciami: liczba wej��, liczba wyj��
		public int[] getStatystyka(Date pocz, Date kon) throws RemoteException;

		// do zdalnego zatrzymywania bramek
		public boolean zamknijBramke() throws RemoteException;

		// do pobrania numeru bramki
		public int getNumer() throws RemoteException;
}

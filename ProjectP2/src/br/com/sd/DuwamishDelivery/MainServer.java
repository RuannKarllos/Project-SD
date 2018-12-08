package br.com.sd.DuwamishDelivery;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JOptionPane;

public class MainServer {
	
	public static void main(String[] args) {
	
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost/dd", new DuwamishDelivery());

			JOptionPane.showMessageDialog(null, "Servidor Online");
		} catch (RemoteException | MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "(Erro no registro da aplicação) " + e.toString());
		}
	}
}

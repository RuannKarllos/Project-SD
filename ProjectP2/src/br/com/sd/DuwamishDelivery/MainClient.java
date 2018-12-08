package br.com.sd.DuwamishDelivery;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class MainClient {
	
	public static void main(String[] args) {
		
		try {
			
			IDuwamishDelivery duwamish = (IDuwamishDelivery) Naming.lookup("rmi://localhost/dd"); 
			
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "(Erro no registro da aplicação) " + e.toString());
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "(Erro no link da aplicação) " + e.toString());
		} catch (NotBoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}

package br.com.sd.AdventureWorks;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class MainClient {
	public static void main(String[] args) {

		try {
			
			IAdventureWorks adventureWorks = (IAdventureWorks) Naming.lookup("rmi://localhost/aw");
						
			String id = JOptionPane.showInputDialog("Informe o id que deseja abrir o processo: ");
			
			if (id != null) {
				if (adventureWorks.listRequest().containsKey(Integer.parseInt(id))) {
					String phone = JOptionPane.showInputDialog("Para finalizar a requisição, digite seu número de telefone: ");
					
					if (phone != null) {
						try {
							adventureWorks.sendRequest(Integer.parseInt(id), phone);
							adventureWorks.listRequest().remove(id);
						} catch (RemoteException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Esse id não existe, tente outro novamente!");
				}
			}
			
			JOptionPane.showMessageDialog(null, adventureWorks.listRequest().get(0) + " \n " + adventureWorks.listRequest().get(1));
			
			
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "(Erro no registro da aplicação) " + e.toString());
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "(Erro no link da aplicação)" + e.toString());
		} catch (NotBoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}

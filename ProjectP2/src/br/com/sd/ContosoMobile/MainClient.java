package br.com.sd.ContosoMobile;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class MainClient {
	public static void main(String[] args) {
		try {

			IContosoMobile notify = (IContosoMobile) Naming.lookup("rmi://localhost/cm");
			String phone = JOptionPane
					.showInputDialog("Digite seu n�mero! \nA requisi��o ser� enviada para este n�mero.");

			notify.setStartRequisition(phone);

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "(Erro no registro da aplica��o) " + e.toString());
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "(Erro no link da aplica��o) " + e.toString());
			e.printStackTrace();
		} catch (NotBoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}

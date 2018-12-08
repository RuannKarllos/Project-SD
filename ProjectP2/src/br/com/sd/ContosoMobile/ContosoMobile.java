package br.com.sd.ContosoMobile;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import br.com.facilitamovel.bean.SmsSimples;
import br.com.facilitamovel.service.SendMessage;

public class ContosoMobile extends UnicastRemoteObject implements IContosoMobile {

	private static final long serialVersionUID = 1L;

	protected ContosoMobile() throws RemoteException {
		super();

	}

	@Override
	public void setStartRequisition(String phone) throws RemoteException {
		if (isPhone(phone)) {
			throw new Error("Digite o número do celular! \nEx.: (DD) 9.9999-9999");
		} else {
			SmsSimples sms = getSms("Pedido efetuado com sucesso!", phone);
			try {
				SendMessage.simpleSend(sms);
				System.out.printf("Mensagem enviada para: %s\n", phone);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao enviar a mensagem" + e.toString());
			}
		}

	}

	@Override
	public void setFinishRequisition(String phone) throws RemoteException {
		if (!isPhone(phone)) {
			throw new Error("Digite o número do celular! \nEx.: (DD) 9.9999-9999");
		} else {
			SmsSimples sms = getSms("Seu pedido está perto!", phone);

			try {
				SendMessage.simpleSend(sms);
				System.out.printf("Mensagem enviada para: %s\n", phone);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "(Erro ao enviar a mensagem)" + e.toString());
			}
		}

	}

	private SmsSimples getSms(String msg, String phone) {
		SmsSimples sms = new SmsSimples();
		sms.setUser("test");
		sms.setPassword("test123");
		sms.setDestinatario(phone);
		sms.setMessage(msg);

		return sms;
	}

	private boolean isPhone(String phone) {
		return phone.matches("^[0-9]*$") && phone.length() == 11;
	}

}

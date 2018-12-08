package br.com.sd.Fabrikam;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import br.com.sd.ContosoMobile.IContosoMobile;
import br.com.sd.DuwamishDelivery.IDuwamishDelivery;

public class Fabrikam extends UnicastRemoteObject implements IFabrikam {

	private static final long serialVersionUID = 1L;
	private IContosoMobile contosoMobile;
	private IDuwamishDelivery duwamishDelivery;
	ArrayList<Integer> nearDeliveryId;
	
	public Fabrikam() throws RemoteException {
		nearDeliveryId = new ArrayList<>();
		initServers();
	}

	@Override
	public String openProcess(Integer id, String phone) throws RemoteException {
		contosoMobile.setStartRequisition(phone);
		return duwamishDelivery.addProcess(id, phone);
	}

	@Override
	public void startCheckAllStatus() throws RemoteException {
		Timer timer = new Timer();
		int sec = 60 * 1000;
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try {
					doCheck();
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
				
			}
		}, 0, sec);
		
	}

	@Override
	public HashMap<Integer, ArrayList> listProcess() throws RemoteException {
		return duwamishDelivery.list();
	}
	
	private void doCheck() throws RemoteException {
		HashMap<Integer, ArrayList> list = listProcess();
		
		if (!list.isEmpty()) {
			String phone;
			Calendar date;
			int id;
			
			for (Map.Entry<Integer, ArrayList> current : list.entrySet()) {
				
				id = current.getKey();
				phone = (String) current.getValue().get(1);
				date = (Calendar) current.getValue().get(0);
				
				if (isDelivery(date)) {
					
					duwamishDelivery.removeProcess(id);
					if (!nearDeliveryId.isEmpty()) {
						nearDeliveryId.remove(new Integer(id));
					}
					
					System.out.printf("Processo Finalizado: \nID: %d \nFone %s\n", id, phone);
					
				} else if (isNearDelivery(date) && !nearDeliveryId.contains(id)) {
					
					contosoMobile.setFinishRequisition(phone);
					nearDeliveryId.add(id);
					System.out.printf("Mensagem final enviada para Fone: %s / ID: %d\n", phone, id);
				}
				
			}
		} else {
			System.out.println("Lista vazia...");
		}
	}
	
	private boolean isNearDelivery(Calendar date) {
		
		int restMin = getDifereceBetweenDates(date);
		
		return Math.abs(restMin) <= 3;
	}

	private int getDifereceBetweenDates(Calendar date) {
		int finalHour = date.get(Calendar.HOUR_OF_DAY);
		int finalMin = date.get(Calendar.MINUTE);
		
		Calendar currentDate = Calendar.getInstance();
		int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
		int currentMin = currentDate.get(Calendar.MINUTE);
		
		int restMint = ((finalHour * 60) + finalMin) - ((currentHour * 60) + currentMin);
				
		return restMint;
	}
	
	private boolean isDelivery(Calendar date) {
		int restMin = getDifereceBetweenDates(date);
		return (restMin <= 0);
	}
	
	private void initServers() throws RemoteException {
		try {
			contosoMobile = (IContosoMobile) Naming.lookup("rmi://localhost/cm");
			duwamishDelivery = (IDuwamishDelivery) Naming.lookup("rmi://localhost/dd");
		} catch (MalformedURLException | NotBoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
}

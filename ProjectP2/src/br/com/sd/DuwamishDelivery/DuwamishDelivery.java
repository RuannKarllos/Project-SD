package br.com.sd.DuwamishDelivery;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class DuwamishDelivery extends UnicastRemoteObject implements IDuwamishDelivery {

	private static final long serialVersionUID = 1L;
	public static HashMap<Integer, ArrayList> listProcess;

	protected DuwamishDelivery() throws RemoteException {
		listProcess = new HashMap<>();

	}

	@Override
	public String addProcess(Integer id, String phone) throws RemoteException {
		String result;

		if (listProcess.containsKey(id)) {
			result = ("ID já existe! Tente novamente com outro!");
		} else {
			ArrayList data = new ArrayList<>();
			Calendar date = getCalendar();
			data.add(date);
			data.add(phone);
			listProcess.put(id, data);
			System.out.printf("Novo processo criado --> DADOS: \nID: %d \nFone: %s \nFim do Processo: %d:%d \n", id,
					phone, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE));

			result = "Processo adicionado com sucesso!";
		}

		return result;
	}

	@Override
	public String removeProcess(Integer id) throws RemoteException {
		String result = "";

		if (listProcess.containsKey(id)) {
			listProcess.remove(id);

			result = "Processo entregue com sucesso!";

		} else {
			result = ("Processo não existe, verifique o ID!");
		}

		return result;
	}

	@Override
	public HashMap<Integer, ArrayList> list() throws RemoteException {
		return listProcess;
	}

	private Calendar getCalendar() {
		Random random = new Random();
		Calendar date = Calendar.getInstance();

		int randomNum = 5 + (int) (Math.random() * 5);
		date.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, date.get(Calendar.HOUR_OF_DAY),
				date.get(Calendar.MINUTE) + randomNum, date.get(Calendar.SECOND));
		
		return date;
	}

}

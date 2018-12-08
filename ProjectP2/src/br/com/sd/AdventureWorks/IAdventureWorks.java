package br.com.sd.AdventureWorks;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IAdventureWorks extends Remote {
	
	public void sendRequest(Integer id, String phone) throws RemoteException;
	public HashMap<Integer, ArrayList> listRequest() throws RemoteException;
	
}

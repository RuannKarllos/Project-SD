package br.com.sd.Fabrikam;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IFabrikam extends Remote {

	public String openProcess(Integer id, String phone) throws RemoteException;

	public void startCheckAllStatus() throws RemoteException;

	public HashMap<Integer, ArrayList> listProcess() throws RemoteException;

}

package br.com.sd.DuwamishDelivery;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDuwamishDelivery extends Remote {

	public String addProcess(Integer id, String phone) throws RemoteException;

	public String removeProcess(Integer id) throws RemoteException;

	public HashMap<Integer, ArrayList> list() throws RemoteException;

}

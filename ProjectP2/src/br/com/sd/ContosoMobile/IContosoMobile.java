package br.com.sd.ContosoMobile;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IContosoMobile extends Remote{

	public void setStartRequisition(String phone) throws RemoteException;
	public void setFinishRequisition(String phone) throws RemoteException;
	
}

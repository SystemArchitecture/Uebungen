package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.pipes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteIOable<T> extends Remote {
    public T read() throws RemoteException;
    public void write(T value) throws RemoteException;
}

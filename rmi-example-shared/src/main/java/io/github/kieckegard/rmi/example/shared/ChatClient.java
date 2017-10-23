/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kieckegard
 */
public interface ChatClient extends Remote {
    
    void receive(Message message) throws RemoteException;
}

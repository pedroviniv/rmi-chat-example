/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.client;

import io.github.kieckegard.rmi.example.shared.ChatClient;
import io.github.kieckegard.rmi.example.shared.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, 
        Subject<Observer<Message>, Message> {
    
    private final List<Observer<Message>> observers;
    
    public ChatClientImpl() throws RemoteException {
        this.observers = new ArrayList<>();
    }

    @Override
    public void receive(Message message) throws RemoteException {
        this.broadcast(message);
    }

    @Override
    public void add(Observer<Message> observer) {
        this.observers.add(observer);
    }

    @Override
    public void remove(Observer<Message> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void broadcast(Message msg) {
        this.observers.forEach(o -> o.receive(msg));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.client;

import io.github.kieckegard.rmi.example.shared.ChatService;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class ChatServiceConnector implements Subject<ChatServiceConnObserver, ChatService> {

    private final List<ChatServiceConnObserver> observers = new ArrayList<>();

    public void connect() {
        new Thread(() -> {
            observers.forEach(o -> o.onConnecting());
            try {
                Registry registry = LocateRegistry.getRegistry(10999);
                ChatService service = (ChatService) registry.lookup("CHAT_SERVICE");
                observers.forEach(o -> this.broadcast(service));
            } catch (NotBoundException | AccessException ex) {
                observers.forEach(o -> o.onError(ex));
            } catch (RemoteException ex) {
                observers.forEach(o -> o.onError(ex));
            }
        }).start();
    }

    @Override
    public void add(ChatServiceConnObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void remove(ChatServiceConnObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void broadcast(ChatService chatService) {
        observers.forEach(o -> o.receive(chatService));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.server;

import io.github.kieckegard.rmi.example.shared.ChatService;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author kieckegard
 */
public class Loader {
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        
        Registry registry = LocateRegistry
                .createRegistry(10999);
        
        ConnectedClients clients = new ConnectedClients();
        System.out.println("CREATING CHAT_SERVICE INSTANCE...");
        ChatService chatService = new ChatServiceImpl(clients);
        
        System.out.println("EXPOSING CHAT_SERVICE INSTANCE...");
        registry.bind("CHAT_SERVICE", chatService);
        System.out.println("CHAT_SERVICE IS EXPOSED!");
    }
}

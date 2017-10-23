/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.server;

import io.github.kieckegard.rmi.example.shared.ChatClient;
import io.github.kieckegard.rmi.example.shared.ChatService;
import io.github.kieckegard.rmi.example.shared.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author kieckegard
 */
public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    
    private ConnectedClients clients;

    public ChatServiceImpl(ConnectedClients clients) throws RemoteException {
        this.clients = clients;
    }

    @Override
    public void send(Message message) throws RemoteException {
        
        String destinationId = message.getTo();
        
        try {
            //gets the client connection by the destination id
            ChatClient chatClient = clients.find(destinationId);
            
            try {
                //try to push the message to the client
                chatClient.receive(message);
            } catch (RemoteException ex) {
                
                System.out.println("The user " + destinationId
                        + " is no longer connected. Removing from connected users.");
                //In case the client is no longer connected
                //The server can remove it's connection from ConnectedClients
                this.clients.disconnect(destinationId);
            }
            
        } catch (IllegalArgumentException ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }

    @Override
    public void connect(String id, ChatClient chatClient) throws RemoteException {
        System.out.println("Client " + id + " is connecting... ");
        this.clients.connect(id, chatClient);
    }

    @Override
    public void disconnect(String id) throws RemoteException {
        try {
            this.clients.disconnect(id);
        } catch (IllegalArgumentException ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
    }
    
}

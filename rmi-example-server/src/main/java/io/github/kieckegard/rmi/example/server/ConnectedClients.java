/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.server;

import io.github.kieckegard.rmi.example.shared.ChatClient;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author kieckegard
 */
public class ConnectedClients {
    
    private final Map<String, ChatClient> clients;
    
    public ConnectedClients() {
        this.clients = Collections.synchronizedMap(new HashMap<>());
    }
    
    public void connect(String id, ChatClient client) {
        System.out.println("Adding client " + id + " to clients[" + this.clients + "]");
        this.clients.put(id, client);
    }
    
    public void disconnect(String id) {
        System.out.println("Disconnecting client " + id + "... ");
        Optional<ChatClient> result = Optional
                .ofNullable(this.clients.remove(id));
        if(!result.isPresent())
            throw new IllegalArgumentException("User " + id
                    + " is not connected!");
        else System.out.println("The client " + id + " was successfully disconnected!");
    }
    
    public ChatClient find(String id) {
        Optional<ChatClient> result = Optional
                .ofNullable(this.clients.get(id));
        if(!result.isPresent())
            throw new IllegalArgumentException("User " + id
                    + " is not connected!");
        return result.get();
    }
}

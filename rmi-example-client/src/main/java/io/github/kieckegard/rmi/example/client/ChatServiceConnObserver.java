/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.client;

import io.github.kieckegard.rmi.example.shared.ChatService;

/**
 *
 * @author kieckegard
 */
public interface ChatServiceConnObserver extends Observer<ChatService> {
    
    void onConnecting();
    void onError(Throwable throwable);
}

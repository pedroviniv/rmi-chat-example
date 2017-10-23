/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.client;

/**
 *
 * @author kieckegard
 */
public interface Observer<T> {
    
    void receive(T msg);
}

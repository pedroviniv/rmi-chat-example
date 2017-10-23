/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.client.gui;

import io.github.kieckegard.rmi.example.client.ChatServiceConnObserver;
import io.github.kieckegard.rmi.example.client.ChatServiceConnector;
import io.github.kieckegard.rmi.example.shared.ChatService;
import java.awt.Color;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author kieckegard
 */
public class Main extends javax.swing.JFrame implements ChatServiceConnObserver {

    public Main() {
        initComponents();
    }
    
    @Override
    public void onConnecting() {
        lblConnectionStatus.setForeground(Color.BLUE);
        lblConnectionStatus.setText("Connecting...");
    }

    @Override
    public void onError(Throwable throwable) {
        lblConnectionStatus.setForeground(Color.RED);
        lblConnectionStatus.setText("Failed to Connect");
        JOptionPane.showMessageDialog(this, throwable.getCause().getMessage(), 
                "Error while connecting to the Chat Service", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void receive(ChatService chatService) {
        lblConnectionStatus.setForeground(Color.GREEN);
        lblConnectionStatus.setText("Connected!");
        String email = txtYourEmail.getText();
        Chat chat = new Chat(chatService, email);
                chat.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtYourEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnEnterChat = new javax.swing.JButton();
        lblConnectionStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Who are you"));

        jLabel1.setText("E-mail");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYourEmail)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 299, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYourEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        btnEnterChat.setText("Next");
        btnEnterChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterChatActionPerformed(evt);
            }
        });

        lblConnectionStatus.setText("Click on the next button to connect to the service");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnterChat, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblConnectionStatus)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblConnectionStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnterChat)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterChatActionPerformed
        String email = txtYourEmail.getText();
        if(email == null || email.isEmpty())
            JOptionPane.showMessageDialog(this, "Please enter your e-mail!", 
                    "Invalid input!", JOptionPane.INFORMATION_MESSAGE);
        else {
            ChatServiceConnector connector = new ChatServiceConnector();
            connector.add(this);
            connector.connect();
        }
    }//GEN-LAST:event_btnEnterChatActionPerformed

    
    public static void main(String args[]) throws RemoteException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException 
                | javax.swing.UnsupportedLookAndFeelException ex) {
        
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnterChat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConnectionStatus;
    private javax.swing.JTextField txtYourEmail;
    // End of variables declaration//GEN-END:variables

}

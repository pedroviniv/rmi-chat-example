/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.rmi.example.client.gui;

import io.github.kieckegard.rmi.example.client.ChatClientImpl;
import io.github.kieckegard.rmi.example.client.Observer;
import io.github.kieckegard.rmi.example.shared.ChatService;
import io.github.kieckegard.rmi.example.shared.Message;
import java.rmi.RemoteException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author kieckegard
 */
public class Chat extends javax.swing.JFrame implements Observer<Message> {
    
    private DefaultListModel listModel = new DefaultListModel();
    private ChatService chatService;
    private String userEmail;

    public Chat(ChatService chatService, String userEmail) {
        initComponents();
        this.chatService = chatService;
        this.userEmail = userEmail;
        this.lblWelcome.setText(String.format("Welcome, %s!", this.userEmail));
        connectClient();
    }
    
    private void connectClient() {
        
        try {
            ChatClientImpl client = new ChatClientImpl();
            client.add(this);
            this.chatService.connect(this.userEmail, client);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Error while connecting to the server", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Message getMessage() {
        String msgTo = this.txtMsgTo.getText();
        String msgText = this.txtMsgText.getText();
        
        if(msgTo == null || msgTo.isEmpty())
            throw new IllegalArgumentException("You have to especify"
                    + " the message destination!");
        if(msgText == null || msgText.isEmpty())
            throw new IllegalArgumentException("You have to especify "
                    + " the message text!");
        
        return new Message(userEmail, msgTo, msgText);
    }
    
    @Override
    public void receive(Message msg) {
        addMessageToList(msg);
    }
    
    public void addMessageToList(Message msg) {
        this.listModel.addElement(String
                .format("%s: %s", msg.getFrom(), 
                        msg.getText()));
        this.listMessages.setModel(listModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtMsgTo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMsgText = new javax.swing.JTextArea();
        btnSendMsg = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMessages = new javax.swing.JList<>();
        lblWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Message Data"));

        txtMsgText.setColumns(20);
        txtMsgText.setRows(5);
        jScrollPane2.setViewportView(txtMsgText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addComponent(txtMsgTo))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMsgTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnSendMsg.setText("Send Message");
        btnSendMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMsgActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Messages"));

        jScrollPane1.setViewportView(listMessages);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblWelcome.setForeground(new java.awt.Color(135, 159, 211));
        lblWelcome.setText("Welcome, user!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSendMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblWelcome)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSendMsg)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMsgActionPerformed
        try {
            Message message = this.getMessage();
            try {
                this.chatService.send(message);
                addMessageToList(message);
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Sending message error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSendMsgActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            System.out.println("Disconnecting...");
            this.chatService.disconnect(this.userEmail);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Failed to disconnect", 
                    ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendMsg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JList<String> listMessages;
    private javax.swing.JTextArea txtMsgText;
    private javax.swing.JTextField txtMsgTo;
    // End of variables declaration//GEN-END:variables

}

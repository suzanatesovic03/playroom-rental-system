/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Primalac {
    private Socket socket;

    public Primalac(Socket socket) {
        this.socket = socket;
    }
    
    public Object primi(){
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (java.io.EOFException | java.net.SocketException e) {
        return null;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
}

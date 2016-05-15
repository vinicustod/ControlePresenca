/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.client;

import java.util.Observable;

/**
 *
 * @author lucasfernandes
 */
public class Session extends Observable{
    
    private boolean connection;
    
    public Session ( boolean connection ) 
    {
        this.connection = connection;
    }
    
    
    public void setConnection(boolean connection) {
        
        this.connection = connection;
        
        // Let observers now that a change occorued 
        setChanged();
        notifyObservers(this);
    }

    /**
     * @return the connection
     */
    public boolean isConnection() {
        return connection;
    }
    
}


// package com.warehouse;
import java.io.*;

public class PriceAssignmentIdServer implements Serializable {

    private int idCounter;
    private static PriceAssignmentIdServer PriceAssignmentServer;

    private PriceAssignmentIdServer() {
        idCounter = 1;
    }

    public static PriceAssignmentIdServer instance() {
        if (PriceAssignmentServer == null) {
            return (PriceAssignmentServer = new PriceAssignmentIdServer());
        } else {
            return PriceAssignmentServer;
        }
    }

    public int getId() {
        return idCounter++;
    }

    public String toString() {
        return ("IdServer" + idCounter);
    }

    public static void retrieve(ObjectInputStream input) {
        try {
            PriceAssignmentServer = (PriceAssignmentIdServer) input.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    private void writeObject(ObjectOutputStream output) throws IOException {
        try {
            output.defaultWriteObject();
            output.writeObject(PriceAssignmentServer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        try {
            input.defaultReadObject();
            if (PriceAssignmentServer == null) {
                PriceAssignmentServer = (PriceAssignmentIdServer) input.readObject();
            } else {
                input.readObject();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
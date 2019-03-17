
// package com.warehouse;
import java.io.*;

public class ClientIDServer implements Serializable {

  private int idCounter;
  private static ClientIDServer clientServer;

  private ClientIDServer() {
    idCounter = 1;
  }

  public static ClientIDServer instance() {
    if (clientServer == null) {
      return (clientServer = new ClientIDServer());
    } else {
      return clientServer;
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
      clientServer = (ClientIDServer) input.readObject();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (Exception cnfe) {
      cnfe.printStackTrace();
    }
  }

  private void writeObject(ObjectOutputStream output) throws IOException {
    try {
      output.defaultWriteObject();
      output.writeObject(clientServer);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
    try {
      input.defaultReadObject();
      if (clientServer == null) {
        clientServer = (ClientIDServer) input.readObject();
      } else {
        input.readObject();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

}
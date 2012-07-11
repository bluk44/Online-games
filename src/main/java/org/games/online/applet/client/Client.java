package org.games.online.applet.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.games.online.applet.events.DisconnectedEvent;
import org.games.online.applet.events.DisconnectedEventListener;
import org.games.online.applet.events.Event;
import org.games.online.applet.events.EventListener;

public class Client {

	protected String name;
	protected int id;
	protected static int clientCounter = 0;

	protected List<Object> listenerList = new ArrayList<Object>();

	protected Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public boolean isConnected = false;
	public boolean isOutputShutdown = true;
	public boolean isInputShutdown = true;
	public Client() {
		this("bezimienny");
	}

	public Client(String name) {
		this.name = name;
		synchronized (this) {
			id = clientCounter++;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addListener(Class<? extends EventListener> listenerType,
			EventListener listener) {
		listenerList.add(listenerType);
		listenerList.add(listener);
	}

	public void eventOccurred(Class<? extends EventListener> eventListenerType,
			Event event) {
		for (int i = 0; i < listenerList.size(); i += 2) {
			if (listenerList.get(i).equals(eventListenerType)) {
				EventListener el = (EventListener) listenerList.get(i + 1);
				el.eventOccurred(this, event);
			}
		}
	}

	/**
	 * Tries to connect to specified server
	 * 
	 * @param hostname
	 *            Server ip address
	 * @param port
	 *            Server port
	 */
	public void initConnection(String hostname, int port) throws IOException,
			UnknownHostException {

		clientSocket = new Socket(hostname, port);
		InputStream  is  = clientSocket.getInputStream();
		OutputStream os = clientSocket.getOutputStream();
	//	ObjectInputStream ois = new ObjectInputStream(is);
		//clientSocket.getOutputStream();
//		in = new ObjectInputStream(clientSocket.getInputStream());
//		out = new ObjectOutputStream(clientSocket.getOutputStream());
		isConnected = clientSocket.isConnected();
		isInputShutdown = clientSocket.isInputShutdown();
		isOutputShutdown = clientSocket.isOutputShutdown();
//		eventOccurred(ConnectedEventListener.class, new ConnectedEvent());
//TODO
//		sendAuthenticationData();
	}

	/**
	 * Disconnects from server
	 * @throws IOException 
	 */
	public void closeConnection() throws IOException {
		if(isConnected){
			out.close();
			in.close();
			isConnected = false;
			eventOccurred(DisconnectedEventListener.class, new DisconnectedEvent());
		}
	}

	@Override
	public String toString() {
		return "client " + id + "\n";
	}
}

package org.games.online.applet.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import org.games.online.applet.client.Client;
import org.games.online.applet.client.ClientPipelineFactory;
import org.games.online.applet.events.ChangedRoomEvent;
import org.games.online.applet.events.ChangedRoomEventListener;
import org.games.online.applet.events.ChannelExceptionEvent;
import org.games.online.applet.events.ChannelExceptionEventListener;
import org.games.online.applet.events.ConnectedEvent;
import org.games.online.applet.events.ConnectedEventListener;
import org.games.online.applet.events.ConnectionFailedEvent;
import org.games.online.applet.events.ConnectionFailedEventListener;
import org.games.online.applet.events.DisconnectedEvent;
import org.games.online.applet.events.DisconnectedEventListener;
import org.games.online.applet.events.SomeEvent;
import org.games.online.applet.events.SomeEventListener;
import org.games.online.applet.model.PlayerInfo;
import org.games.online.applet.model.TableInfo;
import org.games.online.tcpservice.server.TestNettyConnection.ClientChannelHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainClass extends javax.swing.JApplet {

	private Client client;
	private JMenuBar menuBar;
	private JScrollPane consoleScrollPane;
	private JMenuItem MenuItemDisconnect;
	private JMenuItem MenuItemConnect;
	private JMenu ActionMenu;
	private JTextArea consoleTextArea;
	private JList roomInfoList;
	private JScrollPane roomInfoScrollPane;
	private JMenuItem room1;
	private JMenu jMenu1;
	private JList<PlayerInfo> playerInfoList;
	private JScrollPane listScrollPane;
	
	private static int DEFAULT_PORT = 10000;
	/**
	 * Auto-generated main method to display this JApplet inside a new JFrame.
	 */
	@Override
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				public void run() {
//					initClient();
					initGUI();
					String host = getCodeBase().getHost();
//					consoleTextArea.append(host+"\n");
//					ChannelFactory channelFactory;
//					ClientBootstrap bootstrap;
//					ChannelFuture channelFuture;
//					Channel channel;
//					ChannelBuffer writeBuffer;
//					
//					channelFactory = new NioClientSocketChannelFactory(
//							Executors.newCachedThreadPool(),
//							Executors.newCachedThreadPool());
//
//					bootstrap = new ClientBootstrap(channelFactory);
//					ClientPipelineFactory factory = new ClientPipelineFactory(
//							new ClientChannelHandler());
//					bootstrap.setPipelineFactory(factory);
//					bootstrap.setOption("tcpNoDelay", true);
//					bootstrap.setOption("keepAlive", true);
//
//					channelFuture = bootstrap
//							.connect(new InetSocketAddress(host, 10000));
//					consoleTextArea.append("after connect\n");
//					channelFuture.awaitUninterruptibly();
//					consoleTextArea.append("after await\n");
//					if (!channelFuture.isSuccess()) {
//						consoleTextArea.append("connection failed\n");
//					} else {
//						consoleTextArea.append("connection success\n");
//					}
//					
//					try {
//						Socket clientSocket = new Socket(host, 10000);
//						InputStream is= clientSocket.getInputStream();
//						ObjectInputStream b = new ObjectInputStream(is);
//						if(clientSocket.isConnected()){
//							consoleTextArea.append("connected\n");
//						}
//						} catch (UnknownHostException e) {
//							consoleTextArea.append("UnknownHostException\n");
//					} catch (IOException e) {
//						consoleTextArea.append("IOException\n");
//					}
//					ClientBootstrap bootstrap = new ClientBootstrap(
//							new NioClientSocketChannelFactory(
//									Executors.newCachedThreadPool(),
//									Executors.newCachedThreadPool()));
//					ClientPipelineFactory factory = new ClientPipelineFactory(
//							new ClientChannelHandler());
//					
//					ChannelFuture future = bootstrap.connect(new InetSocketAddress(
//							host, 10000));
//
//					// Wait until the connection attempt succeeds or fails.
//					Channel channel = future.awaitUninterruptibly().getChannel();
//					if (!future.isSuccess()) {
//						print("conection failed");
//						bootstrap.releaseExternalResources();
//						return;
//					}else{
//						print("connection success");
//					}
//					client.initConnection(host, DEFAULT_PORT);
//					try {
//						Thread.sleep(3000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					client.closeConnection();
//					if(client.isConnected){
//						print("connection successful");
//					}else{
//						print("connection failed");
//					}
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					ChannelFuture closeFuture = channel.close();
//					
				}

			});
		} catch (Exception ex) {

		}
	}
	class ClientChannelHandler extends SimpleChannelHandler {

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {

		}

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
		}
	}
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setSize(722, 562);
			{
				menuBar = new JMenuBar();
				setJMenuBar(menuBar);
				{
					jMenu1 = new JMenu();
					menuBar.add(jMenu1);
					jMenu1.setText("Room");
					{
						room1 = new JMenuItem();
						jMenu1.add(room1);
						room1.setText("chujewo");
						room1.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out.println("room1.mouseClicked, event="
										+ evt);
								// TODO add your code for room1.mouseClicked
							}
						});
					}
				}
				{
					ActionMenu = new JMenu();
					menuBar.add(ActionMenu);
					ActionMenu.setText("Action");
					{
						MenuItemConnect = new JMenuItem();
						ActionMenu.add(MenuItemConnect);
						MenuItemConnect.setText("Connect");
						MenuItemConnect.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent evt) {
//								try {
//									client.initConnection(getCodeBase().getHost(),DEFAULT_PORT);
//									if(client.isConnected){
//										print("connected");
//										print(""+client.isInputShutdown);
//										print(""+client.isOutputShutdown);
//									}
//									
//								} catch (UnknownHostException e) {
//									print("unknown host: "+getCodeBase().getHost());
//								} catch (IOException e) {
//									print("Couldn't get I/O for the connection to: "+getCodeBase().getHost());
//								}
							}
						});
					}
					{
						MenuItemDisconnect = new JMenuItem();
						ActionMenu.add(MenuItemDisconnect);
						MenuItemDisconnect.setText("Disconnect");
						MenuItemDisconnect.addMouseListener(new MouseAdapter() {
							public void mousePressed(MouseEvent evt) {
							}
						});
					}
				}
			}
			{
				listScrollPane = new JScrollPane();
				{
					ListModel<PlayerInfo> playerInfoListModel = new DefaultComboBoxModel<PlayerInfo>();
					playerInfoList = new JList<PlayerInfo>();
					listScrollPane.setViewportView(playerInfoList);
					playerInfoList.setModel(playerInfoListModel);
				}
			}
			{
				roomInfoScrollPane = new JScrollPane();
				{
					ListModel<TableInfo> roomInfoListModel = new DefaultComboBoxModel<TableInfo>();
					roomInfoList = new JList<TableInfo>();
					roomInfoScrollPane.setViewportView(roomInfoList);
					roomInfoList.setModel(roomInfoListModel);
					roomInfoList.setPreferredSize(new java.awt.Dimension(555,
							435));
				}
			}
			{
				consoleScrollPane = new JScrollPane();
				{
					consoleTextArea = new JTextArea();
					consoleScrollPane.setViewportView(consoleTextArea);
					consoleTextArea.setEditable(false);
				}
			}
			thisLayout
					.setVerticalGroup(thisLayout
							.createParallelGroup()
							.addComponent(listScrollPane,
									GroupLayout.Alignment.LEADING, 0, 562,
									Short.MAX_VALUE)
							.addGroup(
									GroupLayout.Alignment.LEADING,
									thisLayout
											.createSequentialGroup()
											.addComponent(roomInfoScrollPane,
													GroupLayout.PREFERRED_SIZE,
													472,
													GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(consoleScrollPane, 0,
													84, Short.MAX_VALUE)));
			thisLayout.setHorizontalGroup(thisLayout
					.createSequentialGroup()
					.addGroup(
							thisLayout
									.createParallelGroup()
									.addComponent(roomInfoScrollPane,
											GroupLayout.Alignment.LEADING, 0,
											558, Short.MAX_VALUE)
									.addComponent(consoleScrollPane,
											GroupLayout.Alignment.LEADING, 0,
											558, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(listScrollPane, GroupLayout.PREFERRED_SIZE,
							158, GroupLayout.PREFERRED_SIZE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initClient() {
		client = new Client();
		client.addListener(ConnectedEventListener.class,
				new ConnectedEventListener() {

					public void eventOccurred(Object source,
							ConnectedEvent event) {
							print("connection successful lol");
						
					}

				});
		client.addListener(DisconnectedEventListener.class,
				new DisconnectedEventListener() {

					public void eventOccurred(Object source,
							DisconnectedEvent event) {
						print("disconnected");
					}
				});
		client.addListener(ChannelExceptionEventListener.class,
				new ChannelExceptionEventListener() {

					public void eventOccurred(Object source,
							ChannelExceptionEvent event) {
						consoleTextArea.append("channel exception occurred: \n"+event.toString());
					}
				});
		client.addListener(ChangedRoomEventListener.class,
				new ChangedRoomEventListener() {

					public void eventOccurred(Object source,
							ChangedRoomEvent event) {
						consoleTextArea.append("changed room to: \n"+event.getRoomInfo());

					}
				});
		client.addListener(ConnectionFailedEventListener.class, new ConnectionFailedEventListener() {
			
			@Override
			public void eventOccurred(Object source, ConnectionFailedEvent event) {
				print("connection failed");				
			}
		});
		client.addListener(SomeEventListener.class, new SomeEventListener() {
			
			@Override
			public void eventOccurred(Object source, SomeEvent event) {
				consoleTextArea.append(event.getText());	
				
			}
		});
	}
	private void print(String msg){
		consoleTextArea.append(msg+"\n");
	}
}

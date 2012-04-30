package org.games.online.applet.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import org.games.online.applet.events.ChangedRoomEvent;
import org.games.online.applet.events.ChangedRoomEventListener;
import org.games.online.applet.events.ChannelExceptionEvent;
import org.games.online.applet.events.ChannelExceptionEventListener;
import org.games.online.applet.events.ConnectedEvent;
import org.games.online.applet.events.ConnectedEventListener;
import org.games.online.applet.events.DisconnectedEvent;
import org.games.online.applet.events.DisconnectedEventListener;
import org.games.online.applet.model.PlayerInfo;
import org.games.online.applet.model.TableInfo;

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

	/**
	 * Auto-generated main method to display this JApplet inside a new JFrame.
	 */
	@Override
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				public void run() {
					initGUI();
				}

			});
		} catch (Exception ex) {

		}
	}

	public MainClass() {
		super();
		initClient();
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
							public void mouseClicked(MouseEvent evt) {
								System.out
										.println("MenuItemConnect.mouseClicked, event="
												+ evt);
								client.initConnection();
							}
						});
					}
					{
						MenuItemDisconnect = new JMenuItem();
						ActionMenu.add(MenuItemDisconnect);
						MenuItemDisconnect.setText("Disconnect");
						MenuItemDisconnect.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								System.out
										.println("MenuItemDisconnect.mouseClicked, event="
												+ evt);
								client.closeConnection();
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
						consoleTextArea.append("connected \n");
					}

				});
		client.addListener(DisconnectedEventListener.class,
				new DisconnectedEventListener() {

					public void eventOccurred(Object source,
							DisconnectedEvent event) {
						consoleTextArea.append("diss-connected \n");
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
	}

}

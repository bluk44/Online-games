package org.games.online.message;

public class ChangeTableMessage implements Message {
	int tableId;
	public void setTableId(int id){
		tableId = id;
	}
	public int getTableId(){
		return tableId;
	}
}

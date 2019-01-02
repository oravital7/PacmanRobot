package dataBase;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	private static final long serialVersionUID = -2591792764959317565L;
	
	String column[] = {"FirstID","SecondID","ThirdID","LogTime","Point","SomeDouble"};
	ArrayList<Object[]> data;

	public TableModel(ArrayList<Object[]> data) {
		this.data = data;
	}
	
	@Override
	public String getColumnName(int column) {
		return this.column[column];
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}
	
}
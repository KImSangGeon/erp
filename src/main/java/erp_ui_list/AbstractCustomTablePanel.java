package erp_ui_list;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


@SuppressWarnings("serial")
public abstract class  AbstractCustomTablePanel<T> extends JPanel {
	protected JTable table;
	protected List<T> list;

	public AbstractCustomTablePanel() {

		initialize();
	

	}
	public abstract T getItem(); 
	/*{
		int idx = table.getSelectedRow();
		if(idx == -1 ) {
		throw new NotSelectedExceotion();
		}
			return list.get(idx);		
	}
	*/
	public void loadData() {
		initList();
		setList();
		
	}
	
	public void  setPopupMenu(JPopupMenu popMenu) {
		table.setComponentPopupMenu(popMenu);
	}
	public abstract void initList();
		
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(getModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	public DefaultTableModel getModel() {
		CustomTableModel model = new CustomTableModel(getData(),getColumnNames());
		return model;
	}
	public Object[][] getData() {
		return new Object[][] {{null, null, null}};
	}
	
	public abstract String[] getColumnNames(); 
	
	public  void setList() {
		Object[][] data = new Object[list.size()][];
	for(int i = 0; i < data.length; i++) {
		data[i] = toArray(list.get(i));
	}
	CustomTableModel model = new CustomTableModel(data, getColumnNames());
	table.setModel(model);
	
	RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	table.setRowSorter(sorter);
	
	setAlignAndWidth();
	/*
			//컬럼내용 정렬
			setTableCellAlign(SwingConstants.CENTER, 0, 1);
			
			//컬럼별 너비 조정
			setTableCellWidth(100, 250);
	
	*/
	}	
	
	
	protected abstract void setAlignAndWidth();
	
	public void setTableCellWidth(int...width) {
		TableColumnModel tcm = table.getColumnModel();
		
		for(int i=0; i<width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
		
	}
	public void setTableCellAlign(int align, int...idx) {
		TableColumnModel tcm = table.getColumnModel();
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		for(int i=0; i<idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
		
	}
	
	protected abstract  Object[] toArray(T t);
	
	private class CustomTableModel extends DefaultTableModel{

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		
		}

	}
}




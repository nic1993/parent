package com.horstmann.violet.framework.propertyeditor.customeditor;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class SequenceJtabel extends JTable{
       private SequenceTabelModel sequenceTabelModel;
       private JComboBox jComboBox;
       private Vector comboBoxVector;
       public SequenceJtabel(SequenceTabelModel TableModel) {
		// TODO Auto-generated constructor stub
    	   sequenceTabelModel = (SequenceTabelModel) TableModel;
    	   this.setModel(sequenceTabelModel);
    	   
           comboBoxVector = new Vector();
           comboBoxVector.add("post-conditon"); 
           comboBoxVector.add("pre-conditon"); 
           jComboBox = new JComboBox(comboBoxVector);
	   }
	   public void addcolumn()
       {
		   sequenceTabelModel.add();
    	   TableColumn tableColumn = this.getColumnModel().getColumn(this.getColumnCount() - 1);
    	   tableColumn.setCellEditor(new DefaultCellEditor(jComboBox));
       }
	   public void deletecolumn()
       {
		   int selectRow = this.getSelectedRow();
		   sequenceTabelModel.delete(selectRow);
       }
       

}
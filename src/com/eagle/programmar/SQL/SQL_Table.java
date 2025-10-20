// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 19, 2025

package com.eagle.programmar.SQL;

import java.util.ArrayList;

import com.eagle.math.EagleValue;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class SQL_Table extends EagleValue
{
	private static class EagleTableColumn
	{
		public String _columnName;
		public TypeEnum _columnType;
	}
	
	private static class EagleTableRow
	{
		public ArrayList<EagleValue> _values;
	}
	
	private ArrayList<EagleTableColumn> _columns = new ArrayList<EagleTableColumn>();
	private ArrayList<EagleTableRow> _rows = new ArrayList<EagleTableRow>();
	
	public void addColumn(String name, TypeEnum type)
	{
		if (_rows.size() > 0)
		{
			throw new RuntimeException("Can not add columns to table that is not empty");
		}
		
		EagleTableColumn newCol = new EagleTableColumn();
		newCol._columnName = name;
		newCol._columnType = type;
		_columns.add(newCol);
		// System.err.println("***** Added column " + name + " of type " + type);
	}
	
	public void addRow(ArrayList<EagleValue> vals)
	{
		EagleTableRow newRow = new EagleTableRow();
		newRow._values = vals;
		_rows.add(newRow);
	}
	
	public int getNumberColumns()
	{
		return _columns.size();
	}
	
	public int getNumberRows()
	{
		return _rows.size();
	}
	
	public String getColumnName(int col)
	{
		return _columns.get(col)._columnName;
	}
	
	public TypeEnum getColumnType(int col)
	{
		return _columns.get(col)._columnType;
	}
	
	public ArrayList<EagleValue> getRow(int row)
	{
		return _rows.get(row)._values;
	}
	
	@Override
	public String typeName()
	{
		return "Table";
	}

	@Override
	public String toString()
	{
		if (_rows.size() == 0) return "table[]";
		return "table[" + _rows.size() + "]";
	}
}

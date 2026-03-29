// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 19, 2025

namespace com.eagle.programmar.SQL
{

	using EagleValue = com.eagle.math.EagleValue;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class SQL_Table : EagleValue
	{
		private class EagleTableColumn
		{
			public string _columnName;
			public TypeEnum _columnType;
		}

		private class EagleTableRow
		{
			public List<EagleValue> _values;
		}

		private List<EagleTableColumn> _columns = new List<EagleTableColumn>();
		private List<EagleTableRow> _rows = new List<EagleTableRow>();

		public virtual void addColumn(string name, TypeEnum type)
		{
			if (_rows.Count > 0)
			{
				throw new Exception("Can not add columns to table that is not empty");
			}

			EagleTableColumn newCol = new EagleTableColumn();
			newCol._columnName = name;
			newCol._columnType = type;
			_columns.Add(newCol);
			// System.err.println("***** Added column " + name + " of type " + type);
		}

		public virtual void addRow(List<EagleValue> vals)
		{
			EagleTableRow newRow = new EagleTableRow();
			newRow._values = vals;
			_rows.Add(newRow);
		}

		public virtual int NumberColumns
		{
			get
			{
				return _columns.Count;
			}
		}

		public virtual int NumberRows
		{
			get
			{
				return _rows.Count;
			}
		}

		public virtual string getColumnName(int col)
		{
			return _columns[col]._columnName;
		}

		public virtual TypeEnum getColumnType(int col)
		{
			return _columns[col]._columnType;
		}

		public virtual List<EagleValue> getRow(int row)
		{
			return _rows[row]._values;
		}

		public override TypeEnum Type
		{
			get
			{
				return TypeEnum.TABLE;
			}
		}

		public override string ToString()
		{
			if (_rows.Count == 0)
			{
				return "table[]";
			}
			return "table[" + _rows.Count + "]";
		}
	}

}

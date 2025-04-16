// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 12, 2025

package com.eagle.programmar.SQL;

import java.util.ArrayList;
import java.util.HashMap;

import com.eagle.interpret.EagleStateMachine;
import com.eagle.math.EagleValue;

public class SQL_StateMachine extends EagleStateMachine
{
	public static enum SQL_FieldEnum
	{
		SQL_FieldInteger,
		SQL_FieldString
	}
	
	public static class SQL_Field
	{
		public String _name;
		public SQL_FieldEnum _type;
	}
		
	public static class SQL_Row
	{
		public ArrayList<EagleValue> _values;
	}
	
	public static class SQL_Table
	{
		public ArrayList<SQL_Row> _rows;
		public ArrayList<SQL_Field> _fields;
	}
	
	public HashMap<String, SQL_Table> _tables = new HashMap<String, SQL_Table>();
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;

public class RPG_O_Output_Specification_IV extends RPG_O_Output_Specification
{
	public RPG_O_Output_Specification_IV()
	{
		spec = new RPG_O_Output_Spec();
		spec.recordId = new RPG_O_Output_Program_Record_Id_IV();
		spec.fieldDescr = new RPG_O_Output_Program_Field_Descr_IV();
		spec.externalRecordId = new RPG_O_Output_External_Record_Id_IV();
		spec.externalFieldDescr = new RPG_O_Output_External_Field_Descr_IV();
	}
	
	public static class RPG_O_Output_Program_Record_Id_IV extends RPG_O_Output_Program_Record_Id
	{
		public RPG_O_Output_Program_Record_Id_IV()
		{
			piece = new RPG_O_Output_Program_Record_Id_Piece_IV();
			
			skipBefore = new RPG_Literal(46, 48);
			skipAfter = new RPG_Literal(49, 51);
			indicators = new RPG_Literal(21, 29);
			exceptName = new RPG_Literal(30, 39);
			blank1 = new RPG_Blanks(52, 80);
		}
	}
	
	public static class RPG_O_Output_Program_Record_Id_Piece_IV extends RPG_O_Output_Program_Record_Id_Piece
	{
		public RPG_O_Output_Program_Record_Id_Piece_IV()
		{
			piece1 = new RPG_O_Output_Program_Record_Id_Piece1_IV();
			piece2 = new RPG_O_Output_Program_Record_Id_Piece2_IV();
			piece3 = new RPG_O_Output_Program_Record_Id_Piece3_IV();
		}
	}

	public static class RPG_O_Output_Program_Record_Id_Piece1_IV extends RPG_O_Output_Program_Record_Id_Piece1
	{
		public RPG_O_Output_Program_Record_Id_Piece1_IV()
		{
			recordName = new RPG_Literal(7, 16);
			type = new RPG_KeywordChoice(17, 17, "H", "D", "T", "E");
			overflowRelease = new RPG_KeywordChoice(18, 18, "F", "R");
			spaceBefore = new RPG_Number(40, 42);
			spaceAfter = new RPG_Number(43, 45);
			skipBefore = new RPG_Literal(46, 48);
			skipAfter = new RPG_Literal(49, 51);
		}
	}
	
	public static class RPG_O_Output_Program_Record_Id_Piece2_IV extends RPG_O_Output_Program_Record_Id_Piece2
	{
		public RPG_O_Output_Program_Record_Id_Piece2_IV()
		{
			blank1 = new RPG_Blanks(7, 15);
			logicalRelation = new RPG_KeywordChoice(16, 18, "AND", "OR");
			blank2 = new RPG_Blanks(19, 22);
		}
	}
	
	public static class RPG_O_Output_Program_Record_Id_Piece3_IV extends RPG_O_Output_Program_Record_Id_Piece3
	{
		public RPG_O_Output_Program_Record_Id_Piece3_IV()
		{
			blank1 = new RPG_Blanks(7, 17);
			logicalRelation = new RPG_KeywordChoice(18, 20, "ADD", "DEL");
			blank2 = new RPG_Blanks(21, 22);
		}
	}
	
	public static class RPG_O_Output_Program_Field_Descr_IV extends RPG_O_Output_Program_Field_Descr
	{
		public RPG_O_Output_Program_Field_Descr_IV()
		{
			blank1 = new RPG_Blanks(7, 21);
			indicators = new RPG_Literal(21, 29);
			fieldName = new RPG_Literal(30, 43);
			editCodes = new RPG_Literal(44, 44);
			blankAfter = new RPG_Keyword(45, 45, "B");
			endPosition = new RPG_Literal(47, 51);
			dataFormat = new RPG_KeywordChoice(52, 52, "P", "B", "L", "R");
			constantOrEditWord = new RPG_Literal(53, 80);
			blank2 = new RPG_Blanks(0, 0);		// Not Applicable
		}
	}
	
	public static class RPG_O_Output_External_Record_Id_IV extends RPG_O_Output_External_Record_Id
	{
		public RPG_O_Output_External_Record_Id_IV()
		{
			piece = new RPG_O_Output_External_Record_Id_Piece_IV();

			recordName = new RPG_Literal(7, 16);
			indicators = new RPG_Literal(21, 29);
			exceptName = new RPG_Literal(30, 39);
			blank1 = new RPG_Blanks(40, 80);
		}
	}
	
	public static class RPG_O_Output_External_Record_Id_Piece_IV extends RPG_O_Output_External_Record_Id_Piece
	{
		public RPG_O_Output_External_Record_Id_Piece_IV()
		{
			piece1 = new RPG_O_Output_External_Record_Id_Piece1_IV();
			piece2 = new RPG_O_Output_External_Record_Id_Piece2_IV();
			piece3 = new RPG_O_Output_External_Record_Id_Piece3_IV();
		}
	}
	
	public static class RPG_O_Output_External_Record_Id_Piece1_IV extends RPG_O_Output_External_Record_Id_Piece1
	{
		public RPG_O_Output_External_Record_Id_Piece1_IV()
		{
			filename = new RPG_Literal(7, 16);
			type = new RPG_KeywordChoice(17, 17, "H", "D", "T", "E");
			release = new RPG_Keyword(18, 18, "R");
			blank2 = new RPG_Blanks(19, 22);
		}
	}
	
	public static class RPG_O_Output_External_Record_Id_Piece2_IV extends RPG_O_Output_External_Record_Id_Piece2
	{
		public RPG_O_Output_External_Record_Id_Piece2_IV()
		{
			blank1 = new RPG_Blanks(7, 15);
			logicalRelation = new RPG_KeywordChoice(16, 18, "AND", "OR");
			blank2 = new RPG_Blanks(19, 22);
		}
	}
	
	public static class RPG_O_Output_External_Record_Id_Piece3_IV extends RPG_O_Output_External_Record_Id_Piece3
	{
		public RPG_O_Output_External_Record_Id_Piece3_IV()
		{
			blank1 = new RPG_Blanks(7, 17);
			logicalRelation = new RPG_KeywordChoice(18, 20, "ADD", "DEL");
			blank2 = new RPG_Blanks(21, 22);
		}
	}
	
	public static class RPG_O_Output_External_Field_Descr_IV extends RPG_O_Output_External_Field_Descr
	{
		public RPG_O_Output_External_Field_Descr_IV()
		{
			blank1 = new RPG_Blanks(7, 20);
			indicators = new RPG_Literal(21, 29);
			fieldName = new RPG_Literal(30, 43);
			blank2 = new RPG_Blanks(44, 44);
			blankAfter = new RPG_Keyword(45, 45, "B");
			blank3 = new RPG_Blanks(46, 80);
		}
	}
}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.RPG.Specifications
{
	using RPG_Blanks = com.eagle.programmar.RPG.Terminals.RPG_Blanks;
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_KeywordChoice = com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using RPG_Number = com.eagle.programmar.RPG.Terminals.RPG_Number;

	public class RPG_O_Output_Specification_IV : RPG_O_Output_Specification
	{
		public RPG_O_Output_Specification_IV()
		{
			spec = new RPG_O_Output_Spec_IV();
		}

		public class RPG_O_Output_Spec_IV : RPG_O_Output_Spec
		{
			public RPG_O_Output_Spec_IV()
			{
				XXrecordId = new RPG_O_Output_Program_Record_Id_IV();
				XXfieldDescr = new RPG_O_Output_Program_Field_Descr_IV();
				XXexternalRecordId = new RPG_O_Output_External_Record_Id_IV();
				XXexternalFieldDescr = new RPG_O_Output_External_Field_Descr_IV();
			}
		}

		public class RPG_O_Output_Program_Record_Id_IV : RPG_O_Output_Program_Record_Id
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

		public class RPG_O_Output_Program_Record_Id_Piece_IV : RPG_O_Output_Program_Record_Id_Piece
		{
			public RPG_O_Output_Program_Record_Id_Piece_IV()
			{
				XXpiece1 = new RPG_O_Output_Program_Record_Id_Piece1_IV();
				XXpiece2 = new RPG_O_Output_Program_Record_Id_Piece2_IV();
				XXpiece3 = new RPG_O_Output_Program_Record_Id_Piece3_IV();
			}
		}

		public class RPG_O_Output_Program_Record_Id_Piece1_IV : RPG_O_Output_Program_Record_Id_Piece1
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

		public class RPG_O_Output_Program_Record_Id_Piece2_IV : RPG_O_Output_Program_Record_Id_Piece2
		{
			public RPG_O_Output_Program_Record_Id_Piece2_IV()
			{
				blank1 = new RPG_Blanks(7, 15);
				logicalRelation = new RPG_KeywordChoice(16, 18, "AND", "OR");
				blank2 = new RPG_Blanks(19, 22);
			}
		}

		public class RPG_O_Output_Program_Record_Id_Piece3_IV : RPG_O_Output_Program_Record_Id_Piece3
		{
			public RPG_O_Output_Program_Record_Id_Piece3_IV()
			{
				blank1 = new RPG_Blanks(7, 17);
				logicalRelation = new RPG_KeywordChoice(18, 20, "ADD", "DEL");
				blank2 = new RPG_Blanks(21, 22);
			}
		}

		public class RPG_O_Output_Program_Field_Descr_IV : RPG_O_Output_Program_Field_Descr
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
				blank2 = new RPG_Blanks(0, 0); // Not Applicable
			}
		}

		public class RPG_O_Output_External_Record_Id_IV : RPG_O_Output_External_Record_Id
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

		public class RPG_O_Output_External_Record_Id_Piece_IV : RPG_O_Output_External_Record_Id_Piece
		{
			public RPG_O_Output_External_Record_Id_Piece_IV()
			{
				XXpiece1 = new RPG_O_Output_External_Record_Id_Piece1_IV();
				XXpiece2 = new RPG_O_Output_External_Record_Id_Piece2_IV();
				XXpiece3 = new RPG_O_Output_External_Record_Id_Piece3_IV();
			}
		}

		public class RPG_O_Output_External_Record_Id_Piece1_IV : RPG_O_Output_External_Record_Id_Piece1
		{
			public RPG_O_Output_External_Record_Id_Piece1_IV()
			{
				filename = new RPG_Literal(7, 16);
				type = new RPG_KeywordChoice(17, 17, "H", "D", "T", "E");
				release = new RPG_Keyword(18, 18, "R");
				blank2 = new RPG_Blanks(19, 22);
			}
		}

		public class RPG_O_Output_External_Record_Id_Piece2_IV : RPG_O_Output_External_Record_Id_Piece2
		{
			public RPG_O_Output_External_Record_Id_Piece2_IV()
			{
				blank1 = new RPG_Blanks(7, 15);
				logicalRelation = new RPG_KeywordChoice(16, 18, "AND", "OR");
				blank2 = new RPG_Blanks(19, 22);
			}
		}

		public class RPG_O_Output_External_Record_Id_Piece3_IV : RPG_O_Output_External_Record_Id_Piece3
		{
			public RPG_O_Output_External_Record_Id_Piece3_IV()
			{
				blank1 = new RPG_Blanks(7, 17);
				logicalRelation = new RPG_KeywordChoice(18, 20, "ADD", "DEL");
				blank2 = new RPG_Blanks(21, 22);
			}
		}

		public class RPG_O_Output_External_Field_Descr_IV : RPG_O_Output_External_Field_Descr
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

}

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

	public class RPG_O_Output_Specification_III : RPG_O_Output_Specification
	{
		public RPG_O_Output_Specification_III()
		{
			spec = new RPG_O_Output_Spec_III();
		}

		public class RPG_O_Output_Spec_III : RPG_O_Output_Spec
		{
			public RPG_O_Output_Spec_III()
			{
				XXrecordId = new RPG_O_Output_Program_Record_Id_III();
				XXfieldDescr = new RPG_O_Output_Program_Field_Descr_III();
				XXexternalRecordId = new RPG_O_Output_External_Record_Id_III();
				XXexternalFieldDescr = new RPG_O_Output_External_Field_Descr_III();
			}
		}

		public class RPG_O_Output_Program_Record_Id_III : RPG_O_Output_Program_Record_Id
		{
			public RPG_O_Output_Program_Record_Id_III()
			{
				piece = new RPG_O_Output_Program_Record_Id_Piece_III();

				skipBefore = new RPG_Literal(19, 20);
				skipAfter = new RPG_Literal(21, 22);
				indicators = new RPG_Literal(23, 31);
				exceptName = new RPG_Literal(32, 37);
				blank1 = new RPG_Blanks(38, 74);
			}
		}

		public class RPG_O_Output_Program_Record_Id_Piece_III : RPG_O_Output_Program_Record_Id_Piece
		{
			public RPG_O_Output_Program_Record_Id_Piece_III()
			{
				XXpiece1 = new RPG_O_Output_Program_Record_Id_Piece1_III();
				XXpiece2 = new RPG_O_Output_Program_Record_Id_Piece2_III();
				XXpiece3 = new RPG_O_Output_Program_Record_Id_Piece3_III();
			}
		}

		public class RPG_O_Output_Program_Record_Id_Piece1_III : RPG_O_Output_Program_Record_Id_Piece1
		{
			public RPG_O_Output_Program_Record_Id_Piece1_III()
			{
				recordName = new RPG_Literal(7, 14);
				type = new RPG_KeywordChoice(15, 15, "H", "D", "T", "E");
				overflowRelease = new RPG_KeywordChoice(16, 16, "F", "R");
				spaceBefore = new RPG_Number(17, 17);
				spaceAfter = new RPG_Number(18, 18);
				skipBefore = new RPG_Literal(19, 20);
				skipAfter = new RPG_Literal(21, 22);
			}
		}

		public class RPG_O_Output_Program_Record_Id_Piece2_III : RPG_O_Output_Program_Record_Id_Piece2
		{
			public RPG_O_Output_Program_Record_Id_Piece2_III()
			{
				blank1 = new RPG_Blanks(7, 13);
				logicalRelation = new RPG_KeywordChoice(14, 16, "AND", "OR");
				blank2 = new RPG_Blanks(17, 22);
			}
		}

		public class RPG_O_Output_Program_Record_Id_Piece3_III : RPG_O_Output_Program_Record_Id_Piece3
		{
			public RPG_O_Output_Program_Record_Id_Piece3_III()
			{
				blank1 = new RPG_Blanks(7, 15);
				logicalRelation = new RPG_KeywordChoice(16, 18, "ADD", "DEL");
				blank2 = new RPG_Blanks(19, 22);
			}
		}

		public class RPG_O_Output_Program_Field_Descr_III : RPG_O_Output_Program_Field_Descr
		{
			public RPG_O_Output_Program_Field_Descr_III()
			{
				blank1 = new RPG_Blanks(7, 22);
				indicators = new RPG_Literal(23, 31);
				fieldName = new RPG_Literal(32, 37);
				editCodes = new RPG_Literal(38, 38);
				blankAfter = new RPG_Keyword(39, 39, "B");
				endPosition = new RPG_Literal(40, 43);
				dataFormat = new RPG_KeywordChoice(44, 44, "P", "B", "L", "R");
				constantOrEditWord = new RPG_Literal(45, 70);
				blank2 = new RPG_Blanks(71, 74);
			}
		}

		public class RPG_O_Output_External_Record_Id_III : RPG_O_Output_External_Record_Id
		{
			public RPG_O_Output_External_Record_Id_III()
			{
				piece = new RPG_O_Output_External_Record_Id_Piece_III();

				recordName = new RPG_Literal(7, 14);
				indicators = new RPG_Literal(23, 31);
				exceptName = new RPG_Literal(32, 37);
				blank1 = new RPG_Blanks(38, 74);
			}
		}

		public class RPG_O_Output_External_Record_Id_Piece_III : RPG_O_Output_External_Record_Id_Piece
		{
			public RPG_O_Output_External_Record_Id_Piece_III()
			{
				XXpiece1 = new RPG_O_Output_External_Record_Id_Piece1_III();
				XXpiece2 = new RPG_O_Output_External_Record_Id_Piece2_III();
				XXpiece3 = new RPG_O_Output_External_Record_Id_Piece3_III();
			}
		}

		public class RPG_O_Output_External_Record_Id_Piece1_III : RPG_O_Output_External_Record_Id_Piece1
		{
			public RPG_O_Output_External_Record_Id_Piece1_III()
			{
				filename = new RPG_Literal(7, 14);
				type = new RPG_KeywordChoice(15, 15, "H", "D", "T", "E");
				release = new RPG_Keyword(16, 16, "R");
				blank2 = new RPG_Blanks(17, 22);
			}
		}

		public class RPG_O_Output_External_Record_Id_Piece2_III : RPG_O_Output_External_Record_Id_Piece2
		{
			public RPG_O_Output_External_Record_Id_Piece2_III()
			{
				blank1 = new RPG_Blanks(7, 13);
				logicalRelation = new RPG_KeywordChoice(14, 16, "AND", "OR");
				blank2 = new RPG_Blanks(17, 22);
			}
		}

		public class RPG_O_Output_External_Record_Id_Piece3_III : RPG_O_Output_External_Record_Id_Piece3
		{
			public RPG_O_Output_External_Record_Id_Piece3_III()
			{
				blank1 = new RPG_Blanks(7, 15);
				logicalRelation = new RPG_KeywordChoice(16, 18, "ADD", "DEL");
				blank2 = new RPG_Blanks(19, 22);
			}
		}

		public class RPG_O_Output_External_Field_Descr_III : RPG_O_Output_External_Field_Descr
		{
			public RPG_O_Output_External_Field_Descr_III()
			{
				blank1 = new RPG_Blanks(7, 22);
				indicators = new RPG_Literal(23, 31);
				fieldName = new RPG_Literal(32, 37);
				blank2 = new RPG_Blanks(38, 38);
				blankAfter = new RPG_Keyword(39, 39, "B");
				blank3 = new RPG_Blanks(40, 74);
			}
		}
	}

}

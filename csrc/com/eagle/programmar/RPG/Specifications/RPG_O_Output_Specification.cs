// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Specifications
{
	using RPG_Blanks = com.eagle.programmar.RPG.Terminals.RPG_Blanks;
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_KeywordChoice = com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public abstract class RPG_O_Output_Specification : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) RPG_O_Output_Spec spec;
		public RPG_O_Output_Spec spec;

		public class RPG_O_Output_Spec : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_O_Output_Program_Record_Id XXrecordId;
			public RPG_O_Output_Program_Record_Id XXrecordId;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_O_Output_Program_Field_Descr XXfieldDescr;
			public RPG_O_Output_Program_Field_Descr XXfieldDescr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_O_Output_External_Record_Id XXexternalRecordId;
			public RPG_O_Output_External_Record_Id XXexternalRecordId;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE RPG_O_Output_External_Field_Descr XXexternalFieldDescr;
			public RPG_O_Output_External_Field_Descr XXexternalFieldDescr;
		}

		public abstract class RPG_O_Output_Program_Record_Id : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword O = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "O");
			public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) RPG_O_Output_Program_Record_Id_Piece piece;
			public RPG_O_Output_Program_Record_Id_Piece piece;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_Literal skipBefore;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Literal skipAfter;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_Literal indicators;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Literal exceptName;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1;
			public RPG_Blanks blank1;
		}

		public abstract class RPG_O_Output_Program_Field_Descr : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword O = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "O");
			public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1;
			public RPG_Blanks blank1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_Literal indicators;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.RPG.Terminals.RPG_Literal fieldName;
			public RPG_Literal fieldName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_Literal editCodes;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Keyword blankAfter;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_Literal endPosition;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT RPG_KeywordChoice dataFormat;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Literal constantOrEditWord;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2;
			public RPG_Blanks blank2;
		}

		public abstract class RPG_O_Output_External_Record_Id : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword O = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "O");
			public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal recordName;
			public RPG_Literal recordName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) RPG_O_Output_External_Record_Id_Piece piece;
			public RPG_O_Output_External_Record_Id_Piece piece;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Literal indicators;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_Literal exceptName;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1;
			public RPG_Blanks blank1;
		}

		public abstract class RPG_O_Output_External_Field_Descr : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword O = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "O");
			public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1;
			public RPG_Blanks blank1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_Literal indicators;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.RPG.Terminals.RPG_Literal fieldName;
			public RPG_Literal fieldName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2;
			public RPG_Blanks blank2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Keyword blankAfter;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank3;
			public RPG_Blanks blank3;
		}
	}

}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish
{
	using Eaglish_Add_Statement = com.eagle.programmar.Eaglish.Statements.Eaglish_Add_Statement;
	using Eaglish_Array_Data = com.eagle.programmar.Eaglish.Statements.Eaglish_Array_Data;
	using Eaglish_Break_For = com.eagle.programmar.Eaglish.Statements.Eaglish_Break_For;
	using Eaglish_Call_Statement = com.eagle.programmar.Eaglish.Statements.Eaglish_Call_Statement;
	using Eaglish_For_Block = com.eagle.programmar.Eaglish.Statements.Eaglish_For_Block;
	using Eaglish_Function = com.eagle.programmar.Eaglish.Statements.Eaglish_Function;
	using Eaglish_If_Block = com.eagle.programmar.Eaglish.Statements.Eaglish_If_Block;
	using Eaglish_Integer_Data = com.eagle.programmar.Eaglish.Statements.Eaglish_Integer_Data;
	using Eaglish_Print_Statement = com.eagle.programmar.Eaglish.Statements.Eaglish_Print_Statement;
	using Eaglish_Return_Statement = com.eagle.programmar.Eaglish.Statements.Eaglish_Return_Statement;
	using Eaglish_Set_Statement = com.eagle.programmar.Eaglish.Statements.Eaglish_Set_Statement;
	using Eaglish_String_Data = com.eagle.programmar.Eaglish.Statements.Eaglish_String_Data;
	using Eaglish_Subtract_Statement = com.eagle.programmar.Eaglish.Statements.Eaglish_Subtract_Statement;
	using Eaglish_While_Block = com.eagle.programmar.Eaglish.Statements.Eaglish_While_Block;
	using Eaglish_CommentEoln = com.eagle.programmar.Eaglish.Terminals.Eaglish_CommentEoln;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Eaglish_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Add_Statement XXaddStatement;
		public Eaglish_Add_Statement XXaddStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Array_Data XXarrayStatement;
		public Eaglish_Array_Data XXarrayStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Break_For XXbreakFor;
		public Eaglish_Break_For XXbreakFor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Call_Statement XXcalLStatemen;
		public Eaglish_Call_Statement XXcalLStatemen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_CommentEoln XXcomment;
		public Eaglish_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_For_Block XXforBlock;
		public Eaglish_For_Block XXforBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Function XXfunctionBlock;
		public Eaglish_Function XXfunctionBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_If_Block XXifBlock;
		public Eaglish_If_Block XXifBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Integer_Data XXintegerData;
		public Eaglish_Integer_Data XXintegerData;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Print_Statement XXprintStatement;
		public Eaglish_Print_Statement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Return_Statement XXreturnStatement;
		public Eaglish_Return_Statement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Set_Statement XXsetStatement;
		public Eaglish_Set_Statement XXsetStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_String_Data XXstringData;
		public Eaglish_String_Data XXstringData;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Subtract_Statement XXsubtractStatement;
		public Eaglish_Subtract_Statement XXsubtractStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_While_Block XXwhileBlock;
		public Eaglish_While_Block XXwhileBlock;
	}

}

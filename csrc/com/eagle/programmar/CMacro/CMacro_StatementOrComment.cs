// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

namespace com.eagle.programmar.CMacro
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMacro_Define_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
	using CMacro_Error_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Error_Statement;
	using CMacro_IfDef_Statement = com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement;
	using CMacro_IfDefCPlusPlus = com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement.CMacro_IfDefCPlusPlus;
	using CMacro_If_Statement = com.eagle.programmar.CMacro.Statements.CMacro_If_Statement;
	using CMacro_Include_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Include_Statement;
	using CMacro_LineNumber_Statement = com.eagle.programmar.CMacro.Statements.CMacro_LineNumber_Statement;
	using CMacro_Pragma_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Pragma_Statement;
	using CMacro_Region_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Region_Statement;
	using CMacro_Undef_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Undef_Statement;
	using CMacro_Comment = com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
	using CMacro_EndOfLine = com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_StatementOrComment : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CMacro_EndOfLine eoln1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMacro_StmtBody stmt;
		public CMacro_StmtBody stmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.CMacro.Terminals.CMacro_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine endOfLine;
		public CMacro_EndOfLine endOfLine;

		public class CMacro_StmtBody : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST CMacro_IfDefCPlusPlus XXifdefCPlusPlus;
			public CMacro_IfDefCPlusPlus XXifdefCPlusPlus;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Define_Statement XXdefineStatement;
			public CMacro_Define_Statement XXdefineStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Error_Statement XXerrorStatement;
			public CMacro_Error_Statement XXerrorStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_If_Statement XXifStatement;
			public CMacro_If_Statement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_IfDef_Statement XXifdefStatement;
			public CMacro_IfDef_Statement XXifdefStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Include_Statement XXincludeStatement;
			public CMacro_Include_Statement XXincludeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_LineNumber_Statement XXlineNumberStatement;
			public CMacro_LineNumber_Statement XXlineNumberStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Statement XXpragmaStatement;
			public CMacro_Pragma_Statement XXpragmaStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Region_Statement XXregionStatement;
			public CMacro_Region_Statement XXregionStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Undef_Statement XXundefStatement;
			public CMacro_Undef_Statement XXundefStatement;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// Don't do anything right now ... deal with it later
		}
	}

}

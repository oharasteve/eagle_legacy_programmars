// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi
{
	using Delphi_Assignment = com.eagle.programmar.Delphi.Statements.Delphi_Assignment;
	using Delphi_BeginEnd = com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
	using Delphi_Break_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Break_Statement;
	using Delphi_Case_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Case_Statement;
	using Delphi_Close_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Close_Statement;
	using Delphi_ExpressionStatement = com.eagle.programmar.Delphi.Statements.Delphi_ExpressionStatement;
	using Delphi_For_Statement = com.eagle.programmar.Delphi.Statements.Delphi_For_Statement;
	using Delphi_GetDateTime_Statement = com.eagle.programmar.Delphi.Statements.Delphi_GetDateTime_Statement;
	using Delphi_Halt_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Halt_Statement;
	using Delphi_If_Statement = com.eagle.programmar.Delphi.Statements.Delphi_If_Statement;
	using Delphi_Inherited_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Inherited_Statement;
	using Delphi_Raise_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Raise_Statement;
	using Delphi_Readln_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Readln_Statement;
	using Delphi_Repeat_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Repeat_Statement;
	using Delphi_Rewrite_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Rewrite_Statement;
	using Delphi_Try_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Try_Statement;
	using Delphi_While_Statement = com.eagle.programmar.Delphi.Statements.Delphi_While_Statement;
	using Delphi_With_Statement = com.eagle.programmar.Delphi.Statements.Delphi_With_Statement;
	using Delphi_Writeln_Statement = com.eagle.programmar.Delphi.Statements.Delphi_Writeln_Statement;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Delphi_Statement : TokenChooser, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
		public PunctuationSemicolon XXsemicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Assignment XXassignment;
		public Delphi_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_BeginEnd XXbeginEnd;
		public Delphi_BeginEnd XXbeginEnd;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Break_Statement XXbreakStatement;
		public Delphi_Break_Statement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Case_Statement XXcaseStatement;
		public Delphi_Case_Statement XXcaseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Close_Statement XXcloseStatement;
		public Delphi_Close_Statement XXcloseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_For_Statement XXforStatement;
		public Delphi_For_Statement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_GetDateTime_Statement XXgetDateTimeStatement;
		public Delphi_GetDateTime_Statement XXgetDateTimeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Halt_Statement XXhaltStatement;
		public Delphi_Halt_Statement XXhaltStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_If_Statement XXifStatement;
		public Delphi_If_Statement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Inherited_Statement XXinheritedStatement;
		public Delphi_Inherited_Statement XXinheritedStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Raise_Statement XXraiseStatement;
		public Delphi_Raise_Statement XXraiseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Readln_Statement XXreadlnStatement;
		public Delphi_Readln_Statement XXreadlnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Repeat_Statement XXrepeat_Statement;
		public Delphi_Repeat_Statement XXrepeat_Statement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Rewrite_Statement XXrewriteStatement;
		public Delphi_Rewrite_Statement XXrewriteStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Try_Statement XXtryStatement;
		public Delphi_Try_Statement XXtryStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_While_Statement XXwhile_Statement;
		public Delphi_While_Statement XXwhile_Statement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_With_Statement XXwith_Statement;
		public Delphi_With_Statement XXwith_Statement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Delphi_Writeln_Statement XXwritelnStatement;
		public Delphi_Writeln_Statement XXwritelnStatement;

		// This guy has to be last
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Delphi_ExpressionStatement XXexpressionStatement;
		public Delphi_ExpressionStatement XXexpressionStatement;
	}

}

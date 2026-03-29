// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

namespace com.eagle.programmar.VB.Statements
{
	using VB_Identifier_Reference = com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_Number = com.eagle.programmar.VB.Terminals.VB_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class VB_OnStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/on-error-statement") com.eagle.programmar.VB.Terminals.VB_Keyword ON = new com.eagle.programmar.VB.Terminals.VB_Keyword("on");
		public @DOC("statements/on-error-statement") VB_Keyword ON = new VB_Keyword("on");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Keyword ERROR = new com.eagle.programmar.VB.Terminals.VB_Keyword("error");
		public VB_Keyword ERROR = new VB_Keyword("error");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) VB_OnWhat onWhat;
		public VB_OnWhat onWhat;

		public static class VB_OnWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class VB_OnResume extends com.eagle.tokens.TokenSequence
			public static class VB_OnResume extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword RESUME = new com.eagle.programmar.VB.Terminals.VB_Keyword("resume");
				public VB_Keyword RESUME = new VB_Keyword("resume");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Keyword NEXT = new com.eagle.programmar.VB.Terminals.VB_Keyword("next");
				public VB_Keyword NEXT = new VB_Keyword("next");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class VB_OnGotoZero extends com.eagle.tokens.TokenSequence
			public static class VB_OnGotoZero extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword GOTO = new com.eagle.programmar.VB.Terminals.VB_Keyword("goto");
				public VB_Keyword GOTO = new VB_Keyword("goto");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Number zero;
				public VB_Number zero;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class VB_OnGotoLabel extends com.eagle.tokens.TokenSequence
			public static class VB_OnGotoLabel extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword GOTO = new com.eagle.programmar.VB.Terminals.VB_Keyword("goto");
				public VB_Keyword GOTO = new VB_Keyword("goto");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Symbols.VB_Identifier_Reference lbl;
				public VB_Identifier_Reference lbl;
			}
		}
	}

}

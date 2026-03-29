// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

namespace com.eagle.programmar.PLI.Statements
{
	using PLI_Label = com.eagle.programmar.PLI.PLI_Label;
	using PLI_Signal = com.eagle.programmar.PLI.PLI_Signal;
	using PLI_Statement = com.eagle.programmar.PLI.PLI_Statement;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_OnStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("7.36") com.eagle.programmar.PLI.Terminals.PLI_Keyword ON = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("ON");
		public @DOC("7.36") PLI_Keyword ON = new PLI_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.PLI.PLI_Signal, com.eagle.tokens.punctuation.PunctuationComma> signals;
		public SeparatedList<PLI_Signal, PunctuationComma> signals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_Keyword SNAP = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("SNAP");
		public @OPT PLI_Keyword SNAP = new PLI_Keyword("SNAP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) PLI_OnAction action;
		public PLI_OnAction action;

		public static class PLI_OnAction extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Statement XXstmt;
			public PLI_Statement XXstmt;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_On_Action_System extends com.eagle.tokens.TokenSequence
			public static class PLI_On_Action_System extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword SYSTEM = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("SYSTEM");
				public PLI_Keyword SYSTEM = new PLI_Keyword("SYSTEM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public PunctuationSemicolon semicolon;
			}
		}
	}

}

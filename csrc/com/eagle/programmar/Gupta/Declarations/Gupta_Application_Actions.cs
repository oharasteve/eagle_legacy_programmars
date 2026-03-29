// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta.Declarations
{
	using Gupta_Declaration = com.eagle.programmar.Gupta.Gupta_Declaration;
	using Gupta_Statement = com.eagle.programmar.Gupta.Gupta_Statement;
	using Gupta_Keyword = com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
	using Gupta_KeywordChoice = com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Gupta_Application_Actions : Gupta_Declaration
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Application = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Application");
		public Gupta_Keyword Application = new Gupta_Keyword("Application");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword Actions = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("Actions");
		public Gupta_Keyword Actions = new Gupta_Keyword("Actions");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<Gupta_OnEvent> onEvents;
		public TokenList<Gupta_OnEvent> onEvents;

		public class Gupta_OnEvent : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Gupta.Terminals.Gupta_Keyword On = new com.eagle.programmar.Gupta.Terminals.Gupta_Keyword("On");
			public Gupta_Keyword On = new Gupta_Keyword("On");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice eventName = new com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice("SAM_AppStartup");
			public Gupta_KeywordChoice eventName = new Gupta_KeywordChoice("SAM_AppStartup");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Gupta.Gupta_Statement> actions;
			public TokenList<Gupta_Statement> actions;
		}
	}

}

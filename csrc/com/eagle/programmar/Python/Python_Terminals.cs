// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 12, 2014

namespace com.eagle.programmar.Python
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_HexNumber = com.eagle.programmar.Python.Terminals.Python_HexNumber;
	using Python_Identifier = com.eagle.programmar.Python.Terminals.Python_Identifier;
	using Python_KeywordChoice = com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
	using Python_Literal = com.eagle.programmar.Python.Terminals.Python_Literal;
	using Python_Number = com.eagle.programmar.Python.Terminals.Python_Number;
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;

	public class Python_Terminals : AbstractLanguage
	{
		public Python_Terminals() : base("Python Terminals", new Python_Terminal_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return null;
			}
		}

// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @Override public com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken> getTerminals()
		public override TokenList<AbstractToken> Terminals
		{
			get
			{
				return _terminals;
			}
		}

		// Just collect a list of all the terminals
		public TokenList<Python_Terminal> _terminals;

		// Need to use this, so there are no punctuation exceptions like "+="
		public class Python_Terminal_Syntax : Python_Multiline_Syntax
		{
			public Python_Terminal_Syntax()
			{
				_punctuationExceptions = null;
			}
		}

		public class Python_Terminal : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Comment XXcomment;
			public Python_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_HexNumber XXhex;
			public Python_HexNumber XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Number XXnumber;
			public Python_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Literal XXliteral;
			public Python_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_KeywordChoice XXkeywords = new com.eagle.programmar.Python.Terminals.Python_KeywordChoice(getSyntax().allReservedWords());
			public Python_KeywordChoice XXkeywords = new Python_KeywordChoice(getSyntax().allReservedWords());
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Identifier XXid;
			public Python_Identifier XXid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_PunctuationChoice XXpuncts = new com.eagle.programmar.Python.Terminals.Python_PunctuationChoice("+", "-", "*", "/", "%", ",", ".", "=", "<", ">", ":", ";", "_", "!", "@", "(", ")", "[", "]", "{", "}");
			public Python_PunctuationChoice XXpuncts = new Python_PunctuationChoice("+", "-", "*", "/", "%", ",", ".", "=", "<", ">", ":", ";", "_", "!", "@", "(", ")", "[", "]", "{", "}");
		}
	}

}

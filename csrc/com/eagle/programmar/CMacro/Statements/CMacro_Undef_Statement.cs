// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

namespace com.eagle.programmar.CMacro.Statements
{
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_Identifier_Reference = com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_Undef_Statement : TokenSequence, CMacro_Processable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("Undefining-and-Redefining-Macros.html") com.eagle.programmar.CMacro.Terminals.CMacro_Keyword UNDEF = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("undef");
		public @DOC("Undefining-and-Redefining-Macros.html") CMacro_Keyword UNDEF = new CMacro_Keyword("undef");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference var;
		public CMacro_Identifier_Reference var;

		public bool processMacro(CMacro_Preprocess preprocessor)
		{
			string id = var.getValue();
			// System.out.println("#undef " + id + " ...");
			preprocessor._symbolTable.removeSymbol(id);
			return true;
		}
	}

}

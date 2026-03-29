// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 10, 2014

namespace com.eagle.programmar.CMacro.Statements
{
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_KeywordChoice = com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using CMacro_RestOfLine = com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_Error_Statement : TokenSequence, CMacro_Processable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice ERROR = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("error", "warn", "warning");
		public CMacro_KeywordChoice ERROR = new CMacro_KeywordChoice("error", "warn", "warning");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CMacro_RestOfLine message;
		public  OPT; // Just keep it as a String

		public virtual bool processMacro(CMacro_Preprocess preprocessor)
		{
			// Nothing to do
			return false; // false means we didn't change anything
		}
	}

}

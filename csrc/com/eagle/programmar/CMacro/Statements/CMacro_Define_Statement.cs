// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

namespace com.eagle.programmar.CMacro.Statements
{
	using EagleToken = com.eagle.math.EagleToken;
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_Define_Definition = com.eagle.programmar.CMacro.Symbols.CMacro_Define_Definition;
	using CMacro_Parameter_Definition = com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
	using CMacro_Comment = com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using CMacro_RestOfLine = com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CMacro_Define_Statement : TokenSequence, CMacro_Processable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("Macros.html") com.eagle.programmar.CMacro.Terminals.CMacro_Keyword DEFINE = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("define");
		public @DOC("Macros.html") CMacro_Keyword DEFINE = new CMacro_Keyword("define");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.Symbols.CMacro_Define_Definition var;
		public CMacro_Define_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMacro_Comment comment1;
		public @OPT CMacro_Comment comment1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CMacro_Parameters params;
		public @OPT CMacro_Parameters @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT CMacro_RestOfLine value;
		public @OPT CMacro_RestOfLine value; // Just keep it as a String
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT CMacro_Comment comment2;
		public @OPT CMacro_Comment comment2;

		public static class CMacro_Parameters extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<CMacro_Param, com.eagle.tokens.punctuation.PunctuationComma> params;
			public @OPT SeparatedList<CMacro_Param, PunctuationComma> @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;

			public static class CMacro_Param extends TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Parameter_Definition XXvar;
				public CMacro_Parameter_Definition XXvar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Punctuation XXdotDotDot = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation("...");
				public CMacro_Punctuation XXdotDotDot = new CMacro_Punctuation("...");
			}
		}

		public bool processMacro(CMacro_Preprocess preprocessor)
		{
			string macroName = var.getValue();
			// System.out.println("#define " + macroName + " ...");
			if (preprocessor._project == null || preprocessor._project.expandMacro(macroName))
			{
				// The -1 means no subscript
				preprocessor._symbolTable.setSymbol(var, macroName, -1, new EagleToken(this));
			}
			return true; // No need to add these to the file
		}
	}

}

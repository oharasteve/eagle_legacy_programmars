// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

namespace com.eagle.programmar.CMacro.Statements
{

	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_Comment = com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
	using CMacro_IncludeSys = com.eagle.programmar.CMacro.Terminals.CMacro_IncludeSys;
	using CMacro_KeywordChoice = com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
	using CMacro_Literal = com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_Include_Statement : TokenSequence, CMacro_Processable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("Include-Syntax.html") com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice INCLUDE = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("include", "include_next", "import");
		public @DOC("Include-Syntax.html") CMacro_KeywordChoice INCLUDE = new CMacro_KeywordChoice("include", "include_next", "import");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CMacro_IncludeWhat what;
		public CMacro_IncludeWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.CMacro.Terminals.CMacro_Comment> comments;
		public @OPT TokenList<CMacro_Comment> comments;

		public static class CMacro_IncludeWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Literal XXfilename;
			public CMacro_Literal XXfilename;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_IncludeSys XXsys;
			public CMacro_IncludeSys XXsys;
		}

		public bool processMacro(CMacro_Preprocess preprocessor)
		{
			AbstractToken which = what.getWhich();
			if (!(which is CMacro_Literal))
			{
				return false;
			}
			string fileName = ((CMacro_Literal) which).getValue();
			EagleFileReader macro;
			try
			{
				if (fileName.StartsWith("\"", StringComparison.Ordinal) && fileName.EndsWith("\"", StringComparison.Ordinal))
				{
					int len = fileName.Length;
					fileName = fileName.Substring(1, (len - 1) - 1);
				}
				macro = preprocessor._findInclude.findIncludeFile("", fileName);
			}
			catch (IOException)
			{
				return false;
			}
			if (macro == null)
			{
				return false;
			}

			try
			{
				CMacro_Preprocess innerPreprocessor = new CMacro_Preprocess(preprocessor);
				EagleFileReader macroLines = innerPreprocessor.preprocessFile(preprocessor._parser, macro, preprocessor._depth + 1);
				if (macroLines == null)
				{
					return false;
				}
				foreach (EagleLineReader line in macroLines.lines())
				{
					preprocessor.addLine(line);
				}
			}
			catch (Exception ex)
			{
				Console.Error.WriteLine("Failed parsing " + fileName);
				ex.printStackTrace(System.err);
				// Failed -- just leave the #include alone
				return true;
			}

			return true;
		}
	}

}

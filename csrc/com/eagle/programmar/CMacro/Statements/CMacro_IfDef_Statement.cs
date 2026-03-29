// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

namespace com.eagle.programmar.CMacro.Statements
{
	using EagleValue = com.eagle.math.EagleValue;
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_Expression = com.eagle.programmar.CMacro.CMacro_Expression;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_Element = com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
	using CMacro_Identifier_Reference = com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
	using CMacro_Comment = com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
	using CMacro_EndOfLine = com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_KeywordChoice = com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
	using CMacro_Literal = com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class CMacro_IfDef_Statement : TokenSequence, CMacro_Processable
	{
		private const string IFDEF = "ifdef";
		private const string IFNDEF = "ifndef";

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("Ifdef.html") com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice IFDEFNDEF = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice(IFDEF, IFNDEF);
		public @DOC("Ifdef.html") CMacro_KeywordChoice IFDEFNDEF = new CMacro_KeywordChoice(IFDEF, IFNDEF);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference ref;
		public CMacro_Identifier_Reference @ref;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMacro_Comment comment1;
		public @OPT CMacro_Comment comment1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine eoln1;
		public CMacro_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element> elements;
		public @OPT TokenList<CMacro_Element> elements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<CMacro_IfDefElif> ifElif;
		public @OPT TokenList<CMacro_IfDefElif> ifElif;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT CMacro_IfDefElse ifElse;
		public @OPT CMacro_IfDefElse ifElse;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT CMacro_EndOfLine eoln2;
		public @OPT CMacro_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound2 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound2 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ENDIF = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("endif");
		public CMacro_Keyword ENDIF = new CMacro_Keyword("endif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT CMacro_Comment comment2;
		public @OPT CMacro_Comment comment2;

		public static class CMacro_IfDefElif extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound1 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
			public CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ELIF = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("elif");
			public CMacro_Keyword ELIF = new CMacro_Keyword("elif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.CMacro_Expression expr;
			public CMacro_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMacro_Comment comment;
			public @OPT CMacro_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CMacro_EndOfLine eoln;
			public @OPT CMacro_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element> elements;
			public @OPT TokenList<CMacro_Element> elements;
		}

		public static class CMacro_IfDefElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CMacro_EndOfLine eoln1;
			public @OPT CMacro_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound1 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
			public CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ELSE = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("else");
			public CMacro_Keyword ELSE = new CMacro_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMacro_Comment comment;
			public @OPT CMacro_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine eoln2;
			public CMacro_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element> elements;
			public @OPT TokenList<CMacro_Element> elements;
		}

		public static class CMacro_IfDefCPlusPlus extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
			public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("Ifdef.html") com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ifdef1 = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("ifdef");
			public @DOC("Ifdef.html") CMacro_Keyword ifdef1 = new CMacro_Keyword("ifdef");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword CPLUSPLUS1 = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("__cplusplus");
			public CMacro_Keyword CPLUSPLUS1 = new CMacro_Keyword("__cplusplus");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine eoln1;
			public CMacro_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword EXTERN = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("extern");
			public CMacro_Keyword EXTERN = new CMacro_Keyword("extern");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CMacro.Terminals.CMacro_Literal C;
			public CMacro_Literal C;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine eoln2;
			public CMacro_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound2 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
			public CMacro_Punctuation pound2 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ENDIF1 = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("endif");
			public CMacro_Keyword ENDIF1 = new CMacro_Keyword("endif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine eoln3;
			public CMacro_EndOfLine eoln3;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT TokenList<com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element> elements;
			public @OPT TokenList<CMacro_Element> elements;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound3 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
			public CMacro_Punctuation pound3 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) @DOC("Ifdef.html") com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ifdef2 = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("ifdef");
			public @DOC("Ifdef.html") CMacro_Keyword ifdef2 = new CMacro_Keyword("ifdef");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword CPLUSPLUS2 = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("__cplusplus");
			public CMacro_Keyword CPLUSPLUS2 = new CMacro_Keyword("__cplusplus");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine eoln4;
			public CMacro_EndOfLine eoln4;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(170) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(180) @OPT CMacro_Comment comment;
			public @OPT CMacro_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(190) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine eoln5;
			public CMacro_EndOfLine eoln5;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(200) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound4 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
			public CMacro_Punctuation pound4 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(210) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ENDIF2 = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("endif");
			public CMacro_Keyword ENDIF2 = new CMacro_Keyword("endif");
		}

		// Need this for switching languages from CMacro to C
	//	public static class CMacro_IfDefElement extends TokenSequence
	//	{
	//		public @S(10) @SYNTAX(C_Syntax.class) C_StatementOrComment element;
	//	}

		public bool processMacro(CMacro_Preprocess preprocessor)
		{
			string macroName = @ref.getValue();
			// System.out.println("#" + IFDEFNDEF + " " + macroName + " ...");
			bool isIfDef = IFDEFNDEF.ToString().Equals(IFDEF);
			EagleValue value = preprocessor._symbolTable.findSymbol(macroName);

			TokenList<CMacro_Element> whichElements;
			if (value == null ^ isIfDef) // XOR
			{
				whichElements = elements;
			}
			else if (ifElse.isPresent())
			{
				whichElements = ifElse.elements;
			}
			else
			{
				return true; // No "#else" given.
			}

			foreach (AbstractToken token in whichElements._elements)
			{
				if (token is CMacro_Element)
				{
					CMacro_Element element = (CMacro_Element) token;
					preprocessor.preprocessCMacroElement(preprocessor._parser, element);
				}
			}

			return true; // Always change the file
		}
	}

}

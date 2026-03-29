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
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_Expression = com.eagle.programmar.CMacro.CMacro_Expression;
	using CMacro_Interpreter = com.eagle.programmar.CMacro.CMacro_Interpreter;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_Element = com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
	using CMacro_Comment = com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
	using CMacro_EndOfLine = com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_If_Statement : TokenSequence, CMacro_Processable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound1 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("If.html") com.eagle.programmar.CMacro.Terminals.CMacro_Keyword IF = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("if");
		public @DOC("If.html") CMacro_Keyword IF = new CMacro_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMacro.CMacro_Expression expr;
		public CMacro_Expression expr;
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
// ORIGINAL LINE: public @S(70) @OPT TokenList<CMacro_IfElif> ifElif;
		public @OPT TokenList<CMacro_IfElif> ifElif;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT CMacro_IfElse ifElse;
		public @OPT CMacro_IfElse ifElse;
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

		public static class CMacro_IfElif extends TokenSequence
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

		public static class CMacro_IfElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound1 = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
			public CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword ELSE = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("else");
			public CMacro_Keyword ELSE = new CMacro_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CMacro_Comment comment;
			public @OPT CMacro_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CMacro_EndOfLine eoln;
			public @OPT CMacro_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element> elements;
			public @OPT TokenList<CMacro_Element> elements;
		}

	//	// Need this for switching languages from CMacro to C
	//	public static class CMacro_IfElement extends TokenSequence
	//	{
	//		public @S(10) @SYNTAX(C_Syntax.class) C_StatementOrComment element;
	//	}

		public bool processMacro(CMacro_Preprocess preprocessor)
		{
			bool isTrue = getBooleanValue(expr, preprocessor);
			// System.out.println("$$$$$$$$$$$$$$$$$$ " + expr.showText() + " is " +
			// isTrue);
			TokenList<CMacro_Element> whichElements = null;
			if (isTrue)
			{
				whichElements = elements; // Use the "then" clause
			}
			else
			{
				// Check for #elif clauses
				foreach (CMacro_IfElif elif in ifElif._elements)
				{
					if (getBooleanValue(elif.expr, preprocessor))
					{
						whichElements = elif.elements;
						break;
					}
				}
			}

			// Well, maybe there is a #else clause
			if (whichElements == null && ifElse.isPresent())
			{
				whichElements = ifElse.elements;
			}

			// Dang, nothing matches, and no "else" clause
			if (whichElements == null)
			{
				return true;
			}

			foreach (AbstractToken token in whichElements._elements)
			{
				// System.out.println(" $$$$$$$$$$$$$$$ " + token.showText());
				if (token is CMacro_Element)
				{
					CMacro_Element element = (CMacro_Element) token;
					preprocessor.preprocessCMacroElement(preprocessor._parser, element);
				}
				else
				{
					throw new Exception("Didn't expect " + token + " here");
				}
			}
			return true; // Always change the file
		}

		//////////////////////////////////////////////////////////////
		// Evaluation routine, for macros

		private static bool getBooleanValue(CMacro_Expression cond, CMacro_Preprocess preprocessor)
		{
			AbstractToken which = cond.getWhich();
			if (!(which is EagleRunnable))
			{
				throw new Exception("Need to implement EagleRunnable for " + which);
			}

			EagleRunnable runnable = (EagleRunnable) which;
			CMacro_Interpreter interpreter = new CMacro_Interpreter(preprocessor._parser, preprocessor._symbolTable);
			runnable.interpret(interpreter);
			bool val = interpreter.getBoolValue(cond);
			return val;
		}
	}

}

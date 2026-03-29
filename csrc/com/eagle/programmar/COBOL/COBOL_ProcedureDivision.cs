// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

namespace com.eagle.programmar.COBOL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using COBOL_ParagraphHeader = com.eagle.programmar.COBOL.COBOL_Paragraph.COBOL_ParagraphHeader;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_ProcedureDivision : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PROCEDURE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PROCEDURE");
		public COBOL_Keyword PROCEDURE = new COBOL_Keyword("PROCEDURE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DIVISION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DIVISION");
		public COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_ProcedureUsing using;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_ProcedureChaining chaining;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_Keyword WINAPI = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WINAPI");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT COBOL_Declaratives declaratives;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.TokenList<COBOL_Section> sections;
		public TokenList<COBOL_Section> sections;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT COBOL_ParagraphHeader extraPara;
		public  OPT;

		public class COBOL_ProcedureUsing : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword USING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("USING");
			public COBOL_Keyword USING = new COBOL_Keyword("USING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_ProcedureUsingBy by;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
			public COBOL_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<COBOL_ProcedureUsingWhat> uses;
			public  OPT;

			public class COBOL_ProcedureUsingBy : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
				public COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice VALUE = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
				public COBOL_KeywordChoice VALUE = new COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
			}

			public class COBOL_ProcedureUsingWhat : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_ProcedureUsingBy by;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
			}
		}

		public class COBOL_ProcedureChaining : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CHAINING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CHAINING");
			public COBOL_Keyword CHAINING = new COBOL_Keyword("CHAINING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
			public COBOL_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<COBOL_ProcedureChainingWhat> chain;
			public  OPT;

			public class COBOL_ProcedureChainingWhat : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
			}
		}

		public class COBOL_Declaratives : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DECLARATIVES1 = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DECLARATIVES");
			public COBOL_Keyword DECLARATIVES1 = new COBOL_Keyword("DECLARATIVES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
			public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_Section section;
			public COBOL_Section section;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword END = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END");
			public COBOL_Keyword END = new COBOL_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DECLARATIVES2 = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DECLARATIVES");
			public COBOL_Keyword DECLARATIVES2 = new COBOL_Keyword("DECLARATIVES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
			public PunctuationPeriod dot2;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(sections.first());
		}

		public virtual void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			foreach (COBOL_Section section in sections._elements)
			{
				section.transform(transformer, generator);
			}
		}
	}

}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_StatementOrComment = com.eagle.programmar.COBOL.COBOL_StatementOrComment;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_PerformStatement : COBOL_AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsperf.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PERFORM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PERFORM");
		public @DOC("rlpsperf.htm") COBOL_Keyword PERFORM = new COBOL_Keyword("PERFORM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_PerformTestWhen testWhen;
		public @OPT COBOL_PerformTestWhen testWhen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_PerformWhat what;
		public @OPT COBOL_PerformWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword ENDPERFORM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-PERFORM");
		public @OPT COBOL_Keyword ENDPERFORM = new COBOL_Keyword("END-PERFORM");

		public static class COBOL_PerformTestWhen extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
			public @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TEST = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TEST");
			public COBOL_Keyword TEST = new COBOL_Keyword("TEST");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice when = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("BEFORE", "AFTER");
			public COBOL_KeywordChoice when = new COBOL_KeywordChoice("BEFORE", "AFTER");
		}

		public static class COBOL_Paragraph_or_Section_Thru extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice THRU = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("THRU", "THROUGH");
			public COBOL_KeywordChoice THRU = new COBOL_KeywordChoice("THRU", "THROUGH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference performEndParagraph;
			public COBOL_Identifier_Reference performEndParagraph;
		}

		public static class COBOL_PerformWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_PerformParagraph XXperformParagraph;
			public COBOL_PerformParagraph XXperformParagraph;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_PerformInline XXperformInline;
			public COBOL_PerformInline XXperformInline;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class COBOL_PerformNothing extends com.eagle.tokens.TokenSequence
			public static class COBOL_PerformNothing extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<COBOL_PerformClause> clauseList;
				public TokenList<COBOL_PerformClause> clauseList;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class COBOL_PerformTimes extends com.eagle.tokens.TokenSequence
			public static class COBOL_PerformTimes extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference performStartParagraph;
				public COBOL_Identifier_Reference performStartParagraph;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Paragraph_or_Section_Thru performThrough;
				public @OPT COBOL_Paragraph_or_Section_Thru performThrough;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression times;
				public COBOL_Expression times;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TIMES = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TIMES");
				public COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_PerformTimesInline extends com.eagle.tokens.TokenSequence
			public static class COBOL_PerformTimesInline extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Number times;
				public COBOL_Number times;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TIMES = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TIMES");
				public COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_StatementOrComment> statements;
				public TokenList<COBOL_StatementOrComment> statements;
			}
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (testWhen != null && testWhen.isPresent())
			{
				throw new Exception("Can't handle PERFORM TEST yet");
			}

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			if (what.getWhich() is COBOL_PerformParagraph)
			{
				COBOL_PerformParagraph para = (COBOL_PerformParagraph) what.getWhich();
				result = para.interpretStatement(interpreter);
			}
			else if (what.getWhich() is COBOL_PerformInline)
			{
				COBOL_PerformInline inline = (COBOL_PerformInline) what.getWhich();
				result = inline.interpretStatement(interpreter);
			}
			else
			{
				throw new Exception("Can only handle simple PERFORMs right now, not: " + what.getWhich());
			}

			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (what.getWhich() is COBOL_PerformParagraph)
			{
				COBOL_PerformParagraph para = (COBOL_PerformParagraph) what.getWhich();
				return para.transformStatement(transformer, generator);
			}
			if (what.getWhich() is COBOL_PerformInline)
			{
				COBOL_PerformInline inline = (COBOL_PerformInline) what.getWhich();
				return inline.transformStatement(transformer, generator);
			}
			throw new Exception("Unable to handle PERFORM: " + this);
		}
	}

}

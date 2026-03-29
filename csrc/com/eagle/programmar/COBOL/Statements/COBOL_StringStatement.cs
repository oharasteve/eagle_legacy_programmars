// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;
using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

namespace com.eagle.programmar.COBOL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Variable = com.eagle.programmar.COBOL.COBOL_Variable;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_HexNumber = com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_StringStatement : COBOL_AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsstri.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword STRING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("STRING");
		public @DOC("rlpsstri.htm") COBOL_Keyword STRING = new COBOL_Keyword("STRING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_StringWhat> elements;
		public TokenList<COBOL_StringWhat> elements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INTO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INTO");
		public COBOL_Keyword INTO = new COBOL_Keyword("INTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<COBOL_StringPiece> pieces;
		public TokenList<COBOL_StringPiece> pieces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_StringWith with;
		public @OPT COBOL_StringWith with;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_Keyword ENDSTRING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-STRING");
		public @OPT COBOL_Keyword ENDSTRING = new COBOL_Keyword("END-STRING");

		public static class COBOL_StringWhat extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression expr;
			public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_StringDelimited delimit;
			public @OPT COBOL_StringDelimited delimit;

			public static class COBOL_StringDelimited extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DELIMITED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DELIMITED");
				public COBOL_Keyword DELIMITED = new COBOL_Keyword("DELIMITED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
				public @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_StringDelimitByWhat what;
				public COBOL_StringDelimitByWhat what;

				public static class COBOL_StringDelimitByWhat extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXSIZE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SIZE");
					public COBOL_Keyword XXSIZE = new COBOL_Keyword("SIZE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_HexNumber XXhex;
					public COBOL_HexNumber XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXliteral;
					public COBOL_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_StringDelimitSpaces XXstringDelimitSpaces;
					public COBOL_StringDelimitSpaces XXstringDelimitSpaces;
				}
			}
		}

		public static class COBOL_StringDelimitSpaces extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
			public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice SPACES = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("SPACE", "SPACES");
			public COBOL_KeywordChoice SPACES = new COBOL_KeywordChoice("SPACE", "SPACES");
		}

		public static class COBOL_StringPiece extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference intoVar;
			public COBOL_Identifier_Reference intoVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_StringCount count;
			public @OPT COBOL_StringCount count;

			public static class COBOL_StringCount extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword COUNT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("COUNT");
				public COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IN");
				public COBOL_Keyword IN = new COBOL_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference countVar;
				public COBOL_Identifier_Reference countVar;
			}
		}

		public static class COBOL_StringWith extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
			public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword POINTER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("POINTER");
			public COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference withPointer;
			public COBOL_Identifier_Reference withPointer;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, STRING.getValue(), STRING);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			if (pieces.size() != 1)
			{
				throw new Exception("Can only handle one STRING result");
			}
			if (with != null && with.isPresent())
			{
				throw new Exception("Cannot handle POINTER yet");
			}

			StringBuilder result = new StringBuilder();
			foreach (COBOL_StringWhat what in elements._elements)
			{
				if (what.delimit != null && what.delimit.isPresent())
				{
					AbstractToken which = what.delimit.what.getWhich();
					if (!(which is COBOL_StringDelimitSpaces))
					{
						throw new Exception("Can only DELIMIT BY SPACES");
					}
				}

				EagleValue val = interpreter.getEagleValue(what.expr);
				string piece = val.forceStringValue();
				argTypes.Add(val.getType());
				result.Append(piece);
			}
			_metrics.calledWith(argTypes);

			COBOL_StringPiece strPiece = pieces._elements.get(0);
			interpreter.setSymbol(strPiece, strPiece.intoVar.getValue(), new EagleString(result.ToString()));
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Oper2Types types = null;

			if (pieces.size() != 1)
			{
				throw new Exception("Can only handle one STRING result");
			}
			if (with != null && with.isPresent())
			{
				throw new Exception("Cannot handle POINTER yet");
			}

			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> metrics = transformer.findArgumentsMetric(STRING);
			if (metrics != null)
			{
				// System.err.println("***************** FOUND METRICS");
				types = new Oper2Types();
			}

			AbstractExpression newExpr = null;
			int i = 0;
			foreach (COBOL_StringWhat what in elements._elements)
			{
				if (what.delimit != null && what.delimit.isPresent())
				{
					throw new Exception("Can't handle DELIMITED BY yet: " + this);
				}

				AbstractExpression nextExpr = transformer.transformExpression(generator, what.expr);
				if (newExpr == null)
				{
					newExpr = nextExpr;
				}
				else // Concatenate
				{
					if (metrics != null)
					{
						types._type1 = metrics[i - 1];
						types._type2 = metrics[i];
					}

					newExpr = generator.newAdditiveExpression(types, newExpr, EagleGenerator.AdditiveEnum.PLUS, nextExpr, what);
				}

				i++;
			}

			COBOL_StringPiece piece = pieces._elements.get(0);
			AbstractExpression asgExpr = generator.newAssignmentExpression(COBOL_Variable.repairName(piece.intoVar.getValue()), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, newExpr, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}

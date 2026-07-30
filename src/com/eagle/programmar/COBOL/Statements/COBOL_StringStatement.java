// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.programmar.COBOL.Statements;

import java.util.ArrayList;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_StringStatement extends COBOL_AbstractStatement
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("rlpsstri.htm") COBOL_Keyword STRING = new COBOL_Keyword("STRING");
	public @S(20) TokenList<COBOL_StringWhat> elements;
	public @S(30) COBOL_Keyword INTO = new COBOL_Keyword("INTO");
	public @S(40) TokenList<COBOL_StringPiece> pieces;
	public @S(50) @OPT COBOL_Comment comment;
	public @S(60) @OPT COBOL_StringWith with;
	public @S(70) @OPT COBOL_Keyword ENDSTRING = new COBOL_Keyword("END-STRING");

	public static class COBOL_StringWhat extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) @OPT COBOL_StringDelimited delimit;

		public static class COBOL_StringDelimited extends TokenSequence
		{
			public @S(10) COBOL_Keyword DELIMITED = new COBOL_Keyword("DELIMITED");
			public @S(20) @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
			public @S(30) COBOL_StringDelimitByWhat what;

			public static class COBOL_StringDelimitByWhat extends TokenChooser
			{
				public @CHOICE COBOL_Keyword XXSIZE = new COBOL_Keyword("SIZE");
				public @CHOICE COBOL_HexNumber XXhex;
				public @CHOICE COBOL_Literal XXliteral;
				public @CHOICE COBOL_StringDelimitSpaces XXstringDelimitSpaces;
			}
		}
	}

	public static class COBOL_StringDelimitSpaces extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
		public @S(20) COBOL_KeywordChoice SPACES = new COBOL_KeywordChoice("SPACE", "SPACES");
	}

	public static class COBOL_StringPiece extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Identifier_Reference intoVar;
		public @S(30) @OPT COBOL_StringCount count;

		public static class COBOL_StringCount extends TokenSequence
		{
			public @S(10) COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
			public @S(20) COBOL_Keyword IN = new COBOL_Keyword("IN");
			public @S(30) COBOL_Identifier_Reference countVar;
		}
	}

	public static class COBOL_StringWith extends TokenSequence
	{
		public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
		public @S(30) COBOL_Identifier_Reference withPointer;
	}

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, STRING.getValue(), STRING);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		if (pieces.size() != 1)
		{
			throw new RuntimeException("Can only handle one STRING result");
		}
		if (with != null && with.isPresent())
		{
			throw new RuntimeException("Cannot handle POINTER yet");
		}

		StringBuffer result = new StringBuffer();
		for (COBOL_StringWhat what : elements._elements)
		{
			if (what.delimit != null && what.delimit.isPresent())
			{
				AbstractToken which = what.delimit.what.getWhich();
				if (!(which instanceof COBOL_StringDelimitSpaces))
				{
					throw new RuntimeException("Can only DELIMIT BY SPACES");
				}
			}

			EagleValue val = interpreter.getEagleValue(what.expr);
			String piece = val.forceStringValue();
			argTypes.add(val.getType());
			result.append(piece);
		}
		_metrics.calledWith(argTypes);

		COBOL_StringPiece strPiece = pieces._elements.get(0);
		interpreter.setSymbol(strPiece, strPiece.intoVar.getValue(),
				new EagleString(result.toString()));
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Oper2Types types = null;

		if (pieces.size() != 1)
		{
			throw new RuntimeException("Can only handle one STRING result");
		}
		if (with != null && with.isPresent())
		{
			throw new RuntimeException("Cannot handle POINTER yet");
		}

		// Pick up metrics, if known
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(STRING);
		if (metrics != null)
		{
			// System.err.println("***************** FOUND METRICS");
			types = new Oper2Types();
		}

		AbstractExpression newExpr = null;
		int i = 0;
		for (COBOL_StringWhat what : elements._elements)
		{
			if (what.delimit != null && what.delimit.isPresent())
			{
				throw new RuntimeException("Can't handle DELIMITED BY yet: " + this);
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
					types._type1 = metrics.get(i - 1);
					types._type2 = metrics.get(i);
				}

				newExpr = generator.newAppendExpression(newExpr, nextExpr, what);
			}

			i++;
		}

		COBOL_StringPiece piece = pieces._elements.get(0);
		AbstractExpression asgExpr = generator.newAssignmentExpression(
				COBOL_Variable.repairName(piece.intoVar.getValue()),
				SubscriptEnum.FIRST_IS_ONE, null, AssignmentEnum.EQUALS, newExpr, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}

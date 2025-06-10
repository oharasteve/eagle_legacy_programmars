// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.EagleGenerator.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_SubtractStatement extends COBOL_AbstractStatement
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("rlpssubt.htm") COBOL_Keyword SUBTRACT = new COBOL_Keyword("SUBTRACT");
	public @S(20) COBOL_Expression expr;
	public @S(30) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
	public @S(40) COBOL_SubtractType type;
	public @S(50) @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");

	public static class COBOL_SubtractMoreVars extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Variable var;
	}

	public static class COBOL_SubtractType extends TokenChooser
	{
		public @FIRST COBOL_SubtractWithGiving XXwithGiving;
		public @CHOICE COBOL_SubtractNoGiving XXnoGiving;
	}

	public static class COBOL_SubtractNoGiving extends TokenSequence
	{
		public @S(10) COBOL_Variable var;
		public @S(20) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
	}

	public static class COBOL_SubtractWithGiving extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) @OPT TokenList<COBOL_SubtractMoreExprs> moreExprs;
		public @S(30) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(40) COBOL_Variable result;
		public @S(50) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;

		public static class COBOL_SubtractMoreExprs extends TokenSequence
		{
			public @S(10) @OPT PunctuationComma comma;
			public @S(20) COBOL_Expression expr;
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = type.getWhich();
		if (!(which instanceof COBOL_SubtractNoGiving))
		{
			throw new RuntimeException("Cannot handle " + which + " yet");
		}
		COBOL_SubtractNoGiving noGiving = (COBOL_SubtractNoGiving) which;
		if (noGiving.moreVars != null && noGiving.moreVars.isPresent() && noGiving.moreVars.size() > 0)
		{
			throw new RuntimeException("Cannot handle multiple expressions yet");
		}

		AbstractToken which2 = noGiving.var.getWhich();
		EagleValue val = interpreter.getEagleValue(expr);
		int newVal = val.forceIntegerValue();
		if (which2 instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable variable = (COBOL_UserVariable) which2;
			EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
			interpreter.setSymbol(variable, variable.id.getValue(),
					new EagleInteger(oldValue.forceIntegerValue() - newVal));
		}
	}


	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (! (type.getWhich() instanceof COBOL_SubtractNoGiving))
		{
			throw new RuntimeException("Can't handle SUBTRACT with GIVING: " + this);
		}
		COBOL_SubtractNoGiving subtractNoGiving = (COBOL_SubtractNoGiving) type.getWhich();
		if (subtractNoGiving.moreVars != null && subtractNoGiving.moreVars.size() > 0)
		{
			throw new RuntimeException("Can't handle multiple arguments to SUBTRACT: " + this);
		}
		
		COBOL_Variable var = subtractNoGiving.var;
		if (! (var.getWhich() instanceof COBOL_UserVariable))
		{
			throw new RuntimeException("Can only SUBTRACT from a Variable: " + this);
		}
		COBOL_UserVariable userVar = (COBOL_UserVariable) var.getWhich();
		if (userVar.subscript != null && userVar.subscript.size() > 0)
		{
			throw new RuntimeException("Can't handle subscripts here: " + this);
		}
		if (userVar.ofList != null && userVar.ofList.size() > 0)
		{
			throw new RuntimeException("Can't handle field OF variable: " + this);
		}
		
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(userVar.id.getValue(),
				SubscriptEnum.FIRST_IS_ONE, null, AssignmentEnum.MINUS_EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}

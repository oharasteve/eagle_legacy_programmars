// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.EagleGenerator.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
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
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class COBOL_AddStatement extends COBOL_AbstractStatement
		implements EagleRunnable, EagleTransformableStatementList
{
	public @S(10) @DOC("rlpsadd.htm") COBOL_Keyword ADD = new COBOL_Keyword("ADD");
	public @S(20) COBOL_AddType type;
	public @S(30) @OPT TokenList<COBOL_AddOnSizeError> onErrorList;
	public @S(40) @OPT COBOL_Keyword ENDADD = new COBOL_Keyword("END-ADD");

	public static class COBOL_AddType extends TokenChooser
	{
		public @FIRST COBOL_AddWithGiving XXaddWithGiving;
		public @CHOICE COBOL_AddNoGiving XXaddNoGiving;
	}

	public static class COBOL_AddWithGiving extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
		public @S(30) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
		public @S(40) @OPT COBOL_Expression toExpr;
		public @S(50) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
		public @S(60) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(70) TokenList<COBOL_Variable> vars;
	}

	public static class COBOL_AddNoGiving extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
		public @S(30) @OPT COBOL_AddTo addTo;

		public static class COBOL_AddTo extends TokenSequence
		{
			public @S(10) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			public @S(20) COBOL_Variable var;
			public @S(30) @OPT TokenList<COBOL_AddMoreVars> moreVars;
		}
	}

	public static class COBOL_AddMoreVars extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Variable var;
	}

	public static class COBOL_AddMoreExprs extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Expression expr;
	}

	public static class COBOL_AddOnSizeError extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Keyword ON = new COBOL_Keyword("ON");
		public @S(30) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public @S(40) COBOL_Keyword ERROR = new COBOL_Keyword("ERROR");
		public @S(50) TokenList<COBOL_Statement> actions;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = type.getWhich();
		if (!(which instanceof COBOL_AddNoGiving))
		{
			throw new RuntimeException("Cannot handle " + which + " yet");
		}
		COBOL_AddNoGiving noGiving = (COBOL_AddNoGiving) which;
		if (noGiving.moreExprs != null && noGiving.moreExprs.isPresent() && noGiving.moreExprs.size() > 0)
		{
			throw new RuntimeException("Cannot handle multiple expressions yet");
		}

		AbstractToken which2 = noGiving.addTo.var.getWhich();
		EagleValue val = interpreter.getEagleValue(noGiving.expr);
		int newVal = val.forceIntegerValue();
		if (which2 instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable variable = (COBOL_UserVariable) which2;
			EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
			interpreter.setSymbol(variable, variable.id.getValue(),
					new EagleInteger(newVal + oldValue.forceIntegerValue()));
		}
		if (noGiving.addTo.moreVars != null && noGiving.addTo.moreVars.isPresent())
		{
			for (COBOL_AddMoreVars more : noGiving.addTo.moreVars._elements)
			{
				AbstractToken which3 = more.var.getWhich();
				if (which3 instanceof COBOL_UserVariable)
				{
					COBOL_UserVariable variable = (COBOL_UserVariable) which3;
					EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
					interpreter.setSymbol(variable, variable.id.getValue(),
							new EagleInteger(oldValue.forceIntegerValue() + newVal));				}
			}
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (! (type.getWhich() instanceof COBOL_AddNoGiving))
		{
			throw new RuntimeException("Can't handle ADD with GIVING: " + this);
		}
		COBOL_AddNoGiving addNoGiving = (COBOL_AddNoGiving) type.getWhich();
		if (addNoGiving.moreExprs != null && addNoGiving.moreExprs.size() > 0)
		{
			throw new RuntimeException("Can't handle multiple arguments to ADD: " + this);
		}
		if (addNoGiving.addTo == null || ! addNoGiving.addTo.isPresent())
		{
			throw new RuntimeException("ADD value TO var is required: " + this);
		}
		
		COBOL_Variable var = addNoGiving.addTo.var;
		if (! (var.getWhich() instanceof COBOL_UserVariable))
		{
			throw new RuntimeException("Can only ADD to a Variable: " + this);
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
		
		ArrayList<AbstractStatement> results = new ArrayList<AbstractStatement>();
		
		// ADD 1 TO X
		AbstractExpression value = transformer.transformExpression(generator, addNoGiving.expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(
				COBOL_Variable.repairName(userVar.id.getValue()), SubscriptEnum.FIRST_IS_ONE,
				null, AssignmentEnum.PLUS_EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		results.add(exprStmt);

		// ADD 1 TO X, Y, Z
		if (addNoGiving.addTo.moreVars != null && addNoGiving.addTo.moreVars.size() > 0)
		{
			for (COBOL_AddMoreVars more : addNoGiving.addTo.moreVars._elements)
			{
				COBOL_UserVariable moreVar = (COBOL_UserVariable) more.var.getWhich();
				AbstractExpression moreExpr = generator.newAssignmentExpression(
						COBOL_Variable.repairName(moreVar.id.getValue()), SubscriptEnum.FIRST_IS_ONE,
						null, AssignmentEnum.PLUS_EQUALS, value, this);
				AbstractStatement moreStmt = generator.newExpressionStatement(moreExpr, this);
				results.add(moreStmt);
			}
		}
		return results;
	}
}

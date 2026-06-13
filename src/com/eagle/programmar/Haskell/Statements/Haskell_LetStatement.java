// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Syntax.Haskell_Multiline_Syntax;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Haskell_LetStatement extends TokenSequence
		implements EagleRunnable // , EagleTransformableStatementList
{
	public @S(10) Haskell_Keyword LET = new Haskell_Keyword("let");
	public @S(20) Haskell_LetVariable letVariables;
	public @S(30) PunctuationEquals equals;
	public @S(40) @SYNTAX(Haskell_Multiline_Syntax.class) Haskell_Expression expression;
	
	public static class Haskell_LetVariable extends TokenChooser
	{
		public @CHOICE Haskell_Variable XXvariable;
		public @CHOICE Haskell_LetMultiple XXmultipleVars;
	}
	
	public static class Haskell_LetMultiple extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Haskell_Variable,PunctuationComma> variables;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expression);
		AbstractToken which = letVariables.getWhich();
		if (which instanceof Haskell_Variable)
		{
			Haskell_Variable variable = (Haskell_Variable) which;
			interpreter.setSymbol(variable.id, variable.id.getValue(), value);
		}
		else if (value.isArray())
		{
			EagleArray array = (EagleArray) value;
			ArrayList<EagleValue> values = array.getArrayValue();
			Haskell_LetMultiple mult = (Haskell_LetMultiple) which;
			int nVars = mult.variables.getPrimaryCount();
			if (nVars != values.size())
			{
				throw new RuntimeException("#Values=" + values.size() + " but #Variables=" + nVars);
			}
			for (int i = 0; i < nVars; i++)
			{
				Haskell_Variable var = mult.variables.getPrimaryElement(i);
				interpreter.setSymbol(var.id, var.id.getValue(), values.get(i));
			}
		}
		else
		{
			throw new RuntimeException("Unable to handle multiple variables");
		}
	}

//	@Override
//	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
//		assign(transformer, generator, result, variable, expression);
//		
//		if (block != null)
//		{
//			for (Haskell_LetMore let : block.more._elements)
//			{
//				assign(transformer, generator, result, let.var, let.expr);
//			}
//		}
//		return result;
//	}
	
//	private void assign(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
//			ArrayList<AbstractStatement> result, Haskell_Variable var, Haskell_Expression expr)
//	{
//		AbstractExpression subscrExpr = null;
//		if (var.subscript != null && var.subscript.isPresent())
//		{
//			subscrExpr = transformer.transformExpression(generator, var.subscript.expr);
//		}
//		AbstractExpression value = transformer.transformExpression(generator, expr);
//		AbstractExpression asgExpr = generator.newAssignmentExpression(var.id.getValue(),
//				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, value, this);
//		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
//		result.add(exprStmt);
//	}
}

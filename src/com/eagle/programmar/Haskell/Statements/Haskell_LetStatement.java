// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Syntax.Haskell_Multiline_Syntax;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Haskell_LetStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatementList
{
	public @S(10) Haskell_Keyword LET = new Haskell_Keyword("let");
	public @S(20) Haskell_Variable variable;
	public @S(30) PunctuationEquals equals;
	public @S(40) @SYNTAX(Haskell_Multiline_Syntax.class) Haskell_Expression expression;
	
	// Let's get individual "Let" to work
	
//	public @S(50) @OPT Haskell_LetBlock block;
//	
//	public static class Haskell_LetBlock extends TokenSequence
//	{
//		public @S(10) @OPT Haskell_EndOfLine eoln;
//		public @S(20) TokenList<Haskell_LetMore> more;
//	}
//	
//	public static class Haskell_LetMore extends TokenSequence
//	{
//		public @S(10) Haskell_Variable var;
//		public @S(20) PunctuationEquals equals;
//		public @S(30) Haskell_Expression expr;
//		public @S(40) Haskell_EndOfLine eoln;
//	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expression);
		interpreter.setSymbol(variable.id, variable.id.getValue(), value);

//		if (block != null)
//		{
//			for (Haskell_LetMore let : block.more._elements)
//			{
//				value = interpreter.getEagleValue(let.expr);
//				interpreter.setSymbol(let.var.id, let.var.id.getValue(), value);
//			}
//		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		assign(transformer, generator, result, variable, expression);
		
//		if (block != null)
//		{
//			for (Haskell_LetMore let : block.more._elements)
//			{
//				assign(transformer, generator, result, let.var, let.expr);
//			}
//		}
		return result;
	}
	
	private void assign(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
			ArrayList<AbstractStatement> result, Haskell_Variable var, Haskell_Expression expr)
	{
		AbstractExpression subscrExpr = null;
		if (var.subscript != null && var.subscript.isPresent())
		{
			subscrExpr = transformer.transformExpression(generator, var.subscript.expr);
		}
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		result.add(exprStmt);
	}
}

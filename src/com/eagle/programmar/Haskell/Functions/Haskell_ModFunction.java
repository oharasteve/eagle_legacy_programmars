// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 6, 2026

package com.eagle.programmar.Haskell.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.MultiplicativeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Expressions.Haskell_ParenthesizedExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_VariableExpression;
import com.eagle.programmar.Haskell.Terminals.Haskell_KeywordChoice;
import com.eagle.programmar.Haskell.Terminals.Haskell_Number;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Haskell_ModFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Haskell_KeywordChoice MOD = new Haskell_KeywordChoice("mod", "div");
	public @S(20) Haskell_ModArgument numer;
	public @S(30) Haskell_ModArgument denom;

	public static class Haskell_ModArgument extends TokenChooser
	{
		public @CHOICE Haskell_Number XXnumber;
		public @CHOICE Haskell_VariableExpression XXvar;
		public @CHOICE Haskell_ParenthesizedExpression XXparens;
		
		public Haskell_Expression wrapExpression()
		{
			Haskell_Expression expr = new Haskell_Expression();
			expr.setWhich(this.getWhich());
			return expr;
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int numerInt = interpreter.getIntValue(numer);
		int denomInt = interpreter.getIntValue(denom);
		switch (MOD.getValue())
		{
		case "mod":
			interpreter.pushInt(Math.floorMod(numerInt, denomInt));
			return;
		case "div":
			interpreter.pushInt(Math.floorDiv(numerInt, denomInt));
			return;
		}
		throw new RuntimeException("Unable to handle " + MOD.getValue());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression numerExpr = transformer.transformExpression(generator, numer.wrapExpression());
		AbstractExpression denomExpr = transformer.transformExpression(generator, denom.wrapExpression());
		return generator.newMultiplicativeExpression(numerExpr, MultiplicativeEnum.MODULUS, denomExpr, this);
	}
}

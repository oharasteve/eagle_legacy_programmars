// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_CommentEoln;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_List;
import com.eagle.programmar.Python.Python_List.Python_MoreListItem;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGeneratableExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Parenthesized_Expression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression, EagleGeneratableExpression
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT @SYNTAX(Python_Multiline_Syntax.class) TokenList<Python_CommentEoln> comments;
	public @S(30) @OPT @NOSPACE @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (list.moreItems != null && list.moreItems.isPresent() && list.moreItems._elements.size() > 0)
		{
			// It is an array declaration
			EagleArray values = new EagleArray();
			EagleValue val = interpreter.getEagleValue(list.expr);
			values.addValue(val);
			for (Python_MoreListItem item : list.moreItems._elements)
			{
				val = interpreter.getEagleValue(item.expr);
				values.addValue(val);
			}

			interpreter.pushEagleValue(values);
		}
		else
		{
			// Just plain parens, like (1+2)
			interpreter.tryToInterpret(list.expr);
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, list.expr);
		return generator.newParenthesizedExpression(theExpr);
	}
	
	public static Python_Parenthesized_Expression generateExpression(AbstractExpression theExpr)
	{
		Python_Parenthesized_Expression expr = new Python_Parenthesized_Expression();
		expr.list.expr = (Python_Expression) theExpr;
		return expr;
	}
}

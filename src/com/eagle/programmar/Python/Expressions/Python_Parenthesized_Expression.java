// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_CommentEoln;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_List;
import com.eagle.programmar.Python.Python_List.Python_MoreListItem;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Parenthesized_Expression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
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
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		if (list.moreItems != null && list.moreItems.isPresent() && list.moreItems._elements.size() > 0)
		{
			// It is an array declaration
			ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
			exprs.add(transformer.transformExpression(generator, list.expr));

			for (Python_MoreListItem more : list.moreItems._elements)
			{
				exprs.add(transformer.transformExpression(generator, more.expr));
			}
			return generator.newArrayExpression(exprs, this);
		}
		
		// Just plain parens, like (1+2)
		AbstractExpression theExpr = transformer.transformExpression(generator, list.expr);
		return generator.newParenthesizedExpression(theExpr, this);
	}
	
	public Python_Expression generateParentheses(Python_Expression theExpr,
			AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.list = new Python_List();
		this.list.setPresent(true);
		this.list.expr = theExpr;
		this.rightParen = new PunctuationRightParen();
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}

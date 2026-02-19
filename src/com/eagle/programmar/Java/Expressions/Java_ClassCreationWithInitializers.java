// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Java_Type.Java_ArrayType;
import com.eagle.programmar.Java.Java_Type.Java_TypeName;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_ClassCreationWithInitializers extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Keyword NEW = new Java_Keyword("new");
	public @S(20) Java_Type jtype;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT Java_ArgumentList valueList;
	public @S(50) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();

		if (valueList.arg.isPresent())
		{
			Java_Expression expr = valueList.arg;
			array.addValue(interpreter.getEagleValue(expr));
		}

		if (valueList.moreArgs.isPresent())
		{
			for (Java_MoreArguments more : valueList.moreArgs._elements)
			{
				array.addValue(interpreter.getEagleValue(more.arg));
			}
		}

		interpreter.pushEagleValue(array);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();

		if (valueList.arg.isPresent())
		{
			exprs.add(transformer.transformExpression(generator, valueList.arg));
		}

		if (valueList.moreArgs.isPresent())
		{
			for (Java_MoreArguments more : valueList.moreArgs._elements)
			{
				exprs.add(transformer.transformExpression(generator, more.arg));
			}
		}

		return generator.newArrayExpression(exprs, this);
	}

	public static Java_Expression generateArray(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		Java_ClassCreationWithInitializers creat = new Java_ClassCreationWithInitializers();

		// Want to end up with: new String[] {"abc", "def"}
		creat.jtype = new Java_Type();
		Java_KeywordChoice str = new Java_KeywordChoice("String");
		creat.jtype.typeName = new Java_TypeName();
		creat.jtype.typeName.setWhich(str);

		Java_ArrayType array = new Java_ArrayType();
		array.leftBracket = new PunctuationLeftBracket();
		array.rightBracket = new PunctuationRightBracket();

		creat.jtype.arrayTypes = new TokenList<Java_ArrayType>();
		creat.jtype.arrayTypes.setPresent(true);
		creat.jtype.arrayTypes.addToken(array);

		creat.leftBrace = new PunctuationLeftBrace();
		creat.rightBrace = new PunctuationRightBrace();
		creat.valueList = new Java_ArgumentList();
		creat.valueList.setPresent(true);

		for (int i = 0; i < exprs.size(); i++)
		{
			if (i == 0)
			{
				creat.valueList.arg = (Java_Expression) exprs.get(0);
			}
			else
			{
				if (creat.valueList.moreArgs == null)
				{
					creat.valueList.moreArgs = new TokenList<Java_MoreArguments>();
					creat.valueList.moreArgs.setPresent(true);
				}
				Java_MoreArguments more = new Java_MoreArguments();
				more.comma = new PunctuationComma();
				more.arg = (Java_Expression) exprs.get(i);
				creat.valueList.moreArgs.addToken(more);
			}
		}

		creat.setTransformationSource(source);
		return Java_Generator.wrapExpression(creat);
	}
}

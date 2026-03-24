// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.programmar.CSharp.CSharp_Argument;
import com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_ArgumentList.CSharp_MoreArguments;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_ArrayType;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_ClassCreationWithInitializers extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
	public @S(20) CSharp_Type cstype;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT @NOSPACE CSharp_ArgumentList valueList;
	public @S(50) @NOSPACE PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();

		if (valueList.arg.isPresent())
		{
			AbstractToken token = valueList.arg.getWhich();
			if (token instanceof CSharp_ArgumentOut)
			{
				CSharp_ArgumentOut arg = (CSharp_ArgumentOut) token;
				array.addValue(interpreter.getEagleValue(arg.arg));
			}
		}

		if (valueList.moreArgs.isPresent())
		{
			for (CSharp_MoreArguments more : valueList.moreArgs._elements)
			{
				AbstractToken token = more.arg.getWhich();
				if (token instanceof CSharp_ArgumentOut)
				{
					CSharp_ArgumentOut arg = (CSharp_ArgumentOut) token;
					array.addValue(interpreter.getEagleValue(arg.arg));
				}
			}
		}

		interpreter.pushEagleValue(array);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();

		if (valueList.arg.isPresent())
		{
			CSharp_Expression expr1 = valueList.arg.getExpression();
			exprs.add(transformer.transformExpression(generator, expr1));
		}

		if (valueList.moreArgs.isPresent())
		{
			for (CSharp_MoreArguments more : valueList.moreArgs._elements)
			{
				CSharp_Expression expr2 = more.arg.getExpression();
				exprs.add(transformer.transformExpression(generator, expr2));
			}
		}

		return generator.newArrayExpression(exprs, this);
	}

	public static CSharp_Expression generateArray(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		CSharp_ClassCreationWithInitializers creat = new CSharp_ClassCreationWithInitializers();
		// Want to end up with: new string[] {"abc", "def"}
		creat.cstype = CSharp_Type.newPrimitiveType("string");

		CSharp_ArrayType array = new CSharp_ArrayType();
		array.leftBracket = new PunctuationLeftBracket();
		array.rightBracket = new PunctuationRightBracket();

		creat.cstype.arrayTypes = new TokenList<CSharp_ArrayType>();
		creat.cstype.arrayTypes.setPresent(true);
		creat.cstype.arrayTypes.addToken(array);

		creat.leftBrace = new PunctuationLeftBrace();
		creat.rightBrace = new PunctuationRightBrace();
		creat.valueList = new CSharp_ArgumentList();
		creat.valueList.setPresent(true);

		for (int i = 0; i < exprs.size(); i++)
		{
			CSharp_ArgumentOut argOut = new CSharp_ArgumentOut();
			argOut.arg = (CSharp_Expression) exprs.get(i);
			CSharp_Argument arg = new CSharp_Argument();
			arg.setWhich(argOut);

			if (i == 0)
			{
				creat.valueList.arg = arg;
				creat.valueList.arg.setPresent(true);
			}
			else
			{
				if (creat.valueList.moreArgs == null)
				{
					creat.valueList.moreArgs = new TokenList<CSharp_MoreArguments>();
				}
				CSharp_MoreArguments more = new CSharp_MoreArguments();
				more.comma = new PunctuationComma();
				more.arg = arg;
				more.arg.setPresent(true);
				creat.valueList.moreArgs.addToken(more);
			}
		}

		creat.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(creat);
	}
}

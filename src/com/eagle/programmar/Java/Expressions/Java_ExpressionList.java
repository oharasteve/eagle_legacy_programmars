// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import java.util.ArrayList;

import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Java_ExpressionList extends PrimaryOperator
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<Java_Comment> comment;
	public @S(30) @OPT @NOSPACE Java_ArgumentList valueList;
	public @S(40) @NOSPACE PunctuationRightBrace rightBrace;

	public Java_Expression generateArray(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.valueList = new Java_ArgumentList();
		this.valueList.setPresent(true);
		
		for (int i = 0; i < exprs.size(); i++)
		{
			if (i == 0)
			{
				this.valueList.arg = (Java_Expression) exprs.get(0);
			}
			else
			{
				if (this.valueList.moreArgs == null)
				{
					this.valueList.moreArgs = new TokenList<Java_MoreArguments>();
					this.valueList.moreArgs.setPresent(true);
				}
				Java_MoreArguments more = new Java_MoreArguments();
				more.comma = new PunctuationComma();
				more.arg = (Java_Expression) exprs.get(i);
				this.valueList.moreArgs.addToken(more);
			}
		}

		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import java.util.ArrayList;

import com.eagle.programmar.CSharp.CSharp_Argument;
import com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_ArgumentList.CSharp_MoreArguments;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CSharp_ExpressionList extends PrimaryOperator
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT @NOSPACE TokenList<CSharp_Comment> comment;
	public @S(30) CSharp_ArgumentList valueList;
	public @S(40) @NOSPACE PunctuationRightBrace rightBrace;

	public CSharp_Expression generateArray(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.valueList = new CSharp_ArgumentList();
		
		for (int i = 0; i < exprs.size(); i++)
		{
			CSharp_ArgumentOut argOut = new CSharp_ArgumentOut();
			argOut.arg = (CSharp_Expression) exprs.get(i);
			CSharp_Argument arg = new CSharp_Argument();
			arg.setWhich(argOut);
			
			if (i == 0)
			{
				this.valueList.arg = arg;
			}
			else
			{
				if (this.valueList.moreArgs == null)
				{
					this.valueList.moreArgs = new TokenList<CSharp_MoreArguments>();
				}
				CSharp_MoreArguments more = new CSharp_MoreArguments();
				more.comma = new PunctuationComma();
				more.arg = arg;
				this.valueList.moreArgs.addToken(more);
			}
		}

		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}

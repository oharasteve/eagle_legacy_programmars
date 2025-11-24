// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import java.util.ArrayList;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_List;
import com.eagle.programmar.Python.Python_List.Python_MoreListItem;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_Brackets extends PrimaryOperator
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT Python_EndOfLine eoln1;
	public @S(30) @OPT TokenList<Python_Comment> comment;
	public @S(40) @OPT Python_EndOfLine eoln2;
	public @S(50) @OPT @NOSPACE @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
	public @S(60) @NOSPACE PunctuationRightBracket rightBracket;

	public Python_Expression generateArray(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		this.leftBracket = new PunctuationLeftBracket();
		this.rightBracket = new PunctuationRightBracket();
		this.list = new Python_List();
		this.list.setPresent(true);

		for (int i = 0; i < exprs.size(); i++)
		{
			if (i == 0)
			{
				this.list.expr = (Python_Expression) exprs.get(0);
			}
			else
			{
				if (this.list.moreItems == null)
				{
					this.list.moreItems = new TokenList<Python_MoreListItem>();
					this.list.moreItems.setPresent(true);
				}
				Python_MoreListItem more = new Python_MoreListItem();
				more.comma = new PunctuationComma();
				more.expr = (Python_Expression) exprs.get(i);
				this.list.moreItems.addToken(more);
			}
		}

		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}

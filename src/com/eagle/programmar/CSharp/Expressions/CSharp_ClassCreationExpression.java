// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import java.util.ArrayList;

import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_ClassCreationExpression extends PrimaryOperator
{
	public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
	public @S(20) CSharp_Type cstype;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @OPT TokenList<CSharp_Comment> comments;
	public @S(50) @OPT @NOSPACE CSharp_ArgumentList argList;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	
	public CSharp_Expression generateCreation(CSharp_Type type,
			ArrayList<CSharp_Expression> args, AbstractToken source)
	{
		this.cstype = type;
		this.leftParen = new PunctuationLeftParen();
		if (args != null && args.size() > 0)
		{
			this.argList = CSharp_ArgumentList.createArgumentList(args);
			this.argList.setPresent(true);
		}
		this.rightParen = new PunctuationRightParen();

		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}

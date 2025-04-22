// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import java.util.Collection;

import com.eagle.generate.Expressions.Eagle_Generate_ClassCreation;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_ClassCreationExpression extends PrimaryOperator implements
		Eagle_Generate_ClassCreation<Java_Expression, Java_Type>
{
	public @S(10) Java_Keyword NEW = new Java_Keyword("new");
	public @S(20) Java_Type jtype;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE @OPT TokenList<Java_Comment> comments;
	public @S(50) @NOSPACE @OPT Java_ArgumentList argList;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	public @S(70) @OPT Java_ClassOverride override;

	public static class Java_ClassOverride extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TokenList<Java_ClassElement> elementList;
		public @S(30) PunctuationRightBrace rightBrace;
	}
	
	@Override
	public Java_Expression generateCreation(Java_Type type,
			Collection<AbstractExpression> args, AbstractToken source)
	{
		this.jtype = type;
		this.leftParen = new PunctuationLeftParen();
		if (args != null && args.size() > 0)
		{
			this.argList = Java_ArgumentList.createArgumentList(args);
			this.argList.setPresent(true);
		}
		this.rightParen = new PunctuationRightParen();

		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}

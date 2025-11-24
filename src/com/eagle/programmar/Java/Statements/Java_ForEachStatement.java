// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_ForEachStatement extends TokenSequence implements AbstractStatement, EagleScopeInterface
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) Java_Keyword FOR = new Java_Keyword("for");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE Java_Annotation annotation;
	public @S(50) @OPT Java_Keyword FINAL = new Java_Keyword("final");
	public @S(60) Java_Type varType;
	public @S(70) Java_Variable forVar;
	public @S(80) PunctuationColon colon;
	public @S(90) Java_Expression collection;
	public @S(100) PunctuationRightParen rightParen;
	public @S(110) @OPT Java_Comment comment;
	public @S(120) Java_Statement action;

	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
}

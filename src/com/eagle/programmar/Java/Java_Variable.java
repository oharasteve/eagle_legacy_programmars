// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) Java_VariableIdentifier firstId;
	public @S(20) @OPT TokenList<Java_DotVariable> moreIds;
	public @S(30) @OPT TokenList<Java_Subscript> subscript;

	public static class Java_DotVariable extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot;
		public @S(20) @NOSPACE Java_VariableIdentifier nextId;
	}

	public static class Java_VariableIdentifier extends TokenChooser
	{
		public @CHOICE Java_KeywordChoice builtIn = new Java_KeywordChoice("this", "class", "super");
		public @CHOICE Java_Identifier_Reference id;

		public @CHOICE static class Java_CastedVariable extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen1;
			public @S(20) PunctuationLeftParen leftParen2;
			public @S(30) Java_Type jtype;
			public @S(40) PunctuationRightParen rightParen1;
			public @S(50) Java_Identifier_Reference id;
			public @S(60) PunctuationRightParen rightParen2;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Java_Identifier_Reference which = (Java_Identifier_Reference) firstId.getWhich();
		EagleValue value = interpreter._symbolTable.findSymbol(which.toString());
		interpreter.pushEagleValue(value);
	}
}

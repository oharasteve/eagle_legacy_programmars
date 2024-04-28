// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) CSharp_VariableIdentifier firstId;
	public @S(20) @OPT TokenList<CSharp_MoreVariableIdentifiers> more;
	public @S(30) @OPT TokenList<CSharp_Subscript> subscript;
	
	public static class CSharp_MoreVariableIdentifiers extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot;
		public @S(20) @NOSPACE CSharp_VariableIdentifier nextId;
	}
	
	public static class CSharp_VariableIdentifier extends TokenChooser
	{
		public @CHOICE CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice("this", "base", "class");
		public @CHOICE CSharp_Identifier_Reference id;
		
		public @CHOICE static class CSharp_CastedVariable extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen1;
			public @S(20) @NOSPACE PunctuationLeftParen leftParen2;
			public @S(30) @NOSPACE CSharp_Type cstype;
			public @S(40) @NOSPACE PunctuationRightParen rightParen1;
			public @S(50) CSharp_Identifier_Reference id;
			public @S(60) @NOSPACE PunctuationRightParen rightParen2;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		CSharp_Identifier_Reference which = (CSharp_Identifier_Reference) firstId.getWhich();
		EagleValue value = interpreter._symbolTable.findSymbol(which.toString());
		interpreter.pushEagleValue(value);
	}
}

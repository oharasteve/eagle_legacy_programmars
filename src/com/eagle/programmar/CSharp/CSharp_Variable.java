// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Variable extends TokenSequence implements AbstractVariable
{
	public CSharp_VariableIdentifier first;
	public @OPT TokenList<CSharp_MoreVariableIdentifiers> more;
	public @OPT TokenList<CSharp_Subscript> subscript;
	
	public static class CSharp_MoreVariableIdentifiers extends TokenSequence
	{
		public @NOSPACE PunctuationPeriod dot;
		public @NOSPACE CSharp_VariableIdentifier nextId;
	}
	
	public static class CSharp_VariableIdentifier extends TokenChooser
	{
		public @CHOICE CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice("this", "base", "class");
		public @CHOICE CSharp_Identifier_Reference id;
		
		public @CHOICE static class CSharp_CastedVariable extends TokenSequence
		{
			public PunctuationLeftParen leftParen1;
			public @NOSPACE PunctuationLeftParen leftParen2;
			public @NOSPACE CSharp_Type cstype;
			public @NOSPACE PunctuationRightParen rightParen1;
			public CSharp_Identifier_Reference id;
			public @NOSPACE PunctuationRightParen rightParen2;
		}
	}
}

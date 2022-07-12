// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.programmar.BNF.Symbols.BNF_Rule_Reference;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
import com.eagle.programmar.BNF.Terminals.BNF_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class BNF_Expression extends TokenSequence
{
	public @S(10) TokenList<BNF_ExpressionTerm> terms;
	public @S(20) @OPT TokenList<BNF_Alternation> choices;

	public static class BNF_Alternation extends TokenSequence
	{
		public @S(10) BNF_Punctuation VerticalBar = new BNF_Punctuation('|');
		public @S(20) TokenList<BNF_ExpressionTerm> terms;
	}

	public static class BNF_ExpressionTerm extends TokenChooser
	{
		public @CHOICE BNF_Literal literal;
	
		public @CHOICE static class BNF_Rulename extends TokenSequence
		{
			public @S(10) BNF_Rule_Reference ref;
			public @S(20) @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
		}
		
		public @CHOICE static class BNF_Group extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) BNF_Expression expression;
			public @S(30) PunctuationRightParen rightParen;
			public @S(40) @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
		}
		
		public @CHOICE static class BNF_Optional extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) BNF_Expression expression;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_RelationalOperator extends TokenChooser
{
	public @CHOICE COBOL_PunctuationChoice XXoperator =
			new COBOL_PunctuationChoice("<=", "<", "=", ">=", ">", "<>");

	public @CHOICE static class COBOL_Greater extends TokenSequence
	{
		public @S(10) COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
		public @S(20) @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
		public @S(30) @OPT COBOL_GreaterOrEqual orEqual;

		public static class COBOL_GreaterOrEqual extends TokenSequence
		{
			public @S(10) COBOL_Keyword OR = new COBOL_Keyword("OR");
			public @S(20) COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
			public @S(30) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
		}
	}

	public @CHOICE static class COBOL_Equal extends TokenSequence
	{
		public @S(10) COBOL_KeywordChoice EQUAL = new COBOL_KeywordChoice("EQUAL", "EQUALS");
		public @S(20) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
	}

	public @CHOICE static class COBOL_Less extends TokenSequence
	{
		public @S(10) COBOL_Keyword LESS = new COBOL_Keyword("LESS");
		public @S(20) @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
		public @S(30) @OPT COBOL_LessOrEqual orEqual;

		public static class COBOL_LessOrEqual extends TokenSequence
		{
			public @S(10) COBOL_Keyword OR = new COBOL_Keyword("OR");
			public @S(20) COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
			public @S(30) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
		}
	}
	
	// Return simplified version of the above, just "=", "<", etc.
	public String canonicalForm()
	{
		if (this.getWhich() instanceof COBOL_PunctuationChoice)
		{
			COBOL_PunctuationChoice punct = (COBOL_PunctuationChoice) this.getWhich();
			return punct.getValue();
		}
		if (this.getWhich() instanceof COBOL_Greater)
		{
			COBOL_Greater great = (COBOL_Greater) this.getWhich();
			if (great.orEqual != null && great.orEqual.isPresent())
			{
				return ">=";
			}
			return ">";
		}
		if (this.getWhich() instanceof COBOL_Less)
		{
			COBOL_Less less = (COBOL_Less) this.getWhich();
			if (less.orEqual != null && less.orEqual.isPresent())
			{
				return "<=";
			}
			return "<";
		}
		// Must be COBOL_Equal
		return "=";
	}
}

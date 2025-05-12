// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_PerformClause extends TokenChooser
{
	public @CHOICE static class COBOL_PerformVarying extends TokenSequence
	{
		public @S(10) COBOL_KeywordChoice varyingOrAfter = new COBOL_KeywordChoice("VARYING", "AFTER");
		public @S(20) COBOL_Modifiable_Identifier id;
		public @S(30) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
		public @S(40) COBOL_Expression from;
		public @S(50) COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @S(60) COBOL_Expression by;
	}

	public @CHOICE static class COBOL_PerformUntil extends TokenSequence
	{
		public @S(10) COBOL_Keyword UNTIL = new COBOL_Keyword("UNTIL");
		public @S(20) COBOL_Expression condition;
	}
}
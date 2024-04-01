// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_VariableExpression extends PrimaryOperator
{
	public @S(10) COBOL_VariableRef variable;

	public static class COBOL_VariableRef extends TokenSequence
	{
		public @S(10) COBOL_Identifier_Reference id;
		public @S(20) @OPT TokenList<COBOL_Subscript> subscript;
		public @S(30) @OPT TokenList<COBOL_OfVariableRef> ofList;

		public static class COBOL_OfVariableRef extends TokenSequence
		{
			public @S(10) COBOL_Keyword OF = new COBOL_Keyword("OF");
			public @S(20) COBOL_Identifier_Reference id;
		}
	}
}

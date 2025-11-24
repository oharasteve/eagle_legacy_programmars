// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.programmar.BNF.Expressions.BNF_Alternation;
import com.eagle.programmar.BNF.Expressions.BNF_Group;
import com.eagle.programmar.BNF.Expressions.BNF_Optional;
import com.eagle.programmar.BNF.Expressions.BNF_Rulename;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class BNF_Expression extends TokenSequence
{
	public @S(10) TokenList<BNF_ExpressionTerm> terms;
	public @S(20) @OPT TokenList<BNF_Alternation> choices;

	public static class BNF_ExpressionTerm extends TokenChooser
	{
		public @CHOICE BNF_Literal XXliteral;
		public @CHOICE BNF_Rulename XXrulename;
		public @CHOICE BNF_Group XXgroup;
		public @CHOICE BNF_Optional XXoptional;
	}
}

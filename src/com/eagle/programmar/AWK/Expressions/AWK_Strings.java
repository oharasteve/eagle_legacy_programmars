// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class AWK_Strings extends PrimaryOperator
{
	public @S(10) TokenList<AWK_StringPiece> pieces;
	
	public static class AWK_StringPiece extends TokenChooser
	{
		public @CHOICE AWK_Literal literal;
		public @CHOICE AWK_BuiltinFunctionCall fn;
		public @CHOICE AWK_UserFunctionCall userfn;

		public @LAST AWK_Identifier_Reference id;
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class SQL_RelationalExpression extends PrecedenceOperator
{
	public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) SQL_RelationalOperator relationalOperator;
	public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

	public static class SQL_RelationalOperator extends TokenChooser
	{
		public @CHOICE SQL_KeywordChoice LIKE = new SQL_KeywordChoice("LIKE", "IN");
		public @CHOICE SQL_PunctuationChoice operator = new SQL_PunctuationChoice("=", "!=", "<", ">", "<=", ">=");
	}
}

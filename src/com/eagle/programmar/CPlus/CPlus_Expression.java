// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_ParenthesizedExpression;
import com.eagle.programmar.C.C_Subscript;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;

public class CPlus_Expression extends C_Expression implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public CPlus_Expression()
	{
	    super();
	    setOperators(_operators);
	}

	public CPlus_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(token, allowed);
	    setOperators(_operators);
	}

	public static @P(500) class CPlus_NewExpression extends PrimaryOperator
	{
		public @S(10) C_Keyword NEW = new C_Keyword("new");
		public @S(20) C_Type type;
		public @S(30) CPlus_NewWhat what;
		
		public static class CPlus_NewWhat extends TokenChooser
		{
			public @CHOICE C_ParenthesizedExpression expr;
			public @CHOICE C_Subscript size;
		}
	}
}

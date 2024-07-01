// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class AWK_ConcatenationExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_ConcatPiece piece1;
	public @S(20) AWK_ConcatPiece piece2;
	public @S(30) @OPT TokenList<AWK_ConcatPiece> pieces;

	public static class AWK_ConcatPiece extends TokenChooser
	{
		public @CHOICE AWK_String string;
		public @CHOICE AWK_VariableExpression variable;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		StringBuffer sb = new StringBuffer();
		String val1 = interpreter.getStrValue(piece1.getWhich());
		sb.append(val1);
		String val2 = interpreter.getStrValue(piece2.getWhich());
		sb.append(val2);
		for (AWK_ConcatPiece piece : pieces._elements)
		{
			String val = interpreter.getStrValue(piece.getWhich());
			sb.append(val);
		}
		interpreter.pushStr(sb.toString());
	}
}

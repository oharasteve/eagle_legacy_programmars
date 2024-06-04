// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class AWK_Strings extends PrimaryOperator implements EagleRunnable
{
	public @S(10) TokenList<AWK_StringPiece> pieces;

	public static class AWK_StringPiece extends TokenChooser
	{
		public @CHOICE AWK_Literal literal;
		public @CHOICE AWK_BuiltinFunctionCall fn;
		public @CHOICE AWK_UserFunctionCall userfn;

		public @LAST AWK_Identifier_Reference id;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		StringBuffer sb = new StringBuffer();
		for (AWK_StringPiece piece : pieces._elements)
		{
			AbstractToken which = piece.getWhich();
			if (which instanceof AWK_Literal)
			{
				AWK_Literal lit = (AWK_Literal) which;
				String str = lit.getValue();
				if (str.startsWith("\""))
				{
					sb.append(str.substring(1, str.length()-1));	// Remove quotes
				}
				else
				{
					sb.append(str);
				}
			}
			else if (which instanceof AWK_BuiltinFunctionCall)
			{
				AWK_BuiltinFunctionCall fn = (AWK_BuiltinFunctionCall) which;
				String val = interpreter.getStrValue(fn);
				sb.append(val);
			}
			else if (which instanceof AWK_UserFunctionCall)
			{
				AWK_UserFunctionCall fn = (AWK_UserFunctionCall) which;
				String val = interpreter.getStrValue(fn);
				sb.append(val);
			}
			else if (which instanceof AWK_Identifier_Reference)
			{
				AWK_Identifier_Reference id = (AWK_Identifier_Reference) which;
				EagleValue val = interpreter._symbolTable.findSymbol(id.getValue());
				sb.append(val.forceStringValue());
			}
			else throw new RuntimeException("Unable to handle " + which);
		}
		interpreter.pushStr(sb.toString());
	}
}

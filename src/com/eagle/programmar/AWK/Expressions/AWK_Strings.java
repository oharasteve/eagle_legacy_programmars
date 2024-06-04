// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.tokens.PrimaryOperator;

public class AWK_Strings extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_Literal literal;
	
//	public @S(10) TokenList<AWK_StringPiece> pieces;
//
//	public static class AWK_StringPiece extends TokenChooser
//	{
//		public @CHOICE AWK_Literal literal;
//		public @CHOICE AWK_BuiltinFunctionCall fn;
//		public @CHOICE AWK_UserFunctionCall userfn;
//
//		public @LAST AWK_Variable variable;
//	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = literal.getValue();
		if (str.startsWith("\""))
		{
			str = str.substring(1, str.length()-1);	// Remove quotes
		}
		interpreter.pushStr(str);


//		StringBuffer sb = new StringBuffer();
//		for (AWK_StringPiece piece : pieces._elements)
//		{
//			AbstractToken which = piece.getWhich();
//			if (which instanceof AWK_Literal)
//			{
//				AWK_Literal lit = (AWK_Literal) which;
//				String str = lit.getValue();
//				if (str.startsWith("\""))
//				{
//					sb.append(str.substring(1, str.length()-1));	// Remove quotes
//				}
//				else
//				{
//					sb.append(str);
//				}
//			}
//			else if (which instanceof AWK_BuiltinFunctionCall)
//			{
//				AWK_BuiltinFunctionCall fn = (AWK_BuiltinFunctionCall) which;
//				String val = interpreter.getStrValue(fn);
//				sb.append(val);
//			}
//			else if (which instanceof AWK_UserFunctionCall)
//			{
//				AWK_UserFunctionCall fn = (AWK_UserFunctionCall) which;
//				String val = interpreter.getStrValue(fn);
//				sb.append(val);
//			}
//			else if (which instanceof AWK_Variable)
//			{
//				AWK_Variable var = (AWK_Variable) which;
//				String val = interpreter.getStrValue(var);
//				sb.append(val);
//			}
//			else throw new RuntimeException("Unable to handle " + which);
//			System.out.println("****************************** " + sb.toString());
//		}
//		System.out.println();
//		interpreter.pushStr(sb.toString());
	}
}

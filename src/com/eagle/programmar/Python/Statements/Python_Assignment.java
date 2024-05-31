// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Python_VariableList.Python_Variable_or_List;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_Assignment extends TokenSequence implements EagleRunnable
{
	public @S(10) @NOSPACE Python_VariableList varList;
	public @S(20) @OPT Python_ResultType resultType;
	public @S(30) Python_PunctuationChoice operator = new Python_PunctuationChoice("=", "+=", "-=", "*=", "/=", "%=",
			"&=", "|=", "^=", "<<=", ">>=", "**=", "//=");
	public @S(40) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(50) Python_Expression expr;
	public @S(60) @OPT TokenList<Python_MoreAsgExpressions> moreExpressions;
	public @S(70) @OPT Python_Comment comment;

	public static class Python_MoreAsgExpressions extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Python_Expression expr;
	}

	public static class Python_ResultType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Python_Type type;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		Python_Variable_or_List vl = varList.vars.first();
		Python_Variable v = (Python_Variable) vl.getWhich();
		interpreter._symbolTable.setSymbol(v.getFileName(), v.getStartLine(), v.getStartChar(),
				v.var.getWhich().toString(), value);
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Python_VariableList.Python_Just_Var;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableOrList;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_Assignment extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @NOSPACE Python_VariableList varList;
	public @S(20) @OPT TokenList<Python_Subscript> subscripts;
	public @S(30) @OPT Python_ResultType resultType;
	public @S(40) Python_PunctuationChoice operator = new Python_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "**=", "//=");
	public @S(50) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(60) Python_Expression expr;
	public @S(70) @OPT TokenList<Python_MoreAsgExpressions> moreExpressions;
	public @S(80) @OPT Python_Comment comment;

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
		Python_VariableOrList vars = varList.vars.first();
		if (!(vars.getWhich() instanceof Python_Just_Var))
		{
			throw new RuntimeException("Unexpected assignment variable: " + vars.getWhich());
		}
		Python_Just_Var justVar = (Python_Just_Var) vars.getWhich();
		Python_Variable var = justVar.variable.first();

		if (var.var.getWhich() instanceof Python_Identifier_Reference)
		{
			Python_Identifier_Reference id = (Python_Identifier_Reference) var.var.getWhich();
			switch (operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
						id.getValue(), val);
				break;
			case "+=":
				int newVal = interpreter.getIntValue(expr);
				EagleValue oldVar = interpreter._symbolTable.findSymbol(id.toString());
				EagleInteger newValue = new EagleInteger(newVal + oldVar.forceIntegerValue());
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
						id.getValue(), newValue);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
			}
		}
	}
}

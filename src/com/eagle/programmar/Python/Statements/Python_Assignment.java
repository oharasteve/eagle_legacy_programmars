// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Subscript;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Python_VariableList.Python_Just_Var;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableAndSubscript;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableOrList;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Python_Assignment extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @NOSPACE Python_VariableList varList;
	public @S(20) @OPT TokenList<Python_Subscript> subscripts;
	public @S(30) @OPT Python_ResultType resultType;
	public @S(40) Python_PunctuationChoice operator = new Python_PunctuationChoice(
			"=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "**=", "//=");
	public @S(50) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(60) Python_Expression expression;
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
		Python_VariableAndSubscript var = justVar.variable.first();

		if (var.variable.var.getWhich() instanceof Python_Identifier_Reference)
		{
			Python_Identifier_Reference id = (Python_Identifier_Reference) var.variable.var.getWhich();
			switch (operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expression);
				interpreter.setSymbol(var, id.getValue(), val);
				break;
			case "+=":
				int newVal1 = interpreter.getIntValue(expression);
				EagleValue oldVar1 = interpreter.findSymbol(id.toString());
				EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
				interpreter.setSymbol(var, id.getValue(), newValue1);
				break;
			case "-=":
				int newVal2 = interpreter.getIntValue(expression);
				EagleValue oldVar2 = interpreter.findSymbol(id.toString());
				EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
				interpreter.setSymbol(var, id.getValue(), newValue2);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
			}
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		Python_VariableOrList vars = varList.vars.first();
		if (!(vars.getWhich() instanceof Python_Just_Var))
		{
			throw new RuntimeException("Unexpected assignment variable: " + vars.getWhich());
		}
		Python_Just_Var justVar = (Python_Just_Var) vars.getWhich();
		Python_VariableAndSubscript var = justVar.variable.first();

		if (!(var.variable.var.getWhich() instanceof Python_Identifier_Reference))
		{
			throw new RuntimeException("Assigment must be to a variable");
		}
		Python_Identifier_Reference id = (Python_Identifier_Reference) var.variable.var.getWhich();
		AssignmentEnum asg;
		switch (operator.getValue())
		{
		case "=":
			asg = AssignmentEnum.EQUALS;
			break;
		case "+=":
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case "-=":
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}

		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expression);
		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return generator.newExpressionStatement(asgExpr, this);
	}

	public static Python_ExpressionStatement generateAssignment(String name, SubscriptEnum offset,
			Python_Expression subscript, AssignmentEnum oper, Python_Expression expression,
			String comment, AbstractToken source)
	{
		if (oper != AssignmentEnum.EQUALS)
		{
			throw new RuntimeException("Unexpected assigment operator: " + oper.toString());
		}

		Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
		asgExpr.left = Python_VariableExpression.generateVariableExpression(
				name, offset, subscript, source);
		asgExpr.operator = new Python_PunctuationChoice("=");
		asgExpr.right = expression;

		Python_ExpressionStatement exprStmt = new Python_ExpressionStatement();
		Python_Expression expr = new Python_Expression();
		expr.setWhich(asgExpr);
		exprStmt.expression = expr;
		if (comment != null) exprStmt.comment = new Python_Comment(comment);

		return exprStmt;
	}
}

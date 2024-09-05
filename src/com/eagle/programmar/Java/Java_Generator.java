// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Java;

import java.util.ArrayList;

import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_BuiltIn;
import com.eagle.programmar.Java.Expressions.Java_LogicalAndExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalOrExpression;
import com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression;
import com.eagle.programmar.Java.Expressions.Java_NegativeExpression;
import com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
import com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
import com.eagle.programmar.Java.Expressions.Java_VariableExpression;
import com.eagle.programmar.Java.Functions.Java_LengthMethod;
import com.eagle.programmar.Java.Functions.Java_MathPowFunc;
import com.eagle.programmar.Java.Functions.Java_SubstringMethod;
import com.eagle.programmar.Java.Statements.Java_ExitStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_PrintStatement;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;

public class Java_Generator extends EagleGenerator
{
	public Java_Generator()
	{
		_currentLanguage = new Java_Program();
	}
	
	@Override
	public String getName()
	{
		return "Java";
	}
	
	@Override
	public String getSuffix()
	{
		return ".java";
	}
	
	public static Java_Expression wrapExpression(AbstractToken token)
	{
		Java_Expression wrapper = new Java_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	public static Java_Statement wrapStatement(AbstractToken token)
	{
		Java_Statement wrapper = new Java_Statement();
		wrapper.setWhich(token);
		return wrapper;
	}

	@Override
	public void addFunction(AbstractFunction func)
	{
		
	}
	
	@Override
	public void addStatement(AbstractStatement stmt)
	{
		
	}
	
	// ================ Statements ================

	@Override
	public AbstractStatement newExitStatement(AbstractExpression code, AbstractToken source)
	{
		return wrapStatement(Java_ExitStatement.newExitStatement(code, source));
	}
	
	@Override
	public AbstractStatement newExpressionStatement(AbstractExpression expr, AbstractToken source)
	{
		return wrapStatement(Java_ExpressionStatement.newExpressionStatement(expr, source));
	}
	
	@Override
	public AbstractStatement newIfStatement(AbstractExpression condition, ArrayList<AbstractStatement> ifTrue,
			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
	{
		return wrapStatement(Java_IfStatement.newIfStatement(condition, ifTrue, ifFalse, source));
	}
	
	@Override
	public AbstractStatement newPrintStatement(AbstractExpression line, AbstractToken source)
	{
		return wrapStatement(Java_PrintStatement.newPrintStatement(line, source));
	}

	// ================ Expressions ================
	
	@Override
	public AbstractExpression newAdditiveExpression(AbstractExpression left, AdditiveEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Java_AdditiveExpression.generateExpression(left, oper, right, source));
	}

	@Override
	public AbstractExpression newAppendExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Java_AdditiveExpression.generateExpression(left, AdditiveEnum.PLUS, right, source));
	}
	
	@Override
	public AbstractExpression newAssignmentExpression(String name, AbstractExpression subscript,
			AssignmentEnum oper, AbstractExpression expression, String comment, AbstractToken source)
	{
		Java_Expression varExpr = wrapExpression(Java_VariableExpression.newVariableExpression(name, subscript, source));
		return wrapExpression(Java_AssignmentExpression.newAssignmentStatement(varExpr, oper, expression, comment, source));
	}
	
	@Override
	public AbstractExpression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		return wrapExpression(Java_BuiltIn.generateExpression(builtin, source));
	}
	
	@Override
	public AbstractExpression newExponentExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Java_MathPowFunc.generateExpression(left, right, source));
	}
	
	@Override
	public AbstractExpression newLengthFunction(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Java_LengthMethod.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newLiteralExpression(String literal, AbstractToken source)
	{
		return wrapExpression(Java_Literal.generateExpression(literal, source));
	}

	@Override
	public AbstractExpression newLogicalAndExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Java_LogicalAndExpression.generateExpression(left, right, source));
	}
	
	@Override
	public AbstractExpression newLogicalOrExpression(AbstractExpression left, LogicalOrEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Java_LogicalOrExpression.generateExpression(left, oper, right, source));
	}
	
	@Override
	public AbstractExpression newMultiplicativeExpression(AbstractExpression left, MultiplicativeEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Java_MultiplicativeExpression.generateExpression(left, oper, right, source));
	}

	@Override
	public AbstractExpression newNegativeExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Java_NegativeExpression.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newNotExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Java_LogicalNotExpression.generateExpression(expr, source));
	}

	@Override
	public AbstractExpression newNumberExpression(String number, AbstractToken source)
	{
		return wrapExpression(Java_Number.generateExpression(number, source));
	}

	@Override
	public AbstractExpression newParenthesizedExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Java_ParenthesizedExpression.generateExpression(expr, source));
	}

	@Override
	public AbstractExpression newRelationalExpression(AbstractExpression left, RelationalEnum relOp,
			AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Java_RelationalExpression.generateExpression(left, relOp, right, source));
	}
	
	@Override
	public AbstractExpression newSubstringFunction(AbstractExpression expr, AbstractExpression sc,
			SubstringEnum which, AbstractExpression scOrnc, AbstractToken source)
	{
		return wrapExpression(Java_SubstringMethod.generateExpression(expr, sc, which, scOrnc, source));
	}

	@Override
	public AbstractExpression newVariableExpression(String name, AbstractExpression subscript, AbstractToken source)
	{
		return wrapExpression(Java_VariableExpression.newVariableExpression(name, subscript, source));
	}
}

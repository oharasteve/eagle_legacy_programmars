// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Python;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.Python.Python_Statement.Python_SameLineStatement;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_StatementOrComment;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_BuiltIn;
import com.eagle.programmar.Python.Expressions.Python_Literals;
import com.eagle.programmar.Python.Expressions.Python_Logical_And_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression;
import com.eagle.programmar.Python.Expressions.Python_Multiplicative_Expression;
import com.eagle.programmar.Python.Expressions.Python_Negative_Expression;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.programmar.Python.Expressions.Python_Power_Expression;
import com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
import com.eagle.programmar.Python.Expressions.Python_SubscriptExpression;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Functions.Python_Len_Function;
import com.eagle.programmar.Python.Functions.Python_Str_Function;
import com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
import com.eagle.programmar.Python.Statements.Python_IfStatement;
import com.eagle.programmar.Python.Statements.Python_PrintStatement;
import com.eagle.programmar.Python.Statements.Python_QuitStatement;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;

public class Python_Generator extends EagleGenerator
{
	public Python3_Program _currentLanguage;
	
	public Python_Generator()
	{
		_currentLanguage = new Python3_Program();
		_currentLanguage.entries = new TokenList<Python_Statement>();
		_currentLanguage.entries.setPresent(true);
	}
	
	@Override
	public void addMain()
	{
		// Don't really need a main
	}

	@Override
	public String getName()
	{
		return "Python";
	}
	
	@Override
	public String getSuffix()
	{
		return ".py";
	}
	
	@Override
	public AbstractLanguage getTransfomedProgram()
	{
		return _currentLanguage;
	}
	
	public static Python_Expression wrapExpression(AbstractToken token)
	{
		Python_Expression wrapper = new Python_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	public static Python_Statement wrapStatement(AbstractToken token)
	{
		Python_Simple_Statement simple = new Python_Simple_Statement();
		simple.setWhich(token);
		Python_SameLineStatement sameLine = new Python_SameLineStatement();
		sameLine.statements = new SeparatedList<Python_Simple_Statement, PunctuationSemicolon>();
		sameLine.statements.addPrimaryElement(simple);
		Python_Statement wrapper = new Python_Statement();
		wrapper.statementOrComment = new Python_StatementOrComment();
		wrapper.statementOrComment.setWhich(sameLine);
		return wrapper;
	}

	private static Python_Expression maybeWrapStrFunction(AbstractExpression expression)
	{
		Python_Expression expr = (Python_Expression) expression;
		AbstractToken which = expr.getWhich();
		if (which instanceof Python_Literals)
		{
			return expr;
		}
		if (which instanceof Python_Additive_Expression)
		{
			Python_Additive_Expression adder = (Python_Additive_Expression) which;
			if (adder.left.getWhich() instanceof Python_Literals) return expr;
			if (adder.right.getWhich() instanceof Python_Literals) return expr;
		}
		Python_Expression newExpr = wrapExpression(Python_Str_Function.newStrFunction(expr));
		return newExpr;
	}
	
	@Override
	public void addFunction(AbstractFunction func)
	{
		
	}
	
	@Override
	public void addStatement(AbstractStatement stmt)
	{
		_currentLanguage.entries.addToken((Python_Statement) stmt);
	}

	@Override
	public AbstractType transformType(TypeEnum type, String typeName, AbstractToken source)
	{
		return Python_Type.transformType(type, typeName, source);
	}

	// ================ Statements ================

	@Override
	public AbstractStatement newDataDeclaration(String name, AbstractExpression size, AbstractType type,
			AbstractExpression initial, AbstractToken source)
	{
		return wrapStatement(Python_Data.newDataDeclaration(name,size, type, initial, source));
	}
	
	@Override
	public AbstractStatement newExitStatement(AbstractExpression code, AbstractToken source)
	{
		return wrapStatement(Python_QuitStatement.newQuitStatement(code, source));
	}

	@Override
	public AbstractStatement newExpressionStatement(AbstractExpression expr, AbstractToken source)
	{
		return wrapStatement(Python_ExpressionStatement.newExpressionStatement(expr, source));
	}
	
	@Override
	public AbstractStatement newIfStatement(AbstractExpression condition, ArrayList<AbstractStatement> ifTrue,
			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
	{
		return wrapStatement(Python_IfStatement.newIfStatement(condition, ifTrue, ifFalse, source));
	}
	
	@Override
	public AbstractStatement newPrintStatement(AbstractExpression line, AbstractToken source)
	{
		return wrapStatement(Python_PrintStatement.newPrintStatement(line, source));
	}

	// ================ Expressions ================
	
	@Override
	public AbstractExpression newAdditiveExpression(AbstractExpression left, AdditiveEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Python_Additive_Expression.generateExpression(left, oper, right, source));
	}

	@Override
	public AbstractExpression newAppendExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		Python_Expression leftExpr = maybeWrapStrFunction(left);
		Python_Expression rightExpr = maybeWrapStrFunction(right);
		return wrapExpression(Python_Additive_Expression.generateExpression(leftExpr, AdditiveEnum.PLUS, rightExpr, source));
	}
	
	@Override
	public AbstractExpression newAssignmentExpression(String name, AbstractExpression subscript,
			AssignmentEnum oper, AbstractExpression expression, String comment, AbstractToken source)
	{
		Python_Expression varExpr = wrapExpression(Python_VariableExpression.newVariableExpression(name, subscript, source));
		return wrapExpression(Python_Assignment_Expression.newAssignmentStatement(varExpr, oper, expression, comment, source));
	}
	
	@Override
	public AbstractExpression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		return wrapExpression(Python_BuiltIn.generateExpression(builtin, source));
	}
	
	@Override
	public AbstractExpression newExponentExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Python_Power_Expression.generateExpression(left, right, source));
	}
	
	@Override
	public AbstractExpression newLengthFunction(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Python_Len_Function.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newLiteralExpression(String literal, AbstractToken source)
	{
		return wrapExpression(Python_Literals.generateExpression(literal, source));
	}

	@Override
	public AbstractExpression newLogicalAndExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Python_Logical_And_Expression.generateExpression(left, right, source));
	}
	
	@Override
	public AbstractExpression newLogicalOrExpression(AbstractExpression left, LogicalOrEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Python_Logical_Or_Expression.generateExpression(left, oper, right, source));
	}
	
	@Override
	public AbstractExpression newMultiplicativeExpression(AbstractExpression left, MultiplicativeEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Python_Multiplicative_Expression.generateExpression(left, oper, right, source));
	}

	@Override
	public AbstractExpression newNegativeExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Python_Negative_Expression.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newNotExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Python_Logical_Not_Expression.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newNumberExpression(String number, AbstractToken source)
	{
		return wrapExpression(Python_Number.generateExpression(number, source));
	}
	
	@Override
	public AbstractExpression newParenthesizedExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Python_Parenthesized_Expression.generateExpression(expr, source));
	}

	@Override
	public AbstractExpression newRelationalExpression(AbstractExpression left, RelationalEnum relOp,
			AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Python_Relational_Expression.generateExpression(left, relOp, right, source));
	}
	
	@Override
	public AbstractExpression newSubstringFunction(AbstractExpression expr, AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression scOrnc, AbstractToken source)
	{
		return wrapExpression(Python_SubscriptExpression.generateExpression(expr, sc, whichSC, whichEC, scOrnc, source));
	}

	@Override
	public AbstractExpression newVariableExpression(String name, AbstractExpression subscript, AbstractToken source)
	{
		return wrapExpression(Python_VariableExpression.newVariableExpression(name, subscript, source));
	}
}

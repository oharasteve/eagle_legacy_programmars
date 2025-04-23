// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Python;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Python.Python_Statement.Python_MultilineStatement;
import com.eagle.programmar.Python.Python_Statement.Python_SameLineStatement;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_StatementOrComment;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_BuiltIn;
import com.eagle.programmar.Python.Expressions.Python_Function_Call;
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
import com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
import com.eagle.programmar.Python.Statements.Python_ForStatement;
import com.eagle.programmar.Python.Statements.Python_Function;
import com.eagle.programmar.Python.Statements.Python_IfStatement;
import com.eagle.programmar.Python.Statements.Python_PrintStatement;
import com.eagle.programmar.Python.Statements.Python_QuitStatement;
import com.eagle.programmar.Python.Statements.Python_WhileStatement;
import com.eagle.programmar.Python.Terminals.Python_HexNumber;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Python_Generator extends EagleGenerator
{
	public static String NAME = "Python";
	public static String SUFFIX = ".py";
	
	private Python_Program _program;
	private Python_Function _currentFunction = null;
	
	public Python_Generator()
	{
		_program = new Python3_Program();
		_program.entries = new TokenList<Python_Statement>();
		_program.entries.setPresent(true);
	}
	
	@Override
	public void addMain()
	{
		// Don't really need a main
	}

	@Override
	public String getName()
	{
		return NAME;
	}
	
	@Override
	public String getSuffix()
	{
		return SUFFIX;
	}
	
	@Override
	public AbstractLanguage getTransfomedProgram()
	{
		return _program;
	}
	
	public static Python_Expression wrapExpression(AbstractToken token)
	{
		Python_Expression wrapper = new Python_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	public static Python_Statement wrapStatement(AbstractToken token)
	{
		if (token == null) return null;
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
	
	@Override
	public Python_Function newFunction(String name, PrivacyEnum privacy, boolean isStatic, AbstractType type)
	{
		Python_Function newFunction = Python_Function.newPythonFunction(name);
		addStatement(wrapStatement(newFunction));
		_currentFunction = newFunction;	// Has to follow the call to addStatement().
		return newFunction;
	}
	
	@Override
	public void addFunctionParameter(AbstractFunction function, String name, AbstractType type)
	{
		Python_Function func = (Python_Function) function;
		func.addFunctionParameter(name);
	}
	
	@Override
	public void doneFunctionParameters()
	{
		_currentFunction = null;
	}
	
	@Override
	public void addStatement(AbstractStatement stmt)
	{
		if (_currentFunction == null)
		{
			_program.entries.addToken((Python_Statement) stmt);
		}
		else
		{
			Python_MultilineStatement multi = (Python_MultilineStatement) _currentFunction.header.defBody.getWhich();
			multi.statements.addToken((Python_Statement) stmt);
		}
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
	public Python_Statement newDoUntilStatement1(AbstractExpression condition,
			AbstractStatement action, AbstractToken source)
	{
		Python_WhileStatement doStmt = new Python_WhileStatement();
		return doStmt.generateDoUntil1((Python_Expression) condition,
				(Python_Statement) action, source);
	}
	
	@Override
	public Python_Statement newDoUntilStatement(AbstractExpression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		Python_WhileStatement doStmt = new Python_WhileStatement();
		return doStmt.generateDoUntil((Python_Expression) condition, actions, source);
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
	public AbstractStatement newIfStatement1(AbstractExpression condition, AbstractStatement ifTrue,
			AbstractStatement ifFalse, AbstractToken source)
	{
		Python_IfStatement ifStmt = new Python_IfStatement();
		return ifStmt.generateIfElse1((Python_Expression) condition,
				(Python_Statement) ifTrue,(Python_Statement) ifFalse, source);
	}
	
	@Override
	public AbstractStatement newIfStatement(AbstractExpression condition, ArrayList<AbstractStatement> ifTrue,
			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
	{
		Python_IfStatement ifStmt = new Python_IfStatement();
		return ifStmt.generateIfElse((Python_Expression) condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public AbstractStatement newForLoopStatement1(AbstractExpression init,
			AbstractExpression term, AbstractExpression incr, AbstractStatement action,
			AbstractToken source)
	{
		Python_ForStatement forStmt = new Python_ForStatement();
		return forStmt.generateForLoop1((Python_Expression) init, (Python_Expression) term,
				(Python_Expression) incr, (Python_Statement) action, source);
	}

	@Override
	public AbstractStatement newForLoopStatement(AbstractExpression init,
			AbstractExpression term, AbstractExpression incr,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		Python_ForStatement forStmt = new Python_ForStatement();
		return forStmt.generateForLoop((Python_Expression) init, (Python_Expression) term,
				(Python_Expression) incr, actions, source);
	}

	@Override
	public AbstractStatement newPrintStatement(AbstractExpression line, AbstractToken source)
	{
		return wrapStatement(Python_PrintStatement.newPrintStatement(line, source));
	}
	
	@Override
	public Python_Statement newWhileStatement1(AbstractExpression condition,
			AbstractStatement action, AbstractToken source)
	{
		Python_WhileStatement whileStmt = new Python_WhileStatement();
		return whileStmt.generateWhile1((Python_Expression) condition,
				(Python_Statement) action, source);
	}
	
	@Override
	public Python_Statement newWhileStatement(AbstractExpression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		Python_WhileStatement whileStmt = new Python_WhileStatement();
		return whileStmt.generateWhile((Python_Expression) condition, actions, source);
	}

	// ================ Expressions ================
	
	@Override
	public Python_Expression newAdditiveExpression(AbstractExpression left,
			AdditiveEnum oper, AbstractExpression right, AbstractToken source)
	{
		Python_Additive_Expression addExpr = new Python_Additive_Expression();
		return addExpr.generateAdditive((Python_Expression) left, oper,
				(Python_Expression) right, source);
	}

	@Override
	public Python_Expression newAppendExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		Python_Additive_Expression appendExpr = new Python_Additive_Expression();
		return appendExpr.generateAdditive((Python_Expression) left,
				AdditiveEnum.PLUS, (Python_Expression) right, source);
	}
	
	@Override
	public Python_Expression newAssignmentExpression(String name, AbstractExpression subscript,
			AssignmentEnum oper, AbstractExpression expression, AbstractToken source)
	{
		Python_VariableExpression varExpr = new Python_VariableExpression();
		Python_Expression var = varExpr.generateVarExpr(name,
				(Python_Expression) subscript, source);
		Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
		return asgExpr.generateAssignment(var, oper,
				(Python_Expression) expression, source);
	}
	
	@Override
	public Python_Expression newPostIncrementExpression(String name, AbstractExpression subscript,
			IncrementEnum oper, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}
	
	@Override
	public Python_Expression newPreIncrementExpression(String name, AbstractExpression subscript,
			IncrementEnum oper, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}
	
	@Override
	public Python_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		Python_BuiltIn built = new Python_BuiltIn();
		return built.generateBuiltIn(builtin, source);
	}
	
	@Override
	public Python_Expression newExponentExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(Python_Power_Expression.generateExpression(left, right, source));
	}
	
	@Override
	public Python_Expression newLengthFunction(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(Python_Len_Function.generateExpression(expr, source));
	}
	
	@Override
	public Python_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		return wrapExpression(Python_Literals.generateExpression(literal, source));
	}

	@Override
	public Python_Expression newLogicalAndExpression(AbstractExpression left,
			AbstractExpression right, AbstractToken source)
	{
		Python_Logical_And_Expression andExpr = new Python_Logical_And_Expression();
		return andExpr.generateLogicalAnd((Python_Expression) left,
				(Python_Expression) right, source);
	}
	
	@Override
	public Python_Expression newLogicalOrExpression(AbstractExpression left,
			LogicalOrEnum oper, AbstractExpression right, AbstractToken source)
	{
		Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
		return orExpr.generateLogicalOr((Python_Expression) left,
				oper, (Python_Expression) right, source);
	}
	
	@Override
	public Python_Expression newMultiplicativeExpression(AbstractExpression left,
			MultiplicativeEnum oper, AbstractExpression right, AbstractToken source)
	{
		Python_Multiplicative_Expression multExp = new Python_Multiplicative_Expression();
		return multExp.generateMultiplicative(
				(Python_Expression) left, oper, (Python_Expression) right, source);
	}

	@Override
	public Python_Expression newNegativeExpression(NegativeEnum sign,
			AbstractExpression expr, AbstractToken source)
	{
		Python_Negative_Expression negExp = new Python_Negative_Expression();
		return negExp.generateNegative(sign, (Python_Expression) expr, source);
	}
	
	@Override
	public Python_Expression newNotExpression(AbstractExpression expr, AbstractToken source)
	{
		Python_Logical_Not_Expression notExp = new Python_Logical_Not_Expression();
		return notExp.generateLogicalNot((Python_Expression) expr, source);
	}
	
	@Override
	public Python_Expression newNumberExpression(String number, AbstractToken source)
	{
		Python_Number num = new Python_Number();
		return wrapExpression(num.generateNumber(number, source));
	}
	
	@Override
	public Python_Expression newParenthesizedExpression(AbstractExpression expr, AbstractToken source)
	{
		Python_Parenthesized_Expression paren = new Python_Parenthesized_Expression();
		return paren.generateParentheses((Python_Expression) expr, source);
	}

	@Override
	public Python_Expression newRelationalExpression(AbstractExpression left, RelationalEnum relOp,
			AbstractExpression right, AbstractToken source)
	{
		Python_Relational_Expression relExp = new Python_Relational_Expression();
		return relExp.generateRelational((Python_Expression) left,
				relOp, (Python_Expression) right, source);
	}
	
	@Override
	public Python_Expression newSubstringFunction(AbstractExpression expr, AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression scOrnc, AbstractToken source)
	{
		return wrapExpression(Python_SubscriptExpression.generateExpression(expr, sc, whichSC, whichEC, scOrnc, source));
	}

	@Override
	public Python_Expression newVariableExpression(String name, AbstractExpression subscript, AbstractToken source)
	{
		Python_VariableExpression varExp = new Python_VariableExpression();
		return varExp.generateVarExpr(name, (Python_Expression) subscript, source);
	}
	
	@Override
	public Python_Expression newClassCreation(AbstractType type,
			Collection<AbstractExpression> args, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}
	
	@Override
	public Python_Expression newMethodInvocation(AbstractVariable var,
			Collection<AbstractExpression> args, AbstractToken source)
	{
		Python_Function_Call creat = new Python_Function_Call();
		return creat.generateInvocation((Python_Variable) var, args, source);
	}
	
	// ================ Terminals ================

	@Override
	public Python_Number newNumber(String value, AbstractToken source)
	{
		Python_Number num = new Python_Number();
		return num.generateNumber(value, source);
	}

	@Override
	public Python_HexNumber newHexNumber(String value, AbstractToken source)
	{
		Python_HexNumber num = new Python_HexNumber();
		return num.generateHexNumber(value, source);
	}

	@Override
	public Python_Literal newLiteral(String value, AbstractToken source)
	{
		Python_Literal lit = new Python_Literal();
		return lit.generateLiteral(value, source);
	}

	@Override
	public Python_Literal newCharLiteral(String value, AbstractToken source)
	{
		Python_Literal lit = new Python_Literal();
		return lit.generateLiteral(value, source);
	}
}

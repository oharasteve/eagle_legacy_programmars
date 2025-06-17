// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Python;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_StatementOrComment;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_Brackets;
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
import com.eagle.programmar.Python.Expressions.Python_Shift_Expression;
import com.eagle.programmar.Python.Expressions.Python_SubscriptExpression;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Functions.Python_Find_Function;
import com.eagle.programmar.Python.Functions.Python_Len_Function;
import com.eagle.programmar.Python.Functions.Python_Str_Function;
import com.eagle.programmar.Python.Functions.Python_Strip_Method;
import com.eagle.programmar.Python.Statements.Python_BreakStatement;
import com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
import com.eagle.programmar.Python.Statements.Python_ForStatement;
import com.eagle.programmar.Python.Statements.Python_Function;
import com.eagle.programmar.Python.Statements.Python_IfStatement;
import com.eagle.programmar.Python.Statements.Python_PrintStatement;
import com.eagle.programmar.Python.Statements.Python_QuitStatement;
import com.eagle.programmar.Python.Statements.Python_ReturnStatement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
import com.eagle.programmar.Python.Statements.Python_WhileStatement;
import com.eagle.programmar.Python.Terminals.Python_HexNumber;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Python_Generator extends EagleGenerator<Python_ComplexStatement,
		Python_Expression, Python_Variable, Python_Type>
{
	public static String NAME = "Python";
	public static String SUFFIX = ".py";
	
	private Python_Program _program;
	
	// Python requires functions to be declared (visible) before usage.
	// Also, you cannot access variables inside another function.
	// So, we split everything up into three groups and collect them separately.
	// At completion, we combine them back into a single program
	// See getTransformedProgram() for the combining logic.
	// See addStatement() for the logic splitting things up into three parts
	private ArrayList<Python_ComplexStatement> _globalData = new ArrayList<Python_ComplexStatement>();
	private ArrayList<Python_ComplexStatement> _allFunctions = new ArrayList<Python_ComplexStatement>();
	private ArrayList<Python_ComplexStatement> _mainLogic = new ArrayList<Python_ComplexStatement>();
	
	public Python_Generator(String mainName)
	{
		_program = new Python3_Program();
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
		_program.entries = new TokenList<Python_ComplexStatement>();
		_program.entries.setPresent(true);
		for (Python_ComplexStatement stmt1 : _globalData)
		{
			_program.entries.addToken(stmt1);
		}
		for (Python_ComplexStatement stmt2 : _allFunctions)
		{
			_program.entries.addToken(stmt2);
		}
		for (Python_ComplexStatement stmt3 : _mainLogic)
		{
			_program.entries.addToken(stmt3);
		}
		return _program;
	}
	
	public static Python_Expression wrapExpression(AbstractToken token)
	{
		if (token == null) return null;
		Python_Expression wrapper = new Python_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	public static Python_ComplexStatement wrapStatement(AbstractToken token)
	{
		if (token == null) return null;
		Python_Statement simple = new Python_Statement();
		simple.setWhich(token);
		Python_SameLineStatement sameLine = new Python_SameLineStatement();
		sameLine.statements = new SeparatedList<Python_Statement, PunctuationSemicolon>();
		sameLine.statements.addPrimaryElement(simple);
		Python_ComplexStatement wrapper = new Python_ComplexStatement();
		wrapper.statementOrComment = new Python_StatementOrComment();
		wrapper.statementOrComment.setWhich(sameLine);
		return wrapper;
	}
	
	@Override
	public Python_Type transformType(boolean isArray, TypeEnum type, String typeName, AbstractToken source)
	{
		return Python_Type.transformType(isArray, type, typeName, source);
	}

	// ================== Main program and class ==================
	
	private Python_Function _currentFunction = null;

	@Override
	public void addMethod(Python_Type returnType, String name, AbstractToken source)
	{
		_currentFunction = Python_Function.newPythonFunction(name);
		_allFunctions.add(wrapStatement(_currentFunction));
	}
	
	@Override
	public void addMethodParameter(Python_Type type, String name)
	{
		_currentFunction.addFunctionParameter(type, name);
	}
	
	@Override
	public void doneMethod()
	{
		_currentFunction = null;
	}

	@Override
	public void addStatement(Python_ComplexStatement stmt, AbstractToken source)
	{
		if (stmt == null) return;
		
		if (_currentFunction != null)
		{
			// Save everything inside the function, both data and logic
			Python_MultilineStatement multi =
					(Python_MultilineStatement) _currentFunction.header.defBody.getWhich();
			multi.statements.addToken(stmt);
			return;
		}
		
		// Cannot put data into the 'main' method when it was declared in a global area
		AbstractToken which = stmt.statementOrComment.getWhich();
		if (which instanceof Python_SameLineStatement)
		{
			Python_SameLineStatement same = (Python_SameLineStatement) which;
			if (same.statements.getPrimaryCount() == 1)
			{
				Python_Statement stmt1 = same.statements.first();
				if (stmt1.getWhich() instanceof Python_Data)
				{
					_globalData.add(stmt);
					return;
				}
			}
		}
		
		// Must be global logic (i.e., "main")
		_mainLogic.add(stmt);
	}

	@Override
	public void addComment(String comment, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}

	// ================ Statements ================

	@Override
	public Python_ComplexStatement newBlockStatement(
			ArrayList<Python_ComplexStatement> statements, AbstractToken source)
	{
		Python_StatementBlock block = new Python_StatementBlock();
		return block.addStatements(statements);
	}

	@Override
	public Python_ComplexStatement newBreakStatement(AbstractToken source)
	{
		Python_BreakStatement brkStmt = new Python_BreakStatement();
		return brkStmt.generateBreak(source);
	}

	@Override
	public Python_ComplexStatement newDataDeclaration(String name, Python_Expression size, Python_Type type,
			Python_Expression initial, AbstractToken source)
	{
		return wrapStatement(Python_Data.newDataDeclaration(name,size, type, initial, source));
	}

	@Override
	public Python_ComplexStatement newDoUntilStatement1(Python_Expression condition,
			Python_ComplexStatement action, AbstractToken source)
	{
		Python_WhileStatement doStmt = new Python_WhileStatement();
		return doStmt.generateDoUntil1(condition,
				action, source);
	}
	
	@Override
	public Python_ComplexStatement newDoUntilStatement(Python_Expression condition,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		Python_WhileStatement doStmt = new Python_WhileStatement();
		return doStmt.generateDoUntil(condition, actions, source);
	}
	
	@Override
	public Python_ComplexStatement newExitStatement(Python_Expression code, AbstractToken source)
	{
		return wrapStatement(Python_QuitStatement.newQuitStatement(code, source));
	}

	@Override
	public Python_ComplexStatement newExpressionStatement(Python_Expression expr, AbstractToken source)
	{
		return wrapStatement(Python_ExpressionStatement.newExpressionStatement(expr, source));
	}
	
	@Override
	public Python_ComplexStatement newIfStatement1(Python_Expression condition, Python_ComplexStatement ifTrue,
			Python_ComplexStatement ifFalse, AbstractToken source)
	{
		Python_IfStatement ifStmt = new Python_IfStatement();
		return ifStmt.generateIfElse1(condition,
				ifTrue,ifFalse, source);
	}
	
	@Override
	public Python_ComplexStatement newIfStatement(Python_Expression condition,
			ArrayList<Python_ComplexStatement> ifTrue,
			ArrayList<Python_ComplexStatement> ifFalse, AbstractToken source)
	{
		Python_IfStatement ifStmt = new Python_IfStatement();
		return ifStmt.generateIfElse(condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public Python_ComplexStatement newForLoopStatement1(Python_Expression init,
			Python_Expression term, Python_Expression incr, Python_ComplexStatement action,
			AbstractToken source)
	{
		Python_ForStatement forStmt = new Python_ForStatement();
		return forStmt.generateForLoop1(init, term,
				incr, action, source);
	}

	@Override
	public Python_ComplexStatement newForLoopStatement(Python_Expression init,
			Python_Expression term, Python_Expression incr,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		Python_ForStatement forStmt = new Python_ForStatement();
		return forStmt.generateForLoop(init, term,
				incr, actions, source);
	}

	@Override
	public Python_ComplexStatement newForRangeStatement1(Python_Variable var, Python_Expression first,
			RelationalEnum relOp, Python_Expression last, Python_Expression step,
			Python_ComplexStatement action, AbstractToken source)
	{
		Python_ForStatement forStmt = new Python_ForStatement();
		return forStmt.generateForRange1(var, first, relOp, last, step, action, source);
	}

	@Override
	public Python_ComplexStatement newForRangeStatement(Python_Variable var, Python_Expression first,
			RelationalEnum relOp, Python_Expression last, Python_Expression step,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		Python_ForStatement forStmt = new Python_ForStatement();
		return forStmt.generateForRange(var, first, relOp, last, step, actions, source);
	}

	@Override
	public Python_ComplexStatement newPrintStatement1(Python_Expression line, boolean newLine,
			AbstractToken source)
	{
		Python_PrintStatement prtStmt = new Python_PrintStatement();
		return prtStmt.generatePrint1(line, newLine, source);
	}
	
	@Override
	public Python_ComplexStatement newReturnStatement(Python_Expression ret,
			AbstractToken source)
	{
		Python_ReturnStatement retStmt = new Python_ReturnStatement();
		return retStmt.generateReturn(ret, source);
	}

	@Override
	public Python_ComplexStatement newWhileStatement1(Python_Expression condition,
			Python_ComplexStatement action, AbstractToken source)
	{
		Python_WhileStatement whileStmt = new Python_WhileStatement();
		return whileStmt.generateWhile1(condition,
				action, source);
	}
	
	@Override
	public Python_ComplexStatement newWhileStatement(Python_Expression condition,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		Python_WhileStatement whileStmt = new Python_WhileStatement();
		return whileStmt.generateWhile(condition, actions, source);
	}

	// ================ Expressions ================
	
	@Override
	public Python_Expression newAdditiveExpression(Oper2Types types, Python_Expression left,
			AdditiveEnum oper, Python_Expression right, AbstractToken source)
	{
		Python_Additive_Expression addExpr = new Python_Additive_Expression();
		return addExpr.generateAdditive(types, left, oper, right, source);
	}

	@Override
	public Python_Expression newAppendExpression(Oper2Types types,
			Python_Expression left, Python_Expression right, AbstractToken source)
	{
		Python_Additive_Expression appendExpr = new Python_Additive_Expression();
		return appendExpr.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
	}
	
	@Override
	public Python_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			Python_Expression subscript, AssignmentEnum oper, Python_Expression expression, AbstractToken source)
	{
		Python_VariableExpression varExpr = new Python_VariableExpression();
		Python_Expression var = varExpr.generateVarExpr(name, offset,
				subscript, source);
		Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
		return asgExpr.generateAssignment(var, oper, expression, source);
	}
	
	@Override
	public Python_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			Python_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		Python_VariableExpression varExpr = new Python_VariableExpression();
		Python_Expression var = varExpr.generateVarExpr(name, offset, subscript, source);
		Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
		Python_Expression one = newNumberExpression("1", null);
		AssignmentEnum asg;
		switch (oper)
		{
		case INCREMENT:
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case DECREMENT:
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected increment: " + oper);
		}
		return asgExpr.generateAssignment(var, asg, one, source);
	}
	
	@Override
	public Python_Expression newPreIncrementExpression(String name, SubscriptEnum offset,
			Python_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		// ++i and i++ are really the same in Python. Both map to i += 1
		return newPostIncrementExpression(name, offset, subscript, oper, source);
	}
	
	@Override
	public Python_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		Python_BuiltIn built = new Python_BuiltIn();
		return built.generateBuiltIn(builtin, source);
	}
	
	@Override
	public Python_Expression newExponentExpression(Python_Expression left, Python_Expression right, AbstractToken source)
	{
		return wrapExpression(Python_Power_Expression.generateExpression(left, right, source));
	}
	
	@Override
	public Python_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		return wrapExpression(Python_Literals.generateLiterals(literal, source));
	}

	@Override
	public Python_Expression newLogicalAndExpression(Python_Expression left,
			Python_Expression right, AbstractToken source)
	{
		Python_Logical_And_Expression andExpr = new Python_Logical_And_Expression();
		return andExpr.generateLogicalAnd(left,
				right, source);
	}
	
	@Override
	public Python_Expression newLogicalOrExpression(Python_Expression left,
			LogicalOrEnum oper, Python_Expression right, AbstractToken source)
	{
		Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
		return orExpr.generateLogicalOr(left,
				oper, right, source);
	}
	
	@Override
	public Python_Expression newMultiplicativeExpression(Python_Expression left,
			MultiplicativeEnum oper, Python_Expression right, AbstractToken source)
	{
		Python_Multiplicative_Expression multExp = new Python_Multiplicative_Expression();
		return multExp.generateMultiplicative(
				left, oper, right, source);
	}

	@Override
	public Python_Expression newNegativeExpression(NegativeEnum sign,
			Python_Expression expr, AbstractToken source)
	{
		Python_Negative_Expression negExp = new Python_Negative_Expression();
		return negExp.generateNegative(sign, expr, source);
	}
	
	@Override
	public Python_Expression newNotExpression(Python_Expression expr, AbstractToken source)
	{
		Python_Logical_Not_Expression notExp = new Python_Logical_Not_Expression();
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof Python_Parenthesized_Expression)
		{
			return notExp.generateLogicalNot(expr, source);
		}

		Python_Parenthesized_Expression parens = new Python_Parenthesized_Expression();
		parens.generateParentheses(expr, source);
		return notExp.generateLogicalNot(Python_Generator.wrapExpression(parens), source);
	}
	
	@Override
	public AbstractExpression newLogicalExpression(boolean bool, AbstractToken source)
	{
		Python_BuiltIn builtin = new Python_BuiltIn();
		builtin.builtins.setValue(bool ? "True" : "False");
		return wrapExpression(builtin);
	}

	@Override
	public Python_Expression newNumberExpression(String number, AbstractToken source)
	{
		Python_Number num = new Python_Number();
		return wrapExpression(num.generateNumber(number, source));
	}
	
	@Override
	public Python_Expression newParenthesizedExpression(Python_Expression expr, AbstractToken source)
	{
		Python_Parenthesized_Expression paren = new Python_Parenthesized_Expression();
		return paren.generateParentheses(expr, source);
	}

	@Override
	public Python_Expression newRelationalExpression(Oper2Types types, Python_Expression left,
			RelationalEnum relOp, Python_Expression right, AbstractToken source)
	{
		Python_Relational_Expression relExp = new Python_Relational_Expression();
		return relExp.generateRelational(types, left, relOp, right, source);
	}
	
	@Override
	public Python_Expression newShiftExpression(Python_Expression left,
			ShiftEnum shift, Python_Expression right, AbstractToken source)
	{
		Python_Shift_Expression shiftExpr = new Python_Shift_Expression();
		return shiftExpr.generateShift(left, shift,
				right, source);
	}

	@Override
	public Python_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		Python_Brackets exprList = new Python_Brackets();
		return exprList.generateArray(exprs, source);
	}

	@Override
	public Python_Expression newVariableExpression(String name, SubscriptEnum offset,
			Python_Expression subscript, AbstractToken source)
	{
		Python_VariableExpression varExp = new Python_VariableExpression();
		return varExp.generateVarExpr(name, offset, subscript, source);
	}
	
	@Override
	public Python_Variable newVariable(String name)
	{
		return Python_Variable.newVariable(name);
	}
	
	@Override
	public Python_Expression newClassCreation(Python_Type type,
			ArrayList<Python_Expression> args, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}
	
	@Override
	public Python_Expression newMethodInvocation(Python_Variable var,
			ArrayList<Python_Expression> args, AbstractToken source)
	{
		Python_Function_Call creat = new Python_Function_Call();
		return creat.generateInvocation(var, args, source);
	}
	
	@Override
	public Python_Expression newCurrentDatetime()
	{
		throw new RuntimeException("Need to implement");
	}
	
	// ================ Functions ================

	@Override
	public Python_Expression newLengthFunction(Python_Expression expr, AbstractToken source)
	{
		Python_Len_Function lenFn = new Python_Len_Function();
		return lenFn.generateLength(expr, source);
	}
	
	@Override
	public Python_Expression newTrimFunction(Python_Expression expr, AbstractToken source)
	{
		Python_Strip_Method stripMeth = new Python_Strip_Method();
		return stripMeth.generateTrim(expr, source);
	}

	@Override
	public Python_Expression newStringFunction(Python_Expression expr, AbstractToken source)
	{
		Python_Str_Function strFn = new Python_Str_Function();
		return strFn.generateString(expr, source);
	}
	
	@Override
	public Python_Expression newSubstringFunction(Python_Expression expr, Python_Expression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, Python_Expression scOrnc,
			boolean ncMightBeTooBig, AbstractToken source)
	{
		return wrapExpression(Python_SubscriptExpression.generateExpression(expr, sc, whichSC,
				whichEC, scOrnc, ncMightBeTooBig, source));
	}

	@Override
	public Python_Expression newIndexOfFunction(Python_Expression string,
			Python_Expression patt, Python_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Python_Find_Function indexFn = new Python_Find_Function();
		return indexFn.generateIndexOf(string, patt, sc, whichSC, source);
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

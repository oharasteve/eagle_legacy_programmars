// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Python;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_StatementOrComment;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_Bitwise_Expression;
import com.eagle.programmar.Python.Expressions.Python_Bitwise_Not_Expression;
import com.eagle.programmar.Python.Expressions.Python_BracesColons;
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
import com.eagle.programmar.Python.Functions.Python_Abs_Function;
import com.eagle.programmar.Python.Functions.Python_Int_Function;
import com.eagle.programmar.Python.Functions.Python_Len_Function;
import com.eagle.programmar.Python.Functions.Python_Print_Function;
import com.eagle.programmar.Python.Functions.Python_Str_Function;
import com.eagle.programmar.Python.Methods.Python_EndsWith_Method;
import com.eagle.programmar.Python.Methods.Python_Find_Method;
import com.eagle.programmar.Python.Methods.Python_StartsWith_Method;
import com.eagle.programmar.Python.Methods.Python_Strip_Method;
import com.eagle.programmar.Python.Statements.Python_BreakStatement;
import com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
import com.eagle.programmar.Python.Statements.Python_ForStatement;
import com.eagle.programmar.Python.Statements.Python_Function;
import com.eagle.programmar.Python.Statements.Python_GlobalStatement;
import com.eagle.programmar.Python.Statements.Python_IfStatement;
import com.eagle.programmar.Python.Statements.Python_MatchStatement;
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
import com.eagle.transform.EagleGenerator;

public class Python_Generator
		extends EagleGenerator<Python_ComplexStatement, Python_Expression, Python_Variable, Python_Type>
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

	public Python_Generator(ParserManager parser, String mainName)
	{
		super(parser);
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
	public String mainName()
	{
		return "Main";
	}

	@Override
	public void addMainArgs()
	{
		addMethodParameter(null, "args");
	}

	@Override
	public void addCallToMain()
	{
		Python_Variable mainVar = newVariable(mainName());
		ArrayList<Python_Expression> args = new ArrayList<Python_Expression>();
		Python_Expression none = Python_BuiltIn.generateBuiltIn(BuiltInEnum.NULL, null);
		args.add(none);
		Python_Expression mainExpr = Python_Function_Call.generateInvocation(mainVar, args, null);
		Python_ComplexStatement mainStmt = Python_ExpressionStatement.newExpressionStatement(mainExpr, null);
		addStatement(mainStmt, null);
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
	public Python_Type transformType(TypeEnum type, String typeName, AbstractToken source)
	{
		return Python_Type.transformType(type, typeName, source);
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
			Python_MultilineStatement multi = (Python_MultilineStatement) _currentFunction.header.defBody.getWhich();
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
		return Python_BreakStatement.generateBreak(source);
	}

	@Override
	public Python_ComplexStatement newDataDeclaration(boolean isStatic, String name, Python_Expression size,
			Python_Type type, Python_Expression initial, AbstractToken source)
	{
		return wrapStatement(Python_Data.newDataDeclaration(name, size, type, initial, source));
	}

	@Override
	public Python_ComplexStatement newDoUntilStatement1(Python_Expression condition,
			Python_ComplexStatement action, AbstractToken source)
	{
		return Python_WhileStatement.generateDoUntilOne(condition, action, source);
	}

	@Override
	public Python_ComplexStatement newDoUntilStatement(Python_Expression condition,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		return Python_WhileStatement.generateDoUntilMany(condition, actions, source);
	}

	@Override
	public Python_ComplexStatement newExitStatement(Python_Expression code, AbstractToken source)
	{
		return Python_QuitStatement.newQuitStatement(code, source);
	}

	@Override
	public Python_ComplexStatement newExpressionStatement(Python_Expression expr, AbstractToken source)
	{
		return Python_ExpressionStatement.newExpressionStatement(expr, source);
	}

	@Override
	public Python_ComplexStatement newGlobalVariable(String variableName, AbstractToken source)
	{
		if (_currentFunction == null)
		{
			return null;	// Don't add 'global' variables in Python at the top level
		}
		return Python_GlobalStatement.generateGlobal(variableName, source);
	}

	@Override
	public Python_ComplexStatement newIfStatement1(Python_Expression condition, Python_ComplexStatement ifTrue,
			Python_ComplexStatement ifFalse, AbstractToken source)
	{
		return Python_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Python_ComplexStatement newIfStatement(Python_Expression condition,
			ArrayList<Python_ComplexStatement> ifTrue,
			ArrayList<Python_ComplexStatement> ifFalse, AbstractToken source)
	{
		return Python_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Python_ComplexStatement newForLoopStatement1(Python_Expression init,
			Python_Expression term, Python_Expression incr, Python_ComplexStatement action,
			AbstractToken source)
	{
		return Python_ForStatement.generateForLoopOne(init, term,
				incr, action, source);
	}

	@Override
	public Python_ComplexStatement newForLoopStatement(Python_Expression init,
			Python_Expression term, Python_Expression incr,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		return Python_ForStatement.generateForLoopMany(init, term,
				incr, actions, source);
	}

	@Override
	public Python_ComplexStatement newForRangeStatement1(Python_Variable var, TypeEnum type,
			Python_Expression first, RelationalEnum relOp, Python_Expression last,
			Python_Expression step, Python_ComplexStatement action, AbstractToken source)
	{
		return Python_ForStatement.generateForRangeOne(var, first, relOp, last, step, action, source);
	}

	@Override
	public Python_ComplexStatement newForRangeStatement(Python_Variable var, TypeEnum type,
			Python_Expression first, RelationalEnum relOp, Python_Expression last,
			Python_Expression step, ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		return Python_ForStatement.generateForRangeMany(var, first, relOp, last, step, actions, source);
	}

	@Override
	public Python_Expression newPrintFunction(Python_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		return Python_Print_Function.generatePrintFunc(line, type, newLine, source);
	}

	@Override
	public Python_ComplexStatement newPrintStatement(Python_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		Python_Expression prtExpr = newPrintFunction(line, type, newLine, toErr, source);
		return newExpressionStatement(prtExpr, source);
	}

	@Override
	public Python_ComplexStatement newReturnStatement(Python_Expression ret,
			AbstractToken source)
	{
		return Python_ReturnStatement.generateReturn(ret, source);
	}

	@Override
	public Python_ComplexStatement newSwitchStatement(Python_Expression expr,
			ArrayList<Python_Expression> values, ArrayList<ArrayList<Python_ComplexStatement>> cases,
			ArrayList<Python_ComplexStatement> defaultCase, AbstractToken source)
	{
		return Python_MatchStatement.generateMatch(expr, values, cases, defaultCase, source);
	}

	@Override
	public Python_ComplexStatement newWhileStatement1(Python_Expression condition,
			Python_ComplexStatement action, AbstractToken source)
	{
		return Python_WhileStatement.generateWhileOne(condition, action, source);
	}

	@Override
	public Python_ComplexStatement newWhileStatement(Python_Expression condition,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		return Python_WhileStatement.generateWhileMany(condition, actions, source);
	}

	// ================ Expressions ================

	@Override
	public Python_Expression newAdditiveExpression(Oper2Types types, Python_Expression left,
			AdditiveEnum oper, Python_Expression right, AbstractToken source)
	{
		return Python_Additive_Expression.generateAdditive(types, left, oper, right, source);
	}

	@Override
	public Python_Expression newAppendExpression(Oper2Types types,
			Python_Expression left, Python_Expression right, AbstractToken source)
	{
		return Python_Additive_Expression.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
	}

	@Override
	public Python_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			Python_Expression subscript, AssignmentEnum oper, Python_Expression expression,
			AbstractToken source)
	{
		Python_Variable var = Python_Variable.newVariable(name);
		return Python_Assignment_Expression.generateAssignment(var, subscript, oper, expression, source);
	}

	@Override
	public AbstractExpression newHashAssignment(String name, Python_Expression subscript,
			Python_Expression expression, AbstractToken source)
	{
		return newAssignmentExpression(name, SubscriptEnum.FIRST_IS_ZERO, subscript,
				AssignmentEnum.EQUALS, expression, source);
	}

	@Override
	public Python_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			Python_Expression subscript, IncrementEnum incr, AbstractToken source)
	{
		Python_Variable var = Python_Variable.newVariable(name);
		Python_Expression one = newNumberExpression("1", null);
		AssignmentEnum oper;
		switch (incr)
		{
		case INCREMENT:
			oper = AssignmentEnum.PLUS_EQUALS;
			break;
		case DECREMENT:
			oper = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected increment: " + incr);
		}
		return Python_Assignment_Expression.generateAssignment(var, subscript, oper, one, source);
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
		return Python_BuiltIn.generateBuiltIn(builtin, source);
	}

	@Override
	public Python_Expression newExponentExpression(Python_Expression left, Python_Expression right,
			AbstractToken source)
	{
		return Python_Power_Expression.generateExpression(left, right, source);
	}

	@Override
	public Python_Expression newAbsFunction(Python_Expression expr, AbstractToken source)
	{
		return Python_Abs_Function.generateAbsFunc(expr, source);
	}

	@Override
	public Python_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		return Python_Literals.generateLiteralsExpression(literal, source);
	}

	@Override
	public Python_Expression newLogicalAndExpression(Python_Expression left,
			Python_Expression right, AbstractToken source)
	{
		return Python_Logical_And_Expression.generateLogicalAnd(left, right, source);
	}

	@Override
	public Python_Expression newLogicalOrExpression(Python_Expression left,
			LogicalOrEnum oper, Python_Expression right, AbstractToken source)
	{
		return Python_Logical_Or_Expression.generateLogicalOr(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseExpression(Python_Expression left,
			BitwiseEnum oper, Python_Expression right, AbstractToken source)
	{
		return Python_Bitwise_Expression.generateBitwise(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseNotExpression(Python_Expression expr,
			AbstractToken source)
	{
		return Python_Bitwise_Not_Expression.generateBitwiseNot(expr, source);
	}

	@Override
	public Python_Expression newMultiplicativeExpression(Python_Expression left,
			MultiplicativeEnum oper, Python_Expression right, AbstractToken source)
	{
		return Python_Multiplicative_Expression.generateMultiplicative(
				left, oper, right, source);
	}

	@Override
	public Python_Expression newNegativeExpression(NegativeEnum sign,
			Python_Expression expr, AbstractToken source)
	{
		return Python_Negative_Expression.generateNegative(sign, expr, source);
	}

	@Override
	public Python_Expression newTruncateExpression(Python_Expression expr, AbstractToken source)
	{
		return Python_Int_Function.generateInteger(expr, source);
	}

	@Override
	public Python_Expression newLogicalNotExpression(Python_Expression expr, AbstractToken source)
	{
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof Python_Parenthesized_Expression)
		{
			return Python_Logical_Not_Expression.generateLogicalNot(expr, source);
		}

		Python_Expression parens = Python_Parenthesized_Expression.generateParentheses(expr, source);
		return Python_Logical_Not_Expression.generateLogicalNot(parens, source);
	}

	@Override
	public Python_Expression newLogicalExpression(boolean bool, AbstractToken source)
	{
		return Python_BuiltIn.generateBuiltIn(
				(bool ? BuiltInEnum.TRUE : BuiltInEnum.FALSE), source);
	}

	@Override
	public Python_Expression newNumberExpression(String number, AbstractToken source)
	{
		return Python_Number.generateNumberExpression(number, source);
	}

	@Override
	public Python_Expression newParenthesizedExpression(Python_Expression expr, AbstractToken source)
	{
		return Python_Parenthesized_Expression.generateParentheses(expr, source);
	}

	@Override
	public Python_Expression newRelationalExpression(Oper2Types types, Python_Expression left,
			RelationalEnum relOp, Python_Expression right, AbstractToken source)
	{
		return Python_Relational_Expression.generateRelational(types, left, relOp, right, source);
	}

	@Override
	public Python_Expression newShiftExpression(Python_Expression left,
			ShiftEnum shift, Python_Expression right, AbstractToken source)
	{
		return Python_Shift_Expression.generateShift(left, shift, right, source);
	}

	@Override
	public Python_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		return Python_Brackets.generateArray(exprs, source);
	}

	@Override
	public Python_Expression newVariableExpression(String name, SubscriptEnum offset,
			Python_Expression subscript, AbstractToken source)
	{
		return Python_VariableExpression.generateVariableExpression(name, offset, subscript, source);
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
		return Python_BracesColons.generateDictionary(source);
	}

	@Override
	public Python_Expression newMethodInvocation(Python_Variable var,
			ArrayList<Python_Expression> args, AbstractToken source)
	{
		return Python_Function_Call.generateInvocation(var, args, source);
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
		return Python_Len_Function.generateLength(expr, source);
	}

	@Override
	public Python_Expression newTrimFunction(Python_Expression expr, AbstractToken source)
	{
		return Python_Strip_Method.generateTrim(expr, source);
	}

	@Override
	public Python_Expression newStringFunction(Oper1Types types, Python_Expression expr, AbstractToken source)
	{
		return Python_Str_Function.generateString(types, expr, source);
	}

	@Override
	public Python_Expression newSubstringFunction(Python_Expression expr, Python_Expression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, Python_Expression ecOrnc,
			boolean ncMightBeTooBig, AbstractToken source)
	{
		return Python_SubscriptExpression.generateExpression(expr, sc, whichSC,
				whichEC, ecOrnc, ncMightBeTooBig, source);
	}

	@Override
	public AbstractExpression newEndsWithFunction(Python_Expression expr, Python_Expression patt,
			AbstractToken source)
	{
		return Python_EndsWith_Method.generateEndsWith(expr, patt, source);
	}

	@Override
	public Python_Expression newStartsWithFunction(Python_Expression expr, Python_Expression patt,
			Python_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		return Python_StartsWith_Method.generateStartsWith(expr, patt, sc, whichSC, source);
	}

	@Override
	public Python_Expression newIndexOfFunction(Python_Variable string, Python_Expression patt,
			Python_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		return Python_Find_Method.generateIndexOf(string, patt, sc, whichSC, source);
	}

	@Override
	public Python_Expression newFormatNumber(Python_Expression expr, int length,
			AbstractToken source)
	{
		Python_Expression fmt = newLiteralExpression("%" + length + "d", null);
		return Python_Multiplicative_Expression.generateMultiplicative(fmt, MultiplicativeEnum.REMAINDER, expr, source);
	}

	@Override
	public Python_Expression newFormatDecimal(Python_Expression expr, int decimals,
			AbstractToken source)
	{
		Python_Expression fmt = newLiteralExpression("%." + decimals + "f", null);
		return Python_Multiplicative_Expression.generateMultiplicative(fmt, MultiplicativeEnum.REMAINDER, expr, source);
	}

	// ================ Terminals ================

	@Override
	public Python_Number newNumber(String value, AbstractToken source)
	{
		return Python_Number.generateNumber(value, source);
	}

	@Override
	public Python_HexNumber newHexNumber(String value, AbstractToken source)
	{
		return Python_HexNumber.generateHexNumber(value, source);
	}

	@Override
	public Python_Literal newLiteral(String value, AbstractToken source)
	{
		return Python_Literal.generateLiteral(value, source);
	}

	@Override
	public Python_Literal newCharLiteral(String value, AbstractToken source)
	{
		return newLiteral(value, source);
	}
}

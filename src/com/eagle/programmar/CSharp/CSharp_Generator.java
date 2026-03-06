// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.CSharp;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseNotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
import com.eagle.programmar.CSharp.Expressions.CSharp_CastExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithInitializers;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalAndExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalOrExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_MethodInvocation;
import com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PreIncrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ShiftExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
import com.eagle.programmar.CSharp.Functions.CSharp_MathAbsFunc;
import com.eagle.programmar.CSharp.Functions.CSharp_MathPowFunc;
import com.eagle.programmar.CSharp.Functions.CSharp_PrintFunction;
import com.eagle.programmar.CSharp.Functions.CSharp_StringFormatFunc;
import com.eagle.programmar.CSharp.Methods.CSharp_EndsWithMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_IndexOfMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_LengthMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_StartsWithMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_SubstringMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_ToStringMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_TrimMethod;
import com.eagle.programmar.CSharp.Statements.CSharp_BreakStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_DoWhileStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExitStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExpressionStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ForStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_IfStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ReturnStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Statements.CSharp_SwitchStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_WhileStatement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;

public class CSharp_Generator extends EagleGenerator<CSharp_Statement, CSharp_Expression, CSharp_Variable, CSharp_Type>
{
	public static String NAME = "C#";
	public static String SUFFIX = ".cs";

	private CSharp_Program _program;
	private String _className;

	public CSharp_Generator(ParserManager parser, String className)
	{
		super(parser);
		_program = new CSharp_Program();
		_className = className;
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
		CSharp_Type paramType = transformType(TypeEnum.STRING_ARRAY, null, null);
		addMethodParameter(paramType, "args");
	}

	@Override
	public void addCallToMain()
	{
		// Don't ever need this in C#
	}

	@Override
	public AbstractLanguage getTransfomedProgram()
	{
		return _program;
	}

	public static CSharp_Expression wrapExpression(AbstractToken token)
	{
		if (token == null) return null;
		CSharp_Expression wrapper = new CSharp_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}

	public static CSharp_Statement wrapStatement(AbstractToken token)
	{
		if (token == null) return null;
		CSharp_Statement wrapper = new CSharp_Statement();
		wrapper.setWhich(token);
		return wrapper;
	}

	@Override
	public CSharp_Type transformType(TypeEnum type,
			String typeName, AbstractToken source)
	{
		return CSharp_Type.transformType(type, typeName, source);
	}

	// ================== Main program and class ==================

	private CSharp_Class _currentClass = null;
	private CSharp_Method _currentMethod = null;
	private CSharp_Method _previousMethod = null;

	private void checkClass()
	{
		if (_currentClass == null)
		{
			_currentClass = new CSharp_Class();
			_currentClass.newCSharpClass(PrivacyEnum.PUBLIC, _className);
			_program.addClass(_currentClass);
		}
	}

	private void checkMethod()
	{
		checkClass();

		if (_currentMethod == null)
		{
			CSharp_Type mainType = CSharp_Type.newPrimitiveType("void");
			_currentMethod = new CSharp_Method();
			_currentMethod.newCSharpMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC,
					mainType, "Main");
			_currentClass.addMethod(_currentMethod);

			CSharp_Type paramType = CSharp_Type.transformTypeArray(TypeEnum.STRING);
			_currentMethod.addMethodParameter(paramType, "args");
		}
	}

	@Override
	public void addMethod(CSharp_Type returnType, String name, AbstractToken source)
	{
		checkClass();

		_previousMethod = _currentMethod;
		_currentMethod = new CSharp_Method();
		_currentMethod.newCSharpMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC,
				returnType, name);
		_currentMethod.setTransformationSource(source);
		_currentClass.addMethod(_currentMethod);
	}

	@Override
	public void addMethodParameter(CSharp_Type type, String name)
	{
		_currentMethod.addMethodParameter(type, name);
	}

	@Override
	public void doneMethod()
	{
		_currentMethod = _previousMethod;
	}

	@Override
	public void addStatement(CSharp_Statement stmt, AbstractToken source)
	{
		if (stmt == null) return;
		checkClass();

		// Cannot put data into the 'main' method when it was declared in a global area
		if (stmt.getWhich() instanceof CSharp_Data)
		{
			boolean saveInClass = false;
			if (_currentMethod == null)
			{
				saveInClass = true;
			}
			else if (_currentMethod.id.getValue().equals("Main"))
			{
				saveInClass = true;
			}

			if (saveInClass)
			{
				CSharp_Data data = (CSharp_Data) stmt.getWhich();
				data.addModifier("static");

				// Put it in top-level class, not the 'main' method
				CSharp_ClassElement element = new CSharp_ClassElement();
				element.setWhich(stmt);
				_currentClass.elements.addToken(element);
				return;
			}
		}

		checkMethod();

		CSharp_MethodImplementation impl = (CSharp_MethodImplementation) _currentMethod.body.getWhich();
		CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
		stmtOrComment.setWhich(stmt);
		stmtOrComment.setTransformationSource(source);
		impl.block.statements.addToken(stmtOrComment);
	}

	@Override
	public void addComment(String comment, AbstractToken source)
	{
		CSharp_Comment comm = new CSharp_Comment(comment);
		comm.setTransformationSource(source);
		if (_currentMethod != null)
		{
			_currentMethod.addComment(comm);
		}
		else if (_currentClass != null)
		{
			_currentClass.addComment(comm);
		}
		else
		{
			_program.addComment(comm);
		}
	}

	// ================ Statements ================

	@Override
	public CSharp_Statement newBlockStatement(
			ArrayList<CSharp_Statement> statements, AbstractToken source)
	{
		return CSharp_StatementBlock.generateBlock(statements, source);
	}

	@Override
	public CSharp_Statement newBreakStatement(AbstractToken source)
	{
		return CSharp_BreakStatement.generateBreak(source);
	}

	@Override
	public CSharp_Statement newDataDeclaration(boolean isStatic, String name, CSharp_Expression size, CSharp_Type type,
			CSharp_Expression initial, AbstractToken source)
	{
		return wrapStatement(CSharp_Data.newDataDeclaration(isStatic, name, size, type, initial, source));
	}

	@Override
	public CSharp_Statement newDoUntilStatement1(CSharp_Expression condition,
			CSharp_Statement action, AbstractToken source)
	{
		return CSharp_DoWhileStatement.generateDoUntilOne(condition, action, source);
	}

	@Override
	public CSharp_Statement newDoUntilStatement(CSharp_Expression condition,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		return CSharp_DoWhileStatement.generateDoUntilMany(condition, actions, source);
	}

	@Override
	public CSharp_Statement newExpressionStatement(CSharp_Expression expr, AbstractToken source)
	{
		return wrapStatement(CSharp_ExpressionStatement.newExpressionStatement(expr, source));
	}

	@Override
	public CSharp_Statement newExitStatement(CSharp_Expression code, AbstractToken source)
	{
		return wrapStatement(CSharp_ExitStatement.newExitStatement(code, source));
	}

	@Override
	public CSharp_Statement newGlobalVariable(String variableName, AbstractToken source)
	{
		return null;	// Don't need to declare variables as 'global'
	}

	@Override
	public CSharp_Statement newForLoopStatement1(CSharp_Expression init,
			CSharp_Expression term, CSharp_Expression incr, CSharp_Statement action,
			AbstractToken source)
	{
		return CSharp_ForStatement.generateForLoopOne(init, term, incr, action, source);
	}

	@Override
	public CSharp_Statement newForLoopStatement(CSharp_Expression init,
			CSharp_Expression term, CSharp_Expression incr,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		return CSharp_ForStatement.generateForLoopMany(init, term,
				incr, actions, source);
	}

	@Override
	public CSharp_Statement newForRangeStatement1(CSharp_Variable var, TypeEnum type,
			CSharp_Expression first, RelationalEnum relOper, CSharp_Expression last,
			CSharp_Expression step, CSharp_Statement action, AbstractToken source)
	{
		return CSharp_ForStatement.generateForRangeOne(var, type, first, relOper, last, step, action, source);
	}

	@Override
	public CSharp_Statement newForRangeStatement(CSharp_Variable var, TypeEnum type,
			CSharp_Expression first, RelationalEnum relOper, CSharp_Expression last,
			CSharp_Expression step, ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		return CSharp_ForStatement.generateForRangeMany(var, type, first,
				relOper, last, step, actions, source);
	}

	@Override
	public CSharp_Statement newIfStatement1(CSharp_Expression condition,
			CSharp_Statement ifTrue, CSharp_Statement ifFalse, AbstractToken source)
	{
		return CSharp_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
	}

	@Override
	public CSharp_Statement newIfStatement(CSharp_Expression condition,
			ArrayList<CSharp_Statement> ifTrue,
			ArrayList<CSharp_Statement> ifFalse, AbstractToken source)
	{
		return CSharp_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
	}

	@Override
	public CSharp_Expression newPrintFunction(CSharp_Expression line,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		return CSharp_PrintFunction.generatePrintFunc(line, newLine, toErr, source);
	}

	@Override
	public CSharp_Statement newPrintStatement(CSharp_Expression line,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		CSharp_Expression prtExpr = newPrintFunction(line, newLine, toErr, source);
		return newExpressionStatement(prtExpr, source);
	}

	@Override
	public CSharp_Statement newReturnStatement(CSharp_Expression ret,
			AbstractToken source)
	{
		return CSharp_ReturnStatement.generateReturn(ret, source);
	}

	@Override
	public CSharp_Statement newSwitchStatement(CSharp_Expression expr,
			ArrayList<CSharp_Expression> values, ArrayList<ArrayList<CSharp_Statement>> cases,
			ArrayList<CSharp_Statement> defaultCase, AbstractToken source)
	{
		return CSharp_SwitchStatement.generateSwitch(expr, values, cases, defaultCase, source);
	}

	@Override
	public CSharp_Statement newWhileStatement1(CSharp_Expression condition,
			CSharp_Statement action, AbstractToken source)
	{
		return CSharp_WhileStatement.generateWhileOne(condition, action, source);
	}

	@Override
	public CSharp_Statement newWhileStatement(CSharp_Expression condition,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		return CSharp_WhileStatement.generateWhileMany(condition, actions, source);
	}

	// ================ Expressions ================

	@Override
	public CSharp_Expression newAdditiveExpression(Oper2Types types,
			CSharp_Expression left, AdditiveEnum oper, CSharp_Expression right, AbstractToken source)
	{
		return CSharp_AdditiveExpression.generateAdditive(types, left, oper, right, source);
	}

	@Override
	public CSharp_Expression newAppendExpression(Oper2Types types,
			CSharp_Expression left, CSharp_Expression right, AbstractToken source)
	{
		return CSharp_AdditiveExpression.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
	}

	@Override
	public CSharp_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, AssignmentEnum oper, CSharp_Expression expression,
			AbstractToken source)
	{
		CSharp_Variable var = CSharp_Variable.newVariable(name);
		return CSharp_AssignmentExpression.generateAssignment(var, subscript, oper, expression, source);
	}

	@Override
	public AbstractExpression newHashAssignment(String name, CSharp_Expression subscript,
			CSharp_Expression expression, AbstractToken source)
	{
		return newAssignmentExpression(name, SubscriptEnum.FIRST_IS_ZERO, subscript,
				AssignmentEnum.EQUALS, expression, source);
	}

	@Override
	public CSharp_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		CSharp_Variable var = CSharp_Variable.newVariable(name);
		return CSharp_PostIncrementExpression.generateIncrement(var, oper, source);
	}

	@Override
	public CSharp_Expression newPreIncrementExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		CSharp_Variable var = CSharp_Variable.newVariable(name);
		return CSharp_PreIncrementExpression.generateIncrement(var, oper, source);
	}

	@Override
	public CSharp_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		return CSharp_BuiltIn.generateBuiltIn(builtin, source);
	}

	@Override
	public CSharp_Expression newExponentExpression(CSharp_Expression left, CSharp_Expression right,
			AbstractToken source)
	{
		return wrapExpression(CSharp_MathPowFunc.generateExpression(left, right, source));
	}

	@Override
	public CSharp_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		return CSharp_Literal.generateLiteralExpression(literal, source);
	}

	@Override
	public CSharp_Expression newLogicalAndExpression(CSharp_Expression left,
			CSharp_Expression right, AbstractToken source)
	{
		return CSharp_LogicalAndExpression.generateLogicalAnd(left, right, source);
	}

	@Override
	public CSharp_Expression newLogicalOrExpression(CSharp_Expression left,
			LogicalOrEnum oper, CSharp_Expression right, AbstractToken source)
	{
		return CSharp_LogicalOrExpression.generateLogicalOr(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseExpression(CSharp_Expression left,
			BitwiseEnum oper, CSharp_Expression right, AbstractToken source)
	{
		return CSharp_BitwiseExpression.generateBitwise(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseNotExpression(CSharp_Expression expr,
			AbstractToken source)
	{
		return CSharp_BitwiseNotExpression.generateBitwiseNot(expr, source);
	}

	@Override
	public CSharp_Expression newMultiplicativeExpression(CSharp_Expression left,
			MultiplicativeEnum oper, CSharp_Expression right, AbstractToken source)
	{
		return CSharp_MultiplicativeExpression.generateMultiplicative(left, oper,
				right, source);
	}

	@Override
	public CSharp_Expression newNegativeExpression(NegativeEnum sign, CSharp_Expression expr, AbstractToken source)
	{
		return CSharp_NegativeExpression.generateNegative(sign, expr, source);
	}

	@Override
	public CSharp_Expression newTruncateExpression(CSharp_Expression expr, AbstractToken source)
	{
		CSharp_Type type = CSharp_Type.newPrimitiveType("int");
		return CSharp_CastExpression.newCastExpression(type, expr, source);
	}

	@Override
	public CSharp_Expression newLogicalNotExpression(CSharp_Expression expr, AbstractToken source)
	{
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof CSharp_ParenthesizedExpression)
		{
			return CSharp_LogicalNotExpression.generateLogicalNot(expr, source);
		}

		CSharp_Expression parens = CSharp_ParenthesizedExpression.generateParentheses(expr, source);
		return CSharp_LogicalNotExpression.generateLogicalNot(parens, source);
	}

	@Override
	public CSharp_Expression newLogicalExpression(boolean bool, AbstractToken source)
	{
		return CSharp_BuiltIn.generateBuiltIn(
				(bool ? BuiltInEnum.TRUE : BuiltInEnum.FALSE), source);
	}

	@Override
	public CSharp_Expression newNumberExpression(String number, AbstractToken source)
	{
		return CSharp_Number.generateNumberExpression(number, source);
	}

	@Override
	public CSharp_Expression newParenthesizedExpression(CSharp_Expression expr, AbstractToken source)
	{
		return CSharp_ParenthesizedExpression.generateParentheses(expr, source);
	}

	@Override
	public CSharp_Expression newRelationalExpression(Oper2Types types, CSharp_Expression left, RelationalEnum relOp,
			CSharp_Expression right, AbstractToken source)
	{
		return CSharp_RelationalExpression.generateRelational(types, left, relOp,
				right, source);
	}

	@Override
	public CSharp_Expression newShiftExpression(CSharp_Expression left,
			ShiftEnum shift, CSharp_Expression right, AbstractToken source)
	{
		return CSharp_ShiftExpression.generateShift(left, shift, right, source);
	}

	@Override
	public CSharp_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		return CSharp_ClassCreationWithInitializers.generateArray(exprs, source);
	}

	@Override
	public CSharp_Expression newVariableExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, AbstractToken source)
	{
		return CSharp_VariableExpression.generateVarExpr(name, offset, subscript, source);
	}

	@Override
	public CSharp_Variable newVariable(String name)
	{
		return CSharp_Variable.newVariable(name);
	}

	@Override
	public CSharp_Expression newClassCreation(CSharp_Type type,
			ArrayList<CSharp_Expression> args, AbstractToken source)
	{
		return CSharp_ClassCreationExpression.generateCreation(type, args, source);
	}

	@Override
	public CSharp_Expression newMethodInvocation(CSharp_Variable var,
			ArrayList<CSharp_Expression> args, AbstractToken source)
	{
		return CSharp_MethodInvocation.generateInvocation(var, args, source);
	}

	@Override
	public CSharp_Expression newCurrentDatetime()
	{
		throw new RuntimeException("Need to implement");
	}

	// ================ Functions ================

	@Override
	public CSharp_Expression newAbsFunction(CSharp_Expression expr, AbstractToken source)
	{
		return CSharp_MathAbsFunc.generateAbsFunc(expr, source);
	}

	@Override
	public CSharp_Expression newLengthFunction(CSharp_Expression expr, AbstractToken source)
	{
		return CSharp_LengthMethod.generateLength(expr, source);
	}

	@Override
	public CSharp_Expression newTrimFunction(CSharp_Expression expr, AbstractToken source)
	{
		return CSharp_TrimMethod.generateTrim(expr, source);
	}

	@Override
	public CSharp_Expression newStringFunction(Oper1Types types, CSharp_Expression expr, AbstractToken source)
	{
		return CSharp_ToStringMethod.generateString(types, expr, source);
	}

	@Override
	public CSharp_Expression newSubstringFunction(CSharp_Expression expr,
			CSharp_Expression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC,
			CSharp_Expression ecOrnc, boolean ncMightBeTooBig, AbstractToken source)
	{
		return CSharp_SubstringMethod.generateExpression(expr, sc,
				whichSC, whichEC, ecOrnc, ncMightBeTooBig, source);
	}

	@Override
	public AbstractExpression newEndsWithFunction(CSharp_Expression expr, CSharp_Expression patt,
			AbstractToken source)
	{
		return CSharp_EndsWithMethod.generateEndsWith(expr, patt, source);
	}

	@Override
	public CSharp_Expression newStartsWithFunction(CSharp_Expression expr, CSharp_Expression patt,
			CSharp_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		return CSharp_StartsWithMethod.generateStartsWith(expr, patt, sc, whichSC, source);
	}

	@Override
	public CSharp_Expression newIndexOfFunction(CSharp_Variable string, CSharp_Expression patt,
			CSharp_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		return CSharp_IndexOfMethod.generateIndexOf(string, patt, sc, whichSC, source);
	}

	@Override
	public CSharp_Expression newFormatNumber(CSharp_Expression expr, int length,
			AbstractToken source)
	{
		CSharp_Expression fmt = newLiteralExpression("{0," + length + "}", null);
		return CSharp_StringFormatFunc.generateStringFormat(expr, fmt, source);
	}
	
	@Override
	public CSharp_Expression newFormatDecimal(CSharp_Expression expr, int decimals,
			AbstractToken source)
	{
		CSharp_Expression fmt = newLiteralExpression("{F" + decimals + "}", null);
		return CSharp_StringFormatFunc.generateStringFormat(expr, fmt, source);
	}

	// ================ Terminals ================

	@Override
	public CSharp_Number newNumber(String value, AbstractToken source)
	{
		return CSharp_Number.generateNumber(value, source);
	}

	@Override
	public CSharp_HexNumber newHexNumber(String value, AbstractToken source)
	{
		return CSharp_HexNumber.generateHexNumber(value, source);
	}

	@Override
	public CSharp_Literal newLiteral(String value, AbstractToken source)
	{
		return CSharp_Literal.generateLiteral(value, source);
	}

	@Override
	public CSharp_Character_Literal newCharLiteral(String value, AbstractToken source)
	{
		CSharp_Character_Literal lit = new CSharp_Character_Literal();
		return lit.generateCharLiteral(value, source);
	}
}

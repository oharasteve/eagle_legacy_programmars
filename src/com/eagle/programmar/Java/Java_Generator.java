// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Java;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Method.Java_MethodImplementation;
import com.eagle.programmar.Java.Java_Method.Java_MethodType;
import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_BitwiseExpression;
import com.eagle.programmar.Java.Expressions.Java_BitwiseNotExpression;
import com.eagle.programmar.Java.Expressions.Java_BuiltIn;
import com.eagle.programmar.Java.Expressions.Java_CastExpression;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationExpression;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationWithInitializers;
import com.eagle.programmar.Java.Expressions.Java_LogicalAndExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalOrExpression;
import com.eagle.programmar.Java.Expressions.Java_MethodInvocation;
import com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression;
import com.eagle.programmar.Java.Expressions.Java_NegativeExpression;
import com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
import com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression;
import com.eagle.programmar.Java.Expressions.Java_PreIncrementExpression;
import com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
import com.eagle.programmar.Java.Expressions.Java_ShiftExpression;
import com.eagle.programmar.Java.Expressions.Java_VariableExpression;
import com.eagle.programmar.Java.Functions.Java_MathAbsFunc;
import com.eagle.programmar.Java.Functions.Java_MathPowFunc;
import com.eagle.programmar.Java.Functions.Java_PrintFunction;
import com.eagle.programmar.Java.Functions.Java_StringFormatFunc;
import com.eagle.programmar.Java.Methods.Java_EndsWithMethod;
import com.eagle.programmar.Java.Methods.Java_IndexOfMethod;
import com.eagle.programmar.Java.Methods.Java_LengthMethod;
import com.eagle.programmar.Java.Methods.Java_StartsWithMethod;
import com.eagle.programmar.Java.Methods.Java_SubstringMethod;
import com.eagle.programmar.Java.Methods.Java_ToStringMethod;
import com.eagle.programmar.Java.Methods.Java_TrimMethod;
import com.eagle.programmar.Java.Statements.Java_BreakStatement;
import com.eagle.programmar.Java.Statements.Java_DoWhileStatement;
import com.eagle.programmar.Java.Statements.Java_ExitStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_ForStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_ReturnStatement;
import com.eagle.programmar.Java.Statements.Java_StatementBlock;
import com.eagle.programmar.Java.Statements.Java_SwitchStatement;
import com.eagle.programmar.Java.Statements.Java_WhileStatement;
import com.eagle.programmar.Java.Terminals.Java_Character_Literal;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;

public class Java_Generator
		extends EagleGenerator<Java_Statement, Java_Expression, Java_Variable, Java_Type>
{
	public static String NAME = "Java";
	public static String SUFFIX = ".java";

	private Java_Program _program;
	private String _className;

	public Java_Generator(ParserManager parser, String className)
	{
		super(parser);
		_program = new Java_Program();
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
		return "main";
	}

	@Override
	public void addMainArgs()
	{
		Java_Type paramType = transformType(TypeEnum.ARRAY, null, null);
		addMethodParameter(paramType, "args");
	}

	@Override
	public void addCallToMain()
	{
		// Don't ever need this in Java
	}

	@Override
	public AbstractLanguage getTransformedProgram()
	{
		return _program;
	}

	public static Java_Expression wrapExpression(AbstractToken token)
	{
		Java_Expression wrapper = new Java_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}

	public static Java_Statement wrapStatement(AbstractToken token)
	{
		if (token == null) return null;
		token.setPresent(true);
		Java_Statement wrapper = new Java_Statement();
		wrapper.setWhich(token);
		wrapper.setPresent(true);
		return wrapper;
	}

	@Override
	public Java_Type transformType(TypeEnum type,
			String typeName, AbstractToken source)
	{
		return Java_Type.transformType(type, typeName, source);
	}

	// ================== Main program and class ==================

	private Java_Class _currentClass = null;
	private Java_Method _currentMethod = null;
	private Java_Method _previousMethod = null;

	private void checkClass()
	{
		if (_currentClass == null)
		{
			_currentClass = new Java_Class();
			_currentClass.newJavaClass(PrivacyEnum.PUBLIC, _className);
			_program.addClass(_currentClass);
		}
	}

	private void checkMethod()
	{
		checkClass();

		if (_currentMethod == null)
		{
			Java_Type mainType = Java_Type.newPrimitiveType("void");
			_currentMethod = new Java_Method();
			_currentMethod.newJavaMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC,
					mainType, mainName());
			_currentClass.addMethod(_currentMethod);

			Java_Type paramType = Java_Type.transformTypeArray(TypeEnum.STRING);
			_currentMethod.addMethodParameter(paramType, "args");
		}
	}

	@Override
	public void addMethod(Java_Type returnType, String name, AbstractToken source)
	{
		checkClass();

		_previousMethod = _currentMethod;
		_currentMethod = new Java_Method();
		_currentMethod.newJavaMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC,
				returnType, name);
		_currentMethod.setTransformationSource(source);
		_currentClass.addMethod(_currentMethod);
	}

	@Override
	public void addMethodParameter(Java_Type type, String name)
	{
		_currentMethod.addMethodParameter(type, name);
	}

	@Override
	public void doneMethod()
	{
		_currentMethod = _previousMethod;
	}

	@Override
	public void addStatement(Java_Statement stmt, AbstractToken source)
	{
		if (stmt == null) return;
		checkClass();

		// Cannot put data into the 'main' method when it was declared in a global area
		if (stmt.getWhich() instanceof Java_Data)
		{
			boolean saveInClass = false;
			if (_currentMethod == null)
			{
				saveInClass = true;
			}
			else if (_currentMethod.typeAndName.getWhich() instanceof Java_MethodType)
			{
				Java_MethodType methType = (Java_MethodType) _currentMethod.typeAndName.getWhich();
				if (methType.methodName.getValue().equals(mainName()))
				{
					saveInClass = true;
				}
			}

			if (saveInClass)
			{
				Java_Data data = (Java_Data) stmt.getWhich();
				data.addModifier("static");

				// Put it in top-level class, not the 'main' method
				Java_ClassElement element = new Java_ClassElement();
				element.setWhich(stmt);
				_currentClass.elements.addToken(element);
				return;
			}
		}

		checkMethod();

		Java_MethodImplementation impl = (Java_MethodImplementation) _currentMethod.body.getWhich();
		Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
		stmtOrComment.setWhich(stmt);
		stmtOrComment.setTransformationSource(source);
		stmtOrComment.setPresent(true);
		impl.block.statements.addToken(stmtOrComment);
	}

	@Override
	public void addComment(String comment, AbstractToken source)
	{
		Java_Comment comm = new Java_Comment("// " + comment);
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
	public Java_Statement newBlockStatement(
			ArrayList<Java_Statement> statements, AbstractToken source)
	{
		return Java_StatementBlock.generateBlock(statements, source);
	}

	@Override
	public Java_Statement newBreakStatement(AbstractToken source)
	{
		return Java_BreakStatement.generateBreak(source);
	}

	@Override
	public Java_Statement newDataDeclaration(StaticEnum isStatic, String name, Java_Expression size,
			Java_Type type, Java_Expression initial, AbstractToken source)
	{
		return wrapStatement(Java_Data.newDataDeclaration(isStatic, name, size, type, initial, source));
	}

	@Override
	public Java_Statement newDoUntilStatement1(Java_Expression condition,
			Java_Statement action, AbstractToken source)
	{
		return Java_DoWhileStatement.generateDoUntilOne(condition, action, source);
	}

	@Override
	public Java_Statement newDoUntilStatement(Java_Expression condition,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		return Java_DoWhileStatement.generateDoUntilMany(condition, actions, source);
	}

	@Override
	public Java_Statement newExitStatement(Java_Expression code, AbstractToken source)
	{
		return Java_ExitStatement.newExitStatement(code, source);
	}

	@Override
	public Java_Statement newExpressionStatement(Java_Expression expr, AbstractToken source)
	{
		return Java_ExpressionStatement.newExpressionStatement(expr, source);
	}

	@Override
	public Java_Statement newGlobalVariable(String variableName, AbstractToken source)
	{
		return null;	// Don't need to declare variables as 'global'
	}

	@Override
	public Java_Statement newIfStatement1(Java_Expression condition,
			Java_Statement ifTrue, Java_Statement ifFalse, AbstractToken source)
	{
		return Java_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Java_Statement newIfStatement(Java_Expression condition,
			ArrayList<Java_Statement> ifTrue,
			ArrayList<Java_Statement> ifFalse, AbstractToken source)
	{
		return Java_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Java_Statement newIfElseIfStatement(Java_Expression condition,
			ArrayList<Java_Statement> ifTrue, ArrayList<Java_Expression> elseIfConds,
			ArrayList<ArrayList<Java_Statement>> elseIfParts,
			ArrayList<Java_Statement> ifFalse, AbstractToken source)
	{
		return Java_IfStatement.generateIfElseIfMany(condition, ifTrue,
				elseIfConds, elseIfParts, ifFalse, source);
	}

	@Override
	public Java_Statement newForLoopStatement1(Java_Expression init,
			Java_Expression term, Java_Expression incr, Java_Statement action,
			AbstractToken source)
	{
		return Java_ForStatement.generateForLoopOne(init, term,
				incr, action, source);
	}

	@Override
	public Java_Statement newForLoopStatement(Java_Expression init,
			Java_Expression term, Java_Expression incr,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		return Java_ForStatement.generateForLoopMany(init, term, incr, actions, source);
	}

	@Override
	public Java_Statement newForRangeStatement1(Java_Variable var, TypeEnum type,
			Java_Expression first, RelationalEnum relOp, Java_Expression last,
			Java_Expression step, Java_Statement action, AbstractToken source)
	{
		return Java_ForStatement.generateForRangeOne(var, type, first, relOp, last, step, action, source);
	}

	@Override
	public Java_Statement newForRangeStatement(Java_Variable var, TypeEnum type,
			Java_Expression first, RelationalEnum relOp, Java_Expression last,
			Java_Expression step, ArrayList<Java_Statement> actions, AbstractToken source)
	{
		return Java_ForStatement.generateForRangeMany(var, type, first, relOp, last, step, actions, source);
	}

	@Override
	public Java_Statement newPragma(PragmaEnum prag, AbstractToken source)
	{
		return null;	// Java does not allow unreachable code
	}
	
	@Override
	public Java_Expression newPrintFunction1(Java_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		return Java_PrintFunction.generatePrintFunc1(line, type, newLine, toErr, source);
	}

	@Override
	public Java_Statement newPrintStatement1(Java_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		Java_Expression prtExpr = newPrintFunction1(line, type, newLine, toErr, source);
		return newExpressionStatement(prtExpr, source);
	}

	@Override
	public Java_Expression newPrintFunction(ArrayList<Java_Expression> pieces,
			ArrayList<TypeEnum> types, boolean newLine, boolean toErr, AbstractToken source)
	{
		return Java_PrintFunction.generatePrintFunc(pieces, types, newLine, toErr, source);
	}

	@Override
	public Java_Statement newPrintStatement(ArrayList<Java_Expression> pieces,
			ArrayList<TypeEnum> types, boolean newLine, boolean toErr, AbstractToken source)
	{
		Java_Expression prtExpr = newPrintFunction(pieces, types, newLine, toErr, source);
		return newExpressionStatement(prtExpr, source);
	}

	@Override
	public Java_Statement newReturnStatement(Java_Expression ret,
			AbstractToken source)
	{
		return Java_ReturnStatement.generateReturn(ret, source);
	}

	@Override
	public Java_Statement newSwitchStatement(Java_Expression expr,
			ArrayList<ArrayList<Java_Expression>> values, ArrayList<ArrayList<Java_Statement>> cases,
			ArrayList<Java_Statement> defaultCase, AbstractToken source)
	{
		return Java_SwitchStatement.generateSwitch(expr, values, cases, defaultCase, source);
	}

	@Override
	public Java_Statement newWhileStatement1(Java_Expression condition,
			Java_Statement action, AbstractToken source)
	{
		return Java_WhileStatement.generateWhileOne(condition, action, source);
	}

	@Override
	public Java_Statement newWhileStatement(Java_Expression condition,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		return Java_WhileStatement.generateWhileMany(condition, actions, source);
	}

	// ================ Expressions ================

	@Override
	public Java_Expression newAdditiveExpression(Oper2Types types, Java_Expression left,
			AdditiveEnum oper, Java_Expression right, AbstractToken source)
	{
		return Java_AdditiveExpression.generateAdditive(types, left, oper, right, source);
	}

	@Override
	public Java_Expression newAppendExpression(Java_Expression left, Java_Expression right,
			AbstractToken source)
	{
		Oper2Types types = new Oper2Types(TypeEnum.STRING, TypeEnum.STRING);
		return Java_AdditiveExpression.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
	}

	@Override
	public Java_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, AssignmentEnum oper, Java_Expression expression, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name);
		return Java_AssignmentExpression.generateAssignment(var, subscript, oper, expression, source);
	}

	@Override
	public Java_Expression newConcatExpression(ArrayList<Java_Expression> pieces, AbstractToken source)
	{
		int numPieces = pieces.size();
		if (numPieces == 0) return null;
		Java_Expression result = pieces.get(0);
		for (int i = 1; i < numPieces; i++)
		{
			Java_Expression piece = pieces.get(i);
			result = Java_AdditiveExpression.generateAdditive(null, result, AdditiveEnum.PLUS, piece, null);
		}
		return result;
	}
	
	@Override
	public AbstractExpression newHashAssignment(String name, Java_Expression subscript,
			Java_Expression expression, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name + ".put");
		ArrayList<Java_Expression> args = new ArrayList<Java_Expression>();
		ArrayList<TypeEnum> types = new ArrayList<TypeEnum>();
		args.add(subscript);
		args.add(expression);
		types.add(TypeEnum.INTEGER);
		types.add(TypeEnum.STRING);
		return Java_MethodInvocation.generateInvocation(var, args, types, source);
	}

	@Override
	public Java_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name);
		return Java_PostIncrementExpression.generateIncrement(var, oper, source);
	}

	@Override
	public Java_Expression newPreIncrementExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name);
		return Java_PreIncrementExpression.generateIncrement(var, oper, source);
	}

	@Override
	public Java_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		return Java_BuiltIn.generateBuiltIn(builtin, source);
	}

	@Override
	public Java_Expression newExponentExpression(Java_Expression left, Java_Expression right, AbstractToken source)
	{
		return Java_MathPowFunc.generatePowFunc(left, right, source);
	}

	@Override
	public Java_Expression newAbsFunction(Java_Expression expr, AbstractToken source)
	{
		return Java_MathAbsFunc.generateAbsFunc(expr, source);
	}

	@Override
	public Java_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		return Java_Literal.generateLiteralExpression(literal, source);
	}

	@Override
	public Java_Expression newLogicalAndExpression(Java_Expression left,
			Java_Expression right, AbstractToken source)
	{
		return Java_LogicalAndExpression.generateLogicalAnd(left, right, source);
	}

	@Override
	public Java_Expression newLogicalOrExpression(Java_Expression left,
			LogicalOrEnum oper, Java_Expression right, AbstractToken source)
	{
		return Java_LogicalOrExpression.generateLogicalOr(left, oper, right, source);
	}

	@Override
	public Java_Expression newBitwiseExpression(Java_Expression left,
			BitwiseEnum oper, Java_Expression right, AbstractToken source)
	{
		return Java_BitwiseExpression.generateBitwise(left, oper, right, source);
	}

	@Override
	public Java_Expression newBitwiseNotExpression(Java_Expression expr,
			AbstractToken source)
	{
		return Java_BitwiseNotExpression.generateBitwiseNot(expr, source);
	}

	@Override
	public Java_Expression newMultiplicativeExpression(Java_Expression left,
			MultiplicativeEnum oper, Java_Expression right, AbstractToken source)
	{
		return Java_MultiplicativeExpression.generateMultiplicative(left, oper,
				right, source);
	}

	@Override
	public Java_Expression newNegativeExpression(NegativeEnum sign,
			Java_Expression expr, AbstractToken source)
	{
		return Java_NegativeExpression.generateNegative(sign, expr, source);
	}

	@Override
	public Java_Expression newTruncateExpression(Java_Expression expr, AbstractToken source)
	{
		Java_Type type = Java_Type.newPrimitiveType("int");
		return Java_CastExpression.newCastExpression(type, expr, source);
	}

	@Override
	public Java_Expression newLogicalNotExpression(Java_Expression expr, AbstractToken source)
	{
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof Java_ParenthesizedExpression)
		{
			return Java_LogicalNotExpression.generateLogicalNot(expr, source);
		}

		Java_Expression parens = Java_ParenthesizedExpression.generateParentheses(expr, source);
		return Java_LogicalNotExpression.generateLogicalNot(parens, source);
	}

	@Override
	public AbstractExpression newLogicalExpression(boolean bool, AbstractToken source)
	{
		return Java_BuiltIn.generateBuiltIn(
				(bool ? BuiltInEnum.TRUE : BuiltInEnum.FALSE), source);
	}

	@Override
	public Java_Expression newNumberExpression(String number, AbstractToken source)
	{
		return Java_Number.generateNumberExpression(number, source);
	}

	@Override
	public Java_Expression newParenthesizedExpression(Java_Expression expr, AbstractToken source)
	{
		return Java_ParenthesizedExpression.generateParentheses(expr, source);
	}

	@Override
	public Java_Expression newRelationalExpression(Oper2Types types, Java_Expression left, RelationalEnum relOp,
			Java_Expression right, AbstractToken source)
	{
		return Java_RelationalExpression.generateRelational(types, left, relOp, right, source);
	}

	@Override
	public Java_Expression newShiftExpression(Java_Expression left,
			ShiftEnum shift, Java_Expression right, AbstractToken source)
	{
		return Java_ShiftExpression.generateShift(left, shift, right, source);
	}

	@Override
	public Java_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		return Java_ClassCreationWithInitializers.generateArray(exprs, source);
	}

	@Override
	public Java_Expression newVariableExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, AbstractToken source)
	{
		return Java_VariableExpression.generateVariableExpression(name, offset, subscript, source);
	}

	@Override
	public Java_Variable newVariable(String name)
	{
		return Java_Variable.newVariable(name);
	}

	@Override
	public Java_Expression newClassCreation(Java_Type type,
			ArrayList<Java_Expression> args, AbstractToken source)
	{
		return Java_ClassCreationExpression.generateCreation(type, args, source);
	}

	@Override
	public Java_Expression newMethodInvocation(Java_Variable var,
			ArrayList<Java_Expression> args, ArrayList<TypeEnum> types, AbstractToken source)
	{
		return Java_MethodInvocation.generateInvocation(var, args, types, source);
	}

	@Override
	public Java_Expression newCurrentDatetime()
	{
		throw new RuntimeException("Need to implement");
	}

	// ================ Functions ================

	@Override
	public Java_Expression newLengthFunction(Java_Expression expr, AbstractToken source)
	{
		return Java_LengthMethod.generateLength(expr, source);
	}

	@Override
	public Java_Expression newTrimFunction(Java_Expression expr, AbstractToken source)
	{
		return Java_TrimMethod.generateTrim(expr, source);
	}

	@Override
	public Java_Expression newStringFunction(TypeEnum type, Java_Expression expr, AbstractToken source)
	{
		return Java_ToStringMethod.generateString(type, expr, source);
	}

	@Override
	public Java_Expression newSubstringFunction(Java_Expression expr, Java_Expression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, Java_Expression ecOrnc,
			boolean ncMightBeTooBig, AbstractToken source)
	{
		return Java_SubstringMethod.generateExpression(expr, sc, whichSC,
				whichEC, ecOrnc, ncMightBeTooBig, source);
	}

	@Override
	public AbstractExpression newEndsWithFunction(Java_Expression expr, Java_Expression patt,
			AbstractToken source)
	{
		return Java_EndsWithMethod.generateEndsWith(expr, patt, source);
	}

	@Override
	public Java_Expression newStartsWithFunction(Java_Expression expr, Java_Expression patt,
			Java_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		return Java_StartsWithMethod.generateStartsWith(expr, patt, sc, whichSC, source);
	}

	@Override
	public Java_Expression newIndexOfFunction(Java_Variable string, Java_Expression patt,
			Java_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		return Java_IndexOfMethod.generateIndexOf(string, patt, sc, whichSC, source);
	}

	@Override
	public Java_Expression newFormatNumber(Java_Expression expr, int length,
			AbstractToken source)
	{
		Java_Expression fmt = newLiteralExpression("%" + length + "d", null);
		return Java_StringFormatFunc.generateStringFormat(expr, fmt, source);
	}

	@Override
	public AbstractExpression newFormatDecimal2(Java_Expression expr, int width, int decimals,
			AbstractToken source)
	{
		Java_Expression fmt = newLiteralExpression("%" + width + "." + decimals + "f", null);
		return Java_StringFormatFunc.generateStringFormat(expr, fmt, source);
	}

	@Override
	public Java_Expression newFormatDecimal(Java_Expression expr, int decimals,
			AbstractToken source)
	{
		Java_Expression fmt = newLiteralExpression("%." + decimals + "f", null);
		return Java_StringFormatFunc.generateStringFormat(expr, fmt, source);
	}

	// ================ Terminals ================

	@Override
	public Java_Number newNumber(String value, AbstractToken source)
	{
		return Java_Number.generateNumber(value, source);
	}

	@Override
	public Java_HexNumber newHexNumber(String value, AbstractToken source)
	{
		return Java_HexNumber.generateHexNumber(value, source);
	}

	@Override
	public Java_Literal newLiteral(String value, AbstractToken source)
	{
		return Java_Literal.generateLiteral(value, source);
	}

	@Override
	public Java_Character_Literal newCharLiteral(String value, AbstractToken source)
	{
		return Java_Character_Literal.generateCharLiteral(value, source);
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Java;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Java.Java_Method.Java_MethodImplementation;
import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_BuiltIn;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationExpression;
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
import com.eagle.programmar.Java.Expressions.Java_VariableExpression;
import com.eagle.programmar.Java.Functions.Java_LengthMethod;
import com.eagle.programmar.Java.Functions.Java_MathPowFunc;
import com.eagle.programmar.Java.Functions.Java_SubstringMethod;
import com.eagle.programmar.Java.Statements.Java_DoWhileStatement;
import com.eagle.programmar.Java.Statements.Java_ExitStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_PrintStatement;
import com.eagle.programmar.Java.Statements.Java_WhileStatement;
import com.eagle.programmar.Java.Terminals.Java_Character_Literal;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Java_Generator extends EagleGenerator
{
	public static String NAME = "Java";
	public static String SUFFIX = ".java";
	
	private Java_Program _program;
	private Java_Class _mainClass;
	private Java_Method _mainMethod;
	private Java_Method _currentMethod;
	
	public Java_Generator(String className)
	{
		_mainClass = Java_Class.newJavaClass(PrivacyEnum.PUBLIC, className);
		_program = Java_Program.newJavaProgram(_mainClass, "com.eagle.tests.VB.transformed");
		addMain();
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
	public void addMain()
	{
		Java_Type mainType = Java_Type.newPrimitiveType("void");
		_mainMethod = Java_Method.newJavaMethod(PrivacyEnum.PUBLIC, true, mainType, "main");
		Java_Type paramType = Java_Type.transformTypeArray(TypeEnum.STRING);
		_mainMethod.addJavaParameter(paramType, "args");
		_mainClass.addMethod(_mainMethod);
		_currentMethod = _mainMethod;
	}
	
	@Override
	public AbstractLanguage getTransfomedProgram()
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
		Java_Statement wrapper = new Java_Statement();
		wrapper.setWhich(token);
		return wrapper;
	}

	@Override
	public Java_Method newFunction(String name, PrivacyEnum privacy, boolean isStatic, AbstractType type)
	{
		_currentMethod = Java_Method.newJavaMethod(privacy, isStatic, type, name);
		_mainClass.addMethod(_currentMethod);
		return _currentMethod;
	}
	
	@Override
	public void addFunctionParameter(AbstractFunction function, String name, AbstractType type)
	{
		Java_Method func = (Java_Method) function;
		func.addJavaParameter(type, name);
	}
	
	@Override
	public void doneFunctionParameters()
	{
		_currentMethod = _mainMethod;
	}
	
	@Override
	public void addStatement(AbstractStatement stmt)
	{
		Java_MethodImplementation impl = (Java_MethodImplementation) _currentMethod.body.getWhich();
		Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
		stmtOrComment.setWhich((Java_Statement) stmt);
		impl.block.statements.addToken(stmtOrComment);
	}
	
	@Override
	public AbstractType transformType(TypeEnum type, String typeName, AbstractToken source)
	{
		return Java_Type.transformType(type, typeName, source);
	}

	// ================ Statements ================

	@Override
	public AbstractStatement newDataDeclaration(String name, AbstractExpression size, AbstractType type,
			AbstractExpression initial, AbstractToken source)
	{
		return wrapStatement(Java_Data.newDataDeclaration(name, size, type, initial, source));
	}

	@Override
	public Java_Statement newDoUntilStatement1(AbstractExpression condition,
			AbstractStatement action, AbstractToken source)
	{
		Java_DoWhileStatement doStmt = new Java_DoWhileStatement();
		return doStmt.generateDoUntil1((Java_Expression) condition,
				(Java_Statement) action, source);
	}
	
	@Override
	public Java_Statement newDoUntilStatement(AbstractExpression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		Java_DoWhileStatement doStmt = new Java_DoWhileStatement();
		return doStmt.generateDoUntil((Java_Expression) condition, actions, source);
	}

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
	public AbstractStatement newIfStatement1(AbstractExpression condition,
			AbstractStatement ifTrue, AbstractStatement ifFalse, AbstractToken source)
	{
		Java_IfStatement ifStmt = new Java_IfStatement();
		return ifStmt.generateIfElse1((Java_Expression) condition,
				(Java_Statement) ifTrue, (Java_Statement) ifFalse, source);
	}
	
	@Override
	public AbstractStatement newIfStatement(AbstractExpression condition, ArrayList<AbstractStatement> ifTrue,
			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
	{
		Java_IfStatement ifStmt = new Java_IfStatement();
		return ifStmt.generateIfElse((Java_Expression) condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public AbstractStatement newPrintStatement(AbstractExpression line, AbstractToken source)
	{
		return wrapStatement(Java_PrintStatement.newPrintStatement(line, source));
	}

	@Override
	public Java_Statement newWhileStatement1(AbstractExpression condition,
			AbstractStatement action, AbstractToken source)
	{
		Java_WhileStatement whileStmt = new Java_WhileStatement();
		return whileStmt.generateWhile1((Java_Expression) condition,
				(Java_Statement) action, source);
	}
	
	@Override
	public Java_Statement newWhileStatement(AbstractExpression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		Java_WhileStatement whileStmt = new Java_WhileStatement();
		return whileStmt.generateWhile((Java_Expression) condition, actions, source);
	}

	// ================ Expressions ================
	
	@Override
	public Java_Expression newAdditiveExpression(AbstractExpression left,
			AdditiveEnum oper, AbstractExpression right, AbstractToken source)
	{
		Java_AdditiveExpression addExpr = new Java_AdditiveExpression();
		return addExpr.generateAdditive((Java_Expression) left, oper,
				(Java_Expression) right, source);
	}

	@Override
	public Java_Expression newAppendExpression(AbstractExpression left,
			AbstractExpression right, AbstractToken source)
	{
		Java_AdditiveExpression appendExp = new Java_AdditiveExpression();
		return appendExp.generateAdditive((Java_Expression) left,
				AdditiveEnum.PLUS, (Java_Expression) right, source);
	}
	
	@Override
	public Java_Expression newAssignmentExpression(String name, AbstractExpression subscript,
			AssignmentEnum oper, AbstractExpression expression, AbstractToken source)
	{
		Java_VariableExpression varExp = new Java_VariableExpression();
		Java_Expression var = varExp.generateVarExpr(name, (Java_Expression) subscript, source);
		Java_AssignmentExpression asgExpr = new Java_AssignmentExpression();
		return asgExpr.generateAssignment(var, oper, (Java_Expression) expression, source);
	}
	
	@Override
	public Java_Expression newPostIncrementExpression(String name, AbstractExpression subscript,
			IncrementEnum oper, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name);
		Java_PostIncrementExpression incrExpr = new Java_PostIncrementExpression();
		return incrExpr.generateIncrement(var, oper, source);
	}
	
	@Override
	public Java_Expression newPreIncrementExpression(String name, AbstractExpression subscript,
			IncrementEnum oper, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name);
		Java_PreIncrementExpression incrExpr = new Java_PreIncrementExpression();
		return incrExpr.generateIncrement(var, oper, source);
	}
	
	@Override
	public Java_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		Java_BuiltIn built = new Java_BuiltIn();
		return built.generateBuiltIn(builtin, source);
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
	public Java_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		Java_Literal lit = new Java_Literal();
		return wrapExpression(lit.generateLiteral(literal, source));
	}

	@Override
	public Java_Expression newLogicalAndExpression(AbstractExpression left,
			AbstractExpression right, AbstractToken source)
	{
		Java_LogicalAndExpression andExpr = new Java_LogicalAndExpression();
		return andExpr.generateLogicalAnd((Java_Expression) left,
				(Java_Expression) right, source);
	}
	
	@Override
	public Java_Expression newLogicalOrExpression(AbstractExpression left,
			LogicalOrEnum oper, AbstractExpression right, AbstractToken source)
	{
		Java_LogicalOrExpression orExpr = new Java_LogicalOrExpression();
		return orExpr.generateLogicalOr((Java_Expression) left, oper,
				(Java_Expression) right, source);
	}
	
	@Override
	public Java_Expression newMultiplicativeExpression(AbstractExpression left,
			MultiplicativeEnum oper, AbstractExpression right, AbstractToken source)
	{
		Java_MultiplicativeExpression mulExp = new Java_MultiplicativeExpression();
		return mulExp.generateMultiplicative((Java_Expression) left, oper,
				(Java_Expression) right, source);
	}

	@Override
	public Java_Expression newNegativeExpression(NegativeEnum sign,
			AbstractExpression expr, AbstractToken source)
	{
		Java_NegativeExpression negExpr = new Java_NegativeExpression();
		return negExpr.generateNegative(sign, (Java_Expression) expr, source);
	}
	
	@Override
	public Java_Expression newNotExpression(AbstractExpression expr, AbstractToken source)
	{
		Java_LogicalNotExpression notExpr = new Java_LogicalNotExpression();
		return notExpr.generateLogicalNot((Java_Expression) expr, source);
	}

	@Override
	public Java_Expression newNumberExpression(String number, AbstractToken source)
	{
		Java_Number num = new Java_Number();
		return wrapExpression(num.generateNumber(number, source));
	}

	@Override
	public Java_Expression newParenthesizedExpression(AbstractExpression expr, AbstractToken source)
	{
		Java_ParenthesizedExpression paren = new Java_ParenthesizedExpression();
		return paren.generateParentheses((Java_Expression) expr, source);
	}

	@Override
	public Java_Expression newRelationalExpression(AbstractExpression left, RelationalEnum relOp,
			AbstractExpression right, AbstractToken source)
	{
		Java_RelationalExpression relExp = new Java_RelationalExpression();
		return relExp.generateRelational((Java_Expression) left, relOp,
				(Java_Expression) right, source);
	}
	
	@Override
	public AbstractExpression newSubstringFunction(AbstractExpression expr, AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression scOrnc, AbstractToken source)
	{
		return wrapExpression(Java_SubstringMethod.generateExpression(expr, sc, whichSC, whichEC, scOrnc, source));
	}

	@Override
	public Java_Expression newVariableExpression(String name, AbstractExpression subscript, AbstractToken source)
	{
		Java_VariableExpression varExp = new Java_VariableExpression();
		return varExp.generateVarExpr(name, (Java_Expression) subscript, source);
	}
	
	@Override
	public Java_Expression newClassCreation(AbstractType type,
			Collection<AbstractExpression> args, AbstractToken source)
	{
		Java_ClassCreationExpression creat = new Java_ClassCreationExpression();
		return creat.generateCreation((Java_Type) type, args, source);
	}
	
	@Override
	public Java_Expression newMethodInvocation(AbstractVariable var,
			Collection<AbstractExpression> args, AbstractToken source)
	{
		Java_MethodInvocation creat = new Java_MethodInvocation();
		return creat.generateInvocation((Java_Variable) var, args, source);
	}

	// ================ Terminals ================

	@Override
	public Java_Number newNumber(String value, AbstractToken source)
	{
		Java_Number num = new Java_Number();
		return num.generateNumber(value, source);
	}

	@Override
	public Java_HexNumber newHexNumber(String value, AbstractToken source)
	{
		Java_HexNumber num = new Java_HexNumber();
		return num.generateHexNumber(value, source);
	}

	@Override
	public Java_Literal newLiteral(String value, AbstractToken source)
	{
		Java_Literal lit = new Java_Literal();
		return lit.generateLiteral(value, source);
	}

	@Override
	public Java_Character_Literal newCharLiteral(String value, AbstractToken source)
	{
		Java_Character_Literal lit = new Java_Character_Literal();
		return lit.generateCharLiteral(value, source);
	}
}

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.CSharp;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalAndExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalOrExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
import com.eagle.programmar.CSharp.Functions.CSharp_LengthMethod;
import com.eagle.programmar.CSharp.Functions.CSharp_MathPowFunc;
import com.eagle.programmar.CSharp.Functions.CSharp_SubstringMethod;
import com.eagle.programmar.CSharp.Statements.CSharp_ExitStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExpressionStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_IfStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_PrintStatement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;

public class CSharp_Generator extends EagleGenerator
{
	public static String NAME = "C#";
	public static String SUFFIX = ".cs";
	
	private CSharp_Program _program;
	private CSharp_Class _mainClass;
	private CSharp_Method _mainMethod;
	private CSharp_Method _currentMethod;
	
	public CSharp_Generator(String className)
	{
		_mainClass = CSharp_Class.newCSharpClass(PrivacyEnum.PUBLIC, className);
		_program = CSharp_Program.newCSharpProgram(_mainClass);
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
		CSharp_Type mainType = CSharp_Type.newPrimitiveType("void");
		_mainMethod = CSharp_Method.newCSharpMethod(PrivacyEnum.PUBLIC, true, mainType, "Main");
		CSharp_Type paramType = CSharp_Type.transformTypeArray(TypeEnum.STRING);
		_mainMethod.addCSharpParameter(paramType, "args");
		_mainClass.addMethod(_mainMethod);
		_currentMethod = _mainMethod;
	}
	
	@Override
	public AbstractLanguage getTransfomedProgram()
	{
		return _program;
	}
	
	public static CSharp_Expression wrapExpression(AbstractToken token)
	{
		CSharp_Expression wrapper = new CSharp_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	public static CSharp_Statement wrapStatement(AbstractToken token)
	{
		CSharp_Statement wrapper = new CSharp_Statement();
		wrapper.setWhich(token);
		return wrapper;
	}

	@Override
	public CSharp_Method newFunction(String name, PrivacyEnum privacy, boolean isStatic, AbstractType type)
	{
		_currentMethod = CSharp_Method.newCSharpMethod(privacy, isStatic, type, name);
		_mainClass.addMethod(_currentMethod);
		return _currentMethod;
	}
	
	@Override
	public void addFunctionParameter(AbstractFunction function, String name, AbstractType type)
	{
		CSharp_Method func = (CSharp_Method) function;
		func.addCSharpParameter(type, name);
	}
	
	@Override
	public void doneFunctionParameters()
	{
		_currentMethod = _mainMethod;
	}

	@Override
	public void addStatement(AbstractStatement stmt)
	{
		CSharp_MethodImplementation impl = (CSharp_MethodImplementation) _currentMethod.body.getWhich();
		CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
		stmtOrComment.setWhich((CSharp_Statement) stmt);
		impl.block.statements.addToken(stmtOrComment);
	}
	
	@Override
	public AbstractType transformType(TypeEnum type, String typeName, AbstractToken source)
	{
		return CSharp_Type.transformType(type, typeName, source);
	}

	// ================ Statements ================
	
	@Override
	public AbstractStatement newDataDeclaration(String name, AbstractExpression size, AbstractType type,
			AbstractExpression initial, AbstractToken source)
	{
		return wrapStatement(CSharp_Data.newDataDeclaration(name, size, type, initial, source));
	}

	@Override
	public AbstractStatement newExpressionStatement(AbstractExpression expr, AbstractToken source)
	{
		return wrapStatement(CSharp_ExpressionStatement.newExpressionStatement(expr, source));
	}
	
	@Override
	public AbstractStatement newExitStatement(AbstractExpression code, AbstractToken source)
	{
		return wrapStatement(CSharp_ExitStatement.newExitStatement(code, source));
	}

	@Override
	public AbstractStatement newIfStatement(AbstractExpression condition, ArrayList<AbstractStatement> ifTrue,
			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
	{
		return wrapStatement(CSharp_IfStatement.newIfStatement(condition, ifTrue, ifFalse, source));
	}
	
	@Override
	public AbstractStatement newPrintStatement(AbstractExpression line, AbstractToken source)
	{
		return wrapStatement(CSharp_PrintStatement.newPrintStatement(line, source));
	}

	// ================ Expressions ================
	
	@Override
	public CSharp_Expression newAdditiveExpression(AbstractExpression left, AdditiveEnum oper, AbstractExpression right, AbstractToken source)
	{
		CSharp_AdditiveExpression addExp = new CSharp_AdditiveExpression();
		return addExp.generateAdditive((CSharp_Expression) left,
				oper, (CSharp_Expression) right, source);
	}
	
	@Override
	public AbstractExpression newAppendExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		CSharp_AdditiveExpression appendExp = new CSharp_AdditiveExpression();
		return appendExp.generateAdditive((CSharp_Expression) left,
				AdditiveEnum.PLUS, (CSharp_Expression) right, source);
	}

	@Override
	public AbstractExpression newAssignmentExpression(String name, AbstractExpression subscript,
			AssignmentEnum oper, AbstractExpression expression, String comment, AbstractToken source)
	{
		CSharp_VariableExpression varExpr = new CSharp_VariableExpression();
		CSharp_Expression var = varExpr.generateVarExpr(name,
				(CSharp_Expression) subscript, source);
		return wrapExpression(CSharp_AssignmentExpression.newAssignmentStatement(var, oper,
				expression, comment, source));
	}
	
	@Override
	public CSharp_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		CSharp_BuiltIn built = new CSharp_BuiltIn();
		return built.generateBuiltIn(builtin, source);
	}
	
	@Override
	public AbstractExpression newExponentExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(CSharp_MathPowFunc.generateExpression(left, right, source));
	}
	
	@Override
	public AbstractExpression newLengthFunction(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(CSharp_LengthMethod.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newLiteralExpression(String literal, AbstractToken source)
	{
		return wrapExpression(CSharp_Literal.generateExpression(literal, source));
	}

	@Override
	public CSharp_Expression newLogicalAndExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		CSharp_LogicalAndExpression andExpr = new CSharp_LogicalAndExpression();
		return andExpr.generateLogicalAnd((CSharp_Expression) left,
				(CSharp_Expression) right, source);
	}
	
	@Override
	public CSharp_Expression newLogicalOrExpression(AbstractExpression left, LogicalOrEnum oper, AbstractExpression right, AbstractToken source)
	{
		CSharp_LogicalOrExpression orExpr = new CSharp_LogicalOrExpression();
		return orExpr.generateLogicalOr((CSharp_Expression) left, oper,
				(CSharp_Expression) right, source);
	}
	
	@Override
	public CSharp_Expression newMultiplicativeExpression(AbstractExpression left, MultiplicativeEnum oper, AbstractExpression right, AbstractToken source)
	{
		CSharp_MultiplicativeExpression multExp = new CSharp_MultiplicativeExpression();
		return multExp.generateMultiplicative((CSharp_Expression) left, oper,
				(CSharp_Expression) right, source);
	}

	@Override
	public CSharp_Expression newNegativeExpression(NegativeEnum sign, AbstractExpression expr, AbstractToken source)
	{
		CSharp_NegativeExpression negExp = new CSharp_NegativeExpression();
		return negExp.generateNegative(sign, (CSharp_Expression) expr, source);
	}
	
	@Override
	public CSharp_Expression newNotExpression(AbstractExpression expr, AbstractToken source)
	{
		CSharp_LogicalNotExpression notExp = new CSharp_LogicalNotExpression();
		return notExp.generateLogicalNot((CSharp_Expression) expr, source);
	}
	
	@Override
	public AbstractExpression newNumberExpression(String number, AbstractToken source)
	{
		return wrapExpression(CSharp_Number.generateExpression(number, source));
	}
	
	@Override
	public CSharp_Expression newParenthesizedExpression(AbstractExpression expr, AbstractToken source)
	{
		CSharp_ParenthesizedExpression paren = new CSharp_ParenthesizedExpression();
		return paren.generateParentheses((CSharp_Expression) expr, source);
	}

	@Override
	public CSharp_Expression newRelationalExpression(AbstractExpression left, RelationalEnum relOp,
			AbstractExpression right, AbstractToken source)
	{
		CSharp_RelationalExpression relExp = new CSharp_RelationalExpression();
		return relExp.generateRelational((CSharp_Expression) left, relOp,
				(CSharp_Expression) right, source);
	}
	
	@Override
	public AbstractExpression newSubstringFunction(AbstractExpression expr, AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression scOrnc, AbstractToken source)
	{
		return wrapExpression(CSharp_SubstringMethod.generateExpression(expr, sc, whichSC, whichEC, scOrnc, source));
	}

	@Override
	public CSharp_Expression newVariableExpression(String name,
			AbstractExpression subscript, AbstractToken source)
	{
		CSharp_VariableExpression varExp = new CSharp_VariableExpression();
		return varExp.generateVarExpr(name, (CSharp_Expression) subscript, source);
	}
}

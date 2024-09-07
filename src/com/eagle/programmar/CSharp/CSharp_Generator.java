// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.CSharp;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
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
import com.eagle.transform.EagleGenerator;

public class CSharp_Generator extends EagleGenerator
{
	public CSharp_Program _currentLanguage;
	public CSharp_Class _currentClass;
	public CSharp_Method _currentMethod;
	
	public CSharp_Generator()
	{
		_currentMethod = CSharp_Method.newCSharpMethod("Main");
		_currentClass = CSharp_Class.newCSharpClass(_currentMethod, "Expressions_vbs");
		_currentLanguage = CSharp_Program.newCSharpProgram(_currentClass);
	}
	
	@Override
	public String getName()
	{
		return "C#";
	}
	
	@Override
	public String getSuffix()
	{
		return ".cs";
	}
	
	@Override
	public AbstractLanguage getTransfomedProgram()
	{
		return _currentLanguage;
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
	public void addFunction(AbstractFunction func)
	{
		
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
		return wrapStatement(CSharp_Data.newDataDeclaration(name,size, type, initial, source));
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
	public AbstractExpression newAdditiveExpression(AbstractExpression left, AdditiveEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(CSharp_AdditiveExpression.generateExpression(left, oper, right, source));
	}
	
	@Override
	public AbstractExpression newAppendExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(CSharp_AdditiveExpression.generateExpression(left, AdditiveEnum.PLUS, right, source));
	}

	@Override
	public AbstractExpression newAssignmentExpression(String name, AbstractExpression subscript,
			AssignmentEnum oper, AbstractExpression expression, String comment, AbstractToken source)
	{
		CSharp_Expression varExpr = wrapExpression(CSharp_VariableExpression.newVariableExpression(name, subscript, source));
		return wrapExpression(CSharp_AssignmentExpression.newAssignmentStatement(varExpr, oper, expression, comment, source));
	}
	
	@Override
	public AbstractExpression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		return wrapExpression(CSharp_BuiltIn.generateExpression(builtin, source));
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
	public AbstractExpression newLogicalAndExpression(AbstractExpression left, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(CSharp_LogicalAndExpression.generateExpression(left, right, source));
	}
	
	@Override
	public AbstractExpression newLogicalOrExpression(AbstractExpression left, LogicalOrEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(CSharp_LogicalOrExpression.generateExpression(left, oper, right, source));
	}
	
	@Override
	public AbstractExpression newMultiplicativeExpression(AbstractExpression left, MultiplicativeEnum oper, AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(CSharp_MultiplicativeExpression.generateExpression(left, oper, right, source));
	}

	@Override
	public AbstractExpression newNegativeExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(CSharp_NegativeExpression.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newNotExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(CSharp_LogicalNotExpression.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newNumberExpression(String number, AbstractToken source)
	{
		return wrapExpression(CSharp_Number.generateExpression(number, source));
	}
	
	@Override
	public AbstractExpression newParenthesizedExpression(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(CSharp_ParenthesizedExpression.generateExpression(expr, source));
	}

	@Override
	public AbstractExpression newRelationalExpression(AbstractExpression left, RelationalEnum relOp,
			AbstractExpression right, AbstractToken source)
	{
		return wrapExpression(CSharp_RelationalExpression.generateExpression(left, relOp, right, source));
	}
	
	@Override
	public AbstractExpression newSubstringFunction(AbstractExpression expr, AbstractExpression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, AbstractExpression scOrnc, AbstractToken source)
	{
		return wrapExpression(CSharp_SubstringMethod.generateExpression(expr, sc, whichSC, whichEC, scOrnc, source));
	}

	@Override
	public AbstractExpression newVariableExpression(String name, AbstractExpression subscript, AbstractToken source)
	{
		return wrapExpression(CSharp_VariableExpression.newVariableExpression(name, subscript, source));
	}
}

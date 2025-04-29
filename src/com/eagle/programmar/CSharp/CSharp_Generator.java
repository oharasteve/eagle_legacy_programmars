// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.CSharp;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression;
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
import com.eagle.programmar.CSharp.Functions.CSharp_LengthMethod;
import com.eagle.programmar.CSharp.Functions.CSharp_MathPowFunc;
import com.eagle.programmar.CSharp.Functions.CSharp_SubstringMethod;
import com.eagle.programmar.CSharp.Statements.CSharp_BreakStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_DoWhileStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExitStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ExpressionStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ForStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_IfStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_PrintStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_ReturnStatement;
import com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Statements.CSharp_WhileStatement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class CSharp_Generator extends EagleGenerator
{
	public static String NAME = "C#";
	public static String SUFFIX = ".cs";
	
	private CSharp_Program _program;
	private String _className;
	
	public CSharp_Generator(String className)
	{
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
		CSharp_Statement wrapper = new CSharp_Statement();
		wrapper.setWhich(token);
		return wrapper;
	}

	@Override
	public AbstractType transformType(boolean isArray, TypeEnum type,
			String typeName, AbstractToken source)
	{
		return CSharp_Type.transformType(isArray, type, typeName, source);
	}

	// ================== Main program and class ==================
	
	private CSharp_Class _currentClass = null;
	private CSharp_Method _currentMethod = null;

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
	public void addMethod(AbstractType returnType, String name, AbstractToken source)
	{
		checkClass();

		_currentMethod = new CSharp_Method();
		_currentMethod.newCSharpMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC,
				returnType, name);
		_currentMethod.setTransformationSource(source);
		_currentClass.addMethod(_currentMethod);
	}
	
	@Override
	public void addMethodParameter(AbstractType type, String name)
	{
		_currentMethod.addMethodParameter(type, name);
	}
	
	@Override
	public void addStatement(AbstractStatement stmt, AbstractToken source)
	{
		checkMethod();
		
		CSharp_MethodImplementation impl = (CSharp_MethodImplementation) _currentMethod.body.getWhich();
		CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
		stmtOrComment.setWhich((CSharp_Statement) stmt);
		stmtOrComment.setTransformationSource(source);
		impl.block.statements.addToken(stmtOrComment);
	}
	
	@Override
	public void addComment(String comment, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}
	
	// ================ Statements ================
	
	@Override
	public AbstractStatement newBlockStatement(
			ArrayList<AbstractStatement> statements, AbstractToken source)
	{
		CSharp_StatementBlock block = new CSharp_StatementBlock();
		return block.generateBlock(statements, source);
	}

	@Override
	public AbstractStatement newBreakStatement(AbstractToken source)
	{
		CSharp_BreakStatement brkStmt = new CSharp_BreakStatement();
		return wrapStatement(brkStmt.generateBreak(source));
	}

	@Override
	public AbstractStatement newDataDeclaration(String name, AbstractExpression size, AbstractType type,
			AbstractExpression initial, AbstractToken source)
	{
		return wrapStatement(CSharp_Data.newDataDeclaration(name, size, type, initial, source));
	}

	@Override
	public CSharp_Statement newDoUntilStatement1(AbstractExpression condition,
			AbstractStatement action, AbstractToken source)
	{
		CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
		return doStmt.generateDoUntil1((CSharp_Expression) condition,
				(CSharp_Statement) action, source);
	}

	@Override
	public CSharp_Statement newDoUntilStatement(AbstractExpression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
		return doStmt.generateDoUntil((CSharp_Expression) condition, actions, source);
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
	public AbstractStatement newForLoopStatement1(AbstractExpression init,
			AbstractExpression term, AbstractExpression incr, AbstractStatement action,
			AbstractToken source)
	{
		CSharp_ForStatement forStmt = new CSharp_ForStatement();
		return forStmt.generateForLoop1((CSharp_Expression) init, (CSharp_Expression) term,
				(CSharp_Expression) incr, (CSharp_Statement) action, source);
	}

	@Override
	public AbstractStatement newForLoopStatement(AbstractExpression init,
			AbstractExpression term, AbstractExpression incr,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		CSharp_ForStatement forStmt = new CSharp_ForStatement();
		return forStmt.generateForLoop((CSharp_Expression) init, (CSharp_Expression) term,
				(CSharp_Expression) incr, actions, source);
	}

	@Override
	public CSharp_Statement newIfStatement1(AbstractExpression condition,
			AbstractStatement ifTrue, AbstractStatement ifFalse, AbstractToken source)
	{
		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
		return ifStmt.generateIfElse1((CSharp_Expression) condition,
				(CSharp_Statement) ifTrue, (CSharp_Statement) ifFalse, source);
	}
	
	@Override
	public CSharp_Statement newIfStatement(AbstractExpression condition,
			ArrayList<AbstractStatement> ifTrue,
			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
	{
		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
		return ifStmt.generateIfElse((CSharp_Expression) condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public CSharp_Statement newPrintStatement1(AbstractExpression line,
			boolean newLine, AbstractToken source)
	{
		CSharp_PrintStatement prtStmt = new CSharp_PrintStatement();
		return prtStmt.generatePrint1((CSharp_Expression) line, newLine, source);
	}

	@Override
	public CSharp_Statement newPrintStatement(ArrayList<AbstractExpression> pieces,
			boolean newLine, AbstractToken source)
	{
		CSharp_PrintStatement prtStmt = new CSharp_PrintStatement();
		return prtStmt.generatePrint(pieces, newLine, source);
	}

	@Override
	public CSharp_Statement newReturnStatement(AbstractExpression ret,
			AbstractToken source)
	{
		CSharp_ReturnStatement retStmt = new CSharp_ReturnStatement();
		return retStmt.generateReturn((CSharp_Expression) ret, source);
	}

	@Override
	public CSharp_Statement newWhileStatement1(AbstractExpression condition,
			AbstractStatement action, AbstractToken source)
	{
		CSharp_WhileStatement whileStmt = new CSharp_WhileStatement();
		return whileStmt.generateWhile1((CSharp_Expression) condition,
				(CSharp_Statement) action, source);
	}

	@Override
	public CSharp_Statement newWhileStatement(AbstractExpression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		CSharp_WhileStatement whileStmt = new CSharp_WhileStatement();
		return whileStmt.generateWhile((CSharp_Expression) condition, actions, source);
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
	public CSharp_Expression newAssignmentExpression(String name, AbstractExpression subscript,
			AssignmentEnum oper, AbstractExpression expression, AbstractToken source)
	{
		CSharp_VariableExpression varExpr = new CSharp_VariableExpression();
		CSharp_Expression var = varExpr.generateVarExpr(name,
				(CSharp_Expression) subscript, source);
		CSharp_AssignmentExpression asgExpr = new CSharp_AssignmentExpression();
		return asgExpr.generateAssignment(var, oper,
				(CSharp_Expression) expression, source);
	}
	
	@Override
	public CSharp_Expression newPostIncrementExpression(String name, AbstractExpression subscript,
			IncrementEnum oper, AbstractToken source)
	{
		CSharp_Variable var = CSharp_Variable.newVariable(name);
		CSharp_PostIncrementExpression incrExpr = new CSharp_PostIncrementExpression();
		return incrExpr.generateIncrement(var, oper, source);
	}
	
	@Override
	public CSharp_Expression newPreIncrementExpression(String name, AbstractExpression subscript,
			IncrementEnum oper, AbstractToken source)
	{
		CSharp_Variable var = CSharp_Variable.newVariable(name);
		CSharp_PreIncrementExpression incrExpr = new CSharp_PreIncrementExpression();
		return incrExpr.generateIncrement(var, oper, source);
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
	public CSharp_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		CSharp_Literal lit = new CSharp_Literal();
		return wrapExpression(lit.generateLiteral(literal, source));
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
	public CSharp_Expression newNumberExpression(String number, AbstractToken source)
	{
		CSharp_Number num = new CSharp_Number();
		return wrapExpression(num.generateNumber(number, source));
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
	public AbstractExpression newShiftExpression(AbstractExpression left,
			ShiftEnum shift, AbstractExpression right, AbstractToken source)
	{
		CSharp_ShiftExpression shiftExpr = new CSharp_ShiftExpression();
		return shiftExpr.generateShift((CSharp_Expression) left, shift,
				(CSharp_Expression) right, source);
	}

	@Override
	public CSharp_Expression newVariableExpression(String name,
			AbstractExpression subscript, AbstractToken source)
	{
		CSharp_VariableExpression varExp = new CSharp_VariableExpression();
		return varExp.generateVarExpr(name, (CSharp_Expression) subscript, source);
	}
	
	@Override
	public CSharp_Expression newClassCreation(AbstractType type,
			Collection<AbstractExpression> args, AbstractToken source)
	{
		CSharp_ClassCreationExpression creat = new CSharp_ClassCreationExpression();
		return creat.generateCreation((CSharp_Type) type, args, source);
	}
	
	@Override
	public CSharp_Expression newMethodInvocation(AbstractVariable var,
			Collection<AbstractExpression> args, AbstractToken source)
	{
		CSharp_MethodInvocation creat = new CSharp_MethodInvocation();
		return creat.generateInvocation((CSharp_Variable) var, args, source);
	}
	
	@Override
	public AbstractExpression newCurrentDatetime()
	{
		throw new RuntimeException("Need to implement");
	}

	// ================ Functions ================
	
	@Override
	public AbstractExpression newLengthFunction(AbstractExpression expr, AbstractToken source)
	{
		return wrapExpression(CSharp_LengthMethod.generateExpression(expr, source));
	}
	
	@Override
	public AbstractExpression newSubstringFunction(AbstractExpression expr,
			AbstractExpression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC,
			AbstractExpression scOrnc, AbstractToken source)
	{
		return wrapExpression(CSharp_SubstringMethod.generateExpression(expr, sc, whichSC, whichEC, scOrnc, source));
	}

	// ================ Terminals ================

	@Override
	public CSharp_Number newNumber(String value, AbstractToken source)
	{
		CSharp_Number num = new CSharp_Number();
		return num.generateNumber(value, source);
	}

	@Override
	public CSharp_HexNumber newHexNumber(String value, AbstractToken source)
	{
		CSharp_HexNumber num = new CSharp_HexNumber();
		return num.generateHexNumber(value, source);
	}

	@Override
	public CSharp_Literal newLiteral(String value, AbstractToken source)
	{
		CSharp_Literal lit = new CSharp_Literal();
		return lit.generateLiteral(value, source);
	}

	@Override
	public CSharp_Character_Literal newCharLiteral(String value, AbstractToken source)
	{
		CSharp_Character_Literal lit = new CSharp_Character_Literal();
		return lit.generateCharLiteral(value, source);
	}
}

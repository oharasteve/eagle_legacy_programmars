// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.CSharp;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
import com.eagle.programmar.CSharp.CSharp_Class.CSharp_StaticStatement;
import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
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
import com.eagle.programmar.CSharp.Functions.CSharp_IndexOfMethod;
import com.eagle.programmar.CSharp.Functions.CSharp_LengthMethod;
import com.eagle.programmar.CSharp.Functions.CSharp_MathPowFunc;
import com.eagle.programmar.CSharp.Functions.CSharp_SubstringMethod;
import com.eagle.programmar.CSharp.Functions.CSharp_ToStringMethod;
import com.eagle.programmar.CSharp.Functions.CSharp_TrimMethod;
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
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.interfaces.AbstractExpression;

public class CSharp_Generator extends EagleGenerator<CSharp_Statement,
		CSharp_Expression, CSharp_Variable, CSharp_Type>
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
		if (token == null) return null;
		CSharp_Statement wrapper = new CSharp_Statement();
		wrapper.setWhich(token);
		return wrapper;
	}

	@Override
	public CSharp_Type transformType(boolean isArray, TypeEnum type,
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
	public void addMethod(CSharp_Type returnType, String name, AbstractToken source)
	{
		checkClass();

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
		_currentMethod = null;
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
				// Put it in top-level class, not the 'main' method
				CSharp_StaticStatement staticStmt = new CSharp_StaticStatement();
				staticStmt.STATIC = new CSharp_Keyword("static");
				staticStmt.STATIC.setPresent(true);
				staticStmt.statement = stmt;
				CSharp_ClassElement element = new CSharp_ClassElement();
				element.setWhich(staticStmt);
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
		CSharp_StatementBlock block = new CSharp_StatementBlock();
		return block.generateBlock(statements, source);
	}

	@Override
	public CSharp_Statement newBreakStatement(AbstractToken source)
	{
		CSharp_BreakStatement brkStmt = new CSharp_BreakStatement();
		return brkStmt.generateBreak(source);
	}

	@Override
	public CSharp_Statement newDataDeclaration(String name, CSharp_Expression size, CSharp_Type type,
			CSharp_Expression initial, AbstractToken source)
	{
		return wrapStatement(CSharp_Data.newDataDeclaration(name, size, type, initial, source));
	}

	@Override
	public CSharp_Statement newDoUntilStatement1(CSharp_Expression condition,
			CSharp_Statement action, AbstractToken source)
	{
		CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
		return doStmt.generateDoUntil1(condition, action, source);
	}

	@Override
	public CSharp_Statement newDoUntilStatement(CSharp_Expression condition,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
		return doStmt.generateDoUntil(condition, actions, source);
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
	public CSharp_Statement newForLoopStatement1(CSharp_Expression init,
			CSharp_Expression term, CSharp_Expression incr, CSharp_Statement action,
			AbstractToken source)
	{
		CSharp_ForStatement forStmt = new CSharp_ForStatement();
		return forStmt.generateForLoop1(init, term, incr, action, source);
	}

	@Override
	public CSharp_Statement newForLoopStatement(CSharp_Expression init,
			CSharp_Expression term, CSharp_Expression incr,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_ForStatement forStmt = new CSharp_ForStatement();
		return forStmt.generateForLoop(init, term,
				incr, actions, source);
	}

	@Override
	public CSharp_Statement newForRangeStatement1(CSharp_Variable var, CSharp_Expression first,
			RelationalEnum relOper, CSharp_Expression last, CSharp_Expression step,
			CSharp_Statement action, AbstractToken source)
	{
		CSharp_ForStatement forStmt = new CSharp_ForStatement();
		return forStmt.generateForRange1(var, first, relOper, last, step, action, source);
	}

	@Override
	public CSharp_Statement newForRangeStatement(CSharp_Variable var, CSharp_Expression first,
			RelationalEnum relOper, CSharp_Expression last, CSharp_Expression step,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_ForStatement forStmt = new CSharp_ForStatement();
		return forStmt.generateForRange(var, first, relOper, last, step, actions, source);
	}

	@Override
	public CSharp_Statement newIfStatement1(CSharp_Expression condition,
			CSharp_Statement ifTrue, CSharp_Statement ifFalse, AbstractToken source)
	{
		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
		return ifStmt.generateIfElse1(condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public CSharp_Statement newIfStatement(CSharp_Expression condition,
			ArrayList<CSharp_Statement> ifTrue,
			ArrayList<CSharp_Statement> ifFalse, AbstractToken source)
	{
		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
		return ifStmt.generateIfElse(condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public CSharp_Statement newPrintStatement1(CSharp_Expression line,
			boolean newLine, AbstractToken source)
	{
		CSharp_PrintStatement prtStmt = new CSharp_PrintStatement();
		return prtStmt.generatePrint1(line, newLine, source);
	}

	@Override
	public CSharp_Statement newReturnStatement(CSharp_Expression ret,
			AbstractToken source)
	{
		CSharp_ReturnStatement retStmt = new CSharp_ReturnStatement();
		return retStmt.generateReturn(ret, source);
	}

	@Override
	public CSharp_Statement newWhileStatement1(CSharp_Expression condition,
			CSharp_Statement action, AbstractToken source)
	{
		CSharp_WhileStatement whileStmt = new CSharp_WhileStatement();
		return whileStmt.generateWhile1(condition, action, source);
	}

	@Override
	public CSharp_Statement newWhileStatement(CSharp_Expression condition,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_WhileStatement whileStmt = new CSharp_WhileStatement();
		return whileStmt.generateWhile(condition, actions, source);
	}
	
	// ================ Expressions ================
	
	@Override
	public CSharp_Expression newAdditiveExpression(Oper2Types types,
			CSharp_Expression left, AdditiveEnum oper, CSharp_Expression right, AbstractToken source)
	{
		CSharp_AdditiveExpression addExp = new CSharp_AdditiveExpression();
		return addExp.generateAdditive(types, left,
				oper, right, source);
	}
	
	@Override
	public CSharp_Expression newAppendExpression(Oper2Types types,
			CSharp_Expression left, CSharp_Expression right, AbstractToken source)
	{
		CSharp_AdditiveExpression appendExp = new CSharp_AdditiveExpression();
		return appendExp.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
	}

	@Override
	public CSharp_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, AssignmentEnum oper, CSharp_Expression expression,
			AbstractToken source)
	{
		CSharp_VariableExpression varExpr = new CSharp_VariableExpression();
		CSharp_Expression var = varExpr.generateVarExpr(name, offset,
				subscript, source);
		CSharp_AssignmentExpression asgExpr = new CSharp_AssignmentExpression();
		return asgExpr.generateAssignment(var, oper,
				expression, source);
	}
	
	@Override
	public CSharp_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		CSharp_Variable var = CSharp_Variable.newVariable(name);
		CSharp_PostIncrementExpression incrExpr = new CSharp_PostIncrementExpression();
		return incrExpr.generateIncrement(var, oper, source);
	}
	
	@Override
	public CSharp_Expression newPreIncrementExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, IncrementEnum oper, AbstractToken source)
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
	public CSharp_Expression newExponentExpression(CSharp_Expression left, CSharp_Expression right, AbstractToken source)
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
	public CSharp_Expression newLogicalAndExpression(CSharp_Expression left, CSharp_Expression right, AbstractToken source)
	{
		CSharp_LogicalAndExpression andExpr = new CSharp_LogicalAndExpression();
		return andExpr.generateLogicalAnd(left,
				right, source);
	}
	
	@Override
	public CSharp_Expression newLogicalOrExpression(CSharp_Expression left, LogicalOrEnum oper, CSharp_Expression right, AbstractToken source)
	{
		CSharp_LogicalOrExpression orExpr = new CSharp_LogicalOrExpression();
		return orExpr.generateLogicalOr(left, oper,
				right, source);
	}
	
	@Override
	public CSharp_Expression newMultiplicativeExpression(CSharp_Expression left, MultiplicativeEnum oper, CSharp_Expression right, AbstractToken source)
	{
		CSharp_MultiplicativeExpression multExp = new CSharp_MultiplicativeExpression();
		return multExp.generateMultiplicative(left, oper,
				right, source);
	}

	@Override
	public CSharp_Expression newNegativeExpression(NegativeEnum sign, CSharp_Expression expr, AbstractToken source)
	{
		CSharp_NegativeExpression negExp = new CSharp_NegativeExpression();
		return negExp.generateNegative(sign, expr, source);
	}
	
	@Override
	public CSharp_Expression newNotExpression(CSharp_Expression expr, AbstractToken source)
	{
		CSharp_LogicalNotExpression notExp = new CSharp_LogicalNotExpression();
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof CSharp_ParenthesizedExpression)
		{
			return notExp.generateLogicalNot(expr, source);
		}

		CSharp_ParenthesizedExpression parens = new CSharp_ParenthesizedExpression();
		parens.generateParentheses(expr, source);
		return notExp.generateLogicalNot(CSharp_Generator.wrapExpression(parens), source);
	}
	
	@Override
	public AbstractExpression newLogicalExpression(boolean bool, AbstractToken source)
	{
		CSharp_BuiltIn builtin = new CSharp_BuiltIn();
		builtin.builtinConstant.setValue(bool ? "true" : "false");
		return wrapExpression(builtin);
	}
	
	@Override
	public CSharp_Expression newNumberExpression(String number, AbstractToken source)
	{
		CSharp_Number num = new CSharp_Number();
		return wrapExpression(num.generateNumber(number, source));
	}
	
	@Override
	public CSharp_Expression newParenthesizedExpression(CSharp_Expression expr, AbstractToken source)
	{
		CSharp_ParenthesizedExpression paren = new CSharp_ParenthesizedExpression();
		return paren.generateParentheses(expr, source);
	}

	@Override
	public CSharp_Expression newRelationalExpression(Oper2Types types, CSharp_Expression left, RelationalEnum relOp,
			CSharp_Expression right, AbstractToken source)
	{
		CSharp_RelationalExpression relExp = new CSharp_RelationalExpression();
		return relExp.generateRelational(types, left, relOp,
				right, source);
	}
	
	@Override
	public CSharp_Expression newShiftExpression(CSharp_Expression left,
			ShiftEnum shift, CSharp_Expression right, AbstractToken source)
	{
		CSharp_ShiftExpression shiftExpr = new CSharp_ShiftExpression();
		return shiftExpr.generateShift(left, shift, right, source);
	}

	@Override
	public CSharp_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		CSharp_ClassCreationWithInitializers creat = new CSharp_ClassCreationWithInitializers();
		return creat.generateArray(exprs, source);
	}


	@Override
	public CSharp_Expression newVariableExpression(String name, SubscriptEnum offset,
			CSharp_Expression subscript, AbstractToken source)
	{
		CSharp_VariableExpression varExp = new CSharp_VariableExpression();
		return varExp.generateVarExpr(name, offset, subscript, source);
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
		CSharp_ClassCreationExpression creat = new CSharp_ClassCreationExpression();
		return creat.generateCreation(type, args, source);
	}
	
	@Override
	public CSharp_Expression newMethodInvocation(CSharp_Variable var,
			ArrayList<CSharp_Expression> args, AbstractToken source)
	{
		CSharp_MethodInvocation creat = new CSharp_MethodInvocation();
		return creat.generateInvocation(var, args, source);
	}
	
	@Override
	public CSharp_Expression newCurrentDatetime()
	{
		throw new RuntimeException("Need to implement");
	}

	// ================ Functions ================
	
	@Override
	public CSharp_Expression newLengthFunction(CSharp_Expression expr, AbstractToken source)
	{
		CSharp_LengthMethod lenMeth = new CSharp_LengthMethod();
		return lenMeth.generateLength(expr, source);
	}
	
	@Override
	public CSharp_Expression newTrimFunction(CSharp_Expression expr, AbstractToken source)
	{
		CSharp_TrimMethod trimMeth = new CSharp_TrimMethod();
		return trimMeth.generateTrim(expr, source);
	}

	@Override
	public CSharp_Expression newStringFunction(CSharp_Expression expr, AbstractToken source)
	{
		CSharp_ToStringMethod strMeth = new CSharp_ToStringMethod();
		return strMeth.generateString(expr, source);
	}
	
	@Override
	public CSharp_Expression newSubstringFunction(CSharp_Expression expr,
			CSharp_Expression sc, SubstringSCEnum whichSC, SubstringECEnum whichEC,
			CSharp_Expression scOrnc, boolean ncMightBeTooBig, AbstractToken source)
	{
		return wrapExpression(CSharp_SubstringMethod.generateExpression(expr, sc,
				whichSC, whichEC, scOrnc, ncMightBeTooBig, source));
	}

	@Override
	public CSharp_Expression newIndexOfFunction(CSharp_Variable string,
			CSharp_Expression patt, CSharp_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		CSharp_IndexOfMethod indexMeth = new CSharp_IndexOfMethod();
		return indexMeth.generateIndexOf(string, patt, sc, whichSC, source);
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

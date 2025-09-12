// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Java;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Method.Java_MethodImplementation;
import com.eagle.programmar.Java.Java_Method.Java_MethodType;
import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_BuiltIn;
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
import com.eagle.programmar.Java.Functions.Java_IndexOfMethod;
import com.eagle.programmar.Java.Functions.Java_LengthMethod;
import com.eagle.programmar.Java.Functions.Java_MathPowFunc;
import com.eagle.programmar.Java.Functions.Java_StartsWithMethod;
import com.eagle.programmar.Java.Functions.Java_SubstringMethod;
import com.eagle.programmar.Java.Functions.Java_ToStringMethod;
import com.eagle.programmar.Java.Functions.Java_TrimMethod;
import com.eagle.programmar.Java.Statements.Java_BreakStatement;
import com.eagle.programmar.Java.Statements.Java_DoWhileStatement;
import com.eagle.programmar.Java.Statements.Java_ExitStatement;
import com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
import com.eagle.programmar.Java.Statements.Java_ForStatement;
import com.eagle.programmar.Java.Statements.Java_IfStatement;
import com.eagle.programmar.Java.Statements.Java_PrintStatement;
import com.eagle.programmar.Java.Statements.Java_ReturnStatement;
import com.eagle.programmar.Java.Statements.Java_StatementBlock;
import com.eagle.programmar.Java.Statements.Java_StaticStatement;
import com.eagle.programmar.Java.Statements.Java_WhileStatement;
import com.eagle.programmar.Java.Terminals.Java_Character_Literal;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
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
	
	public Java_Generator(String className)
	{
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
		Java_Type paramType = transformType(TypeEnum.STRING_ARRAY, null, null);
		addMethodParameter(paramType, "args");
	}

	@Override
	public void addCallToMain()
	{
		// Don't ever need this in Java
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
					mainType, "main");
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
				if (methType.methodName.getValue().equals("main"))
				{
					saveInClass = true;
				}
			}
			
			if (saveInClass)
			{
				// Put it in top-level class, not the 'main' method
				Java_StaticStatement staticStmt = new Java_StaticStatement();
				staticStmt.STATIC = new Java_Keyword("static");
				staticStmt.STATIC.setPresent(true);
				staticStmt.statement = stmt;
				Java_ClassElement element = new Java_ClassElement();
				element.setWhich(staticStmt);
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
		Java_Comment comm = new Java_Comment(comment);
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
		Java_StatementBlock block = new Java_StatementBlock();
		return block.generateBlock(statements, source);
	}

	@Override
	public Java_Statement newBreakStatement(AbstractToken source)
	{
		Java_BreakStatement brkStmt = new Java_BreakStatement();
		return brkStmt.generateBreak(source);
	}

	@Override
	public Java_Statement newDataDeclaration(String name, Java_Expression size, Java_Type type,
			Java_Expression initial, AbstractToken source)
	{
		return wrapStatement(Java_Data.newDataDeclaration(name, size, type, initial, source));
	}

	@Override
	public Java_Statement newDoUntilStatement1(Java_Expression condition,
			Java_Statement action, AbstractToken source)
	{
		Java_DoWhileStatement doStmt = new Java_DoWhileStatement();
		return doStmt.generateDoUntil1(condition,
				action, source);
	}
	
	@Override
	public Java_Statement newDoUntilStatement(Java_Expression condition,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		Java_DoWhileStatement doStmt = new Java_DoWhileStatement();
		return doStmt.generateDoUntil(condition, actions, source);
	}

	@Override
	public Java_Statement newExitStatement(Java_Expression code, AbstractToken source)
	{
		return wrapStatement(Java_ExitStatement.newExitStatement(code, source));
	}
	
	@Override
	public Java_Statement newExpressionStatement(Java_Expression expr, AbstractToken source)
	{
		return wrapStatement(Java_ExpressionStatement.newExpressionStatement(expr, source));
	}
	
	@Override
	public Java_Statement newIfStatement1(Java_Expression condition,
			Java_Statement ifTrue, Java_Statement ifFalse, AbstractToken source)
	{
		Java_IfStatement ifStmt = new Java_IfStatement();
		return ifStmt.generateIfElse1(condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public Java_Statement newIfStatement(Java_Expression condition,
			ArrayList<Java_Statement> ifTrue,
			ArrayList<Java_Statement> ifFalse, AbstractToken source)
	{
		Java_IfStatement ifStmt = new Java_IfStatement();
		return ifStmt.generateIfElse(condition, ifTrue, ifFalse, source);
	}
	
	@Override
	public Java_Statement newForLoopStatement1(Java_Expression init,
			Java_Expression term, Java_Expression incr, Java_Statement action,
			AbstractToken source)
	{
		Java_ForStatement forStmt = new Java_ForStatement();
		return forStmt.generateForLoop1(init, term,
				incr, action, source);
	}

	@Override
	public Java_Statement newForLoopStatement(Java_Expression init,
			Java_Expression term, Java_Expression incr,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		Java_ForStatement forStmt = new Java_ForStatement();
		return forStmt.generateForLoop(init, term, incr, actions, source);
	}

	@Override
	public Java_Statement newForRangeStatement1(Java_Variable var, TypeEnum type,
			Java_Expression first, RelationalEnum relOp, Java_Expression last,
			Java_Expression step, Java_Statement action, AbstractToken source)
	{
		Java_ForStatement forStmt = new Java_ForStatement();
		return forStmt.generateForRange1(var, type, first, relOp, last, step, action, source);
	}

	@Override
	public Java_Statement newForRangeStatement(Java_Variable var, TypeEnum type,
			Java_Expression first, RelationalEnum relOp, Java_Expression last,
			Java_Expression step, ArrayList<Java_Statement> actions, AbstractToken source)
	{
		Java_ForStatement forStmt = new Java_ForStatement();
		return forStmt.generateForRange(var, type, first, relOp, last, step, actions, source);
	}

	@Override
	public Java_Statement newPrintStatement(Java_Expression line, boolean newLine,
			AbstractToken source)
	{
		Java_PrintStatement prtStmt = new Java_PrintStatement();
		return prtStmt.generatePrintStmt(line, newLine, source);
	}

	@Override
	public Java_Statement newReturnStatement(Java_Expression ret,
			AbstractToken source)
	{
		Java_ReturnStatement retStmt = new Java_ReturnStatement();
		return retStmt.generateReturn(ret, source);
	}

	@Override
	public Java_Statement newWhileStatement1(Java_Expression condition,
			Java_Statement action, AbstractToken source)
	{
		Java_WhileStatement whileStmt = new Java_WhileStatement();
		return whileStmt.generateWhile1(condition,
				action, source);
	}
	
	@Override
	public Java_Statement newWhileStatement(Java_Expression condition,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		Java_WhileStatement whileStmt = new Java_WhileStatement();
		return whileStmt.generateWhile(condition, actions, source);
	}

	// ================ Expressions ================
	
	@Override
	public Java_Expression newAdditiveExpression(Oper2Types types, Java_Expression left,
			AdditiveEnum oper, Java_Expression right, AbstractToken source)
	{
		Java_AdditiveExpression addExpr = new Java_AdditiveExpression();
		return addExpr.generateAdditive(types, left, oper, right, source);
	}

	@Override
	public Java_Expression newAppendExpression(Oper2Types types,
			Java_Expression left, Java_Expression right, AbstractToken source)
	{
		Java_AdditiveExpression appendExp = new Java_AdditiveExpression();
		return appendExp.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
	}
	
	@Override
	public Java_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, AssignmentEnum oper, Java_Expression expression, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name);
		Java_AssignmentExpression asgExpr = new Java_AssignmentExpression();
		return asgExpr.generateAssignment(var, subscript, oper, expression, source);
	}
	
	@Override
	public AbstractExpression newHashAssignment(String name, Java_Expression subscript,
			Java_Expression expression, AbstractToken source)
	{
		Java_MethodInvocation invoke = new Java_MethodInvocation();
		Java_Variable var = Java_Variable.newVariable(name + ".put");
		ArrayList<Java_Expression> args = new ArrayList<Java_Expression>();
		args.add(subscript);
		args.add(expression);
		return invoke.generateInvocation(var, args, source);
	}

	@Override
	public Java_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		Java_Variable var = Java_Variable.newVariable(name);
		Java_PostIncrementExpression incrExpr = new Java_PostIncrementExpression();
		return incrExpr.generateIncrement(var, oper, source);
	}
	
	@Override
	public Java_Expression newPreIncrementExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, IncrementEnum oper, AbstractToken source)
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
	public Java_Expression newExponentExpression(Java_Expression left, Java_Expression right, AbstractToken source)
	{
		return wrapExpression(Java_MathPowFunc.generateExpression(left, right, source));
	}
	
	@Override
	public Java_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		Java_Literal lit = new Java_Literal();
		return wrapExpression(lit.generateLiteral(literal, source));
	}

	@Override
	public Java_Expression newLogicalAndExpression(Java_Expression left,
			Java_Expression right, AbstractToken source)
	{
		Java_LogicalAndExpression andExpr = new Java_LogicalAndExpression();
		return andExpr.generateLogicalAnd(left,
				right, source);
	}
	
	@Override
	public Java_Expression newLogicalOrExpression(Java_Expression left,
			LogicalOrEnum oper, Java_Expression right, AbstractToken source)
	{
		Java_LogicalOrExpression orExpr = new Java_LogicalOrExpression();
		return orExpr.generateLogicalOr(left, oper,
				right, source);
	}
	
	@Override
	public Java_Expression newMultiplicativeExpression(Java_Expression left,
			MultiplicativeEnum oper, Java_Expression right, AbstractToken source)
	{
		Java_MultiplicativeExpression mulExp = new Java_MultiplicativeExpression();
		return mulExp.generateMultiplicative(left, oper,
				right, source);
	}

	@Override
	public Java_Expression newNegativeExpression(NegativeEnum sign,
			Java_Expression expr, AbstractToken source)
	{
		Java_NegativeExpression negExpr = new Java_NegativeExpression();
		return negExpr.generateNegative(sign, expr, source);
	}
	
	@Override
	public Java_Expression newNotExpression(Java_Expression expr, AbstractToken source)
	{
		Java_LogicalNotExpression notExp = new Java_LogicalNotExpression();
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof Java_ParenthesizedExpression)
		{
			return notExp.generateLogicalNot(expr, source);
		}

		Java_ParenthesizedExpression parens = new Java_ParenthesizedExpression();
		parens.generateParentheses(expr, source);
		return notExp.generateLogicalNot(Java_Generator.wrapExpression(parens), source);
	}

	@Override
	public AbstractExpression newLogicalExpression(boolean bool, AbstractToken source)
	{
		Java_BuiltIn builtin = new Java_BuiltIn();
		builtin.builtinConstant.setValue(bool ? "true" : "false");
		return wrapExpression(builtin);
	}

	@Override
	public Java_Expression newNumberExpression(String number, AbstractToken source)
	{
		Java_Number num = new Java_Number();
		return wrapExpression(num.generateNumber(number, source));
	}

	@Override
	public Java_Expression newParenthesizedExpression(Java_Expression expr, AbstractToken source)
	{
		Java_ParenthesizedExpression paren = new Java_ParenthesizedExpression();
		return paren.generateParentheses(expr, source);
	}

	@Override
	public Java_Expression newRelationalExpression(Oper2Types types, Java_Expression left, RelationalEnum relOp,
			Java_Expression right, AbstractToken source)
	{
		Java_RelationalExpression relExp = new Java_RelationalExpression();
		return relExp.generateRelational(types, left, relOp, right, source);
	}
	
	@Override
	public Java_Expression newShiftExpression(Java_Expression left,
			ShiftEnum shift, Java_Expression right, AbstractToken source)
	{
		Java_ShiftExpression shiftExpr = new Java_ShiftExpression();
		return shiftExpr.generateShift(left, shift,
				right, source);
	}

	@Override
	public Java_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		Java_ClassCreationWithInitializers creat = new Java_ClassCreationWithInitializers();
		return creat.generateArray(exprs, source);
	}

	@Override
	public Java_Expression newVariableExpression(String name, SubscriptEnum offset,
			Java_Expression subscript, AbstractToken source)
	{
		Java_VariableExpression varExp = new Java_VariableExpression();
		return varExp.generateVarExpr(name, offset, subscript, source);
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
		Java_ClassCreationExpression creat = new Java_ClassCreationExpression();
		return creat.generateCreation(type, args, source);
	}
	
	@Override
	public Java_Expression newMethodInvocation(Java_Variable var,
			ArrayList<Java_Expression> args, AbstractToken source)
	{
		Java_MethodInvocation creat = new Java_MethodInvocation();
		return creat.generateInvocation(var, args, source);
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
		Java_LengthMethod lenMeth = new Java_LengthMethod();
		return lenMeth.generateLength(expr, source);
	}
	
	@Override
	public Java_Expression newTrimFunction(Java_Expression expr, AbstractToken source)
	{
		Java_TrimMethod trimMeth = new Java_TrimMethod();
		return trimMeth.generateTrim(expr, source);
	}
	
	@Override
	public Java_Expression newStringFunction(Java_Expression expr, AbstractToken source)
	{
		Java_ToStringMethod strMeth = new Java_ToStringMethod();
		return strMeth.generateString(expr, source);
	}
	
	@Override
	public Java_Expression newSubstringFunction(Java_Expression expr, Java_Expression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, Java_Expression ecOrnc,
			boolean ncMightBeTooBig, AbstractToken source)
	{
		return wrapExpression(Java_SubstringMethod.generateExpression(expr, sc, whichSC,
				whichEC, ecOrnc, ncMightBeTooBig, source));
	}

	@Override
	public Java_Expression newStartsWithFunction(Java_Expression expr, Java_Expression patt,
			Java_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Java_StartsWithMethod startsMeth = new Java_StartsWithMethod();
		return startsMeth.generateStartsWith(expr, patt, sc, whichSC, source);
	}
	
	@Override
	public Java_Expression newIndexOfFunction(Java_Variable string,
			Java_Expression patt, Java_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Java_IndexOfMethod indexMeth = new Java_IndexOfMethod();
		return indexMeth.generateIndexOf(string, patt, sc, whichSC, source);
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

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Rust;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.Rust.Rust_Program.Rust_TopElement;
import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BitwiseExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BuiltIn;
import com.eagle.programmar.Rust.Expressions.Rust_EqualityExpression;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalAndExpression;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalOrExpression;
import com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation;
import com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NegativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NotExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ShiftExpression;
import com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Functions.Rust_LenMethod;
import com.eagle.programmar.Rust.Functions.Rust_PrintlnFunction;
import com.eagle.programmar.Rust.Functions.Rust_StartsWithMethod;
import com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
import com.eagle.programmar.Rust.Statements.Rust_BreakStatement;
import com.eagle.programmar.Rust.Statements.Rust_ConstStatement;
import com.eagle.programmar.Rust.Statements.Rust_ExpressionStatement;
import com.eagle.programmar.Rust.Statements.Rust_IfStatement;
import com.eagle.programmar.Rust.Statements.Rust_LetStatement;
import com.eagle.programmar.Rust.Statements.Rust_ReturnStatement;
import com.eagle.programmar.Rust.Statements.Rust_WhileStatement;
import com.eagle.programmar.Rust.Symbols.Rust_Function_Definition;
import com.eagle.programmar.Rust.Terminals.Rust_Character_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_HexNumber;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;

public class Rust_Generator
		extends EagleGenerator<Rust_Statement, Rust_Expression, Rust_Variable, Rust_Type>
{
	public static String NAME = "Rust";
	public static String SUFFIX = ".rs";

	private Rust_Program _program;
	private EagleMetrics _metrics = null;

	public Rust_Generator(ParserManager parser, String className)
	{
		super(parser);
		_program = new Rust_Program();
		_program.elements = new TokenList<Rust_TopElement>();
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
		// Don't need any args for 'fn main()'
	}

	@Override
	public void addCallToMain()
	{
		// Don't ever need this in Rust
	}

	@Override
	public AbstractLanguage getTransfomedProgram()
	{
		return _program;
	}

	public static Rust_Expression wrapExpression(AbstractToken token)
	{
		Rust_Expression wrapper = new Rust_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}

	public static Rust_Statement wrapStatement(AbstractToken token)
	{
		if (token == null) return null;
		token.setPresent(true);
		Rust_Statement wrapper = new Rust_Statement();
		wrapper.setWhich(token);
		wrapper.setPresent(true);
		return wrapper;
	}

	@Override
	public Rust_Type transformType(TypeEnum type,
			String typeName, AbstractToken source)
	{
		return Rust_Type.transformType(type, typeName, source);
	}

	// ================== Main program and class ==================

	private Rust_Function _currentFunction = null;
	private Rust_Function _previousFunction = null;

	private void checkFunction()
	{
		if (_currentFunction == null)
		{
			_currentFunction = new Rust_Function();
			_currentFunction.id = new Rust_Function_Definition();
			_currentFunction.id.setValue("main");
			
			Rust_TopElement topElt = new Rust_TopElement();
			topElt.setWhich(_currentFunction);
			_program.elements.addToken(topElt);
		}
	}

	@Override
	public void addMethod(Rust_Type returnType, String name, AbstractToken source)
	{
		_previousFunction = _currentFunction;
		_currentFunction = new Rust_Function();
		_currentFunction.newRustFunction(returnType, name);
		_currentFunction.setTransformationSource(source);
		
		Rust_TopElement topElt = new Rust_TopElement();
		topElt.setWhich(_currentFunction);
		_program.addTopElement(topElt);
	}

	@Override
	public void addMethodParameter(Rust_Type type, String name)
	{
		// Don't add args to "fn main()"
		if (!_currentFunction.id.getValue().equals("main"))
		{
			_currentFunction.addFunctionParameter(type, name);
		}
	}

	@Override
	public void doneMethod()
	{
		_currentFunction = _previousFunction;
	}

	@Override
	public void addStatement(Rust_Statement stmt, AbstractToken source)
	{
		if (stmt == null) return;

//		// Cannot put data into the 'main' method when it was declared in a global area
//		if (stmt.getWhich() instanceof Rust_Data)
//		{
//			boolean saveGlobally = false;
//			if (_currentFunction == null)
//			{
//				saveGlobally = true;
//			}
//			else if (_currentFunction.id.getValue().equals("main"))
//			{
//				saveGlobally = true;
//			}
//
//			if (saveGlobally)
//			{
//				Rust_Data data = (Rust_Data) stmt.getWhich();
//				data.STATIC.setValue("static");
//
//				// Put it in program, not the 'main' method
//				Rust_TopElement element = new Rust_TopElement();
//				element.setWhich(data);
//				_program.addTopElement(element);
//				return;
//			}
//		}

		checkFunction();
		
		stmt.setTransformationSource(source);
		_currentFunction.block.statements.addToken(stmt);
	}

	@Override
	public void addComment(String comment, AbstractToken source)
	{
		Rust_Comment comm = new Rust_Comment(comment);
		comm.setTransformationSource(source);
		if (_currentFunction != null)
		{
			_currentFunction.addComment(comm);
		}
		else
		{
			_program.addComment(comm);
		}
	}

	// ================ Statements ================

	@Override
	public Rust_Statement newBlockStatement(
			ArrayList<Rust_Statement> statements, AbstractToken source)
	{
		Rust_Block_Statement block = new Rust_Block_Statement();
		return block.generateBlock(statements, source);
	}

	@Override
	public Rust_Statement newBreakStatement(AbstractToken source)
	{
		Rust_BreakStatement brkStmt = new Rust_BreakStatement();
		return brkStmt.generateBreak(source);
	}

	@Override
	public Rust_Statement newDataDeclaration(boolean isStatic, String name, Rust_Expression size,
			Rust_Type type, Rust_Expression initial, AbstractToken source)
	{
		if (_metrics != null)
		{
			if (_metrics.countAssignments(name, null) == 1)
			{
				return wrapStatement(Rust_ConstStatement.newDataDeclaration(isStatic, name, size, type, initial, source));
			}
		}
		return wrapStatement(Rust_LetStatement.newDataDeclaration(isStatic, name, size, type, initial, source));
	}

	@Override
	public Rust_Statement newDoUntilStatement1(Rust_Expression condition,
			Rust_Statement action, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_WhileStatement whileStmt = new Rust_WhileStatement();
//		return whileStmt.generateDoUntil1(condition, action, source);
	}

	@Override
	public Rust_Statement newDoUntilStatement(Rust_Expression condition,
			ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_WhileStatement whileStmt = new Rust_WhileStatement();
//		return whileStmt.generateDoUntil(condition, actions, source);
	}

	@Override
	public Rust_Statement newExitStatement(Rust_Expression code, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		return wrapStatement(Rust_ExitStatement.newExitStatement(code, source));
	}

	@Override
	public Rust_Statement newExpressionStatement(Rust_Expression expr, AbstractToken source)
	{
		return wrapStatement(Rust_ExpressionStatement.newExpressionStatement(expr, source));
	}

	@Override
	public Rust_Statement newGlobalVariable(String variableName, AbstractToken source)
	{
		return null;	// Don't need to declare variables as 'global'
	}

	@Override
	public Rust_Statement newIfStatement1(Rust_Expression condition,
			Rust_Statement ifTrue, Rust_Statement ifFalse, AbstractToken source)
	{
		Rust_IfStatement ifStmt = new Rust_IfStatement();
		return ifStmt.generateIfElse1(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Rust_Statement newIfStatement(Rust_Expression condition,
			ArrayList<Rust_Statement> ifTrue,
			ArrayList<Rust_Statement> ifFalse, AbstractToken source)
	{
		Rust_IfStatement ifStmt = new Rust_IfStatement();
		return ifStmt.generateIfElse(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Rust_Statement newForLoopStatement1(Rust_Expression init,
			Rust_Expression term, Rust_Expression incr, Rust_Statement action,
			AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_ForStatement forStmt = new Rust_ForStatement();
//		return forStmt.generateForLoop1(init, term, incr, action, source);
	}

	@Override
	public Rust_Statement newForLoopStatement(Rust_Expression init,
			Rust_Expression term, Rust_Expression incr,
			ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_ForStatement forStmt = new Rust_ForStatement();
//		return forStmt.generateForLoop(init, term, incr, actions, source);
	}

	@Override
	public Rust_Statement newForRangeStatement1(Rust_Variable var, TypeEnum type,
			Rust_Expression first, RelationalEnum relOp, Rust_Expression last,
			Rust_Expression step, Rust_Statement action, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_ForStatement forStmt = new Rust_ForStatement();
//		return forStmt.generateForRange1(var, type, first, relOp, last, step, action, source);
	}

	@Override
	public Rust_Statement newForRangeStatement(Rust_Variable var, TypeEnum type,
			Rust_Expression first, RelationalEnum relOp, Rust_Expression last,
			Rust_Expression step, ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_ForStatement forStmt = new Rust_ForStatement();
//		return forStmt.generateForRange(var, type, first, relOp, last, step, actions, source);
	}

	@Override
	public Rust_Expression newPrintFunction(Rust_Expression line, boolean newLine,
			boolean toErr, AbstractToken source)
	{
		Rust_PrintlnFunction prtExpr = new Rust_PrintlnFunction();
		return prtExpr.generatePrintFunc(line, newLine, toErr, source);
	}

	@Override
	public Rust_Statement newPrintStatement(Rust_Expression line, boolean newLine,
			boolean toErr, AbstractToken source)
	{
		Rust_Expression prtExpr = newPrintFunction(line, newLine, toErr, source);
		return newExpressionStatement(prtExpr, source);
	}

	@Override
	public Rust_Statement newReturnStatement(Rust_Expression ret,
			AbstractToken source)
	{
		Rust_ReturnStatement retStmt = new Rust_ReturnStatement();
		return retStmt.generateReturn(ret, source);
	}

	@Override
	public Rust_Statement newSwitchStatement(Rust_Expression expr,
			ArrayList<Rust_Expression> values, ArrayList<ArrayList<Rust_Statement>> cases,
			ArrayList<Rust_Statement> defaultCase, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_SwitchStatement switchStmt = new Rust_SwitchStatement();
//		return switchStmt.generateSwitch(expr, values, cases, defaultCase, source);
	}

	@Override
	public Rust_Statement newWhileStatement1(Rust_Expression condition,
			Rust_Statement action, AbstractToken source)
	{
		Rust_WhileStatement whileStmt = new Rust_WhileStatement();
		return whileStmt.generateWhile1(condition,
				action, source);
	}

	@Override
	public Rust_Statement newWhileStatement(Rust_Expression condition,
			ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		Rust_WhileStatement whileStmt = new Rust_WhileStatement();
		return whileStmt.generateWhile(condition, actions, source);
	}

	// ================ Expressions ================

	@Override
	public Rust_Expression newAdditiveExpression(Oper2Types types, Rust_Expression left,
			AdditiveEnum oper, Rust_Expression right, AbstractToken source)
	{
		Rust_AdditiveExpression addExpr = new Rust_AdditiveExpression();
		return addExpr.generateAdditive(types, left, oper, right, source);
	}

	@Override
	public Rust_Expression newAppendExpression(Oper2Types types,
			Rust_Expression left, Rust_Expression right, AbstractToken source)
	{
		Rust_AdditiveExpression appendExp = new Rust_AdditiveExpression();
		return appendExp.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
	}

	@Override
	public Rust_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			Rust_Expression subscript, AssignmentEnum oper, Rust_Expression expression, AbstractToken source)
	{
		Rust_Variable var = Rust_Variable.newVariable(name);
		Rust_AssignmentExpression asgExpr = new Rust_AssignmentExpression();
		return asgExpr.generateAssignment(var, subscript, oper, expression, source);
	}

	@Override
	public AbstractExpression newHashAssignment(String name, Rust_Expression subscript,
			Rust_Expression expression, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_MethodInvocation invoke = new Rust_MethodInvocation();
//		Rust_Variable var = Rust_Variable.newVariable(name + ".put");
//		ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
//		args.add(subscript);
//		args.add(expression);
//		return invoke.generateInvocation(var, args, source);
	}

	@Override
	public Rust_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			Rust_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		Rust_Variable var = Rust_Variable.newVariable(name);
		Rust_Expression one = newNumberExpression("1", null);
		Rust_AssignmentExpression asgStmt = new Rust_AssignmentExpression();
		
		AssignmentEnum newOper;
		switch (oper)
		{
		case INCREMENT:
			newOper = AssignmentEnum.PLUS_EQUALS;
			break;
		case DECREMENT:
			newOper = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + oper);
		}

		return asgStmt.generateAssignment(var, null, newOper, one, source);
	}

	@Override
	public Rust_Expression newPreIncrementExpression(String name, SubscriptEnum offset,
			Rust_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		// ++x and x++ are the same unless they are embedded into another expression
		return newPostIncrementExpression(name, offset, subscript, oper, source);
	}

	@Override
	public Rust_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
	{
		Rust_BuiltIn built = new Rust_BuiltIn();
		return built.generateBuiltIn(builtin, source);
	}

	@Override
	public Rust_Expression newExponentExpression(Rust_Expression left, Rust_Expression right, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		return wrapExpression(Rust_MathPowFunc.generateExpression(left, right, source));
	}

	@Override
	public Rust_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		Rust_Literal lit = new Rust_Literal();
		return wrapExpression(lit.generateLiteral(literal, source));
	}

	@Override
	public Rust_Expression newLogicalAndExpression(Rust_Expression left,
			Rust_Expression right, AbstractToken source)
	{
		Rust_LogicalAndExpression andExpr = new Rust_LogicalAndExpression();
		return andExpr.generateLogicalAnd(left, right, source);
	}

	@Override
	public Rust_Expression newLogicalOrExpression(Rust_Expression left,
			LogicalOrEnum oper, Rust_Expression right, AbstractToken source)
	{
		Rust_LogicalOrExpression orExpr = new Rust_LogicalOrExpression();
		return orExpr.generateLogicalOr(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseExpression(Rust_Expression left,
			BitwiseEnum oper, Rust_Expression right, AbstractToken source)
	{
		Rust_BitwiseExpression bitExpr = new Rust_BitwiseExpression();
		return bitExpr.generateBitwise(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseNotExpression(Rust_Expression expr,
			AbstractToken source)
	{
		Rust_NotExpression bitExpr = new Rust_NotExpression();
		return bitExpr.generateNot(expr, source);
	}

	@Override
	public Rust_Expression newMultiplicativeExpression(Rust_Expression left,
			MultiplicativeEnum oper, Rust_Expression right, AbstractToken source)
	{
		Rust_MultiplicativeExpression mulExp = new Rust_MultiplicativeExpression();
		return mulExp.generateMultiplicative(left, oper,
				right, source);
	}

	@Override
	public Rust_Expression newNegativeExpression(NegativeEnum sign,
			Rust_Expression expr, AbstractToken source)
	{
		Rust_NegativeExpression negExpr = new Rust_NegativeExpression();
		return negExpr.generateNegative(sign, expr, source);
	}

	@Override
	public Rust_Expression newTruncateExpression(Rust_Expression expr, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_Type type = Rust_Type.newPrimitiveType("int");
//		return Rust_CastExpression.newCastExpression(type, expr, source);
	}

	@Override
	public Rust_Expression newLogicalNotExpression(Rust_Expression expr, AbstractToken source)
	{
		Rust_NotExpression notExp = new Rust_NotExpression();
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof Rust_ParenthesizedExpression)
		{
			return notExp.generateNot(expr, source);
		}

		Rust_ParenthesizedExpression parens = new Rust_ParenthesizedExpression();
		parens.generateParentheses(expr, source);
		return notExp.generateNot(Rust_Generator.wrapExpression(parens), source);
	}

	@Override
	public AbstractExpression newLogicalExpression(boolean bool, AbstractToken source)
	{
		Rust_BuiltIn builtin = new Rust_BuiltIn();
		builtin.builtinConstant.setValue(bool ? "true" : "false");
		return wrapExpression(builtin);
	}

	@Override
	public Rust_Expression newNumberExpression(String number, AbstractToken source)
	{
		Rust_Number num = new Rust_Number();
		return wrapExpression(num.generateNumber(number, source));
	}

	@Override
	public Rust_Expression newParenthesizedExpression(Rust_Expression expr, AbstractToken source)
	{
		Rust_ParenthesizedExpression paren = new Rust_ParenthesizedExpression();
		return paren.generateParentheses(expr, source);
	}

	@Override
	public Rust_Expression newRelationalExpression(Oper2Types types, Rust_Expression left, RelationalEnum relOp,
			Rust_Expression right, AbstractToken source)
	{
		switch(relOp)
		{
		case EQUALS, NOT_EQUALS:
			Rust_EqualityExpression eqExp = new Rust_EqualityExpression();
			return eqExp.generateEquality(types, left, relOp, right, source);
		case LESS_THAN, LESS_EQUALS, GREATER_EQUALS, GREATER_THAN:
			Rust_RelationalExpression relExp = new Rust_RelationalExpression();
			return relExp.generateRelational(types, left, relOp, right, source);
		}
		throw new RuntimeException("Unexpected operator: " + relOp);
	}

	@Override
	public Rust_Expression newShiftExpression(Rust_Expression left,
			ShiftEnum shift, Rust_Expression right, AbstractToken source)
	{
		Rust_ShiftExpression shiftExpr = new Rust_ShiftExpression();
		return shiftExpr.generateShift(left, shift, right, source);
	}

	@Override
	public Rust_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_ClassCreationWithInitializers creat = new Rust_ClassCreationWithInitializers();
//		return creat.generateArray(exprs, source);
	}

	@Override
	public Rust_Expression newVariableExpression(String name, SubscriptEnum offset,
			Rust_Expression subscript, AbstractToken source)
	{
		Rust_VariableExpression varExp = new Rust_VariableExpression();
		return varExp.generateVarExpr(name, offset, subscript, source);
	}

	@Override
	public Rust_Variable newVariable(String name)
	{
		throw new RuntimeException("Need to implement");
//		return Rust_Variable.newVariable(name);
	}

	@Override
	public Rust_Expression newClassCreation(Rust_Type type,
			ArrayList<Rust_Expression> args, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_ClassCreationExpression creat = new Rust_ClassCreationExpression();
//		return creat.generateCreation(type, args, source);
	}

	@Override
	public Rust_Expression newMethodInvocation(Rust_Variable var,
			ArrayList<Rust_Expression> args, AbstractToken source)
	{
		Rust_MethodInvocation creat = new Rust_MethodInvocation();
		return creat.generateInvocation(null, var, args, source);
	}

	@Override
	public Rust_Expression newCurrentDatetime()
	{
		throw new RuntimeException("Need to implement");
	}

	// ================ Functions ================

	@Override
	public Rust_Expression newLengthFunction(Rust_Expression expr, AbstractToken source)
	{
		Rust_LenMethod lenMeth = new Rust_LenMethod();
		return lenMeth.generateLength(expr, source);
	}

	@Override
	public Rust_Expression newTrimFunction(Rust_Expression expr, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_TrimMethod trimMeth = new Rust_TrimMethod();
//		return trimMeth.generateTrim(expr, source);
	}

	@Override
	public Rust_Expression newStringFunction(Oper1Types types, Rust_Expression expr, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_ToStringMethod strMeth = new Rust_ToStringMethod();
//		return strMeth.generateString(types, expr, source);
	}

	@Override
	public Rust_Expression newSubstringFunction(Rust_Expression expr, Rust_Expression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, Rust_Expression ecOrnc,
			boolean ncMightBeTooBig, AbstractToken source)
	{
		return wrapExpression(Rust_SubscriptExpression.generateSubscriptExpression(expr, sc, whichSC,
				whichEC, ecOrnc, ncMightBeTooBig, source));
	}

	@Override
	public AbstractExpression newEndsWithFunction(Rust_Expression expr, Rust_Expression patt,
			AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_EndsWithMethod endsMeth = new Rust_EndsWithMethod();
//		return endsMeth.generateEndsWith(expr, patt, source);
	}

	@Override
	public Rust_Expression newStartsWithFunction(Rust_Expression expr, Rust_Expression patt,
			Rust_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Rust_StartsWithMethod startsMeth = new Rust_StartsWithMethod();
		return startsMeth.generateStartsWith(expr, patt, sc, whichSC, source);
	}

	@Override
	public Rust_Expression newIndexOfFunction(Rust_Variable string, Rust_Expression patt,
			Rust_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_IndexOfMethod indexMeth = new Rust_IndexOfMethod();
//		return indexMeth.generateIndexOf(string, patt, sc, whichSC, source);
	}

	@Override
	public Rust_Expression newFormatNumber(Rust_Expression expr, int length,
			AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
//		Rust_Expression fmt = newLiteralExpression("%" + length + "d", null);
//		Rust_StringFormatFunc func = new Rust_StringFormatFunc();
//		return func.generateStringFormat(expr, fmt, source);
	}

	// ================ Terminals ================

	@Override
	public Rust_Number newNumber(String value, AbstractToken source)
	{
		Rust_Number num = new Rust_Number();
		return num.generateNumber(value, source);
	}

	@Override
	public Rust_HexNumber newHexNumber(String value, AbstractToken source)
	{
		Rust_HexNumber num = new Rust_HexNumber();
		return num.generateHexNumber(value, source);
	}

	@Override
	public Rust_Literal newLiteral(String value, AbstractToken source)
	{
		Rust_Literal lit = new Rust_Literal();
		return lit.generateLiteral(value, source);
	}

	@Override
	public Rust_Character_Literal newCharLiteral(String value, AbstractToken source)
	{
		Rust_Character_Literal lit = new Rust_Character_Literal();
		return lit.generateCharLiteral(value, source);
	}
}

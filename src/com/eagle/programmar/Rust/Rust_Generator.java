// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

package com.eagle.programmar.Rust;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.Rust.Rust_Program.Rust_TopElement;
import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Expressions.Rust_AsExpression;
import com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BitwiseExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BuiltIn;
import com.eagle.programmar.Rust.Expressions.Rust_ClassCreationExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ExpressionArray;
import com.eagle.programmar.Rust.Expressions.Rust_FunctionCall;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalAndExpression;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalOrExpression;
import com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NegativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NotExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ShiftExpression;
import com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Functions.Rust_AbsMethod;
import com.eagle.programmar.Rust.Functions.Rust_FindMethod;
import com.eagle.programmar.Rust.Functions.Rust_FormatFunction;
import com.eagle.programmar.Rust.Functions.Rust_LenMethod;
import com.eagle.programmar.Rust.Functions.Rust_PowMethod;
import com.eagle.programmar.Rust.Functions.Rust_PrintlnFunction;
import com.eagle.programmar.Rust.Functions.Rust_StartsWithMethod;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.programmar.Rust.Functions.Rust_TrimMethod;
import com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
import com.eagle.programmar.Rust.Statements.Rust_BreakStatement;
import com.eagle.programmar.Rust.Statements.Rust_ConstStatement;
import com.eagle.programmar.Rust.Statements.Rust_ExitStatement;
import com.eagle.programmar.Rust.Statements.Rust_ExpressionStatement;
import com.eagle.programmar.Rust.Statements.Rust_ForStatement;
import com.eagle.programmar.Rust.Statements.Rust_IfStatement;
import com.eagle.programmar.Rust.Statements.Rust_LetStatement;
import com.eagle.programmar.Rust.Statements.Rust_MatchStatement;
import com.eagle.programmar.Rust.Statements.Rust_Pragma;
import com.eagle.programmar.Rust.Statements.Rust_ReturnStatement;
import com.eagle.programmar.Rust.Statements.Rust_WhileStatement;
import com.eagle.programmar.Rust.Symbols.Rust_Function_Definition;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Character_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_HexNumber;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;

public class Rust_Generator
		extends EagleGenerator<Rust_Statement, Rust_Expression, Rust_Variable, Rust_Type>
{
	public static String NAME = "Rust";
	public static String SUFFIX = ".rs";

	private Rust_Program _program;
//	private EagleMetrics _metrics = null;

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
	public AbstractLanguage getTransformedProgram()
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
			_currentFunction.leftParen = new PunctuationLeftParen();
			_currentFunction.rightParen = new PunctuationRightParen();
			
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

		if (stmt.getWhich() instanceof Rust_ConstStatement)
		{
			// Put it in program, not in the 'main' method
			Rust_TopElement element = new Rust_TopElement();
			element.setWhich(stmt);
			_program.addTopElement(element);
			return;
		}

		checkFunction();
		
		if (_currentFunction.block == null)
		{
			_currentFunction.block = new Rust_Block_Statement();
			_currentFunction.block.leftBrace = new PunctuationLeftBrace();
			_currentFunction.block.rightBrace = new PunctuationRightBrace();
		}
		if (_currentFunction.block.statements == null)
		{
			_currentFunction.block.statements = new TokenList<Rust_Statement>();
		}
		_currentFunction.block.statements.addToken(stmt);

		stmt.setTransformationSource(source);
	}

	@Override
	public void addComment(String comment, AbstractToken source)
	{
		Rust_Comment comm = new Rust_Comment("// " + comment);
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
		return Rust_Block_Statement.generateBlock(statements, source);
	}

	@Override
	public Rust_Statement newBreakStatement(AbstractToken source)
	{
		return Rust_BreakStatement.generateBreak(source);
	}

	@Override
	public Rust_Statement newDataDeclaration(StaticEnum isStatic, String name, Rust_Expression size,
			Rust_Type type, Rust_Expression initial, AbstractToken source)
	{
		if (isStatic == StaticEnum.CONST)
		{
			return wrapStatement(Rust_ConstStatement.newConstDeclaration(isStatic, name, size, type, initial, source));
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
		return Rust_ExitStatement.newExitStatement(code, source);
	}

	@Override
	public Rust_Statement newExpressionStatement(Rust_Expression expr, AbstractToken source)
	{
		return Rust_ExpressionStatement.newExpressionStatement(expr, source);
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
		return Rust_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Rust_Statement newIfStatement(Rust_Expression condition,
			ArrayList<Rust_Statement> ifTrue,
			ArrayList<Rust_Statement> ifFalse, AbstractToken source)
	{
		return Rust_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
	}

	@Override
	public Rust_Statement newIfElseIfStatement(Rust_Expression condition,
			ArrayList<Rust_Statement> ifTrue, ArrayList<Rust_Expression> elseIfConds,
			ArrayList<ArrayList<Rust_Statement>> elseIfParts,
			ArrayList<Rust_Statement> ifFalse, AbstractToken source)
	{
		return Rust_IfStatement.generateIfElseIfMany(condition, ifTrue,
				elseIfConds, elseIfParts, ifFalse, source);
	}

	@Override
	public Rust_Statement newForLoopStatement1(Rust_Expression init,
			Rust_Expression term, Rust_Expression incr, Rust_Statement action,
			AbstractToken source)
	{
		return Rust_ForStatement.generateForLoopOne(init, term,
				incr, action, source);
	}

	@Override
	public Rust_Statement newForLoopStatement(Rust_Expression init,
			Rust_Expression term, Rust_Expression incr,
			ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		return Rust_ForStatement.generateForLoopMany(init, term,
				incr, actions, source);
	}

	@Override
	public Rust_Statement newForRangeStatement1(Rust_Variable var, TypeEnum type,
			Rust_Expression first, RelationalEnum relOp, Rust_Expression last,
			Rust_Expression step, Rust_Statement action, AbstractToken source)
	{
		return Rust_ForStatement.generateForRangeOne(var, type, first, relOp, last, step, action, source);
	}

	@Override
	public Rust_Statement newForRangeStatement(Rust_Variable var, TypeEnum type,
			Rust_Expression first, RelationalEnum relOp, Rust_Expression last,
			Rust_Expression step, ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		return Rust_ForStatement.generateForRangeMany(var, type, first, relOp, last, step, actions, source);
	}

	@Override
	public Rust_Statement newPragma(PragmaEnum prag, AbstractToken source)
	{
		switch (prag)
		{
		case IGNORE_UNREACHABLE_CODE:
			break;
		default:
			return null;	// Don't care
		}
		return Rust_Pragma.generatePragma(prag, source);
	}

	@Override
	public Rust_Expression newPrintFunction1(Rust_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		return Rust_PrintlnFunction.generatePrintFunc1(line, type, newLine, toErr, source);
	}

	@Override
	public Rust_Statement newPrintStatement1(Rust_Expression line, TypeEnum type,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		Rust_Expression prtExpr = newPrintFunction1(line, type, newLine, toErr, source);
		return newExpressionStatement(prtExpr, source);
	}

	@Override
	public Rust_Expression newPrintFunction(ArrayList<Rust_Expression> pieces, ArrayList<TypeEnum> types,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		return Rust_PrintlnFunction.generatePrintFunc(pieces, types, newLine, toErr, source);
	}

	@Override
	public Rust_Statement newPrintStatement(ArrayList<Rust_Expression> pieces, ArrayList<TypeEnum> types,
			boolean newLine, boolean toErr, AbstractToken source)
	{
		Rust_Expression prtExpr = newPrintFunction(pieces, types, newLine, toErr, source);
		return newExpressionStatement(prtExpr, source);
	}

	@Override
	public Rust_Statement newReturnStatement(Rust_Expression ret,
			AbstractToken source)
	{
		return Rust_ReturnStatement.generateReturn(ret, source);
	}

	@Override
	public Rust_Statement newSwitchStatement(Rust_Expression expr,
			ArrayList<ArrayList<Rust_Expression>> values, ArrayList<ArrayList<Rust_Statement>> cases,
			ArrayList<Rust_Statement> defaultCase, AbstractToken source)
	{
		return Rust_MatchStatement.generateMatch(expr, values, cases, defaultCase, source);
	}

	@Override
	public Rust_Statement newWhileStatement1(Rust_Expression condition,
			Rust_Statement action, AbstractToken source)
	{
		return Rust_WhileStatement.generateWhileOne(condition, action, source);
	}

	@Override
	public Rust_Statement newWhileStatement(Rust_Expression condition,
			ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		return Rust_WhileStatement.generateWhileMany(condition, actions, source);
	}

	// ================ Expressions ================

	@Override
	public Rust_Expression newAdditiveExpression(Oper2Types types, Rust_Expression left,
			AdditiveEnum oper, Rust_Expression right, AbstractToken source)
	{
		return Rust_AdditiveExpression.generateAdditive(types, left, oper, right, source);
	}

	@Override
	public Rust_Expression newAppendExpression(Rust_Expression left,
			Rust_Expression right, AbstractToken source)
	{
		return Rust_AdditiveExpression.generateAppend(left, right, source);
	}

	@Override
	public Rust_Expression newAssignmentExpression(String name, SubscriptEnum offset,
			Rust_Expression subscript, AssignmentEnum oper, Rust_Expression expression, AbstractToken source)
	{
		Rust_Variable var = Rust_Variable.generateVariable(name);
		return Rust_AssignmentExpression.generateAssignment(var, subscript, oper, expression, source);
	}

	@Override
	public AbstractExpression newHashAssignment(String name, Rust_Expression subscript,
			Rust_Expression expression, AbstractToken source)
	{
		Rust_Variable var = Rust_Variable.generateVariable(name + ".insert");
		ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
		ArrayList<TypeEnum> types = new ArrayList<TypeEnum>();
		args.add(subscript);
		args.add(expression);
		types.add(TypeEnum.INTEGER);
		types.add(TypeEnum.STRING);
		Rust_Identifier_Reference className = null;
		return Rust_FunctionCall.generateInvocation(className, var, args, types, source);
	}

	@Override
	public Rust_Expression newPostIncrementExpression(String name, SubscriptEnum offset,
			Rust_Expression subscript, IncrementEnum oper, AbstractToken source)
	{
		Rust_Variable var = Rust_Variable.generateVariable(name);
		Rust_Expression one = newNumberExpression("1", null);
		
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

		return Rust_AssignmentExpression.generateAssignment(var, null, newOper, one, source);
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
		return wrapExpression(Rust_BuiltIn.generateBuiltIn(builtin, source));
	}

	@Override
	public Rust_Expression newExponentExpression(Rust_Expression left, Rust_Expression right, AbstractToken source)
	{
		return Rust_PowMethod.generatePower(left, right, source);
	}

	@Override
	public Rust_Expression newAbsFunction(Rust_Expression expr, AbstractToken source)
	{
		return Rust_AbsMethod.generateAbsFunc(expr, source);
	}

	@Override
	public Rust_Expression newLiteralExpression(String literal, AbstractToken source)
	{
		return Rust_Literal.generateLiteralExpression(literal, source);
	}

	@Override
	public Rust_Expression newLogicalAndExpression(Rust_Expression left,
			Rust_Expression right, AbstractToken source)
	{
		return Rust_LogicalAndExpression.generateLogicalAnd(left, right, source);
	}

	@Override
	public Rust_Expression newLogicalOrExpression(Rust_Expression left,
			LogicalOrEnum oper, Rust_Expression right, AbstractToken source)
	{
		return Rust_LogicalOrExpression.generateLogicalOr(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseExpression(Rust_Expression left,
			BitwiseEnum oper, Rust_Expression right, AbstractToken source)
	{
		return Rust_BitwiseExpression.generateBitwise(left, oper, right, source);
	}

	@Override
	public AbstractExpression newBitwiseNotExpression(Rust_Expression expr,
			AbstractToken source)
	{
		return Rust_NotExpression.generateNot(expr, source);
	}

	@Override
	public Rust_Expression newMultiplicativeExpression(Rust_Expression left,
			MultiplicativeEnum oper, Rust_Expression right, AbstractToken source)
	{
		return Rust_MultiplicativeExpression.generateMultiplicative(left, oper,
				right, source);
	}

	@Override
	public Rust_Expression newNegativeExpression(NegativeEnum sign,
			Rust_Expression expr, AbstractToken source)
	{
		return Rust_NegativeExpression.generateNegative(sign, expr, source);
	}

	@Override
	public Rust_Expression newTruncateExpression(Rust_Expression expr, AbstractToken source)
	{
		Rust_Type type = Rust_Type.newPrimitiveType("i32");
		return Rust_AsExpression.generateAsExpr(expr, type, source);
	}

	@Override
	public Rust_Expression newLogicalNotExpression(Rust_Expression expr, AbstractToken source)
	{
		AbstractToken which = expr.getWhich();
		if (which instanceof TerminalToken || which instanceof Rust_ParenthesizedExpression)
		{
			return Rust_NotExpression.generateNot(expr, source);
		}

		Rust_Expression parens = Rust_ParenthesizedExpression.generateParentheses(expr, source);
		return Rust_NotExpression.generateNot(parens, source);
	}

	@Override
	public AbstractExpression newLogicalExpression(boolean bool, AbstractToken source)
	{
		return wrapExpression(Rust_BuiltIn.generateBuiltIn(
				(bool ? BuiltInEnum.TRUE : BuiltInEnum.FALSE), source));
	}

	@Override
	public Rust_Expression newNumberExpression(String number, AbstractToken source)
	{
		return wrapExpression(Rust_Number.generateNumber(number, source));
	}

	@Override
	public Rust_Expression newParenthesizedExpression(Rust_Expression expr, AbstractToken source)
	{
		return Rust_ParenthesizedExpression.generateParentheses(expr, source);
	}

	@Override
	public Rust_Expression newRelationalExpression(Oper2Types types, Rust_Expression left, RelationalEnum relOp,
			Rust_Expression right, AbstractToken source)
	{
		return Rust_RelationalExpression.generateRelational(types, left, relOp, right, source);
	}

	@Override
	public Rust_Expression newShiftExpression(Rust_Expression left,
			ShiftEnum shift, Rust_Expression right, AbstractToken source)
	{
		return Rust_ShiftExpression.generateShift(left, shift, right, source);
	}

	@Override
	public Rust_Expression newArrayExpression(ArrayList<AbstractExpression> exprs,
			AbstractToken source)
	{
		return Rust_ExpressionArray.generateArray(exprs, source);
	}

	@Override
	public Rust_Expression newVariableExpression(String name, SubscriptEnum offset,
			Rust_Expression subscript, AbstractToken source)
	{
		return Rust_VariableExpression.generateVariableExpression(name, offset, subscript, source);
	}

	@Override
	public Rust_Variable newVariable(String name)
	{
		return Rust_Variable.generateVariable(name);
	}

	@Override
	public Rust_Expression newClassCreation(Rust_Type type,
			ArrayList<Rust_Expression> args, AbstractToken source)
	{
		return Rust_ClassCreationExpression.generateCreation(type, args, source);
	}

	@Override
	public Rust_Expression newMethodInvocation(Rust_Variable var,
			ArrayList<Rust_Expression> args, ArrayList<TypeEnum> types, AbstractToken source)
	{
		return Rust_FunctionCall.generateInvocation(null, var, args, types, source);
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
		return Rust_LenMethod.generateLengthI32(expr, source);
	}

	@Override
	public Rust_Expression newTrimFunction(Rust_Expression expr, AbstractToken source)
	{
		return Rust_TrimMethod.generateTrim(expr, source);
	}

	@Override
	public Rust_Expression newStringFunction(TypeEnum type, Rust_Expression expr, AbstractToken source)
	{
		return Rust_ToStringMethod.generateString(type, expr, source);
	}

	@Override
	public Rust_Expression newSubstringFunction(Rust_Expression expr, Rust_Expression sc,
			SubstringSCEnum whichSC, SubstringECEnum whichEC, Rust_Expression ecOrnc,
			boolean ncMightBeTooBig, AbstractToken source)
	{
		return Rust_SubscriptExpression.generateSubscriptExpression(expr, sc, whichSC,
				whichEC, ecOrnc, ncMightBeTooBig, source);
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
		return Rust_StartsWithMethod.generateStartsWith(expr, patt, sc, whichSC, source);
	}

	@Override
	public Rust_Expression newIndexOfFunction(Rust_Variable string, Rust_Expression patt,
			Rust_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		return Rust_FindMethod.generateFind(string, patt, sc, whichSC, source);
	}

	@Override
	public Rust_Expression newFormatNumber(Rust_Expression expr, int length,
			AbstractToken source)
	{
		Rust_Expression fmt = newLiteralExpression("{}", null);
		ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
		args.add(expr);
		return Rust_FormatFunction.generateFormat(fmt, args, source);
	}

	@Override
	public AbstractExpression newFormatDecimal2(Rust_Expression expr, int width, int decimals,
			AbstractToken source)
	{
		Rust_Expression fmt = newLiteralExpression("{:" + width + "." + decimals + "}", null);
		ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
		args.add(expr);
		return Rust_FormatFunction.generateFormat(fmt, args, source);
	}

	@Override
	public Rust_Expression newFormatDecimal(Rust_Expression expr, int decimals,
			AbstractToken source)
	{
		Rust_Expression fmt = newLiteralExpression("{:." + decimals + "}", null);
		ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
		args.add(expr);
		return Rust_FormatFunction.generateFormat(fmt, args, source);
	}

	// ================ Terminals ================

	@Override
	public Rust_Number newNumber(String value, AbstractToken source)
	{
		return Rust_Number.generateNumber(value, source);
	}

	@Override
	public Rust_HexNumber newHexNumber(String value, AbstractToken source)
	{
		return Rust_HexNumber.generateHexNumber(value, source);
	}

	@Override
	public Rust_Literal newLiteral(String value, AbstractToken source)
	{
		return Rust_Literal.generateLiteral(value, source);
	}

	@Override
	public Rust_Character_Literal newCharLiteral(String value, AbstractToken source)
	{
		return Rust_Character_Literal.generateCharLiteral(value, source);
	}
}

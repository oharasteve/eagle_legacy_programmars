// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

namespace com.eagle.programmar.Rust
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleMetrics = com.eagle.metrics.EagleMetrics;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using ParserManager = com.eagle.parsers.ParserManager;
	using Rust_TopElement = com.eagle.programmar.Rust.Rust_Program.Rust_TopElement;
	using Rust_AdditiveExpression = com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
	using Rust_AssignmentExpression = com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
	using Rust_BitwiseExpression = com.eagle.programmar.Rust.Expressions.Rust_BitwiseExpression;
	using Rust_BuiltIn = com.eagle.programmar.Rust.Expressions.Rust_BuiltIn;
	using Rust_CastExpression = com.eagle.programmar.Rust.Expressions.Rust_CastExpression;
	using Rust_ClassCreationExpression = com.eagle.programmar.Rust.Expressions.Rust_ClassCreationExpression;
	using Rust_ExpressionArray = com.eagle.programmar.Rust.Expressions.Rust_ExpressionArray;
	using Rust_LogicalAndExpression = com.eagle.programmar.Rust.Expressions.Rust_LogicalAndExpression;
	using Rust_LogicalOrExpression = com.eagle.programmar.Rust.Expressions.Rust_LogicalOrExpression;
	using Rust_MethodInvocation = com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation;
	using Rust_MultiplicativeExpression = com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression;
	using Rust_NegativeExpression = com.eagle.programmar.Rust.Expressions.Rust_NegativeExpression;
	using Rust_NotExpression = com.eagle.programmar.Rust.Expressions.Rust_NotExpression;
	using Rust_ParenthesizedExpression = com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
	using Rust_RelationalExpression = com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
	using Rust_ShiftExpression = com.eagle.programmar.Rust.Expressions.Rust_ShiftExpression;
	using Rust_SubscriptExpression = com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
	using Rust_VariableExpression = com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
	using Rust_AbsMethod = com.eagle.programmar.Rust.Functions.Rust_AbsMethod;
	using Rust_FindMethod = com.eagle.programmar.Rust.Functions.Rust_FindMethod;
	using Rust_FormatFunction = com.eagle.programmar.Rust.Functions.Rust_FormatFunction;
	using Rust_LenMethod = com.eagle.programmar.Rust.Functions.Rust_LenMethod;
	using Rust_PowMethod = com.eagle.programmar.Rust.Functions.Rust_PowMethod;
	using Rust_PrintlnFunction = com.eagle.programmar.Rust.Functions.Rust_PrintlnFunction;
	using Rust_StartsWithMethod = com.eagle.programmar.Rust.Functions.Rust_StartsWithMethod;
	using Rust_ToStringMethod = com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
	using Rust_TrimMethod = com.eagle.programmar.Rust.Functions.Rust_TrimMethod;
	using Rust_Block_Statement = com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
	using Rust_BreakStatement = com.eagle.programmar.Rust.Statements.Rust_BreakStatement;
	using Rust_ConstStatement = com.eagle.programmar.Rust.Statements.Rust_ConstStatement;
	using Rust_ExitStatement = com.eagle.programmar.Rust.Statements.Rust_ExitStatement;
	using Rust_ExpressionStatement = com.eagle.programmar.Rust.Statements.Rust_ExpressionStatement;
	using Rust_ForStatement = com.eagle.programmar.Rust.Statements.Rust_ForStatement;
	using Rust_IfStatement = com.eagle.programmar.Rust.Statements.Rust_IfStatement;
	using Rust_LetStatement = com.eagle.programmar.Rust.Statements.Rust_LetStatement;
	using Rust_MatchStatement = com.eagle.programmar.Rust.Statements.Rust_MatchStatement;
	using Rust_ReturnStatement = com.eagle.programmar.Rust.Statements.Rust_ReturnStatement;
	using Rust_WhileStatement = com.eagle.programmar.Rust.Statements.Rust_WhileStatement;
	using Rust_Function_Definition = com.eagle.programmar.Rust.Symbols.Rust_Function_Definition;
	using Rust_Identifier_Reference = com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
	using Rust_Character_Literal = com.eagle.programmar.Rust.Terminals.Rust_Character_Literal;
	using Rust_Comment = com.eagle.programmar.Rust.Terminals.Rust_Comment;
	using Rust_HexNumber = com.eagle.programmar.Rust.Terminals.Rust_HexNumber;
	using Rust_Literal = com.eagle.programmar.Rust.Terminals.Rust_Literal;
	using Rust_Number = com.eagle.programmar.Rust.Terminals.Rust_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalToken = com.eagle.tokens.TerminalToken;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;

	public class Rust_Generator : EagleGenerator<Rust_Statement, Rust_Expression, Rust_Variable, Rust_Type>
	{
		public static string NAME = "Rust";
		public static string SUFFIX = ".rs";

		private Rust_Program _program;
		private EagleMetrics _metrics = null;

		public Rust_Generator(ParserManager parser, string className) : base(parser)
		{
			_program = new Rust_Program();
			_program.elements = new TokenList<Rust_TopElement>();
		}

		public override string Name
		{
			get
			{
				return NAME;
			}
		}

		public override string Suffix
		{
			get
			{
				return SUFFIX;
			}
		}

		public override string mainName()
		{
			return "main";
		}

		public override void addMainArgs()
		{
			// Don't need any args for 'fn main()'
		}

		public override void addCallToMain()
		{
			// Don't ever need this in Rust
		}

		public override AbstractLanguage TransfomedProgram
		{
			get
			{
				return _program;
			}
		}

		public static Rust_Expression wrapExpression(AbstractToken token)
		{
			Rust_Expression wrapper = new Rust_Expression();
			wrapper.setWhich(token);
			return wrapper;
		}

		public static Rust_Statement wrapStatement(AbstractToken token)
		{
			if (token == null)
			{
				return null;
			}
			token.setPresent(true);
			Rust_Statement wrapper = new Rust_Statement();
			wrapper.setWhich(token);
			wrapper.setPresent(true);
			return wrapper;
		}

		public override Rust_Type transformType(TypeEnum type, string typeName, AbstractToken source)
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

		public override void addMethod(Rust_Type returnType, string name, AbstractToken source)
		{
			_previousFunction = _currentFunction;
			_currentFunction = new Rust_Function();
			_currentFunction.newRustFunction(returnType, name);
			_currentFunction.setTransformationSource(source);

			Rust_TopElement topElt = new Rust_TopElement();
			topElt.setWhich(_currentFunction);
			_program.addTopElement(topElt);
		}

		public override void addMethodParameter(Rust_Type type, string name)
		{
			// Don't add args to "fn main()"
			if (!_currentFunction.id.getValue().Equals("main"))
			{
				_currentFunction.addFunctionParameter(type, name);
			}
		}

		public override void doneMethod()
		{
			_currentFunction = _previousFunction;
		}

		public override void addStatement(Rust_Statement stmt, AbstractToken source)
		{
			if (stmt == null)
			{
				return;
			}

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

		public override void addComment(string comment, AbstractToken source)
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

		public override Rust_Statement newBlockStatement(List<Rust_Statement> statements, AbstractToken source)
		{
			return Rust_Block_Statement.generateBlock(statements, source);
		}

		public override Rust_Statement newBreakStatement(AbstractToken source)
		{
			return Rust_BreakStatement.generateBreak(source);
		}

		public override Rust_Statement newDataDeclaration(bool isStatic, string name, Rust_Expression size, Rust_Type type, Rust_Expression initial, AbstractToken source)
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

		public override Rust_Statement newDoUntilStatement1(Rust_Expression condition, Rust_Statement action, AbstractToken source)
		{
			throw new Exception("Need to implement");
	//		Rust_WhileStatement whileStmt = new Rust_WhileStatement();
	//		return whileStmt.generateDoUntil1(condition, action, source);
		}

		public override Rust_Statement newDoUntilStatement(Rust_Expression condition, List<Rust_Statement> actions, AbstractToken source)
		{
			throw new Exception("Need to implement");
	//		Rust_WhileStatement whileStmt = new Rust_WhileStatement();
	//		return whileStmt.generateDoUntil(condition, actions, source);
		}

		public override Rust_Statement newExitStatement(Rust_Expression code, AbstractToken source)
		{
			return Rust_ExitStatement.newExitStatement(code, source);
		}

		public override Rust_Statement newExpressionStatement(Rust_Expression expr, AbstractToken source)
		{
			return Rust_ExpressionStatement.newExpressionStatement(expr, source);
		}

		public override Rust_Statement newGlobalVariable(string variableName, AbstractToken source)
		{
			return null; // Don't need to declare variables as 'global'
		}

		public override Rust_Statement newIfStatement1(Rust_Expression condition, Rust_Statement ifTrue, Rust_Statement ifFalse, AbstractToken source)
		{
			return Rust_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
		}

		public override Rust_Statement newIfStatement(Rust_Expression condition, List<Rust_Statement> ifTrue, List<Rust_Statement> ifFalse, AbstractToken source)
		{
			return Rust_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
		}

		public override Rust_Statement newForLoopStatement1(Rust_Expression init, Rust_Expression term, Rust_Expression incr, Rust_Statement action, AbstractToken source)
		{
			return Rust_ForStatement.generateForLoopOne(init, term, incr, action, source);
		}

		public override Rust_Statement newForLoopStatement(Rust_Expression init, Rust_Expression term, Rust_Expression incr, List<Rust_Statement> actions, AbstractToken source)
		{
			return Rust_ForStatement.generateForLoopMany(init, term, incr, actions, source);
		}

		public override Rust_Statement newForRangeStatement1(Rust_Variable var, TypeEnum type, Rust_Expression first, RelationalEnum relOp, Rust_Expression last, Rust_Expression step, Rust_Statement action, AbstractToken source)
		{
			return Rust_ForStatement.generateForRangeOne(var, type, first, relOp, last, step, action, source);
		}

		public override Rust_Statement newForRangeStatement(Rust_Variable var, TypeEnum type, Rust_Expression first, RelationalEnum relOp, Rust_Expression last, Rust_Expression step, List<Rust_Statement> actions, AbstractToken source)
		{
			return Rust_ForStatement.generateForRangeMany(var, type, first, relOp, last, step, actions, source);
		}

		public override Rust_Expression newPrintFunction(Rust_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			return Rust_PrintlnFunction.generatePrintFunc(line, type, newLine, toErr, source);
		}

		public override Rust_Statement newPrintStatement(Rust_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			Rust_Expression prtExpr = newPrintFunction(line, type, newLine, toErr, source);
			return newExpressionStatement(prtExpr, source);
		}

		public override Rust_Statement newReturnStatement(Rust_Expression ret, AbstractToken source)
		{
			return Rust_ReturnStatement.generateReturn(ret, source);
		}

		public override Rust_Statement newSwitchStatement(Rust_Expression expr, List<Rust_Expression> values, List<List<Rust_Statement>> cases, List<Rust_Statement> defaultCase, AbstractToken source)
		{
			return Rust_MatchStatement.generateMatch(expr, values, cases, defaultCase, source);
		}

		public override Rust_Statement newWhileStatement1(Rust_Expression condition, Rust_Statement action, AbstractToken source)
		{
			return Rust_WhileStatement.generateWhileOne(condition, action, source);
		}

		public override Rust_Statement newWhileStatement(Rust_Expression condition, List<Rust_Statement> actions, AbstractToken source)
		{
			return Rust_WhileStatement.generateWhileMany(condition, actions, source);
		}

		// ================ Expressions ================

		public override Rust_Expression newAdditiveExpression(Oper2Types types, Rust_Expression left, AdditiveEnum oper, Rust_Expression right, AbstractToken source)
		{
			return Rust_AdditiveExpression.generateAdditive(types, left, oper, right, source);
		}

		public override Rust_Expression newAppendExpression(Oper2Types types, Rust_Expression left, Rust_Expression right, AbstractToken source)
		{
			return Rust_AdditiveExpression.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
		}

		public override Rust_Expression newAssignmentExpression(string name, SubscriptEnum offset, Rust_Expression subscript, AssignmentEnum oper, Rust_Expression expression, AbstractToken source)
		{
			Rust_Variable var = Rust_Variable.generateVariable(name);
			return Rust_AssignmentExpression.generateAssignment(var, subscript, oper, expression, source);
		}

		public override AbstractExpression newHashAssignment(string name, Rust_Expression subscript, Rust_Expression expression, AbstractToken source)
		{
			Rust_Variable var = Rust_Variable.generateVariable(name + ".insert");
			List<Rust_Expression> args = new List<Rust_Expression>();
			args.Add(subscript);
			args.Add(expression);
			Rust_Identifier_Reference className = null;
			return Rust_MethodInvocation.generateInvocation(className, var, args, source);
		}

		public override Rust_Expression newPostIncrementExpression(string name, SubscriptEnum offset, Rust_Expression subscript, IncrementEnum oper, AbstractToken source)
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
				throw new Exception("Unexpected operator: " + oper);
			}

			return Rust_AssignmentExpression.generateAssignment(var, null, newOper, one, source);
		}

		public override Rust_Expression newPreIncrementExpression(string name, SubscriptEnum offset, Rust_Expression subscript, IncrementEnum oper, AbstractToken source)
		{
			// ++x and x++ are the same unless they are embedded into another expression
			return newPostIncrementExpression(name, offset, subscript, oper, source);
		}

		public override Rust_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
		{
			return wrapExpression(Rust_BuiltIn.generateBuiltIn(builtin, source));
		}

		public override Rust_Expression newExponentExpression(Rust_Expression left, Rust_Expression right, AbstractToken source)
		{
			return Rust_PowMethod.generatePower(left, right, source);
		}

		public override Rust_Expression newAbsFunction(Rust_Expression expr, AbstractToken source)
		{
			return Rust_AbsMethod.generateAbsFunc(expr, source);
		}

		public override Rust_Expression newLiteralExpression(string literal, AbstractToken source)
		{
			return Rust_Literal.generateLiteralExpression(literal, source);
		}

		public override Rust_Expression newLogicalAndExpression(Rust_Expression left, Rust_Expression right, AbstractToken source)
		{
			return Rust_LogicalAndExpression.generateLogicalAnd(left, right, source);
		}

		public override Rust_Expression newLogicalOrExpression(Rust_Expression left, LogicalOrEnum oper, Rust_Expression right, AbstractToken source)
		{
			return Rust_LogicalOrExpression.generateLogicalOr(left, oper, right, source);
		}

		public override AbstractExpression newBitwiseExpression(Rust_Expression left, BitwiseEnum oper, Rust_Expression right, AbstractToken source)
		{
			return Rust_BitwiseExpression.generateBitwise(left, oper, right, source);
		}

		public override AbstractExpression newBitwiseNotExpression(Rust_Expression expr, AbstractToken source)
		{
			return Rust_NotExpression.generateNot(expr, source);
		}

		public override Rust_Expression newMultiplicativeExpression(Rust_Expression left, MultiplicativeEnum oper, Rust_Expression right, AbstractToken source)
		{
			return Rust_MultiplicativeExpression.generateMultiplicative(left, oper, right, source);
		}

		public override Rust_Expression newNegativeExpression(NegativeEnum sign, Rust_Expression expr, AbstractToken source)
		{
			return Rust_NegativeExpression.generateNegative(sign, expr, source);
		}

		public override Rust_Expression newTruncateExpression(Rust_Expression expr, AbstractToken source)
		{
			return Rust_CastExpression.newCastExpression("i32", expr, source);
		}

		public override Rust_Expression newLogicalNotExpression(Rust_Expression expr, AbstractToken source)
		{
			AbstractToken which = expr.getWhich();
			if (which is TerminalToken || which is Rust_ParenthesizedExpression)
			{
				return Rust_NotExpression.generateNot(expr, source);
			}

			Rust_Expression parens = Rust_ParenthesizedExpression.generateParentheses(expr, source);
			return Rust_NotExpression.generateNot(parens, source);
		}

		public override AbstractExpression newLogicalExpression(bool @bool, AbstractToken source)
		{
			return wrapExpression(Rust_BuiltIn.generateBuiltIn((@bool ? BuiltInEnum.TRUE : BuiltInEnum.FALSE), source));
		}

		public override Rust_Expression newNumberExpression(string number, AbstractToken source)
		{

// ====================================================================================================
// End of the allowed output for the Free Edition of Java to C# Converter.

// To buy a Premium Edition license, visit our website:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

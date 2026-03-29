// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

namespace com.eagle.programmar.Python
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using ParserManager = com.eagle.parsers.ParserManager;
	using Python_Statement = com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
	using Python_StatementOrComment = com.eagle.programmar.Python.Python_ComplexStatement.Python_StatementOrComment;
	using Python_Additive_Expression = com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
	using Python_Assignment_Expression = com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
	using Python_Bitwise_Expression = com.eagle.programmar.Python.Expressions.Python_Bitwise_Expression;
	using Python_Bitwise_Not_Expression = com.eagle.programmar.Python.Expressions.Python_Bitwise_Not_Expression;
	using Python_BracesColons = com.eagle.programmar.Python.Expressions.Python_BracesColons;
	using Python_Brackets = com.eagle.programmar.Python.Expressions.Python_Brackets;
	using Python_BuiltIn = com.eagle.programmar.Python.Expressions.Python_BuiltIn;
	using Python_Function_Call = com.eagle.programmar.Python.Expressions.Python_Function_Call;
	using Python_Literals = com.eagle.programmar.Python.Expressions.Python_Literals;
	using Python_Logical_And_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_And_Expression;
	using Python_Logical_Not_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
	using Python_Logical_Or_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression;
	using Python_Multiplicative_Expression = com.eagle.programmar.Python.Expressions.Python_Multiplicative_Expression;
	using Python_Negative_Expression = com.eagle.programmar.Python.Expressions.Python_Negative_Expression;
	using Python_Parenthesized_Expression = com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
	using Python_Power_Expression = com.eagle.programmar.Python.Expressions.Python_Power_Expression;
	using Python_Relational_Expression = com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
	using Python_Shift_Expression = com.eagle.programmar.Python.Expressions.Python_Shift_Expression;
	using Python_SubscriptExpression = com.eagle.programmar.Python.Expressions.Python_SubscriptExpression;
	using Python_VariableExpression = com.eagle.programmar.Python.Expressions.Python_VariableExpression;
	using Python_Abs_Function = com.eagle.programmar.Python.Functions.Python_Abs_Function;
	using Python_Int_Function = com.eagle.programmar.Python.Functions.Python_Int_Function;
	using Python_Len_Function = com.eagle.programmar.Python.Functions.Python_Len_Function;
	using Python_Print_Function = com.eagle.programmar.Python.Functions.Python_Print_Function;
	using Python_Str_Function = com.eagle.programmar.Python.Functions.Python_Str_Function;
	using Python_EndsWith_Method = com.eagle.programmar.Python.Methods.Python_EndsWith_Method;
	using Python_Find_Method = com.eagle.programmar.Python.Methods.Python_Find_Method;
	using Python_StartsWith_Method = com.eagle.programmar.Python.Methods.Python_StartsWith_Method;
	using Python_Strip_Method = com.eagle.programmar.Python.Methods.Python_Strip_Method;
	using Python_BreakStatement = com.eagle.programmar.Python.Statements.Python_BreakStatement;
	using Python_ExpressionStatement = com.eagle.programmar.Python.Statements.Python_ExpressionStatement;
	using Python_ForStatement = com.eagle.programmar.Python.Statements.Python_ForStatement;
	using Python_Function = com.eagle.programmar.Python.Statements.Python_Function;
	using Python_GlobalStatement = com.eagle.programmar.Python.Statements.Python_GlobalStatement;
	using Python_IfStatement = com.eagle.programmar.Python.Statements.Python_IfStatement;
	using Python_MatchStatement = com.eagle.programmar.Python.Statements.Python_MatchStatement;
	using Python_QuitStatement = com.eagle.programmar.Python.Statements.Python_QuitStatement;
	using Python_ReturnStatement = com.eagle.programmar.Python.Statements.Python_ReturnStatement;
	using Python_StatementBlock = com.eagle.programmar.Python.Statements.Python_StatementBlock;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_SameLineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
	using Python_WhileStatement = com.eagle.programmar.Python.Statements.Python_WhileStatement;
	using Python_HexNumber = com.eagle.programmar.Python.Terminals.Python_HexNumber;
	using Python_Literal = com.eagle.programmar.Python.Terminals.Python_Literal;
	using Python_Number = com.eagle.programmar.Python.Terminals.Python_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TerminalToken = com.eagle.tokens.TerminalToken;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;

	public class Python_Generator : EagleGenerator<Python_ComplexStatement, Python_Expression, Python_Variable, Python_Type>
	{
		public static string NAME = "Python";
		public static string SUFFIX = ".py";

		private Python_Program _program;

		// Python requires functions to be declared (visible) before usage.
		// Also, you cannot access variables inside another function.
		// So, we split everything up into three groups and collect them separately.
		// At completion, we combine them back into a single program
		// See getTransformedProgram() for the combining logic.
		// See addStatement() for the logic splitting things up into three parts
		private List<Python_ComplexStatement> _globalData = new List<Python_ComplexStatement>();
		private List<Python_ComplexStatement> _allFunctions = new List<Python_ComplexStatement>();
		private List<Python_ComplexStatement> _mainLogic = new List<Python_ComplexStatement>();

		public Python_Generator(ParserManager parser, string mainName) : base(parser)
		{
			_program = new Python3_Program();
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
			return "Main";
		}

		public override void addMainArgs()
		{
			addMethodParameter(null, "args");
		}

		public override void addCallToMain()
		{
			Python_Variable mainVar = newVariable(mainName());
			List<Python_Expression> args = new List<Python_Expression>();
			Python_Expression none = Python_BuiltIn.generateBuiltIn(BuiltInEnum.NULL, null);
			args.Add(none);
			Python_Expression mainExpr = Python_Function_Call.generateInvocation(mainVar, args, null);
			Python_ComplexStatement mainStmt = Python_ExpressionStatement.newExpressionStatement(mainExpr, null);
			addStatement(mainStmt, null);
		}

		public override AbstractLanguage TransfomedProgram
		{
			get
			{
				_program.entries = new TokenList<Python_ComplexStatement>();
				_program.entries.setPresent(true);
				foreach (Python_ComplexStatement stmt1 in _globalData)
				{
					_program.entries.addToken(stmt1);
				}
				foreach (Python_ComplexStatement stmt2 in _allFunctions)
				{
					_program.entries.addToken(stmt2);
				}
				foreach (Python_ComplexStatement stmt3 in _mainLogic)
				{
					_program.entries.addToken(stmt3);
				}
				return _program;
			}
		}

		public static Python_Expression wrapExpression(AbstractToken token)
		{
			if (token == null)
			{
				return null;
			}
			Python_Expression wrapper = new Python_Expression();
			wrapper.setWhich(token);
			return wrapper;
		}

		public static Python_ComplexStatement wrapStatement(AbstractToken token)
		{
			if (token == null)
			{
				return null;
			}
			Python_Statement simple = new Python_Statement();
			simple.setWhich(token);
			Python_StatementBlock.Python_SameLineStatement sameLine = new Python_StatementBlock.Python_SameLineStatement();
			sameLine.statements = new SeparatedList<Python_Statement, PunctuationSemicolon>();
			sameLine.statements.addPrimaryElement(simple);
			Python_ComplexStatement wrapper = new Python_ComplexStatement();
			wrapper.statementOrComment = new Python_StatementOrComment();
			wrapper.statementOrComment.setWhich(sameLine);
			return wrapper;
		}

		public override Python_Type transformType(TypeEnum type, string typeName, AbstractToken source)
		{
			return Python_Type.transformType(type, typeName, source);
		}

		// ================== Main program and class ==================

		private Python_Function _currentFunction = null;

		public override void addMethod(Python_Type returnType, string name, AbstractToken source)
		{
			_currentFunction = Python_Function.newPythonFunction(name);
			_allFunctions.Add(wrapStatement(_currentFunction));
		}

		public override void addMethodParameter(Python_Type type, string name)
		{
			_currentFunction.addFunctionParameter(type, name);
		}

		public override void doneMethod()
		{
			_currentFunction = null;
		}

		public override void addStatement(Python_ComplexStatement stmt, AbstractToken source)
		{
			if (stmt == null)
			{
				return;
			}

			if (_currentFunction != null)
			{
				// Save everything inside the function, both data and logic
				Python_StatementBlock.Python_MultilineStatement multi = (Python_StatementBlock.Python_MultilineStatement) _currentFunction.header.defBody.getWhich();
				multi.statements.addToken(stmt);
				return;
			}

			// Cannot put data into the 'main' method when it was declared in a global area
			AbstractToken which = stmt.statementOrComment.getWhich();
			if (which is Python_StatementBlock.Python_SameLineStatement)
			{
				Python_StatementBlock.Python_SameLineStatement same = (Python_StatementBlock.Python_SameLineStatement) which;
				if (same.statements.getPrimaryCount() == 1)
				{
					Python_Statement stmt1 = same.statements.first();
					if (stmt1.getWhich() is Python_Data)
					{
						_globalData.Add(stmt);
						return;
					}
				}
			}

			// Must be global logic (i.e., "main")
			_mainLogic.Add(stmt);
		}

		public override void addComment(string comment, AbstractToken source)
		{
			throw new Exception("Need to implement");
		}

		// ================ Statements ================

		public override Python_ComplexStatement newBlockStatement(List<Python_ComplexStatement> statements, AbstractToken source)
		{
			Python_StatementBlock block = new Python_StatementBlock();
			return block.addStatements(statements);
		}

		public override Python_ComplexStatement newBreakStatement(AbstractToken source)
		{
			return Python_BreakStatement.generateBreak(source);
		}

		public override Python_ComplexStatement newDataDeclaration(bool isStatic, string name, Python_Expression size, Python_Type type, Python_Expression initial, AbstractToken source)
		{
			return wrapStatement(Python_Data.newDataDeclaration(name, size, type, initial, source));
		}

		public override Python_ComplexStatement newDoUntilStatement1(Python_Expression condition, Python_ComplexStatement action, AbstractToken source)
		{
			return Python_WhileStatement.generateDoUntilOne(condition, action, source);
		}

		public override Python_ComplexStatement newDoUntilStatement(Python_Expression condition, List<Python_ComplexStatement> actions, AbstractToken source)
		{
			return Python_WhileStatement.generateDoUntilMany(condition, actions, source);
		}

		public override Python_ComplexStatement newExitStatement(Python_Expression code, AbstractToken source)
		{
			return Python_QuitStatement.newQuitStatement(code, source);
		}

		public override Python_ComplexStatement newExpressionStatement(Python_Expression expr, AbstractToken source)
		{
			return Python_ExpressionStatement.newExpressionStatement(expr, source);
		}

		public override Python_ComplexStatement newGlobalVariable(string variableName, AbstractToken source)
		{
			if (_currentFunction == null)
			{
				return null; // Don't add 'global' variables in Python at the top level
			}
			return Python_GlobalStatement.generateGlobal(variableName, source);
		}

		public override Python_ComplexStatement newIfStatement1(Python_Expression condition, Python_ComplexStatement ifTrue, Python_ComplexStatement ifFalse, AbstractToken source)
		{
			return Python_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
		}

		public override Python_ComplexStatement newIfStatement(Python_Expression condition, List<Python_ComplexStatement> ifTrue, List<Python_ComplexStatement> ifFalse, AbstractToken source)
		{
			return Python_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
		}

		public override Python_ComplexStatement newForLoopStatement1(Python_Expression init, Python_Expression term, Python_Expression incr, Python_ComplexStatement action, AbstractToken source)
		{
			return Python_ForStatement.generateForLoopOne(init, term, incr, action, source);
		}

		public override Python_ComplexStatement newForLoopStatement(Python_Expression init, Python_Expression term, Python_Expression incr, List<Python_ComplexStatement> actions, AbstractToken source)
		{
			return Python_ForStatement.generateForLoopMany(init, term, incr, actions, source);
		}

		public override Python_ComplexStatement newForRangeStatement1(Python_Variable var, TypeEnum type, Python_Expression first, RelationalEnum relOp, Python_Expression last, Python_Expression step, Python_ComplexStatement action, AbstractToken source)
		{
			return Python_ForStatement.generateForRangeOne(var, first, relOp, last, step, action, source);
		}

		public override Python_ComplexStatement newForRangeStatement(Python_Variable var, TypeEnum type, Python_Expression first, RelationalEnum relOp, Python_Expression last, Python_Expression step, List<Python_ComplexStatement> actions, AbstractToken source)
		{
			return Python_ForStatement.generateForRangeMany(var, first, relOp, last, step, actions, source);
		}

		public override Python_Expression newPrintFunction(Python_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			return Python_Print_Function.generatePrintFunc(line, type, newLine, source);
		}

		public override Python_ComplexStatement newPrintStatement(Python_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			Python_Expression prtExpr = newPrintFunction(line, type, newLine, toErr, source);
			return newExpressionStatement(prtExpr, source);
		}

		public override Python_ComplexStatement newReturnStatement(Python_Expression ret, AbstractToken source)
		{
			return Python_ReturnStatement.generateReturn(ret, source);
		}

		public override Python_ComplexStatement newSwitchStatement(Python_Expression expr, List<Python_Expression> values, List<List<Python_ComplexStatement>> cases, List<Python_ComplexStatement> defaultCase, AbstractToken source)
		{
			return Python_MatchStatement.generateMatch(expr, values, cases, defaultCase, source);
		}

		public override Python_ComplexStatement newWhileStatement1(Python_Expression condition, Python_ComplexStatement action, AbstractToken source)
		{
			return Python_WhileStatement.generateWhileOne(condition, action, source);
		}

		public override Python_ComplexStatement newWhileStatement(Python_Expression condition, List<Python_ComplexStatement> actions, AbstractToken source)
		{
			return Python_WhileStatement.generateWhileMany(condition, actions, source);
		}

		// ================ Expressions ================

		public override Python_Expression newAdditiveExpression(Oper2Types types, Python_Expression left, AdditiveEnum oper, Python_Expression right, AbstractToken source)
		{
			return Python_Additive_Expression.generateAdditive(types, left, oper, right, source);
		}

		public override Python_Expression newAppendExpression(Oper2Types types, Python_Expression left, Python_Expression right, AbstractToken source)
		{
			return Python_Additive_Expression.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
		}

		public override Python_Expression newAssignmentExpression(string name, SubscriptEnum offset, Python_Expression subscript, AssignmentEnum oper, Python_Expression expression, AbstractToken source)
		{
			Python_Variable var = Python_Variable.newVariable(name);
			return Python_Assignment_Expression.generateAssignment(var, subscript, oper, expression, source);
		}

		public override AbstractExpression newHashAssignment(string name, Python_Expression subscript, Python_Expression expression, AbstractToken source)
		{
			return newAssignmentExpression(name, SubscriptEnum.FIRST_IS_ZERO, subscript, AssignmentEnum.EQUALS, expression, source);
		}

		public override Python_Expression newPostIncrementExpression(string name, SubscriptEnum offset, Python_Expression subscript, IncrementEnum incr, AbstractToken source)
		{
			Python_Variable var = Python_Variable.newVariable(name);
			Python_Expression one = newNumberExpression("1", null);
			AssignmentEnum oper;
			switch (incr)
			{
			case INCREMENT:
				oper = AssignmentEnum.PLUS_EQUALS;
				break;
			case DECREMENT:
				oper = AssignmentEnum.MINUS_EQUALS;
				break;
			default:
				throw new Exception("Unexpected increment: " + incr);
			}
			return Python_Assignment_Expression.generateAssignment(var, subscript, oper, one, source);
		}

		public override Python_Expression newPreIncrementExpression(string name, SubscriptEnum offset, Python_Expression subscript, IncrementEnum oper, AbstractToken source)
		{
			// ++i and i++ are really the same in Python. Both map to i += 1
			return newPostIncrementExpression(name, offset, subscript, oper, source);
		}

		public override Python_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
		{
			return Python_BuiltIn.generateBuiltIn(builtin, source);
		}

		public override Python_Expression newExponentExpression(Python_Expression left, Python_Expression right, AbstractToken source)
		{
			return Python_Power_Expression.generateExpression(left, right, source);
		}

		public override Python_Expression newAbsFunction(Python_Expression expr, AbstractToken source)
		{
			return Python_Abs_Function.generateAbsFunc(expr, source);
		}

		public override Python_Expression newLiteralExpression(string literal, AbstractToken source)
		{
			return Python_Literals.generateLiteralsExpression(literal, source);
		}

		public override Python_Expression newLogicalAndExpression(Python_Expression left, Python_Expression right, AbstractToken source)
		{
			return Python_Logical_And_Expression.generateLogicalAnd(left, right, source);
		}

		public override Python_Expression newLogicalOrExpression(Python_Expression left, LogicalOrEnum oper, Python_Expression right, AbstractToken source)
		{
			return Python_Logical_Or_Expression.generateLogicalOr(left, oper, right, source);
		}

		public override AbstractExpression newBitwiseExpression(Python_Expression left, BitwiseEnum oper, Python_Expression right, AbstractToken source)
		{
			return Python_Bitwise_Expression.generateBitwise(left, oper, right, source);
		}

		public override AbstractExpression newBitwiseNotExpression(Python_Expression expr, AbstractToken source)
		{
			return Python_Bitwise_Not_Expression.generateBitwiseNot(expr, source);
		}

		public override Python_Expression newMultiplicativeExpression(Python_Expression left, MultiplicativeEnum oper, Python_Expression right, AbstractToken source)
		{
			return Python_Multiplicative_Expression.generateMultiplicative(left, oper, right, source);
		}

		public override Python_Expression newNegativeExpression(NegativeEnum sign, Python_Expression expr, AbstractToken source)
		{
			return Python_Negative_Expression.generateNegative(sign, expr, source);
		}

		public override Python_Expression newTruncateExpression(Python_Expression expr, AbstractToken source)
		{
			return Python_Int_Function.generateInteger(expr, source);
		}

		public override Python_Expression newLogicalNotExpression(Python_Expression expr, AbstractToken source)
		{
			AbstractToken which = expr.getWhich();
			if (which is TerminalToken || which is Python_Parenthesized_Expression)
			{
				return Python_Logical_Not_Expression.generateLogicalNot(expr, source);
			}

			Python_Expression parens = Python_Parenthesized_Expression.generateParentheses(expr, source);
			return Python_Logical_Not_Expression.generateLogicalNot(parens, source);
		}

		public override Python_Expression newLogicalExpression(bool @bool, AbstractToken source)
		{
			return Python_BuiltIn.generateBuiltIn((@bool ? BuiltInEnum.TRUE : BuiltInEnum.FALSE), source);
		}

		public override Python_Expression newNumberExpression(string number, AbstractToken source)
		{
			return Python_Number.generateNumberExpression(number, source);
		}

		public override Python_Expression newParenthesizedExpression(Python_Expression expr, AbstractToken source)
		{

// ====================================================================================================
// End of the allowed output for the Free Edition of Java to C# Converter.

// To buy a Premium Edition license, visit our website:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

namespace com.eagle.programmar.CSharp
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using ParserManager = com.eagle.parsers.ParserManager;
	using CSharp_ClassElement = com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
	using CSharp_AdditiveExpression = com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
	using CSharp_AssignmentExpression = com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
	using CSharp_BitwiseExpression = com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseExpression;
	using CSharp_BitwiseNotExpression = com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseNotExpression;
	using CSharp_BuiltIn = com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
	using CSharp_CastExpression = com.eagle.programmar.CSharp.Expressions.CSharp_CastExpression;
	using CSharp_ClassCreationExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression;
	using CSharp_ClassCreationWithInitializers = com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithInitializers;
	using CSharp_LogicalAndExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LogicalAndExpression;
	using CSharp_LogicalNotExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
	using CSharp_LogicalOrExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LogicalOrExpression;
	using CSharp_MethodInvocation = com.eagle.programmar.CSharp.Expressions.CSharp_MethodInvocation;
	using CSharp_MultiplicativeExpression = com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression;
	using CSharp_NegativeExpression = com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression;
	using CSharp_ParenthesizedExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
	using CSharp_PostIncrementExpression = com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression;
	using CSharp_PreIncrementExpression = com.eagle.programmar.CSharp.Expressions.CSharp_PreIncrementExpression;
	using CSharp_RelationalExpression = com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
	using CSharp_ShiftExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ShiftExpression;
	using CSharp_VariableExpression = com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
	using CSharp_MathAbsFunc = com.eagle.programmar.CSharp.Functions.CSharp_MathAbsFunc;
	using CSharp_MathPowFunc = com.eagle.programmar.CSharp.Functions.CSharp_MathPowFunc;
	using CSharp_PrintFunction = com.eagle.programmar.CSharp.Functions.CSharp_PrintFunction;
	using CSharp_StringFormatFunc = com.eagle.programmar.CSharp.Functions.CSharp_StringFormatFunc;
	using CSharp_EndsWithMethod = com.eagle.programmar.CSharp.Methods.CSharp_EndsWithMethod;
	using CSharp_IndexOfMethod = com.eagle.programmar.CSharp.Methods.CSharp_IndexOfMethod;
	using CSharp_LengthMethod = com.eagle.programmar.CSharp.Methods.CSharp_LengthMethod;
	using CSharp_StartsWithMethod = com.eagle.programmar.CSharp.Methods.CSharp_StartsWithMethod;
	using CSharp_SubstringMethod = com.eagle.programmar.CSharp.Methods.CSharp_SubstringMethod;
	using CSharp_ToStringMethod = com.eagle.programmar.CSharp.Methods.CSharp_ToStringMethod;
	using CSharp_TrimMethod = com.eagle.programmar.CSharp.Methods.CSharp_TrimMethod;
	using CSharp_BreakStatement = com.eagle.programmar.CSharp.Statements.CSharp_BreakStatement;
	using CSharp_DoWhileStatement = com.eagle.programmar.CSharp.Statements.CSharp_DoWhileStatement;
	using CSharp_ExitStatement = com.eagle.programmar.CSharp.Statements.CSharp_ExitStatement;
	using CSharp_ExpressionStatement = com.eagle.programmar.CSharp.Statements.CSharp_ExpressionStatement;
	using CSharp_ForStatement = com.eagle.programmar.CSharp.Statements.CSharp_ForStatement;
	using CSharp_IfStatement = com.eagle.programmar.CSharp.Statements.CSharp_IfStatement;
	using CSharp_ReturnStatement = com.eagle.programmar.CSharp.Statements.CSharp_ReturnStatement;
	using CSharp_StatementBlock = com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
	using CSharp_SwitchStatement = com.eagle.programmar.CSharp.Statements.CSharp_SwitchStatement;
	using CSharp_WhileStatement = com.eagle.programmar.CSharp.Statements.CSharp_WhileStatement;
	using CSharp_Character_Literal = com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_HexNumber = com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
	using CSharp_Literal = com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
	using CSharp_Number = com.eagle.programmar.CSharp.Terminals.CSharp_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalToken = com.eagle.tokens.TerminalToken;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using EagleGenerator = com.eagle.transform.EagleGenerator;

	public class CSharp_Generator : EagleGenerator<CSharp_Statement, CSharp_Expression, CSharp_Variable, CSharp_Type>
	{
		public static string NAME = "C#";
		public static string SUFFIX = ".cs";

		private CSharp_Program _program;
		private string _className;

		public CSharp_Generator(ParserManager parser, string className) : base(parser)
		{
			_program = new CSharp_Program();
			_className = className;
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
			CSharp_Type paramType = transformType(TypeEnum.ARRAY, null, null);
			addMethodParameter(paramType, "args");
		}

		public override void addCallToMain()
		{
			// Don't ever need this in C#
		}

		public override AbstractLanguage TransfomedProgram
		{
			get
			{
				return _program;
			}
		}

		public static CSharp_Expression wrapExpression(AbstractToken token)
		{
			if (token == null)
			{
				return null;
			}
			CSharp_Expression wrapper = new CSharp_Expression();
			wrapper.setWhich(token);
			return wrapper;
		}

		public static CSharp_Statement wrapStatement(AbstractToken token)
		{
			if (token == null)
			{
				return null;
			}
			CSharp_Statement wrapper = new CSharp_Statement();
			wrapper.setWhich(token);
			return wrapper;
		}

		public override CSharp_Type transformType(TypeEnum type, string typeName, AbstractToken source)
		{
			return CSharp_Type.transformType(type, typeName, source);
		}

		// ================== Main program and class ==================

		private CSharp_Class _currentClass = null;
		private CSharp_Method _currentMethod = null;
		private CSharp_Method _previousMethod = null;

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
				_currentMethod.newCSharpMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC, mainType, "Main");
				_currentClass.addMethod(_currentMethod);

				CSharp_Type paramType = CSharp_Type.transformTypeArray(TypeEnum.STRING);
				_currentMethod.addMethodParameter(paramType, "args");
			}
		}

		public override void addMethod(CSharp_Type returnType, string name, AbstractToken source)
		{
			checkClass();

			_previousMethod = _currentMethod;
			_currentMethod = new CSharp_Method();
			_currentMethod.newCSharpMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC, returnType, name);
			_currentMethod.setTransformationSource(source);
			_currentClass.addMethod(_currentMethod);
		}

		public override void addMethodParameter(CSharp_Type type, string name)
		{
			_currentMethod.addMethodParameter(type, name);
		}

		public override void doneMethod()
		{
			_currentMethod = _previousMethod;
		}

		public override void addStatement(CSharp_Statement stmt, AbstractToken source)
		{
			if (stmt == null)
			{
				return;
			}
			checkClass();

			// Cannot put data into the 'main' method when it was declared in a global area
			if (stmt.getWhich() is CSharp_Data)
			{
				bool saveInClass = false;
				if (_currentMethod == null)
				{
					saveInClass = true;
				}
				else if (_currentMethod.id.getValue().Equals("Main"))
				{
					saveInClass = true;
				}

				if (saveInClass)
				{
					CSharp_Data data = (CSharp_Data) stmt.getWhich();
					data.addModifier("static");

					// Put it in top-level class, not the 'main' method
					CSharp_ClassElement element = new CSharp_ClassElement();
					element.setWhich(stmt);
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

		public override void addComment(string comment, AbstractToken source)
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

		public override CSharp_Statement newBlockStatement(List<CSharp_Statement> statements, AbstractToken source)
		{
			return CSharp_StatementBlock.generateBlock(statements, source);
		}

		public override CSharp_Statement newBreakStatement(AbstractToken source)
		{
			return CSharp_BreakStatement.generateBreak(source);
		}

		public override CSharp_Statement newDataDeclaration(bool isStatic, string name, CSharp_Expression size, CSharp_Type type, CSharp_Expression initial, AbstractToken source)
		{
			return wrapStatement(CSharp_Data.newDataDeclaration(isStatic, name, size, type, initial, source));
		}

		public override CSharp_Statement newDoUntilStatement1(CSharp_Expression condition, CSharp_Statement action, AbstractToken source)
		{
			return CSharp_DoWhileStatement.generateDoUntilOne(condition, action, source);
		}

		public override CSharp_Statement newDoUntilStatement(CSharp_Expression condition, List<CSharp_Statement> actions, AbstractToken source)
		{
			return CSharp_DoWhileStatement.generateDoUntilMany(condition, actions, source);
		}

		public override CSharp_Statement newExpressionStatement(CSharp_Expression expr, AbstractToken source)
		{
			return wrapStatement(CSharp_ExpressionStatement.newExpressionStatement(expr, source));
		}

		public override CSharp_Statement newExitStatement(CSharp_Expression code, AbstractToken source)
		{
			return wrapStatement(CSharp_ExitStatement.newExitStatement(code, source));
		}

		public override CSharp_Statement newGlobalVariable(string variableName, AbstractToken source)
		{
			return null; // Don't need to declare variables as 'global'
		}

		public override CSharp_Statement newForLoopStatement1(CSharp_Expression init, CSharp_Expression term, CSharp_Expression incr, CSharp_Statement action, AbstractToken source)
		{
			return CSharp_ForStatement.generateForLoopOne(init, term, incr, action, source);
		}

		public override CSharp_Statement newForLoopStatement(CSharp_Expression init, CSharp_Expression term, CSharp_Expression incr, List<CSharp_Statement> actions, AbstractToken source)
		{
			return CSharp_ForStatement.generateForLoopMany(init, term, incr, actions, source);
		}

		public override CSharp_Statement newForRangeStatement1(CSharp_Variable var, TypeEnum type, CSharp_Expression first, RelationalEnum relOper, CSharp_Expression last, CSharp_Expression step, CSharp_Statement action, AbstractToken source)
		{
			return CSharp_ForStatement.generateForRangeOne(var, type, first, relOper, last, step, action, source);
		}

		public override CSharp_Statement newForRangeStatement(CSharp_Variable var, TypeEnum type, CSharp_Expression first, RelationalEnum relOper, CSharp_Expression last, CSharp_Expression step, List<CSharp_Statement> actions, AbstractToken source)
		{
			return CSharp_ForStatement.generateForRangeMany(var, type, first, relOper, last, step, actions, source);
		}

		public override CSharp_Statement newIfStatement1(CSharp_Expression condition, CSharp_Statement ifTrue, CSharp_Statement ifFalse, AbstractToken source)
		{
			return CSharp_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
		}

		public override CSharp_Statement newIfStatement(CSharp_Expression condition, List<CSharp_Statement> ifTrue, List<CSharp_Statement> ifFalse, AbstractToken source)
		{
			return CSharp_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
		}

		public override CSharp_Expression newPrintFunction(CSharp_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			return CSharp_PrintFunction.generatePrintFunc(line, newLine, toErr, source);
		}

		public override CSharp_Statement newPrintStatement(CSharp_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			CSharp_Expression prtExpr = newPrintFunction(line, type, newLine, toErr, source);
			return newExpressionStatement(prtExpr, source);
		}

		public override CSharp_Statement newReturnStatement(CSharp_Expression ret, AbstractToken source)
		{
			return CSharp_ReturnStatement.generateReturn(ret, source);
		}

		public override CSharp_Statement newSwitchStatement(CSharp_Expression expr, List<CSharp_Expression> values, List<List<CSharp_Statement>> cases, List<CSharp_Statement> defaultCase, AbstractToken source)
		{
			return CSharp_SwitchStatement.generateSwitch(expr, values, cases, defaultCase, source);
		}

		public override CSharp_Statement newWhileStatement1(CSharp_Expression condition, CSharp_Statement action, AbstractToken source)
		{
			return CSharp_WhileStatement.generateWhileOne(condition, action, source);
		}

		public override CSharp_Statement newWhileStatement(CSharp_Expression condition, List<CSharp_Statement> actions, AbstractToken source)
		{
			return CSharp_WhileStatement.generateWhileMany(condition, actions, source);
		}

		// ================ Expressions ================

		public override CSharp_Expression newAdditiveExpression(Oper2Types types, CSharp_Expression left, AdditiveEnum oper, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_AdditiveExpression.generateAdditive(types, left, oper, right, source);
		}

		public override CSharp_Expression newAppendExpression(Oper2Types types, CSharp_Expression left, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_AdditiveExpression.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
		}

		public override CSharp_Expression newAssignmentExpression(string name, SubscriptEnum offset, CSharp_Expression subscript, AssignmentEnum oper, CSharp_Expression expression, AbstractToken source)
		{
			CSharp_Variable var = CSharp_Variable.newVariable(name);
			return CSharp_AssignmentExpression.generateAssignment(var, subscript, oper, expression, source);
		}

		public override AbstractExpression newHashAssignment(string name, CSharp_Expression subscript, CSharp_Expression expression, AbstractToken source)
		{
			return newAssignmentExpression(name, SubscriptEnum.FIRST_IS_ZERO, subscript, AssignmentEnum.EQUALS, expression, source);
		}

		public override CSharp_Expression newPostIncrementExpression(string name, SubscriptEnum offset, CSharp_Expression subscript, IncrementEnum oper, AbstractToken source)
		{
			CSharp_Variable var = CSharp_Variable.newVariable(name);
			return CSharp_PostIncrementExpression.generateIncrement(var, oper, source);
		}

		public override CSharp_Expression newPreIncrementExpression(string name, SubscriptEnum offset, CSharp_Expression subscript, IncrementEnum oper, AbstractToken source)
		{
			CSharp_Variable var = CSharp_Variable.newVariable(name);
			return CSharp_PreIncrementExpression.generateIncrement(var, oper, source);
		}

		public override CSharp_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
		{
			return CSharp_BuiltIn.generateBuiltIn(builtin, source);
		}

		public override CSharp_Expression newExponentExpression(CSharp_Expression left, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_MathPowFunc.generateExpression(left, right, source);
		}

		public override CSharp_Expression newLiteralExpression(string literal, AbstractToken source)
		{
			return CSharp_Literal.generateLiteralExpression(literal, source);
		}

		public override CSharp_Expression newLogicalAndExpression(CSharp_Expression left, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_LogicalAndExpression.generateLogicalAnd(left, right, source);
		}

		public override CSharp_Expression newLogicalOrExpression(CSharp_Expression left, LogicalOrEnum oper, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_LogicalOrExpression.generateLogicalOr(left, oper, right, source);
		}

		public override AbstractExpression newBitwiseExpression(CSharp_Expression left, BitwiseEnum oper, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_BitwiseExpression.generateBitwise(left, oper, right, source);
		}

		public override AbstractExpression newBitwiseNotExpression(CSharp_Expression expr, AbstractToken source)
		{
			return CSharp_BitwiseNotExpression.generateBitwiseNot(expr, source);
		}

		public override CSharp_Expression newMultiplicativeExpression(CSharp_Expression left, MultiplicativeEnum oper, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_MultiplicativeExpression.generateMultiplicative(left, oper, right, source);
		}

		public override CSharp_Expression newNegativeExpression(NegativeEnum sign, CSharp_Expression expr, AbstractToken source)
		{
			return CSharp_NegativeExpression.generateNegative(sign, expr, source);
		}

		public override CSharp_Expression newTruncateExpression(CSharp_Expression expr, AbstractToken source)
		{
			CSharp_Type type = CSharp_Type.newPrimitiveType("int");
			return CSharp_CastExpression.newCastExpression(type, expr, source);
		}

		public override CSharp_Expression newLogicalNotExpression(CSharp_Expression expr, AbstractToken source)
		{
			AbstractToken which = expr.getWhich();
			if (which is TerminalToken || which is CSharp_ParenthesizedExpression)
			{
				return CSharp_LogicalNotExpression.generateLogicalNot(expr, source);
			}

			CSharp_Expression parens = CSharp_ParenthesizedExpression.generateParentheses(expr, source);
			return CSharp_LogicalNotExpression.generateLogicalNot(parens, source);
		}

		public override CSharp_Expression newLogicalExpression(bool @bool, AbstractToken source)
		{
			return CSharp_BuiltIn.generateBuiltIn((@bool ? BuiltInEnum.TRUE : BuiltInEnum.FALSE), source);
		}

		public override CSharp_Expression newNumberExpression(string number, AbstractToken source)
		{
			return CSharp_Number.generateNumberExpression(number, source);
		}

		public override CSharp_Expression newParenthesizedExpression(CSharp_Expression expr, AbstractToken source)
		{
			return CSharp_ParenthesizedExpression.generateParentheses(expr, source);
		}

		public override CSharp_Expression newRelationalExpression(Oper2Types types, CSharp_Expression left, RelationalEnum relOp, CSharp_Expression right, AbstractToken source)
		{
			return CSharp_RelationalExpression.generateRelational(types, left, relOp, right, source);
		}

		public override CSharp_Expression newShiftExpression(CSharp_Expression left, ShiftEnum shift, CSharp_Expression right, AbstractToken source)
		{

// ====================================================================================================
// End of the allowed output for the Free Edition of Java to C# Converter.

// To buy a Premium Edition license, visit our website:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

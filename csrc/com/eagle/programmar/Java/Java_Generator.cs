// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2024

namespace com.eagle.programmar.Java
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using ParserManager = com.eagle.parsers.ParserManager;
	using Java_ClassElement = com.eagle.programmar.Java.Java_Class.Java_ClassElement;
	using Java_MethodImplementation = com.eagle.programmar.Java.Java_Method.Java_MethodImplementation;
	using Java_MethodType = com.eagle.programmar.Java.Java_Method.Java_MethodType;
	using Java_AdditiveExpression = com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
	using Java_AssignmentExpression = com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
	using Java_BitwiseExpression = com.eagle.programmar.Java.Expressions.Java_BitwiseExpression;
	using Java_BitwiseNotExpression = com.eagle.programmar.Java.Expressions.Java_BitwiseNotExpression;
	using Java_BuiltIn = com.eagle.programmar.Java.Expressions.Java_BuiltIn;
	using Java_CastExpression = com.eagle.programmar.Java.Expressions.Java_CastExpression;
	using Java_ClassCreationExpression = com.eagle.programmar.Java.Expressions.Java_ClassCreationExpression;
	using Java_ClassCreationWithInitializers = com.eagle.programmar.Java.Expressions.Java_ClassCreationWithInitializers;
	using Java_LogicalAndExpression = com.eagle.programmar.Java.Expressions.Java_LogicalAndExpression;
	using Java_LogicalNotExpression = com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
	using Java_LogicalOrExpression = com.eagle.programmar.Java.Expressions.Java_LogicalOrExpression;
	using Java_MethodInvocation = com.eagle.programmar.Java.Expressions.Java_MethodInvocation;
	using Java_MultiplicativeExpression = com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression;
	using Java_NegativeExpression = com.eagle.programmar.Java.Expressions.Java_NegativeExpression;
	using Java_ParenthesizedExpression = com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
	using Java_PostIncrementExpression = com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression;
	using Java_PreIncrementExpression = com.eagle.programmar.Java.Expressions.Java_PreIncrementExpression;
	using Java_RelationalExpression = com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
	using Java_ShiftExpression = com.eagle.programmar.Java.Expressions.Java_ShiftExpression;
	using Java_VariableExpression = com.eagle.programmar.Java.Expressions.Java_VariableExpression;
	using Java_MathAbsFunc = com.eagle.programmar.Java.Functions.Java_MathAbsFunc;
	using Java_MathPowFunc = com.eagle.programmar.Java.Functions.Java_MathPowFunc;
	using Java_PrintFunction = com.eagle.programmar.Java.Functions.Java_PrintFunction;
	using Java_StringFormatFunc = com.eagle.programmar.Java.Functions.Java_StringFormatFunc;
	using Java_EndsWithMethod = com.eagle.programmar.Java.Methods.Java_EndsWithMethod;
	using Java_IndexOfMethod = com.eagle.programmar.Java.Methods.Java_IndexOfMethod;
	using Java_LengthMethod = com.eagle.programmar.Java.Methods.Java_LengthMethod;
	using Java_StartsWithMethod = com.eagle.programmar.Java.Methods.Java_StartsWithMethod;
	using Java_SubstringMethod = com.eagle.programmar.Java.Methods.Java_SubstringMethod;
	using Java_ToStringMethod = com.eagle.programmar.Java.Methods.Java_ToStringMethod;
	using Java_TrimMethod = com.eagle.programmar.Java.Methods.Java_TrimMethod;
	using Java_BreakStatement = com.eagle.programmar.Java.Statements.Java_BreakStatement;
	using Java_DoWhileStatement = com.eagle.programmar.Java.Statements.Java_DoWhileStatement;
	using Java_ExitStatement = com.eagle.programmar.Java.Statements.Java_ExitStatement;
	using Java_ExpressionStatement = com.eagle.programmar.Java.Statements.Java_ExpressionStatement;
	using Java_ForStatement = com.eagle.programmar.Java.Statements.Java_ForStatement;
	using Java_IfStatement = com.eagle.programmar.Java.Statements.Java_IfStatement;
	using Java_ReturnStatement = com.eagle.programmar.Java.Statements.Java_ReturnStatement;
	using Java_StatementBlock = com.eagle.programmar.Java.Statements.Java_StatementBlock;
	using Java_SwitchStatement = com.eagle.programmar.Java.Statements.Java_SwitchStatement;
	using Java_WhileStatement = com.eagle.programmar.Java.Statements.Java_WhileStatement;
	using Java_Character_Literal = com.eagle.programmar.Java.Terminals.Java_Character_Literal;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_HexNumber = com.eagle.programmar.Java.Terminals.Java_HexNumber;
	using Java_Literal = com.eagle.programmar.Java.Terminals.Java_Literal;
	using Java_Number = com.eagle.programmar.Java.Terminals.Java_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalToken = com.eagle.tokens.TerminalToken;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using EagleGenerator = com.eagle.transform.EagleGenerator;

	public class Java_Generator : EagleGenerator<Java_Statement, Java_Expression, Java_Variable, Java_Type>
	{
		public static string NAME = "Java";
		public static string SUFFIX = ".java";

		private Java_Program _program;
		private string _className;

		public Java_Generator(ParserManager parser, string className) : base(parser)
		{
			_program = new Java_Program();
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
			return "main";
		}

		public override void addMainArgs()
		{
			Java_Type paramType = transformType(TypeEnum.ARRAY, null, null);
			addMethodParameter(paramType, "args");
		}

		public override void addCallToMain()
		{
			// Don't ever need this in Java
		}

		public override AbstractLanguage TransfomedProgram
		{
			get
			{
				return _program;
			}
		}

		public static Java_Expression wrapExpression(AbstractToken token)
		{
			Java_Expression wrapper = new Java_Expression();
			wrapper.setWhich(token);
			return wrapper;
		}

		public static Java_Statement wrapStatement(AbstractToken token)
		{
			if (token == null)
			{
				return null;
			}
			token.setPresent(true);
			Java_Statement wrapper = new Java_Statement();
			wrapper.setWhich(token);
			wrapper.setPresent(true);
			return wrapper;
		}

		public override Java_Type transformType(TypeEnum type, string typeName, AbstractToken source)
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
				_currentMethod.newJavaMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC, mainType, "main");
				_currentClass.addMethod(_currentMethod);

				Java_Type paramType = Java_Type.transformTypeArray(TypeEnum.STRING);
				_currentMethod.addMethodParameter(paramType, "args");
			}
		}

		public override void addMethod(Java_Type returnType, string name, AbstractToken source)
		{
			checkClass();

			_previousMethod = _currentMethod;
			_currentMethod = new Java_Method();
			_currentMethod.newJavaMethod(PrivacyEnum.PUBLIC, StaticEnum.STATIC, returnType, name);
			_currentMethod.setTransformationSource(source);
			_currentClass.addMethod(_currentMethod);
		}

		public override void addMethodParameter(Java_Type type, string name)
		{
			_currentMethod.addMethodParameter(type, name);
		}

		public override void doneMethod()
		{
			_currentMethod = _previousMethod;
		}

		public override void addStatement(Java_Statement stmt, AbstractToken source)
		{
			if (stmt == null)
			{
				return;
			}
			checkClass();

			// Cannot put data into the 'main' method when it was declared in a global area
			if (stmt.getWhich() is Java_Data)
			{
				bool saveInClass = false;
				if (_currentMethod == null)
				{
					saveInClass = true;
				}
				else if (_currentMethod.typeAndName.getWhich() is Java_MethodType)
				{
					Java_MethodType methType = (Java_MethodType) _currentMethod.typeAndName.getWhich();
					if (methType.methodName.getValue().Equals("main"))
					{
						saveInClass = true;
					}
				}

				if (saveInClass)
				{
					Java_Data data = (Java_Data) stmt.getWhich();
					data.addModifier("static");

					// Put it in top-level class, not the 'main' method
					Java_ClassElement element = new Java_ClassElement();
					element.setWhich(stmt);
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

		public override void addComment(string comment, AbstractToken source)
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

		public override Java_Statement newBlockStatement(List<Java_Statement> statements, AbstractToken source)
		{
			return Java_StatementBlock.generateBlock(statements, source);
		}

		public override Java_Statement newBreakStatement(AbstractToken source)
		{
			return Java_BreakStatement.generateBreak(source);
		}

		public override Java_Statement newDataDeclaration(bool isStatic, string name, Java_Expression size, Java_Type type, Java_Expression initial, AbstractToken source)
		{
			return wrapStatement(Java_Data.newDataDeclaration(isStatic, name, size, type, initial, source));
		}

		public override Java_Statement newDoUntilStatement1(Java_Expression condition, Java_Statement action, AbstractToken source)
		{
			return Java_DoWhileStatement.generateDoUntilOne(condition, action, source);
		}

		public override Java_Statement newDoUntilStatement(Java_Expression condition, List<Java_Statement> actions, AbstractToken source)
		{
			return Java_DoWhileStatement.generateDoUntilMany(condition, actions, source);
		}

		public override Java_Statement newExitStatement(Java_Expression code, AbstractToken source)
		{
			return Java_ExitStatement.newExitStatement(code, source);
		}

		public override Java_Statement newExpressionStatement(Java_Expression expr, AbstractToken source)
		{
			return Java_ExpressionStatement.newExpressionStatement(expr, source);
		}

		public override Java_Statement newGlobalVariable(string variableName, AbstractToken source)
		{
			return null; // Don't need to declare variables as 'global'
		}

		public override Java_Statement newIfStatement1(Java_Expression condition, Java_Statement ifTrue, Java_Statement ifFalse, AbstractToken source)
		{
			return Java_IfStatement.generateIfElseOne(condition, ifTrue, ifFalse, source);
		}

		public override Java_Statement newIfStatement(Java_Expression condition, List<Java_Statement> ifTrue, List<Java_Statement> ifFalse, AbstractToken source)
		{
			return Java_IfStatement.generateIfElseMany(condition, ifTrue, ifFalse, source);
		}

		public override Java_Statement newForLoopStatement1(Java_Expression init, Java_Expression term, Java_Expression incr, Java_Statement action, AbstractToken source)
		{
			return Java_ForStatement.generateForLoopOne(init, term, incr, action, source);
		}

		public override Java_Statement newForLoopStatement(Java_Expression init, Java_Expression term, Java_Expression incr, List<Java_Statement> actions, AbstractToken source)
		{
			return Java_ForStatement.generateForLoopMany(init, term, incr, actions, source);
		}

		public override Java_Statement newForRangeStatement1(Java_Variable var, TypeEnum type, Java_Expression first, RelationalEnum relOp, Java_Expression last, Java_Expression step, Java_Statement action, AbstractToken source)
		{
			return Java_ForStatement.generateForRangeOne(var, type, first, relOp, last, step, action, source);
		}

		public override Java_Statement newForRangeStatement(Java_Variable var, TypeEnum type, Java_Expression first, RelationalEnum relOp, Java_Expression last, Java_Expression step, List<Java_Statement> actions, AbstractToken source)
		{
			return Java_ForStatement.generateForRangeMany(var, type, first, relOp, last, step, actions, source);
		}

		public override Java_Expression newPrintFunction(Java_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			return Java_PrintFunction.generatePrintFunc(line, type, newLine, toErr, source);
		}

		public override Java_Statement newPrintStatement(Java_Expression line, TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			Java_Expression prtExpr = newPrintFunction(line, type, newLine, toErr, source);
			return newExpressionStatement(prtExpr, source);
		}

		public override Java_Statement newReturnStatement(Java_Expression ret, AbstractToken source)
		{
			return Java_ReturnStatement.generateReturn(ret, source);
		}

		public override Java_Statement newSwitchStatement(Java_Expression expr, List<Java_Expression> values, List<List<Java_Statement>> cases, List<Java_Statement> defaultCase, AbstractToken source)
		{
			return Java_SwitchStatement.generateSwitch(expr, values, cases, defaultCase, source);
		}

		public override Java_Statement newWhileStatement1(Java_Expression condition, Java_Statement action, AbstractToken source)
		{
			return Java_WhileStatement.generateWhileOne(condition, action, source);
		}

		public override Java_Statement newWhileStatement(Java_Expression condition, List<Java_Statement> actions, AbstractToken source)
		{
			return Java_WhileStatement.generateWhileMany(condition, actions, source);
		}

		// ================ Expressions ================

		public override Java_Expression newAdditiveExpression(Oper2Types types, Java_Expression left, AdditiveEnum oper, Java_Expression right, AbstractToken source)
		{
			return Java_AdditiveExpression.generateAdditive(types, left, oper, right, source);
		}

		public override Java_Expression newAppendExpression(Oper2Types types, Java_Expression left, Java_Expression right, AbstractToken source)
		{
			return Java_AdditiveExpression.generateAdditive(types, left, AdditiveEnum.PLUS, right, source);
		}

		public override Java_Expression newAssignmentExpression(string name, SubscriptEnum offset, Java_Expression subscript, AssignmentEnum oper, Java_Expression expression, AbstractToken source)
		{
			Java_Variable var = Java_Variable.newVariable(name);
			return Java_AssignmentExpression.generateAssignment(var, subscript, oper, expression, source);
		}

		public override AbstractExpression newHashAssignment(string name, Java_Expression subscript, Java_Expression expression, AbstractToken source)
		{
			Java_Variable var = Java_Variable.newVariable(name + ".put");
			List<Java_Expression> args = new List<Java_Expression>();
			args.Add(subscript);
			args.Add(expression);
			return Java_MethodInvocation.generateInvocation(var, args, source);
		}

		public override Java_Expression newPostIncrementExpression(string name, SubscriptEnum offset, Java_Expression subscript, IncrementEnum oper, AbstractToken source)
		{
			Java_Variable var = Java_Variable.newVariable(name);
			return Java_PostIncrementExpression.generateIncrement(var, oper, source);
		}

		public override Java_Expression newPreIncrementExpression(string name, SubscriptEnum offset, Java_Expression subscript, IncrementEnum oper, AbstractToken source)
		{
			Java_Variable var = Java_Variable.newVariable(name);
			return Java_PreIncrementExpression.generateIncrement(var, oper, source);
		}

		public override Java_Expression newBuiltInExpression(BuiltInEnum builtin, AbstractToken source)
		{
			return Java_BuiltIn.generateBuiltIn(builtin, source);
		}

		public override Java_Expression newExponentExpression(Java_Expression left, Java_Expression right, AbstractToken source)
		{
			return Java_MathPowFunc.generatePowFunc(left, right, source);
		}

		public override Java_Expression newAbsFunction(Java_Expression expr, AbstractToken source)
		{
			return Java_MathAbsFunc.generateAbsFunc(expr, source);
		}

		public override Java_Expression newLiteralExpression(string literal, AbstractToken source)
		{
			return Java_Literal.generateLiteralExpression(literal, source);
		}

		public override Java_Expression newLogicalAndExpression(Java_Expression left, Java_Expression right, AbstractToken source)
		{
			return Java_LogicalAndExpression.generateLogicalAnd(left, right, source);
		}

		public override Java_Expression newLogicalOrExpression(Java_Expression left, LogicalOrEnum oper, Java_Expression right, AbstractToken source)
		{
			return Java_LogicalOrExpression.generateLogicalOr(left, oper, right, source);
		}

		public override Java_Expression newBitwiseExpression(Java_Expression left, BitwiseEnum oper, Java_Expression right, AbstractToken source)
		{
			return Java_BitwiseExpression.generateBitwise(left, oper, right, source);
		}

		public override Java_Expression newBitwiseNotExpression(Java_Expression expr, AbstractToken source)
		{
			return Java_BitwiseNotExpression.generateBitwiseNot(expr, source);
		}

		public override Java_Expression newMultiplicativeExpression(Java_Expression left, MultiplicativeEnum oper, Java_Expression right, AbstractToken source)
		{
			return Java_MultiplicativeExpression.generateMultiplicative(left, oper, right, source);
		}

		public override Java_Expression newNegativeExpression(NegativeEnum sign, Java_Expression expr, AbstractToken source)
		{
			return Java_NegativeExpression.generateNegative(sign, expr, source);
		}

		public override Java_Expression newTruncateExpression(Java_Expression expr, AbstractToken source)
		{
			Java_Type type = Java_Type.newPrimitiveType("int");
			return Java_CastExpression.newCastExpression(type, expr, source);
		}

		public override Java_Expression newLogicalNotExpression(Java_Expression expr, AbstractToken source)
		{
			AbstractToken which = expr.getWhich();
			if (which is TerminalToken || which is Java_ParenthesizedExpression)
			{
				return Java_LogicalNotExpression.generateLogicalNot(expr, source);
			}


// ====================================================================================================
// End of the allowed output for the Free Edition of Java to C# Converter.

// To buy a Premium Edition license, visit our website:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

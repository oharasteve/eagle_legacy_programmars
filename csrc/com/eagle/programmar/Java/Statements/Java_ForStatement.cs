// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

namespace com.eagle.programmar.Java.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Java_Annotation = com.eagle.programmar.Java.Java_Annotation;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Label = com.eagle.programmar.Java.Java_Label;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_Syntax = com.eagle.programmar.Java.Java_Syntax;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using Java_AssignmentExpression = com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
	using Java_PostIncrementExpression = com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression;
	using Java_PreIncrementExpression = com.eagle.programmar.Java.Expressions.Java_PreIncrementExpression;
	using Java_RelationalExpression = com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
	using Java_VariableExpression = com.eagle.programmar.Java.Expressions.Java_VariableExpression;
	using Java_Variable_Definition = com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using Java_Number = com.eagle.programmar.Java.Terminals.Java_Number;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using IncrementEnum = com.eagle.transform.EagleGenerator.IncrementEnum;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleScope.EagleScopeInterface, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE Java_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("statements.html#14.14") com.eagle.programmar.Java.Terminals.Java_Keyword FOR = new com.eagle.programmar.Java.Terminals.Java_Keyword("for");
		public @DOC("statements.html#14.14") Java_Keyword FOR = new Java_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE Java_Annotation annotation;
		public @OPT Java_Annotation annotation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT @NOSPACE Java_ForInit initial;
		public @OPT Java_ForInit initial;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationSemicolon semicolon1;
		public @NOSPACE PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Java_Expression terminateCondition;
		public @OPT Java_Expression terminateCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE PunctuationSemicolon semicolon2;
		public @NOSPACE PunctuationSemicolon semicolon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT SeparatedList<com.eagle.programmar.Java.Java_Expression, com.eagle.tokens.punctuation.PunctuationComma> increments;
		public @OPT SeparatedList<Java_Expression, PunctuationComma> increments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @NOSPACE PunctuationRightParen rightParen;
		public @NOSPACE PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT Java_Comment comment;
		public @OPT Java_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.programmar.Java.Java_Statement action;
		public Java_Statement action;

		public static class Java_ForInit extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Java_Keyword FINAL = new com.eagle.programmar.Java.Terminals.Java_Keyword("final");
			public @OPT Java_Keyword FINAL = new Java_Keyword("final");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Java_ForWhat, com.eagle.tokens.punctuation.PunctuationComma> what;
			public SeparatedList<Java_ForWhat, PunctuationComma> what;
		}

		public static class Java_ForWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ForWithType XXforWithType;
			public Java_ForWithType XXforWithType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_ForWithoutType XXforWithoutType;
			public Java_ForWithoutType XXforWithoutType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Java_Expression XXexpr;
			public Java_Expression XXexpr;
		}

		public static class Java_ForWithType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Type varType;
			public Java_Type varType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Symbols.Java_Variable_Definition variable;
			public Java_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_ForTypeInit equalsInit;
			public @OPT Java_ForTypeInit equalsInit;
		}

		public static class Java_ForWithoutType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Symbols.Java_Variable_Definition variable;
			public Java_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Java_ForTypeInit equalsInit;
			public Java_ForTypeInit equalsInit;
		}

		public static class Java_ForTypeInit extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Java_Expression initialExpr;
			public Java_Expression initialExpr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Java.Java_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Java_ForWhat forWhat = initial.what.first();
			if (forWhat.getWhich() is Java_ForWithType)
			{
				Java_ForWithType whatforWith = (Java_ForWithType) forWhat.getWhich();
				EagleValue init = interpreter.getEagleValue(whatforWith.equalsInit.initialExpr);
				interpreter.setSymbol(whatforWith.variable, whatforWith.variable.getValue(), init);
			}
			else if (forWhat.getWhich() is Java_ForWithoutType)
			{
				Java_ForWithoutType whatforWithout = (Java_ForWithoutType) forWhat.getWhich();
				EagleValue init = interpreter.getEagleValue(whatforWithout.equalsInit.initialExpr);
				interpreter.setSymbol(whatforWithout.variable, whatforWithout.variable.getValue(), init);
			}
			else
			{
				throw new Exception("Unexpected for loop construct: " + forWhat.getWhich());
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Java_Expression increment = increments.first();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				bool keepGoing = interpreter.getBoolValue(terminateCondition);
				if (!keepGoing)
				{
					break;
				}

				metric.iterate();
				result = interpreter.tryToInterpret(action);
				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					result = Eagle_Statement_Result.NORMAL;
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
					result = Eagle_Statement_Result.NORMAL;
				}
				else if (result == Eagle_Statement_Result.RETURN)
				{
					break;
				}

				interpreter.tryToInterpret(increment);
			}

			// Have to guess to see if it was backwards
			bool backwards = guessDirection(terminateCondition, increment);

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		private static bool guessDirection(Java_Expression testExpr, Java_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is Java_PostIncrementExpression)
			{
				Java_PostIncrementExpression post = (Java_PostIncrementExpression) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is Java_PreIncrementExpression)
			{
				Java_PreIncrementExpression pre = (Java_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is Java_RelationalExpression)
			{
				Java_RelationalExpression rel = (Java_RelationalExpression) which2;
				string oper = rel.@operator.getValue();
				if (oper.Equals(">") || oper.Equals(">="))
				{
					return true;
				}
			}

			return false; // Just don't know :(
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (this.initial.what.getPrimaryCount() == 1)
			{
				string varName = null;
				Java_Expression forInit = null;
				Java_ForWhat what = this.initial.what.first();
				if (what.getWhich() is Java_ForWithType)
				{
					Java_ForWithType withType = (Java_ForWithType) what.getWhich();
					varName = withType.variable.getValue();
					forInit = withType.equalsInit.initialExpr;
				}
				else if (what.getWhich() is Java_ForWithoutType)
				{
					Java_ForWithoutType withoutType = (Java_ForWithoutType) what.getWhich();
					varName = withoutType.variable.getValue();
					forInit = withoutType.equalsInit.initialExpr;
				}

				if (forInit != null)
				{
					AbstractExpression fromExpr = transformer.transformExpression(generator, forInit);
					AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, null);

					if (this.increments.getPrimaryCount() == 1)
					{
						AbstractExpression termExpr = transformer.transformExpression(generator, terminateCondition);
						AbstractExpression delta = transformer.transformExpression(generator, increments.first());
						AbstractStatement newAction = transformer.transformStatement1(generator, this.action);
						return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
					}
				}
			}

			throw new Exception("Unable to handle for loop: " + this);
		}

		public static Java_Statement generateForLoopOne(Java_Expression initExpression, Java_Expression condExpression, Java_Expression incrExpression, Java_Statement act, AbstractToken source)
		{
			Java_ForStatement forStmt = new Java_ForStatement();
			SeparatedList<Java_ForWhat, PunctuationComma> initializer = new SeparatedList<Java_ForWhat, PunctuationComma>();
			Java_ForWhat forWhat = new Java_ForWhat();
			forWhat.setPresent(true);
			forWhat.setWhich(initExpression);
			initializer.addPrimaryElement(forWhat);

			SeparatedList<Java_Expression, PunctuationComma> loopIncrements = new SeparatedList<Java_Expression, PunctuationComma>();
			loopIncrements.addPrimaryElement(incrExpression);

			forStmt.leftParen = new PunctuationLeftParen();
			forStmt.initial = new Java_ForInit();
			forStmt.initial.setPresent(true);
			forStmt.initial.what = new SeparatedList<Java_ForWhat, PunctuationComma>();
			Java_ForWhat what = new Java_ForWhat();
			what.setWhich(initExpression);
			forStmt.initial.what.addPrimaryElement(what);

			forStmt.semicolon1 = new PunctuationSemicolon();
			forStmt.terminateCondition = condExpression;
			forStmt.terminateCondition.setPresent(true);
			forStmt.semicolon2 = new PunctuationSemicolon();
			forStmt.increments = loopIncrements;
			forStmt.rightParen = new PunctuationRightParen();
			forStmt.action = act;

			forStmt.setTransformationSource(source);
			return Java_Generator.wrapStatement(forStmt);
		}

		public static Java_Statement generateForLoopMany(Java_Expression initExpression, Java_Expression condExpression, Java_Expression incrExpression, List<Java_Statement> actions, AbstractToken source)
		{
			Java_Statement block = Java_StatementBlock.generateBlock(actions, source);
			return generateForLoopOne(initExpression, condExpression, incrExpression, block, source);
		}

		public static Java_Statement generateForRangeOne(Java_Variable var, EagleGenerator.TypeEnum type, Java_Expression fromExpression, EagleGenerator.RelationalEnum relOp, Java_Expression toExpression, Java_Expression delta, Java_Statement act, AbstractToken source)
		{
			Java_ForStatement forStmt = new Java_ForStatement();
			SeparatedList<Java_ForWhat, PunctuationComma> initializer = new SeparatedList<Java_ForWhat, PunctuationComma>();
			Java_ForWhat forWhat = new Java_ForWhat();
			if (type == EagleGenerator.TypeEnum.INTEGER)
			{
				Java_ForWithType withType = new Java_ForWithType();
				withType.varType = Java_Type.newPrimitiveType("int");
				withType.variable = new Java_Variable_Definition();
				string varName = var.firstId.getWhich().ToString();
				withType.variable.setValue(varName);
				withType.equalsInit = new Java_ForTypeInit();
				withType.equalsInit.equals = new PunctuationEquals();
				withType.equalsInit.initialExpr = fromExpression;
				withType.equalsInit.setPresent(true);
				forWhat.setWhich(withType);
			}
			else
			{
				Java_Expression asgExpr = Java_AssignmentExpression.generateAssignment(var, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpression, fromExpression);
				forWhat.setWhich(asgExpr);
			}
			initializer.addPrimaryElement(forWhat);

			SeparatedList<Java_Expression, PunctuationComma> loopIncrements = new SeparatedList<Java_Expression, PunctuationComma>();
			Java_Expression loopIncr;
			if (delta == null)
			{
				loopIncr = Java_PostIncrementExpression.generateIncrement(var, EagleGenerator.IncrementEnum.INCREMENT, source);
			}
			else
			{
				AbstractToken whichDelta = delta.getWhich();
				if (!(whichDelta is Java_Number))
				{
					throw new Exception("Can only handle simple loop increments: " + whichDelta);
				}

				loopIncr = Java_AssignmentExpression.generateAssignment(var, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, delta, source);
			}
			loopIncrements.addPrimaryElement(loopIncr);

			Java_VariableExpression tempVar = new Java_VariableExpression();
			tempVar.variable = var;
			Java_Expression varExpr = Java_Generator.wrapExpression(tempVar);

			Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
			Java_Expression loopTest = Java_RelationalExpression.generateRelational(types, varExpr, relOp, toExpression, toExpression);

			forStmt.leftParen = new PunctuationLeftParen();
			forStmt.initial = new Java_ForInit();
			forStmt.initial.setPresent(true);
			forStmt.initial.what = initializer;
			forStmt.semicolon1 = new PunctuationSemicolon();
			forStmt.terminateCondition = loopTest;
			forStmt.terminateCondition.setPresent(true);
			forStmt.semicolon2 = new PunctuationSemicolon();
			forStmt.increments = loopIncrements;
			forStmt.rightParen = new PunctuationRightParen();
			forStmt.action = act;

			forStmt.setTransformationSource(source);
			return Java_Generator.wrapStatement(forStmt);
		}

		public static Java_Statement generateForRangeMany(Java_Variable var, EagleGenerator.TypeEnum type, Java_Expression fromExpression, EagleGenerator.RelationalEnum relOper, Java_Expression toExpression, Java_Expression delta, List<Java_Statement> actions, AbstractToken source)
		{
			Java_Statement block = Java_StatementBlock.generateBlock(actions, source);
			return generateForRangeOne(var, type, fromExpression, relOper, toExpression, delta, block, source);
		}
	}

}

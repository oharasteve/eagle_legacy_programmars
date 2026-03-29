// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

namespace com.eagle.programmar.CSharp.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Statement = com.eagle.programmar.CSharp.CSharp_Statement;
	using CSharp_Syntax = com.eagle.programmar.CSharp.CSharp_Syntax;
	using CSharp_Type = com.eagle.programmar.CSharp.CSharp_Type;
	using CSharp_Variable = com.eagle.programmar.CSharp.CSharp_Variable;
	using CSharp_AssignmentExpression = com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
	using CSharp_PostIncrementExpression = com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression;
	using CSharp_PreIncrementExpression = com.eagle.programmar.CSharp.Expressions.CSharp_PreIncrementExpression;
	using CSharp_RelationalExpression = com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
	using CSharp_VariableExpression = com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
	using CSharp_Variable_Definition = com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_Number = com.eagle.programmar.CSharp.Terminals.CSharp_Number;
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

	public class CSharp_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleScope.EagleScopeInterface, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @DOC("statements/iteration-statements") com.eagle.programmar.CSharp.Terminals.CSharp_Keyword FOR = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("for");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @NOSPACE SeparatedList<CSharp_ForWhat, com.eagle.tokens.punctuation.PunctuationComma> initial;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationSemicolon semicolon1;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSharp.CSharp_Expression terminateCondition;
		public CSharp_Expression terminateCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationSemicolon semicolon2;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.SeparatedList<com.eagle.programmar.CSharp.CSharp_Expression, com.eagle.tokens.punctuation.PunctuationComma> increments;
		public SeparatedList<CSharp_Expression, PunctuationComma> increments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.CSharp.CSharp_Statement action;
		public CSharp_Statement action;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public class CSharp_ForWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ForWithType XXwithType;
			public CSharp_ForWithType XXwithType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ForWithoutType XXwithoutType;
			public CSharp_ForWithoutType XXwithoutType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CSharp_Expression XXexpr;
			public CSharp_Expression XXexpr;
		}

		public class CSharp_ForWithType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Type varType;
			public CSharp_Type varType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition variable;
			public CSharp_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CSharp_ForTypeInit equalsInit;
			public  OPT;
		}

		public class CSharp_ForWithoutType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition variable;
			public CSharp_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSharp_ForTypeInit equalsInit;
			public CSharp_ForTypeInit equalsInit;
		}

		public class CSharp_ForTypeInit : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.CSharp_Expression initialExpr;
			public CSharp_Expression initialExpr;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.CSharp.CSharp_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			CSharp_ForWhat forWhat = initial.first();
			if (forWhat.getWhich() is CSharp_ForWithType)
			{
				CSharp_ForWithType whatforWith = (CSharp_ForWithType) forWhat.getWhich();
				EagleValue init = interpreter.getEagleValue(whatforWith.equalsInit.initialExpr);
				interpreter.setSymbol(whatforWith.variable, whatforWith.variable.getValue(), init);
			}
			else if (forWhat.getWhich() is CSharp_ForWithoutType)
			{
				CSharp_ForWithoutType whatforWithout = (CSharp_ForWithoutType) forWhat.getWhich();
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

			CSharp_Expression increment = increments.first();

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

		private static bool guessDirection(CSharp_Expression testExpr, CSharp_Expression incrExpr)
		{
			AbstractToken which1 = incrExpr.getWhich();
			if (which1 is CSharp_PostIncrementExpression)
			{
				CSharp_PostIncrementExpression post = (CSharp_PostIncrementExpression) which1;
				return post.@operator.getValue().Equals("--");
			}
			if (which1 is CSharp_PreIncrementExpression)
			{
				CSharp_PreIncrementExpression pre = (CSharp_PreIncrementExpression) which1;
				return pre.@operator.getValue().Equals("--");
			}

			AbstractToken which2 = testExpr.getWhich();
			if (which2 is CSharp_RelationalExpression)
			{
				CSharp_RelationalExpression rel = (CSharp_RelationalExpression) which2;
				string oper = rel.@operator.getValue();
				if (oper.Equals(">") || oper.Equals(">="))
				{
					return true;
				}
			}

			return false; // Just don't know :(
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (this.initial.getPrimaryCount() == 1)
			{
				string varName = null;
				CSharp_Expression forInit = null;
				CSharp_ForWhat what = this.initial.first();
				if (what.getWhich() is CSharp_ForWithType)
				{
					CSharp_ForWithType withType = (CSharp_ForWithType) what.getWhich();
					varName = withType.variable.getValue();
					forInit = withType.equalsInit.initialExpr;
				}
				else if (what.getWhich() is CSharp_ForWithoutType)
				{
					CSharp_ForWithoutType withoutType = (CSharp_ForWithoutType) what.getWhich();
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

		public static CSharp_Statement generateForLoopOne(CSharp_Expression initExpression, CSharp_Expression condExpression, CSharp_Expression incrExpression, CSharp_Statement act, AbstractToken source)
		{
			SeparatedList<CSharp_ForWhat, PunctuationComma> initializer = new SeparatedList<CSharp_ForWhat, PunctuationComma>();
			CSharp_ForWhat forWhat = new CSharp_ForWhat();
			forWhat.setPresent(true);
			forWhat.setWhich(initExpression);
			initializer.addPrimaryElement(forWhat);

			SeparatedList<CSharp_Expression, PunctuationComma> loopIncrements = new SeparatedList<CSharp_Expression, PunctuationComma>();
			loopIncrements.addPrimaryElement(incrExpression);

			CSharp_ForStatement forStmt = new CSharp_ForStatement();
			forStmt.leftParen = new PunctuationLeftParen();
			forStmt.initial = initializer;
			forStmt.semicolon1 = new PunctuationSemicolon();
			forStmt.terminateCondition = condExpression;
			forStmt.terminateCondition.setPresent(true);
			forStmt.semicolon2 = new PunctuationSemicolon();
			forStmt.increments = loopIncrements;
			forStmt.rightParen = new PunctuationRightParen();
			forStmt.action = act;

			forStmt.setTransformationSource(source);
			return CSharp_Generator.wrapStatement(forStmt);
		}

		public static CSharp_Statement generateForLoopMany(CSharp_Expression initExpression, CSharp_Expression condExpression, CSharp_Expression incrExpression, List<CSharp_Statement> actions, AbstractToken source)
		{
			CSharp_Statement block = CSharp_StatementBlock.generateBlock(actions, source);
			return generateForLoopOne(initExpression, condExpression, incrExpression, block, source);
		}

		public static CSharp_Statement generateForRangeOne(CSharp_Variable var, EagleGenerator.TypeEnum type, CSharp_Expression fromExpression, EagleGenerator.RelationalEnum relOp, CSharp_Expression toExpression, CSharp_Expression delta, CSharp_Statement act, AbstractToken source)
		{
			SeparatedList<CSharp_ForWhat, PunctuationComma> initializer = new SeparatedList<CSharp_ForWhat, PunctuationComma>();
			CSharp_ForWhat forWhat = new CSharp_ForWhat();
			forWhat.setPresent(true);
			if (type == EagleGenerator.TypeEnum.INTEGER)
			{
				CSharp_ForWithType withType = new CSharp_ForWithType();
				withType.varType = CSharp_Type.newPrimitiveType("int");
				withType.variable = new CSharp_Variable_Definition();
				string varName = var.firstId.getWhich().ToString();
				withType.variable.setValue(varName);
				withType.equalsInit = new CSharp_ForTypeInit();
				withType.equalsInit.equals = new PunctuationEquals();
				withType.equalsInit.initialExpr = fromExpression;
				withType.equalsInit.setPresent(true);
				forWhat.setWhich(withType);
			}
			else
			{
				CSharp_Expression asgExpr = CSharp_AssignmentExpression.generateAssignment(var, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpression, fromExpression);
				forWhat.setWhich(asgExpr);
			}
			initializer.addPrimaryElement(forWhat);

			SeparatedList<CSharp_Expression, PunctuationComma> loopIncrements = new SeparatedList<CSharp_Expression, PunctuationComma>();
			CSharp_Expression loopIncr;
			if (delta == null)
			{
				loopIncr = CSharp_PostIncrementExpression.generateIncrement(var, EagleGenerator.IncrementEnum.INCREMENT, source);
			}
			else
			{
				AbstractToken whichDelta = delta.getWhich();
				if (!(whichDelta is CSharp_Number))
				{
					throw new Exception("Can only handle simple loop increments: " + whichDelta);
				}

				loopIncr = CSharp_AssignmentExpression.generateAssignment(var, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, delta, source);
			}
			loopIncrements.addPrimaryElement(loopIncr);

			CSharp_VariableExpression tempVar = new CSharp_VariableExpression();
			tempVar.variable = var;
			CSharp_Expression varExpr = CSharp_Generator.wrapExpression(tempVar);

			CSharp_Expression loopTest = CSharp_RelationalExpression.generateRelational(null, varExpr, relOp, toExpression, toExpression);

			CSharp_ForStatement forStmt = new CSharp_ForStatement();
			forStmt.leftParen = new PunctuationLeftParen();
			forStmt.initial = initializer;
			forStmt.semicolon1 = new PunctuationSemicolon();
			forStmt.terminateCondition = loopTest;
			forStmt.terminateCondition.setPresent(true);
			forStmt.semicolon2 = new PunctuationSemicolon();
			forStmt.increments = loopIncrements;
			forStmt.rightParen = new PunctuationRightParen();
			forStmt.action = act;

			forStmt.setTransformationSource(source);
			return CSharp_Generator.wrapStatement(forStmt);
		}

		public static CSharp_Statement generateForRangeMany(CSharp_Variable var, EagleGenerator.TypeEnum type, CSharp_Expression fromExpression, EagleGenerator.RelationalEnum relOper, CSharp_Expression toExpression, CSharp_Expression delta, List<CSharp_Statement> actions, AbstractToken source)
		{
			CSharp_Statement block = CSharp_StatementBlock.generateBlock(actions, source);
			return generateForRangeOne(var, type, fromExpression, relOper, toExpression, delta, block, source);
		}
	}

}

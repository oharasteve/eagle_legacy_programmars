// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression;
import com.eagle.programmar.Java.Expressions.Java_PreIncrementExpression;
import com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
import com.eagle.programmar.Java.Expressions.Java_VariableExpression;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.IncrementEnum;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleScopeInterface,
		EagleTransformableStatement
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.14") Java_Keyword FOR = new Java_Keyword("for");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE Java_Annotation annotation;
	public @S(50) @OPT @NOSPACE Java_ForInit initial;
	public @S(60) @NOSPACE PunctuationSemicolon semicolon1;
	public @S(70) @OPT Java_Expression terminateCondition;
	public @S(80) @NOSPACE PunctuationSemicolon semicolon2;
	public @S(90) @OPT SeparatedList<Java_Expression, PunctuationComma> increments;
	public @S(100) @NOSPACE PunctuationRightParen rightParen;
	public @S(110) @OPT Java_Comment comment;
	public @S(120) Java_Statement action;

	public static class Java_ForInit extends TokenSequence
	{
		public @S(10) @OPT Java_Keyword FINAL = new Java_Keyword("final");
		public @S(20) SeparatedList<Java_ForWhat, PunctuationComma> what;
	}

	public static class Java_ForWhat extends TokenChooser
	{
		public @CHOICE Java_ForWithType XXforWithType;
		public @CHOICE Java_ForWithoutType XXforWithoutType;
		public @LAST Java_Expression XXexpr;
	}

	public static class Java_ForWithType extends TokenSequence
	{
		public @S(10) Java_Type varType;
		public @S(20) Java_Variable_Definition variable;
		public @S(30) @OPT Java_ForTypeInit equalsInit;
	}

	public static class Java_ForWithoutType extends TokenSequence
	{
		public @S(10) Java_Variable_Definition variable;
		public @S(20) Java_ForTypeInit equalsInit;
	}

	public static class Java_ForTypeInit extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Java_Expression initialExpr;
	}

	private @SKIP ForLoopMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Java_ForWhat forWhat = initial.what.first();
		if (forWhat.getWhich() instanceof Java_ForWithType)
		{
			Java_ForWithType whatforWith = (Java_ForWithType) forWhat.getWhich();
			EagleValue init = interpreter.getEagleValue(whatforWith.equalsInit.initialExpr);
			interpreter.setSymbol(whatforWith.variable, whatforWith.variable.getValue(), init);
		}
		else if (forWhat.getWhich() instanceof Java_ForWithoutType)
		{
			Java_ForWithoutType whatforWithout = (Java_ForWithoutType) forWhat.getWhich();
			EagleValue init = interpreter.getEagleValue(whatforWithout.equalsInit.initialExpr);
			interpreter.setSymbol(whatforWithout.variable, whatforWithout.variable.getValue(), init);
		}
		else
		{
			throw new RuntimeException("Unexpected for loop construct: " + forWhat.getWhich());
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
			boolean keepGoing = interpreter.getBoolValue(terminateCondition);
			if (!keepGoing) break;

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
		boolean backwards = guessDirection(terminateCondition, increment);

		_metrics.completedLoop(metric, backwards);
		return result;
	}

	private static boolean guessDirection(Java_Expression testExpr, Java_Expression incrExpr)
	{
		AbstractToken which1 = incrExpr.getWhich();
		if (which1 instanceof Java_PostIncrementExpression)
		{
			Java_PostIncrementExpression post = (Java_PostIncrementExpression) which1;
			return post.operator.getValue().equals("--");
		}
		if (which1 instanceof Java_PreIncrementExpression)
		{
			Java_PreIncrementExpression pre = (Java_PreIncrementExpression) which1;
			return pre.operator.getValue().equals("--");
		}

		AbstractToken which2 = testExpr.getWhich();
		if (which2 instanceof Java_RelationalExpression)
		{
			Java_RelationalExpression rel = (Java_RelationalExpression) which2;
			String oper = rel.operator.getValue();
			if (oper.equals(">") || oper.equals(">="))
			{
				return true;
			}
		}

		return false; // Just don't know :(
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (this.initial.what.getPrimaryCount() == 1)
		{
			String varName = null;
			Java_Expression forInit = null;
			Java_ForWhat what = this.initial.what.first();
			if (what.getWhich() instanceof Java_ForWithType)
			{
				Java_ForWithType withType = (Java_ForWithType) what.getWhich();
				varName = withType.variable.getValue();
				forInit = withType.equalsInit.initialExpr;
			}
			else if (what.getWhich() instanceof Java_ForWithoutType)
			{
				Java_ForWithoutType withoutType = (Java_ForWithoutType) what.getWhich();
				varName = withoutType.variable.getValue();
				forInit = withoutType.equalsInit.initialExpr;
			}

			if (forInit != null)
			{
				AbstractExpression fromExpr = transformer.transformExpression(generator, forInit);
				AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
						SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, fromExpr, null);

				if (this.increments.getPrimaryCount() == 1)
				{
					AbstractExpression termExpr = transformer.transformExpression(generator, terminateCondition);
					AbstractExpression delta = transformer.transformExpression(generator,
							increments.first());
					AbstractStatement newAction = transformer.transformStatement1(generator, this.action);
					return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
				}
			}
		}

		throw new RuntimeException("Unable to handle for loop: " + this);
	}

	public static Java_Statement generateForLoopOne(Java_Expression initExpression,
			Java_Expression condExpression, Java_Expression incrExpression,
			Java_Statement act, AbstractToken source)
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

	public static Java_Statement generateForLoopMany(Java_Expression initExpression,
			Java_Expression condExpression, Java_Expression incrExpression,
			ArrayList<Java_Statement> actions, AbstractToken source)
	{
		Java_Statement block = Java_StatementBlock.generateBlock(actions, source);
		return generateForLoopOne(initExpression, condExpression, incrExpression,
				block, source);
	}

	public static Java_Statement generateForRangeOne(Java_Variable var, TypeEnum type,
			Java_Expression fromExpression, RelationalEnum relOp, Java_Expression toExpression,
			Java_Expression delta, Java_Statement act, AbstractToken source)
	{
		Java_ForStatement forStmt = new Java_ForStatement();
		SeparatedList<Java_ForWhat, PunctuationComma> initializer = new SeparatedList<Java_ForWhat, PunctuationComma>();
		Java_ForWhat forWhat = new Java_ForWhat();
		if (type == TypeEnum.INTEGER)
		{
			Java_ForWithType withType = new Java_ForWithType();
			withType.varType = Java_Type.newPrimitiveType("int");
			withType.variable = new Java_Variable_Definition();
			String varName = var.firstId.getWhich().toString();
			withType.variable.setValue(varName);
			withType.equalsInit = new Java_ForTypeInit();
			withType.equalsInit.equals = new PunctuationEquals();
			withType.equalsInit.initialExpr = fromExpression;
			withType.equalsInit.setPresent(true);
			forWhat.setWhich(withType);
		}
		else
		{
			Java_Expression asgExpr = Java_AssignmentExpression.generateAssignment(
					var, null, AssignmentEnum.EQUALS, fromExpression, fromExpression);
			forWhat.setWhich(asgExpr);
		}
		initializer.addPrimaryElement(forWhat);

		SeparatedList<Java_Expression, PunctuationComma> loopIncrements = new SeparatedList<Java_Expression, PunctuationComma>();
		Java_Expression loopIncr;
		if (delta == null)
		{
			loopIncr = Java_PostIncrementExpression.generateIncrement(var, IncrementEnum.INCREMENT, source);
		}
		else
		{
			AbstractToken whichDelta = delta.getWhich();
			if (!(whichDelta instanceof Java_Number))
			{
				throw new RuntimeException("Can only handle simple loop increments: " + whichDelta);
			}

			loopIncr = Java_AssignmentExpression.generateAssignment(
					var, null, AssignmentEnum.PLUS_EQUALS, delta, source);
		}
		loopIncrements.addPrimaryElement(loopIncr);

		Java_VariableExpression tempVar = new Java_VariableExpression();
		tempVar.variable = var;
		Java_Expression varExpr = Java_Generator.wrapExpression(tempVar);

		Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
		Java_Expression loopTest = Java_RelationalExpression.generateRelational(
				types, varExpr, relOp, toExpression, toExpression);

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

	public static Java_Statement generateForRangeMany(Java_Variable var, TypeEnum type,
			Java_Expression fromExpression, RelationalEnum relOper, Java_Expression toExpression,
			Java_Expression delta, ArrayList<Java_Statement> actions, AbstractToken source)
	{
		Java_Statement block = Java_StatementBlock.generateBlock(actions, source);
		return generateForRangeOne(var, type, fromExpression, relOper, toExpression,
				delta, block, source);
	}
}

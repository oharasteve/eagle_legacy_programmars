// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.C_Syntax;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Expressions.C_PostIncrementVariable;
import com.eagle.programmar.C.Expressions.C_PreIncrementExpression;
import com.eagle.programmar.C.Expressions.C_RelationalExpression;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class C_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleScopeInterface,
		EagleTransformableStatement
{
	public @S(10) @DOC("#The-for-Statement") C_Keyword FOR = new C_Keyword("for");
	public @S(20) C_ForLoopBody body;
	public @S(30) C_Statement action;

	public static class C_ForLoopBody extends TokenChooser
	{
		public @CHOICE C_ForLoopStatement XXloopStatement;
		public @CHOICE C_ForCollectionStatement XXcollectionStatement;
	}

	public static class C_ForLoopStatement extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<C_ForLoopVariable, PunctuationComma> loopVar;
		public @S(30) @OPT C_Comment comment1;
		public @S(40) PunctuationSemicolon semicolon1;
		public @S(50) @OPT C_Expression terminateCondition;
		public @S(60) @OPT C_Comment comment2;
		public @S(70) PunctuationSemicolon semicolon2;
		public @S(80) @OPT C_Expression increment;
		public @S(90) @OPT TokenList<C_MoreLoopIncrements> moreLoopIncrements;
		public @S(100) @OPT C_Comment comment3;
		public @S(110) PunctuationRightParen rightParen;
		public @S(120) @OPT C_Comment comment4;

		public static class C_ForLoopVariable extends TokenChooser
		{
			public @FIRST C_ForWithType XXforWithType;
			public @CHOICE C_ForWithoutType XXforWithoutType;
		}

		public static class C_MoreLoopIncrements extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) C_ForLoopVariable forVar;
		}
	}

	public static class C_ForCollectionStatement extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT C_Keyword CONST = new C_Keyword("const");
		public @S(30) C_Type varType;
		public @S(40) C_Variable forVar;
		public @S(50) PunctuationColon colon;
		public @S(60) C_Expression collection;
		public @S(70) PunctuationRightParen rightParen;
	}

	public static class C_ForWithType extends TokenSequence
	{
		public @S(10) C_Type varType;
		public @S(20) C_Variable_Definition variable;
		public @S(30) @OPT C_ForTypeInit equalsInit;

		public static class C_ForTypeInit extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_Expression initialExpr;
		}
	}

	public static class C_ForWithoutType extends TokenSequence
	{
		public @S(10) C_Variable_Definition variable;
		public @S(20) PunctuationEquals equals;
		public @S(30) C_Expression initialExpr;
	}

	private @SKIP ForLoopMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, C_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (body.getWhich() instanceof C_ForLoopStatement)
		{
			C_ForLoopStatement loop = (C_ForLoopStatement) body.getWhich();

			AbstractToken which = loop.loopVar.first().getWhich();
			if (which instanceof C_ForWithType)
			{
				C_ForWithType whatforWith = (C_ForWithType) which;
				EagleValue initial = interpreter.getEagleValue(whatforWith.equalsInit.initialExpr);
				interpreter.setSymbol(whatforWith.variable, whatforWith.variable.getValue(), initial);
			}
			else if (which instanceof C_ForWithoutType)
			{
				C_ForWithoutType noType = (C_ForWithoutType) which;
				EagleValue initial = interpreter.getEagleValue(noType.initialExpr);
				interpreter.setSymbol(noType.variable, noType.variable.getValue(), initial);
			}
			else
			{
				throw new RuntimeException("Cannot handle " + which);
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean keepGoing = interpreter.getBoolValue(loop.terminateCondition);
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

				interpreter.tryToInterpret(loop.increment);
			}

			// Have to guess to see if it was backwards
			boolean backwards = guessDirection(loop.terminateCondition, loop.increment);

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		throw new RuntimeException("Unexpected for loop construct: " + body.getWhich());
	}

	private static boolean guessDirection(C_Expression testExpr, C_Expression incrExpr)
	{
		AbstractToken which1 = incrExpr.getWhich();
		if (which1 instanceof C_PostIncrementVariable)
		{
			C_PostIncrementVariable post = (C_PostIncrementVariable) which1;
			return post.operator.getValue().equals("--");
		}
		if (which1 instanceof C_PreIncrementExpression)
		{
			C_PreIncrementExpression pre = (C_PreIncrementExpression) which1;
			return pre.operator.getValue().equals("--");
		}

		AbstractToken which2 = testExpr.getWhich();
		if (which2 instanceof C_RelationalExpression)
		{
			C_RelationalExpression rel = (C_RelationalExpression) which2;
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
		if (body.getWhich() instanceof C_ForLoopStatement)
		{
			C_ForLoopStatement loop = (C_ForLoopStatement) body.getWhich();
			String varName = null;
			C_Expression forInit = null;
			AbstractToken which = loop.loopVar.first().getWhich();
			if (which instanceof C_ForWithType)
			{
				C_ForWithType withType = (C_ForWithType) which;
				varName = withType.variable.getValue();
				forInit = withType.equalsInit.initialExpr;
			}
			else if (which instanceof C_ForWithoutType)
			{
				C_ForWithoutType noType = (C_ForWithoutType) which;
				varName = noType.variable.getValue();
				forInit = noType.initialExpr;
			}

			if (forInit != null)
			{
				AbstractExpression fromExpr = transformer.transformExpression(generator, forInit);
				AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
						SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, fromExpr, null);

				AbstractExpression termExpr = transformer.transformExpression(generator, loop.terminateCondition);
				AbstractExpression delta = transformer.transformExpression(generator, loop.increment);
				AbstractStatement newAction = transformer.transformStatement1(generator, this.action);
				return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
			}
		}

		throw new RuntimeException("Unable to handle for loop: " + this);
	}
}

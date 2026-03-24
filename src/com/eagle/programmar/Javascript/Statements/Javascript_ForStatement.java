// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Javascript.Javascript_Data.Javascript_More_Variables;
import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Expressions.Javascript_PostIncrementExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_PreIncrementExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_RelationalExpression;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Javascript_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("js_loop_for.asp") Javascript_Keyword FOR = new Javascript_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Javascript_ForLoopStatement forLoop;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT TokenList<Javascript_Comment> comments;
	public @S(60) Javascript_Element action;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Javascript_ForLoopStatement extends TokenSequence
	{
		public @S(10) @OPT Javascript_ForLoopVariable loopVar;
		public @S(20) @OPT Javascript_Punctuation equals = new Javascript_Punctuation("=");
		public @S(30) @OPT Javascript_Expression initialize;
		public @S(40) @OPT TokenList<Javascript_More_Variables> moreVariables;
		public @S(50) PunctuationSemicolon semicolon1;
		public @S(60) @OPT Javascript_Expression terminateCondition;
		public @S(70) PunctuationSemicolon semicolon2;
		public @S(80) @OPT Javascript_Expression increment;
		public @S(90) @OPT PunctuationComma comma;
		public @S(100) @OPT Javascript_Expression extraIncrement;

		public static class Javascript_ForLoopVariable extends TokenChooser
		{
			public @FIRST Javascript_ForLoopVariableWithType XXwithType;
			public @CHOICE Javascript_ForLoopVariableNoType XXnoType;
		}
	}

	public static class Javascript_ForLoopVariableWithType extends TokenSequence
	{
		public @S(10) Javascript_Type varType;
		public @S(20) Javascript_Variable forVar;
	}

	public static class Javascript_ForLoopVariableNoType extends TokenSequence
	{
		public @S(10) Javascript_Variable forVar;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		AbstractToken which = forLoop.loopVar.getWhich();
		Javascript_Variable forVar;
		if (which instanceof Javascript_ForLoopVariableWithType)
		{
			Javascript_ForLoopVariableWithType withType = (Javascript_ForLoopVariableWithType) which;
			forVar = withType.forVar;
		}
		else
		{
			Javascript_ForLoopVariableNoType noType = (Javascript_ForLoopVariableNoType) which;
			forVar = noType.forVar;
		}

		if (forVar.firstId.getWhich() instanceof Javascript_Identifier_Reference)
		{
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) forVar.firstId.getWhich();
			EagleValue init = interpreter.getEagleValue(forLoop.initialize);
			interpreter.setSymbol(this, id.getValue(), init);
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(forLoop.terminateCondition);
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

			interpreter.tryToInterpret(forLoop.increment);
		}

		// Have to guess to see if it was backwards
		boolean backwards = guessDirection(forLoop.terminateCondition, forLoop.increment);

		_metrics.competedLoop(metric, backwards);
		return result;
	}

	private static boolean guessDirection(Javascript_Expression testExpr, Javascript_Expression incrExpr)
	{
		AbstractToken which1 = incrExpr.getWhich();
		if (which1 instanceof Javascript_PostIncrementExpression)
		{
			Javascript_PostIncrementExpression post = (Javascript_PostIncrementExpression) which1;
			return post.operator.getValue().equals("--");
		}
		if (which1 instanceof Javascript_PreIncrementExpression)
		{
			Javascript_PreIncrementExpression pre = (Javascript_PreIncrementExpression) which1;
			return pre.operator.getValue().equals("--");
		}

		AbstractToken which2 = testExpr.getWhich();
		if (which2 instanceof Javascript_RelationalExpression)
		{
			Javascript_RelationalExpression rel = (Javascript_RelationalExpression) which2;
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
		AbstractToken which1 = forLoop.loopVar.getWhich();
		Javascript_Variable forVar;
		if (which1 instanceof Javascript_ForLoopVariableWithType)
		{
			Javascript_ForLoopVariableWithType withType = (Javascript_ForLoopVariableWithType) which1;
			forVar = withType.forVar;
		}
		else
		{
			Javascript_ForLoopVariableNoType noType = (Javascript_ForLoopVariableNoType) which1;
			forVar = noType.forVar;
		}

		AbstractToken whichName = forVar.firstId.getWhich();
		if (!(whichName instanceof Javascript_Identifier_Reference))
		{
			throw new RuntimeException("Javascript FOR must use a variable");
		}
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) whichName;
		String varName = id.getValue();

		AbstractExpression fromExpr = transformer.transformExpression(generator, forLoop.initialize);
		AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, fromExpr, null);

		AbstractExpression termExpr = transformer.transformExpression(generator, forLoop.terminateCondition);
		AbstractExpression delta = transformer.transformExpression(generator,
				forLoop.increment);
		AbstractStatement newAction = transformer.transformStatement1(generator, this.action.statement);
		return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
	}
}
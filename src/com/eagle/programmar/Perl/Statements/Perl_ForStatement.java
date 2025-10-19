// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
import com.eagle.programmar.Perl.Expressions.Perl_PostIncrementExpression;
import com.eagle.programmar.Perl.Expressions.Perl_PreIncrementExpression;
import com.eagle.programmar.Perl.Expressions.Perl_RelationalExpression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Perl_ForStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("control-structures.for.php") Perl_Keyword FOR = new Perl_Keyword("for");
	public @S(20) Perl_ForWhat forWhat;
	public @S(30) Perl_Statement action;
	
	private @SKIP ForLoopMetrics _metrics = null;

	public static class Perl_ForWhat extends TokenChooser
	{
		public @CHOICE Perl_ForVarInSet XXvarInSet;
		public @CHOICE Perl_ForLikeC XXlikeC;
	}
	
	public static class Perl_ForVarInSet extends TokenSequence
	{
		public @S(10) Perl_Keyword MY = new Perl_Keyword("my");
		public @S(20) Perl_Variable var;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) Perl_Expression initExpr;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class Perl_ForLikeC extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Perl_Variable variable;
		public @S(30) PunctuationEquals equals;
		public @S(40) Perl_Expression initExpr;
		public @S(50) PunctuationSemicolon semicolon1;
		public @S(60) @OPT Perl_Expression testExpr;
		public @S(70) PunctuationSemicolon semicolon2;
		public @S(80) @OPT Perl_Expression incrExpr;
		public @S(90) PunctuationRightParen rightParen;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (forWhat.getWhich() instanceof Perl_ForLikeC)
		{
			Perl_ForLikeC forLikeC = (Perl_ForLikeC) forWhat.getWhich();

			AbstractToken which = forLikeC.variable.getWhich();
			if (! (which instanceof Perl_UserVariable))
			{
				throw new RuntimeException("Must be a simple variable");
			}
			Perl_UserVariable userVar = (Perl_UserVariable) which;
			EagleValue initial = interpreter.getEagleValue(forLikeC.initExpr);
			interpreter.setSymbol(forLikeC.variable, userVar.id.getValue(), initial);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean keepGoing = interpreter.getBoolValue(forLikeC.testExpr);
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

				interpreter.tryToInterpret(forLikeC.incrExpr);
			}

			// Have to guess to see if it was backwards
			boolean backwards = guessDirection(forLikeC.testExpr, forLikeC.incrExpr);
			
			_metrics.competedLoop(metric, backwards);
			return result;
		}

		throw new RuntimeException("Unexpected for loop construct: " + forWhat.getWhich());
	}
	
	private static boolean guessDirection(Perl_Expression testExpr, Perl_Expression incrExpr)
	{
		AbstractToken which1 = incrExpr.getWhich();
		if (which1 instanceof Perl_PostIncrementExpression)
		{
			Perl_PostIncrementExpression post = (Perl_PostIncrementExpression) which1;
			return post.operator.getValue().equals("--");
		}
		if (which1 instanceof Perl_PreIncrementExpression)
		{
			Perl_PreIncrementExpression pre = (Perl_PreIncrementExpression) which1;
			return pre.operator.getValue().equals("--");
		}
		
		AbstractToken which2 = testExpr.getWhich();
		if (which2 instanceof Perl_RelationalExpression)
		{
			Perl_RelationalExpression rel = (Perl_RelationalExpression) which2;
			String oper = rel.operator.getValue();
			if (oper.equals(">") || oper.equals(">="))
			{
				return true;
			}
		}
		
		return false;	// Just don't know :(
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		if (! (forWhat.getWhich() instanceof Perl_ForLikeC))
		{
			throw new RuntimeException("Can only handle regular for loops");
		}
		Perl_ForLikeC forLikeC = (Perl_ForLikeC) forWhat.getWhich();
		
		AbstractToken which = forLikeC.variable.getWhich();
		if (! (which instanceof Perl_UserVariable))
		{
			throw new RuntimeException("Must be a simple variable");
		}
		Perl_UserVariable userVar = (Perl_UserVariable) which;
		String newName = Perl_Variable.repairName(userVar.id.getValue());

		Perl_Expression forInit = forLikeC.initExpr;
		AbstractExpression fromExpr = transformer.transformExpression(generator, forInit);
		AbstractExpression asgExpr = generator.newAssignmentExpression(newName,
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, fromExpr, null);
		
		AbstractExpression termExpr = transformer.transformExpression(generator, forLikeC.testExpr);
		AbstractExpression delta = transformer.transformExpression(generator, forLikeC.incrExpr);
		AbstractStatement newAction = transformer.transformStatement1(generator, this.action);
		return generator.newForLoopStatement1(asgExpr, termExpr, delta, newAction, this);
	}
}

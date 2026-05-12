// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Expressions.Powershell_PostIncrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_PreIncrementExpression;
import com.eagle.programmar.Powershell.Expressions.Powershell_Relational_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Powershell_ForStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
		EagleTransformableStatement
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#843-the-for-statement") Powershell_Keyword FOR = new Powershell_Keyword(
			"For");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Powershell_Variable var;
	public @S(40) PunctuationEquals equals;
	public @S(50) Powershell_Expression init;
	public @S(60) PunctuationSemicolon semicolon1;
	public @S(70) Powershell_Expression stopCondition;
	public @S(80) PunctuationSemicolon semicolon2;
	public @S(90) Powershell_Expression iterate;
	public @S(100) PunctuationRightParen rightParen;

	public @S(110) PunctuationLeftBrace leftBrace;
	public @S(120) @OPT Powershell_EndOfLine eoln;
	public @S(130) TokenList<Powershell_Element> stmts;
	public @S(140) PunctuationRightBrace rightBrace;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		int start = interpreter.getIntValue(init);
		interpreter.setSymbol(this, var.id.getValue(), new EagleInteger(start));

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(stopCondition);
			if (!keepGoing) break;
			metric.iterate();

			for (Powershell_Element stmt : stmts._elements)
			{
				result = interpreter.tryToInterpret(stmt.element);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

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

			interpreter.tryToInterpret(iterate);
		}

		// Have to guess to see if it was backwards
		boolean backwards = guessDirection(stopCondition, iterate);

		_metrics.completedLoop(metric, backwards);
		return result;
	}

	private static boolean guessDirection(Powershell_Expression testExpr, Powershell_Expression incrExpr)
	{
		AbstractToken which1 = incrExpr.getWhich();
		if (which1 instanceof Powershell_PostIncrementExpression)
		{
			Powershell_PostIncrementExpression post = (Powershell_PostIncrementExpression) which1;
			return post.operator.getValue().equals("--");
		}
		if (which1 instanceof Powershell_PreIncrementExpression)
		{
			Powershell_PreIncrementExpression pre = (Powershell_PreIncrementExpression) which1;
			return pre.operator.getValue().equals("--");
		}

		AbstractToken which2 = testExpr.getWhich();
		if (which2 instanceof Powershell_Relational_Expression)
		{
			Powershell_Relational_Expression rel = (Powershell_Relational_Expression) which2;
			String oper = rel.operator.getValue().toLowerCase();
			if (oper.equals("-gt") || oper.equals("-ge"))
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
		String newName = Powershell_Variable.repairName(var.id.getValue());

		AbstractExpression fromExpr = transformer.transformExpression(generator, init);
		AbstractExpression asgExpr = generator.newAssignmentExpression(newName,
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, fromExpr, null);

		AbstractExpression termExpr = transformer.transformExpression(generator, stopCondition);
		AbstractExpression delta = transformer.transformExpression(generator, iterate);

		ArrayList<AbstractStatement> newActions = new ArrayList<AbstractStatement>();
		for (Powershell_Element stmt : stmts._elements)
		{
			AbstractStatement newAction = transformer.transformStatement1(generator,
					stmt.element.getWhich());
			newActions.add(newAction);
		}
		return generator.newForLoopStatement(asgExpr, termExpr, delta, newActions, this);
	}
}

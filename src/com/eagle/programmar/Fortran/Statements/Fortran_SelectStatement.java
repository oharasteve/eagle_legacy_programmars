// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2026

package com.eagle.programmar.Fortran.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.SwitchMetrics;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Fortran_SelectStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) Fortran_Keyword SELECT1 = new Fortran_Keyword("SELECT");
	public @S(20) Fortran_Keyword CASE = new Fortran_Keyword("CASE");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) Fortran_Expression expr;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) Fortran_EOLN eoln1;
	public @S(70) TokenList<Fortran_SelectCase> cases;
	public @S(80) @OPT Fortran_SelectDefault defualtCase; 
	public @S(90) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(100) Fortran_Keyword SELECT2 = new Fortran_Keyword("SELECT");
	public @S(110) Fortran_EOLN eoln2;
	
	public static class Fortran_SelectCase extends TokenSequence
	{
		public @S(10) Fortran_Keyword CASE = new Fortran_Keyword("CASE");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Fortran_Expression expr;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) Fortran_EOLN eoln;
		public @S(60) TokenList<Fortran_Statement> caseStatements;
	}

	public static class Fortran_SelectDefault extends TokenSequence
	{
		public @S(10) Fortran_Keyword CASE = new Fortran_Keyword("CASE");
		public @S(20) Fortran_Keyword DEFAULT = new Fortran_Keyword("DEFAULT");
		public @S(30) Fortran_EOLN eoln;
		public @S(40) TokenList<Fortran_Statement> defaultStatements;
	}

	private @SKIP SwitchMetrics _metrics = null;
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Fortran_Statement> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line numbers etc are all set
			_metrics = new SwitchMetrics(interpreter._metrics, SELECT1);
		}

		Fortran_SelectDefault defaultClause = null;

		int val = interpreter.getIntValue(expr);
		for (Fortran_SelectCase nextCase : cases._elements)
		{
			int whenValue = interpreter.getIntValue(nextCase.expr);
			if (val == whenValue)
			{
				_metrics.matched(expr, whenValue);
				todo = nextCase.caseStatements;
				break;
			}
		}
		
		if (todo == null && defualtCase != null && defualtCase.isPresent())
		{
			_metrics.noMatch(defaultClause);
			todo = defualtCase.defaultStatements;
		}

		if (todo != null)
		{
			for (Fortran_Statement stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt.getWhich());
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression newValue = transformer.transformExpression(generator, expr);

		ArrayList<AbstractStatement> defaultActionList = null;
		
		ArrayList<ArrayList<AbstractExpression>> allCases = new ArrayList<ArrayList<AbstractExpression>>();
		ArrayList<ArrayList<AbstractStatement>> allActions = new ArrayList<ArrayList<AbstractStatement>>();
		for (Fortran_SelectCase nextCase : cases._elements)
		{
			ArrayList<AbstractExpression> caseList = new ArrayList<AbstractExpression>();
			AbstractExpression newExpr = transformer.transformExpression(generator, nextCase.expr);
			caseList.add(newExpr);	// Just one value per Case, for now
			allCases.add(caseList);
				
			ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
			for (Fortran_Statement stmt1 : nextCase.caseStatements._elements)
			{
				ArrayList<AbstractStatement> transStmts = transformer.transformStatement(generator, stmt1);
				for (AbstractStatement stmt2 : transStmts)
				{
					actionList.add(stmt2);
				}
				AbstractStatement breakStmt = generator.newBreakStatement(null);
				actionList.add(breakStmt);
			}
			allActions.add(actionList);
		}
		
		if (defualtCase != null && defualtCase.isPresent())
		{
			defaultActionList = new ArrayList<AbstractStatement>();
			for (Fortran_Statement stmt3 : defualtCase.defaultStatements._elements)
			{
				for (AbstractStatement stmt4 : transformer.transformStatement(generator, stmt3))
				{
					defaultActionList.add(stmt4);
				}
				AbstractStatement breakStmt = generator.newBreakStatement(null);
				defaultActionList.add(breakStmt);
			}
		}

		AbstractStatement stmt = generator.newSwitchStatement(newValue, allCases, allActions, defaultActionList, this);
		return stmt;
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.EagleGenerator.IncrementEnum;
import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.generate.Statements.Eagle_Generate_ForLoop;
import com.eagle.generate.Statements.Eagle_Generate_ForRange;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Java.Java_Annotation;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression;
import com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
import com.eagle.programmar.Java.Expressions.Java_VariableExpression;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleScopeInterface,
				Eagle_Generate_ForLoop<Java_Statement, Java_Expression>,
				Eagle_Generate_ForRange<Java_Statement, Java_Expression>
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.14") Java_Keyword FOR = new Java_Keyword("for");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE Java_Annotation annotation;
	public @S(50) @OPT Java_ForInit init;
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
		public @CHOICE Java_Expression XXexpr;
		public @FIRST Java_ForWithType XXforWithType;
	}

	public static class Java_ForWithType extends TokenSequence
	{
		public @S(10) Java_Type varType;
		public @S(20) Java_Variable_Definition variable;
		public @S(30) @OPT Java_ForTypeInit equalsInit;
		
		public static class Java_ForTypeInit extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Java_Expression initialExpr;
		}
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
		Java_ForWhat forWhat = init.what.first();
		if (forWhat.getWhich() instanceof Java_ForWithType)
		{
			Java_ForWithType whatforWith = (Java_ForWithType) forWhat.getWhich();

			EagleValue initial = interpreter.getEagleValue(whatforWith.equalsInit.initialExpr);
			interpreter.setSymbol(whatforWith.variable, whatforWith.variable.getValue(), initial);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, this);
			}
			ForLoopMetric metric = new ForLoopMetric();

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

				interpreter.tryToInterpret(increments.first());
			}

			_metrics.competedLoop(metric);
			return result;
		}

		throw new RuntimeException("Unexpected for loop construct: " + forWhat.getWhich());
	}

	@Override
	public Java_Statement generateForLoop1(Java_Expression initExpression,
			Java_Expression condExpression, Java_Expression incrExpression,
			Java_Statement action, AbstractToken source)
	{
		SeparatedList<Java_ForWhat, PunctuationComma> initializer = new SeparatedList<Java_ForWhat, PunctuationComma>();
		Java_ForWhat forWhat = new Java_ForWhat();
		forWhat.setPresent(true);
		forWhat.setWhich(initExpression);
		initializer.addPrimaryElement(forWhat);

		SeparatedList<Java_Expression, PunctuationComma> loopIncrements = new SeparatedList<Java_Expression, PunctuationComma>();
		loopIncrements.addPrimaryElement(incrExpression);

		Java_ForStatement forStmt = new Java_ForStatement();
		forStmt.leftParen = new PunctuationLeftParen();
		forStmt.init = new Java_ForInit();
		forStmt.init.what = new SeparatedList<Java_ForWhat, PunctuationComma>();
		Java_ForWhat what = new Java_ForWhat();
		what.setWhich(initExpression);
		forStmt.init.what.addPrimaryElement(what);
		
		forStmt.semicolon1 = new PunctuationSemicolon();
		forStmt.terminateCondition = condExpression;
		forStmt.terminateCondition.setPresent(true);
		forStmt.semicolon2 = new PunctuationSemicolon();
		forStmt.increments = loopIncrements;
		forStmt.rightParen = new PunctuationRightParen();
		forStmt.action = action;
		
		forStmt.setTransformationSource(source);
		return Java_Generator.wrapStatement(forStmt);
	}
	
	@Override
	public Java_Statement generateForLoop(Java_Expression initExpression,
			Java_Expression condExpression, Java_Expression incrExpression,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		Java_StatementBlock block = new Java_StatementBlock();
		block.leftBrace = new PunctuationLeftBrace();
		block.rightBrace = new PunctuationRightBrace();
		block.statements = new TokenList<Java_StatementOrComment>();
		for (AbstractStatement stmt : actions)
		{
			Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
			stmtOrComment.setWhich((Java_Statement) stmt);
			block.statements.addToken(stmtOrComment);
		}
		
		return generateForLoop1(initExpression, condExpression, incrExpression,
				Java_Generator.wrapStatement(block), source);
	}

	@Override
	public Java_Statement generateForRange1(String varName, Java_Expression fromExpression,
			Java_Expression toExpression, Java_Expression delta,
			Java_Statement action, AbstractToken source)
	{
		Java_VariableExpression tempVar = new Java_VariableExpression();
		Java_Expression varExpr = tempVar.generateVarExpr(varName, null, null);
		
		Java_AssignmentExpression asgExpr = new Java_AssignmentExpression();
		Java_Expression initExpr = asgExpr.generateAssignment(varExpr, AssignmentEnum.EQUALS,
				toExpression, source);

		SeparatedList<Java_ForWhat, PunctuationComma> initializer = new SeparatedList<Java_ForWhat, PunctuationComma>();
		Java_ForWhat forWhat = new Java_ForWhat();
		forWhat.setPresent(true);
		forWhat.setWhich(initExpr);
		initializer.addPrimaryElement(forWhat);

		Java_RelationalExpression relExpr = new Java_RelationalExpression();
		relExpr.generateRelational(varExpr, RelationalEnum.LESS_EQUALS, toExpression, toExpression);
		Java_Expression untilCondition = Java_Generator.wrapExpression(relExpr);

		Java_Expression loopTest = untilCondition;
				
		SeparatedList<Java_Expression, PunctuationComma> loopIncrements = new SeparatedList<Java_Expression, PunctuationComma>();
		Java_Expression loopIncr;
		if (delta == null)
		{
			Java_Variable var = Java_Variable.newVariable(varName);
			Java_PostIncrementExpression postExpr = new Java_PostIncrementExpression();
			loopIncr = postExpr.generateIncrement(var, IncrementEnum.INCREMENT, source);
		}
		else
		{
			Java_AssignmentExpression asgExp2 = new Java_AssignmentExpression();
			loopIncr = asgExp2.generateAssignment(varExpr, AssignmentEnum.PLUS_EQUALS, delta, source);
		}
		loopIncrements.addPrimaryElement(loopIncr);

		Java_ForStatement forStmt = new Java_ForStatement();
		forStmt.leftParen = new PunctuationLeftParen();
		forStmt.init.what = initializer;
		forStmt.semicolon1 = new PunctuationSemicolon();
		forStmt.terminateCondition = loopTest;
		forStmt.terminateCondition.setPresent(true);
		forStmt.semicolon2 = new PunctuationSemicolon();
		forStmt.increments = loopIncrements;
		forStmt.rightParen = new PunctuationRightParen();
		forStmt.action = action;
		
		forStmt.setTransformationSource(source);
		return Java_Generator.wrapStatement(forStmt);
	}
	
	@Override
	public Java_Statement generateForRange(String varName, Java_Expression fromExpression,
			Java_Expression toExpression, Java_Expression delta,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		Java_StatementBlock block = new Java_StatementBlock();
		block.leftBrace = new PunctuationLeftBrace();
		block.rightBrace = new PunctuationRightBrace();
		block.statements = new TokenList<Java_StatementOrComment>();
		for (AbstractStatement stmt : actions)
		{
			Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
			stmtOrComment.setWhich((Java_Statement) stmt);
			block.statements.addToken(stmtOrComment);
		}
		
		return generateForRange1(varName, fromExpression, toExpression,
				delta, Java_Generator.wrapStatement(block), source);
	}
}

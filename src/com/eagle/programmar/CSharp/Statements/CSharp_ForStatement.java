// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Syntax;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
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

public class CSharp_ForStatement extends TokenSequence
			implements EagleRunnableWithResult, AbstractStatement, EagleScopeInterface,
					EagleTransformableStatement
{
	public @S(10) @NEWLINE @DOC("statements/iteration-statements") CSharp_Keyword FOR = new CSharp_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT @NOSPACE SeparatedList<CSharp_ForWhat, PunctuationComma> initial;
	public @S(40) @NOSPACE PunctuationSemicolon semicolon1;
	public @S(50) CSharp_Expression terminateCondition;
	public @S(60) @NOSPACE PunctuationSemicolon semicolon2;
	public @S(70) SeparatedList<CSharp_Expression, PunctuationComma> increments;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;
	public @S(90) CSharp_Statement action;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class CSharp_ForWhat extends TokenChooser
	{
		public @CHOICE CSharp_ForWithType XXwithType;
		public @CHOICE CSharp_ForWithoutType XXwithoutType;
		public @LAST CSharp_Expression XXexpr;
	}

	public static class CSharp_ForWithType extends TokenSequence
	{
		public @S(10) CSharp_Type varType;
		public @S(20) CSharp_Variable_Definition variable;
		public @S(30) @OPT CSharp_ForTypeInit equalsInit;
	}
	
	public static class CSharp_ForWithoutType extends TokenSequence
	{
		public @S(10) CSharp_Variable_Definition variable;
		public @S(20) CSharp_ForTypeInit equalsInit;
	}
	
	public static class CSharp_ForTypeInit extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) CSharp_Expression initialExpr;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		CSharp_ForWhat forWhat = initial.first();
		if (forWhat.getWhich() instanceof CSharp_ForWithType)
		{
			CSharp_ForWithType whatforWith = (CSharp_ForWithType) forWhat.getWhich();
			EagleValue init = interpreter.getEagleValue(whatforWith.equalsInit.initialExpr);
			interpreter.setSymbol(whatforWith.variable, whatforWith.variable.getValue(), init);
		}
		else if (forWhat.getWhich() instanceof CSharp_ForWithoutType)
		{
			CSharp_ForWithoutType whatforWithout = (CSharp_ForWithoutType) forWhat.getWhich();
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
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		if (this.initial.getPrimaryCount() == 1)
		{
			String varName = null;
			CSharp_Expression forInit = null;
			CSharp_ForWhat what = this.initial.first();
			if (what.getWhich() instanceof CSharp_ForWithType)
			{
				CSharp_ForWithType withType = (CSharp_ForWithType) what.getWhich();
				varName = withType.variable.getValue();
				forInit = withType.equalsInit.initialExpr;
			}
			else if (what.getWhich() instanceof CSharp_ForWithoutType)
			{
				CSharp_ForWithoutType withoutType = (CSharp_ForWithoutType) what.getWhich();
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
	
	public CSharp_Statement generateForLoop1(CSharp_Expression initExpression,
			CSharp_Expression condExpression, CSharp_Expression incrExpression,
			CSharp_Statement act, AbstractToken source)
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
	
	public CSharp_Statement generateForLoop(CSharp_Expression initExpression,
			CSharp_Expression condExpression, CSharp_Expression incrExpression,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_StatementBlock block = new CSharp_StatementBlock();
		CSharp_Statement stmt = block.generateBlock(actions, source);
		return generateForLoop1(initExpression, condExpression, incrExpression,
				stmt, source);
	}
	
	public CSharp_Statement generateForRange1(CSharp_Variable var, TypeEnum type,
			CSharp_Expression fromExpression, RelationalEnum relOp, CSharp_Expression toExpression,
			CSharp_Expression delta, CSharp_Statement act, AbstractToken source)
	{
		SeparatedList<CSharp_ForWhat, PunctuationComma> initializer = new SeparatedList<CSharp_ForWhat, PunctuationComma>();
		CSharp_ForWhat forWhat = new CSharp_ForWhat();
		forWhat.setPresent(true);
		if (type == TypeEnum.INTEGER)
		{
			CSharp_ForWithType withType = new CSharp_ForWithType();
			withType.varType = CSharp_Type.newPrimitiveType("int");
			withType.variable = new CSharp_Variable_Definition();
			String varName = var.firstId.getWhich().toString();
			withType.variable.setValue(varName);
			withType.equalsInit = new CSharp_ForTypeInit();
			withType.equalsInit.equals = new PunctuationEquals();
			withType.equalsInit.initialExpr = fromExpression;
			withType.equalsInit.setPresent(true);
			forWhat.setWhich(withType);
		}
		else
		{
			CSharp_AssignmentExpression asgExpr = new CSharp_AssignmentExpression();
			asgExpr.generateAssignment(var, null, AssignmentEnum.EQUALS, fromExpression, fromExpression);
			forWhat.setWhich(CSharp_Generator.wrapExpression(asgExpr));
		}
		initializer.addPrimaryElement(forWhat);

		SeparatedList<CSharp_Expression, PunctuationComma> loopIncrements = new SeparatedList<CSharp_Expression, PunctuationComma>();
		CSharp_Expression loopIncr;
		if (delta == null)
		{
			CSharp_PostIncrementExpression postExpr = new CSharp_PostIncrementExpression();
			loopIncr = postExpr.generateIncrement(var, IncrementEnum.INCREMENT, source);
		}
		else
		{
			AbstractToken whichDelta = delta.getWhich();
			if (! (whichDelta instanceof CSharp_Number))
			{
				throw new RuntimeException("Can only handle simple loop increments: " + whichDelta);
			}
			
			CSharp_AssignmentExpression asgExp2 = new CSharp_AssignmentExpression();
			loopIncr = asgExp2.generateAssignment(var, null, AssignmentEnum.PLUS_EQUALS, delta, source);
		}
		loopIncrements.addPrimaryElement(loopIncr);

		CSharp_VariableExpression tempVar = new CSharp_VariableExpression();
		tempVar.variable = var;
		CSharp_Expression varExpr = CSharp_Generator.wrapExpression(tempVar);
		
		CSharp_RelationalExpression relExpr = new CSharp_RelationalExpression();
		relExpr.generateRelational(null, varExpr, relOp, toExpression, toExpression);
		CSharp_Expression untilCondition = CSharp_Generator.wrapExpression(relExpr);
		CSharp_Expression loopTest = untilCondition;
				
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
	
	public CSharp_Statement generateForRange(CSharp_Variable var, TypeEnum type,
			CSharp_Expression fromExpression, RelationalEnum relOper, CSharp_Expression toExpression,
			CSharp_Expression delta, ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_StatementBlock block = new CSharp_StatementBlock();
		block.leftBrace = new PunctuationLeftBrace();
		block.rightBrace = new PunctuationRightBrace();
		block.statements = new TokenList<CSharp_StatementOrComment>();
		for (CSharp_Statement stmt : actions)
		{
			CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
			stmtOrComment.setWhich(stmt);
			block.statements.addToken(stmtOrComment);
		}
		
		return generateForRange1(var, type, fromExpression, relOper, toExpression,
				delta, CSharp_Generator.wrapStatement(block), source);
	}
}

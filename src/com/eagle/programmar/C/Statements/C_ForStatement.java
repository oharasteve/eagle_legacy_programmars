// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.C.C_Assignment;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_ForStatement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @DOC("#The-for-Statement") C_Keyword FOR = new C_Keyword("for");
	public @S(20) C_ForLoopBody body;
	public @S(30) C_Statement action;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class C_ForLoopBody extends TokenChooser
	{
		public @CHOICE C_ForLoopStatement loopStatement;
		public @CHOICE C_ForCollectionStatement collectionStatement;
	}
	
	public static class C_ForLoopStatement extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT C_ForLoopVariable loopVar;
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
			public @FIRST C_ForLoopVariableWithType withType;
			public @CHOICE C_ForLoopVariableNoType noType;
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

	public static class C_ForLoopVariableWithType extends TokenSequence
	{
		public @S(10) C_Type varType;
		public @S(20) C_Assignment assignment;
	}

	public static class C_ForLoopVariableNoType extends TokenSequence
	{
		public @S(10) C_Assignment assignment;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (body.getWhich() instanceof C_ForLoopStatement)
		{
			C_ForLoopStatement what = (C_ForLoopStatement) body.getWhich();
			
			AbstractToken which = what.loopVar.getWhich();
			C_Assignment asg;
			if (which instanceof C_ForLoopVariableWithType)
			{
				C_ForLoopVariableWithType token = (C_ForLoopVariableWithType) which;
				asg = token.assignment;
			}
			else if (which instanceof C_ForLoopVariableNoType)
			{
				C_ForLoopVariableNoType token = (C_ForLoopVariableNoType) which;
				asg = token.assignment;
			}
			else throw new RuntimeException("Cannot handle " + which);
				
			interpreter.tryToInterpret(asg);
	
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(getFileName(), getStartLine(), getStartChar());
			}
			ForLoopMetric metric = new ForLoopMetric();
	
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean keepGoing = interpreter.getBoolValue(what.terminateCondition);
				if (! keepGoing) break;
				
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
				
				interpreter.tryToInterpret(what.increment);
			}
			
			_metrics.competedLoop(metric);
			return result;
		}
		
		throw new RuntimeException("Unexpected for loop construct: " + body.getWhich());
	}
}

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Javascript.Javascript_Data.Javascript_More_Variables;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ForStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("js_loop_for.asp") Javascript_Keyword FOR = new Javascript_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Javascript_ForWhat forWhat;
	
	private @SKIP ForLoopMetrics _metrics = null;

	public static class  Javascript_ForWhat extends TokenChooser
	{
		public @CHOICE Javascript_ForLoopStatement forLoop;
		public @CHOICE Javascript_ForCollectionStatement forCollection;
	}
	
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
		public @S(110) PunctuationRightParen rightParen;
		public @S(120) @OPT TokenList<Javascript_Comment> comments;
		public @S(130) Javascript_Statement action;
		
		public static class Javascript_ForLoopVariable extends TokenChooser
		{
			public @FIRST Javascript_ForLoopVariableWithType withType;
			public @CHOICE Javascript_ForLoopVariableNoType noType;
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

	public static class Javascript_ForCollectionStatement extends TokenSequence
	{
		public @S(10) @OPT Javascript_Type varType;
		public @S(20) @OPT Javascript_Variable forVar; // The Javascript_Type steals it ...
		public @S(30) @OPT Javascript_ForVariables forVars;
		public @S(40) Javascript_InOrColon inOrColon;
		public @S(50) Javascript_Expression collection;
		public @S(60) PunctuationRightParen rightParen;
		public @S(70) @OPT TokenList<Javascript_Comment> comments;
		public @S(80) Javascript_Statement action;

		public static class Javascript_ForVariables extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) SeparatedList<Javascript_Variable_Definition, PunctuationComma> vars;
			public @S(30) PunctuationRightBracket rightBracket;
		}

		public static class Javascript_InOrColon extends TokenChooser
		{
			public @CHOICE PunctuationColon colon;
			public @CHOICE Javascript_KeywordChoice IN = new Javascript_KeywordChoice("in", "of");
		}
	}
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (forWhat.getWhich() instanceof Javascript_ForLoopStatement)
		{
			Javascript_ForLoopStatement forLoop = (Javascript_ForLoopStatement) forWhat.getWhich();

			AbstractToken which = forLoop.loopVar.getWhich();
			
			Javascript_Variable forVar;
			if (which instanceof Javascript_ForLoopVariableWithType)
			{
				forVar = ((Javascript_ForLoopVariableWithType) which).forVar;
			}
			else
			{
				forVar = ((Javascript_ForLoopVariableNoType) which).forVar;
			}
			
			if (forVar.firstId.getWhich() instanceof Javascript_Identifier_Reference)
			{
				Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) forVar.firstId.getWhich();
				EagleValue init = interpreter.getEagleValue(forLoop.initialize);
				interpreter._symbolTable.setSymbol(this.getFileName(), this.getStartLine(), this.getStartChar(), id.getValue(), init);
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				boolean keepGoing = interpreter.getBoolValue(forLoop.terminateCondition);
				if (!keepGoing) break;

				metric.iterate();
				result = interpreter.tryToInterpret(forLoop.action);
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

			_metrics.competedLoop(metric);
			return result;
		}

		throw new RuntimeException("Unexpected for loop construct: " + forWhat.getWhich());
	}
}
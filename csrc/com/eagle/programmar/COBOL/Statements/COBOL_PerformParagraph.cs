// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

namespace com.eagle.programmar.COBOL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using COBOL_Paragraph = com.eagle.programmar.COBOL.COBOL_Paragraph;
	using COBOL_SentenceOrComment = com.eagle.programmar.COBOL.COBOL_Paragraph.COBOL_SentenceOrComment;
	using COBOL_Variable = com.eagle.programmar.COBOL.COBOL_Variable;
	using COBOL_PerformUntil = com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformUntil;
	using COBOL_PerformVarying = com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
	using COBOL_Paragraph_or_Section_Thru = com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement.COBOL_Paragraph_or_Section_Thru;
	using COBOL_PerformTestWhen = com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement.COBOL_PerformTestWhen;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_PerformParagraph : TokenSequence, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference performStartParagraph;
		public COBOL_Identifier_Reference performStartParagraph;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Paragraph_or_Section_Thru performThrough;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_PerformTestWhen testWhen;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<COBOL_PerformClause> clauseList;
		public  OPT;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if ((performThrough != null && performThrough.isPresent()) || (testWhen != null && testWhen.isPresent()))
			{
				throw new Exception("Can only PERFORM one paragraph right now");
			}

			string startPara = performStartParagraph.getValue();

			// Have to search for the PARAGRAPH definition
			AbstractFunction fn = interpreter.findFunction(startPara);
			if (fn == null)
			{
				throw new Exception("Unable to find a Paragraph named " + startPara);
			}
			COBOL_Paragraph paragraph = (COBOL_Paragraph) fn;

			interpreter.callingFunction(startPara, interpreter._lang);

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// Evaluate the paragraph
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (COBOL_Paragraph.COBOL_SentenceOrComment sentence in paragraph.sentences._elements)
			{
				result = interpreter.tryToInterpret(sentence);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

			long elapsedTime = System.nanoTime() - startTime;

			if (paragraph._callMetrics == null)
			{
				paragraph._callMetrics = new CallMetrics(interpreter._metrics, startPara, paragraph.paragraphHeaders.first().paragraphName);
			}
			paragraph._callMetrics.addCallFrom(this, elapsedTime);

			// Remove parameter values (none really)
			interpreter.completedFunction(startPara, null);

			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (performThrough != null && performThrough.isPresent())
			{
				throw new Exception("Cannot handle PERFORM multiple paragraphs yet " + this);
			}

			string indexVar = null;
			AbstractExpression initExpr = null;
			AbstractExpression incrExpr = null;
			AbstractExpression whileExpr = null;

			TokenList<COBOL_PerformClause> clauses = this.clauseList;
			if (clauses != null)
			{
				foreach (COBOL_PerformClause clause in clauses._elements)
				{
					AbstractToken which = clause.getWhich();
					if (which is COBOL_PerformVarying)
					{
						COBOL_PerformVarying varying = (COBOL_PerformVarying) which;
						indexVar = varying.id.getValue();
						AbstractExpression fromExpr = transformer.transformExpression(generator, varying.from);
						initExpr = generator.newAssignmentExpression(indexVar, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, which);
						AbstractExpression byExpr = transformer.transformExpression(generator, varying.by);
						incrExpr = generator.newAssignmentExpression(indexVar, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, byExpr, which);
					}
					else if (which is COBOL_PerformUntil)
					{
						COBOL_PerformUntil until = (COBOL_PerformUntil) which;
						AbstractExpression untilExpr = transformer.transformExpression(generator, until.condition);
						whileExpr = generator.newLogicalNotExpression(untilExpr, which);
					}
				}
			}

			AbstractVariable para = generator.newVariable(COBOL_Variable.repairName(performStartParagraph.getValue()));
			AbstractExpression expr = generator.newMethodInvocation(para, null, this);
			AbstractStatement stmt = generator.newExpressionStatement(expr, this);

			// Four cases: both varying and while; just varying; just while; neither
			if (initExpr != null)
			{
				return generator.newForLoopStatement1(initExpr, whileExpr, incrExpr, stmt, this);
			}
			if (whileExpr == null)
			{
				AbstractExpression callExpr = generator.newMethodInvocation(para, null, this);
				return generator.newExpressionStatement(callExpr, this);
			}
			return generator.newWhileStatement1(whileExpr, stmt, this);
		}
	}
}

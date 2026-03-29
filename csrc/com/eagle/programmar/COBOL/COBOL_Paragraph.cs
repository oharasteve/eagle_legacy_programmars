// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.COBOL
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using COBOL_ScreenDeclaration = com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
	using COBOL_CopyOrDataDeclaration = com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
	using COBOL_Paragraph_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_Paragraph : TokenSequence, EagleRunnable, AbstractFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<COBOL_ParagraphHeader> paragraphHeaders;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_SentenceOrComment> sentences;
		public TokenList<COBOL_SentenceOrComment> sentences;

		public class COBOL_SentenceOrComment : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Comment XXcomment;
			public COBOL_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Sentence XXsentence;
			public COBOL_Sentence XXsentence;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST COBOL_ScreenDeclaration XXscreen;
			public COBOL_ScreenDeclaration XXscreen;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class COBOL_DataInParagraph extends com.eagle.tokens.TokenSequence
			public class COBOL_DataInParagraph : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration> data;
				public TokenList<COBOL_CopyOrDataDeclaration> data;
			}
		}

		public class COBOL_ParagraphHeader : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition paragraphName;
			public COBOL_Paragraph_Definition paragraphName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;

		public override void interpret(EagleInterpreter interpreter)
		{
			foreach (COBOL_SentenceOrComment sentence in sentences._elements)
			{
				interpreter.tryToInterpret(sentence);
			}
		}

		public virtual void transform(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string paraName = "paragraph_with_no_name";
			foreach (COBOL_ParagraphHeader header in paragraphHeaders._elements)
			{
				paraName = COBOL_Variable.repairName(header.paragraphName.getValue());
			}
			if (!paraName.Equals("main"))
			{
				generator.addMethod(null, paraName, paragraphHeaders);
			}

			findGlobalVariables(transformer, generator);

			foreach (COBOL_SentenceOrComment sentOrComm in sentences._elements)
			{
				if (sentOrComm.getWhich() is COBOL_Sentence)
				{
					COBOL_Sentence sent = (COBOL_Sentence) sentOrComm.getWhich();
					sent.transform(transformer, generator);
				}
			}

			if (!paraName.Equals("main"))
			{
				generator.doneMethod();
			}
		}

		private void findGlobalVariables(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Why isn't there a pointer up to the COBOL_Program at the top of the tree?
			AbstractToken parent = this.getParent();
			COBOL_Program_Complete prog = null;
			while (parent != null)
			{
				if (parent is COBOL_Program_Complete)
				{
					prog = (COBOL_Program_Complete) parent;
					break;
				}
				parent = parent.getParent();
			}
			if (prog == null)
			{
				return;
			}

			HashSet<string> added = new HashSet<string>();
			foreach (AssignMetrics var in transformer._metrics.findAllAssignments())
			{
				int line = var._startingLine;
				// System.err.println("****** " + line + " " + this._currentLine + "-" + this._endLine);
				if (line >= this._currentLine && line <= this._endLine)
				{
					string varName = var._symbolName;
					if (!added.Contains(varName))
					{
						AbstractStatement newStmt = generator.newGlobalVariable(varName, null);
						generator.addStatement(newStmt, null);
						added.Add(varName);
					}
				}
			}
		}
	}
}

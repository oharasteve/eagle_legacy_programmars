// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

namespace com.eagle.programmar.COBOL
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using COBOL_DataSection = com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_DataSection;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public abstract class COBOL_Program_Complete : COBOL_Program, EagleRunnable, EagleTransformableProgram
	{
		// Components of a complete COBOL Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<COBOL_Directive> directives;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_SpecialNames specialNames;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_IdentificationDivision identificationDiv;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_EnvironmentDivision environmentDiv;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments3;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT COBOL_DataDivision dataDiv;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) COBOL_ProcedureDivision procedureDiv;
		public COBOL_ProcedureDivision procedureDiv;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<COBOL_Program_Free_Format> nestedPrograms;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT COBOL_EndProgram endProgram;
		public  OPT;

		public class COBOL_EndProgram : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword END = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END");
			public COBOL_Keyword END = new COBOL_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PROGRAM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PROGRAM");
			public COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference programId;
			public COBOL_Identifier_Reference programId;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, COBOL_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, COBOL_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public COBOL_Program_Complete(string name, COBOL_Syntax syntax) : base(name, syntax)
		{
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// Pass 1 : Collect all the variables in Working Storage
			collectDataVariables(interpreter);

			// Pass 2 : Collect all the paragraph names
			collectParagraphNames(interpreter);

			// Pass 3 -- now run it
			interpreter.callingFunction("main", this);
			interpreter.tryToInterpret(procedureDiv);
			interpreter.completedFunction("main", this);
		}

		private void collectDataVariables(EagleInterpreter interpreter)
		{
			foreach (COBOL_DataSection section in dataDiv.sections._elements)
			{
				interpreter.tryToInterpret(section);
			}
		}

		private void collectParagraphNames(EagleInterpreter interpreter)
		{
			foreach (COBOL_Section section in procedureDiv.sections._elements)
			{
				foreach (COBOL_Paragraph paragraph in section.paragraphs._elements)
				{
					if (paragraph.paragraphHeaders != null && paragraph.paragraphHeaders._elements != null && paragraph.paragraphHeaders._elements.size() > 0)
					{
						string paragraphName = paragraph.paragraphHeaders._elements.get(0).paragraphName.getValue();
						interpreter.addFunction(paragraphName, paragraph);
					}
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (dataDiv != null && dataDiv.isPresent())
			{
				dataDiv.transform(transformer, generator);
			}
			procedureDiv.transform(transformer, generator);
			return generator.getTransfomedProgram();
		}
	}

}

// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.Julia
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using Julia_Function = com.eagle.programmar.Julia.Statements.Julia_Function;
	using Julia_Comment = com.eagle.programmar.Julia.Terminals.Julia_Comment;
	using Julia_EOLN = com.eagle.programmar.Julia.Terminals.Julia_EOLN;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Julia_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string JULIA = "Julia";

		public Julia_Program() : base(JULIA, new Julia_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://docs.julialang.org/en/v1/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Julia_Element> elements;
		public TokenList<Julia_Element> elements;

		public class Julia_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_CommentEoln XXcomment;
			public Julia_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_Statement XXstmt;
			public Julia_Statement XXstmt;
		}

		public class Julia_CommentEoln : TokenSequence, EagleRunnable, EagleTransformableStatement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Julia.Terminals.Julia_Comment comment;
			public Julia_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Terminals.Julia_EOLN eoln;
			public Julia_EOLN eoln;

			public override void interpret(EagleInterpreter interpreter)
			{
				// Nothing to do here
			}

			public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				return null; // Might want to keep comment statements somehow.
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Julia_Element elt in elements._elements)
			{
				AbstractToken which1 = elt.getWhich();
				if (which1 is Julia_Statement)
				{
					Julia_Statement stmt = (Julia_Statement) which1;
					AbstractToken which2 = stmt.getWhich();
					if (which2 is Julia_Function)
					{
						Julia_Function fn = (Julia_Function) which2;
						interpreter.addFunction(fn.id.getValue(), fn);
					}
				}
			}

			// Second pass, execute the program
			foreach (Julia_Element elt in elements._elements)
			{
				AbstractToken which = elt.getWhich();
				if (which is Julia_Statement)
				{
					Julia_Statement stmt = (Julia_Statement) which;
					interpreter.tryToInterpret(stmt);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Function definitions
			foreach (Julia_Element elt in elements._elements)
			{
				AbstractToken which1 = elt.getWhich();
				if (which1 is Julia_Statement)
				{
					Julia_Statement stmt = (Julia_Statement) which1;
					AbstractToken which2 = stmt.getWhich();
					if (which2 is Julia_Function)
					{
						Julia_Function func = (Julia_Function) which2;
						func.transformFunction(transformer, generator);
					}
				}
			}

			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typE = met.uniqueType();
				if (typE != EagleGenerator.TypeEnum.VOID)
				{
					AbstractType abstrType = generator.transformType(typE, null, this);

					AbstractExpression initExpr = null;
					if (typE == EagleGenerator.TypeEnum.HASH)
					{
						// Need to create an empty hashmap
						initExpr = generator.newClassCreation(abstrType, null, this);
					}

					// System.err.println("****** Found var " + met._symbolName);
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, initExpr, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// Second pass, transform all the data and logic
			foreach (Julia_Element elt in elements._elements)
			{
				AbstractToken which2 = elt.getWhich();
				if (which2 is Julia_Statement)
				{
					Julia_Statement stmt = (Julia_Statement) which2;
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}
}

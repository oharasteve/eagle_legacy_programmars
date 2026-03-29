// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rexx
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using Rexx_Function = com.eagle.programmar.Rexx.Statements.Rexx_Function;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string REXX = "Rexx";

		public Rexx_Program() : base(REXX, new Rexx_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://www.ibm.com/docs/en/cics-ts/6.x?topic=";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Rexx_TopElement> elements;
		public TokenList<Rexx_TopElement> elements;

		public class Rexx_TopElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_Element XXelement;
			public Rexx_Element XXelement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_Function XXfunction;
			public Rexx_Function XXfunction;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the Function and Sub definitions
			foreach (Rexx_TopElement elt in elements._elements)
			{
				AbstractToken which = elt.getWhich();
				if (which is Rexx_Function)
				{
					Rexx_Function func = (Rexx_Function) which;
					interpreter.addFunction(func.id.getValue(), func);
					interpreter.tryToInterpret(func); // Initialize metrics because functions *follow* main()
				}
			}

			// Second pass, run any stuff in the outermost 'object'
			foreach (Rexx_TopElement elt in elements._elements)
			{
				interpreter.tryToInterpret(elt);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Function definitions
			foreach (Rexx_TopElement stmt in elements._elements)
			{
				AbstractToken which1 = stmt.getWhich();
				if (which1 is Rexx_Function)
				{
					Rexx_Function func = (Rexx_Function) which1;
					func.transformFunction(transformer, generator);
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
			foreach (Rexx_TopElement stmt in elements._elements)
			{
				AbstractToken which2 = stmt.getWhich();
				if (which2 is Rexx_Element)
				{
					Rexx_Element elt = (Rexx_Element) which2;
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, elt.baseStatement.getWhich());
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, elt);
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}
}

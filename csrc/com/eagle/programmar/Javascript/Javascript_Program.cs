// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Javascript_Function_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Javascript_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string JAVASCRIPT = "Javascript";

		public Javascript_Program() : base(JAVASCRIPT, new Javascript_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.w3schools.com/js/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Javascript_TopElement> elements;
		public  OPT;

		public class Javascript_TopElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Comment XXcomment;
			public Javascript_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Function XXfunction;
			public Javascript_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Statement XXstatement;
			public Javascript_Statement XXstatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Class XXclass;
			public Javascript_Class XXclass;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the method definitions
			foreach (Javascript_TopElement element in elements._elements)
			{
				if (element.getWhich() is Javascript_Function)
				{
					Javascript_Function func = (Javascript_Function) element.getWhich();
					Javascript_Function_Definition functionName = func.implementation.id;
					if (functionName != null && functionName.isPresent())
					{
						interpreter.addFunction(functionName.getValue(), func);
					}
				}
			}

			// Second pass, run everything
			foreach (Javascript_TopElement element in elements._elements)
			{
				interpreter.tryToInterpret(element);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First (and only) pass, transform the main method
			foreach (Javascript_TopElement elt1 in elements._elements)
			{
				AbstractToken which1 = elt1.getWhich();
				if (which1 is EagleTransformableFunction)
				{
					EagleTransformableFunction transformable = (EagleTransformableFunction) which1;
					transformable.transformFunction(transformer, generator);
				}
				else if (which1 is Javascript_Statement)
				{
					Javascript_Statement stmt1 = (Javascript_Statement) which1;
					AbstractToken which2 = stmt1.getWhich();
					if (which2 is Javascript_Data)
					{
						Javascript_Data data = (Javascript_Data) which2;
						List<AbstractStatement> stmts3 = data.transformStaticData(true, transformer, generator);
						foreach (AbstractStatement stmt3 in stmts3)
						{
							generator.addStatement(stmt3, elt1);
						}
					}
					else
					{
						List<AbstractStatement> stmts = transformer.transformStatement(generator, stmt1.getWhich());
						if (stmts != null)
						{
							foreach (AbstractStatement stmt4 in stmts)
							{
								generator.addStatement(stmt4, which2);
							}
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}

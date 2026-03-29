// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.Ada
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Ada_Function = com.eagle.programmar.Ada.Statements.Ada_Function;
	using Ada_Procedure = com.eagle.programmar.Ada.Statements.Ada_Procedure;
	using Ada_Comment = com.eagle.programmar.Ada.Terminals.Ada_Comment;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string ADA = "Ada";

		public Ada_Program() : base(ADA, new Ada_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://www.adaic.org/resources/add_content/standards/05rm/RM-Final.pdf";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Ada_Element> elements;
		public TokenList<Ada_Element> elements;

		public class Ada_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Comment XXcomment;
			public Ada_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Statement XXstmt;
			public Ada_Statement XXstmt;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Ada_Element element in elements._elements)
			{
				AbstractToken whichElt = element.getWhich();
				if (whichElt is Ada_Statement)
				{
					Ada_Statement statement = (Ada_Statement) whichElt;
					AbstractToken whichStmt = statement.getWhich();
					if (whichStmt is Ada_Procedure)
					{
						Ada_Procedure proc = (Ada_Procedure) whichStmt;
						foreach (Ada_Statement stmt in proc.statements1._elements)
						{
							AbstractToken which = stmt.getWhich();
							if (which is Ada_Function)
							{
								Ada_Function fn = (Ada_Function) which;
								interpreter.addFunction(fn.id.getValue(), fn);
							}
							else if (which is Ada_Procedure)
							{
								Ada_Procedure pr = (Ada_Procedure) which;
								interpreter.addFunction(pr.id.getValue(), pr);
							}
						}
					}
				}
			}

			// Second pass, execute the program
			foreach (Ada_Element element in elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is Ada_Statement)
				{
					Ada_Statement stmt = (Ada_Statement) which;
					interpreter.tryToInterpret(stmt);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Transform all the Function definitions and global data
			foreach (Ada_Element elt in elements._elements)
			{
				AbstractToken whichElt = elt.getWhich();
				if (whichElt is Ada_Statement)
				{
					Ada_Statement stmt = (Ada_Statement) whichElt;
					AbstractToken whichStmt = stmt.getWhich();
					if (whichStmt is Ada_Procedure)
					{
						// Main procedure, skip headers and such.
						// Just transform all the data & funcs & procs inside the main proc
						Ada_Procedure proc = (Ada_Procedure) whichStmt;
						proc.transformBody(transformer, generator);
					}
				}
				else
				{
					// Ignore Comments and 'with' statementsOS
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}

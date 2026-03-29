// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.Scala
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Scala_Function = com.eagle.programmar.Scala.Statements.Scala_Function;
	using Scala_Import = com.eagle.programmar.Scala.Statements.Scala_Import;
	using Scala_Object = com.eagle.programmar.Scala.Statements.Scala_Object;
	using Scala_Package = com.eagle.programmar.Scala.Statements.Scala_Package;
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

	public class Scala_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string SCALA = "Scala";

		public Scala_Program() : base(SCALA, new Scala_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://docs.scala-lang.org/scala3/book/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Scala_Element> elements;
		public TokenList<Scala_Element> elements;

		public class Scala_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_CommentEoln XXcomment;
			public Scala_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_Import XXimport;
			public Scala_Import XXimport;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_Object XXobject;
			public Scala_Object XXobject;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_Package XXpkg;
			public Scala_Package XXpkg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Scala_Statement XXstmt;
			public Scala_Statement XXstmt;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the method definitions
			foreach (Scala_Element elt in elements._elements)
			{
				if (elt.getWhich() is Scala_Object)
				{
					Scala_Object obj = (Scala_Object) elt.getWhich();
					foreach (Scala_Statement stmt in obj.statement.statements._elements)
					{
						if (stmt.getWhich() is Scala_Function)
						{
							Scala_Function func = (Scala_Function) stmt.getWhich();
							interpreter.addFunction(func.id.getValue(), func);
						}
					}
				}
			}

			// Second pass, run any stuff in the outermost 'object'
			foreach (Scala_Element elt in elements._elements)
			{
				if (elt.getWhich() is Scala_Object)
				{
					Scala_Object obj = (Scala_Object) elt.getWhich();
					foreach (Scala_Statement stmt in obj.statement.statements._elements)
					{
						interpreter.tryToInterpret(stmt);
					}
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			bool needCallToMain = false;

			// First pass, transform all the function definitions
			foreach (Scala_Element elt in elements._elements)
			{
				if (elt.getWhich() is Scala_Object)
				{
					Scala_Object obj = (Scala_Object) elt.getWhich();
					foreach (Scala_Statement stmt in obj.statement.statements._elements)
					{
						if (stmt.getWhich() is EagleTransformableFunction)
						{
							EagleTransformableFunction transformable = (EagleTransformableFunction) stmt.getWhich();
							transformable.transformFunction(transformer, generator);

							// Expressions and Statements need this for Python
							// Romanamor does not. C# and Java do not.
							// Not really sure when / why this is needed, but it works.
							if (transformable is Scala_Function)
							{
								Scala_Function func = (Scala_Function) transformable;
								if (func.id.getValue().Equals("main"))
								{
									needCallToMain = true;
								}
							}
						}
					}
				}
			}

			// Second pass, transform all the data and logic
			foreach (Scala_Element elt in elements._elements)
			{
				if (elt.getWhich() is Scala_Object)
				{
					Scala_Object obj = (Scala_Object) elt.getWhich();
					foreach (Scala_Statement stmt in obj.statement.statements._elements)
					{
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
			}

			// Not needed for C# or Java, but Python needs this (unless there was an 'object' line)
			if (needCallToMain)
			{
				generator.addCallToMain();
			}

			return generator.getTransfomedProgram();
		}
	}

}

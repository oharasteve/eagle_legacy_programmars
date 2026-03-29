// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using C_Function_TypeAndName = com.eagle.programmar.C.C_Function.C_Function_TypeAndName;
	using C_AsmVolatile = com.eagle.programmar.C.Statements.C_AsmVolatile;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using CMacro_StatementOrComment = com.eagle.programmar.CMacro.CMacro_StatementOrComment;
	using CMacro_Syntax = com.eagle.programmar.CMacro.CMacro_Syntax;
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

	public class C_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string C = "C";

		public C_Program() : base(C, new C_Syntax())
		{
		}

		// Called from C++ constructor
		public C_Program(string name, EagleSyntax syntax) : base(name, syntax)
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.gnu.org/s/gnu-c-manual/gnu-c-manual.html";
			}
		}

		private static string[] primitives = new string[] {"auto", "bool", "char", "double", "float", "int", "long", "short", "void"};

		// Careful, this gets added to in some projects
		private static string[] modifiers = new string[] {"const", "constexpr", "extern", "final", "inline", "mutable", "register", "static", "virtual", "volatile", "__inline"};

		public static void addPrimitive(string primitive)
		{
			// Append to an array
			int n = primitives.Length;
			for (int i = 0; i < n; i++)
			{
				if (primitives[i].Equals(primitive))
				{
					return;
				}
			}

			string[] temp = new string[n + 1];
			for (int i = 0; i < n; i++)
			{
				temp[i] = primitives[i];
			}
			temp[n] = primitive;
			primitives = temp;
		}

		public static void addModifier(string modifier)
		{
			// Append to an array
			int n = modifiers.Length;
			for (int i = 0; i < n; i++)
			{
				if (modifiers[i].Equals(modifier))
				{
					return;
				}
			}

			string[] temp = new string[n + 1];
			for (int i = 0; i < n; i++)
			{
				temp[i] = modifiers[i];
			}
			temp[n] = modifier;
			modifiers = temp;
		}

		public static string[] Primitives
		{
			get
			{
				return primitives;
			}
		}

		public static string[] Modifiers
		{
			get
			{
				return modifiers;
			}
		}

		// Note that CPlus_Program has an S(9) and ObjectiveC_Program has an S(8)
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<C_StatementOrComment> elements;
		public  OPT;

		public class C_StatementOrComment : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Comment XXcomment;
			public C_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_TypeDef XXtypeDef;
			public C_TypeDef XXtypeDef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_Data XXdata;
			public C_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Function XXfunction;
			public C_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_Statement XXstatement;
			public C_Statement XXstatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Enum XXenum;
			public C_Enum XXenum;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_AsmVolatile XXasmVolatile;
			public C_AsmVolatile XXasmVolatile;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.CMacro.CMacro_Syntax.class) com.eagle.programmar.CMacro.CMacro_StatementOrComment XXmacro;
			public @SYNTAX(typeof(CMacro_Syntax)) CMacro_StatementOrComment XXmacro;

			// NOTE: C++ adds the 'extern' statement here. See the constructor in
			// CPlus_Program.java
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the function definitions
			foreach (C_StatementOrComment elt in elements._elements)
			{
				AbstractToken which = elt.getWhich();
				if (which is C_Function)
				{
					C_Function fn = (C_Function) which;
					{
						which = fn.typeName.getWhich();
						if (which is C_Function_TypeAndName)
						{
							C_Function_TypeAndName typeName = (C_Function_TypeAndName) which;
							interpreter.addFunction(typeName.functionName.getValue(), fn);
						}
					}
				}
			}

			// Second pass, execute the program
			foreach (C_StatementOrComment element in elements._elements)
			{
				interpreter.tryToInterpret(element);
			}
		}

		public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Function definitions
			foreach (C_StatementOrComment elt in elements._elements)
			{
				AbstractToken which1 = elt.getWhich();
				if (which1 is C_Function)
				{
					C_Function func = (C_Function) which1;
					func.transformFunction(transformer, generator);
				}
			}

			// Second pass, transform all the data and logic
			foreach (C_StatementOrComment element in elements._elements)
			{
				AbstractToken which3 = element.getWhich();
				if (which3 is C_Statement)
				{
					C_Statement stmt = (C_Statement) which3;
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
				else if (which3 is C_Data)
				{
					C_Data data = (C_Data) which3;
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, data.getWhich());
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, data);
						}
					}
				}
			}

			// Not needed for C# or CSharp, but Python needs this
			generator.addCallToMain();

			return generator.getTransfomedProgram();
		}
	}

}

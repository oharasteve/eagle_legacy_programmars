// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

namespace com.eagle.programmar.CSharp
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_ClassElement = com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
	using CSharp_Directive = com.eagle.programmar.CSharp.Directives.CSharp_Directive;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
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

	public class CSharp_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string CSHARP = "CSharp";

		public CSharp_Program() : base(CSHARP, new CSharp_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://learn.microsoft.com/en-us/dotnet/csharp/language-reference/";
			}
		}

		public static readonly string[] MODIFIERS = new string[] {"abstract", "async", "const", "delegate", "event", "extern", "final", "internal", "lock", "override", "partial", "private", "protected", "public", "readonly", "ref", "sealed", "static", "unsafe", "virtual"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @BLANKLINE TokenList<CSharp_NamespaceOrClassEntry> myClasses;
		public  OPT;

		public class CSharp_NamespaceOrClassEntry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Using XXimportList;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Comment XXcomment;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Namespace XXmyNamespace;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Class XXclass;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Annotation XXannotation;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Directive XXdirective;
			public  NEWLINE;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the method definitions
			foreach (CSharp_NamespaceOrClassEntry nsClass in myClasses._elements)
			{
				if (nsClass.getWhich() is CSharp_Class)
				{
					CSharp_Class cls = (CSharp_Class) nsClass.getWhich();
					foreach (CSharp_ClassElement element in cls.elements._elements)
					{
						if (element.getWhich() is CSharp_Method)
						{
							CSharp_Method meth = (CSharp_Method) element.getWhich();
							interpreter.addFunction(meth.id.getValue(), meth);
						}
					}
				}
			}

			// Second pass, run any stuff in the outermost class
			foreach (CSharp_NamespaceOrClassEntry nsClass in myClasses._elements)
			{
				if (nsClass.getWhich() is CSharp_Class)
				{
					CSharp_Class cls = (CSharp_Class) nsClass.getWhich();
					interpreter.tryToInterpret(cls);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First (and only) pass, transform the main method
			foreach (CSharp_NamespaceOrClassEntry nsClass in myClasses._elements)
			{
				AbstractToken which1 = nsClass.getWhich();
				if (which1 is CSharp_Class)
				{
					CSharp_Class cls = (CSharp_Class) which1;
					foreach (CSharp_ClassElement elt2 in cls.elements._elements)
					{
						AbstractToken which2 = elt2.getWhich();
						if (which2 is EagleTransformableFunction)
						{
							EagleTransformableFunction transformable = (EagleTransformableFunction) which2;
							transformable.transformFunction(transformer, generator);
						}
						else
						{
							// Probably global (class-level) data
							if (which2 is CSharp_Statement)
							{
								CSharp_Statement stmt = (CSharp_Statement) which2;
								AbstractToken which3 = stmt.getWhich();
								if (which3 is CSharp_Data)
								{
									CSharp_Data data = (CSharp_Data) which3;
									AbstractStatement stmt3 = data.transformStaticData(transformer, generator);
									generator.addStatement(stmt3, nsClass);
								}
							}
						}
					}
				}
			}

			// Not needed for C# or CSharp, but Python needs this
			generator.addCallToMain();

			return generator.getTransfomedProgram();
		}

		public virtual void addClass(CSharp_Class cls)
		{
			CSharp_NamespaceOrClassEntry entry = new CSharp_NamespaceOrClassEntry();
			entry.setWhich(cls);

			if (this.myClasses == null)
			{
				this.myClasses = new TokenList<CSharp_NamespaceOrClassEntry>();
				this.myClasses.setPresent(true);
			}
			this.myClasses.addToken(entry);
		}

		public virtual void addComment(CSharp_Comment comment)
		{
			if (this.comments1 == null)
			{
				this.comments1 = new TokenList<CSharp_Comment>();
			}
			this.comments1.addToken(comment);
		}
	}

}

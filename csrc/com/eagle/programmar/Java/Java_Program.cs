// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

namespace com.eagle.programmar.Java
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_ClassElement = com.eagle.programmar.Java.Java_Class.Java_ClassElement;
	using Java_MethodType = com.eagle.programmar.Java.Java_Method.Java_MethodType;
	using Java_Method_Definition = com.eagle.programmar.Java.Symbols.Java_Method_Definition;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string JAVA = "Java";

		public Java_Program() : base(JAVA, new Java_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://docs.oracle.com/javase/specs/jls/se5.0/html/";
			}
		}

		public static readonly string[] MODIFIERS = new string[] {"abstract", "default", "final", "native", "private", "protected", "public", "static", "strictfp", "synchronized", "transient", "volatile"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_Annotation annotation;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Java_Package jpackage;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Java_ImportOrComment> jimportList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<Java_ClassOrEnum> classOrEnumList;
		public  OPT;

		public class Java_ClassOrEnum : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Class XXclass;
			public Java_Class XXclass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Enum XXenum;
			public Java_Enum XXenum;
		}

		public class Java_ImportOrComment : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE Java_Comment XXcomment;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE Java_Import XXimport;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE @CURIOUS("Extra Semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon XXsemicolon;
			public  NEWLINE;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the method definitions
			foreach (Java_ClassOrEnum classOrEnum in classOrEnumList._elements)
			{
				if (classOrEnum.getWhich() is Java_Class)
				{
					Java_Class cls = (Java_Class) classOrEnum.getWhich();
					foreach (Java_ClassElement element in cls.elements._elements)
					{
						if (element.getWhich() is Java_Method)
						{
							Java_Method meth = (Java_Method) element.getWhich();
							AbstractToken which = meth.typeAndName.getWhich();
							if (which is Java_MethodType)
							{
								Java_Method_Definition methodName = ((Java_MethodType) which).methodName;
								interpreter.addFunction(methodName.getValue(), meth);
							}
						}
					}
				}
			}

			// Second pass, run any stuff in the outermost class
			foreach (Java_ClassOrEnum classOrEnum in classOrEnumList._elements)
			{
				if (classOrEnum.getWhich() is Java_Class)
				{
					Java_Class cls = (Java_Class) classOrEnum.getWhich();
					interpreter.tryToInterpret(cls);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First (and only) pass, transform the main method
			foreach (Java_ClassOrEnum elt1 in classOrEnumList._elements)
			{
				AbstractToken which1 = elt1.getWhich();
				if (which1 is Java_Class)
				{
					Java_Class cls = (Java_Class) which1;
					foreach (Java_ClassElement elt2 in cls.elements._elements)
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
							if (which2 is Java_Statement)
							{
								Java_Statement stmt = (Java_Statement) which2;
								AbstractToken which3 = stmt.getWhich();
								if (which3 is Java_Data)
								{
									Java_Data data = (Java_Data) which3;
									AbstractStatement stmt3 = data.transformStaticData(transformer, generator);
									generator.addStatement(stmt3, elt1);
								}
							}
						}
					}
				}
			}

			// Not needed for C# or Java, but Python needs this
			generator.addCallToMain();

			return generator.getTransfomedProgram();
		}

		public static Java_Program newJavaProgram(Java_Class cls, string pkg)
		{
			Java_ClassOrEnum entry = new Java_ClassOrEnum();
			entry.setWhich(cls);

			Java_Program prog = new Java_Program();
			prog.classOrEnumList = new TokenList<Java_ClassOrEnum>();
			prog.classOrEnumList.setPresent(true);
			prog.classOrEnumList.addToken(entry);

			prog.jpackage = Java_Package.newPackage(pkg);
			prog.jpackage.setPresent(true);

			return prog;
		}

		public virtual void addClass(Java_Class cls)
		{
			Java_ClassOrEnum entry = new Java_ClassOrEnum();
			entry.setWhich(cls);

			if (this.classOrEnumList == null)
			{
				this.classOrEnumList = new TokenList<Java_ClassOrEnum>();
				this.classOrEnumList.setPresent(true);
			}
			this.classOrEnumList.addToken(entry);
		}

		public virtual void addComment(Java_Comment comment)
		{
			if (this.comments1 == null)
			{
				this.comments1 = new TokenList<Java_Comment>();
			}
			this.comments1.addToken(comment);
		}
	}

}

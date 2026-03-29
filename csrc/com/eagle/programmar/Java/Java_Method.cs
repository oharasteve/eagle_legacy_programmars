// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.Java
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Java_MethodParameter = com.eagle.programmar.Java.Java_ParameterList.Java_MethodParameter;
	using Java_GenericType = com.eagle.programmar.Java.Java_Type.Java_GenericType;
	using Java_StatementBlock = com.eagle.programmar.Java.Statements.Java_StatementBlock;
	using Java_Current_Class_Reference = com.eagle.programmar.Java.Symbols.Java_Current_Class_Reference;
	using Java_Method_Definition = com.eagle.programmar.Java.Symbols.Java_Method_Definition;
	using Java_Variable_Definition = com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using PrivacyEnum = com.eagle.transform.EagleGenerator.PrivacyEnum;
	using StaticEnum = com.eagle.transform.EagleGenerator.StaticEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_Method : TokenSequence, AbstractMethod, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @BLANKLINE TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_Annotation annotation;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Java_MethodModifier> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Java_MethodTypeAndName typeAndName;
		public Java_MethodTypeAndName typeAndName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Java_EmptyBrackets> brackets;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Java_MethodDefault methodDefault;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Java_MethodThrows jthrows;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Java_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) Java_MethodBody body;
		public Java_MethodBody body;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ReturnMetrics _returnMetrics = null;
		public ReturnMetrics _returnMetrics = null;

		public class Java_MethodTypeAndName : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MethodType XXmethodType;
			public Java_MethodType XXmethodType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MethodGeneric XXmethodGeneric;
			public Java_MethodGeneric XXmethodGeneric;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MethodTwoTypes XXmethodTwoTypes;
			public Java_MethodTwoTypes XXmethodTwoTypes;
		}

		public class Java_MethodType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Java_Type jtype;
			public Java_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Symbols.Java_Method_Definition methodName;
			public Java_Method_Definition methodName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_ParameterList parameters;
			public  NOSPACE;
		}

		public class Java_MethodGeneric : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Type.Java_GenericType genericType;
			public Java_GenericType genericType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Symbols.Java_Method_Definition methodName;
			public Java_Method_Definition methodName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Java_ParameterList parameters;
			public Java_ParameterList parameters;
		}

		public class Java_MethodTwoTypes : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Type.Java_GenericType genericType;
			public Java_GenericType genericType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Java_Type jtype;
			public Java_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Symbols.Java_Method_Definition methodName;
			public Java_Method_Definition methodName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Java_ParameterList parameters;
			public Java_ParameterList parameters;
		}

		public class Java_EmptyBrackets : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public class Java_MethodDefault : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword DEFAULT = new com.eagle.programmar.Java.Terminals.Java_Keyword("default");
			public Java_Keyword DEFAULT = new Java_Keyword("default");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_Expression expr;
			public  OPT;
		}

		public class Java_MethodModifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST @NEWLINE Java_Comment XXcomment;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_KeywordChoice XXmodifier = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice(Java_Program.MODIFIERS);
			public Java_KeywordChoice XXmodifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Annotation XXannotation;
			public Java_Annotation XXannotation;
		}

		public class Java_MethodThrows : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword jthrows = new com.eagle.programmar.Java.Terminals.Java_Keyword("throws");
			public Java_Keyword jthrows = new Java_Keyword("throws");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Java_Expression, com.eagle.tokens.punctuation.PunctuationComma> jclass;
			public SeparatedList<Java_Expression, PunctuationComma> jclass;
		}

		public class Java_MethodBody : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MethodImplementation XXmethodImplementation;
			public Java_MethodImplementation XXmethodImplementation;
		}

		public class Java_MethodImplementation : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Statements.Java_StatementBlock block;
			public Java_StatementBlock block;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
			public  OPT;
		}

		public class Java_Constructor : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @BLANKLINE TokenList<Java_Annotation> annotation;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Java_MethodModifier> modifiers;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Symbols.Java_Current_Class_Reference constructorName;
			public Java_Current_Class_Reference constructorName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE Java_ParameterList parameters;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Java_MethodThrows jthrows;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Java_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) Java_MethodBody body;
			public Java_MethodBody body;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = typeAndName.getWhich();
			if (which is Java_MethodType)
			{
				Java_Method_Definition id = ((Java_MethodType) which).methodName;

				if (_callMetrics == null)
				{
					_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
				}
				if (_argumentsMetrics == null)
				{
					_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
				}
				if (_returnMetrics == null)
				{
					_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
				}

				// Nothing to do here. Only run methods when they are called / invoked.
				// Exception is 'main'
				if (id.getValue().Equals("main"))
				{
					interpreter.callingFunction("main", this);
					which = body.getWhich();
					if (which is Java_MethodImplementation)
					{
						Java_MethodImplementation impl = (Java_MethodImplementation) which;
						foreach (Java_StatementOrComment stmt in impl.block.statements._elements)
						{
							interpreter.tryToInterpret(stmt);
						}
					}
					interpreter.completedFunction("main", this);
				}
			}
		}

		public virtual void newJavaMethod(EagleGenerator.PrivacyEnum privacy, EagleGenerator.StaticEnum isStatic, Java_Type returnType, string methodName)
		{
			this.setPresent(true);
			this.modifiers = new TokenList<Java_MethodModifier>();

			Java_MethodModifier modifier1 = null;
			switch (privacy)
			{
			case NONE:
				break;
			case PUBLIC:
				modifier1 = new Java_MethodModifier();
				modifier1.setWhich(new Java_KeywordChoice("public"));
				break;
			case PRIVATE:
				modifier1 = new Java_MethodModifier();
				modifier1.setWhich(new Java_KeywordChoice("private"));
				break;
			default:
				throw new Exception("Can't handle privacy: " + privacy);
			}
			if (modifier1 != null)
			{
				this.modifiers.addToken(modifier1);
			}

			switch (isStatic)
			{
			case NONE:
				break;
			case STATIC:
				Java_MethodModifier modifier2 = new Java_MethodModifier();
				modifier2.setWhich(new Java_KeywordChoice("static"));
				this.modifiers.addToken(modifier2);
				break;
			default:
				throw new Exception("Can't handle static: " + isStatic);
			}

			this.typeAndName = new Java_MethodTypeAndName();
			Java_MethodType methodType = new Java_MethodType();
			this.typeAndName.setWhich(methodType);
			if (returnType == null)
			{
				methodType.jtype = Java_Type.newPrimitiveType("void");
			}
			else
			{
				methodType.jtype = returnType;
			}

			methodType.parameters = new Java_ParameterList();
			methodType.parameters.setPresent(true);
			methodType.parameters.leftParen = new PunctuationLeftParen();
			methodType.parameters.rightParen = new PunctuationRightParen();

			this.body = new Java_MethodBody();
			Java_MethodImplementation impl = new Java_MethodImplementation();
			impl.block = new Java_StatementBlock();
			impl.block.leftBrace = new PunctuationLeftBrace();
			impl.block.statements = new TokenList<Java_StatementOrComment>();
			impl.block.rightBrace = new PunctuationRightBrace();
			this.body.setWhich(impl);

			methodType.methodName = new Java_Method_Definition();
			methodType.methodName.setValue(methodName);
		}

		public virtual void addMethodParameter(AbstractType type, string name)
		{
			Java_MethodParameter param = new Java_MethodParameter();
			param.setPresent(true);
			param.id = new Java_Variable_Definition();
			param.id.setValue(name);
			param.jtype = (Java_Type) type;

			AbstractToken which = this.typeAndName.getWhich();
			if (which is Java_MethodType)
			{
				Java_MethodType methType = (Java_MethodType) which;
				if (methType.parameters.@params == null)
				{
					methType.parameters.@params = new SeparatedList<Java_MethodParameter, PunctuationComma>();
				}
				if (methType.parameters.@params.size() > 0)
				{
					methType.parameters.@params.addSecondaryElement(new PunctuationComma());
				}
				methType.parameters.@params.addPrimaryElement(param);
			}
			else
			{
				throw new Exception("Can't handle: " + which);
			}
		}

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which1 = typeAndName.getWhich();
			if (!(which1 is Java_MethodType))
			{
				throw new Exception("Can only handle simple methods now");
			}

			Java_MethodType methodType = (Java_MethodType) which1;
			AbstractType newReturnType = Java_Type.findType(generator, methodType.jtype);

			string newName = methodType.methodName.getValue();
			if (VERBOSE)
			{
				Console.WriteLine("** Found Java method " + newName);
			}
			if (newName.Equals("main"))
			{
				newName = generator.mainName();
			}

			generator.addMethod(newReturnType, newName, this);
			generator.setMethodName(newName);

			Java_ParameterList @params = methodType.parameters;
			if (@params != null && @params.isPresent())
			{
				int nParams = @params.@params.getPrimaryCount();
				for (int i = 0; i < nParams; i++)
				{
					Java_MethodParameter param = @params.@params.getPrimaryElement(i);
					AbstractType paramType = Java_Type.findType(generator, param.jtype);
					generator.addMethodParameter(paramType, param.id.getValue());
				}
			}

			if (!(body.getWhich() is Java_MethodImplementation))
			{
				throw new Exception("Methods need an implementation");
			}

			Java_MethodImplementation impl = (Java_MethodImplementation) body.getWhich();
			List<AbstractStatement> newStmts = new List<AbstractStatement>();
			foreach (Java_StatementOrComment javaStmt in impl.block.statements._elements)
			{
				if (javaStmt.getWhich() is Java_Statement)
				{
					Java_Statement stmt1 = (Java_Statement) javaStmt.getWhich();
					List<AbstractStatement> stmts2 = transformer.transformStatement(generator, stmt1.getWhich());
					if (stmts2 != null)
					{
						foreach (AbstractStatement stmt2 in stmts2)
						{
							newStmts.Add(stmt2);
						}
					}
				}
			}

			AbstractStatement newBlock = generator.newBlockStatement(newStmts, impl);
			generator.addStatement(newBlock, impl);
			generator.doneMethod();
		}

		public virtual void addComment(Java_Comment comm)
		{
			if (this.comments == null)
			{
				this.comments = new TokenList<Java_Comment>();
			}
			this.comments.addToken(comm);
		}
	}

}

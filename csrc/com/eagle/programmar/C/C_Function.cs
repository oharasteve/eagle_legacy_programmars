// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using C_StatementOrComment = com.eagle.programmar.C.C_Program.C_StatementOrComment;
	using C_Function_Definition = com.eagle.programmar.C.Symbols.C_Function_Definition;
	using C_Variable_Definition = com.eagle.programmar.C.Symbols.C_Variable_Definition;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_Literal = com.eagle.programmar.C.Terminals.C_Literal;
	using C_Number = com.eagle.programmar.C.Terminals.C_Number;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using C_FunctionPointer = com.eagle.programmar.C.Types.C_FunctionPointer;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationAmpersand = com.eagle.tokens.punctuation.PunctuationAmpersand;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Extern_C externC;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_FunctionDeclspec declspec;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_Keyword EXTENSION = new com.eagle.programmar.C.Terminals.C_Keyword("__extension__");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_FunctionAttributes attributes;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT C_KeywordChoice scope1 = new com.eagle.programmar.C.Terminals.C_KeywordChoice(C_Program.getModifiers());
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_Comment comment1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT C_KeywordChoice scope2 = new com.eagle.programmar.C.Terminals.C_KeywordChoice(C_Program.getModifiers());
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) C_FunctionTypeName typeName;
		public C_FunctionTypeName typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) C_Function_ParameterDefs parameters;
		public C_Function_ParameterDefs parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT C_Keyword CONST = new com.eagle.programmar.C.Terminals.C_Keyword("const");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) C_FunctionBody body;
		public C_FunctionBody body;

		public class C_FunctionTypeName : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Keyword XXMAIN = new com.eagle.programmar.C.Terminals.C_Keyword("main");
			public C_Keyword XXMAIN = new C_Keyword("main"); // Strange syntax with no return type on 'main'
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Function_TypeAndName XXtypeAndName;
			public C_Function_TypeAndName XXtypeAndName;
		}

		public class C_Function_TypeAndName : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) C_Type ctype;
			public C_Type ctype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Symbols.C_Function_Definition functionName;
			public C_Function_Definition functionName;
		}

		public class C_Function_ParameterDefs : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Comment comment1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_FunctionParameter param;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_Comment comment2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<C_MoreParameterDefs> moreParams;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class C_FunctionParameter : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST C_FunctionPointer XXfunctionPointer;
			public C_FunctionPointer XXfunctionPointer;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_FunctionParamAmpersand XXparamAmpersand;
			public C_FunctionParamAmpersand XXparamAmpersand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_FunctionFunctionParameter XXfunctionParam;
			public C_FunctionFunctionParameter XXfunctionParam;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_FunctionDotDotDotParameter XXdotDotParam;
			public C_FunctionDotDotDotParameter XXdotDotParam;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_FunctionRegularParameter XXparamRegular;
			public C_FunctionRegularParameter XXparamRegular; // Otherwise it misses C_FunctionFunctionParameter
		}

		public class C_FunctionParamAmpersand : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationAmpersand ampersand;
			public PunctuationAmpersand ampersand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_Type type;
			public C_Type type;
		}

		public class C_FunctionRegularParameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Keyword CONST = new com.eagle.programmar.C.Terminals.C_Keyword("const");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_Type ctype;
			public C_Type ctype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_KeywordChoice RESTRICT = new com.eagle.programmar.C.Terminals.C_KeywordChoice("__restrict", "restrict");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_Variable_Definition id;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<C_Subscript> subscripts;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT C_FunctionDefaultValue value;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT C_Comment comment;
			public  OPT;

			public class C_FunctionDefaultValue : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_Expression expr;
				public C_Expression expr;
			}
		}

		public class C_FunctionFunctionParameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) C_Type ctype;
			public C_Type ctype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Function_Definition id;
			public C_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) C_Function_ParameterDefs params;
			public C_Function_ParameterDefs @params;
		}

		public class C_FunctionDotDotDotParameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Punctuation dotDotDot = new com.eagle.programmar.C.Terminals.C_Punctuation("...");
			public C_Punctuation dotDotDot = new C_Punctuation("...");
		}

		public class C_MoreParameterDefs : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) C_FunctionParameter param;
			public C_FunctionParameter param;
		}

		public class C_FunctionBody : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_FunctionImplementation XXimplementation;
			public C_FunctionImplementation XXimplementation;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_FunctionEqualsZero extends com.eagle.tokens.TokenSequence
			public class C_FunctionEqualsZero : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Number zero;
				public C_Number zero;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_FunctionEqualsDefault extends com.eagle.tokens.TokenSequence
			public class C_FunctionEqualsDefault : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Keyword DEFAULT = new com.eagle.programmar.C.Terminals.C_Keyword("default");
				public C_Keyword DEFAULT = new C_Keyword("default");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_FunctionNoBody extends com.eagle.tokens.TokenSequence
			public class C_FunctionNoBody : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_FunctionAssembler assembler;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<C_FunctionAttributes> attributes;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public PunctuationSemicolon semicolon;

				public class C_FunctionAssembler : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword ASM = new com.eagle.programmar.C.Terminals.C_Keyword("__asm__");
					public C_Keyword ASM = new C_Keyword("__asm__");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_Literal blank;
					public C_Literal blank;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.Terminals.C_Literal functionName;
					public C_Literal functionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}
			}
		}

		public class C_FunctionImplementation : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.C.C_Program.C_StatementOrComment> elements;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public  OPT;
		}

		public class C_FunctionDeclspec : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_KeywordChoice DECLSPEC = new com.eagle.programmar.C.Terminals.C_KeywordChoice("_declspec", "__declspec");
			public C_KeywordChoice DECLSPEC = new C_KeywordChoice("_declspec", "__declspec");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_Keyword DLLEXPORT = new com.eagle.programmar.C.Terminals.C_Keyword("dllexport");
			public C_Keyword DLLEXPORT = new C_Keyword("dllexport");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ReturnMetrics _returnMetrics = null;
		public ReturnMetrics _returnMetrics = null;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, C_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, C_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_callMetrics == null)
			{
				string fname = "main";
				AbstractToken which = typeName.getWhich();
				if (which is C_Function_TypeAndName)
				{
					C_Function_TypeAndName typeAndName = (C_Function_TypeAndName) which;
					C_Function_Definition id = typeAndName.functionName;
					fname = id.getValue();
					_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
					_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
					_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
				}

				// Don't do anything here, unless the function name is 'main'
				// We searched for all the functions in a preliminary pass
				// And we only evaluate them when they are called
				if (fname.Equals("main"))
				{
					interpreter.callingFunction("main", this);
					AbstractToken token = body.getWhich();
					if (token is C_FunctionImplementation)
					{
						C_FunctionImplementation impl = (C_FunctionImplementation) token;
						foreach (C_StatementOrComment stmt in impl.elements._elements)
						{
							interpreter.tryToInterpret(stmt);
						}
					}
					interpreter.completedFunction("main", this);
				}
			}
		}

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which1 = typeName.getWhich();
			if (!(which1 is C_Function_TypeAndName))
			{
				throw new Exception("Unable to handle: " + which1);
			}
			C_Function_TypeAndName typeAndName = (C_Function_TypeAndName) which1;
			EagleGenerator.TypeEnum retType = typeAndName.ctype.findType();
			C_Function_Definition id = typeAndName.functionName;

			string fnName = id.getValue();
			bool isMain = false;
			if (fnName.Equals("main"))
			{
				fnName = generator.mainName();
				retType = EagleGenerator.TypeEnum.VOID;
				isMain = true;
			}

			AbstractType newReturnType = generator.transformType(retType, null, id);
			generator.addMethod(newReturnType, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("*** Found C function " + fnName);
			}

			if (isMain)
			{
				generator.addMainArgs();
			}
			else
			{
				// First parameter is kept separately from remainder, unfortunately
				if (parameters.param != null && parameters.param.isPresent())
				{
					AbstractToken which2 = parameters.param.getWhich();
					if (which2 is C_FunctionRegularParameter)
					{
						C_FunctionRegularParameter regParam1 = (C_FunctionRegularParameter) which2;
						string firstName = regParam1.id.getValue();
						if (!string.ReferenceEquals(firstName, null))
						{
							// if firstName is null means the parameter list is just 'void'
							if (VERBOSE)
							{
								Console.WriteLine("****** First Parameter " + firstName);
							}
							EagleGenerator.TypeEnum argType1 = regParam1.ctype.findType();
							AbstractType newArgType1 = generator.transformType(argType1, null, regParam1);
							generator.addMethodParameter(newArgType1, regParam1.id.getValue());
						}
					}
				}
				if (parameters.moreParams != null && parameters.moreParams.size() > 0)
				{
					foreach (C_MoreParameterDefs nextParam in parameters.moreParams._elements)
					{
						AbstractToken which3 = nextParam.param.getWhich();
						if (which3 is C_FunctionRegularParameter)
						{
							C_FunctionRegularParameter regParam2 = (C_FunctionRegularParameter) which3;
							if (VERBOSE)
							{
								Console.WriteLine("******  Next Parameter " + regParam2.id.getValue());
							}
							EagleGenerator.TypeEnum argType2 = regParam2.ctype.findType();
							AbstractType newArgType2 = generator.transformType(argType2, null, regParam2);
							generator.addMethodParameter(newArgType2, regParam2.id.getValue());
						}
					}
				}
			}

			AbstractToken which4 = body.getWhich();
			if (which4 is C_FunctionImplementation)
			{
				C_FunctionImplementation impl = (C_FunctionImplementation) which4;

				foreach (C_StatementOrComment stmtOrComment in impl.elements._elements)
				{
					AbstractToken which5 = stmtOrComment.getWhich();
					if (which5 is C_Statement)
					{
						C_Statement stmt = (C_Statement) which5;
						List<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
						if (newStmts != null)
						{
							foreach (AbstractStatement newStmt in newStmts)
							{
								generator.addStatement(newStmt, stmt);
							}
						}
					}
					else if (which5 is C_Data)
					{
						C_Data data = (C_Data) which5;
						List<AbstractStatement> newData = transformer.transformStatement(generator, data.getWhich());
						if (newData != null)
						{
							foreach (AbstractStatement newDatum in newData)
							{
								generator.addStatement(newDatum, data.getWhich());
							}
						}
					}
				}
			}

			generator.doneMethod();
		}
	}

}

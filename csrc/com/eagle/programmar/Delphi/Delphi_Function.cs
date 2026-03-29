// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

namespace com.eagle.programmar.Delphi
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using Delphi_MoreParameters = com.eagle.programmar.Delphi.Delphi_Parameter_List.Delphi_MoreParameters;
	using Delphi_Parameter = com.eagle.programmar.Delphi.Delphi_Parameter_List.Delphi_Parameter;
	using Delphi_BeginEnd = com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
	using Delphi_Function_Definition = com.eagle.programmar.Delphi.Symbols.Delphi_Function_Definition;
	using Delphi_Identifier_Reference = com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Delphi_FunctionForward forward;
		public Delphi_FunctionForward forward;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Delphi_Header> headers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd body;
		public Delphi_BeginEnd body;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Delphi.Terminals.Delphi_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
		public PunctuationSemicolon semicolon2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;

		public class Delphi_FunctionForward : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("Procedures_and_Functions_(Delphi)#Function_Declarations") com.eagle.programmar.Delphi.Terminals.Delphi_Keyword FUNCTION = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Function");
			public @DOC("Procedures_and_Functions_(Delphi)#Function_Declarations") Delphi_Keyword FUNCTION = new Delphi_Keyword("Function");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Delphi_FunctionClass> classes;
			public @OPT TokenList<Delphi_FunctionClass> classes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Symbols.Delphi_Function_Definition id;
			public Delphi_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Delphi_Parameter_List args;
			public @OPT Delphi_Parameter_List args;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) Delphi_Type type;
			public Delphi_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
			public PunctuationSemicolon semicolon1;
		}

		public static class Delphi_FunctionClass extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference clsRef;
			public Delphi_Identifier_Reference clsRef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, Delphi_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Delphi_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// Don't run it here. Wait until it is called.

			if (_callMetrics == null)
			{
				_callMetrics = new CallMetrics(interpreter._metrics, forward.id.getValue(), forward.id);
			}
			if (_argumentsMetrics == null)
			{
				_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, forward.id.getValue(), forward.id);
			}
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string funcName = this.forward.id.getValue();
			AbstractType type = this.forward.type.convertType(generator);
			generator.addMethod(type, funcName, this);

			Delphi_Parameter param = this.forward.args.firstParam;
			if (param != null && param.isPresent())
			{
				AbstractType paramType1 = param.type.convertType(generator);
				string paramName1 = param.names.first().var.getValue();
				generator.addMethodParameter(paramType1, paramName1);

				foreach (Delphi_MoreParameters more in this.forward.args.moreParams._elements)
				{
					AbstractType paramType2 = more.param.type.convertType(generator);
					string paramName2 = more.param.names.first().var.getValue();
					generator.addMethodParameter(paramType2, paramName2);
				}
			}

			if (this.headers != null)
			{
				foreach (Delphi_Header header in this.headers._elements)
				{
					header.processHeader(transformer, generator);
				}
			}

			this.body.statements.transformRemoveBeginEnd(transformer, generator);

			generator.doneMethod();
		}
	}

}

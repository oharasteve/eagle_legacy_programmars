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
	using Delphi_Identifier_Reference = com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
	using Delphi_Procedure_Definition = com.eagle.programmar.Delphi.Symbols.Delphi_Procedure_Definition;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Procedure : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Delphi_ProcedureForward forward;
		public Delphi_ProcedureForward forward;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Delphi_Header> headers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_BeginEnd body;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Delphi.Terminals.Delphi_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PunctuationSemicolon semicolon;
		public  OPT;

		public class Delphi_ProcedureForward : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("Procedures_and_Functions_(Delphi)#Procedure_Declarations") com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice PROCEDURE = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("Procedure", "Constructor", "Destructor");
			public @DOC("Procedures_and_Functions_(Delphi)#Procedure_Declarations") Delphi_KeywordChoice PROCEDURE = new Delphi_KeywordChoice("Procedure", "Constructor", "Destructor");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Delphi_ProcedureClass> classes;
			public @OPT TokenList<Delphi_ProcedureClass> classes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Symbols.Delphi_Procedure_Definition id;
			public Delphi_Procedure_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Delphi_Parameter_List args;
			public @OPT Delphi_Parameter_List args;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Delphi_Override override;
			public @OPT Delphi_Override @override;

			public static class Delphi_ProcedureClass extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference clsRef;
				public Delphi_Identifier_Reference clsRef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
			}

			public static class Delphi_Override extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword OVERRIDE = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Override");
				public Delphi_Keyword OVERRIDE = new Delphi_Keyword("Override");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public PunctuationSemicolon semicolon;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;

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

		public void transformProcedure(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string procName = this.forward.id.getValue();
			generator.addMethod(null, procName, this);

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

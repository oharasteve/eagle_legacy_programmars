// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Javascript_StatementOrComment = com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
	using Javascript_FunctionParameter = com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
	using Javascript_MoreParameters = com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_MoreParameters;
	using Javascript_Function_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
	using Javascript_Variable_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
	using Javascript_Comment = com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
	using Javascript_Keyword = com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
	using Javascript_KeywordChoice = com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Javascript_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Javascript_KeywordChoice STATIC = new com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice("static", "async");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Javascript_Keyword EXPORT = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("export");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Javascript_Keyword DEFAULT = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("default");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Javascript_Keyword FUNCTION = new com.eagle.programmar.Javascript.Terminals.Javascript_Keyword("function");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Javascript_FunctionImplementation implementation;
		public Javascript_FunctionImplementation implementation;

		public class Javascript_FunctionImplementation : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Javascript_Function_Definition id;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Javascript_FunctionParameters params;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.Javascript.Terminals.Javascript_Comment> comments2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) Javascript_FunctionBody body;
			public Javascript_FunctionBody body;
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
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, Javascript_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Javascript_Syntax.IS_CASE_SENSITIVE);

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
				_callMetrics = new CallMetrics(interpreter._metrics, implementation.id.getValue(), implementation.id);
			}
			if (_argumentsMetrics == null)
			{
				_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, implementation.id.getValue(), implementation.id);
			}
			if (_returnMetrics == null)
			{
				_returnMetrics = new ReturnMetrics(interpreter._metrics, implementation.id.getValue(), implementation.id);
			}

			// Nothing to do here. Only run functions when they are called / invoked.
		}

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newReturnType = null;
			EagleGenerator.TypeEnum metricRetType = transformer.findReturnMetric(implementation.id);
			if (metricRetType != null)
			{
				newReturnType = generator.transformType(metricRetType, null, implementation);
			}

			string newName = implementation.id.getValue();
			if (VERBOSE)
			{
				Console.WriteLine("** Found Javascript method " + newName);
			}
			if (newName.Equals("Main"))
			{
				newName = generator.mainName();
			}

			generator.addMethod(newReturnType, newName, this);
			generator.setMethodName(newName);

			// Pick up metrics, if known
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(implementation.id);

			Javascript_FunctionParameter param1 = implementation.@params.param;
			AbstractToken which1 = param1.paramName.getWhich();
			AbstractType type = null;
			if (which1 is Javascript_Variable_Definition)
			{
				if (argTypes != null)
				{
					EagleGenerator.TypeEnum argType = argTypes[0];
					type = generator.transformType(argType, null, which1);
				}
				Javascript_Variable_Definition varDef1 = (Javascript_Variable_Definition) which1;
				generator.addMethodParameter(type, varDef1.getValue());
			}

			int argNumber = 1;
			foreach (Javascript_MoreParameters next in implementation.@params.moreParams._elements)
			{
				Javascript_FunctionParameter param2 = next.param;
				AbstractToken which2 = param2.paramName.getWhich();
				if (which2 is Javascript_Variable_Definition)
				{
					if (argTypes != null)
					{
						EagleGenerator.TypeEnum argType = argTypes[argNumber];
						type = generator.transformType(argType, null, which1);
					}
					Javascript_Variable_Definition varDef2 = (Javascript_Variable_Definition) which2;
					generator.addMethodParameter(type, varDef2.getValue());
				}
				argNumber++;
			}

			Javascript_FunctionBody impl = implementation.body;
			List<AbstractStatement> newStmts = new List<AbstractStatement>();
			foreach (Javascript_StatementOrComment javaStmt in impl.statements._elements)
			{
				if (javaStmt.getWhich() is Javascript_Statement)
				{
					Javascript_Statement stmt1 = (Javascript_Statement) javaStmt.getWhich();
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
	}

}

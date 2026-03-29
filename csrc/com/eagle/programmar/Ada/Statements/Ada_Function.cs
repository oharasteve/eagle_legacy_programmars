// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using Ada_Statement = com.eagle.programmar.Ada.Ada_Statement;
	using Ada_Syntax = com.eagle.programmar.Ada.Ada_Syntax;
	using Ada_Type = com.eagle.programmar.Ada.Ada_Type;
	using Ada_Function_Definition = com.eagle.programmar.Ada.Symbols.Ada_Function_Definition;
	using Ada_Identifier_Reference = com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
	using Ada_Variable_Definition = com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition;
	using Ada_Keyword = com.eagle.programmar.Ada.Terminals.Ada_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword FUNCTION = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("function");
		public Ada_Keyword FUNCTION = new Ada_Keyword("function");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Symbols.Ada_Function_Definition id;
		public Ada_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Ada_FunctionParams funcParamDefs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Ada_FunctionReturns returns;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Ada.Terminals.Ada_Keyword IS = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("is");
		public Ada_Keyword IS = new Ada_Keyword("is");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.Ada.Ada_Statement> statements1;
		public TokenList<Ada_Statement> statements1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Ada.Terminals.Ada_Keyword BEGIN = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("begin");
		public Ada_Keyword BEGIN = new Ada_Keyword("begin");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.TokenList<com.eagle.programmar.Ada.Ada_Statement> statements2;
		public TokenList<Ada_Statement> statements2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Ada.Terminals.Ada_Keyword END = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("end");
		public Ada_Keyword END = new Ada_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT Ada_Identifier_Reference id2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class Ada_FunctionParams : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<Ada_Parameter, com.eagle.tokens.punctuation.PunctuationSemicolon> parameters;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class Ada_Parameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition param;
			public Ada_Variable_Definition param;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Ada_Type type;
			public Ada_Type type;
		}

		public class Ada_FunctionReturns : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword RETURN = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("return");
			public Ada_Keyword RETURN = new Ada_Keyword("return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Ada_Type type;
			public Ada_Type type;
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
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Ada.Ada_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Ada_Syntax.IS_CASE_SENSITIVE);

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

			// Nothing to do here. Ignore the function definitions
		}

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newReturnType = null;
			if (returns != null && returns.isPresent())
			{
				newReturnType = returns.type.convertType(generator);
			}

			if (newReturnType == null)
			{
				EagleGenerator.TypeEnum metricRetType = transformer.findReturnMetric(id);
				newReturnType = generator.transformType(metricRetType, null, id);
			}

			string fnName = id.getValue();

			generator.addMethod(newReturnType, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found Ada function " + fnName);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (funcParamDefs != null && funcParamDefs.isPresent())
			{
				if (funcParamDefs.parameters != null && funcParamDefs.parameters.isPresent())
				{
					for (int i = 0; i < funcParamDefs.parameters.getPrimaryCount(); i++)
					{
						Ada_Parameter param = funcParamDefs.parameters.getPrimaryElement(i);
						AbstractType paramType = null;

						if (argTypes != null && i < argTypes.Count)
						{
							EagleGenerator.TypeEnum metricArg = argTypes[i];
							paramType = generator.transformType(metricArg, null, param);
						}

						generator.addMethodParameter(paramType, param.param.getValue());
					}
				}
			}

			foreach (Ada_Statement stmt1 in statements1._elements)
			{
				ICollection<AbstractStatement> newStmts1 = transformer.transformStatement(generator, stmt1.getWhich());
				if (newStmts1 != null)
				{
					foreach (AbstractStatement newStmt1 in newStmts1)
					{
						generator.addStatement(newStmt1, stmt1.getWhich());
					}
				}
			}

			foreach (Ada_Statement stmt2 in statements2._elements)
			{
				ICollection<AbstractStatement> newStmts2 = transformer.transformStatement(generator, stmt2.getWhich());
				if (newStmts2 != null)
				{
					foreach (AbstractStatement newStmt2 in newStmts2)
					{
						generator.addStatement(newStmt2, stmt2.getWhich());
					}
				}
			}

			generator.doneMethod();
		}
	}

}

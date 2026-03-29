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
	using Ada_Statement = com.eagle.programmar.Ada.Ada_Statement;
	using Ada_Syntax = com.eagle.programmar.Ada.Ada_Syntax;
	using Ada_FunctionParams = com.eagle.programmar.Ada.Statements.Ada_Function.Ada_FunctionParams;
	using Ada_Parameter = com.eagle.programmar.Ada.Statements.Ada_Function.Ada_Parameter;
	using Ada_Function_Definition = com.eagle.programmar.Ada.Symbols.Ada_Function_Definition;
	using Ada_Identifier_Reference = com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
	using Ada_Keyword = com.eagle.programmar.Ada.Terminals.Ada_Keyword;
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
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_Procedure : TokenSequence, EagleRunnable, AbstractFunction, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword PROCEDURE = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("procedure");
		public Ada_Keyword PROCEDURE = new Ada_Keyword("procedure");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Symbols.Ada_Function_Definition id;
		public Ada_Function_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Ada_FunctionParams procParamDefs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Ada.Terminals.Ada_Keyword IS = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("is");
		public Ada_Keyword IS = new Ada_Keyword("is");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Ada_Package pkg;
		public  OPT;
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
// ORIGINAL LINE: public @S(100) @OPT Ada_Identifier_Reference name;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;

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

			// Only deal with main procedure
			// ideone.com wants it named "test" for some reason
			if (id.getValue().Equals("main") || id.getValue().Equals("test"))
			{
				foreach (Ada_Statement stmt1 in statements1._elements)
				{
					interpreter.tryToInterpret(stmt1);
				}
				foreach (Ada_Statement stmt2 in statements2._elements)
				{
					interpreter.tryToInterpret(stmt2);
				}
			}
		}

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string fnName = id.getValue();
			bool isMain = false;
			if (fnName.Equals("main"))
			{
				fnName = generator.mainName(); // Change from 'main' to 'Main' for C#
				isMain = true;
			}

			generator.addMethod(null, fnName, this);
			generator.setMethodName(fnName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found Ada function " + fnName);
			}

			if (isMain)
			{
				// Have to wait until addMethod is called
				generator.addMainArgs(); // For java and C# but not for Python
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (procParamDefs != null && procParamDefs.isPresent())
			{
				if (procParamDefs.parameters != null && procParamDefs.parameters.isPresent())
				{
					for (int i = 0; i < procParamDefs.parameters.getPrimaryCount(); i++)
					{
						Ada_Parameter param = procParamDefs.parameters.getPrimaryElement(i);
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

			transformBody(transformer, generator);

			generator.doneMethod();
		}

		public virtual void transformBody(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			foreach (Ada_Statement stmt1 in statements1._elements)
			{
				AbstractToken which1 = stmt1.getWhich();
				if (which1 is Ada_Function)
				{
					Ada_Function func = (Ada_Function) which1;
					func.transformFunction(transformer, generator);
				}
				else if (which1 is Ada_Procedure)
				{
					Ada_Procedure proc = (Ada_Procedure) which1;
					proc.transformFunction(transformer, generator);
				}
				else // Other statements
				{
					ICollection<AbstractStatement> newStmts1 = transformer.transformStatement(generator, which1);
					if (newStmts1 != null)
					{
						foreach (AbstractStatement newStmt1 in newStmts1)
						{
							generator.addStatement(newStmt1, which1);
						}
					}
				}
			}

			foreach (Ada_Statement stmt2 in statements2._elements)
			{
				AbstractToken which2 = stmt2.getWhich();
				ICollection<AbstractStatement> newStmts2 = transformer.transformStatement(generator, which2);
				if (newStmts2 != null)
				{
					foreach (AbstractStatement newStmt2 in newStmts2)
					{
						generator.addStatement(newStmt2, which2);
					}
				}
			}
		}
	}

}

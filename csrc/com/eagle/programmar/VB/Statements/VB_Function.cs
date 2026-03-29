// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

namespace com.eagle.programmar.VB.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using VB_Element = com.eagle.programmar.VB.VB_Element;
	using VB_Statement = com.eagle.programmar.VB.VB_Element.VB_Statement;
	using VB_Parameters = com.eagle.programmar.VB.VB_Parameters;
	using VB_Parameter = com.eagle.programmar.VB.VB_Parameters.VB_Parameter;
	using VB_Syntax = com.eagle.programmar.VB.VB_Syntax;
	using VB_Type = com.eagle.programmar.VB.VB_Type;
	using VB_Sub_Definition = com.eagle.programmar.VB.Symbols.VB_Sub_Definition;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
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
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class VB_Function : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT VB_KeywordChoice modifier = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("private", "public");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("statements/function-statement") com.eagle.programmar.VB.Terminals.VB_Keyword FUNCTION1 = new com.eagle.programmar.VB.Terminals.VB_Keyword("function");
		public @DOC("statements/function-statement") VB_Keyword FUNCTION1 = new VB_Keyword("function");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Symbols.VB_Sub_Definition id;
		public VB_Sub_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.VB_Parameters params;
		public VB_Parameters @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT VB_Keyword AS = new com.eagle.programmar.VB.Terminals.VB_Keyword("as");
		public @OPT VB_Keyword AS = new VB_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT VB_Type returnType;
		public @OPT VB_Type returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
		public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<com.eagle.programmar.VB.VB_Element> stmts;
		public @OPT TokenList<VB_Element> stmts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.VB.Terminals.VB_Keyword END = new com.eagle.programmar.VB.Terminals.VB_Keyword("end");
		public VB_Keyword END = new VB_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.VB.Terminals.VB_Keyword FUNCTION2 = new com.eagle.programmar.VB.Terminals.VB_Keyword("function");
		public VB_Keyword FUNCTION2 = new VB_Keyword("function");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.VB.VB_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, VB_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
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

		public void interpret(EagleInterpreter interpreter)
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

			// Don't do anything here.
			// We searched for all the functions in a preliminary pass
			// And we only evaluate when it is called
		}

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newReturnType = null;
			if (returnType != null && returnType.isPresent())
			{
				VB_KeywordChoice kw = (VB_KeywordChoice) returnType.getWhich();
				newReturnType = VB_Type.findType(generator, kw.getValue());
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
				Console.WriteLine("** Found VB function " + fnName);
			}

			// Search metrics for arg types -- might not be any
			List<EagleGenerator.TypeEnum> argTypes = transformer.findArgumentsMetric(id);

			if (@params.@params != null && @params.@params.isPresent())
			{
				for (int i = 0; i < @params.@params.getPrimaryCount(); i++)
				{
					VB_Parameters.VB_Parameter param = @params.@params.getPrimaryElement(i);
					AbstractType paramType = null;
					if (param.@as != null && param.@as.isPresent())
					{
						VB_KeywordChoice kw = (VB_KeywordChoice) param.@as.type.getWhich();
						paramType = VB_Type.findType(generator, kw.getValue());
					}

					if (paramType == null && argTypes != null && i < argTypes.Count)
					{
						EagleGenerator.TypeEnum metricArg = argTypes[i];
						paramType = generator.transformType(metricArg, null, param);
					}

					generator.addMethodParameter(paramType, param.var.getValue());
				}
			}

			foreach (VB_Element stmt in stmts._elements)
			{
				for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
				{
					VB_Element.VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
					AbstractToken which = baseStatement.getWhich();

					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
			}

			generator.doneMethod();
		}
	}

}

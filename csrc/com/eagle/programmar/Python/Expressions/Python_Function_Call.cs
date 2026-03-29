// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Python_Argument_List = com.eagle.programmar.Python.Python_Argument_List;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Parameter = com.eagle.programmar.Python.Python_Params.Python_Parameter;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Function = com.eagle.programmar.Python.Statements.Python_Function;
	using Python_Identifier_Reference = com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
	using Python_Variable_Definition = com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Function_Call : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Variable fnName;
		public Python_Variable fnName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE @OPT @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) com.eagle.tokens.SeparatedList<com.eagle.programmar.Python.Python_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public @NOSPACE @SYNTAX(typeof(Python_Multiline_Syntax)) SeparatedList<Python_Expression, PunctuationComma> argList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationComma extraComma;
		public @OPT PunctuationComma extraComma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationRightParen rightParen;
		public @NOSPACE PunctuationRightParen rightParen;

		public void interpret(EagleInterpreter interpreter)
		{
			string name = "unknown";
			if (fnName.var.getWhich() is Python_Identifier_Reference)
			{
				Python_Identifier_Reference id = (Python_Identifier_Reference) fnName.var.getWhich();
				name = id.getValue();
			}

			// Look up the function in our function list
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			Python_Function func = (Python_Function) fn;

			// Make sure the function args match up
			int argCount = argList.getPrimaryCount();
			int paramCount = 0;
			if (func.header.@params.@params != null && func.header.@params.@params.isPresent())
			{
				paramCount++;
			}
			if (func.header.@params.@params.moreParams != null && func.header.@params.@params.moreParams.isPresent())
			{
				paramCount += func.header.@params.@params.moreParams.size();
			}
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, func.header);

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			Python_Parameter param = func.header.@params.@params.param;
			for (int i = 0; i < argCount; i++)
			{
				Python_Expression expr = argList.getPrimaryElement(i);
				if (i > 0)
				{
					param = func.header.@params.@params.moreParams._elements.get(i - 1).param;
				}
				if (param.getWhich() is Python_Variable_Definition)
				{
					Python_Variable_Definition def = (Python_Variable_Definition) param.getWhich();
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(def, def.getValue(), val);
					argTypes.Add(val.getType());
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the function
			interpreter.tryToInterpret(func.header.defBody);

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, func.header);
		}

		public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(fnName.var.getWhich() is Python_Identifier_Reference))
			{
				throw new Exception("Must be a simple function call");
			}
			Python_Identifier_Reference id = (Python_Identifier_Reference) fnName.var.getWhich();
			string name = id.getValue();
			List<AbstractExpression> args = new List<AbstractExpression>();
			int argCount = argList.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Python_Expression arg = argList.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.Add(newArg);
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, id);
		}

		public static Python_Expression generateInvocation(Python_Variable var, List<Python_Expression> args, AbstractToken source)
		{
			Python_Function_Call invoke = new Python_Function_Call();
			invoke.leftParen = new PunctuationLeftParen();
			invoke.leftParen.setPresent(true);
			invoke.rightParen = new PunctuationRightParen();
			invoke.rightParen.setPresent(true);
			AbstractToken which = var.var.getWhich();
			if (which is Python_Identifier_Reference)
			{
				string id = ((Python_Identifier_Reference) which).getValue();
				// if (id.indexOf('.') < 0) id = "self." + id;
				invoke.fnName = Python_Variable.newVariable(id);
				invoke.argList = Python_Argument_List.createArgumentList(args);
			}
			else
			{
				throw new Exception("Expected an Identifier, not " + which);
			}

			invoke.setTransformationSource(source);
			return Python_Generator.wrapExpression(invoke);
		}
	}

}

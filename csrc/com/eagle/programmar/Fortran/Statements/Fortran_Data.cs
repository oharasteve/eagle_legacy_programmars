// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Fortran_Type = com.eagle.programmar.Fortran.Fortran_Type;
	using Fortran_Variable_Definition = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Definition;
	using Fortran_Variable_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Punctuation = com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_Data : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Fortran.Fortran_Type type;
		public Fortran_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation colobColon = new com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation("::");
		public Fortran_Punctuation colobColon = new Fortran_Punctuation("::");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Definition, com.eagle.tokens.punctuation.PunctuationComma> variables;
		public SeparatedList<Fortran_Variable_Definition, PunctuationComma> variables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln;
		public Fortran_EOLN eoln;

		public override void interpret(EagleInterpreter interpreter)
		{
			// Could create an empty variable here
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			AbstractType newType = Fortran_Type.findType(generator, type);

			for (int i = 0; i < variables.getPrimaryCount(); i++)
			{
				// No initial values on data lines like INTEGER and CHARACTER
				Fortran_Variable_Definition varDef = variables.getPrimaryElement(i);

				// Skip declarations of Functions
				if (transformer.findCallTo(varDef.getValue()))
				{
					continue;
				}

				// Skip declaration of function/subroutine parameters
				// Fortran puts the parameters in the middle of the local variables
				bool skipVariable = false;
				AbstractToken parent = this.getParent();
				while (parent != null)
				{
					if (parent is Fortran_Function)
					{
						Fortran_Function func = (Fortran_Function) parent;
						if (func.parameters != null && func.parameters.isPresent())
						{
							int numParams = func.parameters.getPrimaryCount();
							for (int j = 0; j < numParams; j++)
							{
								Fortran_Variable_Reference @ref = func.parameters.getPrimaryElement(j);
								if (@ref.getValue().Equals(varDef.getValue()))
								{
									skipVariable = true;
									break;
								}
							}
						}
						break;
					}
					else if (parent is Fortran_Subroutine)
					{
						Fortran_Subroutine sub = (Fortran_Subroutine) parent;
						if (sub.parameters != null && sub.parameters.isPresent())
						{
							int numParams = sub.parameters.getPrimaryCount();
							for (int j = 0; j < numParams; j++)
							{
								Fortran_Variable_Reference @ref = sub.parameters.getPrimaryElement(j);
								if (@ref.getValue().Equals(varDef.getValue()))
								{
									skipVariable = true;
									break;
								}
							}
						}
						break;
					}
					parent = parent.getParent();
				}

				if (!skipVariable)
				{
					AbstractStatement stmt = generator.newDataDeclaration(false, varDef.getValue(), null, newType, null, varDef);
					result.Add(stmt);
				}
			}
			return result;
		}
	}

}

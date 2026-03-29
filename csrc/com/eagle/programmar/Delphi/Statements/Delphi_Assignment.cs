// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Function = com.eagle.programmar.Delphi.Delphi_Function;
	using Delphi_Variable = com.eagle.programmar.Delphi.Delphi_Variable;
	using Delphi_Extended_Variable = com.eagle.programmar.Delphi.Delphi_Variable.Delphi_Extended_Variable;
	using Delphi_Subscript = com.eagle.programmar.Delphi.Delphi_Variable.Delphi_Subscript;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Punctuation = com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Variable var;
		public Delphi_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation colonEquals = new com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation(":=");
		public Delphi_Punctuation colonEquals = new Delphi_Punctuation(":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Delphi_Expression expr;
		public Delphi_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Delphi_Comment comment;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var, var.var.getValue(), val);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			string varName = var.var.getValue();

			// Delphi doesn't have a Return statement.
			// It assigns a value to the function name
			AbstractToken parent = var.var;
			while (parent != null)
			{
				if (parent is Delphi_Function)
				{
					Delphi_Function fn = (Delphi_Function) parent;
					if (fn.forward.id.getValue().Equals(varName))
					{
						// Is a function return: function cube(a) begin cube := a*a*a end
						return generator.newReturnStatement(newExpr, this);
					}
					break;
				}
				parent = parent.getParent();
			}

			AbstractExpression subscrExpr = null;
			if (this.var.extensions != null)
			{
				foreach (Delphi_Variable.Delphi_Extended_Variable extension in this.var.extensions._elements)
				{
					AbstractToken which = extension.getWhich();
					if (which is Delphi_Variable.Delphi_Subscript)
					{
						Delphi_Variable.Delphi_Subscript delphiSubscr = (Delphi_Variable.Delphi_Subscript) which;
						if (delphiSubscr.exprs.size() > 1)
						{
							throw new Exception("Cannot handle multiple subscripts yet: " + delphiSubscr.exprs);
						}
						Delphi_Expression delphiExpr = delphiSubscr.exprs.first();
						subscrExpr = transformer.transformExpression(generator, delphiExpr);
					}
					else
					{
						throw new Exception("Can only handle Delphi_Subscript now, not " + which);
					}
				}
			}

			AbstractExpression asgStmt = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, EagleGenerator.AssignmentEnum.EQUALS, newExpr, null);
			return generator.newExpressionStatement(asgStmt, this);
		}
	}

}

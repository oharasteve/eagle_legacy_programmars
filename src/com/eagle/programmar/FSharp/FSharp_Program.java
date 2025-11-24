// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement_List;
import com.eagle.programmar.FSharp.Statements.FSharp_Function;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class FSharp_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String FSHARP = "FSharp";

	public FSharp_Program()
	{
		super(FSHARP, new FSharp_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.microsoft.com/en-us/dotnet/fsharp/language-reference/";
	}

	public @S(10) TokenList<FSharp_Element> elements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		for (FSharp_Element element : elements._elements)
		{
			if (element.statementOrComment.getWhich() instanceof FSharp_Statement_List)
			{
				FSharp_Statement_List statements = (FSharp_Statement_List) element.statementOrComment.getWhich();
				for (int i = 0; i < statements.statements.getPrimaryCount(); i++)
				{
					FSharp_Statement stmt = statements.statements.getPrimaryElement(i);
					if (stmt.getWhich() instanceof FSharp_Function)
					{
						FSharp_Function func = (FSharp_Function) stmt.getWhich();
						interpreter.addFunction(func.id.getValue(), func);
					}
				}
			}
		}

		// Second pass, run any stuff in the outermost class
		for (FSharp_Element element : elements._elements)
		{
			interpreter.tryToInterpret(element.statementOrComment.getWhich());
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// Are there any global variables we need to declare?
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typE = met.uniqueType();
			if (typE != TypeEnum.VOID)
			{
				AbstractType abstrType = generator.transformType(typE, null, this);
				AbstractExpression initExpr = null;
				// System.err.println("****** Found var " + met._symbolName);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
						null, abstrType, initExpr, this);
				generator.addStatement(dataStmt, this);
			}
		}

		// Transform all the Function definitions and global data
		for (FSharp_Element elt : elements._elements)
		{
			AbstractToken which1 = elt.statementOrComment.getWhich();
			if (which1 instanceof FSharp_Statement_List)
			{
				FSharp_Statement_List stmtList = (FSharp_Statement_List) which1;
				for (int i = 0; i < stmtList.statements.getPrimaryCount(); i++)
				{
					FSharp_Statement stmt = stmtList.statements.getPrimaryElement(i);
					AbstractToken which2 = stmt.getWhich();
					if (which2 instanceof FSharp_Function)
					{
						FSharp_Function func = (FSharp_Function) which2;
						func.transformFunction(transformer, generator);
					}
					else
					{
						Collection<AbstractStatement> newStmts = transformer.transformStatement(
								generator, which2);
						if (newStmts != null)
						{
							for (AbstractStatement newStmt : newStmts)
							{
								generator.addStatement(newStmt, elt);
							}
						}
					}
				}
			}
		}

		return generator.getTransfomedProgram();
	}
}

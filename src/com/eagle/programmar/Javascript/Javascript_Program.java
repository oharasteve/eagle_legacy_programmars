// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import java.util.ArrayList;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Javascript_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String JAVASCRIPT = "Javascript";

	public Javascript_Program()
	{
		super(JAVASCRIPT, new Javascript_Syntax());
	}

	/** For a language that is these rules under another name and syntax: TypeScript (Oct 2026, shane branch). */
	protected Javascript_Program(String name, Javascript_Syntax syntax)
	{
		super(name, syntax);
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/js/";
	}

	public @S(10) @OPT TokenList<Javascript_TopElement> elements;

	public static class Javascript_TopElement extends TokenChooser
	{
		public @CHOICE Javascript_Comment XXcomment;
		public @CHOICE Javascript_Function XXfunction;
		public @CHOICE Javascript_Statement XXstatement;
		public @CHOICE Javascript_Class XXclass;
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First (and only) pass, transform the main method
		for (Javascript_TopElement elt1 : elements._elements)
		{
			AbstractToken which1 = elt1.getWhich();
			if (which1 instanceof EagleTransformableFunction)
			{
				EagleTransformableFunction transformable = (EagleTransformableFunction) which1;
				transformable.transformFunction(transformer, generator);
			}
			else if (which1 instanceof Javascript_Statement)
			{
				Javascript_Statement stmt1 = (Javascript_Statement) which1;
				AbstractToken which2 = stmt1.getWhich();
				if (which2 instanceof Javascript_Data)
				{
					Javascript_Data data = (Javascript_Data) which2;
					StaticEnum isConst = StaticEnum.NONE;
					if (data.type.getWhich() instanceof Javascript_KeywordChoice)
					{
						Javascript_KeywordChoice kw = (Javascript_KeywordChoice) data.type.getWhich();
						if (kw.getValue().equals("const"))
						{
						  isConst = StaticEnum.CONST;
						}
					}
					ArrayList<AbstractStatement> stmts3 = data.transformStaticData(isConst, transformer, generator);
					for (AbstractStatement stmt3 : stmts3)
					{
					  generator.addStatement(stmt3, elt1);
					}
				}
				else
				{
					ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, stmt1.getWhich());
					if (stmts != null)
					{
						for (AbstractStatement stmt4 : stmts)
						{
							generator.addStatement(stmt4, which2);
						}
					}
				}
			}
		}

		return generator.getTransformedProgram();
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		for (Javascript_TopElement element : elements._elements) rt.hoistOne(element.getWhich());
		for (Javascript_TopElement element : elements._elements) interpreter.tryToInterpret(element);
	}
}

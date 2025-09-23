// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class AWK_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String AWK = "AWK";

	public AWK_Program()
	{
		super(AWK, new AWK_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.gnu.org/software/gawk/manual/gawk.html";
	}

	public @S(10) TokenList<AWK_Element> elements;

	public static class AWK_Element extends TokenChooser
	{
		public @CHOICE AWK_Command XXcommand;
		public @FIRST AWK_CommentLine XXcomment;
		public @CHOICE AWK_Function XXfunction;
	}

	public static class AWK_CommentLine extends TokenSequence
	{
		public @S(10) AWK_Comment comment;
		public @S(20) AWK_EndOfLine eoln;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (AWK_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof AWK_Function)
			{
				AWK_Function fn = (AWK_Function) which;
				interpreter.addFunction(fn.id.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (AWK_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof AWK_Command)
			{
				AWK_Command cmd = (AWK_Command) which;
				interpreter.tryToInterpret(cmd.action);
			}
		}
	}
	
	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// First pass, transform all the Function definitions
		for (AWK_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof AWK_Function)
			{
				AWK_Function func = (AWK_Function) which;
				// System.err.println("****** Found func " + func.id.getValue());
				func.transformFunction(transformer, generator);
			}
		}

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
				if (typE == TypeEnum.STRING_HASH)
				{
					// Need to create an empty hashmap
					initExpr = generator.newClassCreation(abstrType, null, this);
				}
				
				//System.err.println("****** Found var " + met._symbolName);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
						null, abstrType, initExpr, this);
				generator.addStatement(dataStmt, this);
			}
		}
		
		// Second pass, transform all the data and logic
		for (AWK_Element elt : elements._elements)
		{
			AbstractToken which2 = elt.getWhich();
			if (which2 instanceof AWK_Command)
			{
				AWK_Command cmd = (AWK_Command) which2;
				ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator, cmd.action);
				for (AbstractStatement stmt2 : stmts2)
				{
					generator.addStatement(stmt2, cmd);
				}
			}
		}
		
		return generator.getTransfomedProgram();
	}
}

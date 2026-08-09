// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2026

package com.eagle.programmar.COBOL;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration.COBOL_DataClause;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
import com.eagle.programmar.COBOL.Picture.COBOL_Usage;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleTransformer;

public class COBOL_LinkageSection extends TokenSequence implements EagleRunnable
{
	public @S(10) COBOL_Keyword LINKAGE = new COBOL_Keyword("LINKAGE");
	public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
	public @S(30) PunctuationPeriod dot;
	public @S(40) @OPT COBOL_Comment comment;
	public @S(50) TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
		{
			interpreter.tryToInterpret(decl);
		}
	}
	
	public void collectParameters(String funcName, EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		int declCount = 0;
		for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
		{
			declCount++;	// Careful, 1 is the FIRST, 0 means no return value

			AbstractType paramType = null;
			String paramName = null;
			AbstractToken which2 = decl.getWhich();
			if (which2 instanceof COBOL_DataDeclaration)
			{
				COBOL_DataDeclaration data = (COBOL_DataDeclaration) which2;
				AbstractToken which3 = data.fieldName.getWhich();
				if (which3 instanceof COBOL_Data_Definition)
				{
					COBOL_Data_Definition dataDef = (COBOL_Data_Definition) which3;
					paramName = COBOL_Variable.repairName(dataDef.getValue());
					boolean isComp = false;
					if (data.clauses != null)
					{
						for (COBOL_DataClause clause : data.clauses._elements)
						{
							AbstractToken which4 = clause.getWhich();
							if (which4 instanceof COBOL_PictureClause)
							{
								COBOL_PictureClause picClause = (COBOL_PictureClause) which4;
								String pic = picClause.picture.getValue().toUpperCase();
								if (pic.startsWith("9") || pic.startsWith("X") || pic.startsWith("Z"))
								{
									paramType = generator.transformType(TypeEnum.STRING, null, picClause);
									// Will get replaced by INTEGER if USAGE COMP is present
								}
							}
							if (which4 instanceof COBOL_Usage)
							{
								COBOL_Usage usage = (COBOL_Usage) which4;
								AbstractToken which5 = usage.usage.getWhich();
								if (which5 instanceof COBOL_KeywordChoice)
								{
									COBOL_KeywordChoice kw = (COBOL_KeywordChoice) which5;
									if (kw.getValue().toUpperCase().startsWith("COMP"))
									{
										isComp = true;
									}
								}
							}
						}
					}
					if (isComp) // COMP, COMP-1, etc.
					{
						paramType = generator.transformType(TypeEnum.INTEGER, null, null);
					}
					
					boolean isModified = false;
					COBOL_Program_Complete complete = null;
					COBOL_ProcedureDivision procDiv = null;
					AbstractToken parent = this.getParent();
					while (parent != null)
					{
						if (parent instanceof COBOL_Program_Complete)
						{
							complete = ((COBOL_Program_Complete) parent);
							procDiv = complete.procedureDiv;
							break;
						}
						parent = parent.getParent();
					}
					int assignments = transformer._metrics.countAssignments(paramName, procDiv);
					// System.out.println("****** " + paramName + " procDiv=" + procDiv.getStartLine() + " asg=" + assignments);
					if (assignments > 0)
					{
						isModified = true;
					}
					if (isModified && complete._retName == null)
					{
						complete._retType = paramType;
						complete._retName = paramName;
						complete._retIndex = declCount;	// Which parameter is the Return, first is 1
					}
					else
					{
						complete._paramTypes.add(paramType);
						complete._paramNames.add(paramName);
					}
				}
			}
		}
	}
}
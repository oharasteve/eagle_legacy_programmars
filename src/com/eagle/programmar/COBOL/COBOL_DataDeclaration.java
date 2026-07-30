// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.SubscriptEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_Picture_Value.COBOL_Picture_Value_Literal;
import com.eagle.programmar.COBOL.COBOL_Picture_Value.COBOL_Picture_Value_Number;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Picture.COBOL_BlankWhenZero;
import com.eagle.programmar.COBOL.Picture.COBOL_ObjectReference;
import com.eagle.programmar.COBOL.Picture.COBOL_PictureClause;
import com.eagle.programmar.COBOL.Picture.COBOL_RedefinesClause;
import com.eagle.programmar.COBOL.Picture.COBOL_RenamesClause;
import com.eagle.programmar.COBOL.Picture.COBOL_Sign;
import com.eagle.programmar.COBOL.Picture.COBOL_ThruClause;
import com.eagle.programmar.COBOL.Picture.COBOL_Typedef;
import com.eagle.programmar.COBOL.Picture.COBOL_Usage;
import com.eagle.programmar.COBOL.Picture.COBOL_ValueClause;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Index_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationStar;
import com.eagle.transform.EagleTransformer;

public class COBOL_DataDeclaration extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT TokenList<COBOL_Comment> comments;
	public @S(20) COBOL_Level level;
	public @S(30) @OPT COBOL_DataFieldName fieldName;
	public @S(40) @OPT TokenList<COBOL_DataClause> clauses;
	public @S(50) PunctuationPeriod dot;
	public @S(60) @OPT COBOL_DataComment comment;

	// These are special -- context-sensitive, must have
	// larger (deeper) Level numbers
	public @S(70) @OPT TokenList<COBOL_CopyOrDataDeclaration> children;

	public static class COBOL_DataClause extends TokenChooser
	{
		public @CHOICE COBOL_Type XXprimitive;

		public @CHOICE COBOL_BlankWhenZero XXblankWhenZero;
		public @CHOICE COBOL_Justified XXjustified;
		public @CHOICE COBOL_ObjectReference XXobjectReference;
		public @CHOICE COBOL_OccursClause XXoccurs;
		public @CHOICE COBOL_PictureClause XXpictureClause;
		public @CHOICE COBOL_RedefinesClause XXredefinesClause;
		public @CHOICE COBOL_RenamesClause XXrenamesClause;
		public @CHOICE COBOL_Sign XXsign;
		public @CHOICE COBOL_ThruClause XXthruClause;
		public @CHOICE COBOL_TypeLiteral XXtype;
		public @CHOICE COBOL_Typedef XXtypedef;
		public @CHOICE COBOL_Usage XXusage;
		public @CHOICE COBOL_ValueClause XXvalueClause;
		public @CHOICE COBOL_ValueIsGlobal XXisGlobal;
	}

	public static class COBOL_Justified extends TokenSequence
	{
		public @S(10) COBOL_Keyword JUSTIFIED = new COBOL_Keyword("JUSTIFIED");
		public @S(20) COBOL_Keyword RIGHT = new COBOL_Keyword("RIGHT");
	}

	public static class COBOL_DataFieldName extends TokenChooser
	{
		public @CHOICE COBOL_Keyword XXFILLER = new COBOL_Keyword("FILLER");
		public @CHOICE COBOL_Data_Definition XXid;
	}

	public static class COBOL_TypeLiteral extends TokenChooser
	{
		public @CHOICE COBOL_Keyword XXTYPE = new COBOL_Keyword("TYPE");
		public @CHOICE COBOL_Literal XXtype;
	}

	public static class COBOL_OccursClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword OCCURS = new COBOL_Keyword("OCCURS");
		public @S(20) COBOL_Expression count;
		public @S(30) @OPT COBOL_OccursTo to;
		public @S(40) @OPT COBOL_Keyword TIMES = new COBOL_Keyword("TIMES");
		public @S(50) @OPT COBOL_Depending depends;
		public @S(60) @OPT COBOL_OccursKey key;
		public @S(70) @OPT COBOL_IndexedBy indexedBy;

		public static class COBOL_Depending extends TokenSequence
		{
			public @S(10) COBOL_Keyword DEPENDING = new COBOL_Keyword("DEPENDING");
			public @S(20) COBOL_Keyword ON = new COBOL_Keyword("ON");
			public @S(30) COBOL_Identifier_Reference index;
		}

		public static class COBOL_OccursTo extends TokenSequence
		{
			public @S(10) COBOL_Keyword TO = new COBOL_Keyword("TO");
			public @S(20) COBOL_Expression count;
		}

		public static class COBOL_OccursKey extends TokenSequence
		{
			public @S(10) COBOL_Keyword ASCENDING = new COBOL_Keyword("ASCENDING");
			public @S(20) COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @S(30) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Identifier_Reference index;
		}

		public static class COBOL_IndexedBy extends TokenSequence
		{
			public @S(10) COBOL_Keyword INDEXED = new COBOL_Keyword("INDEXED");
			public @S(20) COBOL_Keyword BY = new COBOL_Keyword("BY");
			public @S(30) COBOL_Index_Definition index;
		}
	}

	public static class COBOL_ValueIsGlobal extends TokenSequence
	{
		public @S(10) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(20) COBOL_Keyword GLOBAL = new COBOL_Keyword("GLOBAL");
	}

	public static class COBOL_DataComment extends TokenSequence
	{
		public @S(10) PunctuationStar star;
		public @S(20) COBOL_CommentToEndOfLine comment;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (fieldName.getWhich() instanceof COBOL_Data_Definition)
		{
			COBOL_Data_Definition dataDef = (COBOL_Data_Definition) fieldName.getWhich();
			String varName = dataDef.getValue();
			String pic = null;
			String redefines = null;
			EagleInteger initInteger = null;
			EagleString initString = null;
			boolean isComp = false;
			if (clauses != null)
			{
				for (COBOL_DataClause clause : clauses._elements)
				{
					AbstractToken which = clause.getWhich();
					if (which instanceof COBOL_PictureClause)
					{
						COBOL_PictureClause picClause = (COBOL_PictureClause) which;
						pic = picClause.picture.getValue().toUpperCase();
					}
					if (which instanceof COBOL_RedefinesClause)
					{
						COBOL_RedefinesClause redefinesClause = (COBOL_RedefinesClause) which;
						redefines = redefinesClause.id.getValue();
					}
					if (which instanceof COBOL_Usage)
					{
						COBOL_Usage usage = (COBOL_Usage) which;
						if (usage.type.getValue().toUpperCase().startsWith("COMP"))
						{
							isComp = true;
						}
					}
					if (which instanceof COBOL_ValueClause)
					{
						COBOL_ValueClause valueClause = (COBOL_ValueClause) which;
						COBOL_Picture_Value picValue = valueClause.values.first();
						if (picValue.getWhich() instanceof COBOL_Picture_Value_Number)
						{
							COBOL_Picture_Value_Number num = (COBOL_Picture_Value_Number) picValue.getWhich();
							initInteger = new EagleInteger(Integer.parseInt(num.number.getValue()));
						}
						else if (picValue.getWhich() instanceof COBOL_Picture_Value_Literal)
						{
							COBOL_Picture_Value_Literal lit = (COBOL_Picture_Value_Literal) picValue.getWhich();
							initString = new EagleString(lit.literal.getValue());
						}
					}

				}
			}

			// Check for REDEFINES first
			EagleValue value = null;
			if (redefines != null)
			{
				value = interpreter.findSymbol(redefines);
				if (value != null)
				{
					// Change the name of the symbol
					interpreter.removeSymbol(redefines);
				}
			}

			else if (isComp)
			{
				value = initInteger;
			}
			else if (pic == null)
			{
				value = collectArrayValues();
			}
			else if (pic.startsWith("9"))
			{
				value = initInteger;
			}
			else if (pic.startsWith("X") || pic.startsWith("Z"))
			{
				value = initString;
			}
			else
			{
				System.err.println("*** data " + level + " " + varName + " " + pic);
			}
			interpreter.setSymbol(dataDef, varName, value);
		}
	}

	private EagleArray collectArrayValues()
	{
		// Look at all the children
		EagleArray array = new EagleArray();
		for (COBOL_CopyOrDataDeclaration child : this.children._elements)
		{
			AbstractToken which = child.getWhich();
			if (which instanceof COBOL_DataDeclaration)
			{
				COBOL_DataDeclaration dataDeclaration2 = (COBOL_DataDeclaration) which;
				for (COBOL_DataClause clause2 : dataDeclaration2.clauses._elements)
				{
					AbstractToken whichValue = clause2.getWhich();
					if (whichValue instanceof COBOL_ValueClause)
					{
						COBOL_ValueClause valueClause = (COBOL_ValueClause) whichValue;
						COBOL_Picture_Value picValue = valueClause.values.first();
						if (picValue.getWhich() instanceof COBOL_Picture_Value_Literal)
						{
							COBOL_Picture_Value_Literal lit = (COBOL_Picture_Value_Literal) picValue.getWhich();
							EagleString str = new EagleString(lit.literal.removeQuotes());
							// System.err.println("************** Adding " + str.toString());
							array.addValue(str);
						}
					}
				}
			}
		}
		return array;
	}

	public AbstractStatement transformData(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (fieldName.getWhich() instanceof COBOL_Data_Definition)
		{
			COBOL_Data_Definition dataDef = (COBOL_Data_Definition) fieldName.getWhich();
			String varName = COBOL_Variable.repairName(dataDef.getValue());
			AbstractType newType = null;
			AbstractExpression expression = null;
			boolean isComp = false;
			if (clauses != null)
			{
				for (COBOL_DataClause clause : clauses._elements)
				{
					AbstractToken which = clause.getWhich();
					if (which instanceof COBOL_PictureClause)
					{
						COBOL_PictureClause picClause = (COBOL_PictureClause) which;
						String pic = picClause.picture.getValue().toUpperCase();
						if (pic.startsWith("9") || pic.startsWith("X") || pic.startsWith("Z"))
						{
							newType = generator.transformType(TypeEnum.STRING, null, picClause);
							// Will get replaced by INTEGER if USAGE COMP is present
						}
					}
					if (which instanceof COBOL_RedefinesClause)
					{
						COBOL_RedefinesClause redefClause = (COBOL_RedefinesClause) which;
						String redefWhat = redefClause.id.getValue();
						AbstractExpression redef = generator.newVariableExpression(redefWhat,
								SubscriptEnum.FIRST_IS_ONE, null, this);
						newType = generator.transformType(TypeEnum.ARRAY, varName, this);
						return generator.newDataDeclaration(
								StaticEnum.NONE, varName, null, newType, redef, this);
					}
					if (which instanceof COBOL_ValueClause)
					{
						COBOL_ValueClause valueClause = (COBOL_ValueClause) which;
						COBOL_Picture_Value picValue = valueClause.values.first();
						if (picValue.getWhich() instanceof COBOL_Picture_Value_Number)
						{
							COBOL_Picture_Value_Number num = (COBOL_Picture_Value_Number) picValue.getWhich();
							expression = generator.newNumberExpression(num.number.getValue(), num);
						}
						else if (picValue.getWhich() instanceof COBOL_Picture_Value_Literal)
						{
							COBOL_Picture_Value_Literal lit = (COBOL_Picture_Value_Literal) picValue.getWhich();
							expression = generator.newLiteralExpression(lit.literal.getValue(), lit);
						}
					}
					if (which instanceof COBOL_Usage)
					{
						COBOL_Usage usage = (COBOL_Usage) which;
						if (usage.type.getValue().toUpperCase().startsWith("COMP"))
						{
							isComp = true;
						}
					}
				}
			}

			if (isComp) // COMP, COMP-1, etc.
			{
				newType = generator.transformType(TypeEnum.INTEGER, null, null);
			}
			if (newType != null)
			{
				return generator.newDataDeclaration(
						StaticEnum.NONE, varName, null, newType, expression, this);
			}

			// Maybe it is an array definition
			EagleArray array = this.collectArrayValues();
			if (array != null)
			{
				ArrayList<EagleValue> values = array.getArrayValue();
				if (values != null && values.size() > 0)
				{
					ArrayList<AbstractExpression> newValues = new ArrayList<AbstractExpression>();
					for (EagleValue val : values)
					{
						String str = val.forceStringValue();
						AbstractExpression newExpr = generator.newLiteralExpression(str, this);
						newValues.add(newExpr);
					}

					AbstractExpression arrayExpr = generator.newArrayExpression(newValues, this);
					newType = generator.transformType(TypeEnum.ARRAY, varName, this);
					return generator.newDataDeclaration(
							StaticEnum.NONE, varName, null, newType, arrayExpr, this);
				}
			}
		}

		throw new RuntimeException("Unable to process: " + this);
	}

	public TypeEnum findDefinitionType(COBOL_Identifier_Reference id)
	{
		if (fieldName.getWhich() instanceof COBOL_Data_Definition)
		{
			COBOL_Data_Definition dataDef = (COBOL_Data_Definition) fieldName.getWhich();
			if (dataDef.getValue().equals(id.getValue()))
			{
				// Found it, finally!!
				String pic = null;
				if (clauses != null)
				{
					for (COBOL_DataClause clause : clauses._elements)
					{
						AbstractToken which = clause.getWhich();
						if (which instanceof COBOL_PictureClause)
						{
							COBOL_PictureClause picClause = (COBOL_PictureClause) which;
							pic = picClause.picture.getValue().toUpperCase();
						}
						if (which instanceof COBOL_Usage)
						{
							COBOL_Usage usage = (COBOL_Usage) which;
							if (usage.type.getValue().toUpperCase().startsWith("COMP"))
							{
								return TypeEnum.INTEGER;
							}
						}
					}
				}
				
				if (pic != null)
				{
					if (pic.startsWith("9") || pic.startsWith("X") || pic.startsWith("Z"))
					{
						return TypeEnum.STRING;
					}
				}
			}
		}
		return TypeEnum.OTHER;	// Can't find it
	}
}

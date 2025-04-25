// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2016

package com.eagle.programmar.Java.Generate;

public class Generate_Java_Data_Statement
{
//	public static Java_Data createData(Generate_Java target, PRIVACY privacy, int qual, int seq, String varName,
//			TYPES type, String userType, Java_Expression expr, String comment, AbstractToken source)
//	{
//		// Set the data type
//		String newTypeName = target.getTypeString(type, userType);
//		return createData(target, privacy, qual, seq, varName, newTypeName, expr, comment, source);
//	}
//
//	public static Java_Data createData(Generate_Java target, PRIVACY privacy, int qual, int seq, String varName,
//			String newTypeName, Java_Expression expr, String comment, AbstractToken source)
//	{
//		Java_Data dataWrapper = new Java_Data();
//		dataWrapper.modifiers = new TokenList<Java_DataModifier>();
//		dataWrapper.semicolon = new PunctuationSemicolon();
//		dataWrapper.setTransformationSource(source);
//
//		// Set public/private
//		Java_DataModifier mod = new Java_DataModifier();
//		String priv = target.getPrivacyString(privacy);
//		mod.setWhich(new Java_KeywordChoice(priv));
//		dataWrapper.modifiers.addToken(mod);
//
//		// Maybe set final, OPT, etc
//		if ((qual & DATA_QUALIFIERS.FINAL._value) != 0)
//		{
//			mod = new Java_DataModifier();
//			mod.setWhich(new Java_KeywordChoice("final"));
//			dataWrapper.modifiers.addToken(mod);
//		}
//		if ((qual & DATA_QUALIFIERS.STATIC._value) != 0)
//		{
//			mod = new Java_DataModifier();
//			mod.setWhich(new Java_KeywordChoice("static"));
//			dataWrapper.modifiers.addToken(mod);
//		}
//		if ((qual & DATA_QUALIFIERS.SEQUENCE._value) != 0)
//		{
//			addAnnotation2(target, dataWrapper, "S(" + seq + ")");
//		}
//		if ((qual & DATA_QUALIFIERS.OPTIONAL._value) != 0)
//		{
//			addAnnotation2(target, dataWrapper, "OPT");
//		}
//		if ((qual & DATA_QUALIFIERS.CHOICE._value) != 0)
//		{
//			addAnnotation2(target, dataWrapper, "CHOICE");
//		}
//
//		// Set data name
//		dataWrapper.id = new Java_Variable_Definition();
//		dataWrapper.id.setValue(varName);
//
//		// Always set the data type
//		dataWrapper.jtype = new Java_Type();
//		Java_TypeName typeName = new Java_TypeName();
//		typeName.setWhich(new Java_KeywordChoice(newTypeName));
//		dataWrapper.jtype.typeName = typeName;
//
//		// Set the initial value, if any
//		if (expr != null)
//		{
//			Java_DataInitialValue init = new Java_DataInitialValue();
//			init.equals = new PunctuationEquals();
//			init.expression = expr;
//			dataWrapper.initialValue = init;
//			dataWrapper.initialValue.setPresent(true);
//		}
//
//		// Maybe there was a comment
//		if (comment != null && comment.length() > 0)
//		{
//			dataWrapper.comments = new TokenList<>();
//			dataWrapper.comments.addToken(new Java_Comment("// " + comment));
//		}
//
//		return dataWrapper;
//	}
//
//	private static void addAnnotation2(Generate_Java target, Java_Data data, String name)
//	{
//		if (data.annotation2 == null) data.annotation2 = new TokenList<Java_Annotation>();
//		Java_Annotation annotation = target.createAnnotation(name);
//		data.annotation2.addToken(annotation);
//	}
}

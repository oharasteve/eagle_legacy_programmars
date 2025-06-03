// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2016

package com.eagle.programmar.CSharp.Generate_Unused;

public class Generate_CSharp_Data_Statement
{
//	public static CSharp_Data createData(Generate_CSharp target, PRIVACY privacy, int qual, int seq, String varName,
//			TYPES type, String userType, CSharp_Expression expr, String comment, AbstractToken source)
//	{
//		// Set the data type
//		String newTypeName = target.getTypeString(type, userType);
//		return createData(target, privacy, qual, seq, varName, newTypeName, expr, comment, source);
//	}
//
//	private static void addDataModifier(CSharp_DataBeforeSemicolon data, String mod)
//	{
//		if (data.modifiers == null)
//		{
//			data.modifiers = new TokenList<CSharp_DataModifier>();
//			data.modifiers.setPresent(true);
//		}
//		CSharp_DataModifier modifier = new CSharp_DataModifier();
//		modifier.modifier.setValue(mod);
//		data.modifiers.addToken(modifier);
//	}
//
//	public static CSharp_Data createData(Generate_CSharp target, PRIVACY privacy,
//			int qual, int seq, String varName, String newTypeName,
//			CSharp_Expression expr, String comment, AbstractToken source)
//	{
//		CSharp_DataBeforeSemicolon data = new CSharp_DataBeforeSemicolon();
//		data.modifiers = new TokenList<CSharp_DataModifier>();
//
//		// Set public/private
//		switch (privacy)
//		{
//		case PUBLIC:
//			addDataModifier(data, "public");
//			break;
//		case PRIVATE:
//			addDataModifier(data, "private");
//			break;
//		case PROTECTED:
//			addDataModifier(data, "protected");
//			addDataModifier(data, "internal");
//			break;
//		case PACKAGE:
//			break;
//		default:
//			throw new RuntimeException("Can't handle data privacy: " + privacy);
//		}
//
//		// Set data name
//		data.id = new CSharp_Variable_Definition();
//		data.id.setValue(varName);
//
//		// Always set the data type
//		data.type = new CSharp_Type();
//		CSharp_TypeName typeName = new CSharp_TypeName();
//		typeName.setWhich(new CSharp_KeywordChoice(newTypeName));
//		data.type.typeName = typeName;
//
//		// Set the initial value, if any
//		if (expr != null)
//		{
//			CSharp_DataInitialValue init = new CSharp_DataInitialValue();
//			init.setPresent(true);
//			init.equals = new PunctuationEquals();
//			init.expression = expr;
//			data.initialValue = init;
//			data.initialValue.setPresent(true);
//		}
//
//		CSharp_Data dataWrapper = new CSharp_Data();
//		dataWrapper.dataBody = data;
//		dataWrapper.semicolon = new PunctuationSemicolon();
//		dataWrapper.setTransformationSource(source);
//
//		// Maybe there was a comment
//		if (comment != null && comment.length() > 0)
//		{
//			dataWrapper.comments = new TokenList<>();
//			dataWrapper.comments.addToken(new CSharp_Comment("// " + comment));
//		}
//
//		// Maybe set final, OPT, etc
//		if ((qual & DATA_QUALIFIERS.FINAL._value) != 0)
//		{
//			addDataModifier(data, "final");
//		}
//		if ((qual & DATA_QUALIFIERS.STATIC._value) != 0)
//		{
//			addDataModifier(data, "static");
//		}
//		if ((qual & DATA_QUALIFIERS.OPTIONAL._value) != 0)
//		{
//			addAnnotation(target, dataWrapper, "OPT");
//		}
//		if ((qual & DATA_QUALIFIERS.CHOICE._value) != 0)
//		{
//			addAnnotation(target, dataWrapper, "CHOICE");
//		}
//		if ((qual & DATA_QUALIFIERS.SEQUENCE._value) != 0)
//		{
//			addAnnotation(target, dataWrapper, "S(" + seq + ")");
//		}
//
//		return dataWrapper;
//	}
//
//	private static void addAnnotation(Generate_CSharp target, CSharp_Data data, String name)
//	{
//		if (data.dataBody.annotation1 == null) data.dataBody.annotation1 = new TokenList<CSharp_Annotation>();
//		CSharp_Annotation annotation = target.createAnnotation(name);
//		data.dataBody.annotation1.addToken(annotation);
//	}
}

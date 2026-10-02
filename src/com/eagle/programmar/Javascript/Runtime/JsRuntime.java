// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: the JavaScript runtime the grammar's interpret
// methods share: environments and closures, calls and `new`, properties, the built-ins a
// small program expects (console, Object, Math, JSON, Map, Set, array and string methods).
// The engine's own symbol table is still written for every variable, so the profile the
// generators read is unchanged; reads come from the lexical environment first.

package com.eagle.programmar.Javascript.Runtime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.programmar.Javascript.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
import com.eagle.programmar.Javascript.Javascript_Pattern;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Expressions.Javascript_ClassExpr;
import com.eagle.programmar.Javascript.Expressions.Javascript_EllipsisExpr;
import com.eagle.programmar.Javascript.Functions.Javascript_FunctionExpr;
import com.eagle.programmar.Javascript.Functions.Javascript_LambdaFunction;
import com.eagle.programmar.Javascript.Functions.Javascript_LambdaFunction.Javascript_LambdaParam;
import com.eagle.programmar.Javascript.Javascript_Class;
import com.eagle.programmar.Javascript.Javascript_Class.Javascript_ClassElement;
import com.eagle.programmar.Javascript.Javascript_Class.Javascript_ClassField;
import com.eagle.programmar.Javascript.Javascript_Class.Javascript_MemberName;
import com.eagle.programmar.Javascript.Javascript_Class.Javascript_Method;
import com.eagle.programmar.Javascript.Javascript_Class.Javascript_StaticBlock;
import com.eagle.programmar.Javascript.Runtime.JsValues.JsFunction;
import com.eagle.programmar.Javascript.Runtime.JsValues.JsObject;
import com.eagle.programmar.Javascript.Runtime.JsValues.JsThrow;
import com.eagle.programmar.Javascript.Runtime.JsValues.JsUndefined;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Symbols.Javascript_Field_Definition;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenList;

public final class JsRuntime
{
	private static final WeakHashMap<EagleInterpreter, JsRuntime> RUNTIMES = new WeakHashMap<EagleInterpreter, JsRuntime>();

	public static JsRuntime of(EagleInterpreter interpreter)
	{
		synchronized (RUNTIMES)
		{
			JsRuntime r = RUNTIMES.get(interpreter);
			if (r == null)
			{
				r = new JsRuntime(interpreter);
				RUNTIMES.put(interpreter, r);
			}
			return r;
		}
	}

	public final EagleInterpreter interpreter;
	public final JsEnv global = new JsEnv(null);
	public JsEnv env = global;
	private final ArrayList<EagleValue> thisStack = new ArrayList<EagleValue>();
	private final ArrayList<JsFunction> functionStack = new ArrayList<JsFunction>();
	public int depth = 0;

	private JsRuntime(EagleInterpreter interpreter)
	{
		this.interpreter = interpreter;
		JsBuiltins.install(this);
	}

	// ------------------------------------------------------------ lists

	/** A list that parsed something: isPresent() is only set on optional elements, so the list is asked directly. */
	public static boolean has(TokenList<?> list)
	{
		return list != null && list._elements != null && !list._elements.isEmpty();
	}

	public static int count(SeparatedList<?, ?> list)
	{
		if (list == null) return 0;
		try { return list.getPrimaryCount(); } catch (RuntimeException ex) { return 0; }
	}

	// ------------------------------------------------------------ variables

	public EagleValue read(String name)
	{
		EagleValue v = env.lookup(name);
		if (v != null) return v;
		try
		{
			v = interpreter.findSymbol(name);
		}
		catch (RuntimeException ex)
		{
			v = null;
		}
		if (v != null) return v;
		throw new JsThrow(JsValues.error("ReferenceError: " + name + " is not defined"));
	}

	public boolean isDefined(String name)
	{
		if (env.lookup(name) != null) return true;
		try { return interpreter.findSymbol(name) != null; } catch (RuntimeException ex) { return false; }
	}

	/** let/const/var/parameter: in this environment, and in the engine's table for the profile. */
	public void declare(AbstractToken token, String name, EagleValue value)
	{
		env.define(name, value);
		profile(token, name, value);
	}

	public void assign(AbstractToken token, String name, EagleValue value)
	{
		env.assign(name, value);
		profile(token, name, value);
	}

	private void profile(AbstractToken token, String name, EagleValue value)
	{
		try
		{
			interpreter.setSymbol(token, name, JsValues.of(value));
		}
		catch (RuntimeException ex)
		{
			// The engine's table could not take it (a value kind it does not know); the
			// lexical environment has it, which is what the program needs.
		}
	}

	public EagleValue thisValue()
	{
		return thisStack.isEmpty() ? JsValues.undefined() : thisStack.get(thisStack.size() - 1);
	}

	public JsFunction currentFunction()
	{
		return functionStack.isEmpty() ? null : functionStack.get(functionStack.size() - 1);
	}

	// ------------------------------------------------------------ evaluation helpers

	public EagleValue eval(AbstractToken expr)
	{
		if (expr == null || !expr.isPresent()) return JsValues.undefined();
		return JsValues.of(interpreter.getEagleValue(expr));
	}

	/** Arguments of a call, with ...spread expanded. */
	public List<EagleValue> args(SeparatedList<Javascript_Expression, ?> list)
	{
		ArrayList<EagleValue> out = new ArrayList<EagleValue>();
		for (int i = 0; i < count(list); i++)
		{
			Javascript_Expression e = list.getPrimaryElement(i);
			if (e.getWhich() instanceof Javascript_EllipsisExpr)
			{
				EagleValue spread = eval(((Javascript_EllipsisExpr) e.getWhich()).expr);
				if (spread.isArray()) out.addAll(JsValues.items(spread));
				else if (spread.isString()) for (char c : spread.forceStringValue().toCharArray()) out.add(JsValues.str(String.valueOf(c)));
				continue;
			}
			out.add(eval(e));
		}
		return out;
	}

	public static EagleValue arg(List<EagleValue> args, int i)
	{
		return i < args.size() ? JsValues.of(args.get(i)) : JsValues.undefined();
	}

	// ------------------------------------------------------------ statements

	/** Runs a body: function declarations first (hoisting), then each statement in order. */
	public Eagle_Statement_Result runStatements(TokenList<Javascript_StatementOrComment> statements)
	{
		if (!has(statements)) return Eagle_Statement_Result.NORMAL;
		hoist(statements);
		for (Javascript_StatementOrComment stmt : statements._elements)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
			if (result != null && result != Eagle_Statement_Result.NORMAL) return result;
		}
		return Eagle_Statement_Result.NORMAL;
	}

	public void hoist(TokenList<Javascript_StatementOrComment> statements)
	{
		for (Javascript_StatementOrComment stmt : statements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof Javascript_Statement) which = ((Javascript_Statement) which).getWhich();
			hoistOne(which);
		}
	}

	/** A function or class declaration becomes a value in the current environment. */
	public void hoistOne(AbstractToken which)
	{
		if (which instanceof Javascript_Element) which = ((Javascript_Element) which).statement.getWhich();
		if (which instanceof Javascript_Statement) which = ((Javascript_Statement) which).getWhich();
		if (which instanceof com.eagle.programmar.Javascript.Statements.Javascript_ExpressionStmt)
		{
			AbstractToken expr = ((com.eagle.programmar.Javascript.Statements.Javascript_ExpressionStmt) which).expression.getWhich();
			if (expr instanceof Javascript_ClassExpr && ((Javascript_ClassExpr) expr).className != null && ((Javascript_ClassExpr) expr).className.isPresent())
			{
				Javascript_ClassExpr c = (Javascript_ClassExpr) expr;
				if (!env.hasLocal(c.className.getValue())) env.define(c.className.getValue(), makeClass(c.className.getValue(), c.extend, c.elements, c));
			}
			return;
		}
		if (which instanceof Javascript_Function)
		{
			Javascript_Function f = (Javascript_Function) which;
			if (f.implementation.id != null && f.implementation.id.isPresent())
			{
				String name = f.implementation.id.getValue();
				if (!env.hasLocal(name)) env.define(name, new JsFunction(name, f, env));
				try { interpreter.addFunction(name, f); } catch (RuntimeException ex) { /* already known */ }
			}
		}
		else if (which instanceof Javascript_Class)
		{
			Javascript_Class c = (Javascript_Class) which;
			if (c.name != null && c.name.isPresent() && !env.hasLocal(c.name.getValue()))
				env.define(c.name.getValue(), makeClass(c.name.getValue(), c.extend, c.elements, c));
		}
	}

	// ------------------------------------------------------------ calls

	public EagleValue call(EagleValue callee, EagleValue thisValue, List<EagleValue> args, AbstractToken callSite)
	{
		if (!(callee instanceof JsFunction))
			throw new JsThrow(JsValues.error("TypeError: " + describe(callSite) + " is not a function"));
		JsFunction f = (JsFunction) callee;
		if (f.boundThis != null) thisValue = f.boundThis;
		if (f.builtin != null) return JsValues.of(f.builtin.call(interpreter, thisValue, args));
		if (++depth > 2000) { depth = 0; throw new JsThrow(JsValues.error("RangeError: Maximum call stack size exceeded")); }

		AbstractToken token = f.getTokenValue();
		JsEnv saved = env;
		env = new JsEnv(f.closure);
		thisStack.add(JsValues.of(thisValue));
		functionStack.add(f);
		long started = System.nanoTime();
		try
		{
			if (token instanceof Javascript_Function) return callFunction(f, (Javascript_Function) token, args, callSite, started);
			if (token instanceof Javascript_FunctionExpr) return callFunction(f, ((Javascript_FunctionExpr) token).function, args, callSite, started);
			if (token instanceof Javascript_LambdaFunction) return callLambda((Javascript_LambdaFunction) token, args);
			if (token instanceof Javascript_Method) return callMethod((Javascript_Method) token, args);
			if (token instanceof Javascript_Function.Javascript_FunctionImplementation)
			{
				Javascript_Function.Javascript_FunctionImplementation impl = (Javascript_Function.Javascript_FunctionImplementation) token;
				bindParameters(impl.params, args);
				return runBody(impl.body);
			}
			throw new RuntimeException("Cannot call a " + token.getClass().getSimpleName());
		}
		finally
		{
			depth--;
			functionStack.remove(functionStack.size() - 1);
			thisStack.remove(thisStack.size() - 1);
			env = saved;
		}
	}

	private EagleValue callFunction(JsFunction f, Javascript_Function func, List<EagleValue> args, AbstractToken callSite, long started)
	{
		String name = f.name;
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (EagleValue a : args) argTypes.add(a.getType());
		bindParameters(func.implementation.params, args);
		boolean declared = func.implementation.id != null && func.implementation.id.isPresent();
		if (declared) interpreter.tryToInterpret(func); // lets the function create its metrics objects
		if (declared) interpreter.callingFunction(name, func);
		EagleValue result;
		try
		{
			result = runBody(func.implementation.body);
		}
		finally
		{
			if (declared) interpreter.completedFunction(name, func);
		}
		if (declared && func._callMetrics != null)
		{
			func._callMetrics.addCallFrom(callSite, System.nanoTime() - started);
			func._argumentsMetrics.calledWith(argTypes);
		}
		return result;
	}

	private EagleValue callLambda(Javascript_LambdaFunction lambda, List<EagleValue> args)
	{
		AbstractToken params = lambda.params.getWhich();
		if (params instanceof Javascript_LambdaParam)
		{
			bindLambdaParam((Javascript_LambdaParam) params, arg(args, 0));
		}
		else
		{
			Javascript_LambdaFunction.Javascript_LambdaParams.Javascript_LambdaManyParams many =
					(Javascript_LambdaFunction.Javascript_LambdaParams.Javascript_LambdaManyParams) params;
			{
				for (int i = 0; i < count(many.params); i++)
				{
					Javascript_LambdaParam p = many.params.getPrimaryElement(i);
					if (p.rest != null && p.rest.isPresent())
					{
						EagleArray rest = JsValues.newArray();
						for (int j = i; j < args.size(); j++) rest.addValue(args.get(j));
						bindPattern(p.param.getWhich(), rest, true);
						break;
					}
					bindLambdaParam(p, arg(args, i));
				}
			}
		}
		AbstractToken body = lambda.body.getWhich();
		if (body instanceof Javascript_FunctionBody) return runBody((Javascript_FunctionBody) body);
		return eval(body);
	}

	private void bindLambdaParam(Javascript_LambdaParam p, EagleValue value)
	{
		if (value instanceof JsUndefined && p.init != null && p.init.isPresent()) value = eval(p.init.initValue);
		bindPattern(p.param.getWhich(), value, true);
	}

	private EagleValue callMethod(Javascript_Method method, List<EagleValue> args)
	{
		bindParameters(method.params, args);
		if (method.body == null || !method.body.isPresent()) return JsValues.undefined();
		return runBody(method.body);
	}

	public void bindParameters(Javascript_FunctionParameters params, List<EagleValue> args)
	{
		if (params == null || !params.isPresent()) return;
		ArrayList<Javascript_FunctionParameter> all = new ArrayList<Javascript_FunctionParameter>();
		all.add(params.param);
		if (has(params.moreParams))
			for (Javascript_FunctionParameters.Javascript_MoreParameters more : params.moreParams._elements) all.add(more.param);
		for (int i = 0; i < all.size(); i++)
		{
			Javascript_FunctionParameter p = all.get(i);
			if (p.rest != null && p.rest.isPresent())
			{
				EagleArray rest = JsValues.newArray();
				for (int j = i; j < args.size(); j++) rest.addValue(args.get(j));
				bindPattern(p.paramName.getWhich(), rest, true);
				break;
			}
			EagleValue value = arg(args, i);
			if (value instanceof JsUndefined && p.value != null && p.value.isPresent()) value = eval(p.value.initValue);
			bindPattern(p.paramName.getWhich(), value, true);
			// A TypeScript constructor parameter with a modifier is also a field on this.
			if (p.modifier != null && p.modifier.isPresent() && thisValue() instanceof JsObject
					&& p.paramName.getWhich() instanceof Javascript_Variable_Definition)
				((JsObject) thisValue()).set(((Javascript_Variable_Definition) p.paramName.getWhich()).getValue(), value);
		}
	}

	private EagleValue runBody(Javascript_FunctionBody body)
	{
		Eagle_Statement_Result result = runStatements(body.statements);
		if (result == Eagle_Statement_Result.RETURN) return JsValues.of(interpreter.popEagleValue());
		return JsValues.undefined();
	}

	// ------------------------------------------------------------ patterns

	/** Binds a name or a destructuring pattern to a value, declaring or assigning. */
	public void bindPattern(AbstractToken target, EagleValue value, boolean declare)
	{
		value = JsValues.of(value);
		if (target instanceof Javascript_Variable_Definition)
		{
			String name = ((Javascript_Variable_Definition) target).getValue();
			if (declare) declare(target, name, value); else assign(target, name, value);
		}
		else if (target instanceof Javascript_Pattern.Javascript_ObjectPattern)
		{
			Javascript_Pattern.Javascript_ObjectPattern pattern = (Javascript_Pattern.Javascript_ObjectPattern) target;
			ArrayList<String> taken = new ArrayList<String>();
			{
				for (int i = 0; i < count(pattern.properties); i++)
				{
					AbstractToken prop = pattern.properties.getPrimaryElement(i).getWhich();
					if (prop instanceof Javascript_Pattern.Javascript_PatternBinding)
					{
						Javascript_Pattern.Javascript_PatternBinding b = (Javascript_Pattern.Javascript_PatternBinding) prop;
						String name = b.id.getValue();
						taken.add(name);
						EagleValue v = getProperty(value, name, true);
						if (v instanceof JsUndefined && b.initial != null && b.initial.isPresent()) v = eval(b.initial.expr);
						bindPattern(b.id, v, declare);
					}
					else if (prop instanceof Javascript_Pattern.Javascript_PatternRenamed)
					{
						Javascript_Pattern.Javascript_PatternRenamed r = (Javascript_Pattern.Javascript_PatternRenamed) prop;
						AbstractToken keyToken = r.key.getWhich();
						String key = keyToken instanceof Javascript_Literal ? ((Javascript_Literal) keyToken).removeQuotes() : ((TerminalToken) keyToken).getValue();
						taken.add(key);
						EagleValue v = getProperty(value, key, true);
						if (v instanceof JsUndefined && r.initial != null && r.initial.isPresent()) v = eval(r.initial.expr);
						bindPattern(r.target.getWhich(), v, declare);
					}
					else if (prop instanceof Javascript_Pattern.Javascript_PatternRest)
					{
						JsObject rest = new JsObject();
						if (value instanceof JsObject)
							for (String k : ((JsObject) value).keys()) if (!taken.contains(k)) rest.set(k, ((JsObject) value).get(k));
						bindPattern(((Javascript_Pattern.Javascript_PatternRest) prop).id, rest, declare);
					}
				}
			}
		}
		else if (target instanceof Javascript_Pattern.Javascript_ArrayPattern)
		{
			Javascript_Pattern.Javascript_ArrayPattern pattern = (Javascript_Pattern.Javascript_ArrayPattern) target;
			List<EagleValue> items = value.isArray() ? JsValues.items(value) : Collections.<EagleValue>emptyList();
			int index = 0;
			if (has(pattern.elements))
			{
				for (Javascript_Pattern.Javascript_ArrayPatternElement element : pattern.elements._elements)
				{
					AbstractToken which = element.getWhich();
					if (!(which instanceof Javascript_Pattern.Javascript_ArrayPatternItem)) { index++; continue; } // a hole
					Javascript_Pattern.Javascript_ArrayPatternItem item = (Javascript_Pattern.Javascript_ArrayPatternItem) which;
					AbstractToken t = item.target.getWhich();
					if (t instanceof Javascript_Pattern.Javascript_PatternRest)
					{
						EagleArray rest = JsValues.newArray();
						for (int j = index; j < items.size(); j++) rest.addValue(items.get(j));
						bindPattern(((Javascript_Pattern.Javascript_PatternRest) t).id, rest, declare);
						break;
					}
					EagleValue v = index < items.size() ? items.get(index) : JsValues.undefined();
					if (v instanceof JsUndefined && item.initial != null && item.initial.isPresent()) v = eval(item.initial.expr);
					bindPattern(t, v, declare);
					index++;
				}
			}
		}
		else
		{
			throw new RuntimeException("Cannot bind to " + target.getClass().getSimpleName());
		}
	}

	// ------------------------------------------------------------ properties

	public EagleValue getProperty(EagleValue target, String name, boolean lenient)
	{
		target = JsValues.of(target);
		if (JsValues.isNullish(target))
		{
			if (lenient) return JsValues.undefined();
			throw new JsThrow(JsValues.error("TypeError: Cannot read properties of " + target.forceStringValue() + " (reading '" + name + "')"));
		}
		if (target instanceof JsObject)
		{
			JsObject o = (JsObject) target;
			if (o.store != null && "size".equals(name)) return JsValues.num(o.store.size());
			EagleValue v = o.get(name);
			if (v != null) return v;
			JsFunction builtin = JsBuiltins.objectMethod(o, name);
			return builtin != null ? builtin : JsValues.undefined();
		}
		if (target instanceof JsFunction)
		{
			JsFunction f = (JsFunction) target;
			if ("prototype".equals(name)) return f.prototype == null ? JsValues.undefined() : f.prototype;
			if ("name".equals(name)) return JsValues.str(f.name);
			EagleValue v = f.statics.get(name);
			if (v == null && f.parent != null) v = getProperty(f.parent, name, true);
			return v == null ? JsValues.undefined() : v;
		}
		if (target.isArray())
		{
			EagleArray a = (EagleArray) target;
			if ("length".equals(name)) return JsValues.num(JsValues.items(a).size());
			JsFunction m = JsBuiltins.arrayMethod(name);
			return m != null ? m : JsValues.undefined();
		}
		if (target.isString())
		{
			if ("length".equals(name)) return JsValues.num(target.forceStringValue().length());
			JsFunction m = JsBuiltins.stringMethod(name);
			return m != null ? m : JsValues.undefined();
		}
		if (JsValues.isNumber(target))
		{
			JsFunction m = JsBuiltins.numberMethod(name);
			return m != null ? m : JsValues.undefined();
		}
		return JsValues.undefined();
	}

	public void setProperty(EagleValue target, String name, EagleValue value)
	{
		target = JsValues.of(target);
		if (target instanceof JsObject) ((JsObject) target).set(name, value);
		else if (target instanceof JsFunction) ((JsFunction) target).statics.set(name, value);
		else if (target.isArray() && "length".equals(name))
		{
			List<EagleValue> items = JsValues.items(target);
			int n = (int) JsValues.toNumber(value);
			while (items.size() > n) items.remove(items.size() - 1);
			while (items.size() < n) items.add(JsValues.undefined());
		}
		else throw new JsThrow(JsValues.error("TypeError: Cannot set properties of " + JsValues.typeOf(target) + " (setting '" + name + "')"));
	}

	public EagleValue getIndex(EagleValue target, EagleValue key, boolean lenient)
	{
		target = JsValues.of(target);
		if (target.isArray() && JsValues.isNumber(key))
		{
			List<EagleValue> items = JsValues.items(target);
			int i = (int) JsValues.toNumber(key);
			return i >= 0 && i < items.size() ? JsValues.of(items.get(i)) : JsValues.undefined();
		}
		if (target.isString() && JsValues.isNumber(key))
		{
			String s = target.forceStringValue();
			int i = (int) JsValues.toNumber(key);
			return i >= 0 && i < s.length() ? JsValues.str(String.valueOf(s.charAt(i))) : JsValues.undefined();
		}
		return getProperty(target, JsValues.toText(key), lenient);
	}

	public void setIndex(EagleValue target, EagleValue key, EagleValue value)
	{
		target = JsValues.of(target);
		if (target.isArray() && JsValues.isNumber(key))
		{
			List<EagleValue> items = JsValues.items(target);
			int i = (int) JsValues.toNumber(key);
			while (items.size() <= i) items.add(JsValues.undefined());
			items.set(i, JsValues.of(value));
			return;
		}
		setProperty(target, JsValues.toText(key), value);
	}

	/** target.name(args), with this bound. */
	public EagleValue callMethod(EagleValue target, String name, List<EagleValue> args, AbstractToken callSite)
	{
		EagleValue f = getProperty(target, name, false);
		if (!(f instanceof JsFunction))
			throw new JsThrow(JsValues.error("TypeError: " + describe(callSite) + "." + name + " is not a function"));
		return call(f, target, args, callSite);
	}

	// ------------------------------------------------------------ classes and new

	public JsFunction makeClass(String name, Javascript_Class.Javascript_ClassExtends extend, TokenList<Javascript_ClassElement> elements, AbstractToken token)
	{
		JsFunction cls = new JsFunction(name, token, env);
		cls.className(name);
		if (extend != null && extend.isPresent())
		{
			String parentName = extend.name.getPrimaryElement(0).getValue();
			EagleValue parent = read(parentName);
			if (parent instanceof JsFunction)
			{
				cls.parent = (JsFunction) parent;
				cls.prototype.proto = cls.parent.prototype;
			}
		}
		if (has(elements))
		{
			for (Javascript_ClassElement element : elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which instanceof Javascript_Method)
				{
					Javascript_Method m = (Javascript_Method) which;
					String memberName = memberName(m.name);
					JsFunction fn = new JsFunction(memberName, m, env);
					boolean isStatic = isStatic(m.modifiers, m.STATIC);
					(isStatic ? cls.statics : cls.prototype).set(memberName, fn);
				}
				else if (which instanceof Javascript_ClassField)
				{
					Javascript_ClassField field = (Javascript_ClassField) which;
					if (isStatic(field.modifiers, field.STATIC))
						cls.statics.set(memberName(field.name), field.value != null && field.value.isPresent() ? eval(field.value.expr) : JsValues.undefined());
				}
				else if (which instanceof Javascript_StaticBlock)
				{
					thisStack.add(cls);
					try { runStatements(((Javascript_StaticBlock) which).body.statements); }
					finally { thisStack.remove(thisStack.size() - 1); }
				}
			}
		}
		return cls;
	}

	private static boolean isStatic(Javascript_Class.Javascript_ClassModifiers modifiers, TerminalToken staticKeyword)
	{
		if (staticKeyword != null && staticKeyword.isPresent()) return true;
		return modifiers != null && modifiers.isPresent() && modifiers.second != null && modifiers.second.isPresent()
				&& "static".equals(modifiers.second.getValue());
	}

	public static String memberName(Javascript_MemberName name)
	{
		AbstractToken which = name.getWhich();
		if (which instanceof Javascript_MemberName.Javascript_PrivateName) return "#" + ((Javascript_MemberName.Javascript_PrivateName) which).id.getValue();
		if (which instanceof Javascript_Literal) return ((Javascript_Literal) which).removeQuotes();
		if (which instanceof TerminalToken) return ((TerminalToken) which).getValue();
		return which.toString();
	}

	/** new C(args): an object with C.prototype behind it, fields, then the constructor chain. */
	public EagleValue construct(EagleValue callee, List<EagleValue> args, AbstractToken callSite)
	{
		if (!(callee instanceof JsFunction))
			throw new JsThrow(JsValues.error("TypeError: " + describe(callSite) + " is not a constructor"));
		JsFunction cls = (JsFunction) callee;
		if (cls.builtin != null && cls.statics.get("[[construct]]") instanceof JsFunction)
			return call(cls.statics.get("[[construct]]"), JsValues.undefined(), args, callSite);
		JsObject obj = new JsObject(cls.prototype);
		obj.className = cls.name;
		AbstractToken token = cls.getTokenValue();
		if (token instanceof Javascript_Class || token instanceof Javascript_ClassExpr)
		{
			runConstructor(cls, obj, args, callSite);
		}
		else
		{
			// A plain function used as a constructor: run it with this = the new object.
			EagleValue returned = call(cls, obj, args, callSite);
			if (returned instanceof JsObject) return returned;
		}
		return obj;
	}

	private void runConstructor(JsFunction cls, JsObject obj, List<EagleValue> args, AbstractToken callSite)
	{
		AbstractToken token = cls.getTokenValue();
		TokenList<Javascript_ClassElement> elements = token instanceof Javascript_Class ? ((Javascript_Class) token).elements : ((Javascript_ClassExpr) token).elements;
		Javascript_Method constructor = null;
		if (has(elements))
			for (Javascript_ClassElement element : elements._elements)
				if (element.getWhich() instanceof Javascript_Method && "constructor".equals(memberName(((Javascript_Method) element.getWhich()).name)))
					constructor = (Javascript_Method) element.getWhich();
		if (constructor == null)
		{
			// No constructor: the parent's runs with the same arguments, then the fields.
			if (cls.parent != null) runConstructor(cls.parent, obj, args, callSite);
			initFields(cls, obj, elements);
			return;
		}
		// With a constructor: fields are set when super() returns (or first, with no parent).
		if (cls.parent == null) initFields(cls, obj, elements);
		JsFunction ctor = new JsFunction("constructor", constructor, cls.closure);
		ctor.statics.set("[[class]]", cls);
		call(ctor, obj, args, callSite);
	}

	/** super(args) inside a constructor: the parent's constructor on the same object, then this class's fields. */
	public void superCall(List<EagleValue> args, AbstractToken callSite)
	{
		JsFunction ctor = currentFunction();
		JsFunction cls = ctor == null ? null : (JsFunction) ctor.statics.get("[[class]]");
		if (cls == null || !(thisValue() instanceof JsObject)) throw new JsThrow(JsValues.error("SyntaxError: 'super' keyword unexpected here"));
		if (cls.parent != null) runConstructor(cls.parent, (JsObject) thisValue(), args, callSite);
		AbstractToken token = cls.getTokenValue();
		initFields(cls, (JsObject) thisValue(), token instanceof Javascript_Class ? ((Javascript_Class) token).elements : ((Javascript_ClassExpr) token).elements);
	}

	private void initFields(JsFunction cls, JsObject obj, TokenList<Javascript_ClassElement> elements)
	{
		if (!has(elements)) return;
		JsEnv saved = env;
		env = new JsEnv(cls.closure);
		thisStack.add(obj);
		try
		{
			for (Javascript_ClassElement element : elements._elements)
			{
				if (!(element.getWhich() instanceof Javascript_ClassField)) continue;
				Javascript_ClassField field = (Javascript_ClassField) element.getWhich();
				if (isStatic(field.modifiers, field.STATIC)) continue;
				obj.set(memberName(field.name), field.value != null && field.value.isPresent() ? eval(field.value.expr) : JsValues.undefined());
			}
		}
		finally
		{
			thisStack.remove(thisStack.size() - 1);
			env = saved;
		}
	}

	public boolean instanceOf(EagleValue value, EagleValue cls)
	{
		if (!(value instanceof JsObject) || !(cls instanceof JsFunction)) return false;
		JsObject target = ((JsFunction) cls).prototype;
		for (JsObject p = ((JsObject) value).proto; p != null; p = p.proto) if (p == target) return true;
		return false;
	}

	// ------------------------------------------------------------ odds and ends

	public static String describe(AbstractToken token)
	{
		if (token == null) return "expression";
		String s = token.toString();
		return s.length() > 40 ? s.substring(0, 40) : s;
	}

	public static String keyText(AbstractToken key)
	{
		if (key instanceof Javascript_Literal) return ((Javascript_Literal) key).removeQuotes();
		if (key instanceof Javascript_Number) return ((Javascript_Number) key).getValue();
		if (key instanceof Javascript_Field_Definition || key instanceof Javascript_Function_Definition || key instanceof TerminalToken) return ((TerminalToken) key).getValue();
		return key.toString();
	}

	/** `text ${expr} more`: the expressions are parsed and evaluated here. */
	public EagleValue template(String raw)
	{
		String body = raw.substring(1, raw.length() - 1);
		StringBuilder out = new StringBuilder();
		int i = 0;
		while (i < body.length())
		{
			char c = body.charAt(i);
			if (c == '\\' && i + 1 < body.length())
			{
				char n = body.charAt(i + 1);
				out.append(n == 'n' ? '\n' : n == 't' ? '\t' : n);
				i += 2;
				continue;
			}
			if (c == '$' && i + 1 < body.length() && body.charAt(i + 1) == '{')
			{
				int depth = 0, j = i + 2;
				for (; j < body.length(); j++)
				{
					char d = body.charAt(j);
					if (d == '{') depth++;
					else if (d == '}') { if (depth == 0) break; depth--; }
					else if (d == '`') { j = body.indexOf('`', j + 1); if (j < 0) j = body.length() - 1; }
				}
				String expr = body.substring(i + 2, j).trim();
				out.append(JsValues.toText(evalSource(expr)));
				i = j + 1;
				continue;
			}
			out.append(c);
			i++;
		}
		return JsValues.str(out.toString());
	}

	/** Parses and evaluates one expression written as text, in the current environment. */
	public EagleValue evalSource(String source)
	{
		Javascript_Expression expr = new Javascript_Expression();
		boolean ok = interpreter._parser.parseLine(source, interpreter._lang, expr);
		if (!ok) throw new JsThrow(JsValues.error("SyntaxError: cannot parse `" + source + "`"));
		return eval(expr);
	}
}

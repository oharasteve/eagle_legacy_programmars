// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: the values JavaScript needs beyond the engine's
// integers, strings, booleans and arrays: undefined, null, objects, functions, thrown values.

package com.eagle.programmar.Javascript.Runtime;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleBoolean;
import com.eagle.math.EagleDouble;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.math.EagleToken;
import com.eagle.math.EagleValue;
import com.eagle.tokens.AbstractToken;

public final class JsValues
{
	private JsValues() { }

	/** undefined */
	public static final class JsUndefined extends EagleValue
	{
		public static final JsUndefined INSTANCE = new JsUndefined();
		@Override public TypeEnum getType() { return TypeEnum.VOID; }
		@Override public boolean forceBooleanValue() { return false; }
		@Override public int forceIntegerValue() { return 0; }
		@Override public double forceDoubleValue() { return Double.NaN; }
		@Override public String forceStringValue() { return "undefined"; }
		@Override public String toString() { return "undefined"; }
	}

	/** null */
	public static final class JsNull extends EagleValue
	{
		public static final JsNull INSTANCE = new JsNull();
		@Override public TypeEnum getType() { return TypeEnum.OTHER; }
		@Override public boolean forceBooleanValue() { return false; }
		@Override public int forceIntegerValue() { return 0; }
		@Override public double forceDoubleValue() { return 0; }
		@Override public String forceStringValue() { return "null"; }
		@Override public String toString() { return "null"; }
	}

	/** An object: ordered string keys, a prototype chain, and for Map/Set an internal store. */
	public static class JsObject extends EagleValue
	{
		public final LinkedHashMap<String, EagleValue> props = new LinkedHashMap<String, EagleValue>();
		public JsObject proto;
		public String className = "Object";
		/** For Map and Set: the entries, by the key's string form. */
		public LinkedHashMap<String, EagleValue[]> store;

		public JsObject() { }
		public JsObject(JsObject proto) { this.proto = proto; }

		@Override public boolean isHash() { return true; }
		@Override public TypeEnum getType() { return TypeEnum.HASH; }
		@Override public boolean forceBooleanValue() { return true; }
		@Override public int forceIntegerValue() { return 0; }
		@Override public double forceDoubleValue() { return Double.NaN; }
		@Override public String forceStringValue() { return "[object " + className + "]"; }
		@Override public String toString() { return forceStringValue(); }

		public EagleValue get(String name)
		{
			for (JsObject o = this; o != null; o = o.proto)
			{
				EagleValue v = o.props.get(name);
				if (v != null) return v;
			}
			return null;
		}
		public boolean has(String name) { return get(name) != null; }
		public void set(String name, EagleValue value) { props.put(name, value == null ? JsUndefined.INSTANCE : value); }
		public List<String> keys() { return new ArrayList<String>(props.keySet()); }
	}

	/** What a native function does. */
	public interface Builtin
	{
		EagleValue call(EagleInterpreter interpreter, EagleValue thisValue, List<EagleValue> args);
	}

	/** A function value: a grammar token with the environment it closed over, or a native. */
	public static final class JsFunction extends EagleToken
	{
		public final String name;
		public final JsEnv closure;
		public final Builtin builtin;
		/** Properties on the function itself: statics, prototype. */
		public final JsObject statics = new JsObject();
		public JsObject prototype;
		/** For a class: the class it extends. */
		public JsFunction parent;
		/** For a method: the object it was taken from, when bound. */
		public EagleValue boundThis;

		public JsFunction(String name, AbstractToken token, JsEnv closure)
		{
			super(token);
			this.name = name;
			this.closure = closure;
			this.builtin = null;
			this.prototype = new JsObject();
		}

		public JsFunction(String name, Builtin builtin)
		{
			super(null);
			this.name = name;
			this.closure = null;
			this.builtin = builtin;
		}

		public void className(String name) { prototype.className = name; }

		public JsFunction bind(EagleValue thisValue)
		{
			JsFunction f = builtin != null ? new JsFunction(name, builtin) : new JsFunction(name, getTokenValue(), closure);
			f.prototype = prototype;
			f.parent = parent;
			f.boundThis = thisValue;
			f.statics.props.putAll(statics.props);
			return f;
		}

		@Override public TypeEnum getType() { return TypeEnum.TOKEN; }
		@Override public boolean forceBooleanValue() { return true; }
		@Override public String forceStringValue() { return "[Function: " + name + "]"; }
		@Override public String toString() { return forceStringValue(); }
	}

	/** A JavaScript throw, carried through the engine's Java frames. */
	public static final class JsThrow extends RuntimeException
	{
		public final EagleValue value;
		public JsThrow(EagleValue value)
		{
			super(str(value));
			this.value = value;
		}
	}

	// ------------------------------------------------------------ conversions

	public static EagleValue undefined() { return JsUndefined.INSTANCE; }
	public static EagleValue nul() { return JsNull.INSTANCE; }
	public static boolean isNullish(EagleValue v) { return v == null || v instanceof JsUndefined || v instanceof JsNull; }
	public static EagleValue bool(boolean b) { return new EagleBoolean(b); }
	public static EagleValue str(String s) { return new EagleString(s); }
	public static EagleValue num(double d)
	{
		if (d == Math.rint(d) && !Double.isInfinite(d) && Math.abs(d) < Integer.MAX_VALUE) return new EagleInteger((int) d);
		return new EagleDouble(d);
	}
	public static EagleValue num(int i) { return new EagleInteger(i); }

	public static EagleValue of(EagleValue v) { return v == null ? JsUndefined.INSTANCE : v; }

	/** An empty array that already has its list. */
	public static EagleArray newArray()
	{
		EagleArray a = new EagleArray();
		a.setValues(new ArrayList<EagleValue>());
		return a;
	}

	/** The array's items, never null. */
	public static List<EagleValue> items(EagleValue v)
	{
		EagleArray a = (EagleArray) v;
		if (a.getArrayValue() == null) a.setValues(new ArrayList<EagleValue>());
		return a.getArrayValue();
	}

	public static boolean truthy(EagleValue v)
	{
		if (isNullish(v)) return false;
		if (v.isBoolean()) return v.forceBooleanValue();
		if (v.isString()) return v.forceStringValue().length() > 0;
		if (v.isInteger()) return v.forceIntegerValue() != 0;
		if (v.isDouble()) { double d = v.forceDoubleValue(); return d != 0 && !Double.isNaN(d); }
		return true;
	}

	public static double toNumber(EagleValue v)
	{
		if (isNullish(v)) return v instanceof JsNull ? 0 : Double.NaN;
		if (v.isInteger()) return v.forceIntegerValue();
		if (v.isDouble()) return v.forceDoubleValue();
		if (v.isBoolean()) return v.forceBooleanValue() ? 1 : 0;
		if (v.isString())
		{
			String s = v.forceStringValue().trim();
			if (s.isEmpty()) return 0;
			try { return Double.parseDouble(s); } catch (NumberFormatException ex) { return Double.NaN; }
		}
		return Double.NaN;
	}

	public static boolean isNumber(EagleValue v) { return v != null && (v.isInteger() || v.isDouble()); }

	/** The text an expression gives: String(x), `${x}`, and + with a string. */
	public static String toText(EagleValue v)
	{
		if (v == null) return "undefined";
		if (v.isDouble()) return numberText(v.forceDoubleValue());
		if (v.isArray()) return join((EagleArray) v, ",");
		return v.forceStringValue();
	}

	public static String numberText(double d)
	{
		if (Double.isNaN(d)) return "NaN";
		if (Double.isInfinite(d)) return d > 0 ? "Infinity" : "-Infinity";
		if (d == Math.rint(d) && Math.abs(d) < 1e21) return String.valueOf((long) d);
		String s = String.valueOf(d);
		return s.endsWith(".0") ? s.substring(0, s.length() - 2) : s;
	}

	public static String join(EagleArray a, String sep)
	{
		StringBuilder sb = new StringBuilder();
		List<EagleValue> items = items(a);
		for (int i = 0; i < items.size(); i++)
		{
			if (i > 0) sb.append(sep);
			EagleValue item = items.get(i);
			if (!isNullish(item)) sb.append(toText(item));
		}
		return sb.toString();
	}

	/** How console.log shows a value: strings bare at the top, quoted inside structures. */
	public static String show(EagleValue v, boolean top)
	{
		if (v == null) return "undefined";
		if (v.isString()) return top ? v.forceStringValue() : "'" + v.forceStringValue() + "'";
		if (v.isArray())
		{
			List<EagleValue> items = items(v);
			if (items.isEmpty()) return "[]";
			StringBuilder sb = new StringBuilder("[ ");
			for (int i = 0; i < items.size(); i++)
			{
				if (i > 0) sb.append(", ");
				sb.append(show(items.get(i), false));
			}
			return sb.append(" ]").toString();
		}
		if (v instanceof JsObject)
		{
			JsObject o = (JsObject) v;
			if (o.store != null)
			{
				StringBuilder sb = new StringBuilder(o.className).append("(").append(o.store.size()).append(") {");
				boolean first = true;
				for (EagleValue[] e : o.store.values())
				{
					sb.append(first ? " " : ", ");
					first = false;
					sb.append(show(e[0], false));
					if ("Map".equals(o.className)) sb.append(" => ").append(show(e[1], false));
				}
				return sb.append(first ? "}" : " }").toString();
			}
			if (o.props.isEmpty()) return "Object".equals(o.className) ? "{}" : o.className + " {}";
			StringBuilder sb = new StringBuilder("Object".equals(o.className) ? "{ " : o.className + " { ");
			boolean first = true;
			for (Map.Entry<String, EagleValue> e : o.props.entrySet())
			{
				if (!first) sb.append(", ");
				first = false;
				sb.append(e.getKey()).append(": ").append(show(e.getValue(), false));
			}
			return sb.append(" }").toString();
		}
		if (v instanceof JsFunction) return "[Function: " + ((JsFunction) v).name + "]";
		return toText(v);
	}

	public static String str(EagleValue v) { return show(v, true); }

	public static String typeOf(EagleValue v)
	{
		if (v == null || v instanceof JsUndefined) return "undefined";
		if (v instanceof JsNull) return "object";
		if (v instanceof JsFunction) return "function";
		if (v.isBoolean()) return "boolean";
		if (v.isString()) return "string";
		if (isNumber(v)) return "number";
		return "object";
	}

	public static boolean strictEquals(EagleValue a, EagleValue b)
	{
		a = of(a); b = of(b);
		if (a instanceof JsUndefined || b instanceof JsUndefined) return a instanceof JsUndefined && b instanceof JsUndefined;
		if (a instanceof JsNull || b instanceof JsNull) return a instanceof JsNull && b instanceof JsNull;
		if (isNumber(a) && isNumber(b)) return toNumber(a) == toNumber(b);
		if (a.isString() && b.isString()) return a.forceStringValue().equals(b.forceStringValue());
		if (a.isBoolean() && b.isBoolean()) return a.forceBooleanValue() == b.forceBooleanValue();
		if (a.isString() || b.isString() || isNumber(a) || isNumber(b) || a.isBoolean() || b.isBoolean()) return false;
		return a == b;
	}

	public static boolean looseEquals(EagleValue a, EagleValue b)
	{
		a = of(a); b = of(b);
		if (isNullish(a) || isNullish(b)) return isNullish(a) && isNullish(b);
		if (isNumber(a) && b.isString() || a.isString() && isNumber(b)) return toNumber(a) == toNumber(b);
		if (a.isBoolean() || b.isBoolean()) return toNumber(a) == toNumber(b);
		return strictEquals(a, b);
	}

	public static JsObject error(String message)
	{
		JsObject e = new JsObject();
		e.className = "Error";
		e.set("message", str(message));
		e.set("name", str("Error"));
		return e;
	}
}

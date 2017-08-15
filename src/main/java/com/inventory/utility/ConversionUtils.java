package com.inventory.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang.ArrayUtils;

public class ConversionUtils {

	public static Integer getSafeInteger(Object val) {
		return val != null ? getSafeBigDecimal(val).intValue() : null;
	}
	
	public static BigInteger getSafeBigInteger(Object val) {
		return val != null ? getSafeBigDecimal(val).toBigInteger() : null;
	}
	public static Long getSafeLong(Object val) {
		return val != null ? getSafeBigDecimal(val).longValue() : null;
	}

	public static String getSafeString(Object val) {
		return val != null ? String.valueOf(val) : null;
	}

	public static Double getSafeDouble(Object val) {
		return val != null ? getSafeBigDecimal(val).doubleValue() : null;
	}

	public static <E> boolean isEmpty(Collection<E> collection) {
		return collection == null || collection.isEmpty();
	}

	public static BigDecimal getSafeBigDecimal(Object val) {
		if (val == null) {
			return null;
		}

		if (val instanceof Short) {
			return new BigDecimal((Short) val);
		} else if (val instanceof Integer) {
			return new BigDecimal((Integer) val);
		} else if (val instanceof Long) {
			return new BigDecimal((Long) val);
		} else if (val instanceof BigInteger) {
			return new BigDecimal((BigInteger) val);
		} else if (val instanceof Float) {
			return new BigDecimal((Float) val);
		} else if (val instanceof Double) {
			return new BigDecimal((Double) val);
		} else if (val instanceof BigDecimal) {
			return (BigDecimal) val;
		} else {
			return null;
		}
	}


	public static byte[] toPrimitive(Byte[] bytesWrapper) {
		if (bytesWrapper == null) {
			return null;
		}
		return ArrayUtils.toPrimitive(bytesWrapper);
	}

	public static byte[] serialize(Object obj) throws IOException {
		if (obj == null) {
			return null;
		}
		try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
			try (ObjectOutputStream o = new ObjectOutputStream(b)) {
				o.writeObject(obj);
			}
			return b.toByteArray();
		}
	}

}

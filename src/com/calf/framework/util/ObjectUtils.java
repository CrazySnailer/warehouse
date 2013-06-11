package com.calf.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * 对象序列化工具类
 *
 */
public class ObjectUtils {
	/**
	 * 将对象序列化成BYTE字节
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytesFromObject(final Object obj) {
		ByteArrayOutputStream baos = null;
		Hessian2Output outh = null;
		try {
			baos = new ByteArrayOutputStream();
			outh = new Hessian2Output(baos);
			outh.writeObject(obj);
			outh.close();
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outh != null) {
				try {
					outh.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return new byte[0];
	}

	/**
	 * 从16进制中获取对象
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static Object getObjectFromHex(String s) throws IOException {
		byte b[] = new byte[s.length() / 2];
		int i = 0;
		for (int j = 0; i < b.length; j += 2) {
			b[i] = (byte) Integer.parseInt(s.substring(j, j + 2), 16);
			i++;
		}

		ByteArrayInputStream bin = null;
		Hessian2Input inh = null;
		Object obj = null;
		try {
			bin = new ByteArrayInputStream(b);
			inh = new Hessian2Input(bin);
			obj = inh.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inh != null) {
				try {
					inh.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bin != null) {
				try {
					bin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return obj;
	}
}

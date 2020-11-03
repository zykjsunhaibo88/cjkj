package com.neusoft.unieap.misc.license;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import org.apache.commons.logging.LogFactory;

import com.neusoft.unieap.config.EAPConfig;
import com.neusoft.unieap.config.SystemConfig;

public class Check {
	private static final String strPubkey = "308201b73082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a038184000281801c7d7e38b11505c7cf6b63880e83bfb2b3a2133cd0282aabfe84f7d757318acbd6d1181bd90c761bd227f472610df2d209078ad055037d1e1bc6a5557135bff43079d1047d6273cbf2157354e5aea47105716f3284014bc7cb3da52d49f9ce66dbb0d10209e66eba5cdd59457f3bb7e144dac507e6d4941403700caac86b706f";

	public Check() {
	}

	public static boolean check() {

		try {
			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(ByteHex
					.decodeHex(strPubkey));
			KeyFactory keyFactory = getkeyFactory("DSA");
			PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
			// read license
			InputStream in = null;
			// 将下面的路径进行混淆，增大破解的难度，可以采用给出密文，然后解码的方法。
			in = new FileInputStream(EAPConfig.web_root_path
					+ "/WEB-INF/conf/unieap/license/license.unieap");
			StringBuffer xml = new StringBuffer();
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
				xml.append(new String(buffer, 0, bytesRead));
			}
			in.close();

			License license = License.formXML(xml.toString());
			// 验证

			Signature signetcheck = getSignature("DSA");

			signetcheck.initVerify(pubKey);
			signetcheck.update(license.getFingerprint());
			if (signetcheck.verify(ByteHex.decodeHex(license.getSignature()))) {
				java.net.InetAddress address = java.net.InetAddress
						.getLocalHost();
				String ip = address.toString().substring(
						address.toString().indexOf("/") + 1);
				long currSysTime = System.currentTimeMillis();
				Date currDate = new Date(currSysTime);
				Date expiresDate = (new Date(license.getExpiresDate()));
				// 获取类型
				if (license.getLicenseType().equalsIgnoreCase(
						license.LICENSE_TYPE_DEMO)) {// 校验时间
					if (expiresDate.before(currDate))
						return true;
				} else if (license.getLicenseType().equalsIgnoreCase(
						license.LICENSE_TYPE_ENTERPRISE)) {// 校验ip
					if (!license.getHost().equals(ip))
						return true;
					if (expiresDate.before(currDate))
						return true;
				}
				return true;
			} else
				return false;
			// throw new Exception("Signature check failure.");
		} catch (Exception ex) {
		}
		return false;
	}

	public static boolean check(String path) {

		try {
			InputStream in = null;

			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(ByteHex
					.decodeHex(strPubkey));

			KeyFactory keyFactory = getkeyFactory("DSA");
			PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
			// 将下面的文件名进行混淆，增大破解的难度，可以采用给出密文，然后解码的方法。
			in = new FileInputStream(path + "/license.unieap");
			StringBuffer xml = new StringBuffer();
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
				xml.append(new String(buffer, 0, bytesRead));
			}
			in.close();
			// 对XML解密
			// 获得对象
			// Permit license = Permit.formXML(xml.toString());
			License license = License.formXML(xml.toString());
			// 验证
			Signature signetcheck = getSignature("DSA");
			signetcheck.initVerify(pubKey);
			signetcheck.update(license.getFingerprint());
			if (signetcheck.verify(ByteHex.decodeHex(license.getSignature()))) {
				java.net.InetAddress address = java.net.InetAddress
						.getLocalHost();
				String ip = address.toString().substring(
						address.toString().indexOf("/") + 1);
				long currSysTime = System.currentTimeMillis();
				Date currDate = new Date(currSysTime);
				Date expiresDate = (new Date(license.getExpiresDate()));
				// 获取类型
				if (license.getLicenseType().equalsIgnoreCase(
						license.LICENSE_TYPE_DEMO)) {// 校验时间
					if (expiresDate.before(currDate))
						return false;
				} else if (license.getLicenseType().equalsIgnoreCase(
						license.LICENSE_TYPE_ENTERPRISE)) {// 校验ip
					if (!license.getHost().equals(ip))
						return false;
					if (expiresDate.before(currDate))
						return false;
				}
				return true;
			} else
				return false;
			// throw new Exception("Signature check failure.");
		} catch (Exception ex) {
		}
		return false;
	}

	/**
	 * 用于检查license文件格式是否正确
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkFormat(String str) {
		try {
			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(ByteHex
					.decodeHex(strPubkey));
			KeyFactory keyFactory = getkeyFactory("DSA");
			PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
			License license = License.formXML(str);
			// 验证
			Signature signetcheck = getSignature("DSA");
			signetcheck.initVerify(pubKey);
			signetcheck.update(license.getFingerprint());
			if (signetcheck.verify(ByteHex.decodeHex(license.getSignature())))
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return false;
	}

	private static KeyFactory getkeyFactory(String algorithm) throws Exception {
		try {
			Class.forName("sun.security.provider.Sun");
		} catch (ClassNotFoundException e) {

			return KeyFactory.getInstance(algorithm);
		}

		return KeyFactory.getInstance(algorithm, (Provider) Class.forName(
				"sun.security.provider.Sun").newInstance());
	}

	private static Signature getSignature(String algorithm) throws Exception {
		try {
			Class.forName("sun.security.provider.Sun");
		} catch (ClassNotFoundException e) {

			return Signature.getInstance(algorithm);
		}

		return Signature.getInstance(algorithm, (Provider) Class.forName(
				"sun.security.provider.Sun").newInstance());
	}

}
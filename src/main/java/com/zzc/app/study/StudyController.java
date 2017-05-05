package com.zzc.app.study;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

@Controller
@RequestMapping("/appservice/study")
public class StudyController {

	/**
	 * 学习文件下载
	 * 
	 * @param request
	 * @param response
	 * @return null
	 */
	@RequestMapping(params = "download")
	public String downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.addHeader("Content-Disposition",
				"attachment;fileName=study.doc");// 设置文件名
		byte[] buffer = new byte[1024];
		InputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = StudyController.class
					.getResourceAsStream("/com/zzc/app/study/study.doc");
			bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * 安卓端下载
	 * 
	 * @param request
	 * @param response
	 * @return null
	 */
	@RequestMapping(params = "downloadApp")
	public String downloadApp(HttpServletRequest request,
			HttpServletResponse response) {
		response.reset();
		response.setContentType("application/force-download");// 设置强制下载不打开

		String fileName = this.getDownLoadFileName("sanyaTravel.apk", request);

		response.addHeader("Content-Disposition", "attachment;" + fileName);// 设置文件名
		byte[] buffer = new byte[1024];
		InputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = StudyController.class
					.getResourceAsStream("/com/zzc/app/study/三亚旅游.apk");
			response.setHeader("Content-Length",
					String.valueOf(fis.available()));
			bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	// 下载方法解析
	private String getDownLoadFileName(String filename,
			HttpServletRequest request) {
		String new_filename = null;
		try {
			new_filename = URLEncoder.encode(filename, "UTF8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String userAgent = request.getHeader("User-Agent");
		// System.out.println(userAgent);
		String rtn = "filename=\"" + new_filename + "\"";
		// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") != -1) {
				rtn = "filename=\"" + new_filename + "\"";
			}
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") != -1) {
				rtn = "filename*=UTF-8''" + new_filename;
			}
			// Safari浏览器，只能采用ISO编码的中文输出
			else if (userAgent.indexOf("safari") != -1) {
				try {
					rtn = "filename=\""
							+ new String(filename.getBytes("UTF-8"),
									"ISO8859-1") + "\"";
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			else if (userAgent.indexOf("applewebkit") != -1) {
				try {
					new_filename = MimeUtility
							.encodeText(filename, "UTF8", "B");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				rtn = "filename=\"" + new_filename + "\"";
			}
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") != -1) {
				rtn = "filename*=UTF-8''" + new_filename;
			}
		}
		return rtn;
	}

}
